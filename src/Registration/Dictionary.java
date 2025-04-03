package Registration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;


public class  Dictionary extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Label oroLabel = new Label("A/Oromo Word");
        Label engLabel = new Label("English Word");
        
        TextField oroText = new TextField();
        TextField engText = new TextField();
        
        Button translate = new Button("Translate");
        Button save = new Button("Save");
       
        save.setOnAction(e->{
            try {
                // register a driver
                Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
                // create a connection
                Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JAVA2025", "bonbonsa", "1994928");
                //  create statement    
                Statement st = conn.createStatement();
                //execute sql statement
                String oro, eng;
                oro = oroText.getText();
                eng = engText.getText();
                String sql = "Insert into Transilation values('"+oro+"','"+eng+"')";
                int i = st.executeUpdate(sql);
                if(i>0)
                {
                    System.out.println("Success");
                }
                else{
                    System.out.println("Failure");
                }
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());

            }
        });
        
//        translate.setOnAction(e->{
//            try{
//         // register a driver
//                Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
//                // create a connection
//               Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JAVA2025", "bonbonsa", "1994928");
//                  //  create statement    
//                Statement st = conn.createStatement();
//                String eng = "";
//                String oro = oroText.getText();
//                String sql = "Select * from Transilation where OROMO= '"+oro+"'";
//                
//                ResultSet rs = st.executeQuery(sql);
//                while(rs.next())
//                {
//                    eng = rs.getString("English");
//                }
//                engText.setText(eng);
//                
//                
//            }catch(Exception ex){
//                System.out.println(ex.getMessage());
//            }
//        });
//        

translate.setOnAction(e -> {
    try {
        // Register JDBC driver
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");

        // Create a connection
        Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/JAVA2025", "bonbonsa", "1994928");

        // Get input values
        String oro = oroText.getText().trim();
        String eng = engText.getText().trim();
        String translatedWord = "";

        // Prepare statement
        String sql = "";
        PreparedStatement pst;

        if (!oro.isEmpty() && eng.isEmpty()) {  // Oromo to English
            sql = "SELECT English FROM Transilation WHERE OROMO = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, oro);
        } else if (!eng.isEmpty() && oro.isEmpty()) {  // English to Oromo
            sql = "SELECT OROMO FROM Transilation WHERE English = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, eng);
        } else {
            System.out.println("Enter only one word to translate.");
            return;
        }
  
        // Execute query
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            translatedWord = rs.getString(1); // Gets the first column value
        }

        // Display translation
        if (!oro.isEmpty()) {
            engText.setText(translatedWord); // Display English translation
        } else {
            oroText.setText(translatedWord); // Display Oromo translation
        }

        // Close resources
        rs.close();
        pst.close();
        conn.close();

    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
});

        GridPane root =new GridPane();
        root.add(oroLabel, 0 ,0);
        root.add(oroText, 1 ,0);
        root.add(engLabel, 0 ,1);
        root.add(engText, 1 ,1);
        root.add(translate, 0 ,2);
        root.add(save, 1 ,2);
        
        root.setHgap(15);
        root.setVgap(15);
        
        root.setPadding(new Insets(15));
        
        Scene scene = new Scene(root, 300, 250);
        
                
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}






//package Registration;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//public class Dictionary extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Label oroLabel = new Label("Oromo Word:");
//        Label engLabel = new Label("English Word:");
//        
//        TextField oroText = new TextField();
//        TextField engText = new TextField();
//        
//        Button translate = new Button("Translate");
//        Button save = new Button("Save");
//        
//        
//
//        
//        GridPane root = new GridPane();
//        root.setHgap(10);
//        root.setVgap(10);
//        root.setPadding(new javafx.geometry.Insets(10));
//        
//        root.add(oroLabel, 0, 0);
//        root.add(oroText, 1, 0);
//        root.add(engLabel, 0, 1);
//        root.add(engText, 1, 1);
//        root.add(translate, 0, 2);
//        root.add(save, 1, 2);
//        
//        Scene scene = new Scene(root, 350, 250);
//        primaryStage.setTitle("Dictionary");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//    
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
//
//
//
//translate.setOnAction(e -> {
//            String oroWord = oroText.getText();
//            engText.setText("Translated: " + oroWord);
//        });
//        
//        save.setOnAction(e -> {
//            String oroWord = oroText.getText();
//            String engWord = engText.getText();
//            System.out.println("Saving: " + oroWord + " -> " + engWord);
//        });
