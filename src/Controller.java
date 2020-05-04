/*
        James Adams
        Lab-07-jadams18
        Controller.java
        @username jwadams18
         */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Controller {

    private Model m;

    public Controller(JFrame frame){

        this.m = new Model();
        m.mainFrame = frame;
        m.initializeCards();
        loadPanels();

    }

    public void updateTable(){
        m.panels[3] = new tableView(this).mainPanel;

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

        m.panels[1] = new newBaseballCard(this).mainPanel;

        m.panels[2] = new viewBaseballCard(this).mainPanel;

        m.panels[3] = new tableView(this).mainPanel;
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

    public ArrayList<bsbCard> getCards(){
        return m.cards;
    }

    public void initTable() {
        m.initializeTable();
    }
}
