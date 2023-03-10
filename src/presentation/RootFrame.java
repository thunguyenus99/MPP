package presentation;

import presentation.addauthor.AddAuthorUiPlugin;
import presentation.addbook.AddBookUiPlugin;
import presentation.addbook.AddBookWindow;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RootFrame extends JFrame {

    private static RootFrame INSTANCE;

    public static final String LOGIN_WINDOW = "LOGIN_WINDOW";

    public static final String HOME_WINDOW = "HOME_WINDOW";

    public static final String CHECKOUT_BOOK_WINDOW = "CHECKOUT_BOOK_WINDOW";

    public static final String ADD_COPY_WINDOW = "ADD_COPY_WINDOW";

    public static final String ADD_MEMBER_WINDOW = "ADD_MEMBER_WINDOW";

    public static final String ADD_BOOK_WINDOW = "ADD_BOOK_WINDOW";

    public static final String ADD_AUTHOR_WINDOW = "ADD_AUTHOR_WINDOW";

    public static final String PRINT_CHECKOUT_RECORDS_WINDOW = "PRINT_CHECKOUT_RECORDS_WINDOW";

    public static final String SEARCH_BOOK_WINDOW = "SEARCH_BOOK_WINDOW";

    private final Map<String, UIFrame> uiFrameMap = new HashMap<>();

    private LinkedList<String> frameStack;

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
        setVisible(true);

        UiPlugin addBookUiPlugin = new AddBookUiPlugin();
        plugUi(addBookUiPlugin);
        plugUi(new AddAuthorUiPlugin((AddBookWindow) addBookUiPlugin.getUiFrame()));

        frameStack = new LinkedList<>();

        uiFrameMap.put(LOGIN_WINDOW, new LoginWindow());
        uiFrameMap.put(HOME_WINDOW, new HomeWindow());
        uiFrameMap.put(ADD_MEMBER_WINDOW, new AddMemberWindow());
        uiFrameMap.put(ADD_COPY_WINDOW, new AddCopyWindow());
        uiFrameMap.put(CHECKOUT_BOOK_WINDOW, new CheckoutBookWindow());
        uiFrameMap.put(ADD_AUTHOR_WINDOW, ((AddBookWindow) uiFrameMap.get(ADD_BOOK_WINDOW)).getAddAuthorWindow());
        uiFrameMap.put(PRINT_CHECKOUT_RECORDS_WINDOW, new PrintCheckOutRecords());
        uiFrameMap.put(SEARCH_BOOK_WINDOW, new SearchBookWindow());

        add(LOGIN_WINDOW, uiFrameMap.get(LOGIN_WINDOW).getRoot());
        add(HOME_WINDOW, uiFrameMap.get(HOME_WINDOW).getRoot());
        add(ADD_MEMBER_WINDOW, uiFrameMap.get(ADD_MEMBER_WINDOW).getRoot());
        add(ADD_COPY_WINDOW, uiFrameMap.get(ADD_COPY_WINDOW).getRoot());
        add(CHECKOUT_BOOK_WINDOW, uiFrameMap.get(CHECKOUT_BOOK_WINDOW).getRoot());
        add(ADD_BOOK_WINDOW, uiFrameMap.get(ADD_BOOK_WINDOW).getRoot());
        add(ADD_AUTHOR_WINDOW, uiFrameMap.get(ADD_AUTHOR_WINDOW).getRoot());
        add(PRINT_CHECKOUT_RECORDS_WINDOW, uiFrameMap.get(PRINT_CHECKOUT_RECORDS_WINDOW).getRoot());
        add(SEARCH_BOOK_WINDOW, uiFrameMap.get(SEARCH_BOOK_WINDOW).getRoot());
        addPanel(LOGIN_WINDOW, true);
    }

    public void addPanel(String panelName, boolean runInit) {
        frameStack.push(panelName);
        showPanel(panelName, runInit);
    }

    public void removePanel(boolean runInit) {
        if (frameStack.size() <= 1) {
            throw new RuntimeException();
        }
        frameStack.pop();
        showPanel(frameStack.peek(), runInit);
    }

    public String getNavigationLink() {
        StringBuilder link = new StringBuilder();
        for (int i = frameStack.size() - 1; i >= 0; i--) {
            if (frameStack.get(i).equals(LOGIN_WINDOW)) {
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
