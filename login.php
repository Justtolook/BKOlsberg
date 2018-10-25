<?phhp
require "conn.php";
$user_name=$_POST["user_name"];
$user_pass=$_POST["password"];
$mysql_qry="Select * from employee_data where username like '$user_name' and password ='$user_pass'";
$result = mysql_query($conn, $mysql_qry);
if(mysqli_num_rows($result)>0){
	$row = mysqli_fetch_assoc($result);
	$name = $row["name"];
	echo"login success";
}
else
{
	echo"login not success";
?>