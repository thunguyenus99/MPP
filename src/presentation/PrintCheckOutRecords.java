package presentation;

import business.LibraryController;
import business.exception.GetCheckoutRecordException;
import data.model.Book;
import data.model.BookCopy;
import data.model.CheckoutRecord;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PrintCheckOutRecords implements UIFrame {
    private JPanel root;
    private JTextField memberIdTxt;
    private JButton printCheckOutRecordsButton;
    private JTable checkOutRecordTbl;

    public PrintCheckOutRecords() {
        printCheckOutRecordsButton.addActionListener(e -> {
            try {
                List<CheckoutRecord> checkoutRecords = LibraryController.getInstance().getCheckoutRecordByMemberId(memberIdTxt.getText().trim());

                String[] columnNames = {
                        "Book ISBN",
                        "Book Title",
                        "Book Copy Number",
                        "Due Date",
                        "Checkout Date",
                        "Checkin Date",
                        "Fine"
                };

                String[][] rowValues = new String[checkoutRecords.size()][7];
                for (int i = 0; i < checkoutRecords.size(); i++) {
                    CheckoutRecord record = checkoutRecords.get(i);
                    BookCopy bookCopy = record.getBookCopy();
                    Book book = bookCopy.getBook();
                    rowValues[i][0] = book.getIsbn();
                    rowValues[i][1] = book.getTitle();
                    rowValues[i][2] = String.valueOf(bookCopy.getCopyNum());
                    rowValues[i][3]= record.getDueDate().toString();
                    rowValues[i][4] = record.getCheckoutDate().toString();
                    rowValues[i][5] = record.getCheckinDate() == null ?"":record.getCheckinDate().toString();
                    rowValues[i][6] = String.valueOf(record.getFine());

                }
                checkOutRecordTbl.setModel(new DefaultTableModel(rowValues, columnNames));
            } catch (GetCheckoutRecordException ex) {
                JOptionPane.showMessageDialog(root, ex.getMessage());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PrintCheckoutRecords");
        frame.setContentPane(new PrintCheckOutRecords().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void run() {

    }

    @Override
    public void updateNavigationLink() {
        root.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getRoot() {
        return root;
    }
}
