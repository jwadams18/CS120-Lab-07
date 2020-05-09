/*
        James Adams
        Lab-07-jadams18
        NewBaseballCard.java
        @username jwadams18
         */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NewBaseballCard extends JPanel {

    public JPanel mainPanel;
    private JLabel playerNameLabel;
    private JTextField playerNameEntry;
    private JLabel positionLabel;
    private JComboBox positionCombo;
    private JLabel rarityLabel;
    private JSlider raritySlider;
    private JLabel tradeLabel;
    private JCheckBox trade;
    private JLabel picture;
    private JButton imgBtn;
    private JButton saveBtn;
    private JButton cancelBtn;
    private JComboBox teamSelection;
    private JLabel teamLabel;
    private JComboBox conditionSelection;
    private JTextField ageEntry;
    private JTextField yrsEntry;
    private Controller c;
    private String image; // filename.txt form
    private ArrayList<BsbCard> cards;
    private Boolean newChanges = false;

    public NewBaseballCard(Controller c) {
        this.c = c;
        cards = c.getCards();


        try {
            picture.setIcon(new ImageIcon(ImageIO.read(new File("Images/person.png")).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO implement this
        imgBtn.addActionListener(event -> {
            System.out.println("TODO!");
        });

        saveBtn.addActionListener(event -> {
            if (!playerNameEntry.getText().trim().isBlank()) {
                saveCard();
                String[] options = {"Add another", "View all", "Return to menu"};
                //Add Another - 0, View all - 1, Return to menu - 2
                int selection = JOptionPane.showOptionDialog(this, "Card successfully saved!", "Card save",
                        0, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                switch (selection) {
                    case 0:
                        clearValues();
                        break;
                    case 1:
                        c.openPanel(Model.Panel.tableView);
                        break;
                    case 2:
                        c.openPanel(Model.Panel.menu);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a player name", "Unknown player", JOptionPane.WARNING_MESSAGE);
            }

        });

        cancelBtn.addActionListener(event -> {
            boolean textIsBlank = playerNameEntry.getText().isBlank() && ageEntry.getText().isBlank() && yrsEntry.getText().isBlank();
            boolean isSelected = trade.isSelected();
            boolean sliderMoved = raritySlider.getValue() != 1;
            if (!newChanges && textIsBlank && isSelected && sliderMoved) {
                c.openPanel(Model.Panel.menu);
            } else {
                int selection = JOptionPane.showConfirmDialog(this, "Save your changes?", "Save changes",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (selection == JOptionPane.YES_OPTION) {
                    if (!playerNameEntry.getText().isBlank()) {
                        saveCard();
                    } else {
                        JOptionPane.showMessageDialog(this, "Please enter a player name", "Unknown player", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    clearValues();
                    c.openPanel(Model.Panel.menu);
                }
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Jframe");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.add(new NewBaseballCard(new Controller(frame)).mainPanel);
        frame.setVisible(true);
    }

    private void saveCard() {

        int age = Integer.parseInt(ageEntry.getText().trim());
        int yrsPlayed = Integer.parseInt(yrsEntry.getText().trim());
        BsbCard newCard = new BsbCard(playerNameEntry.getText().trim(), image, age, yrsPlayed, teamSelection.getSelectedIndex(),
                positionCombo.getSelectedIndex(), raritySlider.getValue(), conditionSelection.getSelectedIndex(), trade.isSelected());
        newCard.save();
        cards.add(newCard);
        clearValues();
        c.updateTable();
    }

    public void clearValues() {
        //TODO clear picture selection
        playerNameEntry.setText(null);
        ageEntry.setText(null);
        yrsEntry.setText(null);
        teamSelection.setSelectedIndex(0);
        positionCombo.setSelectedIndex(0);
        raritySlider.setValue(0);
        trade.setSelected(false);
        conditionSelection.setSelectedIndex(0);
    }

}
