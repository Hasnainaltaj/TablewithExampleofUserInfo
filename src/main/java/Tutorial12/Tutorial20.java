package Tutorial12;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Tutorial20 extends Application {

    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    TextField id, fn, ln, em, un;
    PasswordField pw;
    DatePicker datePicker;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 8 Tutorial 20 - DatePicker");


//        AnchorPane anchorPane = new AnchorPane();
//        Scene scene = new Scene(anchorPane, 400, 400);
//        primaryStage.setScene(scene);
        CheckConnection();
        BorderPane layout = new BorderPane();
        Scene newScene = new Scene(layout, 330, 500, Color.rgb(0, 0, 0, 0));

        // Create transparent stage
        //primaryStage.initStyle(StageStyle.TRANSPARENT);

        Group root = new Group();
        Scene scene = new Scene(root, 420, 210, Color.rgb(0, 0, 0, 0));
        //Cascading Style Sheet (CSS)
        scene.getStylesheets().add(getClass().getResource("../Style.css").toExternalForm());
        Color foreground = Color.rgb(255, 255, 255, 0.9);

        //Rounded Rectangular where we will add our components
        //Rectangular Background
        Rectangle background = new Rectangle(420, 210);
        background.setX(0);
        background.setY(0);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setFill(Color.rgb(0, 0, 0, 0.55));
        background.setStroke(foreground);
        background.setStrokeWidth(1.5);

        VBox vBox = new VBox(5);
        vBox.setPadding(new Insets(10, 0, 0, 10));
        Label label = new Label("Login Status");
        //label.setTextFill(Color.WHITESMOKE);
        label.setFont(new Font("SanSerif", 20));

        TextField userName = new TextField();
        userName.setFont(Font.font("SanSerif", 20));
        userName.setPromptText("User Name");

        userName.getStyleClass().add("field-background");

        userName.setMaxWidth(200);


        PasswordField password = new PasswordField();
        password.setFont(Font.font("SanSerif", 20));
        password.setPromptText("Password");
        password.setMaxWidth(200);

        Button button = new Button("Login");
        button.setFont(Font.font("SanSerif, 15"));
        button.setMaxWidth(200);


        password.setOnAction(e -> {
            try {
                String query = "SELECT * FROM UserTable WHERE UserName = ? and Password = ?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, userName.getText());
                preparedStatement.setString(2, password.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    label.setText("Login Successful");
                    primaryStage.setScene(newScene);
                } else {
                    label.setText("Login Failed");
                }
                userName.clear();
                password.clear();
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e1) {
                label.setText("SQL Error");
                e1.printStackTrace();
                System.out.println(e1);
            }
        });
        userName.setOnAction(e -> {
            try {
                String query = "SELECT * FROM UserTable WHERE UserName = ? and Password = ?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, userName.getText());
                preparedStatement.setString(2, password.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    label.setText("Login Successful");
                    primaryStage.setScene(newScene);
                } else {
                    label.setText("Login Failed");
                }
                userName.clear();
                password.clear();
                preparedStatement.close();
                resultSet.close();
            } catch (Exception e1) {
                label.setText("SQL Error");
                e1.printStackTrace();
                System.out.println(e1);
            }
        });
        button.setOnAction(e -> {

        });

        Button logout = new Button("Logout");
        logout.setFont(Font.font("SanSerif", 15));
        logout.setOnAction(e -> {
            primaryStage.setScene(scene);
            primaryStage.show();
        });
        layout.setTop(logout);
        BorderPane.setAlignment(logout, Pos.TOP_RIGHT);
        BorderPane.setMargin(logout, new Insets(10));
        vBox.getChildren().addAll(label, userName, password, button);
        root.getChildren().addAll(background, vBox);

        VBox fields = new VBox(5);
        Label label1 = new Label("Create New User");
        label1.setFont(new Font("SanSerif", 15));

        id = new TextField();
        id.setFont(Font.font("SanSerif", 20));
        id.setPromptText("ID");
        id.setMaxWidth(300);

        fn = new TextField();
        fn.setFont(Font.font("SanSerif", 20));
        fn.setPromptText("First Name");
        fn.setMaxWidth(300);

        ln = new TextField();
        ln.setFont(Font.font("SanSerif", 20));
        ln.setPromptText("Last Name");
        ln.setMaxWidth(300);

        em = new TextField();
        em.setFont(Font.font("SanSerif", 20));
        em.setPromptText("E-mail");
        em.setMaxWidth(300);

        un = new TextField();
        un.setFont(Font.font("SanSerif", 20));
        un.setPromptText("UserName");
        un.setMaxWidth(300);

        pw = new PasswordField();
        pw.setFont(Font.font("SanSerif", 20));
        pw.setPromptText("Password");
        pw.setMaxWidth(300);

        datePicker = new DatePicker();
        datePicker.setStyle("-fx-font-size: 20");
        datePicker.setPromptText("Date of Birth");
        datePicker.setMaxWidth(300);

        Button save = new Button("Save");
        save.setFont(Font.font("SanSerif", 15));

        save.setOnAction(e -> {
            try {
                String query = "INSERT INTO UserTable (ID, FirstName,LastName,Email,UserName,Password,DOB) VALUES (?,?,?,?,?,?,?)";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, id.getText());
                preparedStatement.setString(2, fn.getText());
                preparedStatement.setString(3, ln.getText());
                preparedStatement.setString(4, em.getText());
                preparedStatement.setString(5, un.getText());
                preparedStatement.setString(6, pw.getText());
                preparedStatement.setString(7, ((TextField) datePicker.getEditor()).getText());
                preparedStatement.execute();
                clearField();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User has been created...");
                alert.showAndWait();


                preparedStatement.close();

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });
        fields.getChildren().addAll(label1, id, fn, ln, em, un, pw, datePicker, save);
        layout.setCenter(fields);
        BorderPane.setMargin(fields, new Insets(0, 0, 0, 20));

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void clearField() {
        id.clear();
        fn.clear();
        ln.clear();
        em.clear();
        un.clear();
        pw.clear();
        datePicker.setValue(null);
    }


    public void CheckConnection() {
        conn = SqlConnection.DbConnector();
        if (conn == null) {
            System.out.println("Connection Not Successful");
            System.exit(1);
        } else {
            System.out.println("Connection Successful");
        }
    }

}
