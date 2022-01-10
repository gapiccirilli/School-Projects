public abstract class Employee
{
    //creates generic fields for subclasses to inherit
    private int idNumber;
    private String lastName;
    private String firstName;
    protected double payRate;
    protected String jobTitle;
    //sets id
    public void setIdNum(int id)
    {
        idNumber = id;
    }
    //sets last name
    public void setLastName(String last)
    {
        lastName = last;
    }
    //sets first name
    public void setFirstName(String first)
    {
        firstName = first;
    }
    //abstract methods are decalred to be used in each subclass
    public abstract void setPayRate(double rate);
    public abstract void setJobTitle();
    //returns ID
    public int getIdNum()
    {
        return idNumber;
    }
    //returns last name
    public String getLastName()
    {
        return lastName;
    }
    //returns first name
    public String getFirstName()
    {
        return firstName;
    }
    //returns pay rate
    public double getPayRate()
    {
        return payRate;
    }
    //returns static job title
    public String getJobTitle()
    {
        return jobTitle;
    }
}
