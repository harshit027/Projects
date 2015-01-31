

class PnCClerkSystem
{
  public PnCClerkUI pncUI;
  public ClaimController claimController;
  public void run()
  {
    pncUI=new PnCClerkUI();
    String cont="Y";
    while(cont.equalsIgnoreCase("Y"))
    {
      int option = pncUI.getOption();
      if(option == 1)
      {
        claimController=new ClaimController();
        claimController.initiateClaimRegistration();
      }
      else
        if(option == 2)
      {
        claimController=new ClaimController();
        claimController.initiateClaimUpdation();
      }
      else
        if(option == 0)
      {
        break;
      }
      else
      {
        pncUI.displayErrorMessage("Invalid option selected");
      }
      //cont = pncUI.getContinueSignal();
    }
    pncUI.displayErrorMessage("Exiting to main menu");
  }
  
}
