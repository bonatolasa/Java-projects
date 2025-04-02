package Registration;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegistrationForm extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Registration Form");
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        
        // Name Label and Field
        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);
        
        // Phone Label and Field
        Label phoneLabel = new Label("Phone:");
        GridPane.setConstraints(phoneLabel, 0, 1);
        TextField phoneInput = new TextField();
        GridPane.setConstraints(phoneInput, 1, 1);
        
        // ID Label and Field
        Label idLabel = new Label("ID:");
        GridPane.setConstraints(idLabel, 0, 2);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 2);
        
        // Gender Radio Buttons
        Label genderLabel = new Label("Gender:");
        GridPane.setConstraints(genderLabel, 0, 3);
        ToggleGroup genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Male");
        maleRadio.setToggleGroup(genderGroup);
        RadioButton femaleRadio = new RadioButton("Female");
        femaleRadio.setToggleGroup(genderGroup);
        
        GridPane.setConstraints(maleRadio, 1, 3);
        GridPane.setConstraints(femaleRadio, 2, 3);
        
        // Department Dropdown (ChoiceBox)
        Label departmentLabel = new Label("Department:");
        GridPane.setConstraints(departmentLabel, 0, 4);
        ChoiceBox<String> departmentChoice = new ChoiceBox<>();
        departmentChoice.getItems().addAll("IT", "Computer", "Software", "Biomed");
        GridPane.setConstraints(departmentChoice, 1, 4);
        
        // Save Button
        Button saveButton = new Button("Save");
        GridPane.setConstraints(saveButton, 0, 5);
        saveButton.setOnAction(e -> {
            String gender = (maleRadio.isSelected()) ? "Male" : (femaleRadio.isSelected()) ? "Female" : "Not selected";
            String department = departmentChoice.getValue() != null ? departmentChoice.getValue() : "Not selected";
            System.out.println("Saved: " + nameInput.getText() + ", " + phoneInput.getText() + ", " + idInput.getText() + ", " + gender + ", " + department);
        });
        
        // Reset Button
        Button resetButton = new Button("Reset");
        GridPane.setConstraints(resetButton, 1, 5);
        resetButton.setOnAction(e -> {
            nameInput.clear();
            phoneInput.clear();
            idInput.clear();
            genderGroup.selectToggle(null);
            departmentChoice.setValue(null);
        });
        
        grid.getChildren().addAll(nameLabel, nameInput, phoneLabel, phoneInput, idLabel, idInput,
                                   genderLabel, maleRadio, femaleRadio, departmentLabel, departmentChoice,
                                   saveButton, resetButton);
        
        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
