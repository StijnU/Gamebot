import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Handler {

    LinkedList<GameObject> objects;
    private int mapHeight;
    private int mapWidth;
    private int score = 0;
    public boolean died = false;

    int nbOfTicks = 0;
    int nbTicksSpeedUpdate = 1000;
    int nbTicksSpawnUpdate = 500;
    int nbTickSpawn = 50;
    int baseSpeed = 10;

    Random random = new Random();

    public Handler (int mapHeight, int mapWidth) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        objects = new LinkedList<GameObject>();
    }

    public void tick(){
        GameObject player = null;
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.tick();
            if (tempObject.getY() + tempObject.getHeight() + 40> mapHeight){
                removeObject(tempObject);
                score += 1;
            }

            if (tempObject.getType() == Type.PLAYER){
                player = tempObject;
                // check if player is out of map
                if (player.getX() > mapWidth - player.getWidth()){
                    player.setX(0);
                }

                if (player.getX() < 0){
                    player.setX(mapWidth - player.getWidth());
                }
            }
        }

        if (nbOfTicks % nbTickSpawn == 0) {
            objects.add(new Obstacle(random.nextInt((mapWidth - 17 - 20)),
                    -150, 0, baseSpeed + (nbOfTicks / nbTicksSpeedUpdate),
                    Type.OBSTACLE, 50, 100));
        }

        if (nbOfTicks % nbTicksSpawnUpdate == 0){
            nbTickSpawn -= 5;
        }
        nbOfTicks++;

        try{
            if (collided(player, objects)) {
                died = true;
            }
        }catch (NullPointerException e){
            System.out.println("No Player instantiated");
        }
    }

    public void render(Graphics g) {
        if (died) {
            for (int i = 0; i < objects.size(); i++) {
                GameObject tempObject = objects.get(i);

                if (tempObject.getType() == Type.OBSTACLE) {
                    objects.remove(tempObject);
                }
            }
        // black screen with score
            Font font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Score: " + Integer.toString(score), mapWidth / 2 - 80, mapHeight / 2);
        }
        else {
            for (int i = 0; i < objects.size(); i++) {
                GameObject tempObject = objects.get(i);

                tempObject.render(g);
            }

            Font font = new Font(Font.MONOSPACED, Font.PLAIN, 30);
            g.setFont(font);
            g.setColor(Color.BLACK);
            g.drawString("Score: " + Integer.toString(score), mapWidth / 2 - 80, 35);
        }

    }

    public void addObject(GameObject object){
        this.objects.add(object);
    }

    public void removeObject(GameObject object){
        this.objects.remove(object);
    }

    private boolean collided(GameObject player, LinkedList<GameObject> objects){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);

            if (tempObject.getType() == Type.OBSTACLE) {

                if (tempObject.getY() + tempObject.getHeight() > player.getY()) {
                    if ((tempObject.getX() + tempObject.getWidth()) > player.getX() && tempObject.getX() < player.getX() + player.getWidth()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void restart(){
        if (died){
            died = false;
            score = 0;
            nbOfTicks = 0;
            nbTickSpawn = 50;
        }
    }

    public void moveLeft(){
        for(int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            if (tempObject.getType() == Type.PLAYER)
                tempObject.setVelX(-5);
        }
    }

    public void moveRight(){
        for(int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            if (tempObject.getType() == Type.PLAYER)
                tempObject.setVelX(5);
        }
    }

    public void stopMove(){
        for(int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);

            if (tempObject.getType() == Type.PLAYER)
                tempObject.setVelX(0);
        }
    }
}
