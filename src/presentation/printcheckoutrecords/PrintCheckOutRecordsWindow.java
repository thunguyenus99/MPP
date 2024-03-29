package presentation.printcheckoutrecords;

import business.LibraryController;
import business.exception.GetCheckoutRecordException;
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
import java.util.List;

public class PrintCheckOutRecordsWindow implements UIFrame {
    private JPanel root;
    private JTextField txtMemberId;
    private JButton btnPrintCheckOutRecords;
    private JTable checkOutRecordTbl;
    private JLabel lblMemberId;

    public PrintCheckOutRecordsWindow() {
        btnPrintCheckOutRecords.addActionListener(e -> {
            try {
                ValidatorFactory.getValidator(getClass()).validate(this);
                List<CheckoutRecord> checkoutRecords = LibraryController.getInstance().getCheckoutRecords(txtMemberId.getText().trim());
                updateUi(checkoutRecords);
            } catch (RuleException | GetCheckoutRecordException ex) {
                JOptionPane.showMessageDialog(root, ex.getMessage());
            }
        });
    }

    private void updateUi(List<CheckoutRecord> checkoutRecords) {
        String[] columnNames = {
                "Book ISBN",
                "Book Title",
                "Book Copy Number",
                "Due Date",
                "Checkout Date"
        };

        String[][] rowValues = new String[checkoutRecords.size()][7];
        for (int i = 0; i < checkoutRecords.size(); i++) {
            CheckoutRecord record = checkoutRecords.get(i);
            BookCopy bookCopy = record.getBookCopy();
            Book book = bookCopy.getBook();
            rowValues[i][0] = book.getIsbn();
            rowValues[i][1] = book.getTitle();
            rowValues[i][2] = String.valueOf(bookCopy.getCopyNum());
            rowValues[i][3] = record.getDueDate().toString();
            rowValues[i][4] = record.getCheckoutDate().toString();
        }
        checkOutRecordTbl.setModel(new DefaultTableModel(rowValues, columnNames));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PrintCheckoutRecords");
        frame.setContentPane(new PrintCheckOutRecordsWindow().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void run() {
        txtMemberId.setText("");
        ((DefaultTableModel) checkOutRecordTbl.getModel()).setRowCount(0);
        ((DefaultTableModel) checkOutRecordTbl.getModel()).setColumnCount(0);
    }

    @Override
    public void updateNavigationLink() {
        root.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getRoot() {
        return root;
    }

    public JTextField getTxtMemberId() {
        return txtMemberId;
    }
}
