import java.util.Date;

class Claim
{
  //Data Members
  
  private int claimId;
  private Date claimDate;
  private int customerId;
  private String status;
  
  //Constructor
  public Claim(int claimId,int customerId,String status)
  {
    claimDate=new Date();
    this.claimId=claimId;
    this.customerId=customerId;
    this.status=status;
  }
  
  // Method to set the status of claim made by the Customer (Accepted/Rejected/Already Setteled).
   public  void setClaimStatus(String status)
   {
      this.status = status;  
   }
   
   // Method to get the current claim status.
   public String getClaimStatus()
   {
      return status;
   }
   
   // Method to get the Claim ID.
   public int getClaimId()
   {
      return claimId;
   }
   
   public Date getDate()
   {
     return claimDate;
   }
   
}