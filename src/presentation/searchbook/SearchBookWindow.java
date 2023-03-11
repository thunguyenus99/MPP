package presentation.searchbook;

import business.LibraryController;
import data.model.Book;
import data.model.BookCopy;
import data.model.CheckoutRecord;
import data.model.LibraryMember;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.validator.RuleException;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class SearchBookWindow implements UIFrame {
    private JTextField txtIsbn;
    private JButton btnCheck;
    private JTable tblMember;
    private JLabel lblIsbn;
    private JScrollPane scrMember;
    private JPanel root;

    public SearchBookWindow() {
        btnCheck.addActionListener(this::onCheckClick);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CheckBookCopyOverdueStatusWindow");
        frame.setContentPane(new SearchBookWindow().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void onCheckClick(ActionEvent e) {
        try {
            ValidatorFactory.getValidator(getClass()).validate(this);
        } catch (RuleException ex) {
            JOptionPane.showMessageDialog(root, ex.getMessage());
            return;
        }
        String isbn = txtIsbn.getText().trim();
        Book book = LibraryController.getInstance().getBookByIsbn(isbn);
        if (book == null) {
            JOptionPane.showMessageDialog(root, "Book not found!");
            return;
        }

        displayBookInfo(book);
    }

    private void displayBookInfo(Book book) {
        String[] columnNames = {
                "ISBN",
                "Book title",
                "Copy number",
                "Member id",
                "Member name",
                "Checkout date",
                "Due date"
        };
        List<BookCopy> bookCopies = book.getBookCopyList();
        String[][] rowValues = new String[bookCopies.size()][7];
        for (int i = 0; i < bookCopies.size(); i++) {
            BookCopy bookCopy = bookCopies.get(i);
            rowValues[i][0] = book.getIsbn();
            rowValues[i][1] = book.getTitle();
            rowValues[i][2] = String.valueOf(bookCopy.getCopyNum());
            CheckoutRecord checkoutRecord = bookCopy.getCheckoutRecord();
            if (checkoutRecord == null) {
                continue;
            }
            LibraryMember member = checkoutRecord.getLibraryMember();
            rowValues[i][3] = member.getMemberId();
            rowValues[i][4] = member.getFirstName() + " " + member.getLastName();
            rowValues[i][5] = checkoutRecord.getCheckoutDate().toString();
            rowValues[i][6] = checkoutRecord.getDueDate().toString();
        }
        tblMember.setModel(new DefaultTableModel(rowValues, columnNames));
    }

    public JTextField getTxtIsbn() {
        return txtIsbn;
    }

    @Override
    public void run() {
        txtIsbn.setText("");
        ((DefaultTableModel) tblMember.getModel()).setRowCount(0);
        ((DefaultTableModel) tblMember.getModel()).setColumnCount(0);
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
