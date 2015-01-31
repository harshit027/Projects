<?php

session_start();


if(isset($_SESSION['username']) && isset($_SESSION['userid']) && isset($_SESSION['usertype']) && $_SESSION['usertype']==1)
{
$name=$_SESSION['username'];
}
else
header ("Location: default.php");


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
<br>
Welcome to Accounts department <?php echo $name ?>
</div>

<div style='background:#008888; color:#ffffff; height: 20px; width:100%; text-align:center;'>
<h4>All Rights reserved with &copy National Insurance Co.</h4>
</div>
</body>
</html>