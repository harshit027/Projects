<?php
if(isset($_POST['customerId']) && isset($_POST['policyDate']))
{
$customerId=$_POST['customerId'];
$policyDate=$_POST['policyDate'];
$user1='u372567';
$pass1='tcstvm';
$server1='intvmunix1';
$con=mysql_connect($server1,$user1,$pass1);
mysql_select_db("u372567",$con);
$sql="UPDATE CUSTOMER SET policyDate='$policyDate' WHERE customerId=$customerId"; 
echo $sql;
mysql_query($sql);










}
?>

<html>
<head><title>Customer policy date change</title></head>
<body>
<form method='post'>
Customer ID <input type="text" name="customerId" value=""><br/>
Policy Date <input type="text" name="policyDate" value=""><br/>
<input type='submit' value='submit'>
</form>
</body>