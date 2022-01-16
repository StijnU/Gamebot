import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class NeuralNet {
    private InputLayer input;
    private InnerLayer[] inners;
    private OutputLayer output;
    private int layerAmount;

    public NeuralNet(int inputAmount, int outputAmount, int[] innerLayerAmounts){
        this.layerAmount = 2 + innerLayerAmounts.length;

        this.inners = new InnerLayer[innerLayerAmounts.length];

        this.input = new InputLayer(inputAmount, innerLayerAmounts[0]);

        if (innerLayerAmounts.length > 1) {

            this.inners[0] = new InnerLayer(innerLayerAmounts[0], innerLayerAmounts[1], this.input);

            for (int layerNb = 1; layerNb < innerLayerAmounts.length - 1; layerNb++) {
                this.inners[layerNb] = new InnerLayer(innerLayerAmounts[layerNb], innerLayerAmounts[layerNb + 1], this.inners[layerNb - 1]);
            }

            this.inners[innerLayerAmounts.length - 1] =
                    new InnerLayer(innerLayerAmounts[innerLayerAmounts.length - 1], outputAmount, this.inners[innerLayerAmounts.length - 2]);
        }
        else {
            this.inners[innerLayerAmounts.length - 1] = new InnerLayer(innerLayerAmounts[0], outputAmount, this.input);
        }

        this.output = new OutputLayer(outputAmount, this.inners[this.inners.length - 1]);
    }

    public double[] predict(int[] input){
        if (input.length != this.input.nodes.length) {
            System.out.println("Wrong input size");
        }
        this.input.setInput(input);
        for (InnerLayer layer : this.inners){
            layer.getValues();
        }
        return this.output.getValues();
    }

    public void setWeights(double[][][] weights){
        this.input.setWeights(weights[0]);
        for (int i = 0; i < this.inners.length; i++){
            this.inners[i].setWeights(weights[i + 1]);
        }
    }

    public void setRandomWeights(double lowerLimit, double higherLimit){
        this.input.setRandomWeights(lowerLimit, higherLimit);
        for (InnerLayer layer : this.inners){
            layer.setRandomWeights(lowerLimit, higherLimit);
        }
    }

    public void save(String name){
        try {
            FileWriter myWriter = new FileWriter(name + ".nnet");
            myWriter.write(this.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void read(String name){
        try {
            File myObj = new File(name + ".nnet");
            Scanner myReader = new Scanner(myObj);
            double[][][] weights = new double[this.layerAmount][][];
            for (int k = 0; k < this.layerAmount - 1; k++) {
                String data = myReader.nextLine();
                String[] parsedNodes = data.split(";");
                double[][] layerWeights = new double[parsedNodes.length][];
                for (int j = 0; j < parsedNodes.length; j++) {
                    String[] parsedNodeWeights = parsedNodes[j].split(",");
                    double[] nodeWeights = new double[parsedNodeWeights.length];
                    for (int i = 0; i < parsedNodeWeights.length; i++){
                        nodeWeights[i] = Double.parseDouble(parsedNodeWeights[i]);
                    }
                    layerWeights[j] = nodeWeights;
                }
                weights[k] = layerWeights;
            }
            myReader.close();
            this.setWeights(weights);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String toString(){
        String totalString = "";

        totalString += this.input.toString() + "\n";

        for (InnerLayer layer : this.inners){
            totalString += layer.toString() + "\n";
        }

        totalString += this.output.toString() + "\n";
        return totalString;
    }

    public static void main(String args[]) throws Exception {
        int[] nodeAmounts = {2};
        NeuralNet model = new NeuralNet(2, 2, nodeAmounts);
        double[][][] weights = {
                // input layer
                {{1,2},{2,1}},
                // inner layer
                {{1,1},{1,1}},
        };
        int[] input = {1,5};
        model.setWeights(weights);
        model.predict(input);
        model.save("model1");
        model.read("model1");
        model.save("model2");
    }


}
