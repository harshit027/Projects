import java.util.Date;

class Policy
{
  //Data Members
  
  private int policyId;
  private double amountCovered;
  private double premium;
  private double interestRate;
  private int duration;
  private double bonusRate;
  private double maturedAmount;
  
  //Methods
  
  Policy(int policyId,double amountCovered, double premium,double interestRate,int duration,double bonusRate)
  {
    this.policyId=policyId;
    this.amountCovered=amountCovered;
    this.premium=premium;
    this.interestRate=interestRate;
    this.duration=duration;
    this.bonusRate=bonusRate;
    this.maturedAmount=0;
  }
  
  public boolean checkPolicy(int policyId,int duration)
  {
    if(this.policyId==policyId && this.duration==duration)
      return true;
      else
      return false;
  }
  
  public  Date checkMaturity(Date startDate)
  {
    Date currentDate=new Date();
    Date maturityDate=new Date(startDate.getYear()+this.duration,startDate.getMonth(),startDate.getDate());
    if(maturityDate.compareTo(currentDate)<=0)
      processPolicy();    
    return maturityDate;
  }
  
  public void processPolicy()
  {
    this.maturedAmount=this.premium+this.premium*(bonusRate+interestRate*duration)/100;
  }
  
  public double getMaturedAmount()
  {
    return maturedAmount;
  }
}