

public class PartTimeStaffHire extends StaffHire
{
   
    private double WageHr;      //wage per hour
    private int WorkHr;         //Hours worked
    private String shifts;      //the number of shifts worked
    private boolean terminated; //terminated or not
   
    
    public PartTimeStaffHire(int Vac,String Desig, String JbType, String StffName, 
    String JnDate, String Quali, String Appointed, boolean Jned, double Wage, int WHrs, String shifts)
    {
        
        super(Vac, Desig, JbType, StffName, JnDate, Quali, Appointed, Jned);
        //calling the super class' constructor
        this.WageHr = Wage;
        this.WorkHr = WHrs;
        this.terminated = false;
        this.shifts = shifts;
    }

   
    public double getWageHr(){
        return this.WageHr;
    }
    
    public int getWorkHr(){
        return this.WorkHr;
    }
    
    public String getShifts(){
        return this.shifts;
    }
    public boolean getTerminated(){
        return this.terminated;
    }
    
       public void setWageHr(double Salary){
        this.WageHr = Salary;
    }
    
    public void setShifts(String Shift){
        this.shifts = Shift;
    }
    
    public void setWorkHr(int WHrs){
        this.WorkHr = WHrs;
    }
    
    public void setTerminated(boolean term){
        this.terminated = term;
    }
}
