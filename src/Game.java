import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = -1442798787354930462L;
    public static final int WIDTH = 640, HEIGHT = 1000;

    private Thread thread;
    private boolean running = false;
    public Handler handler;

    private Bot bot = null;

    public Game(Bot bot){
        this.bot = bot;
        handler = new Handler(HEIGHT, WIDTH);

        if (bot == null) {
            this.addKeyListener(new KeyInput(handler));
        }
        handler.addObject(new Player((WIDTH - 17 - 20) / 2, HEIGHT - 40 - 50, 0, 0,50, 20));
        new Window(WIDTH, HEIGHT, "Game 1.0", this);
    }

    public synchronized  void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        if (bot != null) {
            long lastTime = System.nanoTime();
            double amountOfTicks = 60.0;
            double ns = 1_000_000_000 / amountOfTicks;
            double delta = 0;

            while (running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                while (delta >= 1) {
                    tick();
                    bot.predictMove(handler);
                    delta--;
                }
                if (running)
                    render();
            }
        }
        else{
            long lastTime = System.nanoTime();
            double amountOfTicks = 60.0;
            double ns = 1_000_000_000 / amountOfTicks;
            double delta = 0;

            while (running) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                while (delta >= 1) {
                    tick();
                    delta--;
                }
                if (running)
                    render();
            }
        }
    }

    private void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return ;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }

    public static void main(String args[]){
        new Game(null);
    }
}
