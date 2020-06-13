<html>
<head>
    <title>BK Olsberg - Abschlüsse</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="./css/indexCss.css" />

    <!-- Bootstrap CDN, JS and JQuery -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script src="./script/abschluss.js"></script>

    <?php
        require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

        //echo $_SERVER["DOCUMENT_ROOT"];

        session_start();
        //zeigeParameter();

	checkAccess();
    ?>


</head>
<body>

<!-- Modal -->
<div class="modal fade insert-modal" tabindex="-1" role="dialog" aria-labelledby="modal-insert" aria-hidden="true">
    <form method="post" action="utils/Abschluesse/insertAbschluss.php">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
					Abschluss hinzufügen
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md"> Bezeichnung eingeben: </div>
						<div class="col-md"><input type="text" name="name" placeholder="Bezeichnung eingeben" required></div>
					</div>
					<div class="row">
						<div class="col-md"> Bildungs-Level eingeben: </div>
						<div class="col-md"><input type="number" name="level" min=1 max=100 value=1 required/></div>
					</div>
					<div class="row">
						<div class="col-md"> <button type="submit" class="btn btn-success">Speichern</button> </div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="updateModal">

</div>

<?php include("./navbar.html"); ?>

<br>

<div class="main">
	<table class="table table-striped">
		<thead>
			<tr>
				<td>ID</td>
				<td>Bezeichnung</td>
				<td>Bildungsstufe</td>
				<td> </td>
			</tr>
		</thead>
		<tbody>
		<?php
			require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");

			$databaseHandler = new DatabaseHandler();
			$dbh = $databaseHandler->dbh;

			$sql = "SELECT * FROM Abschluss ORDER BY Bildungsstufe DESC";

			$result = $dbh->query($sql);

			while($row = $result->fetch_array())
			{
				?>
			<tr>
				<td><?php echo $row["ID_Abschluss"]; ?></td>
				<td><?php echo $row["Bezeichnung"]; ?></td>
				<td><?php echo $row["Bildungsstufe"]; ?></td>
				<td><button class="btn btn-warning" onclick="createAbschlussUpdateModal('<?php echo $row["ID_Abschluss"]; ?>')">Editieren</button></td>
			</tr>
				<?php
			}
		?>


		<tr>
			<td> </td>
            <td><button class="btn btn-success" data-toggle="modal" data-target=".insert-modal">Abschluss hinzufügen</button></td>
            <td> </td>
            <td> </td>
		</tr>
		</tbody>
	</table>
</div>

</body>
</html>

