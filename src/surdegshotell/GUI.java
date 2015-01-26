package surdegshotell;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * GUI for the Surdegshotel application
 * @author Erik
 * @verision 2015-01-19
 */
public class GUI extends JFrame {
    private final Surdegshotell _main;
    private ArrayList<String> _checkedInDoughs = new ArrayList();
    
    //main panels of the gui
    private final JTabbedPane panel = new JTabbedPane();
    private final JPanel _checkInOut = new JPanel(new BorderLayout());
    private final JPanel _mending = new JPanel(new BorderLayout());
    private final JPanel _statistics = new JPanel(new BorderLayout());
    
    //Components for the checkinout tabb
    private final DefaultListModel _checkInListModel = new DefaultListModel();
    private final JList _checkInList = new JList(_checkInListModel); 
    private final JScrollPane _checkInScrollbar = new JScrollPane(_checkInList);
    private final ArrayList<JTextField> _checkInFields = new ArrayList();
    private final JButton _checkInButton = new JButton("Check In");
    private final JButton _checkOutButton = new JButton("Check Out");
    
    //components for the mending tabb
    private final DefaultListModel _mendingListModel = new DefaultListModel();
    private final JList _mendingList = new JList(_mendingListModel);
    private final JScrollPane _mendingScrollbar = new JScrollPane(_mendingList);
    private final JButton _mendingDoneButton = new JButton("Done");
    
    // components for the statistics tabb
    private final JTextField _statisticsFrom = new JTextField("From");
    private final JTextField _statisticsTo = new JTextField("to");
    private final JTextField _priceTextField = new JTextField("price");
    private final JButton _priceSubmittButton = new JButton("Submitt");
    private final JButton _statisticsGetButton = new JButton("Get Statistics");
    
    /**
     * Constructor for GUI initializes the constructions of the GUI components
     */
    public GUI(){
        _main = new Surdegshotell();
        setTitle("Sourdough hotel");
        fillGUI();
        addActionListners();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    
    /**
     * initializes the construction of each induvidual tabb
     */
    private void fillGUI(){
        add(panel);
        panel.setPreferredSize(new Dimension(700, 400));
        panel.addTab("Check in/out", _checkInOut);
        panel.addTab("Mending", _mending);
        panel.addTab("Statistics", _statistics);
        createCheckinOut();
        createMending();
        createStatistics();
        updateList();
    }

    /**
     * constructs the checkinout tabb
     */
    private void createCheckinOut() {
        _checkInFields.add(new JTextField("Name"));
        _checkInFields.add(new JTextField("Address"));
        _checkInFields.add(new JTextField("Number"));
        _checkInFields.add(new JTextField("E-Mail"));
        _checkInFields.add(new JTextField("Interval"));
        _checkInFields.add(new JTextField("Flour"));
        _checkInFields.add(new JTextField("Flour amount"));
        _checkInFields.add(new JTextField("Water amount"));
        _checkInFields.add(new JTextField("Special Request"));
        _checkInScrollbar.setPreferredSize(new Dimension(350, 250));
        _checkInScrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel checkInCenterPanel = new JPanel();
        _checkInOut.add(_checkInScrollbar, BorderLayout.NORTH);
        _checkInOut.add(checkInCenterPanel, BorderLayout.CENTER);
        for (JTextField _checkInField : _checkInFields) {
            _checkInField.setPreferredSize(new Dimension(75, 25));
            checkInCenterPanel.add(_checkInField);
        }
        checkInCenterPanel.add(_checkInButton);
        _checkInOut.add(_checkOutButton, BorderLayout.SOUTH);
    }

    /**
     * constructs the mending tabb
     */
    private void createMending() {
        _mendingList.setPreferredSize(new Dimension(350, 250));
//        _mendingScrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        _mending.add(_mendingList, BorderLayout.NORTH);
        _mending.add(_mendingDoneButton, BorderLayout.SOUTH);
        ArrayList<String> todaysDoughs = Sorter.getAllDoughsForToday();
        for (String dough : todaysDoughs) {
            _mendingListModel.addElement(dough);
        }
    }

    /**
     * constructs the statistics tabb
     */
    private void createStatistics() {
        _statisticsFrom.setPreferredSize(new Dimension(100, 25));
        _statisticsTo.setPreferredSize(new Dimension(100, 25));
        _priceTextField.setPreferredSize(new Dimension(100, 25));
        _priceSubmittButton.setPreferredSize(new Dimension(75, 50));
        _statisticsGetButton.setPreferredSize(new Dimension(150, 50));
        JPanel centerStatisticsPanel = new JPanel();
        JPanel southStatisticsPanel = new JPanel();
        _statistics.add(centerStatisticsPanel, BorderLayout.CENTER);
        _statistics.add(southStatisticsPanel, BorderLayout.SOUTH);
        centerStatisticsPanel.add(_statisticsFrom);
        centerStatisticsPanel.add(_statisticsTo);
        centerStatisticsPanel.add(_statisticsGetButton);
        southStatisticsPanel.add(_priceTextField, BorderLayout.SOUTH);
        southStatisticsPanel.add(_priceSubmittButton, BorderLayout.SOUTH);
    }
    
    public void updateList(){
        _checkInListModel.removeAllElements();
        _checkedInDoughs = FileManager.readFile("CheckedIn.txt");
        for (String string : _checkedInDoughs) {
            _checkInListModel.addElement(StringHandler.fixString(string));
        }
    }
    
    private void showBill(String dough){
        JFrame bill = new JFrame();
        bill.setLayout(new FlowLayout());
        ArrayList<String> billInfo = _main.createBill(dough);
        for (String info : billInfo) {
            bill.add(new JLabel(info));
        }
        bill.setTitle("Bill");
        bill.setPreferredSize(new Dimension(200, 200));
        JButton printButton = new JButton("Print to file");
        bill.add(printButton);
        printButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] list = dough.split(";");
                for (String info : billInfo) {
                    FileManager.writeToFile(list[1]+".txt", info);
                }
            }
        });
        bill.pack();
        bill.setVisible(true);
    }
    
    private void addActionListners(){
        _checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("***Checking in:***");
                try{
                    _main.checkIn(_checkInFields);
                    updateList();
                }
                catch(InvalidParameterException ipe){
                    System.out.println("somthing went wrong :§");
                }
            }
        });
        
        _checkOutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("***Checking Out:***");
                try{
                    String checkOutDough = _checkedInDoughs.get(_checkInList.getSelectedIndex());
                    System.out.println("initiating main code");
                    _main.checkOut(checkOutDough);
                    System.out.println("uppdating list");
                    updateList();
                    try{
                        showBill(checkOutDough);
                    }
                    catch(ArrayIndexOutOfBoundsException aioobe){
                        System.out.println(aioobe.getMessage());
                        System.out.println(aioobe.fillInStackTrace());
                    }
                }
                catch(Exception ex){
                    System.out.println("Could nor check out dough");
                }
            }
        });
    }
}