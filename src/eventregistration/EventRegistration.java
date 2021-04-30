/**
 * EventRegistration.java
 * @author Sicelo Zitha (216140943)
 * Date: 28 September 2020
 */

package eventregistration;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;

public class EventRegistration extends JFrame implements ActionListener {
    private JPanel panelNorth;
    private JPanel panelCenter, panelRB1, panelRB2;
    private JPanel panelSouth;
    
    private JLabel lblEventLogo;
    private JLabel lblHeading;
    
    private JLabel lblStudentNumber;
    private JTextField txtStudentNumber;
        
    private JLabel lblFirstName;
    private JTextField txtFirstName;
        
    private JLabel lblLastName;
    private JTextField txtLastName;
        
    private JLabel lblProgramme;
    private JComboBox cboProgramme;
    
    private JLabel lblHasResources;
    private JRadioButton radHasResourcesYes;
    private JRadioButton radHasResourcesNo;
    
    private ButtonGroup resourcesGroup;
    
    private JButton btnSave, btnReset, btnExit;
    private Font ft1, ft2, ft3;
        
    public EventRegistration() {
        super("Hackathon Event Registration App version 1.0");
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        panelRB1 = new JPanel();
        panelRB2 = new JPanel();
        panelSouth = new JPanel();
        
        lblEventLogo = new JLabel(new ImageIcon("user.png"));
        lblHeading = new JLabel("Hackathon Event Registration");

        lblStudentNumber = new JLabel("Student Number: ");
        txtStudentNumber = new JTextField();
        
        lblFirstName = new JLabel("First Name: ");
        txtFirstName = new JTextField();
        
        lblLastName = new JLabel("Last Name: ");
        txtLastName = new JTextField();
        
        lblProgramme = new JLabel("Programme: ");
        cboProgramme = new JComboBox();
        cboProgramme.addItem("Applications Development");
        cboProgramme.addItem("Communication Networks");
        cboProgramme.addItem("Multimedia Technology");
        
        lblHasResources = new JLabel("Do you have a resources (pc or laptop and internet): ");
        radHasResourcesYes = new JRadioButton("Yes");
        radHasResourcesNo = new JRadioButton("No");
        
        resourcesGroup = new ButtonGroup();
        
        btnSave = new JButton("Save");
        btnReset = new JButton("Reset");
        btnExit = new JButton("Exit");
        
        ft1 = new Font("Arial", Font.BOLD, 32);
        ft2 = new Font("Arial", Font.PLAIN, 22);
        ft3 = new Font("Arial", Font.PLAIN, 24);
    }
    
    public void setGUI() {
        panelNorth.setLayout(new FlowLayout());
        panelCenter.setLayout(new GridLayout(5, 2));
        panelRB1.setLayout(new GridLayout(1, 2));
        panelRB2.setLayout(new GridLayout(1, 2));
        panelSouth.setLayout(new GridLayout(1, 3));
        
        panelNorth.add(lblEventLogo);
        panelNorth.add(lblHeading);
        lblHeading.setFont(ft1);
        lblHeading.setForeground(Color.yellow);
        panelNorth.setBackground(new Color(0, 106, 255));
            
        lblStudentNumber.setFont(ft2);
        lblStudentNumber.setHorizontalAlignment(JLabel.RIGHT);
        txtStudentNumber.setFont(ft2);
        panelCenter.add(lblStudentNumber);
        panelCenter.add(txtStudentNumber);
                
        lblFirstName.setFont(ft2);
        lblFirstName.setHorizontalAlignment(JLabel.RIGHT);
        txtFirstName.setFont(ft2);
        panelCenter.add(lblFirstName);
        panelCenter.add(txtFirstName);
                
        lblLastName.setFont(ft2);
        lblLastName.setHorizontalAlignment(JLabel.RIGHT);
        txtLastName.setFont(ft2);
        panelCenter.add(lblLastName);
        panelCenter.add(txtLastName);
        
        lblProgramme.setFont(ft2);
        lblProgramme.setHorizontalAlignment(JLabel.RIGHT);
        cboProgramme.setFont(ft2);
        panelCenter.add(lblProgramme);
        panelCenter.add(cboProgramme);
        panelCenter.setBackground(new Color(36, 145, 255));
        
        lblHasResources.setFont(ft2);
        lblHasResources.setHorizontalAlignment(JLabel.RIGHT);
        radHasResourcesYes.setFont(ft2);
        radHasResourcesYes.setHorizontalAlignment(JRadioButton.CENTER);
        radHasResourcesYes.setBackground(new Color(36, 145, 255));
        radHasResourcesNo.setFont(ft2);
        radHasResourcesNo.setHorizontalAlignment(JRadioButton.LEFT);
        radHasResourcesNo.setBackground(new Color(36, 145, 255));
        radHasResourcesYes.setSelected(true);
        
        resourcesGroup.add(radHasResourcesYes);
        resourcesGroup.add(radHasResourcesNo);
        
        panelCenter.add(lblHasResources);      
        panelRB1.add(radHasResourcesYes);
        panelRB1.add(radHasResourcesNo);
        panelCenter.add(panelRB1);
        
        btnSave.setFont(ft3);
        btnReset.setFont(ft3);
        btnExit.setFont(ft3);
        panelSouth.add(btnSave);
        panelSouth.add(btnReset);
        panelSouth.add(btnExit);
        
        this.add(panelNorth, BorderLayout.NORTH);
        this.add(panelCenter, BorderLayout.CENTER);
        this.add(panelSouth, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        btnSave.addActionListener(this);
        btnReset.addActionListener(this);
        btnExit.addActionListener(this);
        
        this.setSize(600, 600);
        this.pack();
        this.setVisible(true);
    }
    
    private boolean isInputValid() {
        // determines if the student number is valid
        return true;
    }
    
    private void resetForm() {
        txtStudentNumber.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        cboProgramme.setSelectedIndex(0);
        radHasResourcesYes.setSelected(true);
        txtStudentNumber.requestFocus();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            if (isInputValid()) {
                // update database
                Event attendee = new Event(txtStudentNumber.getText(), 
                                           txtFirstName.getText(), 
                                           txtLastName.getText(),
                                           cboProgramme.getSelectedItem().toString(),
                                           radHasResourcesYes.isSelected()?"Yes":"No");
                if (attendee.isUniqueStudentNumber()) {
                    attendee.save();
                    resetForm();
                }
                else { 
                    JOptionPane.showMessageDialog(this, "The student number is not unique");
                    txtStudentNumber.requestFocus();
                }
            }
        }
        else if (e.getActionCommand().equals("Reset")) {
            resetForm();
        }   
        else if (e.getActionCommand().equals("Exit")) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new EventRegistration().setGUI();
    }    
}