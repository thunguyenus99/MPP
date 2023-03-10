package presentation;

public abstract class UiPlugin {
    private final String name;
    private final UIFrame uiFrame;

    public UiPlugin(String name, UIFrame uiFrame) {
        this.name = name;
        this.uiFrame = uiFrame;
    }

    public String getName() {
        return name;
    }

    public UIFrame getUiFrame() {
        return uiFrame;
    }
}
