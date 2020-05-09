/*
        James Adams
        Lab-07-jadams18
        Controller.java
        @username jwadams18
         */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;

public class Controller {

    private Model m;

    public static String errorLoadingImage = "Error loading selected file or no file was selected. Try again";

    public Controller(JFrame frame){

        this.m = new Model();
        m.mainFrame = frame;
        m.initializeCards();
        loadPanels();

    }

    public void updateTable(){
        m.panels[3] = new TableView(this).mainPanel;

    }

    public void setTableModel(DefaultTableModel tm, JTable cardTable){
        m.tableModel = tm;
        m.cardTable = cardTable;
    }

    public Object[][] getTableData(){
        return m.tableData;
    }

    /**
     * Creates panels and stores then in an array to reduce the number of panels created and will allow for re-use
     */
    public void loadPanels(){

        m.panels[0] = new ClassifierMenu(this).mainPanel;

        m.panels[1] = new NewBaseballCard(this).mainPanel;

        m.panels[2] = new ViewBaseballCard(this).mainPanel;

        m.panels[3] = new TableView(this).mainPanel;
    }

    public void openPanel(Model.Panel type){
            m.prevPanel = m.currentPanel;
        switch (type){
            case menu:
                m.currentPanel = m.panels[0];
                break;
            case newCard:
                m.currentPanel = m.panels[1];
                break;
            case viewCard:
                m.currentPanel = m.panels[2];
                break;
            case tableView:
                m.currentPanel = m.panels[3];
                break;
        }
        if(m.prevPanel != null)
        m.mainFrame.remove(m.prevPanel);

        m.mainFrame.add(m.currentPanel);
        m.mainFrame.repaint();
        m.mainFrame.pack();
    }

    public void openViewCard(JPanel panel) {
        m.prevPanel = m.currentPanel;
        m.currentPanel = panel;
        if (m.prevPanel != null) {
            m.mainFrame.remove(m.prevPanel);
        }

        m.mainFrame.add(m.currentPanel);
        m.mainFrame.repaint();
        m.mainFrame.pack();
    }

    public ArrayList<BsbCard> getCards() {
        return m.cards;
    }

    public void initTable() {
        m.initializeTable();
    }

    public BsbCard getCard(String selection) {
        return m.cards.get(m.deckOrder.get(selection));
    }

    public void putInDeck(BsbCard temp) {
        m.deckOrder.put(temp.getName(), m.cards.size());
    }

    public void delete(BsbCard card) {
        File cardFile = new File("Cards/" + card.getName() + ".txt");
        if (cardFile.exists()) {
            cardFile.delete();
        }
        int index = m.deckOrder.get(card.getName());
        m.cards.remove(index);
        m.deckOrder.forEach((k, v) -> {
            System.out.println(k + " " + v);
        });
        m.deckOrder.remove(card.getName(), null);
        updateTable();
    }

}
