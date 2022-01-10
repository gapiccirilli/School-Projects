//extends the equipment class
public class EquipmentWithoutLesson extends Equipment
{
    //adds type to constuctor and passes to super constructor.
    //if the type is greater than 8 or less than 6 then type is reset to OTHER(8)
    //constructor sets rental fee
    public EquipmentWithoutLesson(int type)
    {
        super(type);
        if (type > 8 || type < 6)
        {
            setEquipmentType(8);
        }
        setRentalFee();
    }
    //overrides abstract getLessonPolicy from abstract equipment class which says it does not require a lesson
    @Override
    public String getLessonPolicy()
    {
        return ("Renting a " + getEquipmentType() + 
        " does not require a lesson. Your surcharge will be $" + getRentalFee());
    }
}
