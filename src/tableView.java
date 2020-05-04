/*
        James Adams
        Lab-07-jadams18
        tableView.java
        @username jwadams18
         */

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class tableView {

    public JPanel mainPanel;
    private JLabel title;
    private JButton closeBtn;
    private JButton editBtn;
    private JScrollPane container;
    private JTable cardTable;
    private JButton addBtn;
    private Controller c;
    private ArrayList<bsbCard> cards;
    private DefaultTableModel model;

    public tableView(Controller c) {
        this.c = c;
        cards = c.getCards();
        String[] colTitles = {"Player name", "Position", "Age", "Yrs. Played", "Condition", "Rarity", "Tradable"};
        c.initTable();
        model = new DefaultTableModel(c.getTableData(), colTitles) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false, don't want edits this way
                return false;
            }
        };

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        cardTable.setModel(model);
        cardTable.getColumnModel().getColumn(2).setPreferredWidth(25);
        cardTable.getColumnModel().getColumn(1).setPreferredWidth(105);
        for (int i = 2; i < cardTable.getColumnCount(); i++) {
            cardTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        cardTable.setBounds(20, 20, 200, 150);
        cardTable.setDragEnabled(false);
        cardTable.getTableHeader().setReorderingAllowed(false);
        container.setPreferredSize(new Dimension(600, 300));

        //TODO implement this
        editBtn.addActionListener(event -> {

        });

        closeBtn.addActionListener(event -> {
            c.openPanel(Model.Panel.menu);
        });
        addBtn.addActionListener(event -> {
            c.openPanel(Model.Panel.newCard);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jframe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new tableView(new Controller(frame)).mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}