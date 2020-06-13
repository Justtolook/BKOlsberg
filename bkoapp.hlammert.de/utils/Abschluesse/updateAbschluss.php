<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 15.01.2019
 * Time: 20:22
 */

    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

    checkAccess();

    $updateDescription = $_POST["description"];
    $updateLevel = $_POST["level"];
    $dbid = $_POST["dbid"];

    zeigeParameter();

    $databasehandler = new DatabaseHandler();
    $dbh = $databasehandler->dbh;

    //Tabelle Bildungsgang Updaten
    $sql = "UPDATE `Abschluss` SET `Bezeichnung`= ?, `Bildungsstufe` = ? WHERE ID_Abschluss = ?";

    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("sii", $updateDescription, $updateLevel, $dbid);
        $sql->execute();
    }

    echo "<br>UPDATE Abschluss Fehler: " .$sql->error;
    increaseVersionCounter();

    header("Location: ../../Abschlüsse.php");
    exit();

?>