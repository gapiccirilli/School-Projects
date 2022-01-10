public class DinnerEvent extends Event
{
    //DinnerEvent extends Event
    //int fields to hold decision for menu choices
    private int entree;
    private int sideDish_1;
    private int sideDish_2;
    private int dessert;
    //holds 15 employee objects of varying subclasses
    private Employee[] dinEventEmps = new Employee[15];
    //constant string arrays to hold menu options for each course
    private final String[] entreeChoices = {"Chicken", "Steak", "Salmon"};
    private final String[] sideDishChoices = {"Mashed Potatoes", "Broccoli", "Salad"};
    private final String[] dessertChoices = {"Red Velvet Cheese Cake", "Banana Cream Pie", "Hot Fudge Sundae"};

    public DinnerEvent(String eventNum, int guestNum, int entree, int side_1, int side_2, int dessert)
    {
        //passes event number and guest number to superclass constructor
        super(eventNum, guestNum);
        this.entree = entree;
        sideDish_1 = side_1;
        sideDish_2 = side_2;
        this.dessert = dessert;
    }
    //returns string array after assigning each element to corresponding string value
    public String[] getMenu()
    {
        String[] menu = new String[4];
        menu[0] = entreeChoices[entree - 1];
        menu[1] = sideDishChoices[sideDish_1 - 1];
        menu[2] = sideDishChoices[sideDish_2 - 1];
        menu[3] = dessertChoices[dessert - 1];
        return menu;
    }
    //sets dinEventEmps field array
    public void setEmps(Employee[] emps)
    {
        dinEventEmps = emps;
    }
    //returns dinEventEmps array field
    public Employee[] getEmps()
    {
        return dinEventEmps;
    }
}
