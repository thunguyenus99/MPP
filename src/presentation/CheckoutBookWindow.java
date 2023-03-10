package presentation;

import business.LibraryController;
import business.exception.CheckoutException;
import data.model.Book;
import data.model.BookCopy;
import data.model.CheckoutRecord;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CheckoutBookWindow implements UIFrame {
    private JPanel panel;
    private JTextField txtMemberId;
    private JTextField txtIsbn;
    private JButton btnCheckout;
    private JLabel lblMemberId;
    private JLabel lblIsbn;
    private JTable tblCheckoutRecord;

    public CheckoutBookWindow() {
        btnCheckout.addActionListener(e -> {
            try {
                CheckoutRecord checkoutRecord = LibraryController.getInstance().checkoutBook(txtMemberId.getText().trim(), txtIsbn.getText().trim());
                BookCopy bookCopy = checkoutRecord.getBookCopy();
                Book book = checkoutRecord.getBookCopy().getBook();
                String[] columnNames = {
                        "Book Title",
                        "Book Copy Number",
                        "Checkout Date",
                        "Due Date"
                };
                String[][] rowValues = {
                        {book.getTitle(), String.valueOf(bookCopy.getCopyNum()), checkoutRecord.getCheckoutDate().toString(), checkoutRecord.getDueDate().toString()},
                };
                tblCheckoutRecord.setModel(new DefaultTableModel(rowValues, columnNames));
            } catch (CheckoutException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckoutBookWindow");
        frame.setContentPane(new CheckoutBookWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel.setBorder(BorderFactory.createTitledBorder(null, "Checkout Book", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        lblMemberId = new JLabel();
        lblMemberId.setText("Member ID");
        panel.add(lblMemberId, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblIsbn = new JLabel();
        lblIsbn.setText("ISBN");
        panel.add(lblIsbn, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtMemberId = new JTextField();
        panel.add(txtMemberId, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        txtIsbn = new JTextField();
        panel.add(txtIsbn, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        panel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        btnCheckout = new JButton();
        btnCheckout.setText("Checkout");
        panel.add(btnCheckout, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    @Override
    public JPanel getRoot() {
        return panel;
    }

    @Override
    public void run() {

    }

    @Override
    public void updateNavigationLink() {
        panel.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }
}
