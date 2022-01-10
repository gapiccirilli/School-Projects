import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JSammysSeashore extends JFrame implements ActionListener, ItemListener
{
    //two parallel arrays. One holds a fill in index 0 to be displayed and the rest are equipment. The parallel integer array
    //holds corresponding prices. 0 is in for select
    //The lesson price is stored in a constant
    private final String[] EQUIPMENT_TYPES = {"--Select--", "Personal WaterCraft", "Pontoon Boat", "Rowboat", "Canoe", "Kayak",
    "Beach Chair", "Umbrella"};
    private final int[] PRICES = {0, 40, 40, 20, 20, 20, 7, 7};
    private final int LESSON = 5;
    //creates label and textbox for hours rented, Label and combo box for equipment type, checkbox for lesson add on
    //and button for getting price and calculating based on results
    JLabel hoursLabel = new JLabel("Hours Rented");
    JTextField hoursText = new JTextField(3);
    JLabel equipmentLabel = new JLabel("Equipment Type");
    JComboBox<String> equipmentType = new JComboBox<String>(EQUIPMENT_TYPES);
    JCheckBox lessonBox = new JCheckBox("Add Lesson ($5)", false);
    JButton getPrice = new JButton("Get Price");
    //constructor sets the name of the JFrame, default closing op (exit on close), sets coords and size and makes non resizable
    //sets flowlayout, disables the JButton and sets the displayed String to --select--
    public JSammysSeashore()
    {
        super("Sammy's Seashore Rental");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(550, 300, 300, 150);
        setResizable(false);
        setLayout(new FlowLayout());
        getPrice.setEnabled(false);
        equipmentType.setSelectedIndex(0);
        //adds event listener to button. The items info will be retrieved, calculated and displayed on button press
        //also adds ItemListener to comboBox
        getPrice.addActionListener(this);
        equipmentType.addItemListener(this);
        //adds all components to frame
        add(hoursLabel);
        add(hoursText);
        add(equipmentLabel);
        add(equipmentType);
        add(lessonBox);
        add(getPrice);
    }
    //combobox selections triggers event listener. A choice variable holds the selected index. If it's "--select--", then
    //the button stays disabled. If it's anything else, it's enabled.
    @Override
    public void itemStateChanged(ItemEvent event)
    {
        int choice;
        choice = equipmentType.getSelectedIndex();
        if (choice == 0)
        {
            getPrice.setEnabled(false);
        }
        else
        {
            getPrice.setEnabled(true);
        }
    }
    @Override
    public void actionPerformed(ActionEvent event)
    {
        //only one listener so don't have to specify source
        int hoursNum, choice, equipPrice, total;
        String choiceStr;
        //retrieves text from text box. A try...catch block is used when parsing hours from string to int. If a NumberFormatException
        //is thrown, the catch block sets the hours to 0
        String hours = hoursText.getText();
        try
        {
            hoursNum = Integer.parseInt(hours);
        }
        catch (NumberFormatException e)
        {
            hoursNum = 0;
        }
        //the index choice is stored and used in the PRICE array to retrieve the correct price. The string is stored for display
        //total is calculated
        choice = equipmentType.getSelectedIndex();
        choiceStr = (String)equipmentType.getSelectedItem();
        equipPrice = PRICES[choice];
        total = hoursNum * equipPrice;
        //lesson check box is evaluated and added if the check is checked. A separate window is used to display with lesson or
        //without lesson
        if (lessonBox.isSelected())
        {
            total += LESSON;
            JOptionPane.showMessageDialog(null, "You chose " + choiceStr + "\nYou added a lesson for " + choiceStr + "\nYour total is $" + total, 
            "Equipment Rental Price", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You chose " + choiceStr + "\nYour total is $" + total, 
            "Equipment Rental Price", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //instantiates JFrame object and makes it visible
    public static void main(String[] args)
    {
        JSammysSeashore mainFrame = new JSammysSeashore();
        mainFrame.setVisible(true);
    }
}
