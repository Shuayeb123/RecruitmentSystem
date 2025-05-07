
/**
 * Write a description of class FullTimeStaffHire here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class FullTimeStaffHire extends StaffHire
{
    // instance variables - replace the example below with your own
    private double Salary;      //Salray
    private int WkFrcHrs;       //Weekly Fractional hours
    private boolean terminated; //terminated or not
    /**
     * Constructor for objects of class FullTimeStaffHire
     */
    public FullTimeStaffHire(int Vac,String Desig, String JbType, String StffName, 
    String JnDate, String Quali, String Appointed, boolean Jned, double Wage, int WHrs)
    {
        // initialise instance variables
        super(Vac, Desig, JbType, StffName, JnDate, Quali, Appointed, Jned);
        //calling the super class' constructor
        this.Salary = Wage;
        this.WkFrcHrs = WHrs;
        this.terminated = false;
    }

   
    public double getSalary(){
        return this.Salary;
    }
    
    public int getWkFrcHrs(){
        return this.WkFrcHrs;
    }
    
    public boolean getTerminated(){
        return this.terminated;
    }
    
       public void setSalary(double Salary){
        if(getJoined()){
           this.Salary = Salary;
        }
    }
    
    public void setWkFrcHrs(int WHrs){
        this.WkFrcHrs = WHrs;
    }
    
    public void setTerminated(boolean term){
        this.terminated = term;
    }
}
