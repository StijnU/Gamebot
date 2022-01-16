import java.awt.*;

public class Player extends GameObject{

    public Player(int x, int y, int velX, int velY, int height, int width) {
        super(x, y, velX, velY, Type.PLAYER, height, width);

    }


    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void tick() {
        this.x += velX;
    }
}
