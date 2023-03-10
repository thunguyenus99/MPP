import presentation.RootFrame;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(RootFrame::getInstance);
    }
}