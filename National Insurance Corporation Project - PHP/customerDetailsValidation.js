function validateDetails()
{
if(validateName() && validateAge() && validateNominee() && validateAddress())
{
form1.action='newCustomer.php';
form1.submit();
}
else
{
if(!validateName()){document.getElementById('customerName').focus();} 
else
if(!validateAge()){document.getElementById('customerAge').focus();} 
else
if(!validateNominee()){document.getElementById('customerNominee').focus();} 
else
if(!validateAddress()){document.getElementById('customerAddress').focus();} 
}
}



function validateName()
{
var name=document.getElementById('customerName');
if(name.value=='')
{
document.getElementById('errorName').innerHTML="Field cannot be empty";
return false;
}
else
{
document.getElementById('errorName').innerHTML='';
return true;
}

}

function validateAge()
{

var age=document.getElementById('customerAge');

if(age.value=='')
{
document.getElementById('errorAge').innerHTML='Field cannot be empty';
return false;
}
else
if(parseInt(age.value)!=age.value || age.value<1)
{
document.getElementById('errorAge').innerHTML='Age has to be integer';
return false;
}
else
{
document.getElementById('errorAge').innerHTML='';
return true;
}
}



function validateNominee()
{
var nominee=document.getElementById('customerNominee');
if(nominee.value=='')
{
document.getElementById('errorNominee').innerHTML="Field cannot be empty";
return false;
}
else
{
document.getElementById('errorNominee').innerHTML="";
return true;
}
}


function validateAddress()
{
var address=document.getElementById('customerAddress');
if(address.value=='')
{
document.getElementById('errorAddress').innerHTML="Field cannot be empty";
return false;
}
else
{
document.getElementById('errorAddress').innerHTML="";
return true;
}
}