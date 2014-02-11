import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class MyGuiTest
{
   JFrame mFrame;
   JPanel mPanel;
   JTextField field;
   File file;
   String htmlText;
   JEditorPane htmlEdit;
   JTextField titleEdit;
   JPanel textPanel;
   
   final JFileChooser fc = new JFileChooser();
   
   public static void main(String[] args)
   {
      new MyGuiTest().buildGui();
   }
   
   public void buildGui()
   {
      mFrame = new JFrame("File Opener");
      JButton open = new JButton("Save location");
      open.addActionListener(new MyOpenListener());
      mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mPanel = new JPanel();
      mFrame.getContentPane().add(BorderLayout.NORTH, open);
      field = new JTextField(25);
      field.setText("No file specified...");
      field.addFocusListener(new MyTextFieldListener());
      JButton submit = new JButton("Submit");
      submit.addActionListener(new MySubmitListener());
      JLabel inputFile = new JLabel("File name: ");
      mPanel.add(inputFile);
      mPanel.add(field);
      mPanel.add(submit);
      mFrame.getContentPane().add(BorderLayout.CENTER, mPanel);
      JLabel title = new JLabel("Web Page Title:");
      textPanel = new JPanel();
      textPanel.setPreferredSize(new Dimension(500, 500));
      titleEdit = new JTextField(30);
      htmlEdit = new JEditorPane();
      htmlEdit.setContentType("text/html");
      htmlEdit.setPreferredSize(new Dimension(400, 400));
      htmlEdit.setBorder(new BevelBorder(BevelBorder.LOWERED));
      textPanel.add(title);
      textPanel.add(titleEdit);
      textPanel.add(htmlEdit);
      
      mFrame.getContentPane().add(BorderLayout.SOUTH, textPanel);
      mFrame.setVisible(true);
      mFrame.setSize(500, 600);
   }
   
   class MyOpenListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         int returnVal = fc.showSaveDialog(mPanel);
         
         if (returnVal == JFileChooser.APPROVE_OPTION)
         {
            file = fc.getSelectedFile();
            field.setText(file.getAbsolutePath());
         }
         else
         {
            field.setText("No file specified...");
         }
      }
   }
   
   class MyTextFieldListener implements FocusListener
   {
      public void focusGained(FocusEvent e)
      {
         field.selectAll();
      }
      
      public void focusLost(FocusEvent e)
      {
         if (field.getText().equals(""))
         {
            field.setText("No file specified...");
         }
      }
   }
   class MySubmitListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         String blankHTML = generateBlankHTML();
         try
         {
            BufferedWriter fout = new BufferedWriter(new FileWriter(file));
            fout.write(blankHTML);
            fout.close();
         }
         catch (Exception e)
         {
            System.out.println("Could not write the file.");
         }
      }
   }
   
   public String generateBlankHTML()
   {
      String html = "";
      html += "<!DOCTYPE html>\n";
      html += htmlEdit.getText();
      return html;
   }
}