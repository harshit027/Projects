<?php

session_start();

if(isset($_SESSION['username']) && isset($_SESSION['userid']) && isset($_SESSION['usertype']) && $_SESSION['usertype']==1)
{
$name=$_SESSION['username'];
}
else
header ("Location: default.php");

if(isset($_POST['customerPolicy']))
{
$policyId=$_POST['customerPolicy'];
$custName=$_POST['customerName'];
$custAge=$_POST['customerAge'];
$custGender=$_POST['customerGender'];
$custAddress=$_POST['customerAddress'];
$custNominee=$_POST['customerNominee'];
$custDuration=$_POST['customerDuration'];
$maturedAmount=0;
$user1='u372567';
$pass1='tcstvm';
$server1='intvmunix1';

$con=mysql_connect($server1,$user1,$pass1);
mysql_select_db("u372567",$con);
$sql="SELECT policyId,policyDuration from POLICY where policyId=$policyId and policyDuration=$custDuration;";
$result=mysql_query($sql);
$policy=-1;
if(mysql_num_rows($result)>0)
	{
		while($row = mysql_fetch_array($result))
			$policy=$row['policyId'];
	}

if($policy!=-1)
{
	$sql="INSERT INTO CUSTOMER (name,age,gender,policyId,policyDuration,nominee,address,maturedAmount,policyDate) VALUES ('$custName',$custAge,'$custGender',$policyId,$custDuration,'$custNominee','$custAddress',$maturedAmount,CURDATE());";
	mysql_query($sql);
	$sql="SELECT customerId from CUSTOMER where name='$custName' and address='$custAddress'";
	$result=mysql_query($sql);
	$claimId=-1;
	if(mysql_num_rows($result)>0)
		{
			while($row = mysql_fetch_array($result))
				$claimId=$row['customerId'];
		}

	if($claimId==-1)
		{
		$msg="Error adding customer";
		}
		else
		$msg="New customer added. Customer id = $claimId";
		
}

else
$msg="PolicyId doesn't exist.";
}


?>




<html>

<head>
<title>Welcome to NIC</title>
<link rel='stylesheet' href='styles.css' type='text/css'>
<script type='text/javascript' src='menuOperations.js'>
</script>
<script type='text/javascript' src='customerDetailsValidation.js'>
</script>
</head>
<body onLoad='form1.customerName.focus();'>
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
?></div>
<form name='form1'  method='post'>

<table align='center' border=1 cellpadding='5' cellspacing='0' style='width:500; background:008888; color:#ffffff;'>
<tr>
<th colspan='2'>Customer Registration</th>
</tr>
<tr>
<td width='30%'>Name</td>
<td align='left'><input type='text' style='position:relative;left:10px;' name='customerName' id='customerName' onBlur='validateName()'>  
<span style='position:relative;left:10px;' id="errorName" style="color:'#EE0000'"></span>
</td>
</tr>
<tr>
<td>Age</td>
<td align='left'><input type='text' style='position:relative;left:10px;' name='customerAge' id='customerAge' onBlur='validateAge()'>
<span  style='position:relative;left:10px;' id="errorAge" style="color:'#EE0000'"></span>
</td>

</tr>
<tr>
<td>Gender</td>
<td>
<select style='position:relative;left:10px;' id='customerGender' name='customerGender'>
<option value='m'>Male</option>
<option value='f'>Female</option>
</select>
</td>
</tr>
<tr>
<td>Policy Id</td>
<td align='left'><input type='text' style='position:relative;left:10px;' name='customerPolicy' id='customerPolicy'></td>
<span id="errorPolicy"  style='position:relative;left:10px;' style="color:'#EE0000'"></span>
</tr>
<tr>
<td>Policy Duration</td>
<td align='left'><input  style='position:relative;left:10px;' type='text' name='customerDuration' id='customerDuration'></td>
<span style='position:relative;left:10px;' id="errorDuration" style="color:'#EE0000'"></span>
</tr>


<tr>
<td>Nominee Details</td>
<td align='left'><input style='position:relative;left:10px;' type='text' name='customerNominee' id='customerNominee' onBlur='validateNominee();'>
<span style='position:relative;left:10px;' id="errorNominee" style="color:'#EE0000'"></span>
</td>
</tr>
<tr>
<td>Address</td>
<td align='left'><input style='position:relative;left:10px;' type='text' name='customerAddress' id='customerAddress' onBlur='validateAddress();'>
<span style='position:relative;left:10px;' id="errorAddress" style="color:'#EE0000'"></span>
</td>
</tr>
<tr>
<th colspan='2'>
<input type='button' class = 'button'  value='Register' onclick='validateDetails()'>
<input type='reset' class = 'button' ></th>
</tr>
</table>

</form>

</div>

<div style='background:#008888; color:#ffffff; height: 20px; width:100%; text-align:center;'>
<h4>All Rights reserved with &copy National Insurance Co.</h4>
</div>
</body>
</html>