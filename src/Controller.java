/*
        James Adams
        Lab-07-jadams18
        Controller.java
        @username jwadams18
         */

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class Controller {

    private Model m;

    public static String errorLoadingImage = "Error loading selected file or no file was selected. Try again";

    public Controller(JFrame frame){

        this.m = new Model();
        m.mainFrame = frame;
        m.initializeCards();
        m.initializeImages();
        loadPanels();

    }

    /**
     * Creates a new table panel, so the data reflects the changes made
     */
    public void updateTable(){
        m.panels[3] = new TableView(this).mainPanel;

    }

    /**
     * @return the data displayed in the table
     */
    public Object[][] getTableData(){
        return m.tableData;
    }

    /**
     * Creates panels and stores then in an array to reduce the number of panels created and will allow for re-use
     */
    public void loadPanels(){

        m.panels[0] = new ClassifierMenu(this).mainPanel;

        m.panels[1] = new NewBaseballCard(this).mainPanel;

        m.panels[2] = new TableView(this).mainPanel;
    }

    /**
     * Opens the specified panel
     *
     * @param type Model.Panel menu/table/new
     */
    public void openPanel(Model.Panel type){
        m.prevPanel = m.currentPanel;
        switch (type){
            case menu:
                m.currentPanel = m.panels[0];
                break;
            case newCard:
                m.currentPanel = m.panels[1];
                break;
            case tableView:
                m.currentPanel = m.panels[2];
                break;
        }
        if(m.prevPanel != null)
            m.mainFrame.remove(m.prevPanel);

        m.mainFrame.add(m.currentPanel);
        m.mainFrame.repaint();
        m.mainFrame.pack();
    }

    /**
     * Open the view of a specific card
     * @param panel
     */
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

    /**
     * @return the list of baseball cards
     */
    public ArrayList<BsbCard> getCards() {
        return m.cards;
    }

    /**
     * Loads the data into the JTable
     */
    public void initTable() {
        m.initializeTable();
    }

    /**
     * Used to get a card based on selection from combo box
     * @param selection the name of the player
     * @return the player's card object
     */
    public BsbCard getCard(String selection) {
        for (BsbCard card : m.cards) {
            if (card.getName().equals(selection)) {
                return card;
            }
        }
        return null;
    }

    /**
     * @param card removed the card from the Cards/ and the ArrayList/Hashmap then updates the table data
     */
    public void delete(BsbCard card) {
        File cardFile = new File("Cards/" + card.getName() + ".txt");
        if (cardFile.exists()) {
            cardFile.delete();
        }
        m.cards.remove(card);
        m.deckOrder.forEach((k, v) -> {
            v = v-1;
        });
        m.deckOrder.remove(card.getName(), null);
        updateTable();
    }

}
