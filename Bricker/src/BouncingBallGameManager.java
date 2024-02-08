import danogl.GameManager;
import danogl.GameObject;
import danogl.gui.ImageReader;
import danogl.gui.SoundReader;
import danogl.gui.UserInputListener;
import danogl.gui.WindowController;
import danogl.gui.rendering.Renderable;
import danogl.util.Vector2;
import gameobjects.Ball;

public class BouncingBallGameManager extends GameManager{
    private static final Vector2 WINDOW_DIMENSIONS = new Vector2(700,500);
    private static final Vector2 BALL_DIMENSIONS = new Vector2(50,50);
    private static final Vector2 PADDLE_DIMENSIONS = new Vector2(200,20);
    public BouncingBallGameManager(String windowTitle, Vector2 windowDimension) {
        super(windowTitle,windowDimension);
    }

    @Override
    public void initializeGame(ImageReader imageReader, SoundReader soundReader,
                               UserInputListener inputListener, WindowController windowController) {
        super.initializeGame(imageReader, soundReader, inputListener, windowController);
        // create ball (and define its location and velocity)
        Renderable ballImage = imageReader.readImage("assets/ball.png",true);
        Ball ball = new Ball(Vector2.ZERO, BALL_DIMENSIONS,ballImage);
        Vector2 windowDimensions = windowController.getWindowDimensions();
        ball.setCenter(windowDimensions.mult(0.5F));
        ball.setVelocity(Vector2.DOWN.mult(100));
        // display the ball on the screen
        this.gameObjects().addGameObject(ball);

        // create paddles (and define their locations)
        float[] paddlesHeights = {windowDimensions.y()-PADDLE_DIMENSIONS.y()/2
                ,0+PADDLE_DIMENSIONS.y()/2};
        GameObject[] paddles = new GameObject[2];
        Renderable paddleImage = imageReader.readImage("assets/paddle.png", true);
        for (int i = 0; i < paddles.length; i++) {
            paddles[i] = new GameObject(Vector2.ZERO,PADDLE_DIMENSIONS,paddleImage);
            paddles[i].setCenter(new Vector2(windowDimensions.x()/2, paddlesHeights[i]));
            // display the paddle on the screen
            this.gameObjects().addGameObject(paddles[i]);
        }


    }

    public static void main(String[] args) {
        new BouncingBallGameManager("BouncingBall", WINDOW_DIMENSIONS).run();
    }
}
