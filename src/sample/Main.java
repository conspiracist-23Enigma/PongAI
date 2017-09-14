package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;


public class Main extends Application {

    // Scene Nodes
    private Group root = new Group();

    // window size
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 600;

    // rectangle paddles
    private static Rectangle paddle1 = new Rectangle(0, WINDOW_HEIGHT / 2 - 60, 10, 120);
    private static Rectangle paddle2 = new Rectangle(WINDOW_WIDTH - 10, WINDOW_HEIGHT / 2 - 60, 10, 120);
    private double paddleMove = WINDOW_HEIGHT / 50;

    // Ball position X and Y
    private static Circle ball = new Circle(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, 10, Color.WHITE);
    private static double moveX = 5.0;
    private static double moveY = 5.0;

    // Score
    private static int P1Score = 0;
    private static int P2Score = 0;
    private Label P1Text = new Label(Integer.toString(P1Score));
    private Label P2Text = new Label(Integer.toString(P2Score));
    private HBox scoreBox = new HBox(50);

    //Key Event Buffers
    private boolean UP_ARROWKEY = false;
    private boolean DOWN_ARROWKEY = false;
    private boolean UP_J = false;
    private boolean DOWN_K = false;

    //Ball Movement
    private double Static_moveX = 5.0;
    private double Static_moveY = 5.0;

    //Game State
    private int SIDE_TO_BE_PLAYED = 0;
    private int GAMES_PLAYED;
    private static boolean BALL_IN_PLAY = false;

    // private void coreGame(){
    //
    // }

    private static void ball_physics(Label p1Text, Label p2Text) {
        if (ball.getCenterX() + moveX < 10 &&
                ball.getCenterY() + moveY >= paddle1.getY() && ball.getCenterY() + moveY <= paddle1.getY() + 120) {

            ball.setCenterY(ball.getCenterY() + moveY);
            ball.setCenterX(paddle1.getX() + 20); // x is from left side and ball is on right, so length of paddle and radius
            moveX *= (-1.1);
            moveY = delflectionAngle(moveX, moveY, paddle1);

        } else if (ball.getCenterX() + moveX > WINDOW_WIDTH - 10 &&
                ball.getCenterY() + moveY >= paddle2.getY() && ball.getCenterY() + moveY <= paddle2.getY() + 120) {

            ball.setCenterX(paddle2.getX() - 10); // x is on left side and ball is on left as well, so only radius
            moveX *= (-1.05);
            moveY = delflectionAngle(moveX, moveY, paddle2);

        } else if (ball.getCenterX() + moveX < 10) {
            ball.setCenterY(ball.getCenterY() + moveY);
            ball.setCenterX(5);
            P2Score = scored(p2Text, P2Score);

        } else if (ball.getCenterX() + moveX > WINDOW_WIDTH - 10) {
            ball.setCenterY(ball.getCenterY() + moveY);
            ball.setCenterX(WINDOW_WIDTH - 5);
            P1Score = scored(p1Text, P1Score);

        } else {

            ball.setCenterY(ball.getCenterY() + moveY);
            ball.setCenterX(ball.getCenterX() + moveX);
        }
    }

    private static int scored(Label pText, int pScore) {
        BALL_IN_PLAY = false;
        pScore += 1;
        pText.setText(pScore + "");
        ball.setCenterX(WINDOW_WIDTH / 2);
        ball.setCenterY(WINDOW_HEIGHT / 2);

        //return The score to be set to the global variable
        return pScore;
    }

    private static double delflectionAngle(double new_moveX, double old_moveY, Rectangle paddle){

        new_moveX = Math.abs(new_moveX);
        double paddle_Y = paddle.getY();
        double partition = paddle.getHeight() / 7;
        double radian_conversion = Math.PI / 180;

        if (ball.getCenterY() <= paddle_Y + partition ){
            return -(new_moveX / (Math.tan(30 * radian_conversion)));
        } else if (ball.getCenterY() >= paddle_Y + 3*partition && ball.getCenterY() <= paddle_Y + 4*partition){
            return 0;
        }else if (ball.getCenterY() >= paddle_Y + 6*partition){
            return new_moveX / (Math.tan(30 * radian_conversion));
        }
        else return old_moveY;
    }

    public static void main(String[] args) {
        launch(args);


    }

    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {

            switch (event.getCode()) {
                case UP:
                    UP_ARROWKEY = true;
                    break;
                case DOWN:
                    DOWN_ARROWKEY = true;
                    break;
                case J:
                    UP_J = true;
                    break;
                case K:
                    DOWN_K = true;
                    break;
                case SPACE: {

                    if (!BALL_IN_PLAY) {
                        SIDE_TO_BE_PLAYED = ThreadLocalRandom.current().nextInt(0, 2);
                        if (SIDE_TO_BE_PLAYED == 0) {
                            moveX = Static_moveX;
                            if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
                                moveY = Static_moveY;
                            } else moveY = -Static_moveY;
                        } else {
                            moveX = -Static_moveX;
                            if (ThreadLocalRandom.current().nextInt(0, 2) == 0) {
                                moveY = Static_moveY;
                            } else moveY = -Static_moveY;
                        }
                        BALL_IN_PLAY = true;
                    }

                }
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:
                    UP_ARROWKEY = false; break;
                case DOWN:
                    DOWN_ARROWKEY = false; break;
                case J:
                    UP_J = false; break;
                case K:
                    DOWN_K = false; break;
            }

        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Line mid = new Line(WINDOW_WIDTH / 2, 0, WINDOW_WIDTH / 2, WINDOW_HEIGHT);
        mid.setStroke(Color.WHITE);
        mid.setStrokeWidth(2);
        mid.setOpacity(0.75);
        mid.getStrokeDashArray().addAll(15d, 15d);

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


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15),
                ae -> {

                    if (DOWN_K) {
                        if (paddle1.getY() < WINDOW_HEIGHT - paddle1.getHeight()) {
                            paddle1.setY(paddle1.getY() + paddleMove);
                        }

                    }
                    if (UP_J) {
                        if (paddle1.getY() > 0) {
                            paddle1.setY(paddle1.getY() - paddleMove);
                        }

                    }

                    if (DOWN_ARROWKEY) {
                        if (paddle2.getY() < WINDOW_HEIGHT - paddle2.getHeight()) {
                            paddle2.setY(paddle2.getY() + paddleMove);
                        }

                    }
                    if (UP_ARROWKEY) {
                        if (paddle2.getY() > 0) {
                            paddle2.setY(paddle2.getY() - paddleMove);
                        }

                    }

                    //Checks if the flag for starting the round has been set
                    if (BALL_IN_PLAY) {

                        //The conditionals below define the wall reflection physics
                        if (10 < ball.getCenterY() && ball.getCenterY() < WINDOW_HEIGHT - 10) {
                            ball_physics(P1Text, P2Text);

                        } else {
                            moveY *= (-1);
                            ball_physics(P1Text, P2Text);

                        }
                    }

                }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        primaryStage.show();


    }
}