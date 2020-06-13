<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 16.10.2018
 * Time: 13:35
 */

require_once ($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

class Interesse
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

    public static function getAllInteressen()
    {
        $returnList = new SplDoublyLinkedList();
        $DatabaseHandler = new DatabaseHandler();
        $dbh = $DatabaseHandler->dbh;

        $sql = "SELECT * FROM Interessen";
        $result = $dbh->query($sql);

        while($row = $result->fetch_array())
        {
            $returnList->push(new Interesse($row["ID_Interesse"], $row["Beschreibung"]));
        }

        return $returnList;
    }
}