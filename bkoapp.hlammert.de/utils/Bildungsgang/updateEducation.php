<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 27.10.2018
 * Time: 23:02
 */

    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

    $insertDescription = $_POST["description"];
    $insertDuration = $_POST["duration"];
    $insertKuerzel = $_POST["kuerzel"];
    $insertLongDescription = $_POST["longDescription"];
    $insertURL = $_POST["URL"];
    $interests = $_POST["interests"];
    $degrees = $_POST["degrees"];
    $additionalQualifications = $_POST["additionalQualification"];
    $dbid = $_POST["dbid"];

    $numberConditions = $_POST["numberConditions"];

    echo $insertLongDescription;
    echo $insertURL;
    echo $insertKuerzel;
    echo $insertDuration;

    zeigeParameter();

    $databasehandler = new DatabaseHandler();
    $dbh = $databasehandler->dbh;

    //Tabelle Bildungsgang Updaten
    /*$sql = "UPDATE `Bildungsgang` SET `Bezeichnung`= ?,`Dauer`= ?, `Beschreibung` = ?, `URL` = ?, `Kürzel` = ?  WHERE ID_Bildungsgang = ?";

    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("sdsssi", $insertDescription, $insertDuration, $insertLongDescription, $insertURL, $insertKuerzel, $dbid);
        $sql->execute();
    }*/

    $sql = "UPDATE `Bildungsgang` SET `Bezeichnung`= ?,`Dauer`= ?,`Beschreibung`= ?,`URL`= ?,`Kuerzel`= ? WHERE ID_Bildungsgang = ?";

    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("sdsssi", $insertDescription, $insertDuration, $insertLongDescription, $insertURL, $insertKuerzel, $dbid);
        $sql->execute();
    }

    echo "<br>UPDATE BILDUNSGANG Fehler: " .$sql->error;


    //Tabelle: nuetzlich_fuer --> Verlinkung von Interessen und Bildunsgang
    //Löschen der Alten Beziehungen => Anlegen von neuen

    $sql = "DELETE FROM `nuetzlich_fuer` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $dbid);
        $sql->execute();
    }
echo "<br>Fehler: " .$sql->error;

    foreach ($interests as $interest => $key)
    {
        $sqlUsefulFor = "INSERT INTO `nuetzlich_fuer`(`ID_Interessen`, `ID_Bildungsgang`) VALUES (?, ?)";

        if ($sqlUsefulFor = $dbh->prepare($sqlUsefulFor))
        {
            $sqlUsefulFor->bind_param("ii", $key, $dbid);
            $sqlUsefulFor->execute();
        }

        echo "<br>Fehler: " .$sqlUsefulFor->error;
    }

    //Tabelle : erreicht --> Verlinkung von Bildunsgang mit erreichbaren Abschlüssen
    //Löschen der Alten Beziehungen => Anlegen von neuen
    $sql = "DELETE FROM `erreicht` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $dbid);
        $sql->execute();
    }
echo "<br>Fehler: " .$sql->error;

    foreach($degrees as $degree => $key)
    {
        $sqlReachableDegree = "INSERT INTO `erreicht`(`ID_Bildungsgang`, `ID_Abschluss`) VALUES (? ,?)";

        if ($sqlReachableDegree = $dbh->prepare($sqlReachableDegree))
        {
            $sqlReachableDegree->bind_param("ii", $dbid, $key);
            $sqlReachableDegree->execute();
        }

        echo "<br>Fehler: " .$sqlReachableDegree->error;
    }

    //Tabelle : erhält --> Verlinkung von Bildungsgang mit zusätzlichen, erreichbaren Qualifikationen
    //Löschen der Alten Beziehungen => Anlegen von neuen
    $sql = "DELETE FROM `erhaelt` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $dbid);
        $sql->execute();
    }
echo "<br>Fehler: " .$sql->error;

    foreach($additionalQualifications as $additionalQualification => $key)
    {
        $sqlAdditionalQulification = "INSERT INTO `erhaelt`(`ID_Zusatzqualifikation`, `ID_Bildungsgang`) VALUES (?,?)";

        if ($sqlAdditionalQulification = $dbh->prepare($sqlAdditionalQulification))
        {
            $sqlAdditionalQulification->bind_param("ii", $key, $dbid);

            $sqlAdditionalQulification->execute();

            echo "<br> Fehler: " .$sqlAdditionalQulification->error;
        }
    }



    //Tabelle : benoetigt --> Verlinkung von Bildunsgang mit Abschluss, und Zusatzqualifikation
    //Löschen der Alten Beziehungen => Anlegen von neuen
    $sql = "DELETE FROM `benoetigt` WHERE ID_Bildungsgang = ?";
    if ($sql = $dbh->prepare($sql))
    {
        $sql->bind_param("i", $dbid);
        $sql->execute();
    }
echo "<br>Fehler: " .$sql->error;

    for ($i = 1; $i <= $numberConditions; $i++)
    {
        $strCondition_Education = "condition_%count%_education";
        $strCondition_Qualification = "condition_%count%_qualification";

        $strCondition_Education = str_replace("%count%", $i, $strCondition_Education);
        $strCondition_Qualification = str_replace("%count%", $i, $strCondition_Qualification);

        $sqlRequired = "INSERT INTO `benoetigt`(`ID_Bildungsgang`, `ID_Abschluss`, `ID_Zusatzqualifikation`) VALUES (? ,? ,?)";

        if ($sqlRequired = $dbh->prepare($sqlRequired))
        {
            $sqlRequired->bind_param("iii", $dbid,
                $_POST[$strCondition_Education], $_POST[$strCondition_Qualification]);

            $sqlRequired->execute();

            echo "<br> Fehler: " .$sqlRequired->error;
        }
    }
    increaseVersionCounter();

    header("Location: ../../main.php");
    exit();

?>