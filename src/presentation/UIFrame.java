package presentation;

import javax.swing.*;

public interface UIFrame {
    JPanel getRoot();

    void run();

    void updateNavigationLink();
}
