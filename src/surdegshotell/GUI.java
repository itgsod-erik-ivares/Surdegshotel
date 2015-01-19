package surdegshotell;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 *
 * @author Erik
 */
public class GUI extends JFrame {
    private JTabbedPane panel = new JTabbedPane();
    private JPanel _checkInOut = new JPanel(new BorderLayout());
    private JPanel _mending = new JPanel(new BorderLayout());
    private JPanel _statistics = new JPanel(new BorderLayout());
    
    private DefaultListModel model = new DefaultListModel();
    private JList _checkInList = new JList(model); 
    private JScrollPane scrollbar = new JScrollPane(_checkInList);
    private ArrayList<JTextField> _checkInFields = new ArrayList();
    private JButton _checkInButton = new JButton("Check In");
    private JButton _checkOutButton = new JButton("Check Out");
    
    public GUI(){
        setTitle("Sourdough hotel");
        fillGUI();
        pack();
        setVisible(true);
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
        scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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

    }

    private void createStatistics() {

    }
}
