<?php

session_start();

if(isset($_SESSION['username']) && isset($_SESSION['userid']) && isset($_SESSION['usertype']) && $_SESSION['usertype']==1)
{
$name=$_SESSION['username'];
}
else
header ("Location: default.php");

$msg="";

if(isset($_POST['claimId']))
{
$claimId=$_POST['claimId'];


$user1='u372567';
$pass1='tcstvm';
$server1='localhost';

$con=mysql_connect($server1,$user1,$pass1);
mysql_select_db("u372567",$con);

$sql="SELECT claimId, claimStatus from CLAIM where claimId=$claimId";
$result=mysql_query($sql);
$cust=-1;
if(mysql_num_rows($result)>0)
	{
		while($row = mysql_fetch_array($result))
			{
			$cust=$row['claimId'];
			$claimStatus=$row['claimStatus'];
			}
	}

if($cust!=-1)
{
	if($claimStatus!='accepted')
	{
	$msg="Error settling claim. Claim is $claimStatus";
	}
	else
	{
	$sql="UPDATE CLAIM set claimStatus='settled' where claimId=$claimId";
	mysql_query($sql);
	$msg="Claim has been settled";
	}
		
}

else
$msg="ClaimId doesnt exist.";
}


?>

<html>

<head>
<title>Welcome to NIC</title>
<link rel='stylesheet' href='styles.css' type='text/css'>
<script type='text/javascript' src='menuOperations.js'>
</script>

<script type='text/javascript' src='claimSettleDetailsValidation.js'>
</script>

</head>
<body>
<div style='background:#008888; color:#ffffff; height: 40px; width:100%; text-align:center;'>
<h2>National Insurance Co.</h2>
</div>
<diV style='width:100%; height:470;background-image:url("images/lg.gif");'>


<table align='left' width='100' >
<tr>
<td><a href='accHome.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>Home</a></td>
<td><a href='newCustomer.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>NewCustomer</a></td> 
<td><a href='reports.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>Reports</a></td>
<td><a href='settleClaim.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>SettleClaim</a></td> 
<td><a href='processMaturity.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>ProcessMaturity</a></td>

</tr>
</table>



<table align='right' width='50'>
<tr>
<td><a href='logout.php' class='class1' onmouseover='changeColor(this)' onmouseout='restoreColor(this)'>Logout</a></td> 
</tr>
</table>
<br><br><br><br>
<div id='msg' align='center' style='color:red'><?php 
echo $msg;
?></div><form name='form1'  method='post'>

<table align='center' border=1 cellpadding='5' cellspacing='0' style='width:500; background:008888; color:#ffffff;'>
<tr>
<th colspan='2'>Claim Settlement</th>
</tr>

<tr>
<td width='30%'>Claim Id</td>
<td align='left'>
<input style='position:relative;left:10px;' type='text' id='claimId' name='claimId' onBlur='validateClaimId()'>
<span style='position:relative;left:10px;' id="errorClaimId" style="color:'#EE0000'"></span>
</td>
</tr>

<tr>
<th colspan='2'>
<input class = 'button'  type='button' value='Settle Claim' onclick='validateDetails()' >
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