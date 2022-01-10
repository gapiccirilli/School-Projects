public class EquipmentWithLesson extends Equipment
{
    //fields for lesson charge and costwith lesson
    private static final int LESSON_CHARGE = 27;
    private double costWithLesson;
    //constructor accepts type and passes to super constructor
    public EquipmentWithLesson(int type)
    {
        //if type is greater than 5 or less than 1, type is reset to OTHER(8)
        //rental fee is also set based off of this
        super(type);
        if (type > 5 || type < 1)
        {
            setEquipmentType(8);
        }
        setRentalFee();
    }
    //if other is selected, there will be no lesson charge and if it's within range, there will be
    public double getCostWithLesson()
    {
        if (getEquipmentNum() == 8)
            costWithLesson = getRentalFee();
        else
            costWithLesson = getRentalFee() + LESSON_CHARGE;
        return costWithLesson;
    }
    //overridden from abstract Equipment class to return "no lesson" if it's OtHER and "requires lesson + charge" if in range
    @Override
    public String getLessonPolicy()
    {
        if (getEquipmentNum() == 8)
            return (getEquipmentType() + " does not require a lesson");
        else
            return ("Renting a " + getEquipmentType() + " requires a lesson and will cost an additional $" + 
            LESSON_CHARGE + ". This will put your total charge at $" + getCostWithLesson());
    }
}
