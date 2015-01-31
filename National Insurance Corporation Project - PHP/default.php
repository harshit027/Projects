<?php
session_start();

$msg="";
if(isset($_POST['username']) && isset($_POST['password']))
{
$user=$_POST['username'];
$pass=$_POST["password"];
$userdb='u372567';
$passdb='tcstvm';
$serverdb='localhost';

$con=mysql_connect($serverdb,$userdb,$passdb);
mysql_select_db("u372567",$con);

$sql="select clerkId, clerkType from CLERK where username='$user' and password='$pass'";
$result=mysql_query($sql);
if (mysql_num_rows($result)>0)
	{
	$row=mysql_fetch_array($result);
	session_start();
	$_SESSION['username']=$user;
	$_SESSION['userid']=$row[0];
	$_SESSION['usertype']=$row[1];
	session_write_close();
	if($_SESSION['usertype']==1)
	{
	//acct clerk
	session_write_close();
	header ("Location:accHome.php");
	}
	else
	if($_SESSION['usertype']==2)
	{
	//pnc clerk
	session_write_close();
	header ("Location:pcHome.php");
	}
	
	}
	else
	{
	$msg="Invalid username/password";
	}


}
else
{
if(isset($_SESSION['username']) &&isset($_SESSION['userid']) &&isset($_SESSION['usertype']))
	if($_SESSION['usertype']==1)
	{
	//acct clerk

	header ("Location: accHome.php");
	}
	else
	if($_SESSION['usertype']==2)
	{
	//pnc clerk

	header ("Location: pcHome.php");
	}
}


?>

<html>


<head>
<title>Welcome to NIC</title>

<link rel='stylesheet' href='styles.css' type='text/css'>
<style type="text/css">
.tabSpan
{
background-color:#008888;
color:#ffffff;
height:200px;
width:450px;
position:absolute;
top:30px;
}




</style>
<script type='text/javascript' src='loginCheck.js'>
</script>
<script type='text/javascript' src='menuOperations.js'>
</script>
<script type='text/javascript' src='homePageScripts.js'>

</script>
</head>
<body>

<div style='background:#008888; color:#ffffff; height: 40; width:100%; text-align:center;'>
<h2>National Insurance Co.</h2>
</div>
<div style='background-image:url("images/lg.gif");  height: 470; width:100%;' >

<table width='100%'  style='height: 230' cellpadding='0'>
<tr>
<td style='vertical-align:top;'>
<img src='images/logo.jpg' height='100px' width='90%' >
</td>
<td style=' vertical-align:top;'>
<table width='300' align='center'  cellspacing='0' style='  color:#008888; border-color:#008888;'>
<tr >
<th colspan='2' ><u>Employee Login</u><br><br></th>
</tr>
<form  method='post' onSubmit='return verifyUser();'>
<tr style='text-align:center;border:0px' >
<td>Username : </td>
<td><input type='text' id='txtUsername' name='username' >
</tr>
<tr style='text-align:center' >
<td>Password : </td>
<td><input type='password' id='txtPassword' name='password' >
</tr>
<tr>
<td colspan='2' ><input type='submit' style='position:relative;left:160px;' style='background-color:#008888; color:#ffffff;' value='Login' ></form></td>
</tr>
<tr>
<th colspan='2'>
<div id='errorMessage' style='text-align:center; background:#dddddd; color:#008888;'> <?php echo $msg;?>
</div>
</th>
</tr>
</table>
</td>
</tr>
<tr>
<td >
<img src='images/2.jpg' width='200' height='100px' onmouseup="window.open('howToLodgeAClaim.htm')" ><br><br>
<img src='images/1.jpg' width='200' height='100px' onmouseup="window.open('howToMakeAPolicy.htm')" >
</td>
<td style='vertical-align:top;'>
<table  width='450' align='center' style='background:#eeeeee'>
<tr>
<td style='position:absolute;top:250px'>

<span style="cursor:pointer" onMouseover="overProd();"  onMouseOut="window.status=''"  onMouseup="showProduct();" onFocus="" onBlur="">
<img src='images/products.jpg'style=""/>
<img id='product1' src='images/products1.jpg'style="visibility:visible;position:absolute;left:1px;"/>
</span>

<span style="cursor:pointer" onMouseover="overAbout();" onMouseOut="window.status=''" onMouseup="showAbout();" onFocus="" onBlur="" style="position:relative;left='-3px'">
<img src='images/aboutUs.jpg'  />
<img id='aboutus1' src='images/aboutUs1.jpg'style="visibility:hidden;position:absolute;left:0px;" />
</span>


<span style="cursor:pointer" onMouseover="overContact();" onMouseOut="window.status=''" onMouseup="showContact();" onFocus="" onBlur=""  style="position:relative;left='-6px'">
<img src='images/contactUs.jpg' />
<img id='contactus1' src='images/contactUs1.jpg' style="visibility:hidden;position:absolute;left:0px;"/>
</span>





</span>
<br />


<div class='tabSpan' id='productSpan' style="position:absolute;visibility:visible" >
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enjoy benefits of latest policies:
<ul>
<li>Medical Insurance Policies .</li>
<li>Travel Insurance Policies .</li>
<li>Vehicle Insurance Policies .</li>
<li>Life Insurance Policies .</li>

</ul>
 
</div>



<div class='tabSpan' id='contactSpan' style="position:absolute;visibility:hidden">

<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;For any information and queries:
<ul>
<li>Please call our Toll Free Customer care helpline 1800 123 456 </li>
<li>SMS NIC to 55555</li>
<li>Log on to <a href="http://www.nic.com">www.nic.com</a></li>
<li>You can also write to us at National Insurance Co, MG Road, Trivandrum </li>
</ul> 
</div>




<div class='tabSpan' id='aboutSpan' style="position:absolute;visibility:hidden">
<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;National Insurance Company (A Govt. of India undertaking) is the &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;largest insurance provider in India.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Established in 1906, it is the oldest insurance company in India.<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We at National Insurance strive to provide the best services to all our &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;policy holders. 


</div>

</td> 
</tr>
<tr>
<td>
<span id='homePageMessages' style='font-family:times; font-italic:true; font-bold:true;'>

</span>
</td>
</tr>
</table>
</td>

</tr>

</table>



</div>
<div style='background:#008888; color:#ffffff; height: 10; width:100%; text-align:center;'>
<h4>All Rights reserved with &copy National Insurance Co.</h4>
</div>
</body>
</html>