<html>
<head>
    <title>BK Olsberg</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="./css/mainCss.css" />

    <!-- Bootstrap CDN, JS and JQuery -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="./script/main.js"></script>

    <?php
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Interesse.php");
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Bildungsgang.php");
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Abschluss.php");
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
        require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Zusatzqualifikation.php");
        require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

        checkAccess();


        $alleBildungsgange = new SplDoublyLinkedList();
        $alleInteressen = new SplDoublyLinkedList();
        $alleAbschlüsse = new SplDoublyLinkedList();
        $alleZusatzqualifikationen = new SplDoublyLinkedList();

        $databaseHandler = new DatabaseHandler();
        $dbh = $databaseHandler->dbh;

        $sql = "SELECT * FROM Bildungsgang";
        $result = $dbh->query($sql);

        //Alle Bidlungsgänge in LinkedList speichern
        while ($row = $result->fetch_array())
        {
            $alleBildungsgange->push(new Bildungsgang($row["ID_Bildungsgang"], $row["Bezeichnung"], $row["Dauer"], 
            $row["Beschreibung"], $row["URL"], $row["Kuerzel"]));
        }

        //Werden für das Modal "mainInsertModal.html" benötigt
        //Alle Interesses in LinkedList speichern
        $alleInteressen = Interesse::getAllInteressen();

        //Alle Abschlüsse in LinkedList speichern
        $alleAbschlüsse = Abschluss::getAllAbschlusse();

        //Alle Zusatzqualifikationen in LinkedList speichern
        $alleZusatzqualifikationen = Zusatzqualifikation::getAllZusatzqualifikationen();

    ?>
</head>
<div class="content container-fluid">
    <div class="container-fluid menubar">
	<?php include("./navbar.html"); ?>
    </div>
    <br>

    <!-- Modal für neuen Bildungsgang -->
    <?php
    include($_SERVER["DOCUMENT_ROOT"]. "/mainInsertModal.html");
    //include("./mainUpdateModal.php");
    ?>




    <div id="updateModal"></div>



    <!-- Main Area -->

    <?php
        require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

        $numberPerRow = 4;

        $numberEducation = countSQL("SELECT COUNT(ID_Bildungsgang) FROM Bildungsgang") + 1;
        //4 per row;
        $rows = ceil($numberEducation / $numberPerRow);

        for ($i = 0; $i < $rows; $i++)
        {
            ?>

            <div class="row">

                <?php

                    for ($u = 0; $u < $numberPerRow; $u++)
                    {
                        $educationNumber = ($numberPerRow * $i) + $u;

                        $notused = "";
                        if ($educationNumber > $alleBildungsgange->count())
                            $notused="empty-education";
                        ?>
                            <div class="col-md education <?php echo $notused; ?>">
                                <h3>Bildungsgang</h3>

                                <?php


                                    if ($educationNumber < $alleBildungsgange->count()) {
                                        echo "Bezeichnung: " . $alleBildungsgange->offsetGet($educationNumber)->getDescription();
                                        echo "<br>Dauer: " . $alleBildungsgange->offsetGet($educationNumber)->getDuration() . " Jahre";
                                        $id = $alleBildungsgange->offsetGet($educationNumber)->getID();
                                        ?>
                                            <button class="btn btn-secondary btn-lower-right" onclick="createMainUpdateModal('<?php echo $id;?>')">Anzeigen / Ändern</button>
                                        <?php
                                    }
                                    else if ($educationNumber == ($alleBildungsgange->count()))
                                    {
                                        echo "<h5>Neuen Bildungsgang anlegen</h5>";
                                        ?>
                                        <button class="btn btn-success btn-lower-right" data-toggle="modal" data-target=".insert-modal" onclick="conditionNumberChanged(1)">Bildungsgang hinzufügen</button>
                                        <?php
                                    }
                                    else
                                    {
                                        echo "--";
                                        $id = -1;
                                    }
                                ?>

                            </div>
                        <?php
                    }

                ?>

            </div>

            <?php
        }
    ?>

</div>
</html>
