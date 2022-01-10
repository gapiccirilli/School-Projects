public class Rental
{
    public final static int MINS_IN_HOUR = 60;
    public final static int HOURLY_RATE = 40;
    //added equipment object
    private Equipment equip;
    private String contractNum;
    private String phoneNum;
    private int hoursRented;
    private int minutesOver;
    private int totalMinutes;
    private int price;
    private double priceWithFee;
    //added equipment type parameter to rental constructor
    public Rental(String number, int minutes, int type)
    {
        setContractNumber(number);
        setHoursandMinutes(minutes);
        //added equipment object of EquipmentWithLesson class and set price with fee then assigns reference to equip object
        EquipmentWithLesson equipment = new EquipmentWithLesson(type);
        priceWithFee = equipment.getCostWithLesson() + price;
        equip = equipment;
    }
    public Rental()
    {
        this("A000", 0, 8);
    }
    public void setContractNumber(String conNum)
    {
        if (Character.isLetter(conNum.charAt(0)) && conNum.length() == 4)
        {
            int x;
            char firstLetter;
            if (Character.isLowerCase(conNum.charAt(0)))
            {
                firstLetter = Character.toUpperCase(conNum.charAt(0));
                conNum = conNum.replace(conNum.charAt(0), firstLetter);
            }
            for (x = 1; x < 4; ++x)
            {
                if (!(Character.isDigit(conNum.charAt(x))))
                {
                    contractNum = "A000";
                    x = 4;
                }
            }
            if (contractNum != "A000")
                contractNum = conNum;
        }
        else
            contractNum = "A000";
    }
    public void setPhoneNumber(String phoneNumber)
    {
        int x;
        int length = phoneNumber.length();
        boolean notDigit = false;
        StringBuilder newNumber = new StringBuilder(phoneNumber);
        for (x = 0; x < length; ++x)
        {
            if (notDigit)
            {
                x = x - 1;
                notDigit = false;
            }
            if (!(Character.isDigit(newNumber.charAt(x))))
            {
                newNumber.deleteCharAt(x);
                length -= 1;
                notDigit = true;
            }
        }
        if (newNumber.toString().length() != 10)
            phoneNum = "0000000000";
        else
            phoneNum = newNumber.toString();
    }
    public String getPhoneNumber()
    {
        StringBuilder area;
        StringBuilder exchange;
        StringBuilder lastFour;
        
        area = new StringBuilder(phoneNum.substring(0, 3));
        area.insert(0, "(");
        area.append(") ");
        exchange = new StringBuilder(phoneNum.substring(3, 6));
        exchange.append("-");
        lastFour = new StringBuilder(phoneNum.substring(6, 10));

        phoneNum = area.toString() + exchange + lastFour;

        return phoneNum;
    }
    public void setHoursandMinutes(int minutes)
    {
        //minsReduced is to set the price accurately if the minutes go over 40 minutes
        int minsReduced;
        //totalMinutes is used to compare the longest rental time easily 
        totalMinutes = minutes;
        //properly sets hours 
        if (minutes >= MINS_IN_HOUR)
        {
            hoursRented = minutes / 60;
        }
        else
        {
            hoursRented = 0;
        }
        minutesOver = minutes - (hoursRented * 60);
        if (minutesOver > HOURLY_RATE && minutesOver < MINS_IN_HOUR)
        {
            minsReduced = minutesOver - 40;
        }
        else
        {
            minsReduced = 0;
        }
        price = (hoursRented * HOURLY_RATE) + minutesOver - minsReduced;
    }
    public String getContractNum()
    {
        return contractNum;
    }
    public int getHours()
    {
        return hoursRented;
    }
    public int getMinutesOver()
    {
        return minutesOver;
    }
    public int getTotalMinutes()
    {
        return totalMinutes;
    }
    public int getPrice()
    {
        return price;
    }
    //returns price with rental fees
    public double getPriceWithFee()
    {
        return priceWithFee;
    }
    //returns equipment object
    public Equipment getEquipment()
    {
        return equip;
    }
}