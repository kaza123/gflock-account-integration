/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kasun
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

//import com.controller.Controller;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    public JLabel inputTextArea;
    public JTextArea outputTextArea;
    private JButton inputBtn;
    private JButton outputBtn;
    private JButton sortBtn;
    public JRadioButton firstButton;
    public JRadioButton secondButton;
    public JRadioButton thirdButton;
    JSplitPane splitPane;

    //Controller controller;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
    //  controller = new Controller(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);  
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        /**
         * center
         * include two TextArea for display text
         */

        inputTextArea = new JLabel("ABC");
        outputTextArea = new JTextArea();

        // put two TextArea to JScrollPane so text can be scrolled when too long
        JScrollPane scrollPanelLeft = new JScrollPane(inputTextArea);
        JScrollPane scrollPanelRight = new JScrollPane(outputTextArea);

        // put two JScrollPane into SplitPane 
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                scrollPanelLeft, scrollPanelRight);
        splitPane.setOneTouchExpandable(true);
        contentPane.add(splitPane, BorderLayout.CENTER);

        /**
         * Top
         * Include two button : SelectFile and WriteToFile
         * this layout includes some tricky thing to done work
         */

        // create new input button
        inputBtn = new JButton("Select File");
        // declare action. when user click. will call Controller.readFile() method 
        // (see this method for detail)
        inputBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            //  controller.readFile();
            }
        });

        // create new output button
        outputBtn = new JButton("Write To File");
        // declare action. when user click. will call Controller.writeFile() method 
        // (see this method for detail)
        outputBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //  controller.writeFile();
            }
        });

        // put each button into seperate panel
        JPanel tmpPanel1 = new JPanel();
        tmpPanel1.add(inputBtn);
        JPanel tmpPanel2 = new JPanel();
        tmpPanel2.add(outputBtn);

        // finnally. put those two pane into TopPane
        // TopPane is GridLayout
        // by using this. we can sure that both two button always at center of screen like Demo
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(tmpPanel1);
        topPanel.add(tmpPanel2);
        contentPane.add(topPanel, BorderLayout.NORTH);

        /**
         * Bottom panel
         * Include all radionbutton and sortbutton
         */

        // Group the radio buttons.
        firstButton = new JRadioButton("Last Name");
        secondButton = new JRadioButton("Yards");
        thirdButton = new JRadioButton("Rating");
        // add those button into a group
        // so . ONLY ONE button at one time can be clicked
        ButtonGroup group = new ButtonGroup();
        group.add(firstButton);
        group.add(secondButton);
        group.add(thirdButton);

        // create sor button
        sortBtn = new JButton("Sort QB Stats");
        // add action for this button : will Call Controller.SortFile()
        sortBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            //  controller.sortFile();
            }
        });

        // add all to bottomPanel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(firstButton);
        bottomPanel.add(secondButton);
        bottomPanel.add(thirdButton);
        bottomPanel.add(sortBtn);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);
        setTitle("2013 College Quarterback Statistics");
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setVisible(true);
        System.out.println("getwidth: " + getWidth());
        splitPane.setDividerLocation(getWidth()/2);
    }

}
