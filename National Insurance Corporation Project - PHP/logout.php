<?php
session_start();
unset($_SESSION['username']); 
unset($_SESSION['userid']) ;
unset($_SESSION['usertype']);
header ("Location: default.php");

?>