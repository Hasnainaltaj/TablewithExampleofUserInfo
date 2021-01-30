package Tutorial12;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Tutorial12 extends Application {

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("JavaFX 8 Tutorial 12 - Table");
        primaryStage.setWidth(450);
        primaryStage.setHeight(550);

        TableView<User> tableView = new TableView<>();
        final ObservableList<User> data = FXCollections.observableArrayList(new User("Hamid", "Salam", "first@gmail.com"),
                new User("Tony", "Stark", "tonystark@gmail.com"),
                new User("Peter", "Parker", "peterparker@gmail.com"));

        tableView.setItems(data);
        tableView.setEditable(true);

        Label label = new Label("User Info");
        label.setFont(new Font("Arial", 20));

        TableColumn column1 = new TableColumn("First Name");
        column1.setMinWidth(100);
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn column2 = new TableColumn("Last Name");
        column2.setMinWidth(100);
        column2.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn column3 = new TableColumn("E-mail");
        column3.setMinWidth(100);
        column3.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(column1, column2, column3);

        TextField firstName = new TextField();
        firstName.setMaxWidth(column1.getPrefWidth());
        firstName.setPromptText("First Name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");
        lastName.setMaxWidth(column2.getPrefWidth());

        TextField email = new TextField();
        email.setMaxWidth(column3.getPrefWidth());
        email.setPromptText("E-mail");

        Button button = new Button("Add");
        button.setOnAction(e -> {
            data.add(
                    new User(
                            firstName.getText(),
                            lastName.getText(),
                            email.getText()
                    )
            );
            firstName.clear();
            lastName.clear();
            email.clear();
        });

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(firstName, lastName, email, button);

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(label, tableView, hBox);
        vBox.setPadding(new Insets(10, 0, 0, 10));


        Scene scene = new Scene(new Group());
        ((Group) scene.getRoot()).getChildren().addAll(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
