    package in.cdac.java.auth.core;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Find extends JFrame {
   public Find() {
      JLabel label = new JLabel("Find What:");
      JTextField textField = new JTextField();
      JCheckBox caseCheckBox = new JCheckBox("Match Case");
      JCheckBox wrapCheckBox = new JCheckBox("Wrap Around");
      JCheckBox wholeCheckBox = new JCheckBox("Whole Words");
      JCheckBox backCheckBox = new JCheckBox("Search Backwards");
      JButton findButton = new JButton("Find");
      JButton cancelButton = new JButton("Cancel");
      caseCheckBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      wrapCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      wholeCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      backCheckBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
      GroupLayout layout = new GroupLayout(this.getContentPane());
      this.getContentPane().setLayout(layout);
      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);
      layout.setHorizontalGroup(layout.createSequentialGroup().addComponent(label).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(textField).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(caseCheckBox).addComponent(wholeCheckBox)).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(wrapCheckBox).addComponent(backCheckBox)))).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(findButton).addComponent(cancelButton)));
      layout.linkSize(0, new Component[]{findButton, cancelButton});
      layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(label).addComponent(textField).addComponent(findButton)).addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(caseCheckBox).addComponent(wrapCheckBox)).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(wholeCheckBox).addComponent(backCheckBox))).addComponent(cancelButton)));
      this.setTitle("Find");
      this.pack();
      this.setDefaultCloseOperation(3);
   }

   public static void main(String[] args) {
     // EventQueue.invokeLater(new 1());
   }
}
 