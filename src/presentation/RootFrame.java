package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RootFrame extends JFrame {

    private static RootFrame INSTANCE;

    public static final String LOGIN_FRAME = "LOGIN_FRAME";

    public static final String HOME_FRAME = "HOME_FRAME";
    public static final String CHECKOUT_BOOK_FRAME = "CHECKOUT_BOOK_FRAME";

    public static Map<String, UIFrame> uiFrameMap;

    {
        uiFrameMap = new HashMap<>();
        uiFrameMap.put(LOGIN_FRAME, new LoginWindow());
        uiFrameMap.put(HOME_FRAME, new HomeWindow());
        uiFrameMap.put(CHECKOUT_BOOK_FRAME, new CheckoutBookWindow());
    }

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
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new CardLayout());
        setVisible(true);

        add(LOGIN_FRAME, uiFrameMap.get(LOGIN_FRAME).getPanel());
        add(HOME_FRAME, uiFrameMap.get(HOME_FRAME).getPanel());
        add(CHECKOUT_BOOK_FRAME, uiFrameMap.get(CHECKOUT_BOOK_FRAME).getPanel());
        showPanel(LOGIN_FRAME);
    }

    public void showPanel(String panelName) {
        Initialization frame = (Initialization) uiFrameMap.get(panelName);
        frame.run();
        ((CardLayout) getContentPane().getLayout()).show(this.getContentPane(), panelName);
    }
}
