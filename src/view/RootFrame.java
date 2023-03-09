package view;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class RootFrame extends JFrame {

    private static RootFrame INSTANCE;

    public static final String LOGIN_PANEL = "LOGIN_PANEL";

    public static final String HOME_PANEL = "HOME_PANEL";

    public static final String ADD_MEMBER_PANEL = "ADD_MEMBER_PANEL";

    public static Map<String, JPanel> panelMap;

    {
        panelMap = new HashMap<>();
        panelMap.put(LOGIN_PANEL, new LoginPanel());
        panelMap.put(HOME_PANEL, new HomePanel());
//        panelMap.put(ADD_MEMBER_PANEL, new AddMemberPanel());
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

        add(LOGIN_PANEL, panelMap.get(LOGIN_PANEL));
        add(HOME_PANEL, panelMap.get(HOME_PANEL));
        showPanel(LOGIN_PANEL);
    }

    public void showPanel(String panelName) {
        Initialization panel = (Initialization) panelMap.get(panelName);
        panel.run();
        ((CardLayout)getContentPane().getLayout()).show(this.getContentPane(), panelName);
    }
}
