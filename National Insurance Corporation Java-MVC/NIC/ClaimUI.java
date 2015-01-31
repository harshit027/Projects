import java.util.*;

class ClaimUI
{
  private Scanner in;
    ////////////////////////////////////////
  public int getNewClaimDetails()
  {
    in=new Scanner(System.in);
    boolean validate=false;
    do
    {
      int newClaimDetails = showClaimRegistrationScreen();
      validate = validateNewClaimDetails(newClaimDetails);
      if(validate==true)
      {
        return newClaimDetails;
      }
      else
      {
        notify("Some Problem with details...");
        newClaimDetails = showClaimRegistrationScreen();
        return newClaimDetails;
      }
    }
    while (validate == false);
  }
  public int showClaimRegistrationScreen()
  {
    int newClaimDetails = -1;
    System.out.println();
    System.out.print("Enter Customer ID: ");
    newClaimDetails =Integer.parseInt((in.nextLine()));
    return newClaimDetails;
  }
  public boolean validateNewClaimDetails(int newClaimDetails)
  {
    if(newClaimDetails<=0)
    {
      System.err.println("Customer Id should be greater than 0");
      return false;
    }
    else
    {
      return true;
    }
  }
  
  ////////////////////////////////////////
  
  public ArrayList<Object>  getUpdateClaimDetails()
  {
    in=new Scanner(System.in);
    boolean validate=false;
    do
    {
      ArrayList<Object> updateClaimStatus = showClaimUpdationScreen();
      validate = validateUpdateClaimStatus(updateClaimStatus);
      if(validate==true)
      {
        return updateClaimStatus;
      }
      else
      {
        notify("Some Problem with details...");
        updateClaimStatus = showClaimUpdationScreen();
        return updateClaimStatus;
      }
    }
    while (validate == false);
  }
  public ArrayList<Object>  showClaimUpdationScreen()
  {
    ArrayList<Object> updateClaimStatus = new ArrayList<Object>();
    System.out.println();
    System.out.print("Enter Claim ID: ");
    updateClaimStatus.add(Integer.parseInt(in.nextLine()));
    System.out.print("Enter new status: ");
    updateClaimStatus.add(in.nextLine());
    return updateClaimStatus;
  }
  public boolean validateUpdateClaimStatus(ArrayList<Object> updateClaimStatus)
  {
    if((Integer)(updateClaimStatus.get(0))<=0)
    {
      System.out.println("Customer Id should be greater than 0");
      return false;
    }
    else
      if(!(((String)updateClaimStatus.get(1)).equalsIgnoreCase("accepted") || ((String)updateClaimStatus.get(1)).equalsIgnoreCase("rejected")))
    {
      System.out.println("Status should either be accepted or rejected");
      return false;
    }
      
      else
    {
      return true;
    }
  }
  
  //////////////////////////////////////////////
    public int getSettleClaimDetails()
  {
    in=new Scanner(System.in);
    boolean validate=false;
    do
    {
      int settleClaimDetails = showClaimSettlementScreen();
      validate = validateSettleClaimDetails(settleClaimDetails);
      if(validate==true)
      {
        return settleClaimDetails;
      }
      else
      {
        notify("Some Problem with details...");
        settleClaimDetails = showClaimSettlementScreen();
        return settleClaimDetails;
      }
    }
    while (validate == false);
  }
  public int showClaimSettlementScreen()
  {
    int settleClaimDetails = -1;
    System.out.println();
    System.out.print("Enter Claim ID you want to settle: ");
    settleClaimDetails =Integer.parseInt(in.nextLine());
    return settleClaimDetails;
  }
  public boolean validateSettleClaimDetails(int settleClaimDetails)
  {
    if(settleClaimDetails<=0)
    {
      System.err.println("Claim Id should be greater than 0");
      return false;
    }
    else
    {
      return true;
    }
  }
  
  public void notify(String status)
  {
    System.out.println(status);
  }
  
  
}
