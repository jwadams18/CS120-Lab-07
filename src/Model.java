/*
        James Adams
        Lab-07-jadams18
        Model.java
        @username jwadams18
         */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;

public class Model {

    public enum Panel {menu, newCard, viewCard, tableView}


    public JFrame mainFrame;
    public JPanel[] panels = new JPanel[4];
    public JPanel prevPanel, currentPanel;
    public ArrayList<bsbCard> cards = new ArrayList<>();
    public Object[][] tableData;
    public DefaultTableModel tableModel;
    public JTable cardTable;


    /**
     * Takes the files from the Game directory, checks that said directory exists
     * Loops through the directory to get all the files, and loads them into game objects
     */
    public void initializeCards(){

        File dir = new File("Cards");
        if(!dir.exists()){
            boolean success = dir.mkdir();
            System.out.println("Games directory created!");
        }
        if(dir.list() != null){
            for(String strng : dir.list()){
                cards.add(new bsbCard(strng));
            }
        }
    }

    public void initializeTable(){
        tableData = new Object[cards.size()][7];

        for(int i = 0; i < cards.size(); i++){
            Object[] player = tableData[i];
            bsbCard card = cards.get(i);

            player[0] = card.getName();
//            System.out.println(card.getPos());
            player[1] = card.getPos().getName();
            player[2] = card.getAge();
            player[3] = card.getYrsPlayed();
//            System.out.println(card.getCondition());
            player[4] = card.getCondition();

            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < card.getRarity(); j++){
                sb.append("\u2605");
            }
            player[5] = sb.toString();
            if(card.isTrade()){
                player[6] = "\u2714";
            } else {
                player[6] = "\u0078";
            }
        }
    }

}
