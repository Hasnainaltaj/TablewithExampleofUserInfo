package Tutorial12;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Tutorial17 extends Application {

    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;


    @Override
    public void start(Stage primaryStage) {

        CheckConnection();
        primaryStage.setTitle("JavaFX 8 Tutorial 17 - Switching");
        BorderPane layout = new BorderPane();
        Scene newScene = new Scene(layout, 500, 250, Color.rgb(0, 0, 0, 0));
        primaryStage.initStyle(StageStyle.DECORATED);

        Group root = new Group();
        Scene scene = new Scene(root, 360, 210, Color.rgb(0, 0, 0, 0));
        scene.getStylesheets().add(getClass().getResource("../Style.css").toExternalForm());

        Color foreground = Color.rgb(255, 255, 255, 0.9);

        //Rounded Rectangular where we will add our components
        //Rectangular Background
        Rectangle background = new Rectangle(360, 210);
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
        label.setTextFill(Color.WHITESMOKE);
        label.setFont(new Font("SanSerif", 20));

        TextField userName = new TextField();
        userName.setFont(Font.font("SanSerif", 20));
        userName.setPromptText("User Name");
        userName.setMaxWidth(200);
        userName.getStyleClass().add("field-background");


        PasswordField password = new PasswordField();
        password.setFont(Font.font("SanSerif", 20));
        password.setPromptText("Password");
        password.setMaxWidth(200);
        password.getStyleClass().add("field-password");

        Button button = new Button("Login");
        button.setFont(Font.font("SanSerif, 15"));
        button.setMaxWidth(200);


        button.setOnAction(e -> {
            try {
                String query = "SELECT * FROM UserTable WHERE UserName = ? and Password = ?";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, userName.getText());
                preparedStatement.setString(2, password.getText());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    label.setText("Login Successful");
                    primaryStage.setScene(newScene);
                    primaryStage.show();
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


        Button logout = new Button("logout");
        logout.setFont(Font.font("SanSerif", 15));

        layout.setTop(logout);
        BorderPane.setAlignment(logout, Pos.TOP_RIGHT);
        BorderPane.setMargin(logout, new Insets(10));

        logout.setOnAction(e -> {
            primaryStage.setScene(scene);
            primaryStage.show();
        });


        vBox.getChildren().addAll(label, userName, password, button);
        root.getChildren().addAll(background, vBox);
        primaryStage.setScene(scene);
        primaryStage.show();

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
