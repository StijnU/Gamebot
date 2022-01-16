public class InnerLayer extends Layer{
    private Layer lastLayer;
    public int nodeAmount;


    public InnerLayer(int nodeAmount, int nextLayerNodeAmount, Layer lastLayer) {
        super(new Node[nodeAmount]);
        this.nodeAmount = nodeAmount;
        this.lastLayer = lastLayer;

        for (int i = 0; i < nodeAmount; i++){
            this.nodes[i] = new Node(nextLayerNodeAmount, this.lastLayer.nodes, i);
        }
    }
}
