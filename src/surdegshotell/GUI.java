package surdegshotell;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Erik
 */
public class GUI extends JFrame {
    private final JTabbedPane panel = new JTabbedPane();
    private final JPanel _checkInOut = new JPanel(new BorderLayout());
    private final JPanel _mending = new JPanel(new BorderLayout());
    private final JPanel _statistics = new JPanel(new BorderLayout());
    
    private final DefaultListModel _checkInListModel = new DefaultListModel();
    private final JList _checkInList = new JList(_checkInListModel); 
    private final JScrollPane _checkInScrollbar = new JScrollPane(_checkInList);
    private ArrayList<JTextField> _checkInFields = new ArrayList();
    private final JButton _checkInButton = new JButton("Check In");
    private final JButton _checkOutButton = new JButton("Check Out");
    
    private final DefaultListModel _mendingListModel = new DefaultListModel();
    private final JList _mendingList = new JList(_mendingListModel);
    private final JScrollPane _mendingScrollbar = new JScrollPane(_mendingList);
    private final JButton _mendingDoneButton = new JButton("Done");
    
    private final JTextField _statisticsFrom = new JTextField("From");
    private final JTextField _statisticsTo = new JTextField("to");
    private final JTextField _priceTextField = new JTextField("price");
    private final JButton _priceSubmittButton = new JButton("Submitt");
    private final JButton _statisticsGetButton = new JButton("Get Statistics");
    
    public GUI(){
        setTitle("Sourdough hotel");
        fillGUI();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void fillGUI(){
        add(panel);
        panel.setPreferredSize(new Dimension(700, 400));
        panel.addTab("Check in/out", _checkInOut);
        panel.addTab("Mending", _mending);
        panel.addTab("Statistics", _statistics);
        createCheckinOut();
        createMending();
        createStatistics();
    }

    private void createCheckinOut() {
        for (int i = 0; i < 7; i++) {
            _checkInFields.add(new JTextField("Testing"));
        }
        _checkInList.setPreferredSize(new Dimension(350, 250));
        _checkInScrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        _checkInOut.add(_checkInList, BorderLayout.NORTH);
        JPanel checkInCenterPanel = new JPanel();
        _checkInOut.add(checkInCenterPanel, BorderLayout.CENTER);
        for (JTextField _checkInField : _checkInFields) {
            _checkInField.setPreferredSize(new Dimension(75, 25));
            checkInCenterPanel.add(_checkInField, BorderLayout.CENTER);
        }
        checkInCenterPanel.add(_checkInButton);
        _checkInOut.add(_checkOutButton, BorderLayout.SOUTH);
    }

    private void createMending() {
        _mendingList.setPreferredSize(new Dimension(350, 250));
        _mendingScrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        _mending.add(_mendingList, BorderLayout.NORTH);
        _mending.add(_mendingDoneButton, BorderLayout.SOUTH);
    }

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
}