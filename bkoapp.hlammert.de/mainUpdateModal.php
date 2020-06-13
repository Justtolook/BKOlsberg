<!-- Update Modal -->
<div class="modal fade update-modal" tabindex="-1" role="dialog" aria-labelledby="modal-update-education" aria-hidden="true">
    <form method="post" action="utils/Bildungsgang/updateEducation.php">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Bildungsgang ändern</h5>
                </div>

                <?php
                    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
                    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Bildungsgang.php");
                    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

                    checkAccess();

                    $listDegrees = Abschluss::getAllAbschlusse();
                    $listQualifications = Zusatzqualifikation::getAllZusatzqualifikationen();
                    $listInterests = Interesse::getAllInteressen();


                    $dbid = $_GET["dbid"];

                    $DatabaseHandler = new DatabaseHandler();

                    $dbh = $DatabaseHandler->dbh;

                    $sql = "SELECT * FROM Bildungsgang WHERE ID_Bildungsgang = ?";

                    if($sql = $dbh->prepare($sql))
                    {
                        $sql->bind_param("i", $dbid);
                        $sql->execute();

                        $result = $sql->get_result();

                        while ($row = $result->fetch_array())
                        {
                            $actualElement = new Bildungsgang($row["ID_Bildungsgang"], $row["Bezeichnung"], $row["Dauer"], 
                            $row["Beschreibung"], $row["URL"], $row["Kuerzel"]);
                        }
                    }

                    $gewahlteInteressen = $actualElement->getInterests();
                    $gewählteAbschlüsse = $actualElement->getReachableEducations();
                    $gewählteZusatzqualiikationen = $actualElement->getReachableQualifications();
                    $usedListRequirements = $actualElement->getRequirements();
                ?>

                <div class="modal-body">
                    <div class="container">

                        <input type="number" name="dbid" style="display: none;" value="<?php echo $dbid; ?>"/>

                        <div class="row">
                            <div class="col-8"><h5>Bezeichnung</h5></div>
                            <div class="col"><h5>Dauer</h5></div>
                            <div class="col"><h5>Kürzel</h5></div>
                        </div>

                        <div class="row">
                            <div class="col-8">
                                <input class="input-form input-editable" type="text" placeholder="Bezeichnung" required name="description"
                                       disabled value="<?php echo $actualElement->getDescription(); ?>">
                            </div>
                            <div class="col">
                                <input class="input-form input-editable" type="number" placeholder="Dauer" min="0" max="5" step="0.1"
                                       required name="duration" disabled value="<?php echo $actualElement->getDuration(); ?>">
                            </div>
                            <div class="col">
                                <input class="input-form input-editable" type="text" placeholder="Kürzel" 
                                       required name="kuerzel" disabled value="<?php echo $actualElement->getKuerzel(); ?>">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-8">
                                <h5>Beschreibung</h5>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <textarea class="input-form input-editable" placeholder="Beschreibung des Bildungsganges"
                                    required name="longDescription" disabled
                                    style="width: 100%" row="10" cols="70"><?php echo $actualElement->getLongDescription(); ?></textarea>
                            </div>
                        </div>

                        <br>

                        <div class="row">
                            <div class="col-md"><h5>Interessen</h5><br>
                                <select class="select-form input-editable" name="interests[]" size="6" multiple="true" required disabled>
                                    <?php
                                            foreach ($listInterests as $interest)
                                            {
                                                $selected = "";
                                                $id = $interest->getID();
                                                $description = $interest->getDescription();

                                                foreach($gewahlteInteressen as $usedInterest)
                                                {
                                                    if ($usedInterest->getID() == $interest->getID())
                                                        $selected = "selected";
                                                }

                                    echo "<option value='" .$id. "'" .$selected. ">" .$description. "</option>\n";
                                    }
                                    ?>
                                </select>
                            </div>
                            <div class="col-md"><h5>Erreichbare Abschlüsse</h5><br>
                                <select class="select-form input-editable" name="degrees[]" size="6" multiple="true" required disabled>
                                    <?php
                                        foreach ($listDegrees as $degree)
                                        {
                                            $selected = "";
                                            $id = $degree->getID();
                                            $description = $degree->getDescription();
                                            $level = $degree->getLevel();

                                            foreach ($gewählteAbschlüsse as $usedEducation)
                                            {
                                                if ($usedEducation->getID() == $degree->getID())
                                                    $selected = "selected";
                                            }

                                    echo "<option value='" .$id. "' " .$selected. ">" .$description. "</option>\n";
                                    }
                                    ?>
                                </select>
                            </div>
                            <div class="col-md"><h5>Erreichbare Zusatzqualifikationen</h5>
                                <select class="select-form input-editable" name="additionalQualification[]" size="6" multiple="true" required disabled>
                                    <?php
                                        foreach($listQualifications as $qualification)
                                        {
                                            $selected = "";
                                            $id = $qualification->getID();
                                            $description = $qualification->getDescription();

                                            foreach($gewählteZusatzqualiikationen as $usedQualification)
                                            {
                                                if ($usedQualification->getID() == $qualification->getID())
                                                    $selected = "selected";
                                            }

                                    echo "<option value='" .$id. "' " .$selected. ">" .$description. "</option>\n";
                                    }
                                    ?>
                                </select>
                            </div>
                        </div>
                        <br>

                        <div class="row">
                            <div class="col"><h5>Bedingungen</h5></div>
                        </div>
                        <div class="row">
                            <div class="col"><h6>Anzahl der Bedingungen: </h6></div>
                            <div class="col"><input type="number" id="numberConditions_Update" class="input-form input-editable"
                                                    name="numberConditions" onchange="conditionNumberChanged(this.value)"
                                                    required placeholder="1" value="<?php echo $usedListRequirements->count() ?>" min="1" max="5" disabled></div>
                        </div>

                        <br>

                        <div class="row">
                            <div class="col"><h6>Benötigter Abschluss</h6></div>
                            <div class="col"><h6>Benötigte Zusatzqualifikation</h6></div>
                        </div>

                        <br>

                        <?php //Dynamicly Generate 5 Select Views for 5 Differnt Conditions
                            for ($x = 0; $x < 5; $x++)
                            {
                                $style=null;
                                $required = "required";
                                if (!($x < $usedListRequirements->count()))
                                {
                                    $style="none";
                                    $required = "";
                                }

                                if ($x >= $usedListRequirements->count())
                                    $requirement = null;
                                else
                                    $requirement = $usedListRequirements->offsetGet($x);

                                ?>
                                <div class="row Condition_<?php echo $x+1; ?>" style="display: <?php echo $style; ?>;">
                                    <div class="col">
                                        <select name="condition_<?php echo $x+1; ?>_education"
                                                class="condition_<?php echo $x+1; ?>_education form-control input-editable" disabled multiple="false" <?php echo $required; ?> size="1">
                                            <?php
                                            foreach($listDegrees as $degree)
                                            {
                                                $selected = "";
                                                $id = $degree->getID();
                                                $description = $degree->getDescription();

                                                if ($requirement != null) {
                                                    if ($degree->getID() == $requirement->getEducationID())
                                                        $selected = "selected";
                                                }
                                                echo "<option value='" .$id. "' ".$selected.">" .$description. "</option>\n";
                                            }
                                            ?>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <select name="condition_<?php echo $x+1; ?>_qualification"
                                                class="condition_<?php echo $x+1; ?>_qualification form-control input-editable" disabled multiple="false" <?php echo $required; ?> size="1">
                                            <?php
                                            foreach($listQualifications as $qualification)
                                            {
                                                $selected = "";
                                                $id = $qualification->getID();
                                                $description = $qualification->getDescription();

                                                if ($requirement != null) {
                                                    if ($qualification->getID() == $requirement->getQualificationID())
                                                        $selected = "selected";
                                                }

                                                echo "<option value='" .$id. "' ".$selected.">" .$description. "</option>\n";
                                            }
                                            ?>
                                        </select>
                                    </div>
                                </div>
                        <?php
                            }
                            ?>

                        <div class="row">
                            <div class="col"><h5>Link zum Bildungsgang</h5></div>
                        </div>
                        <div class="row">
                            <div class="col"><input class="input-form input-editable" type="text" placeholder="URL" name="URL" disabled
                                 style="width: 100%" required value="<?php echo $actualElement->getURL(); ?>"></div>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" id="button-edit" class="btn btn-warning" onclick="allowEdit()">Editieren</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Schließen</button>
                    <button type="submit" id="update-save" class="btn btn-primary" style="display: none;">Speichern und Schließen</button>
                    <button type="button" id="button-delete" class="btn btn-danger" style="display: none;" onclick="deleteBildungsgang(<?php echo $dbid; ?>)">Löschen</button>
                </div>
            </div>
        </div>
    </form>
</div>
