package presentation.checkoutbook;

import business.LibraryController;
import business.exception.CheckoutException;
import data.model.Book;
import data.model.BookCopy;
import data.model.CheckoutRecord;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.validator.RuleException;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

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
                ValidatorFactory.getValidator(this.getClass()).validate(this);
                CheckoutRecord checkoutRecord = LibraryController.getInstance().checkoutBook(txtMemberId.getText().trim(), txtIsbn.getText().trim());
                updateUi(checkoutRecord);
            } catch (RuleException | CheckoutException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });
    }

    private void updateUi(CheckoutRecord checkoutRecord) {
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
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckoutBookWindow");
        frame.setContentPane(new CheckoutBookWindow().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public JPanel getRoot() {
        return panel;
    }

    @Override
    public void run() {
        txtMemberId.setText("");
        txtIsbn.setText("");
        ((DefaultTableModel) tblCheckoutRecord.getModel()).setRowCount(0);
        ((DefaultTableModel) tblCheckoutRecord.getModel()).setColumnCount(0);
    }

    @Override
    public void updateNavigationLink() {
        panel.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    public JTextField getTxtMemberId() {
        return txtMemberId;
    }

    public JTextField getTxtIsbn() {
        return txtIsbn;
    }
}
