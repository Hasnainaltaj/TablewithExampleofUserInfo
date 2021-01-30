package Tutorial12;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Tutorial13 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 8 Tutorial 13 - List View");
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, 500, 450);

        ListView listView = new ListView();
        ObservableList<String> data = FXCollections.observableArrayList("red", "black", "green", "yellow", "pink", "blue", "violet", "brown", "silver","white","orange");
        listView.setItems(data);
        listView.setCellFactory(e -> {
            return new ColorName();
        });

        ListView listView1 = new ListView();
        ObservableList<String> data1 = FXCollections.observableArrayList("red", "black", "green", "yellow", "pink", "blue", "violet", "brown", "silver","white","orange");
        listView1.setItems(data1);
        listView1.setCellFactory(e -> {
            return new ColorName();
        });
        // To set Orientation
        listView1.setOrientation(Orientation.HORIZONTAL);
        //To set Multiple selection at the same time
        listView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Label label = new Label();
        label.setLayoutX(10);
        label.setLayoutY(100);
        label.setFont(Font.font("Arial", 20));


        listView.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            label.setText((String) nv);
            label.setTextFill(Color.web((String) nv));
        });
        listView1.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
            label.setText((String) nv);
            label.setTextFill(Color.web((String) nv));
        });
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(listView,listView1);

        vBox.getChildren().addAll(hBox, label);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    static class ColorName extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rectangle = new Rectangle(100, 20);
            if (item != null) {
                rectangle.setFill(Color.web(item));
                setGraphic(rectangle);
            } else {
                setGraphic(null);
            }
        }
    }
}
