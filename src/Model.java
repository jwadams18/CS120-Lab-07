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
import java.util.HashMap;

public class Model {

    public JPanel[] panels = new JPanel[3];


    public JFrame mainFrame;

    public enum Panel {menu, newCard, tableView}
    public JPanel prevPanel, currentPanel;
    public ArrayList<BsbCard> cards = new ArrayList<>();
    public Object[][] tableData;
    public DefaultTableModel tableModel;
    public JTable cardTable;
    public HashMap<String, Integer> deckOrder = new HashMap<>();


    /**
     * Takes the files from the Game directory, checks that said directory exists
     * Loops through the directory to get all the files, and loads them into game objects
     */
    public void initializeCards(){

        File dir = new File("Cards");
        if(!dir.exists()){
            boolean success = dir.mkdir();
            System.out.println("Cards directory created!");
        }
        if(dir.list() != null){
            for(String strng : dir.list()){
                cards.add(new BsbCard(strng));
            }
        }
    }

    /**
     * Checks the directory is created
     */
    public void initializeImages() {

        File dir = new File("Images");
        if (!dir.exists()) {
            boolean success = dir.mkdir();
            System.out.println("Images directory created!");
        }
    }


    /**
     * Loads the player data into the table from the Bsbcard objects
     */
    public void initializeTable(){
        tableData = new Object[cards.size()][8];

        for(int i = 0; i < cards.size(); i++){
            Object[] player = tableData[i];
            BsbCard card = cards.get(i);

            player[0] = card.getName();
            player[1] = card.getAge();
            player[2] = card.getTeam().getName();
//            System.out.println(card.getPos());
            player[3] = card.getPos().getName();

            player[4] = card.getYrsPlayed();
//            System.out.println(card.getCondition());
            player[5] = card.getCondition();

            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < card.getRarity(); j++){
                sb.append("\u2605");
            }
            player[6] = sb.toString();
            if(card.isTrade()){
                player[7] = "\u2714";
            } else {
                player[7] = "\u0078";
            }
        }
    }

}
