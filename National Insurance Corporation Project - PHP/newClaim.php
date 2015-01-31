<?php

session_start();

if(isset($_SESSION['username']) && isset($_SESSION['userid']) && isset($_SESSION['usertype']) && $_SESSION['usertype']==2)
{
$name=$_SESSION['username'];
}
else
header ("Location: default.php");

if(isset($_POST['txtCustomerId']))
{
$custId=$_POST['txtCustomerId'];

$user1='u372567';
$pass1='tcstvm';
$server1='localhost';

$con=mysql_connect($server1,$user1,$pass1);
mysql_select_db("u372567",$con);
$sql="SELECT customerId from CUSTOMER where customerId=$custId";
$result=mysql_query($sql);
$cust=-1;
if(mysql_num_rows($result)>0)
	{
		while($row = mysql_fetch_array($result))
			$cust=$row['customerId'];
	}

if($cust!=-1)
{
	$sql="INSERT INTO CLAIM (customerId, claimdate, claimStatus) VALUES ($custId,CURDATE(),'new')";
	mysql_query($sql);
	$sql="SELECT claimId from CLAIM where customerId=$custId";
	$result=mysql_query($sql);
	$claimId=-1;
	if(mysql_num_rows($result)>0)
		{
			while($row = mysql_fetch_array($result))
				$claimId=$row['claimId'];
		}

	if($claimId==-1)
		{
		$msg="Error adding claim";
		}
		else
		$msg="New claim added. Claim id = $claimId";
		
}

else
$msg="CustomerId doesnt exist.";
}


?>

<html>

<head>
<title>Welcome to NIC</title>
<link rel='stylesheet' href='styles.css' type='text/css'>

<script type='text/javascript' src='menuOperations.js' >

</script>
<script type='text/javascript' src='claimDetailsValidation.js' >

</script>
</head>
<body onload="form1.customerId.focus()">
<div style='background:#008888; color:#ffffff; height: 40px; width:100%; text-align:center;'>
<h2>National Insurance Co.</h2>
</div>

<diV style='width:100%; height:470;background-image:url("images/lg.gif");'>


<table align='left' width='100' >
<tr>
<td><a href='pcHome.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>Home</a></td>
<td><a href='newClaim.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>NewClaim</a></td> 
<td><a href='updateClaim.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>UpdateClaim</a></td>
</tr>
</table>



<table align='right' width='50'>
<tr>
<td><a href='logout.php' class='class1'onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>Logout</a></td> 
</tr>
</table>
<br><br><br><br>
<div id='msg' align='center' style='color:red'><?php 
echo $msg;
?></div>
<form name='form1'  method='post'>

<table align='center' border=1 cellpadding='5' cellspacing='0' style='width:500; background:008888; color:#ffffff;'>
<tr>
<th colspan='2'><u>Claim Registration</u></th>
</tr>
<tr style='text-align:center;'>
<td width='30%'>Customer Id</td>
<td align='left'><input  style='position:relative;left:10px;' type='text' id='customerId' name='txtCustomerId' onBlur='validateCustomerId()'>
<span style='position:relative;left:10px;' id="errorCustomerId" style="color:'#EE0000'"></span></td>
</tr>

<tr>
<th colspan='2'>
<input type='button' value='Add Claim' class = 'button' onclick='validateDetails()'>
<input type='reset' class = 'button'  value='Cancel'> 
</th>
</tr>
</table>

</form>

</div>

<div style='background:#008888; color:#ffffff; height: 20px; width:100%; text-align:center;'>
<h4>All Rights reserved with &copy National Insurance Co.</h4>
</div>
</body>
</html>