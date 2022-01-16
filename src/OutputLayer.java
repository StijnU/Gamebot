public class OutputLayer extends Layer{
    private Layer lastLayer;
    public int nodeAmount;


    public OutputLayer(int nodeAmount, Layer lastLayer) {
        super(new Node[nodeAmount]);
        this.nodeAmount = nodeAmount;
        this.lastLayer = lastLayer;

        for (int i = 0; i < nodeAmount; i++){
            this.nodes[i] = new Node(0, this.lastLayer.nodes, i);
        }
    }
}
