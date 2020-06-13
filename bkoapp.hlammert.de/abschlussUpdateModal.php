<!-- Update Modal -->
<div class="modal fade update-modal" tabindex="-1" role="dialog" aria-labelledby="update-modal" aria-hidden="true">
    <form method="post" action="utils/Abschluesse/updateAbschluss.php">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Abschluss ändern</h5>
                </div>

                <?php
                    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
                    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Bildungsgang.php");
                    require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/Abschluss.php");
                    require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

                    checkAccess();

                    $listInterests = Abschluss::getAllAbschlusse();

                    $dbid = $_GET["dbid"];

                    $DatabaseHandler = new DatabaseHandler();

                    $dbh = $DatabaseHandler->dbh;

                    $sql = "SELECT * FROM Abschluss WHERE ID_Abschluss = ?";

                    if($sql = $dbh->prepare($sql))
                    {
                        $sql->bind_param("i", $dbid);
                        $sql->execute();

                        $result = $sql->get_result();

                        while ($row = $result->fetch_array())
                        {
                            $actualElement = new Abschluss($row["ID_Abschluss"]
                                , $row["Bezeichnung"], $row["Bildungsstufe"]);
                        }
                    }

                    $gewahlteAbschlusse = $actualElement;
                ?>

                <div class="modal-body">
                    <div class="container">

                        <input type="number" name="dbid" style="display: none;" value="<?php echo $dbid; ?>"/>

                        <div class="row">
                            <div class="col"><h5>Bezeichnung</h5></div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <input class="input-form input-editable" type="text" placeholder="Bezeichnung" required name="description"
                                       disabled value="<?php echo $actualElement->getDescription(); ?>">
                            </div>
                        </div>

                        <div class="row">
                            <div class="col"><h5>Bildungsstufe</h5></div>
                        </div>

                        <div class="row">
                            <div class="col">
                                <input type="number" class="input-form input-editable" placeholder="<?php echo $actualElement->getLevel(); ?>" required name="level"
                                disabled value="<?php echo $actualElement->getLevel(); ?>">
                            </div>
                        </div>

                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" id="button-edit" class="btn btn-warning" onclick="allowEdit()">Editieren</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Schließen</button>
                    <button type="submit" id="update-save" class="btn btn-primary" style="display: none;">Speichern und Schließen</button>
                    <button type="button" id="button-delete" class="btn btn-danger" style="display: none;" onclick="deleteAbschluss(<?php echo $dbid; ?>)">Löschen</button>
                </div>
            </div>
        </div>
    </form>
</div>
