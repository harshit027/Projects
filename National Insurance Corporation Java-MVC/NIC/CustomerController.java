import java.util.ArrayList;


class CustomerController
{
  public void initiateCustomerRegistration()
  {
    CustomerUI customerUI=new CustomerUI();
    ArrayList<Object> newCustomerDetails= customerUI.getNewCustomerDetails();
    int policyId=(Integer)newCustomerDetails.get(1);
    int duration=(Integer)newCustomerDetails.get(2); 
    String name=(String)newCustomerDetails.get(0); 
    char sex=((String)newCustomerDetails.get(3)).charAt(0);
    String nomineeDetails=(String)newCustomerDetails.get(4); 
    String address=(String)newCustomerDetails.get(5); 
    String status=NIC.addCustomer(policyId,duration,name,sex,nomineeDetails,address);
    customerUI.notify(status);
  }
  
 /* public void initiateCustomerDisplay()
  {
    CustomerUI customerUI=new CustomerUI();
    
  }*/
}