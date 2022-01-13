import java.awt.*;

public class Obstacle extends GameObject {
    public Obstacle(int x, int y, int velX, int velY, Type id, int width, int height) {
        super(x, y, velX, velY, id, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

    @Override
    public void tick() {
        this.y += velY;
    }
}
