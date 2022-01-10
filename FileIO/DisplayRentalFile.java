import java.nio.file.*;
import java.io.*;
public class DisplayRentalFile
{
    public static void main(String[] args)
    {
        //establishes path object with relative path then is converted to absolute path
        Path file = Paths.get("RentalData.txt");
        file.toAbsolutePath();
        //file is tried by passing it to the readFile method. The readFile method can throw an IOException
        try
        {
            readFile(file);
        }
        catch (IOException io)
        {
            System.out.println(io.getMessage());
        }
    }
    public static void readFile(Path file) throws IOException
    {
        String[] records = new String[6];
        String str;
        //An input stream is wrapped in a bufferinputstream and held in an InputStream reference
        //The iStream is then wrapped in a Input reader that is wrapped in a Buffer reader to read multiple lines of characters
        InputStream iStream = new BufferedInputStream(Files.newInputStream(file));
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        //str stores each line. while checks for str to see if if has any more characters stored
        str = reader.readLine();
        while (str != null)
        {
            //array uses the split() method to split using the record delimiter ", "
            //the array is sent to display along with the counter and x is incremented while str tries to read another line
            records = str.split(", ");
            display(records);
            str = reader.readLine();
        }
        //final message to notify the user that all lines were successfully read
        System.out.println("All Data Read Successfully!");
    }
    //displays all lines from the records array
    public static void display(String[] array)
    {
        String contractNum, hours, minutes, typeCode, typeName, price;
        contractNum = array[0];
        hours = array[1];
        minutes = array[2];
        typeCode = array[3];
        typeName = array[4];
        price = array[5];

        System.out.println("Contract #: " + contractNum + "\nHours: " + hours + 
        "\nMinutes: " + minutes + "\nType Code: " + typeCode + "\nType Name: " +
        typeName + "\nPrice: $" + price + "\n");
    }
}