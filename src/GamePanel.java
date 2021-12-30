import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {
    static final int SCREEN_WIDTH = 660;
    static final int SCREEN_HEIGHT =700;
    static final int DELAY = 75;
    Image bgImage = Toolkit.getDefaultToolkit().createImage("ball.png");
    int currentLevel = 1;
    int userLives = 3;
//    Timer timer;
//    Random random;
//    Thread thread;

    ArrayList<Block> blocks = new ArrayList<Block>();
    Block ball = new Block(SCREEN_WIDTH /2 - 12, SCREEN_HEIGHT - 120, 25, 25, "ball.png");
    Block paddle= new Block(SCREEN_WIDTH /2 - 50, SCREEN_HEIGHT - 90, 100, 28, "paddle.png");
    Block paddleLive= new Block(20, SCREEN_HEIGHT - 35, 60, 17, "paddleLive.png");
    
    GamePanel(){
//        random = new Random();
//        thread = new Thread();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
//        this.setBackground(bgImage);
        addKeyListener(this);
        this.setFocusable(true);
        levelBuilder();
    }

    public void levelBuilder() {

        for(int i = 0; i < 11; i++)
            blocks.add(new Block(0+(i*60),50, 64, 32, "block_1.png"));
        for(int i = 0; i < 11; i++)
            blocks.add(new Block(0+(i*60),82, 64, 32, "block_3.png"));
        for(int i = 0; i < 11; i++)
            blocks.add(new Block(0+(i*60),114, 64, 32, "block_2.png"));
        for(int i = 0; i < 11; i++)
            blocks.add(new Block(0+(i*60),146, 64, 32, "block_5.png"));
        for(int i = 0; i < 11; i++)
            blocks.add(new Block(0+(i*60),178, 64, 32, "block_4.png"));
        for(int i = 0; i < 11; i++)
            blocks.add(new Block(0+(i*60),210, 64, 32, "block_6.png"));
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        g.drawImage(bgImage,0,0,this);

        blocks.forEach(block -> {
            block.draw(g, this);
        });
        paddle.draw(g, this);
        ball.draw(g, this);
        paddleLive.draw(g, this);
    }



    public void update() {

        //bounce from the side walls
        if(ball.x >(getWidth() -25) || ball.x < 0) {
            ball.movX *= -1;
        }

       //bounce from top side and paddle
        if(ball.y < 0 || ball.intersects(paddle)) {
            ball.movY *= -1;
        }

        ball.x += ball.movX;
        ball.y += ball.movY;

        //bounce from blocks and destroyes them
        blocks.forEach(block -> {
            if(ball.intersects(block) && block.destroyed == false) {
                block.destroyed = true;
                ball.movY *= -1;
            }
//            if(block.destroyed){
//                System.out.println("Next Level");
//            }
        });

        if(ball.y> SCREEN_HEIGHT){
            System.out.println("Game Over");
        }

        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            new Thread(() -> {
                while(true){
                    update();
                    try {
                        Thread.sleep(DELAY );
                    } catch (InterruptedException err) {
                        err.printStackTrace();
                    }
                }
            }).start();
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
