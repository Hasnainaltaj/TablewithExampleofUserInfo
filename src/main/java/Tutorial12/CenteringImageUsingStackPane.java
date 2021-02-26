package Tutorial12;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CenteringImageUsingStackPane extends Application {

    private final ObservableList<PieChart.Data> details = FXCollections.observableArrayList();
    private BorderPane root;
    private PieChart pieChart;
    private Label label;

    public void start(Stage primaryStage) {

        primaryStage.setTitle("JavaFX 8 Tutorial 56 - Pie Chart");

        details.addAll(new PieChart.Data("Printing cost", 20),
                new PieChart.Data("Paper cost", 25),
                new PieChart.Data("Binding cost", 30),
                new PieChart.Data("Promotion cost", 10),
                new PieChart.Data("Transportation cost", 10),
                new PieChart.Data("Royalty cost", 15));

/**Stream function introduce in java 8*/
        root = new BorderPane();
        Scene scene = new Scene(root, 600, 500);
        pieChart = new PieChart();
        pieChart.setData(details);
        pieChart.setTitle("Various Expenditures (in %) Incurred in Publishing A Book");
        //pieChart.setLegendSide(Side.BOTTOM);
        //pieChart.setLabelsVisible(true);
        //pieChart.setClockwise(false);
        // pieChart.setLabelLineLength(12);
        //pieChart.setStartAngle(90);
        root.setCenter(pieChart);
        label = new Label();
        label.setFont(Font.font("SanSerif", FontWeight.BOLD, 15));
        pieChart.getData().stream().forEach(data -> {
            data.getNode().addEventHandler(MouseEvent.ANY, e -> {
                label.setText(data.getName() + " Expenditures: " + (int)data.getPieValue() + "%\n" + "Central Angle: " + (int)((data.getPieValue() / 100) * 360) + " degree.");
            });
        });

        root.setBottom(label);
        BorderPane.setMargin(label, new Insets(0, 0, 10, 120));
        scene.getStylesheets().add(getClass().getResource("../Style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();


        /**Centering Image Using StackPane
         *
         */
        /**
         primaryStage.setTitle("Centering Image Using StackPane");
         StackPane root = new StackPane();
         Scene scene = new Scene(root, 400, 200);

         Image img = new Image("file:C:\\Users\\lenovo\\Downloads\\javafx-tasks-master\\TablewithExampleofUserInfo1\\src\\main\\resources\\r.jpg", 100, 150, true, true);
         ImageView iv = new ImageView(img);

         Ellipse ellipse = new Ellipse();
         ellipse.setFill(Color.MEDIUMAQUAMARINE);
         ellipse.radiusXProperty().bind(scene.widthProperty().divide(3));
         ellipse.radiusYProperty().bind(scene.heightProperty().divide(3));

         root.getChildren().addAll(ellipse, iv);


         //root.getChildren().add(iv);
         primaryStage.setScene(scene);
         primaryStage.show();
         */


    }
}
