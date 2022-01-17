import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

public class GamePanel<Destroy> extends JPanel implements KeyListener {
    static final int SCREEN_WIDTH = 550;
    static final int SCREEN_HEIGHT =700;
    static final int DELAY = 20; //75
    int currentLevel = 1; //Test level 0
    int blockDestroy;
    int lives = 3; //the life count starts from 1
//    Timer timer;
//    Random random;

    ArrayList<Block> blocks = new ArrayList<Block>();
    ArrayList<UserLive> userLives = new ArrayList<UserLive>();
    Block ball = new Block(SCREEN_WIDTH /2 - 12, SCREEN_HEIGHT - 115, 25, 25, "ball.png");
    Block paddle= new Block(SCREEN_WIDTH /2 - 50, SCREEN_HEIGHT - 90, 100, 28, "paddle.png");
    Thread thread;
//     random = new Random();


    GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        addKeyListener(this);
        this.setFocusable(true);
        newGame();
    }

    public void newGame(){
        setGamePosition();
        setUserLives();
        levelBuilder();
    }

    public  void  nextLevel(){
        setGamePosition();
        levelBuilder();
    }

    public void levelBuilder() {

        int w_block = 50;
        int h_block = 25;
        blocks.clear();

        if(currentLevel == 0 ) { //test level
            for (int i = 0; i < 2; i++) {
                blocks.add(new Block(300+i* w_block, 80, w_block, h_block, "block_2.png"));
            }
        }

        if(currentLevel == 1 ) {
            for (int i = 0; i < 11; i++) {
                blocks.add(new Block(0 + (i * w_block), 20 + h_block, w_block, h_block, "block_1.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 2, w_block, h_block, "block_2.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 3, w_block, h_block, "block_3.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 4, w_block, h_block, "block_4.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 5, w_block, h_block, "block_5.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 6, w_block, h_block, "block_8.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 7, w_block, h_block, "block_9.png"));
            }
        }
        if(currentLevel == 2 ) {
            for (int i = 0; i < 10; i++)
                blocks.add(new Block(w_block * 0 , 20 + h_block * i, w_block, h_block, "block_1.png"));
            for (int i = 0; i < 9; i++)
                blocks.add(new Block(w_block * 1 , 20 + h_block * 1 +  h_block * i, w_block, h_block, "block_2.png"));
            for (int i = 0; i < 8; i++)
                blocks.add(new Block(w_block * 2 , 20 + h_block * 2 +  h_block * i, w_block, h_block, "block_3.png"));
            for (int i = 0; i < 7; i++)
                blocks.add(new Block(w_block * 3 , 20 + h_block * 3 +  h_block * i, w_block, h_block, "block_4.png"));
            for (int i = 0; i < 6; i++)
                blocks.add(new Block(w_block * 4 , 20 + h_block * 4 +  h_block * i, w_block, h_block, "block_5.png"));
            for (int i = 0; i < 5; i++)
                blocks.add(new Block(w_block * 5 , 20 + h_block * 5 +  h_block * i, w_block, h_block, "block_6.png"));
            for (int i = 0; i < 4; i++)
                blocks.add(new Block(w_block * 6 , 20 + h_block * 6 +  h_block * i, w_block, h_block, "block_7.png"));
            for (int i = 0; i < 3; i++)
                blocks.add(new Block(w_block * 7 , 20 + h_block * 7 +  h_block * i, w_block, h_block, "block_8.png"));
            for (int i = 0; i < 2; i++)
                blocks.add(new Block(w_block * 8 , 20 + h_block * 8 +  h_block * i, w_block, h_block, "block_9.png"));
            for (int i = 0; i < 1; i++)
                blocks.add(new Block(w_block * 9 , 20 + h_block * 9 +  h_block * i, w_block, h_block, "block_10.png"));
           for (int i = 0; i < 10; i++)
                blocks.add(new Block(0 + (i * w_block), 20+ h_block * 9 + h_block, w_block, h_block, "block_20.png"));
            blocks.add(new Block(0 + (10 * w_block) , 20 + h_block * 9 +  h_block, w_block, h_block, "block_14.png"));
        }
        if(currentLevel == 3 ) {
            for (int i = 0; i < 11; i++) {
                blocks.add(new Block(0 + (i * w_block), 20 + h_block, w_block, h_block, "block_1.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 3, w_block, h_block, "block_2.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 5, w_block, h_block, "block_3.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 7, w_block, h_block, "block_4.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 9, w_block, h_block, "block_5.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 11, w_block, h_block, "block_8.png"));
                blocks.add(new Block(0 + (i * w_block), 20 + h_block * 13, w_block, h_block, "block_9.png"));
            }
       }

        blockDestroy = 0;
    }

    public void setUserLives() {

        for(int i = 0; i < lives; i++)
            userLives.add(new UserLive(20 + (i * 70), 670, 60, 17, "paddleLive.png"));
      }

    public void setGamePosition(){

        ball.y = SCREEN_HEIGHT - 115;
        ball.x = SCREEN_WIDTH /2 - 12;

        paddle.y = SCREEN_HEIGHT - 90;
        paddle.x = SCREEN_WIDTH /2 - 50;
    }

    public void paintComponent(Graphics g){

       super.paintComponent(g);

       blocks.forEach(block -> {
            block.draw(g, this);
        });
        paddle.draw(g, this);
        ball.draw(g, this);

        userLives.forEach(userLive -> {
            userLive.draw(g, this);
        });
    }

    public void ballMotion(){
        //bounce from the side walls
        if(ball.x > (getWidth() -25) || ball.x < 0) {
            ball.movX *= -1;
        }

        //bounce from top side and paddle
        if(ball.intersects(paddle)) {
            ball.movY *= -1;
//            System.out.println("ball centre " + ball.getCenterX() + " paddle centre " + paddle.getCenterX());
//            System.out.println(paddle.getWidth());
            //left bounce
            if(ball.getCenterX() < (paddle.getCenterX() - paddle.getWidth() / 4)){
//                ball.movX = ball.movX + 2;
            }
            //right left
            if(ball.getCenterX() > (paddle.getCenterX() + paddle.getWidth() / 4)){
//                ball.movX = ball.movX - 2;
            }
        }

        if(ball.y < 0 ) {
            ball.movY *= -1;
        }
        ball.x += ball.movX;
        ball.y += ball.movY;
    }

    public void update() {

        blocks.forEach(block -> {
            //bounce from blocks and destroys them
            if(ball.intersects(block) && !block.destroyed) {
                ball.movY *= -1;
                block.destroyed = true;
                blockDestroy += 1;
            }
        });

        //check if any blocks left
            if(blocks.size() == blockDestroy){
                System.out.println("Won the game, move to next level");
                currentLevel += 1; //set next level
                nextLevel();
                thread.stop();
            }

        //check if the ball is lost
        if(ball.y >= SCREEN_HEIGHT) {
            //check how many lives left and reduce by one
            if (lives > 0) {
                lives -= 1;
                userLives.remove(lives);
                System.out.println(lives); //test
                setGamePosition();
                    //check if non lives left
                    if(lives == 0) {
                        System.out.println("Game Over");//test
                    }
            }
        }

        repaint();
    }

    public void run(){
        thread =  new Thread(() -> {
            while(lives > 0){
                ballMotion();
                update();
                try {
                    Thread.sleep(DELAY );
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            run();
            thread.start();
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth() - paddle.width)){
            paddle.x +=15;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0){
            paddle.x -=15;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
