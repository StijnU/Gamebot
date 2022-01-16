public class Bot {
    // inputs = Nodes
    private NeuralNet model;
    public int nbRuns = 0;

    public Bot(NeuralNet model){
        this.model = model;
    }

    public void play(){
        new Game(this);
    }

    public void predictMove(Handler handler){
        for (int i = handler.objects.size() - 1; i >= 0; i++){
            GameObject tempObject = handler.objects.get(i);
            if (tempObject.getType() == Type.OBSTACLE){
                int xInput = tempObject.getX();
                int yInput = tempObject.getY();
                int[] input = {xInput, yInput};
                double[] output = this.model.predict(input);
                double max = 0.0;
                int index = 0;

                for (int j = 0; j < output.length; j++){
                    if (output[j] > max){
                        max = output[j];
                        index = j;
                    }
                }

                if (max < 0.7){
                    handler.stopMove();
                    return;
                }

                switch(index){
                    case 0:
                        handler.moveLeft();
                        return;
                    default:
                        handler.moveRight();;
                        return;
                }
            }
        }
    }


    public static void main(String args[]){
        int[] innerLayerAmounts = {4};
        NeuralNet model = new NeuralNet(2, 2, innerLayerAmounts);
        model.setRandomWeights(0.0, 0.5);
        Bot bot = new Bot(model);
        bot.play();
    }

}
