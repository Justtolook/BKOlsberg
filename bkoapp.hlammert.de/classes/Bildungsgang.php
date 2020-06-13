<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 16.10.2018
 * Time: 12:32
 */

require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Zusatzqualifikation.php");
require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Abschluss.php");
require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Interesse.php");
require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Bedingung.php");


class Bildungsgang
{
    var $id;
    var $description;
    var $duration;
    var $url;
    var $longDescription;
    var $kuerzel;

    var $interests;
    var $reachableQualifications;
    var $reachableEducations;

    var $requirements;

    function __construct($id, $description, $duration, $longDescription, $url, $kuerzel)
    {
        $this->id = $id;
        $this->description = $description;
        $this->duration = $duration;
        $this->url = $url;
        $this->longDescription = $longDescription;
        $this->kuerzel = $kuerzel;

        $this->interests = new SplDoublyLinkedList();
        $this->reachableEducations = new SplDoublyLinkedList();
        $this->reachableQualifications = new SplDoublyLinkedList();
        $this->requirements = new SplDoublyLinkedList();

        $this->getInterestsFromDB();
        $this->getQualificationsFromDB();
        $this->getEducationsFromDB();
        $this->getRequirementsFromDB();
    }

    //Getter & (Setter)
    function getDescription() { return $this->description; }

    function getDuration() { return $this->duration; }

    function getLongDescription() { return $this->longDescription; }

    function getKuerzel() { return $this->kuerzel; }

    function getURL() { return $this->url; }

    function getInterests() { return $this->interests; }

    function getReachableEducations() {return $this->reachableEducations;}

    function getReachableQualifications() {return $this->reachableQualifications;}

    function getRequirements() {return $this->requirements;}

    function getID()
    {
        return $this->id;
    }

    //Methods
    function getInterestsFromDB()
    {
        $sql = "SELECT i.ID_Interesse, i.Beschreibung FROM Interessen i, nuetzlich_fuer f, Bildungsgang b 
                WHERE i.ID_Interesse = f.ID_Interessen AND f.ID_Bildungsgang = b.ID_Bildungsgang AND b.ID_Bildungsgang = ?";

        $DatabaseHandler = new DatabaseHandler();
        $dbh = $DatabaseHandler->dbh;

        if ($sqlInterests = $dbh->prepare($sql))
        {
            $sqlInterests->bind_param("i",$this->id);
            $sqlInterests->execute();

            $result = $sqlInterests->get_result();

            while ($row = $result->fetch_array()) {
                $this->interests->push(new Interesse($row["ID_Interesse"], $row["Beschreibung"]));
            }
        }
    }

    function getQualificationsFromDB()
    {
        $sql = "SELECT z.ID_Zusatzqualifikation, z.Bezeichnung FROM Zusatzqualifikation z, Bildungsgang b, erhaelt e 
                WHERE b.ID_Bildungsgang = e.ID_Bildungsgang AND e.ID_Zusatzqualifikation = z.ID_Zusatzqualifikation AND b.ID_Bildungsgang = ? ";

        $DatabaseHandler = new DatabaseHandler();

        $dbh = $DatabaseHandler->dbh;

        if ($sql = $dbh->prepare($sql))
        {
            $sql->bind_param("i", $this->id);

            $sql->execute();
            $result = $sql->get_result();

            while ($row = $result->fetch_array())
            {
                $this->reachableQualifications->push(new Zusatzqualifikation($row["ID_Zusatzqualifikation"], $row["Bezeichnung"]));
            }
        }
    }

    function getEducationsFromDB()
    {
        $sql = "SELECT a.Bezeichnung, a.Bildungsstufe, a.ID_Abschluss
                FROM Abschluss a, erreicht e, Bildungsgang b
                WHERE a.ID_Abschluss = e.ID_Abschluss AND e.ID_Bildungsgang = b.ID_Bildungsgang AND b.ID_Bildungsgang = ?";

        $DatabaseHandler = new DatabaseHandler();

        $dbh = $DatabaseHandler->dbh;

        if ($sql = $dbh->prepare($sql))
        {
            $sql->bind_param("i", $this->id);
            $sql->execute();

            $result = $sql->get_result();

            while ($row = $result->fetch_array())
            {
                $this->reachableEducations->push(new Abschluss($row["ID_Abschluss"], $row["Bezeichnung"], $row["Bildungsstufe"]));
            }
        }
    }

    function getRequirementsFromDB()
    {
        $sql = "SELECT a.Bezeichnung AS 'aBez' , a.Bildungsstufe, a.ID_Abschluss, z.Bezeichnung AS 'zBez', z.ID_Zusatzqualifikation
                FROM Bildungsgang b, Abschluss a, Zusatzqualifikation z, benoetigt e
                WHERE a.ID_Abschluss = e.ID_Abschluss AND z.ID_Zusatzqualifikation = e.ID_Zusatzqualifikation AND e.ID_Bildungsgang = b.ID_Bildungsgang AND b.ID_Bildungsgang = ?";

        $DatabaseHandler = new DatabaseHandler();
        $dbh = $DatabaseHandler->dbh;

        if ($sql = $dbh->prepare($sql))
        {
            $sql->bind_param("i", $this->id);
            $sql->execute();

            $result = $sql->get_result();

            while($row = $result->fetch_array())
            {
                $qTemp = new Zusatzqualifikation($row["ID_Zusatzqualifikation"], $row["zBez"]);
                $eTemp = new Abschluss($row["ID_Abschluss"], $row["aBez"], $row["Bildungsstufe"]);

                $this->requirements->push(new Bedingung($qTemp, $eTemp));
            }
        }
    }
}