package presentation;

import data.model.Role;
import data.model.User;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel implements Initialization {

    private JButton checkoutBookButton;
    private JButton searchMemberButton;
    private JButton searchBookButton;
    private JButton addMemberButton;
    private JButton addCopyButton;
    private JButton addBookButton;

    HomePanel() {
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        checkoutBookButton = new JButton();
        checkoutBookButton.setText("Checkout Book");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(checkoutBookButton, gbc);
        searchMemberButton = new JButton();
        searchMemberButton.setText("Search Member");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(searchMemberButton, gbc);
        searchBookButton = new JButton();
        searchBookButton.setText("Search Book");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(searchBookButton, gbc);
        addMemberButton = new JButton();
        addMemberButton.setText("Add Member");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addMemberButton, gbc);
        addCopyButton = new JButton();
        addCopyButton.setText("Add Copy");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addCopyButton, gbc);
        addBookButton = new JButton();
        addBookButton.setText("Add Book");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(addBookButton, gbc);

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
}
