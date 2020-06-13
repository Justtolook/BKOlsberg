<?php

	require_once($_SERVER["DOCUMENT_ROOT"]. "/classes/DatabaseHandler.php");
	require_once($_SERVER["DOCUMENT_ROOT"]. "/utils/functions.php");

	checkAccess();

	$DatabaseHandler = new DatabaseHandler();

	zeigeParameter();

	$dbh = $DatabaseHandler->dbh;

	$id = $_GET["dbid"];

	$sql = "DELETE FROM Abschluss WHERE ID_Abschluss = ?";

	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From Abschluss: " .$sql->error;

	$sql = "DELETE FROM benoetigt WHERE ID_Abschluss = ?";
	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From benoetigt: " .$sql->error;

	$sql = "DELETE FROM erreicht WHERE ID_Abschluss = ?";
	if ($sql = $dbh->prepare($sql))
	{
		$sql->bind_param("i", $id);
		$sql->execute();
	}
	echo "<br>Fehler bei Delete From erreicht: " .$sql->error;
	increaseVersionCounter();

	header("Location: ../../Abschluss.php");
	exit();

?>