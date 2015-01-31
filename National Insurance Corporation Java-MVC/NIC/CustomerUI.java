import java.util.*;

class CustomerUI
{
  private Scanner in;
  
  
  public ArrayList<Object> getNewCustomerDetails()
  {
    in=new Scanner(System.in);
    boolean validate=false;
    do
    {
      ArrayList<Object> newCustomerDetails = showCustomerRegistrationScreen();
      validate = validateNewCustomerDetails(newCustomerDetails);
      if(validate==true)
      {
        return newCustomerDetails;
      }
      else
      {
        notify("Some Problem with details...");
        newCustomerDetails = showCustomerRegistrationScreen();
        return newCustomerDetails;
      }
    }
    while (validate == false);
  }
  public ArrayList<Object> showCustomerRegistrationScreen()
  {
    ArrayList<Object>  newCustomerDetails = new ArrayList<Object> ();
    System.out.println();
    System.out.print("Enter Customer Name: ");
    newCustomerDetails.add(in.nextLine());
    System.out.print("Enter Policy ID: ");
    newCustomerDetails.add(Integer.parseInt(in.nextLine()));
    System.out.print("Enter Duration : ");
    newCustomerDetails.add(Integer.parseInt(in.nextLine()));
    System.out.print("Enter Sex: ");
    newCustomerDetails.add(in.nextLine());
    System.out.print("Enter Nominee Details: ");
    newCustomerDetails.add(in.nextLine());
    System.out.print("Enter Address: ");
    newCustomerDetails.add(in.nextLine());
    return newCustomerDetails;
  }
  
  public boolean validateNewCustomerDetails(ArrayList<Object> newCustomerDetails)
  {
    if(((String)newCustomerDetails.get(0)).equals(""))
    {
      System.out.println("Customer name should not be empty");
      return false;
    }
    if((Integer)newCustomerDetails.get(1)<=0)
    {
      System.out.println("Policy ID should not be negative");
      return false;
    }
    if((Integer)newCustomerDetails.get(2)<=0 )
    {
      System.out.println("Duration should greater than 0");
      return false;
    }
    if(!(((String)(newCustomerDetails.get(3))).toLowerCase().charAt(0)=='m' || ((String)(newCustomerDetails.get(3))).toLowerCase().charAt(0)=='f') )
    {
      System.out.println("Invalid sex");
      return false;
    }
    if(((String)newCustomerDetails.get(4)).equals(""))
    {
      System.out.println("Nominee Details should not be empty");
      return false;
    }
    if(((String)newCustomerDetails.get(5)).equals(""))
    {
      System.out.println("Customer Address should not be empty");
      return false;
    }
    return true;
  }
  
  public void notify(String status)
  {
    System.out.println(status);
  }
}