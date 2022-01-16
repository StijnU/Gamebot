import java.util.Random;

public abstract class Layer {
    public Node[] nodes;
    public Layer(Node[] nodes){
        this.nodes = nodes;
    }

    public double[] getValues(){
        double[] values = new double[this.nodes.length];
        for (int i = 0; i < this.nodes.length; i++){
            values[i] = this.nodes[i].calcValue();
        }
        return values;
    }

    public void setWeights(double[][] weights){
        for (int i = 0; i < this.nodes.length; i++){
            this.nodes[i].setWeights(weights[i]);
        }
    }

    public void setRandomWeights(double lowerLimit, double higherLimit){
        Random rd = new Random();
        double[] weights = new double[this.nodes[0].weights.length];
        for (Node node : this.nodes) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] = lowerLimit + (higherLimit - lowerLimit) * rd.nextDouble();
            }
            node.setWeights(weights);
        }
    }

    public String toString(){
        String totalString = "";

        for (int i = 0; i < this.nodes.length; i++){
            totalString += this.nodes[i].toString() + ";";
        }
        return totalString;
    }
}
