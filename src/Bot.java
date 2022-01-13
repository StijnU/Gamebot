public class Bot {
    // inputs = Nodes

    public void play(){
        new Game(this);
    }

    public void predictMove(Handler handler){
        handler.moveLeft();
    }

    public static void main(String args[]){
        Bot bot = new Bot();
        bot.play();
    }

}
