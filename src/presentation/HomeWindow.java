package presentation;

import data.model.Role;
import data.model.User;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeWindow implements UIFrame, Initialization {
    private JPanel panel;
    private JButton checkoutBookButton;
    private JButton addMemberButton;
    private JButton searchMemberButton;
    private JButton addCopyButton;
    private JButton searchBookButton;
    private JButton addBookButton;

    public HomeWindow() {
        initializeUI();
        checkoutBookButton.addActionListener(e -> RootFrame.getInstance().showPanel(RootFrame.CHECKOUT_BOOK_FRAME));
    }

    private void initializeUI() {
        checkoutBookButton.setVisible(false);
        searchMemberButton.setVisible(false);
        searchBookButton.setVisible(false);
        addMemberButton.setVisible(false);
        addCopyButton.setVisible(false);
        addBookButton.setVisible(false);
    }

    //    void initializeController() {
//        addMemberButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                RootFrame.getInstance().showPanel();
//            }
//        });
//    }
    public void authorizeFunction() {
        User user = LoggedInUser.get();
        if (user != null) {
            if (user.getAuthorizations().contains(Role.LIBRARIAN)) {
                checkoutBookButton.setVisible(true);
                searchMemberButton.setVisible(true);
                searchBookButton.setVisible(true);
            }
            if (user.getAuthorizations().contains(Role.ADMIN)) {
                addBookButton.setVisible(true);
                addMemberButton.setVisible(true);
                addCopyButton.setVisible(true);
            }
        }
    }

    @Override
    public void run() {
        authorizeFunction();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}