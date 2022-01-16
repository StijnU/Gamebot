public class Node {
    double[] weights;
    Node[] inputs;
    double value;
    int id;

    public Node(int weightAmount, Node[] inputs, int id){
        this.weights = new double[weightAmount];
        this.inputs = inputs;
        this.id = id;
    }

    public double[] getOutputs(int input){
        double[] outputs = new double[this.weights.length];
        int i = 0;

        for (double weight : this.weights){
            outputs[i] = weight * input;
            i ++;
        }
        return outputs;
    }

    public double calcValue(){
        this.value = 0;

        for (Node input: inputs){
            this.value += input.value * input.weights[id];
        }

        if (this.value < 0){
            this.value = 0;
        }
        return this.value;
    }

    public void setWeights(double[] weights){
        this.weights = weights;
    }

    public String toString(){
        String totalString = "";
        if (this.weights.length > 1) {
            for (int i = 0; i < this.weights.length - 1; i++) {
                totalString += this.weights[i] + ",";
            }
            totalString += this.weights[this.weights.length - 1];
        }

        return totalString;
    }
}
