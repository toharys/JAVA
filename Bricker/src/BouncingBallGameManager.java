import danogl.GameManager;
import danogl.GameObject;
import danogl.collisions.Layer;
import danogl.gui.*;
import danogl.gui.rendering.RectangleRenderable;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import gameobjects.Ball;

import java.awt.*;

public class BouncingBallGameManager extends GameManager{
    private static final Vector2 WINDOW_DIMENSIONS = new Vector2(700,500);
    private static final Vector2 BALL_DIMENSIONS = new Vector2(50,50);
    private static final Vector2 PADDLE_DIMENSIONS = new Vector2(200,20);
    private static final int BALL_VELOCITY_FACTOR = 300;
    public static final int WALL_WIDTH = 10;

    public BouncingBallGameManager(String windowTitle, Vector2 windowDimension) {
        super(windowTitle,windowDimension);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        Vector2 windowDimensions = windowController.getWindowDimensions();
        // create background
        createBackground(imageReader, windowDimensions);
        // create border walls (and define their locations)
        createWalls(windowDimensions);
        // create ball (and define its location and velocity)
        createBall(imageReader, soundReader,windowDimensions);
        // create paddles (and define their locations)
        createPaddles(imageReader, windowDimensions);
    }

    private void createBackground(ImageReader imageReader, Vector2 windowDimensions) {
        Renderable backgroundImage = imageReader.readImage("assets/DARK_BG2_small.jpeg",true);
        GameObject background = new GameObject(Vector2.ZERO,new Vector2(windowDimensions.x(),
                windowDimensions.y()),backgroundImage);
//        background.setCoordinateSpace(CoordinateSpace.CAMERA_COORDINATES);
        background.setCenter(windowDimensions.mult(0.5F));
        // display the background on the screen
        this.gameObjects().addGameObject(background,Layer.BACKGROUND);
    }
    private void createWalls(Vector2 windowDimensions) {
        float[] wallsXWidth = {WALL_WIDTH, WALL_WIDTH, windowDimensions.x()};
        float[] wallsYWidth = {windowDimensions.y(), windowDimensions.y(), WALL_WIDTH};
        float[] wallsXCenter = {0, windowDimensions.x(), windowDimensions.x()/2};
        float[] wallsYCenter = {windowDimensions.y()/2, windowDimensions.y()/2, 0};
        GameObject[] walls = new GameObject[3];
        for (int i = 0; i < walls.length; i++) {
            walls[i]= new GameObject(Vector2.ZERO, new Vector2(wallsXWidth[i], wallsYWidth[i]),null);
            walls[i].setCenter(new Vector2(wallsXCenter[i], wallsYCenter[i]));
            // add the wall to the screen
            this.gameObjects().addGameObject(walls[i], Layer.STATIC_OBJECTS);
        }
    }
    private void createBall(ImageReader imageReader,SoundReader soundReader, Vector2 windowDimensions) {
        Renderable ballImage = imageReader.readImage("assets/ball.png",true);
        Sound collisionSound = soundReader.readSound("assets/blop_cut_silenced.wav") ;
        Ball ball = new Ball(Vector2.ZERO, BALL_DIMENSIONS,ballImage,collisionSound);
        ball.setCenter(windowDimensions.mult(0.5F));
        ball.setVelocity(Vector2.DOWN.mult(BALL_VELOCITY_FACTOR));
        // display the ball on the screen
        this.gameObjects().addGameObject(ball);
    }
    private void createPaddles(ImageReader imageReader, Vector2 windowDimensions) {
        float[] paddlesHeights = {windowDimensions.y()-PADDLE_DIMENSIONS.y()/2
                ,0+PADDLE_DIMENSIONS.y()/2};
        GameObject[] paddles = new GameObject[2];
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        for (int i = 0; i < paddles.length; i++) {
            paddles[i] = new GameObject(Vector2.ZERO, PADDLE_DIMENSIONS, paddleImage);
            paddles[i].setCenter(new Vector2(windowDimensions.x() / 2, paddlesHeights[i]));
            // display the paddle on the screen
            this.gameObjects().addGameObject(paddles[i]);
        }
    }

    public static void main(String[] args) {
        new BouncingBallGameManager("BouncingBall", WINDOW_DIMENSIONS).run();
    }
}
