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
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 550;

    private double stepped = 5.0;

    private Line line1 = new Line();
    private Line line2 = new Line();

    private double startx1 = 5.0;
    private double endx1 = 5.0;
    private double starty1 = 390;
    private double endy1 = 510;


    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {

                if (event.getCode() == KeyCode.K) {
                    if ( endy1 <= 900 ) {
                        starty1 += 20.0;
                        endy1 += 20.0;
                        line1.setStartY(starty1);
                        line1.setEndY(endy1);
                    }else {
//                        starty1 -= 20.0;
//                        endy1 -= 20.0;
//                        line1.setStartY(starty1);
//                        line1.setEndY(endy1);
                    }

                }else if (event.getCode() == KeyCode.J) {
                    if (starty1 >= 0 ) {
                        starty1 -= 20.0;
                        endy1 -= 20.0;
                        line1.setStartY(starty1);
                        line1.setEndY(endy1);
                    }else {
//                        starty1 += 20.0;
//                        endy1 += 20.0;
//                        line1.setStartY(starty1);
//                        line1.setEndY(endy1);
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
        line2.setStartX(1195.0);
        line2.setStartY(390);
        line2.setEndX(1195.0);
        line2.setEndY(510);
        line2.setStrokeWidth(10.0);
        line2.setStroke(Color.WHITE);


        Group root = new Group(line1,line2);
        Scene scene = new Scene(root, 1200, 900);
        scene.setFill(Color.BLACK);

        primaryStage.setTitle("Pong AI");
        primaryStage.setScene(scene);
        setUpHandlers(scene);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                ae -> {
                        if ( line2.getStartY() > 10 && line2.getEndY() <890){
                                line2.setStartY(line2.getStartY() + stepped);
                                line2.setEndY(line2.getEndY() + stepped);

                        }else {
                            stepped *= -1;
                            line2.setStartY(line2.getStartY() + stepped);
                            line2.setEndY(line2.getEndY() + stepped);
                        }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
