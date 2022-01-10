import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
import java.text.DecimalFormat;
public class ReadWriteData
{
    public static void main(String[] args)
    {
        //2d array is created for [record][field]. A path is created for the Props.txt file and the readFile is called
        //after the readFile returns the 2d array, the 2darray is passed to the calculatedData method where it will be returned
        //and overwrite itself. The writeData method is called passing the 2d arrayß
        String[][] array2d = new String[10][13];
        Path relativeFile = Paths.get("Prop.txt");
        Path file = relativeFile.toAbsolutePath();
        array2d = readFile(file);
        array2d = calculateData(array2d);
        writeData(array2d);
    }
    public static String[][] readFile(Path file)
    {
        //2d array is declared and a String variable to hold each record. x is used to increment the record field each pass
        //through the while loop
        String[][] lines = new String[10][13];
        String str;
        int x = 0;
        InputStream input = null;
        //inputstream is declared and made into a bufferedreader. The reader reads lines into a string while the 2d array
        //holds each record and field in a while loop.
        try
        {
            input = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            str = reader.readLine();
            while (str != null)
            {
                lines[x] = str.split("\\|");
                str = reader.readLine();
                ++x;
            }
            //closes input stream
            reader.close();
        }
        //catches potential exception
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
            e.printStackTrace();
        }
        return lines;
    }
    public static String[][] calculateData(String[][] records)
    {
        //double variables declared to parse and store doubles for calculations
        double total, priceSold, net, taxes, afterTax, gain;
        //formats the decimals that will be converted back into Strings
        String percentFormat = "0.00";
        DecimalFormat df = new DecimalFormat(percentFormat);
        
        final double TAX = 0.35;
        //calculates everything and then all fields are formatted and then stored back in their respective spots
        //for loop goes through all records
        for (int x = 1; x < records.length; ++x)
        {
            total = Double.parseDouble(records[x][7]);
            priceSold = Double.parseDouble(records[x][8]);
            net = priceSold - total;
            taxes = TAX * net;
            afterTax = net - taxes;
            gain = (afterTax / total) * 100;

            records[x][9] = df.format(net);
            records[x][10] = df.format(taxes);
            records[x][11] = df.format(afterTax);
            records[x][12] = df.format(gain) + "%";
        }
        //returns the records 2d array back to main
        return records;
    }
    public static void writeData(String[][] records)
    {
        //declares a new path to write data to
        Path relativeFile = Paths.get("Prop2.txt");
        Path file = relativeFile.toAbsolutePath();
        //declares bufferedwriter and writes data one record at a time in for loop
        try
        {
            OutputStream output = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

            String str;
            for (int x = 0; x < records.length; ++x)
            {
                str = records[x][0] + "|" + records[x][1] + "|" + records[x][2] + "|" + records[x][3] + "|" + records[x][4] + 
                "|" + records[x][5] + "|" + records[x][6] + "|" + records[x][7] + "|" + records[x][8] + "|" + records[x][9] + "|" + 
                records[x][10] + "|" + records[x][11] + "|" + records[x][12];
                writer.write(str, 0, str.length());
                writer.newLine();
            }
            //closes output stream
            writer.close();
        }
        //catches any possible exception
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
