package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    // window size
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 600;

    // right paddle animation
    private double stepped = 5.0;

    // paddles (could be replaced by rectangle?)
    private Line line1 = new Line();
    private Line line2 = new Line();


    // starting position of the left paddle
    private double startx1 = WINDOW_WIDTH / 100;
    private double starty1 = (WINDOW_HEIGHT / 2) - 60;

    private double endx1 = WINDOW_WIDTH / 100;
    private double endy1 = (WINDOW_HEIGHT / 2) + 60;

    // Ball position X and Y
    private double ballX = WINDOW_HEIGHT / 2;
    private double ballY = WINDOW_WIDTH / 2;

    // private void coreGame(){}

    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {

                if (event.getCode() == KeyCode.K) {
                    if ( endy1 < WINDOW_HEIGHT) {
                        starty1 += WINDOW_HEIGHT / 30;
                        endy1 += WINDOW_HEIGHT / 30;
                        line1.setStartY(starty1);
                        line1.setEndY(endy1);
                    }


                }else if (event.getCode() == KeyCode.J) {
                    if (starty1 >= 0 ) {
                        starty1 -= WINDOW_HEIGHT / 30;
                        endy1 -= WINDOW_HEIGHT / 30;
                        line1.setStartY(starty1);
                        line1.setEndY(endy1);
                    }

                }

        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        /* draw the left bat*/
        line1.setStartX(startx1);
        line1.setStartY(starty1);
        line1.setEndX(endx1);
        line1.setEndY(endy1);
        line1.setStrokeWidth(10.0);
        line1.setStroke(Color.WHITE);

        /* draw the right bat*/
        line2.setStartX(WINDOW_WIDTH - (WINDOW_WIDTH / 100));
        line2.setStartY(WINDOW_HEIGHT / 2 - 60);

        line2.setEndX(WINDOW_WIDTH - (WINDOW_WIDTH / 100));
        line2.setEndY(WINDOW_HEIGHT / 2 + 60);

        line2.setStrokeWidth(10.0);
        line2.setStroke(Color.WHITE);


        Group root = new Group(line1,line2);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setFill(Color.BLACK);

        primaryStage.setTitle("Pong AI");
        primaryStage.setScene(scene);
        setUpHandlers(scene);


//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
//                ae -> {
//                        if ( line2.getStartY() > 10 && line2.getEndY() <890){
//                                line2.setStartY(line2.getStartY() + stepped);
//                                line2.setEndY(line2.getEndY() + stepped);
//
//                        }else {
//                            stepped *= -1;
//                            line2.setStartY(line2.getStartY() + stepped);
//                            line2.setEndY(line2.getEndY() + stepped);
//                        }
//                }));
//        timeline.setCycleCount(Animation.INDEFINITE);
//        timeline.play();

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
