<?php
session_start();
if(isset($_SESSION['username']) && isset($_SESSION['userid']) && isset($_SESSION['usertype']) && $_SESSION['usertype']==1)
{
$user=$_SESSION['username'];
}
else
header("Location: default.htm");
$msg="";
if(isset($_POST['process']))
if($_POST['process']=='yes')
{
$username='u372567';
$password='tcstvm';
$server='intvmunix1';

$con=mysql_connect($server,$username,$password);
mysql_select_db("u372567",$con);

$sql1="SELECT customerId,policyId,policyDuration,policyDate,maturedAmount FROM CUSTOMER where DATEDIFF(CURDATE(),policyDate)/365>=policyDuration && maturedAmount=0";

$result=mysql_query($sql1);
if($result==null)
$msg="<br />No matured policies";
else
if(mysql_num_rows($result)>0)
	{
		while($row = mysql_fetch_array($result))
			{
                        {
                          $sql2="SELECT interest,bonus,premium FROM POLICY WHERE policyDuration=$row[2] AND policyId=$row[1]";
                          $result1=mysql_query($sql2);
                          if($result1==null)
				                $msg=("<br/> Error processing policies ");
				else
				{
				$row1=mysql_fetch_array($result1);
			       $maturedAmount=$row1[2]+($row1[2]*$row1[0]/100)+($row[2]*$row1[1]/100);
                           $sql3="UPDATE CUSTOMER SET maturedAmount=$maturedAmount WHERE customerId=$row[0]"; 
				mysql_query($sql3);
             	                $msg=("<br/> Process completed Successfully ");
				}
                        }}

	}
else
$msg="<br />No policies matured";

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
<form name='form1' action='processMaturity.php' method='post'>

<table align='center' border=1 cellpadding='5' cellspacing='0' style='width:500; background:008888; color:#ffffff;'>
<tr>
<th >Process Matured Policies</th>
</tr>
<input type='hidden' name='process' value='yes'>
<tr>
<th >
<input class = 'button'  type='submit'  value='Process' >
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