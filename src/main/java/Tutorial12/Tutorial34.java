package Tutorial12;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tutorial34 extends Application {

    Connection conn;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    TextField id, fn, ln, em, un, mobile;
    PasswordField pw;
    DatePicker datePicker;
    final ObservableList options = FXCollections.observableArrayList();
    TableView<User2> tableView = new TableView<>();
    final ObservableList<User2> data = FXCollections.observableArrayList();
    private RadioButton male;
    private RadioButton female;
    private String radioButtonLabel;
    private ListView listView = new ListView(options);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 8 Tut 38 - Mobile Number Validation");


        CheckConnection();
        fillComboBox();
        BorderPane layout = new BorderPane();
        Scene newScene = new Scene(layout, 1200, 600, Color.rgb(0, 0, 0, 0));

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


        password.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.getKeyCode("Enter")) {
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
            }
        });
//        userName.setOnAction(e -> {
//            try {
//                String query = "SELECT * FROM UserTable WHERE UserName = ? and Password = ?";
//                preparedStatement = conn.prepareStatement(query);
//                preparedStatement.setString(1, userName.getText());
//                preparedStatement.setString(2, password.getText());
//                resultSet = preparedStatement.executeQuery();
//                if (resultSet.next()) {
//                    label.setText("Login Successful");
//                    primaryStage.setScene(newScene);
//                } else {
//                    label.setText("Login Failed");
//                }
//                userName.clear();
//                password.clear();
//                preparedStatement.close();
//                resultSet.close();
//            } catch (Exception e1) {
//                label.setText("SQL Error");
//                e1.printStackTrace();
//                System.out.println(e1);
//            }
//        });
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

        Button logout = new Button("Logout");
        logout.setFont(Font.font("SanSerif", 15));
        logout.setOnAction(e -> {
            for (int i = 0; i < tableView.getItems().size(); i++) {
                tableView.getItems().clear();
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        });

        Button clear = new Button("clear");
        clear.setFont(Font.font("SanSerif", 15));
        clear.setOnAction(e -> {
            for (int i = 0; i < tableView.getItems().size(); i++) {
                tableView.getItems().clear();
            }
            clearField();
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

        mobile = new TextField();
        mobile.setFont(Font.font("SanSerif", 20));
        mobile.setPromptText("Mobile No.");
        mobile.setMaxWidth(300);

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

        ToggleGroup gender = new ToggleGroup();
        male = new RadioButton("Male");
        male.setToggleGroup(gender);
        male.setOnAction(e -> {
            radioButtonLabel = male.getText();
        });
        female = new RadioButton("Female");
        female.setToggleGroup(gender);
        //To get the value of the Radio Button
        female.setOnAction(e -> {
            radioButtonLabel = female.getText();
        });
//        gender.getToggles().addAll(male, female);

        Button save = new Button("Save");
        save.setFont(Font.font("SanSerif", 15));

        save.setOnAction(e -> {
            if (validateFields() && validateNumber() && validateFirstLastName() && validateEmail() && validatePassword()) {
                try {
                    String query = "INSERT INTO UserTable (ID, FirstName,LastName,Email,UserName,Password,DOB, gender) VALUES (?,?,?,?,?,?,?,?)";
                    preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setString(1, id.getText());
                    preparedStatement.setString(2, fn.getText());
                    preparedStatement.setString(3, ln.getText());
                    preparedStatement.setString(4, em.getText());
                    preparedStatement.setString(5, un.getText());
                    preparedStatement.setString(6, pw.getText());
                    preparedStatement.setString(7, ((TextField) datePicker.getEditor()).getText());
                    preparedStatement.setString(8, radioButtonLabel);
                    preparedStatement.execute();
                    clearField();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("تم اضافة الحساب");
                    alert.showAndWait();


                    preparedStatement.close();


                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                load();
                fillComboBox();
            }
        });
        fields.getChildren().addAll(label1, id, fn, ln, em, mobile, un, pw, datePicker, male, female, save);
        listView.setMaxSize(100, 250);
        layout.setLeft(listView);
        BorderPane.setMargin(listView, new Insets(10));
        layout.setCenter(fields);
        BorderPane.setMargin(fields, new Insets(0, 0, 0, 20));


        TableColumn column1 = new TableColumn("ID");
        column1.setMinWidth(20);
        column1.setPrefWidth(20);
        column1.setMaxWidth(20);
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn column2 = new TableColumn("First Name");
        column2.setMinWidth(70);
        column2.setMaxWidth(70);
        column2.setCellValueFactory(new PropertyValueFactory<>("FirstName"));

        TableColumn column3 = new TableColumn("Last Name");
        column3.setMinWidth(70);
        column3.setMaxWidth(70);
        column3.setCellValueFactory(new PropertyValueFactory<>("LastName"));

        TableColumn column4 = new TableColumn("E-mail");
        column4.setMinWidth(150);
        column4.setMaxWidth(150);
        column4.setCellValueFactory(new PropertyValueFactory<>("Email"));

        TableColumn column5 = new TableColumn("User Name");
        column5.setMinWidth(80);
        column5.setCellValueFactory(new PropertyValueFactory<>("UserName"));

        TableColumn column6 = new TableColumn("Password");
        column6.setMinWidth(80);
        column6.setCellValueFactory(new PropertyValueFactory<>("Password"));

        TableColumn column7 = new TableColumn("Date of Birth");
        column7.setMinWidth(80);
        column7.setCellValueFactory(new PropertyValueFactory<>("DOB"));

        TableColumn column8 = new TableColumn("Gender");
        column8.setMinWidth(60);
        column8.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn column9 = new TableColumn("Mobile");
        column9.setMinWidth(60);
        column9.setCellValueFactory(new PropertyValueFactory<>("mobile"));


        tableView.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8, column9);
        //tableView.setTableMenuButtonVisible(true);
        layout.setRight(tableView);
        BorderPane.setMargin(tableView, new Insets(0, 10, 10, 0));

        Button load = new Button("Load");
        load.setFont(Font.font("SanSerif", 15));
        load.setOnAction(e -> {
            load();
        });

//        fields.getChildren().add(load);

        ComboBox comboBox = new ComboBox(options);
        comboBox.setMaxHeight(30);
        comboBox.setMaxWidth(300);
        comboBox.setPromptText("First Name");

        comboBox.setOnAction(e -> {
            String query = "SELECT * FROM UserTable WHERE FirstName = ?";

            try {
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, (String) comboBox.getSelectionModel().getSelectedItem());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    id.setText(resultSet.getString("ID"));
                    fn.setText(resultSet.getString("FirstName"));
                    ln.setText(resultSet.getString("LastName"));
                    em.setText(resultSet.getString("Email"));
                    un.setText(resultSet.getString("UserName"));
                    pw.setText(resultSet.getString("Password"));
                    ((TextField) datePicker.getEditor()).setText(resultSet.getString("DOB"));
//                    if ("Male".equals(resultSet.getString("Gender"))) {
//                        male.setSelected(true);
//                    } else if ("Female".equals(resultSet.getString("Gender"))) {
//                        female.setSelected(true);
//                    } else {
//
//                        male.setSelected(false);
//                        female.setSelected(false);
//                    }

                    //SWITCH Retrieve data into comboBox
                    if (null != resultSet.getString("Gender")) switch (resultSet.getString("gender")) {
                        case "Male":
                            male.setSelected(true);
                            break;
                        case "Female":
                            female.setSelected(true);
                            break;
                        default:
                            male.setSelected(false);
                            female.setSelected(false);
                            break;
                    }
                    else {
                        male.setSelected(false);
                        female.setSelected(false);
                    }
                }
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        listView.setOnMouseClicked(e -> {
            String query = "SELECT * FROM UserTable WHERE FirstName = ?";

            try {
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, (String) listView.getSelectionModel().getSelectedItem());
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    id.setText(resultSet.getString("ID"));
                    fn.setText(resultSet.getString("FirstName"));
                    ln.setText(resultSet.getString("LastName"));
                    em.setText(resultSet.getString("Email"));
                    un.setText(resultSet.getString("UserName"));
                    pw.setText(resultSet.getString("Password"));
                    ((TextField) datePicker.getEditor()).setText(resultSet.getString("DOB"));
//                    if ("Male".equals(resultSet.getString("Gender"))) {
//                        male.setSelected(true);
//                    } else if ("Female".equals(resultSet.getString("Gender"))) {
//                        female.setSelected(true);
//                    } else {
//
//                        male.setSelected(false);
//                        female.setSelected(false);
//                    }

                    //SWITCH Retrieve data into comboBox
                    if (null != resultSet.getString("Gender")) switch (resultSet.getString("gender")) {
                        case "Male":
                            male.setSelected(true);
                            break;
                        case "Female":
                            female.setSelected(true);
                            break;
                        default:
                            male.setSelected(false);
                            female.setSelected(false);
                            break;
                    }
                    else {
                        male.setSelected(false);
                        female.setSelected(false);
                    }
                }
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });

        Button delete = new Button("Delete User");
        delete.setFont(Font.font("SanSerif", 15));
        delete.setOnAction(e -> {
//JavaFX 8 Tutorial 29 - Confirmation Dialog Before Deleting a User From Database
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("سيتم حذف أسم مستخدم!!!");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                if (validateFields()) {
                    try {
                        String query = "DELETE FROM UserTable WHERE id = ?";
                        preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setString(1, id.getText());
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    } catch (SQLException throwables) {
                        System.out.println(throwables);
                    }
                    clearField();
                    fillComboBox();
                    load();
                }
            }

        });

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(load, comboBox, clear, delete);
        fields.getChildren().add(hBox);
        //layout.setBottom(hBox);
        BorderPane.setMargin(hBox, new Insets(10));
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void load() {
        data.clear();
        try {
            String query = "SELECT * FROM UserTable";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data.add(new User2(
                        resultSet.getInt("id"),
                        resultSet.getString("firstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Mobile"),
                        resultSet.getString("UserName"),
                        resultSet.getString("Password"),
                        resultSet.getString("DOB"),
                        resultSet.getString("gender")

                ));
                tableView.setItems(data);
            }


            preparedStatement.close();
            resultSet.close();

        } catch (Exception loadError) {
            System.err.println(loadError);
            loadError.printStackTrace();
        }
    }

    public void fillComboBox() {
        options.clear();
        String query = "SELECT FirstName FROM UserTable ";
        try {
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                options.add(resultSet.getString("FirstName"));
            }
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private boolean validateNumber() {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(id.getText());
        if (matcher.find() && matcher.group().equals(id.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Number");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Number");
            alert.showAndWait();
            return false;
        }
    }

    private boolean validateFirstLastName() {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(fn.getText());
        Matcher matcher1 = pattern.matcher(ln.getText());
        if ((matcher.find() && matcher.group().equals(fn.getText())) && (matcher1.find() && matcher1.group().equals(ln.getText()))) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate First and Last Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter valid first name and last name");
            alert.showAndWait();
            return false;
        }
    }

    private boolean validateEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher matcher = pattern.matcher(em.getText());
        if (matcher.find() && matcher.group().equals(em.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate E-Mail");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid E-Mail");
            alert.showAndWait();
            return false;
        }
    }

    private boolean validatePassword() {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})");
        Matcher matcher = pattern.matcher(pw.getText());
        if (matcher.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Password");
            alert.showAndWait();
            return false;
        }
    }


    private boolean validateFields() {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Empty fields");
        warning.setHeaderText(null);
        if (id.getText().isEmpty() |
                fn.getText().isEmpty() |
                ln.getText().isEmpty() |
                em.getText().isEmpty() |
                un.getText().isEmpty() |
                pw.getText().isEmpty()
        ) {
            warning.setContentText("Empty Fields");
            warning.showAndWait();
            return false;
        }
        if (datePicker.getEditor().getText().isEmpty()) {
            warning.setContentText("please enter date of birth in (dd/mm/yyyy) format");
            warning.showAndWait();
            return false;
        }
        if (!(male.isSelected() | female.isSelected())) {
            warning.setContentText("please choose gender");
            warning.showAndWait();
            return false;
        }
        return true;
    }

    public void clearField() {
        id.clear();
        fn.clear();
        ln.clear();
        em.clear();
        un.clear();
        pw.clear();
        ((TextField) datePicker.getEditor()).setText(null);
        male.setSelected(false);
        female.setSelected(false);
//        datePicker.setValue(null);
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
