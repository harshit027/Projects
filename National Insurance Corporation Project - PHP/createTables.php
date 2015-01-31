<?php
$user1='u372567';
$pass1='tcstvm';
$server1='localhost';

$con=mysql_connect($server1,$user1,$pass1);

if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

  if (mysql_query("CREATE DATABASE u372567",$con))
  {
  echo "Database created";
  }
else
  {
  echo "Error creating database: " . mysql_error();
  }


mysql_select_db("u372567",$con);


$sql="CREATE TABLE CLERK (clerkId int PRIMARY KEY NOT NULL AUTO_INCREMENT, username VARCHAR(10), password VARCHAR(10), clerkType int)";
mysql_query($sql);

$sql="INSERT INTO CLERK VALUES(1,'acc','accpass',1)";
mysql_query($sql);

$sql="INSERT INTO CLERK VALUES(2,'pnc','pncpass',2)";
mysql_query($sql);

$sql="SELECT * FROM CLERK";
$result=mysql_query($sql);
echo '<br />';
if(mysql_num_rows($result)>0)
	{
		while($row = mysql_fetch_array($result))
			echo ($row["clerkId"]."\t".$row["username"]."\t".$row["password"]."\t".$row["clerkType"]."<BR />");
	}




$sql="CREATE TABLE CUSTOMER (customerId int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), age int, gender VARCHAR(6), policyId int, policyDuration int, nominee VARCHAR(20), address VARCHAR(20), maturedAmount DECIMAL(9,2), policyDate DATE) ";
mysql_query($sql);


$sql="CREATE TABLE POLICY (id int PRIMARY KEY AUTO_INCREMENT, policyId int, policyDuration int, amountCovered int, premium int, interest DECIMAL(4,2), bonus DECIMAL(4,2))";
mysql_query($sql);


$sql="CREATE TABLE CLAIM(claimId int PRIMARY KEY AUTO_INCREMENT, customerId int, claimdate DATE, claimStatus varchar(10), FOREIGN KEY(customerId) REFERENCES customer(customerId))";
mysql_query($sql);

$policies=array
(
array(101,100000,20000,9,1,6),
array(101,100000,20000,9,2,7),
array(101,100000,20000,9,3,8),
array(102,200000,38000,9.5,1,6.5),
array(102,200000,38000,9.5,2,7.5),
array(102,200000,38000,9.5,3,8.5),
array(103,300000,56000,10,1,7.5),
array(103,300000,56000,10,2,8.5),
array(103,300000,56000,10,3,9.5),
array(104,500000,92500,11,1,9.5),
array(104,500000,92500,11,2,11),
array(104,500000,92500,11,3,13)
);
  
for($i=0;$i<count($policies);$i++)
{
$policy=$policies[$i];
$sql="INSERT INTO POLICY VALUES(".($i+1).",$policy[0],$policy[4],$policy[1],$policy[2],$policy[3],$policy[5])";
mysql_query($sql);
}








mysql_close($con);


?>