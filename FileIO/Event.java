public class Event
{
    public final static int LOW_GUEST_PRICE = 32;
    public final static int HIGH_GUEST_PRICE = 35;
    public final static int CUT_OFF = 50;
    private String eventNum;
    //added String Array to hold event types and int to hold corresponding event numbers
    private final String[] EVENTS = {"Wedding", "Baptism", "Birthday", "Corporate", "Other"};
    private int eventType;
    private int guestNumber;
    private int price;
    private int pricePerGuest;
    private String contactNumber;

    public Event(String num, int guestNum)
    {
        setEventNumber(num);
        setGuests(guestNum);
    }
    public Event()
    {
        this("A000", 0);
    }
    public void setEventNumber(String num)
    {
        if (Character.isLetter(num.charAt(0)) && num.length() == 4)
        {
            int x;
            char firstLetter;
            if (Character.isLowerCase(num.charAt(0)))
            {
                firstLetter = Character.toUpperCase(num.charAt(0));
                num = num.replace(num.charAt(0), firstLetter);
            }
            for (x = 1; x < 4; ++x)
            {
                if (!(Character.isDigit(num.charAt(x))))
                {
                    eventNum = "A000";
                    x = 4;
                }
            }
            if (eventNum != "A000")
                eventNum = num;
        }
        else
            eventNum = "A000";
    }
    public void setGuests(int guests)
    {
        guestNumber = guests;

        if (guestNumber >= 50)
            pricePerGuest = LOW_GUEST_PRICE;
        else
            pricePerGuest = HIGH_GUEST_PRICE;

        price = guestNumber * pricePerGuest;
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
            contactNumber = "0000000000";
        else
            contactNumber = newNumber.toString();
    }
    //accepts a int (1-5) and then subtracts it to get index value for array
    //if the index is out of bounds, then the event type is set to Other by default
    public void setEventType(int event)
    {
        final int OTHER = 4;
        --event;

        if (event >= EVENTS.length || event < 0)
        {
            eventType = OTHER;
        }
        else
        {
            eventType = event;
        }
    }
    //returns event type
    public String getEventType()
    {
        return EVENTS[eventType];
    }
    public String getPhoneNumber()
    {
        StringBuilder area;
        StringBuilder exchange;
        StringBuilder lastFour;
        
        area = new StringBuilder(contactNumber.substring(0, 3));
        area.insert(0, "(");
        area.append(") ");
        exchange = new StringBuilder(contactNumber.substring(3, 6));
        exchange.append("-");
        lastFour = new StringBuilder(contactNumber.substring(6, 10));

        contactNumber = area.toString() + exchange + lastFour;

        return contactNumber;
    }
    public String getEventNumber()
    {
        return eventNum;
    }
    public int getGuests()
    {
        return guestNumber;
    }
    public int getPrice()
    {
        return price;
    }
    public int getPricePer()
    {
        return pricePerGuest;
    }
    public boolean isLargeEvent()
    {
        if (guestNumber >= 50)
            return true;
        else
            return false;
    }

}
