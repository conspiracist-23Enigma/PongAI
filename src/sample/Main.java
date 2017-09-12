package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.security.Key;
import java.util.concurrent.ThreadLocalRandom;


public class Main extends Application {

    // Scene Nodes
    private Group root = new Group();

    // window size
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 600;

    // Score
    // TODO cant update text score with this set up
    private int P1Score = 0;
    private int P2Score = 0;
    private Label P1Text = new Label(Integer.toString(P1Score));
    private Label P2Text = new Label(Integer.toString(P2Score));
    private HBox scoreBox = new HBox(50);


    // rectangle paddles
    private Rectangle paddle1 = new Rectangle(0, WINDOW_HEIGHT / 2 - 60, 10,120);
    private Rectangle paddle2 = new Rectangle(WINDOW_WIDTH - 10, WINDOW_HEIGHT / 2 - 60 , 10,120);

    private boolean UP_ARROWKEY = false;
    private boolean DOWN_ARROWKEY = false;
    private boolean UP_J = false;
    private boolean DOWN_K = false;


    // Ball position X and Y
    private Circle ball = new Circle(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, 10, Color.WHITE);
    private double Static_moveX = 5.0;
    private double Static_moveY = 5.0;
    private double moveX = 5.0;
    private double moveY = 5.0;


    private int SIDE_TO_BE_PLAYED = 0;
    private int GAMES_PLAYED;
    private boolean BALL_IN_PLAY = false;

    // private void coreGame(){
    //
    // }

    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {

            switch (event.getCode()){
                case UP: UP_ARROWKEY     = true; break;
                case DOWN: DOWN_ARROWKEY = true; break;
                case J: UP_J             = true; break;
                case K: DOWN_K           = true; break;
                case SPACE: {
                    SIDE_TO_BE_PLAYED = ThreadLocalRandom.current().nextInt(0,2);
                    if (SIDE_TO_BE_PLAYED == 0){
                        moveX = Static_moveX;
                        if (ThreadLocalRandom.current().nextInt(0,2) == 0){
                            moveY = Static_moveY;
                        }else moveY = -Static_moveY;
                    }else {
                        moveX = -Static_moveX;
                        if (ThreadLocalRandom.current().nextInt(0,2) == 0){
                            moveY = Static_moveY;
                        }else moveY = -Static_moveY;
                    }
                    BALL_IN_PLAY = true;
                }
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()){
                case UP: UP_ARROWKEY     = false; break;
                case DOWN: DOWN_ARROWKEY = false; break;
                case J: UP_J             = false; break;
                case K: DOWN_K           = false; break;
            }

        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Line mid = new Line(WINDOW_WIDTH / 2 , 0, WINDOW_WIDTH/2, WINDOW_HEIGHT);
        mid.setStroke(Color.WHITE);
        mid.setStrokeWidth(2);

        paddle1.setFill(Color.WHITE);
        paddle2.setFill(Color.WHITE);
        P1Text.setTextFill(Color.WHITE);
        P2Text.setTextFill(Color.WHITE);
        P1Text.setFont(new Font("Arial", 20));
        P2Text.setFont(new Font("Arial", 20));
        scoreBox.setLayoutY(WINDOW_HEIGHT - 50);
        scoreBox.setLayoutX(WINDOW_WIDTH / 2 - 38.5);

        scoreBox.getChildren().addAll(P1Text, P2Text);


        root.getChildren().addAll(paddle1, paddle2, ball, scoreBox, mid);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setFill(Color.BLACK);


        primaryStage.setTitle("Pong AI");
        primaryStage.setScene(scene);
        setUpHandlers(scene);



        primaryStage.show();


            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15),
                    ae -> {

                        if (DOWN_K) {
                            if (paddle1.getY() < WINDOW_HEIGHT - 120) {
                                paddle1.setY(paddle1.getY() + WINDOW_HEIGHT / 50);
                            }

                        }
                        if (UP_J) {
                            if (paddle1.getY() != 0) {
                                paddle1.setY(paddle1.getY() - WINDOW_HEIGHT / 50);
                            }

                        }

                        if (DOWN_ARROWKEY) {
                            if (paddle2.getY() < WINDOW_HEIGHT - 120) {
                                paddle2.setY(paddle2.getY() + WINDOW_HEIGHT / 50);
                            }

                        }
                        if (UP_ARROWKEY) {
                            if (paddle2.getY() != 0) {
                                paddle2.setY(paddle2.getY() - WINDOW_HEIGHT / 50);
                            }

                        }


                        if (BALL_IN_PLAY){
                            if (ball.getCenterX() < 0) {
                                P2Score += 1;
                                P2Text.setText(P2Score + "");
                                ball.setCenterX(WINDOW_WIDTH/2 + 5 );
                                ball.setCenterY(WINDOW_HEIGHT/2 +   5 );
                                BALL_IN_PLAY = false;

                            }else if (ball.getCenterX() > WINDOW_WIDTH){
                                P1Score += 1;
                                P1Text.setText(P1Score + "");
                                ball.setCenterX(WINDOW_WIDTH/2 - 5 );
                                ball.setCenterY(WINDOW_HEIGHT/2- 5 );
                                BALL_IN_PLAY = false;

                            }

                            // if (ball.getCenterX() > WINDOW_WIDTH) -> player 1 gets the point

                            // when the ball hits the bat, return it as a fraction of the move depending on which part
                            // of the bat it hit. also have to change the direction of the X and Y components seperately.

                            ball.setCenterX(ball.getCenterX() + moveX);


                        }

                    }));

            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();




    }


    public static void main(String[] args) {
        launch(args);


    }
}
