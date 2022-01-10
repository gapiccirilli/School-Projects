import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//class extends JFrame and implements ActionListener for update button and ItemListener for check marks
public class JCarlysCatering extends JFrame implements ActionListener, ItemListener
{
    //constant for price per guest
    private final int PRICE_PER_GUEST = 35;
    //holds total guest price when guest number is entered
    private int guestPrice;
    //holds the amount of sides chosen so that they do not exceed 2
    private int sidesChosen;
    //label for guest number
    JLabel guestNumLabel = new JLabel("# of Guests");
    JTextField guestNumText = new JTextField(4);
    //textfield for guest count
    JButton button = new JButton("Update");
    //labels and checkboxes for all food items
    JLabel entreeLabel = new JLabel("Entree");
    JCheckBox chicken = new JCheckBox("Chicken", false);
    JCheckBox steak = new JCheckBox("Steak", false);
    JCheckBox salmon = new JCheckBox("Salmon", false);
    JCheckBox lobster = new JCheckBox("Lobster", false);
    JLabel sideLabel1 = new JLabel("Side Dishes");
    JCheckBox potatoes = new JCheckBox("Potatoes", false);
    JCheckBox broccoli = new JCheckBox("Broccoli", false);
    JCheckBox salad = new JCheckBox("Salad", false);
    JCheckBox sweetPot = new JCheckBox("Sweet Potato", false);
    JLabel dessertLabel = new JLabel("Dessert");
    JCheckBox applePie = new JCheckBox("Apple Pie", false);
    JCheckBox sundae = new JCheckBox("Sundae", false);
    JCheckBox bananaSplit = new JCheckBox("Banana Split", false);
    //text field that holds the total price
    JTextField price = new JTextField(4);
    //button groups to group entrees and desserts so that only one choice can be made
    ButtonGroup entreeGroup = new ButtonGroup();
    ButtonGroup dessertGroup = new ButtonGroup();
    //labels for menu choices to populate when selected
    JLabel list1 = new JLabel();
    JLabel list2 = new JLabel();
    JLabel list3 = new JLabel();
    JLabel list4 = new JLabel();

    public JCarlysCatering()
    {
        //calls JFrame super constructor and passes a title. Sets closing option to exit app on close. Sets frame to flowlayout
        //sets size and location on screen with setBounds()
        super("Carly's Catering");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setBounds(500, 400, 250, 300);
        //adds one action listener for button and 11 item listeners for checkboxes
        button.addActionListener(this);
        chicken.addItemListener(this);
        steak.addItemListener(this);
        salmon.addItemListener(this);
        lobster.addItemListener(this);
        potatoes.addItemListener(this);
        broccoli.addItemListener(this);
        salad.addItemListener(this);
        sweetPot.addItemListener(this);
        applePie.addItemListener(this);
        sundae.addItemListener(this);
        bananaSplit.addItemListener(this);
        //adds checkboxes to their groups
        entreeGroup.add(chicken);
        entreeGroup.add(steak);
        entreeGroup.add(salmon);
        entreeGroup.add(lobster);
        dessertGroup.add(applePie);
        dessertGroup.add(sundae);
        dessertGroup.add(bananaSplit);
        //finally adds all components to the frame
        add(guestNumLabel);
        add(guestNumText);
        add(button);
        add(entreeLabel);
        add(chicken);
        add(steak);
        add(salmon);
        add(lobster);
        add(sideLabel1);
        add(potatoes);
        add(broccoli);
        add(salad);
        add(sweetPot);
        add(dessertLabel);
        add(applePie);
        add(sundae);
        add(bananaSplit);
        add(price);
        add(list1);
        add(list2);
        add(list3);
        add(list4);
        //initializes sidesChosen to zero
        sidesChosen = 0;
    }
    //method to reset side dish check boxes if there are more than 2 side dishes selected
    private void resetChecks()
    {
        potatoes.setSelected(false);
        broccoli.setSelected(false);
        salad.setSelected(false);
        sweetPot.setSelected(false);
    }
    //overrides itemStateChanged from ItemListener interface
    @Override
    public void itemStateChanged(ItemEvent event)
    {
        //holds the state of the checkbox as an int
        int state;
        //holds the object in an object reference
        Object source = event.getItem();
        //The entree and dessert conditional statements are identical besides their different names and checkboxes.
        //first the source object is compared to each checkbox object, if there's a match then state is stored in state
        // and then state is compared to the integer for SELECTED. If theres a match there, then the corresponding list label
        //is populated with the correct text.
        if (source.equals(chicken))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
                list1.setText("Chicken");
            else
                list1.setText("");
        }
        else if (source.equals(steak))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
                list1.setText("Steak");
            else
                list1.setText("");
        }
        else if (source.equals(salmon))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
                list1.setText("Salmon");
            else
                list1.setText("");
        }
        else if (source.equals(lobster))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
                list1.setText("Lobster");
            else
                list1.setText("");
        }
        //The side dishes differ a bit from dessert and entree code. State is compared. If it matches selected, then sideChosen
        //is incremented then another if...else to make sure that sideChosen isn't more than 2. If it's 2 or less, then
        //the items are evaluated to see which item it is. If sideChosen is 1 then it's the 2nd label and 3rd if it's 2
        //if sideChosen is greater than 2, then the list labels are set to no text and the method resetChecks is called.
        //then sidesChosen is reset to 0;
        else if (source.equals(potatoes))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
            {
                ++sidesChosen;
                if (sidesChosen <= 2)
                {
                    if (sidesChosen == 1)
                        list2.setText("Potatoes");
                    else if (sidesChosen == 2)
                        list3.setText("Potatoes");
                }
                else
                {
                    list2.setText("");
                    list3.setText("");
                    resetChecks();
                    sidesChosen = 0;
                }
            }
            else
            {
                --sidesChosen;
            }
        }
        else if (source.equals(broccoli))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
            {
                ++sidesChosen;
                if (sidesChosen <= 2)
                {
                    if (sidesChosen == 1)
                        list2.setText("Broccoli");
                    else if (sidesChosen == 2)
                        list3.setText("Broccoli");
                }
                else
                {
                    list2.setText("");
                    list3.setText("");
                    resetChecks();
                    sidesChosen = 0;
                }
            }
            else
            {
                --sidesChosen;
            }
        }
        else if (source.equals(salad))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
            {
                ++sidesChosen;
                if (sidesChosen <= 2)
                {
                    if (sidesChosen == 1)
                        list2.setText("Salad");
                    else if (sidesChosen == 2)
                        list3.setText("Salad");
                }
                else
                {
                    list2.setText("");
                    list3.setText("");
                    resetChecks();
                    sidesChosen = 0;
                }
            }
            else
            {
                --sidesChosen;
            }
        }
        else if (source.equals(sweetPot))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
            {
                ++sidesChosen;
                if (sidesChosen <= 2)
                {
                    if (sidesChosen == 1)
                        list2.setText("Sweet Potato");
                    else if (sidesChosen == 2)
                        list3.setText("Sweet Potato");
                }
                else
                {
                    list2.setText("");
                    list3.setText("");
                    resetChecks();
                    sidesChosen = 0;
                }
            }
            else
            {
                --sidesChosen;
            }
        }
        else if (source.equals(applePie))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
                list4.setText("Apple Pie");
            else
                list4.setText("");
        }
        else if (source.equals(sundae))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
                list4.setText("Sundae");
            else
                list4.setText("");
        }
        else if (source.equals(bananaSplit))
        {
            state = event.getStateChange();
            if (state == ItemEvent.SELECTED)
                list4.setText("Banana Split");
            else
                list4.setText("");
        }
    }
    //overrides actionPerformed in ActionListener Interface
    @Override
    public void actionPerformed(ActionEvent event)
    {
        //holds guest num when parsed from string
        int guestNum;
        //extracts text from text field on button press
        String guestNumStr = guestNumText.getText();
        //text is attempted to be parsed to int and calculated. Catch block is placed ready to catch exception
        //if NumberFormatException is thrown, guestPrice ends up at 0
        try
        {
            guestNum = Integer.parseInt(guestNumStr);
            guestPrice = PRICE_PER_GUEST * guestNum;
        }
        catch (NumberFormatException e)
        {
            guestNum = 0;
            guestPrice = PRICE_PER_GUEST * guestNum;
        }
        //price textfield sets the final price
        price.setText("$" + guestPrice);
    }
    //main method instantiates frame of JCarlysCatering type and makes it visible
    public static void main(String[] args)
    {
        JCarlysCatering frame = new JCarlysCatering();
        frame.setVisible(true);
    }
}