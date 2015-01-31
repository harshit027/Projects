function verifyUser()
{

var username=document.getElementById("txtUsername");

var password=document.getElementById("txtPassword");

var message=document.getElementById("errorMessage");
if(username.value=='')
{
   alert("Username not filled");
   username.focus();
   return false;
}
else
if(password.value=='')
{ 
   alert("Password not filled");
   password.focus();
   return false;
}
return true;

}


