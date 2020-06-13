<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 07.01.2019
 * Time: 13:37
 */

    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

    checkAccess();

    $updateDescription = $_POST["description"];
    $dbid = $_POST["dbid"];

    zeigeParameter();

    $databasehandler = new DatabaseHandler();
    $dbh = $databasehandler->dbh;

    //Tabelle Bildungsgang Updaten
    $sql = "UPDATE `Interessen` SET `Beschreibung`= ? WHERE ID_Interesse = ?";

    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("si", $updateDescription, $dbid);
        $sql->execute();
    }

    echo "<br>UPDATE INTERESSEN Fehler: " .$sql->error;
    increaseVersionCounter();

    header("Location: ../../Interessen.php");
    exit();

?>