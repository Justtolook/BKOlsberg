<?phhp
require "conn.php";


$name=$_POST["name"];
$surname=$_POST["surname"];
$age=$_POST["age"];
$username=$_POST["username"];
$password=$_POST["password"];
$mysql_qry="SELECT * FROM Interessen";

if($conn->query(mysql_qry)===TRUE)
{
		while($row=mysqli_fetch_array($query))
		{
			$flag[]=$row;
		}
		
		print(json_encode($flag));
}
$conn->close();
?>