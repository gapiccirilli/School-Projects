import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import java.io.*;
import java.util.*;
public class RentalDemoAndCreateFile
{
    public static void main(String[] args)
    {
        int x;
        boolean properExec;
        //sets all rental types in an array
        Rental[] rentals = new Rental[4];
        //array is loaded using for loop and setObjectRental method
        for (x = 0; x < rentals.length; ++x)
        {
            //boolean value is declared as the flag for while loop. Each rental object is tried. if it fails, the user sees
            //an error message and must try that object again until no exceptions are thrown
            properExec = false;
            while (!properExec)
            {
                try
                {
                    rentals[x] = setObjectRental();
                    properExec = true;
                }
                catch (InputMismatchException inputError)
                {
                    System.out.println("Enter a Number Only!\n" + inputError.getMessage());
                }
            }
        }
        Path file = Paths.get("RentalData.txt");
        file.toAbsolutePath();
        try
        {
            createFile(file, rentals);
        }
        catch (IOException io)
        {
            System.out.println(io.getMessage());
        }
        //passes array to display method
        //displayInfo(rentals);
    }
    //uses a throw clause to rethrow an exception back to the main method where it's called
    public static Rental setObjectRental() throws InputMismatchException
    {
        String number;
        String contactNum;
        int minutes;
        int type;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Contract Number >> ");
        number = input.nextLine();
        System.out.print("Enter a number to contact you >> ");
        contactNum = input.nextLine();
        System.out.print("Enter minutes rented >> ");
        minutes = input.nextInt();
        while (minutes < 60 || minutes > 7200)
        {
            System.out.println("\nRental time must be atleast 1 hour and no longer than 5 full days\n");
            System.out.print("Enter minutes rented >> ");
            minutes = input.nextInt();
        }
        //accepts equipment type input
        System.out.print("Enter Equipment Type (1-8) >> ");
        type = input.nextInt();
        //added type to constructor call
        Rental newRental = new Rental(number, minutes, type);
        newRental.setPhoneNumber(contactNum);
        //sets equipmentType field
        return newRental;
    }
    public static void createFile(Path file, Rental[] rentalInfo) throws IOException
    {
        //establishes a buffered writer and writes data stored in data variable to file
        int x;
        OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
        String data = null;
        //loops through array of rentals to extract info
        for (x = 0; x < rentalInfo.length; ++x)
        {
            data = rentalInfo[x].getContractNum() + ", " + rentalInfo[x].getHours() + ", " + rentalInfo[x].getMinutesOver() + 
            ", " + rentalInfo[x].getEquipment().getEquipmentNum() + ", " + rentalInfo[x].getEquipment().getEquipmentType() + 
            ", " + rentalInfo[x].getPrice();
            //writes data one line at a time
            writer.write(data, 0, data.length());
            writer.newLine();
        }
        writer.close();
    }
    /*
    public static void displayInfo(Rental[] rentals)
    {
        int x;
        for (x = 0; x < rentals.length; ++x)
        {
            //added equipment type from the EquipmentWithLesson reference and price with fee and lesson policy
            System.out.println("--OBJECT " + (x + 1) + "--");
            System.out.println("Contract #: " + rentals[x].getContractNum());
            System.out.println("Equipment Type: " + rentals[x].getEquipment().getEquipmentType());
            System.out.println("Phone Number: " + rentals[x].getPhoneNumber());
            System.out.println("Hours: " + rentals[x].getHours());
            System.out.println("Minutes: " + rentals[x].getMinutesOver());
            System.out.println("Rate: $" + Rental.HOURLY_RATE);
            System.out.println("Price before equipment fee: $" + rentals[x].getPrice());
            System.out.println("Price after equipment fee: $" + rentals[x].getPriceWithFee());
            System.out.println("Lesson Policy: " + rentals[x].getEquipment().getLessonPolicy());
        }
    }*/
}