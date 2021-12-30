import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Block extends Rectangle {

    Image pic;
    boolean destroyed;

    int movX, movY;

    Block(int x, int y, int w, int h, String s){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

        movX = 3;
        movY = 3;

        try {
            pic = ImageIO.read(new File("src/img/" + s));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void  draw(Graphics g, Component c){

        if(!destroyed) {
            g.drawImage(pic, x, y, width, height, c);
        }
    }
}
