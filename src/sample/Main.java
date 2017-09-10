package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.java2d.pipe.SolidTextRenderer;




public class Main extends Application {

    // Scene Nodes
    Group root = new Group();

    // window size
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 600;

    // Score
    private int P1Score = 0;
    private int P2Score = 0;
    private Label P1Text = new Label(Integer.toString(P1Score));
    private Label P2Text = new Label(Integer.toString(P2Score));
    private HBox scoreBox = new HBox(50);


    // rectangle paddles
    private Rectangle paddle1 = new Rectangle(0, WINDOW_HEIGHT / 3, 10,120);
    private Rectangle paddle2 = new Rectangle(WINDOW_WIDTH - 10, WINDOW_HEIGHT / 3, 10,120);


    // Ball position X and Y
    private double ballX = WINDOW_HEIGHT / 2;
    private double ballY = WINDOW_WIDTH / 2;
    private Circle ball = new Circle(ballX, ballY, 10, Color.WHITE);
    private double move = 5.0;

    // private void coreGame(){
    //
    // }

    private void setUpHandlers(Scene scene) {
        /* create handlers for key press and release events */
        scene.setOnKeyPressed(event -> {

                if (event.getCode() == KeyCode.K) {
                    if (paddle1.getY() < WINDOW_HEIGHT - 120) {
                        paddle1.setY(paddle1.getY() + WINDOW_HEIGHT/ 30);
                    }

                }else if (event.getCode() == KeyCode.J) {
                    if (paddle1.getY() != 0 ) {
                        paddle1.setY(paddle1.getY() - WINDOW_HEIGHT/ 30);
                    }

                }

        });

    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        Line mid = new Line(WINDOW_WIDTH / 2 , 0, WINDOW_WIDTH/2, WINDOW_HEIGHT);
        mid.setFill(Color.WHITE);
        mid.setStrokeWidth(10);

        paddle1.setFill(Color.WHITE);
        paddle2.setFill(Color.WHITE);
        P1Text.setTextFill(Color.WHITE);
        P2Text.setTextFill(Color.WHITE);
        P1Text.setFont(new Font("Arial", 20));
        P2Text.setFont(new Font("Arial", 20));
        scoreBox.setLayoutY(WINDOW_HEIGHT - 50);
        scoreBox.setLayoutX(WINDOW_WIDTH / 2 - 25);

        scoreBox.getChildren().addAll(P1Text, P2Text);


        root.getChildren().addAll(paddle1, paddle2, ball, scoreBox, mid);
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.setFill(Color.BLACK);


        primaryStage.setTitle("Pong AI");
        primaryStage.setScene(scene);
        setUpHandlers(scene);


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15),
                ae -> {
                        // if (ball.getCenterX() < 0 -> player 2 gets a point

                        // if (ball.getCenterX() > WINDOW_WIDTH -> player 1 gets the point

                        // if ( 0 < ball.getCenterY() < WINDOW_HEIGHT - 10) -> ball.setCenterY(ball.setCenterY * (-1))

                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
