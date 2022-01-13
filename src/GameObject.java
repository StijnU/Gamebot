import java.awt.*;

public abstract class GameObject {

    protected  int x, y;
    protected Type type;
    protected int velX, velY, width, height;

    public GameObject(int x, int y, int velX, int velY, Type id, int height, int width){
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
        this.type = id;
        this.width = width;
        this.height = height;
    }

    public abstract void render(Graphics g);
    public abstract void tick();

    public int getX(){return this.x;}
    public void setX(int x){this.x = x;}

    public int getY(){return this.y;}
    public void setY(int y){this.y = y;}

    public Type getType(){return this.type;}
    public void setType(Type type){this.type = type;}

    public int getVelX(){return this.velX;}
    public void setVelX(int velX){this.velX = velX;}

    public int getVelY(){return this.velY;}
    public void setVelY(int velY){this.velY = velY;}

    public int getHeight(){return this.height;}
    public int getWidth(){return this.width;}

}
