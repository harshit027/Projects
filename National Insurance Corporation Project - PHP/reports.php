<?php

session_start();

if(isset($_SESSION['username']) && isset($_SESSION['userid']) && isset($_SESSION['usertype']) && $_SESSION['usertype']==1)
{
$name=$_SESSION['username'];
}
else
header ("Location: default.php");

$msg="";

if(isset($_POST['customerChoice']))
{
$reportChoice=$_POST['customerChoice'];

$user1='u372567';
$pass1='tcstvm';
$server1='localhost';

$con=mysql_connect($server1,$user1,$pass1);
mysql_select_db("u372567",$con);

$sql="";

if($reportChoice=='product')
{
$sql="SELECT * FROM CUSTOMER ORDER BY policyId,policyDuration";
}

if($reportChoice=='claim')
{
$sql="SELECT * FROM CLAIM where claimStatus='Settled' AND Month(claimdate)= Month(CURDATE()) AND YEAR (claimdate)= YEAR (CURDATE()); ";
}


if($reportChoice=='matured')
{
$sql="SELECT * FROM CUSTOMER where maturedAmount > 0 AND Month(policyDate)= Month(CURDATE()) AND (YEAR (policyDate) + policyDuration )= YEAR (CURDATE()); ";
}

if($reportChoice=='newPolicy')
{
$sql="SELECT * FROM CUSTOMER where Month(policyDate)= Month(CURDATE()) AND YEAR (policyDate)= YEAR (CURDATE()); ";
}


if($sql!="")
{


$result=mysql_query($sql);
$count=0;
$count1=0;
$msgheader=
"
<table style='background:#008888; color:#ffffff; ' align='center' border='1'>
<tr>

";



if(mysql_num_rows($result)>0)
{
	
while($row = mysql_fetch_array($result))
{

	$msg.="<tr>";
	foreach($row as $field=>$value)
	{


	if($count==0)
	{
	if($count1==0) {$msgheader.="<th colspan='".count($row)."'>".$reportChoice." Report</th></tr><tr>"; $count1++;}
	if(!is_int($field))
	$msgheader.="<td>".$field."</td>";
	}
	if(!is_int($field))
	$msg.="<td>$value</td>\n";
	}
	$count++;
	$msg.="</tr>";


}
$msgheader.="</tr>";
$msg=$msgheader.$msg."</table>";

}
else
$msg="No records found";
}

}


?>



<html>

<head>
<title>Welcome to NIC</title>
<link rel='stylesheet' href='styles.css' type='text/css'>
<script type='text/javascript' src='menuOperations.js'>
</script>
</head>
<body>
<div style='background:#008888; color:#ffffff; height: 40px; width:100%; text-align:center;'>
<h2>National Insurance Co.</h2>
</div>

<diV style='width:100%; height:470; background-image:url("images/lg.gif");'>


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

<form name='form1' action='reports.php' method='post'>

<table align='center' border=1 cellpadding='5' cellspacing='0' style='width:500; background:008888; color:#ffffff;'>
<tr>
<th colspan='2'>Generate Reports</th>
</tr>

<tr>
<td>Gender</td>
<td>
<select id='reportChoice' name='customerChoice'>
<option value='product'>Product</option>
<option value='claim'>Claim</option>
<option value='matured'>Matured</option>
<option value='newPolicy'>New Policies</option>

</select>
</td>
</tr>

<tr>
<th colspan='2'>
<input  class = 'button' type='submit' value='Generate' >
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