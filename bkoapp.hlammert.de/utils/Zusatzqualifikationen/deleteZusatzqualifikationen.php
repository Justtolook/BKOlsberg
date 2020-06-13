<?php

	require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
	require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

	checkAccess();

	$DatabaseHandler = new DatabaseHandler();

	zeigeParameter();

	$dbh = $DatabaseHandler->dbh;

	$id = $_GET["dbid"];

	$sql = "DELETE FROM Zusatzqualifikation WHERE ID_Zusatzqualifikation = ?";

	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From Zusatzqualifikation: " .$sql->error;

	$sql = "DELETE FROM benoetigt WHERE ID_Zusatzqualifikation = ?";
	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From benoetigt: " .$sql->error;

	$sql = "DELETE FROM erhaelt WHERE ID_Zusatzqualifikation = ?";
	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From erhaelt: " .$sql->error;
	

	increaseVersionCounter();

	header("Location: ../../Zusatzqualifikationen.php");
	exit();

?>