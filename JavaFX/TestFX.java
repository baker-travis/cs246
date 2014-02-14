import javafx.application.Application;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.*;
import javafx.scene.web.HTMLEditor;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;

public class TestFX extends Application 
{
   private FileChooser fc;
   private HTMLEditor editor;
   private File filename;
   
   public static void main(String[] args) 
   {
      launch(args);
   }
    
   @Override
   public void start(Stage primaryStage) {
      primaryStage.setTitle("HTML Editor");
      fc = new FileChooser();
      GridPane grid = new GridPane();
      grid.setAlignment(Pos.CENTER);
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(25, 25, 25, 25));
      
      Label title = new Label("Page Title:");
      grid.add(title, 0, 1);

      TextField titleTextField = new TextField();
      grid.add(titleTextField, 1, 1);
      
      editor = new HTMLEditor();
      
      grid.add(editor, 0, 2, 2, 1);
      
      Button btn = new Button("Save");
      HBox hbBtn = new HBox(10);
      hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
      hbBtn.getChildren().add(btn);
      grid.add(hbBtn, 0, 3);
      
      btn.setOnAction(new EventHandler<ActionEvent>() 
      { 
         @Override
         public void handle(ActionEvent e) 
         {
            filename = fc.showSaveDialog(primaryStage);
            System.out.println(filename.getName());
            System.out.println(editor.getHtmlText());
            try
            {
               BufferedWriter fout = new BufferedWriter(new FileWriter(filename));
               fout.write(editor.getHtmlText());
               fout.close();
               primaryStage.setTitle(filename.getName());
            }
            catch (Exception error)
            {
               System.out.println("Could not write the file.");
            }
         }
      });
      
      Scene scene = new Scene(grid, 700, 400);
      primaryStage.setScene(scene);
      scene.getStylesheets().add(TestFX.class.getResource("LogIn.css").toExternalForm());
      primaryStage.show();
   }
}