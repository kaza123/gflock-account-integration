/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author kasun
 */
public class Loding {

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Test");

        ImageIcon loading2 = new ImageIcon("./images/loder.gif");
        frame.add(new JLabel("Image3 ", loading2, JLabel.CENTER));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setUndecorated(true);
        frame.setOpacity(0.5f);
        frame.setVisible(true);
    }

   
   
}
