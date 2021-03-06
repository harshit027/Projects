import java.util.ArrayList;
import java.util.Date;
class Customer
{
  //Data members
  private ArrayList<Claim> claimList;
  private  Policy clientPolicy;
  private  Date startDate;
  private  int customerId;
  private  String name;
  private  char sex;
  private  int age;
  private  String nomineeDetails;
  private String address;
  
  //Methods
  
  //Customer constructor
  
  Customer(Policy clientPolicy,int customerId,String name,char sex,String nomineeDetails,String address,Date startDate)
  {
    claimList=new ArrayList<Claim>();
    this.clientPolicy=clientPolicy;
    this.customerId=customerId;
    this.name=name;
    this.sex=sex;
    this.nomineeDetails=nomineeDetails;
    this.address=address;
    this.startDate=startDate;
  }
  
  //checking matured status
  
  public Date checkMaturedStatus()
  {
    return clientPolicy.checkMaturity(this.startDate);
  }
  
  //returning customer id
  
  public int getCustomerId()
  {
    return customerId;
  }
  
  //adding claim to the customer's claimList
  
  public void addClaim(Claim newClaim)
  {
    claimList.add(newClaim);
  }
  
  //checking and getting claim from claimlist
  
  public Claim getClaim(int claimId)
  {
    for(Claim currentClaim: claimList)
    {
      if(currentClaim.getClaimId()==claimId)
        return currentClaim;
    }
    return null;
  }
  
  //updating claim status
  
  public String updateClaimStatus(int claimId,String status)
  {
    Claim newClaim=this.getClaim(claimId);
    if(newClaim==null)
      return "Claim not found";
    else
    {
      newClaim.setClaimStatus(status);
      return "Status update to : "+status;
    }
  }
  
  //returning customer's policy
  
  public Policy getPolicy()
  {
    return this.clientPolicy;
  }
   
  //calling policy.maturedAmount
  
  public double getMaturedAmount()
  {
    return this.clientPolicy.getMaturedAmount();
  }
  
  //returning start date
  
  public Date getStartDate()
  {
    return startDate;
  }
  
  public String getName()
  {
  return this.name;
  }
  
  public String getNominee()
  {
  return this.nomineeDetails;
  }
  
  public String getAddress()
  {
  return this.address;
  }
  
  public char getGender()
  {
  return this.sex;
  }
  
  
  
}
