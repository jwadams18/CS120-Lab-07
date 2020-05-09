/*
        James Adams
        Lab-07-jadams18
        TableView.java
        @username jwadams18
         */

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class TableView extends JPanel implements Serializable {

    private static final long serialVersionUID = 173044072361605644L;
    public JPanel mainPanel;
    private JLabel title;
    private JButton closeBtn;
    private JButton editBtn;
    private JScrollPane container;
    private JTable cardTable;
    private JButton addBtn;
    private Controller c;
    private ArrayList<BsbCard> cards;
    private DefaultTableModel model;

    public TableView(Controller c) {
        this.c = c;
        cards = c.getCards();
        String[] colTitles = {"Player name", "Age", "Team", "Position", "Yrs. Played", "Condition", "Rarity", "Tradable"};
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
        //Shrinks age
        cardTable.getColumnModel().getColumn(1).setPreferredWidth(30);
        //Expands Team
        cardTable.getColumnModel().getColumn(2).setPreferredWidth(125);
        //Expands Position
        cardTable.getColumnModel().getColumn(3).setPreferredWidth(95);
        //Shrinks trade setting
        cardTable.getColumnModel().getColumn(7).setPreferredWidth(55);
        for (int i = 1; i < cardTable.getColumnCount(); i++) {
            cardTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        cardTable.setBounds(20, 20, 225, 150);
        cardTable.setDragEnabled(false);
        cardTable.getTableHeader().setReorderingAllowed(false);
        container.setPreferredSize(new Dimension(600, 300));

        editBtn.addActionListener(event -> {
            String[] currentCardsName = new String[c.getCards().size()];
            for (int i = 0; i < currentCardsName.length; i++) {
                ArrayList<BsbCard> temp = c.getCards();
                currentCardsName[i] = temp.get(i).getName();

            }
            String selection = (String) JOptionPane.showInputDialog(this, "Please select a card to edit", "Edit card", JOptionPane.QUESTION_MESSAGE, null, currentCardsName, currentCardsName[0]);
            if (selection != null) {
                ViewBaseballCard vbc = new ViewBaseballCard(c);
                vbc.setValues(c.getCard(selection));
                c.openViewCard(vbc.mainPanel);
            }
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
        frame.add(new TableView(new Controller(frame)).mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}