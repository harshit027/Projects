

class AccountClerkSystem
{
  public AccountClerkUI accUI;
  public void run()
  {
    accUI=new AccountClerkUI();
    String cont="Y";
    while(cont.equalsIgnoreCase("Y"))
    {
      int option = accUI.getOption();
      if(option == 1)
      {
        CustomerController custController=new CustomerController();
        custController.initiateCustomerRegistration();
      }
      else
        if(option == 2)
      {
        ClaimController claimController=new ClaimController ();
        claimController.initiateClaimSettlement();
      }
      else
        if(option == 3)
      {
        ReportGenerator.generateReport(1);
      }
      else
        if(option == 4)
      {
        ReportGenerator.generateReport(2);
      }
      else
        if(option == 5)
      {
        ReportGenerator.generateReport(3);
      }
      else
        if(option == 6)
      {
        ReportGenerator.generateReport(4);
      }
      else
        if(option == 7)
      {
        ReportGenerator.generateReport(5);
      }
      else
        if(option == 0)
      {
        break;
      }
      else
      {
        accUI.displayErrorMessage("Invalid option selected");
      }
   //   cont = accUI.getContinueSignal();
    }
    accUI.displayErrorMessage("Exiting to main menu");
  }
  
}
