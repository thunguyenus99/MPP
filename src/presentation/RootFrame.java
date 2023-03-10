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

    public static final String ADD_COPY_FRAME = "ADD_COPY_FRAME";

    public static final String ADD_MEMBER_WINDOW = "ADD_MEMBER_WINDOW";

    public static  final String PRINT_CHECKOUT_RECORDS_WINDOW = "PRINT_CHECKOUT_RECORDS_WINDOW";

    public static Map<String, UIFrame> uiFrameMap;

    {
        uiFrameMap = new HashMap<>();
        uiFrameMap.put(LOGIN_FRAME, new LoginWindow());
        uiFrameMap.put(HOME_FRAME, new HomeWindow());
        uiFrameMap.put(ADD_MEMBER_WINDOW, new AddMemberWindow());
        uiFrameMap.put(ADD_COPY_FRAME, new AddCopyWindow());
        uiFrameMap.put(CHECKOUT_BOOK_FRAME, new CheckoutBookWindow());

        uiFrameMap.put(PRINT_CHECKOUT_RECORDS_WINDOW, new PrintCheckOutRecords());
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
        add(ADD_MEMBER_WINDOW, uiFrameMap.get(ADD_MEMBER_WINDOW).getPanel());
        add(ADD_COPY_FRAME, uiFrameMap.get(ADD_COPY_FRAME).getPanel());
        add(CHECKOUT_BOOK_FRAME, uiFrameMap.get(CHECKOUT_BOOK_FRAME).getPanel());
        add(PRINT_CHECKOUT_RECORDS_WINDOW, uiFrameMap.get(PRINT_CHECKOUT_RECORDS_WINDOW).getPanel());
        showPanel(LOGIN_FRAME);
    }

    public void showPanel(String panelName) {
        Initialization frame = (Initialization) uiFrameMap.get(panelName);
        frame.run();
        ((CardLayout) getContentPane().getLayout()).show(this.getContentPane(), panelName);
    }
}
