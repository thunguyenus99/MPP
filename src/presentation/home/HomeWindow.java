package presentation.home;

import data.model.Role;
import data.model.User;
import presentation.LoggedInUser;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.addbook.AddBookUiPlugin;
import presentation.addcopy.AddCopyUiPlugin;
import presentation.addmember.AddMemberUiPlugin;
import presentation.checkoutbook.CheckBookUiPlugin;
import presentation.printcheckoutrecords.PrintCheckoutRecordsUiPlugin;
import presentation.searchbook.SearchBookUiPlugin;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class HomeWindow implements UIFrame {
    private JPanel panel;
    private JButton checkoutBookButton;
    private JButton addMemberButton;
    private JButton printCheckOutRecordsButton;
    private JButton addCopyButton;
    private JButton searchBookButton;
    private JButton addBookButton;

    public HomeWindow() {
        setUpListener();
    }

    void setUpListener() {
        addCopyButton.addActionListener(e -> RootFrame.getInstance().addPanel(AddCopyUiPlugin.NAME, true));
        checkoutBookButton.addActionListener(e -> RootFrame.getInstance().addPanel(CheckBookUiPlugin.NAME, true));
        addMemberButton.addActionListener(e -> RootFrame.getInstance().addPanel(AddMemberUiPlugin.NAME, true));
        addBookButton.addActionListener(e -> RootFrame.getInstance().addPanel(AddBookUiPlugin.NAME, true));
        printCheckOutRecordsButton.addActionListener(e -> RootFrame.getInstance().addPanel(PrintCheckoutRecordsUiPlugin.NAME, true));
        searchBookButton.addActionListener(e -> RootFrame.getInstance().addPanel(SearchBookUiPlugin.NAME, true));
    }

    public void authorizeFunction() {
        User user = LoggedInUser.get();
        if (user != null) {
            boolean isLibrarian = user.getAuthorizations().contains(Role.LIBRARIAN);
            boolean isAdmin = user.getAuthorizations().contains(Role.ADMIN);
            checkoutBookButton.setVisible(isLibrarian);
            printCheckOutRecordsButton.setVisible(isLibrarian);
            searchBookButton.setVisible(isLibrarian);
            addBookButton.setVisible(isAdmin);
            addMemberButton.setVisible(isAdmin);
            addCopyButton.setVisible(isAdmin);
        }
    }

    @Override
    public void run() {
        authorizeFunction();
    }

    @Override
    public void updateNavigationLink() {
        panel.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getRoot() {
        return panel;
    }
}
