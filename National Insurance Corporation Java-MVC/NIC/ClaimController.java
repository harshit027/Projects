import java.util.ArrayList;

class ClaimController
{
   public void initiateClaimRegistration()
  {
    ClaimUI claimUI = new ClaimUI();
    int claimDetails= claimUI.getNewClaimDetails();
    String status=NIC.addClaim(claimDetails);
    claimUI.notify(status);
  }
  
   
   public void initiateClaimUpdation()
  {
    ClaimUI claimUI = new ClaimUI();
    ArrayList<Object> updateClaimStatus= claimUI.getUpdateClaimDetails();
    String status=NIC.updateClaim((Integer)updateClaimStatus.get(0),(String)updateClaimStatus.get(1));
    claimUI.notify(status);
  }
  
   public void initiateClaimSettlement()
  {
    ClaimUI claimUI = new ClaimUI();
   int  settleClaimDetails= claimUI.getSettleClaimDetails();
    String status=NIC.settleClaim(settleClaimDetails);
    claimUI.notify(status);
  }
   
}