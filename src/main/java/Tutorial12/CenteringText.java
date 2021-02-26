package Tutorial12;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CenteringText extends Application {


    //http://www-acad.sheridanc.on.ca/~jollymor/prog24178/javafx_mvc1.html
    //https://download.code-projects.org/details/59ea954e-e3c2-4296-a16f-852af8fbdbcd
    //https://openjfx.io/
    //https://www.swtestacademy.com/database-operations-javafx/
    //https://www.chegg.com/homework-help/questions-and-answers/sample-javafx-app-jdbc-file-operations-help-employee-id-new-email-name-surname-email-name--q32702649#question-transcript
    //https://www.codersarts.com/post/employee-management-system-using-javafx
    //https://medium.com/@keeptoo/javafx-java-modern-ui-design-starter-pack-aab1c331fd3c

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX 8 Centering Text in Scene");
        Group root = new Group();
        Scene scene = new Scene(root, 400, 200);

        Text text = new Text("JavaFX 8 Centering Text in Scene");
        text.setTextOrigin(VPos.TOP);
        text.setFont(Font.font(null, FontWeight.BOLD,15));

        text.layoutXProperty().bind(scene.widthProperty().subtract(text.prefWidth(-1)).divide(2));
        text.layoutYProperty().bind(scene.heightProperty().subtract(text.prefHeight(-1)).divide(1));
        root.getChildren().add(text);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
