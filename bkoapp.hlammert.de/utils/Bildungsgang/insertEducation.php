<?php
/**
 * Created by PhpStorm.
 * User: Christian
 * Date: 16.10.2018
 * Time: 21:54
 */


    echo "Hallo??";   

    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

    $insertDescription = $_POST["insertDescription"];
    $insertDuration = $_POST["insertDuration"];
    $insertLongDescription = $_POST["insertLongDescription"];
    $insertURL = $_POST["insertURL"];
    $insertKuerzel = $_POST["insertKuerzel"];
    $interests = $_POST["interests"];
    $degrees = $_POST["degrees"];
    $additionalQualifications = $_POST["additionalQualification"];

    $numberConditions = $_POST["numberConditions"];

    zeigeParameter();

    $databasehander = new DatabaseHandler();
    $dbh = $databasehander->dbh;

    // Tabelle : Bildungsgang --> Neuen Bildungsgang anlegen
    $sqlEducation = "INSERT INTO Bildungsgang (`Bezeichnung`, `Dauer`, `Beschreibung`, `Kuerzel`, `URL`) VALUES (?, ?, ?, ?, ?)";

    if ($sqlBildunsgang = $dbh->prepare($sqlEducation))
    {
        $sqlBildunsgang->bind_param("sdsss", $insertDescription,
            $insertDuration, $insertLongDescription, $insertKuerzel, $insertURL);

        $sqlBildunsgang->execute();

    }

    //Tabelle: nuetzlich_fuer --> Verlinkung von Interessen und Bildunsgang

    $EducationID = getLastElement("ID_Bildungsgang", "Bildungsgang");

    foreach ($interests as $interest => $key)
    {
        $sqlUsefulFor = "INSERT INTO `nuetzlich_fuer`(`ID_Interessen`, `ID_Bildungsgang`) VALUES (?, ?)";

        if ($sqlUsefulFor = $dbh->prepare($sqlUsefulFor))
        {
            $sqlUsefulFor->bind_param("ii", $key, $EducationID);

            $sqlUsefulFor->execute();

            echo "<br> Fehler: " .$sqlUsefulFor->error;
        }
    }

    //Tabelle : erreicht --> Verlinkung von Bildunsgang mit erreichbaren Abschlüssen

    foreach($degrees as $degree => $key)
    {
        $sqlReachableDegree = "INSERT INTO `erreicht`(`ID_Bildungsgang`, `ID_Abschluss`) VALUES (? ,?)";

        if ($sqlReachableDegree = $dbh->prepare($sqlReachableDegree))
        {
            $sqlReachableDegree->bind_param("ii", $EducationID, $key);
            $sqlReachableDegree->execute();

            echo "<br> Fehler: " .$sqlReachableDegree->error;
        }
    }

    //Tabelle : erhält --> Verlinkung von Bildungsgang mit zusätzlichen, erreichbaren Qualifikationen
    foreach($additionalQualifications as $additionalQualification => $key)
    {
        $sqlAdditionalQulification = "INSERT INTO `erhaelt`(`ID_Zusatzqualifikation`, `ID_Bildungsgang`) VALUES (?,?)";

        if ($sqlAdditionalQulification = $dbh->prepare($sqlAdditionalQulification))
        {
            $sqlAdditionalQulification->bind_param("ii", $key, $EducationID);

            $sqlAdditionalQulification->execute();

            echo "<br> Fehler: " .$sqlAdditionalQulification->error;
        }
    }

    //Tabelle : benoetigt --> Verlinkung von Bildunsgang mit Abschluss, und Zusatzqualifikation
    for ($i = 1; $i <= $numberConditions; $i++)
    {
        $strCondition_Education = "condition_%count%_education";
        $strCondition_Qualification = "condition_%count%_qualification";

        $strCondition_Education = str_replace("%count%", $i, $strCondition_Education);
        $strCondition_Qualification = str_replace("%count%", $i, $strCondition_Qualification);

        $sqlRequired = "INSERT INTO `benoetigt`(`ID_Bildungsgang`, `ID_Abschluss`, `ID_Zusatzqualifikation`) VALUES (? ,? ,?)";

        if ($sqlRequired = $dbh->prepare($sqlRequired))
        {
            $sqlRequired->bind_param("iii", $EducationID,
                $_POST[$strCondition_Education], $_POST[$strCondition_Qualification]);

            $sqlRequired->execute();

            echo "<br> Fehler: " .$sqlRequired->error;
        }
    }
    increaseVersionCounter();

    header("Location: ../../main.php");
    exit();
?>