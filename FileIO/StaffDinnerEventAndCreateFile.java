import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import java.io.*;
import javax.swing.JOptionPane;
public class StaffDinnerEventAndCreateFile
{
    public static void main(String[] args)
    {
        //sets up a DinnerEvent array of 3 elements and then uses a for loop to build each event
        DinnerEvent[] dEvent = new DinnerEvent[3];
        boolean properExec;
        
        for (int x = 0; x < dEvent.length; ++x)
        {
            properExec = false;
            while (!properExec)
            {
                try
                {
                    //builds each event while being able to catch an exception
                    dEvent[x] = buildEvent();
                    properExec = true;
                }
                catch(NumberFormatException inputError)
                {
                    JOptionPane.showMessageDialog(null, "Must be a numerical entry!\nRe-enter Event Data", "Event Data Error", 0);
                }
                catch(IndexOutOfBoundsException indexError)
                {
                    JOptionPane.showMessageDialog(null, indexError.getMessage() + "\nTry Again", "Error", 0);
                }
            }
        }
        //declares a path file and converts to absolute
        Path file = Paths.get("EventData.txt");
        file.toAbsolutePath();
        try
        {
            //passes file and dEvent array to createFile method. After createFile executes, Data has executed successfully
            createFile(file, dEvent);
            JOptionPane.showMessageDialog(null, "Data successfully stored in \"" + file.getFileName() + "\"", "Success", 
            JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException io)
        {
            JOptionPane.showMessageDialog(null, "Error: " + io.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
        }
        //irrelevant methods to this case study
        //addEmployees(dEvent);
        //display(dEvent);
    }
    //this method can throw both a NumberFormatException and IndexOutOfBoundsException
    public static DinnerEvent buildEvent() throws NumberFormatException, IndexOutOfBoundsException
    {
        String eventNum, guests, entree, side_1, side_2, dessert, eventType;
        int guestNum, entreeNum, side1Num, side2Num, dessertNum, type;
        //same as last case study except this info is used to write to a file
        eventNum = JOptionPane.showInputDialog(null, "Enter Event Number");
        guests = JOptionPane.showInputDialog(null, "Enter Guest Count for Event");
        guestNum = Integer.parseInt(guests);
        entree = JOptionPane.showInputDialog(null, "Enter Entree Choice (1-3)\n1. Chicken\n2. Steak\n3. Salmon");
        entreeNum = Integer.parseInt(entree);
        //throw statement is coded for each menu choice so that if an invalid index is entered, an IndexOutOfBounds is thrown
        //the method is then tried again
        if (entreeNum < 1 || entreeNum > 3)
            throw (new IndexOutOfBoundsException("Entree dish is invalid"));
        side_1 = JOptionPane.showInputDialog(null, "Enter your first side dish (1-3)\n1. Mashed Potatoes\n2. Broccoli\n3. Salad");
        side1Num = Integer.parseInt(side_1);
        if (side1Num < 1 || side1Num > 3)
            throw (new IndexOutOfBoundsException("Side Dish 1 is invalid"));
        side_2 = JOptionPane.showInputDialog(null, "Enter your second side dish (1-3)\n1. Mashed Potatoes\n2. Broccoli\n3. Salad");
        side2Num = Integer.parseInt(side_2);
        if (side2Num < 1 || side2Num > 3)
            throw (new IndexOutOfBoundsException("Side Dish 2 is invalid"));
        dessert = JOptionPane.showInputDialog(null, "Enter Dessert Choice (1-3)\n1. Red Velvet Cheese Cake\n2. Banana Cream Pie\n3. Hot Fudge Sundae");
        dessertNum = Integer.parseInt(dessert);
        if (dessertNum < 1 || dessertNum > 3)
            throw (new IndexOutOfBoundsException("Dessert is invalid"));
        eventType = JOptionPane.showInputDialog(null, "Event Type (1-5)\n1. Wedding\n2. Baptism\n3. Birthday\n4. Corporate\n5. Other");
        type = Integer.parseInt(eventType);
        DinnerEvent event = new DinnerEvent(eventNum, guestNum, entreeNum, side1Num, side2Num, dessertNum);
        event.setPhoneNumber(JOptionPane.showInputDialog(null, "Enter contact phone number"));
        event.setEventType(type);
        return event;
    }
    //establishes a buffer writer and writes data from a variable to the file and establishing a new line 
    //after each iteration in the for loop
    public static void createFile(Path file, DinnerEvent[] eventsInfo) throws IOException
    {
        int x;
        OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        String data = null;

        for (x = 0; x < eventsInfo.length; ++x)
        {
            data = eventsInfo[x].getEventNumber() + ", " + eventsInfo[x].getEventType() + ", " + eventsInfo[x].getGuests() + 
            ", " + eventsInfo[x].getPrice();

            writer.write(data, 0, data.length());
            writer.newLine();
        }
        writer.close();
    }
    //irrelevant methods to this case study
    /*
    public static void addEmployees(DinnerEvent event) throws NumberFormatException
    {
        int waitNum, barNum, guestCount;
        int z = 0;
        boolean properExec;
        //only one coord per event
        final int COORD = 1;
        guestCount = event.getGuests();
        //calcs the number of each employee
        waitNum = (guestCount / 10) + 1;
        barNum = guestCount / 25;
        //creates an array based off of total emps
        Employee[] emps = new Employee[COORD + waitNum + barNum];
        //uses z as a counter between for loops and then builds array based off of number of each type of employee
        for (int x = 0; x < waitNum; ++x, ++z)
        {
            properExec = false;
            emps[z] = new Waitstaff();
            //each try...catch in this addEmployees method is identical. The reason for multiple try...catches is because
            //if an exception is thrown late in the loading of the emps array, you won't have to re-enter all of the 
            //data from the first employee object again. You only have to re-enter the element you threw the exception at
            while (!properExec)
            {
                try
                {
                    emps[z] = setEmpObj(emps[z]);
                    properExec = true;
                }
                catch(NumberFormatException inputError)
                {
                    JOptionPane.showMessageDialog(null, "Must be a numerical entry!\nRe-enter Waitstaff Data", "Employee Entry Error", 0);
                }
            }
        }
        for (int y = 0; y < barNum; ++y, ++z)
        {
            properExec = false;
            emps[z] = new Bartender();
            while (!properExec)
            {
                try
                {
                    emps[z] = setEmpObj(emps[z]);
                    properExec = true;
                }
                catch(NumberFormatException inputError)
                {
                    JOptionPane.showMessageDialog(null, "Must be a numerical entry!\nRe-enter Bartender Data", "Employee Entry Error", 0);
                }
            }
        }
        emps[z] = new Coordinator();
        properExec = false;
        while (!properExec)
            {
                try
                {
                    emps[z] = setEmpObj(emps[z]);
                    properExec = true;
                }
                catch(NumberFormatException inputError)
                {
                    JOptionPane.showMessageDialog(null, "Must be a numerical entry!\nRe-enter Coordinator Data", "Employee Entry Error", 0);
                }
            }
        event.setEmps(emps);
    }
    public static Employee setEmpObj(Employee emp) throws NumberFormatException
    {
        //sets each employee in the array by setting job title, ID, name, and pay rate
        //strings are set while ints are parsed from strings
        String strEntry;
        int intEntry;
        double dblEntry;
        emp.setJobTitle();
        strEntry = JOptionPane.showInputDialog(null, "Enter Employee ID Number", emp.getJobTitle(), 1);
        intEntry = Integer.parseInt(strEntry);
        emp.setIdNum(intEntry);
        strEntry = JOptionPane.showInputDialog(null, "Enter Employee's First Name", emp.getJobTitle(), 1);
        emp.setFirstName(strEntry);
        strEntry = JOptionPane.showInputDialog(null, "Enter Employee's Last Name", emp.getJobTitle(), 1);
        emp.setLastName(strEntry);
        strEntry = JOptionPane.showInputDialog(null, "Enter Employee Pay Rate", emp.getJobTitle(), 1);
        dblEntry = Double.parseDouble(strEntry);
        emp.setPayRate(dblEntry);
        //returns each constructed employee to array to be stored
        return emp;
    }
    public static void display(DinnerEvent event)
    {
        //displays main event details first then uses a for loop to showcase details about each employee of the night
        JOptionPane.showMessageDialog(null, "Event #: " + event.getEventNumber() +
        "\nEvent Type: " + event.getEventType() + "\nContact Number: " + event.getPhoneNumber() + 
        "\nGuest Count: " + event.getGuests() + "\nEvent Price: $" + event.getPrice() + "\nEntree: " + event.getMenu()[0]
         + "\nSide 1: " + event.getMenu()[1] + "\nSide 2: " + event.getMenu()[2] + "\nDessert: " + event.getMenu()[3], "Event Details", 
        JOptionPane.INFORMATION_MESSAGE);
        for (int x = 0; x < event.getEmps().length; ++x)
        {
            JOptionPane.showMessageDialog(null, "Name: " + event.getEmps()[x].getFirstName() + " " + 
            event.getEmps()[x].getLastName() + "\nID: " + event.getEmps()[x].getIdNum() + "\nPay Rate: $" + 
            event.getEmps()[x].getPayRate(), event.getEmps()[x].getJobTitle(), JOptionPane.INFORMATION_MESSAGE);
        }
    }*/
}
