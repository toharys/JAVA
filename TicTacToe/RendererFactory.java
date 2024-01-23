public class RendererFactory {
    public Renderer buildRenderer(String type, int size){
        Renderer newRenderer;
        type = type.toLowerCase();
        switch(type){
            case "console":
                newRenderer = new ConsoleRenderer(size);
                break;
            case "none":
                newRenderer = new VoidRenderer();
                break;
            default:
                newRenderer = null;
        }
        return newRenderer;
    }
}
