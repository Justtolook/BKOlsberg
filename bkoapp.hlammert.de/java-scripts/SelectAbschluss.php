<?php
$host='127.0.0.1';
$username='bkoapp_admin';
$pwd='KP955JeFAeGmHZmZckesdPyEbSFgMeDn';
$db="bkoapp";

$con=mysqli_connect($host,$username,$pwd,$db) or die ('Unable to Connect');

if(mysqli_connect_error($con))
{
	echo"Failed to Connect to Database ".mysqli_connect_error();
}

$query=mysqli_query($con, "SELECT * FROM Abschluss");

if($query)
{
	while($row=mysqli_fetch_array($query))
	{
		$flag[]=$row;
	}
	
	print(json_encode($flag));
}
mysqli_close($con);
?>