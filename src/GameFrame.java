import javax.swing.JFrame;

public class GameFrame extends JFrame {

    GameFrame(){

        GamePanel panel = new GamePanel();

        this.setTitle("Arkanoid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
