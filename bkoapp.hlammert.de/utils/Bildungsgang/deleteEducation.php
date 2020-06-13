<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 28.10.2018
 * Time: 19:15
 */


    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

    $DatabaseHandler = new DatabaseHandler();
    $dbh = $DatabaseHandler->dbh;

    $id = $_GET["dbid"];

    $sql = "DELETE FROM `Bildungsgang` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $id);
        $sql->execute();
    }
    echo "<br>Fehler Delete From Bildungsgang: " .$sql->error;

    $sql = "DELETE FROM `benoetigt` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $id);
        $sql->execute();
    }
    echo "<br>Fehler Delete From benotigt: " .$sql->error;

    $sql = "DELETE FROM `erhaelt` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $id);
        $sql->execute();
    }
    echo "<br>Fehler Delete From erhaelt: " .$sql->error;

    $sql = "DELETE FROM `erreicht` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $id);
        $sql->execute();
    }
    echo "<br>Fehler Delete From erreicht: " .$sql->error;

    $sql = "DELETE FROM `nuetzlich_fuer` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $id);
        $sql->execute();
    }
    echo "<br>Fehler Delete From nuetzlich_fuer: " .$sql->error;
    increaseVersionCounter();

    header("Location: ../../main.php");
    exit();


    ?>