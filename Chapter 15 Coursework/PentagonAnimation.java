import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PentagonAnimation extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Polygon pentagon = new Polygon();
        pentagon.getPoints().addAll(
            200.0, 50.0, 
            340.0, 150.0,
            290.0, 310.0,
            110.0, 310.0,
            60.0, 150.0 
        );
        pentagon.setFill(Color.TRANSPARENT);
        pentagon.setStroke(Color.DARKSLATEGRAY);
        pentagon.setStrokeWidth(2);

        Rectangle rectangle = new Rectangle(30, 15);
        rectangle.setFill(Color.RED);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(5));
        pathTransition.setPath(pentagon);
        pathTransition.setNode(rectangle);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(PathTransition.INDEFINITE);

        Timeline opacityTimeline = new Timeline(
            new KeyFrame(Duration.ZERO, new KeyValue(rectangle.opacityProperty(), 1.0)),
            new KeyFrame(Duration.seconds(2.5), new KeyValue(rectangle.opacityProperty(), 0.1)),
            new KeyFrame(Duration.seconds(5.0), new KeyValue(rectangle.opacityProperty(), 1.0))
        );
        opacityTimeline.setCycleCount(Timeline.INDEFINITE);

        pane.getChildren().addAll(pentagon, rectangle);

        pane.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                pathTransition.play();
                opacityTimeline.play();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                pathTransition.pause();
                opacityTimeline.pause();
            }
        });

        pathTransition.play();
        opacityTimeline.play();

        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("Pentagon Animation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
