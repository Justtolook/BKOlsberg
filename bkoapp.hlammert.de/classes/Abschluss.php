<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 16.10.2018
 * Time: 16:13
 */

require_once ($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

class Abschluss
{
    var $id;
    var $description;
    var $level;

    function __construct($id, $description, $level)
    {
        $this->id = $id;
        $this->description = $description;
        $this->level = $level;
    }

    function getID()
    {
        return $this->id;
    }

    function getDescription()
    {
        return $this->description;
    }

    function getLevel()
    {
        return $this->level;
    }

    static function getAllAbschlusse()
    {
        $returnList = new SplDoublyLinkedList();
        $DatabaseHandler = new DatabaseHandler();
        $dbh = $DatabaseHandler->dbh;

        $sql = "SELECT * FROM Abschluss";
        $result = $dbh->query($sql);

        while($row = $result->fetch_array())
        {
            $returnList->push(new Abschluss($row["ID_Abschluss"], $row["Bezeichnung"], $row["Bildungsstufe"]));
        }

        return $returnList;
    }
}