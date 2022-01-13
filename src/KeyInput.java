import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput (Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();


        if (key == KeyEvent.VK_LEFT) {
            handler.moveLeft();
        }

        if (key == KeyEvent.VK_RIGHT) {
            handler.moveRight();
        }
        if (key == KeyEvent.VK_ENTER){
            handler.restart();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            handler.stopMove();
        }
        if (key == KeyEvent.VK_RIGHT) {
            handler.stopMove();
        }
    }
}
