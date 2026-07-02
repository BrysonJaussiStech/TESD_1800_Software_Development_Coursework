import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CountdownStopwatch extends Application {

    private int timeRemaining = 0;
    private Timeline timeline;
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        TextField txtTime = new TextField();
        txtTime.setAlignment(Pos.CENTER);
        txtTime.setFont(Font.font("Times New Roman", 60));
        txtTime.setPrefWidth(200);

        try {
            String musicUrl = "https://liveexample.pearsoncmg.com/common/audio/anthem/anthem0.mp3";
            Media media = new Media(musicUrl);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        } catch (Exception e) {
            System.out.println("Audio source error. Make sure your URL is valid.");
        }

        txtTime.setOnAction(e -> {
            try {
                if (timeline != null) timeline.stop();
                if (mediaPlayer != null) mediaPlayer.stop();

                timeRemaining = Integer.parseInt(txtTime.getText());

                timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                    timeRemaining--;
                    txtTime.setText(String.valueOf(timeRemaining));

                    if (timeRemaining <= 0) {
                        timeline.stop();
                        if (mediaPlayer != null) {
                            mediaPlayer.play();
                        }
                    }
                }));

                timeline.setCycleCount(timeRemaining);
                timeline.play();

            } catch (NumberFormatException ex) {
                txtTime.setText("Invalid Input");
            }
        });

        StackPane root = new StackPane(txtTime);
        Scene scene = new Scene(root, 250, 150);

        primaryStage.setTitle("Countdown Stopwatch");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}