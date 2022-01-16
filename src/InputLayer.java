public class InputLayer extends Layer{
    public int nodeAmount;


    public InputLayer(int nodeAmount, int nextLayerNodeAmount) {
        super(new Node[nodeAmount]);
        this.nodeAmount = nodeAmount;

        for (int i = 0; i < nodeAmount; i++){
            this.nodes[i] = new Node(nextLayerNodeAmount, null, i);
        }
    }

    public void setInput(int[] inputs){
        for (int i = 0; i < inputs.length; i++){
            this.nodes[i].value = inputs[i];
        }
    }
}
