import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Creates a blank html file in the specified directory.
 * Contains all necessary html elements.
 * @author Travis Baker
 */
public class MyGuiTest
{
   JFrame mFrame;
   JPanel mPanel;
   JTextField field;
   File file;
   
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
      mFrame.setVisible(true);
      mFrame.setSize(500, 200);
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
         String html = "";
         html += "<!DOCTYPE html>\n<html>\n  <head>\n    <title>Enter Title Here</title>\n";
         html += "  </head>\n  <body>\n  </body>\n</html>";
         String blankHTML = html;
         try
         {
            BufferedWriter fout = new BufferedWriter(new FileWriter(file));
            fout.write("<!DOCTYPE html>\n<html>\n  <head>\n    <title>Enter Title Here</title>\n  </head>\n  <body>\n  </body>\n</html>");
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
      html += "<!DOCTYPE html>\n<html>\n  <head>\n    <title>Enter Title Here</title>\n";
      html += "  </head>\n  <body>\n  </body>\n</html>";
      return html;
   }
}