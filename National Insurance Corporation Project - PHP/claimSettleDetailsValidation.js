function validateDetails()
{

if(validateClaimId())
{
form1.action='settleClaim.php';
form1.submit();
}
else
document.getElementById('claimId').focus();
}

function validateClaimId()
{
var claimId=document.getElementById('claimId');

if(claimId.value=='')
{
document.getElementById('errorClaimId').innerHTML='Field cannot be empty';
return false;
}
else
if(parseInt(claimId.value)!=claimId.value || claimId.value<1)
{
document.getElementById('errorClaimId').innerHTML='Claim Id has to be integer';
return false;
}
else
{
document.getElementById('errorClaimId').innerHTML='';
return true;
}


}

