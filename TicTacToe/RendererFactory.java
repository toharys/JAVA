/** a class that builds renderer objects **/
public class RendererFactory {
    /** method that receives requested renderer type, call his constructor and returns it**/
    public Renderer buildRenderer(String type, int size){
        Renderer newRenderer;
        switch(type){
            case "console":
                newRenderer = new ConsoleRenderer(size);
                break;
            case "none":
                newRenderer = new VoidRenderer();
                break;
            default: // if the requested type isn't in the list define it as a null
                newRenderer = null;
        }
        return newRenderer;
    }
}
