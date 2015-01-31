function validateDetails()
{


if(validateCustomerId())
{
form1.action='newClaim.php'
form1.submit();
}
else
document.getElementById('customerId').focus();


}





function validateCustomerId()
{

var customerId=document.getElementById('customerId');

if(customerId.value=='')
{
document.getElementById('errorCustomerId').innerHTML='Field cannot be empty';
return false;
}
else
if(parseInt(customerId.value)!=customerId.value || customerId.value<1)
{
document.getElementById('errorCustomerId').innerHTML='Customer Id has to be integer';
return false;
}
else
{
document.getElementById('errorCustomerId').innerHTML='';
return true;
}



}