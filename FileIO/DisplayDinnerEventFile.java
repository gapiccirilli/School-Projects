import java.nio.file.*;
import java.io.*;
import javax.swing.JOptionPane;
public class DisplayDinnerEventFile
{
    public static void main(String[] args)
    {
        //establishes path object with relative path then is converted to absolute path
        Path file = Paths.get("EventData.txt");
        file.toAbsolutePath();
        //file is tried by passing it to the readFile method. The readFile method can throw an IOException
        try
        {
            readFile(file);
        }
        catch (IOException io)
        {
            JOptionPane.showMessageDialog(null, "Error: " + io.getMessage());
        }
    }
    //readFile passes the file. The method establishes a String array to hold fields to make up a single record
    public static void readFile(Path file) throws IOException
    {
        String[] records = new String[4];
        String str;
        //An input stream is wrapped in a bufferinputstream and held in an InputStream reference
        //The iStream is then wrapped in a Input reader that is wrapped in a Buffer reader to read multiple lines of characters
        InputStream iStream = new BufferedInputStream(Files.newInputStream(file));
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        //str stores each line. while checks for str to see if if has any more characters stored
        str = reader.readLine();
        while (str != null)
        {
            //x is used as a counter to know how many events there are when they are displayed
            //array uses the split() method to split using the record delimiter ", "
            //the array is sent to display along with the counter and x is incremented while str tries to read another line
            int x = 0;
            records = str.split(", ");
            display(records, x);
            ++x;
            str = reader.readLine();
        }
        //final message to notify the user that all lines were successfully read
        JOptionPane.showMessageDialog(null, "All Data Read Successfully!");
    }
    //displays all lines from the records array
    public static void display(String[] array, int counter)
    {
        String eventNum, type, guests, price;
        eventNum = array[0];
        type = array[1];
        guests = array[2];
        price = array[3];

        JOptionPane.showMessageDialog(null, "Event #: " + eventNum + "\nEvent Type: " + type + 
        "\n# of guests: " + guests + "\nPrice: $" + price, "Event " + (counter + 1), JOptionPane.INFORMATION_MESSAGE);
    }
}