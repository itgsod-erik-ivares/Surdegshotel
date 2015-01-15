package surdegshotell;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

/**
 *
 * @author Erik
 */
public class GUI extends JFrame {
    private JTabbedPane panel = new JTabbedPane();
    private JPanel card1 = new JPanel();
    
    public GUI(){
        add(panel);
        panel.setPreferredSize(new Dimension(100, 100));
        card1.add(new JButton("button1"));
        card1.add(new JButton("button1"));
        card1.add(new JButton("button1"));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
