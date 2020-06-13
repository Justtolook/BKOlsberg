<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 16.10.2018
 * Time: 16:48
 */

require_once ($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

class Zusatzqualifikation
{
    var $id;
    var $description;

    function __construct($id, $description)
    {
        $this->id = $id;
        $this->description = $description;
    }

    function getID()
    {
        return $this->id;
    }

    function getDescription()
    {
        return $this->description;
    }

    static function getAllZusatzqualifikationen()
    {
        $returnList = new SplDoublyLinkedList();
        $DatabaseHandler = new DatabaseHandler();
        $dbh = $DatabaseHandler->dbh;
        $sql = "SELECT * FROM Zusatzqualifikation";
        $result = $dbh->query($sql);

        while ($row = $result->fetch_array())
        {
            $returnList->push(new Zusatzqualifikation($row["ID_Zusatzqualifikation"], $row["Bezeichnung"]));
        }

        return $returnList;
    }
}