public abstract class Equipment
{
    //abstract class that takes type of equipment, name of equip, rental fee for each. Final arrays for type and fee to match to.
    private int equipmentType;
    private String equipmentName;
    private double rentalFee;
    final String[] EQUIPMENT = {"Personal WaterCraft", "Pontoon Boat", "Row Boat", "Canoe", "Kayak", "Beach Chair", 
    "Umbrella", "Other"};
    final int[] SURCHARGE = {50, 40, 15, 12, 10, 2, 1, 0};
    //constructor sets type within range. If out of range, it's set to OTHER
    public Equipment(int type)
    {
        final int OTHER = 8;
        if (type > 0 && type < 9)
            equipmentType = type;
        else
            equipmentType = OTHER;
    }
    //does the same as constructor but it can be call once object is set
    public void setEquipmentType(int type)
    {
        final int OTHER = 8;
        if (type > 0 && type < 9)
            equipmentType = type;
        else
            equipmentType = OTHER;
    }
    //sets rental fee by matching parallel arrays up by using type as the index - 1
    public void setRentalFee()
    {
        rentalFee = SURCHARGE[equipmentType - 1];
    }
    //returns name of equipment by using integer type as index - 1
    public String getEquipmentType()
    {
        equipmentName = EQUIPMENT[equipmentType - 1];
        return equipmentName;
    }
    //returns equipment number in case the index is needed
    public int getEquipmentNum()
    {
        return equipmentType;
    }
    //returns rental fee
    public double getRentalFee()
    {
        return rentalFee;
    }
    //abstract method getLessonPolicy that differs for each subclass
    public abstract String getLessonPolicy();
}
