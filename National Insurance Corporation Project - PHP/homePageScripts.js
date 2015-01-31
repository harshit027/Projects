function showAbout()
{
document.getElementById('productSpan').style.visibility='hidden';
document.getElementById('contactSpan').style.visibility='hidden';
document.getElementById('aboutSpan').style.visibility='visible';

document.getElementById('aboutus1').style.visibility='visible';
document.getElementById('product1').style.visibility='hidden';
document.getElementById('contactus1').style.visibility='hidden';
}

function showContact()
{
document.getElementById('productSpan').style.visibility='hidden';
document.getElementById('aboutSpan').style.visibility='hidden';
document.getElementById('contactSpan').style.visibility='visible';

document.getElementById('contactus1').style.visibility='visible';
document.getElementById('product1').style.visibility='hidden';
document.getElementById('aboutus1').style.visibility='hidden';

}

function showProduct()
{
document.getElementById('contactSpan').style.visibility='hidden';
document.getElementById('aboutSpan').style.visibility='hidden';
document.getElementById('productSpan').style.visibility='visible';


document.getElementById('product1').style.visibility='visible';
document.getElementById('aboutus1').style.visibility='hidden';
document.getElementById('contactus1').style.visibility='hidden';

}


function overProd()
{
window.status='Our Products';
document.getElementById('product1').style.visibility='visible';
outAbout();
outContact();
}


function overAbout()
{
window.status='About Us';
document.getElementById('aboutus1').style.visibility='visible';
outProd();
outContact();

}

function overContact()
{
document.getElementById('contactus1').style.visibility='visible';
window.status='Contact Us';
outAbout();
outProd();
}


function outProd()
{
window.status='';
document.getElementById('product1').style.visibility='hidden';
}


function outAbout()
{
document.getElementById('aboutus1').style.visibility='hidden';
window.status='';
}

function outContact()
{
document.getElementById('contactus1').style.visibility='hidden';
window.status='';
}
