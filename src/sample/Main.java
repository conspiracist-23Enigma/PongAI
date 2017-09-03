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

    // testing push

    double stepped = 5.0;

    Line line1 = new Line();
    Line line2 = new Line();

    double startx1 = 5.0;
    double endx1 = 5.0;
    double starty1 = 390;
    double endy1 = 510;


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
                        starty1 -= 20.0;
                        endy1 -= 20.0;
                        line1.setStartY(starty1);
                        line1.setEndY(endy1);
                    }

                }else if (event.getCode() == KeyCode.J) {
                    if (starty1 >= 0 ) {
                        starty1 -= 20.0;
                        endy1 -= 20.0;
                        line1.setStartY(starty1);
                        line1.setEndY(endy1);
                    }else {
                        starty1 += 20.0;
                        endy1 += 20.0;
                        line1.setStartY(starty1);
                        line1.setEndY(endy1);
                    }

                }

        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception{


        line1.setStartX(startx1);
        line1.setStartY(starty1);
        line1.setEndX(endx1);
        line1.setEndY(endy1);
        line1.setStrokeWidth(10.0);
        line1.setStroke(Color.WHITE);


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
