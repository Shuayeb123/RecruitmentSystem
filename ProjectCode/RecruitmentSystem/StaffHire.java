
import java.io.*;

public class StaffHire
{
        private int Vacancy_No;     //the vacancy number
    private String Des;         //the Designation
    private String JType;       //the Job type
    private String SName;       //the Staff Name
    private String JDate;       //the Joining Date
    private String Qual;        //the Qualification
    private String AppointBy;   //the person who appointed this staff member
    private boolean Joined;        //tells wether the staff member has joined the job
    
    /**
     * Constructor for objects of class StaffHire
     */
    public StaffHire(int Vac,String Desig, String JbType, String StffName, 
    String JnDate, String Quali, String Appointed, boolean Jned)
    {
        Vacancy_No = Vac;     //the vacancy number
        Des = Desig;         //the Designation
        JType = JbType;       //the Job type
        SName = StffName;       //the Staff Name
        JDate = JnDate;       //the Joining Date
        Qual = Quali;        //the Qualification
        AppointBy = Appointed;   //the person who appointed this staff member
        Joined = Jned; 
        
    }

    // ACESSOR METHODS
    
    public int getVacancyNo()
    {
        // put your code here
        return this.Vacancy_No;
    }
    
    public String getDesignation()
    {
        // put your code here
        return this.Des;
    }
    
    public String getJobType()
    {
        // put your code here
        return this.JType;
    }
    
    public String getStaffName()
    {
        // put your code here
        return this.SName;
    }
    
    public String getJoinDate()
    {
        // put your code here
        return this.JDate;
    }
    
    public String getQualification()
    {
        // put your code here
        return this.Qual;
    }
    
    public String getAppointedBy()
    {
        // put your code here
        return this.AppointBy;
    }
    
    public boolean getJoined()
    {
        // put your code here
        return this.Joined;
    }
    
    
    // MUTATOR METHODS
    
    public void setVacancyNo(int VacancyNo)
    {
        // put your code here
        this.Vacancy_No = VacancyNo;
    }
    
    public void setDesignation(String Des)
    {
        // put your code here
        this.Des = Des;
    }
    
    public void setJobType(String JType)
    {
        // put your code here
        this.JType = JType;
    }
    
    public void setStaffName(String SName)
    {
        // put your code here
        this.SName = SName;
    }
    
    public void setJoinDate(String JDate)
    {
        // put your code here
        this.JDate = JDate;
    }
    
    public void setQualification(String Qual)
    {
        // put your code here
        this.Qual = Qual;
    }
    
    public void setAppointedBy(String AppointBy)
    {
        // put your code here
        this.AppointBy = AppointBy;
    }
    
    public void setJoined(boolean x)
    {
        // put your code here
        this.Joined = x;
    }
    
    public void setNotJoined()
    {
        // put your code here
        this.Joined = false;
    }
    
    // the display method
    
    public void PrintDetails()
    {
        System.out.println("1.  Vacancy Number:  " + getVacancyNo());
        System.out.println("2.	Designation Type:  "+ getDesignation());
        System.out.println("3.	Job Type:  "+ getJobType());
        System.out.println("4.	staffName:  "+ getStaffName());
        System.out.println("5.	joiningDate:  "+ getJoinDate());
        System.out.println("6.	qualification:  "+ getQualification());
        System.out.println("7.	appointedBy:  "+ getAppointedBy());
        System.out.println("8.	joined:  "+ getJoined());

    }
}
