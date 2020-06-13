<?php

	require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
	require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

	checkAccess();

	$DatabaseHandler = new DatabaseHandler();

	zeigeParameter();

	$dbh = $DatabaseHandler->dbh;

	$id = $_GET["dbid"];

	$sql = "DELETE FROM Interessen WHERE ID_Interesse = ?";

	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From Interessen: " .$sql->error;

	$sql = "DELETE FROM nuetzlich_fuer WHERE ID_Interesse = ?";
	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From nuetzlich_fuer: " .$sql->error;
	increaseVersionCounter();

	header("Location: ../../Interessen.php");
	exit();

?>