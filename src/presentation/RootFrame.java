package presentation;

import presentation.addauthor.AddAuthorUiPlugin;
import presentation.addbook.AddBookUiPlugin;
import presentation.addbook.AddBookWindow;
import presentation.addcopy.AddCopyUiPlugin;
import presentation.addmember.AddMemberUiPlugin;
import presentation.checkoutbook.CheckBookUiPlugin;
import presentation.home.HomeUiPlugin;
import presentation.login.LoginUiPlugin;
import presentation.printcheckoutrecords.PrintCheckoutRecordsUiPlugin;
import presentation.searchbook.SearchBookUiPlugin;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RootFrame extends JFrame {

    private static RootFrame INSTANCE;

    private final Map<String, UIFrame> uiFrameMap = new HashMap<>();

    private Stack<String> frameStack;

    private RootFrame() {
        initialize();
    }

    public static RootFrame getInstance() {
        if (RootFrame.INSTANCE == null) {
            RootFrame.INSTANCE = new RootFrame();
        }
        return RootFrame.INSTANCE;
    }

    private void initialize() {
        setTitle("AURORA GROUP");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        UiUtils.centerFrameOnDesktop(this);
        setVisible(true);

        plugUi(new LoginUiPlugin());
        plugUi(new HomeUiPlugin());
        plugUi(new AddMemberUiPlugin());
        plugUi(new AddCopyUiPlugin());
        plugUi(new CheckBookUiPlugin());
        plugUi(new PrintCheckoutRecordsUiPlugin());
        plugUi(new SearchBookUiPlugin());
        UiPlugin addBookUiPlugin = new AddBookUiPlugin();
        plugUi(addBookUiPlugin);
        plugUi(new AddAuthorUiPlugin((AddBookWindow) addBookUiPlugin.getUiFrame()));
        frameStack = new Stack<>();
        addPanel(LoginUiPlugin.NAME, true);
    }

    public void addPanel(String panelName, boolean runInit) {
        frameStack.push(panelName);
        showPanel(panelName, runInit);
    }

    public void removePanel(boolean runInit) {
        if (frameStack.isEmpty()) {
            throw new RuntimeException();
        }
        frameStack.pop();
        showPanel(frameStack.peek(), runInit);
    }

    public String getNavigationLink() {
        StringBuilder link = new StringBuilder();
        for (int i = frameStack.size() - 1; i >= 0; i--) {
            if (frameStack.get(i).equals(LoginUiPlugin.NAME)) {
                continue;
            }

            StringBuilder panelName = new StringBuilder();
            String[] split = frameStack.get(i).split("_");
            for (String str : split) {
                if (str.equals("WINDOW")) {
                    continue;
                }
                panelName.append(str).append(" ");
            }

            link.append(panelName.substring(0, panelName.length() - 1)).append(" -> ");
        }
        return link.substring(0, link.length() - 4);
    }

    private void showPanel(String panelName, boolean runInit) {
        UIFrame frame = uiFrameMap.get(panelName);
        if (runInit) {
            frame.run();
        }
        frame.updateNavigationLink();
        ((CardLayout) getContentPane().getLayout()).show(this.getContentPane(), panelName);
    }

    public void plugUi(UiPlugin uiPlugin) {
        uiFrameMap.put(uiPlugin.getName(), uiPlugin.getUiFrame());
        add(uiPlugin.getName(), uiPlugin.getUiFrame().getRoot());
    }
}
