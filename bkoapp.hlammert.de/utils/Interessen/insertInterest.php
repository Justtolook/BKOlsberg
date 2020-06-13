<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 16.10.2018
 * Time: 21:54
 */

    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

    $insertDescription = $_POST["name"];
   	
    zeigeParameter();

    $databasehander = new DatabaseHandler();
    $dbh = $databasehander->dbh;

    // Tabelle : Interessen --> Neue Interessen anlegen
    $sqlInteressen = "INSERT INTO Interessen (`Beschreibung`) VALUES (?)";

    if ($sql = $dbh->prepare($sqlInteressen))
    {
        $sql->bind_param("s", $insertDescription);

        $sql->execute();

    }
    increaseVersionCounter();

    
    header("Location: ../../Interessen.php");
    exit();
?>
