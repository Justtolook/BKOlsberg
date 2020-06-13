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
    $insertLevel = $_POST["level"];
   	
    zeigeParameter();

    $databasehander = new DatabaseHandler();
    $dbh = $databasehander->dbh;

    // Tabelle : Interessen --> Neue Interessen anlegen
    $sqlAbschluss = "INSERT INTO Abschluss (`Bezeichnung`, `Bildungsstufe`) VALUES (?, ?)";

    if ($sql = $dbh->prepare($sqlAbschluss))
    {
        $sql->bind_param("si", $insertDescription, $insertLevel);

        $sql->execute();

    }
    echo "Fehler bei INSERT Abschluss: " .$sql->error;
    increaseVersionCounter();

    
    //header("Location: ../../Abschluss.php");
    exit();
?>
