package view;

import javax.swing.JTable;

public class NonEditableTable extends JTable{

    NonEditableTable(Object[][] rowData, String[] columnNames) {
        super(rowData, columnNames);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}
