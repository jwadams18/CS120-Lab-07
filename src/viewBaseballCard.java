/*
        James Adams
        Lab-07-jadams18
        viewBaseballCard.java
        @username jwadams18
         */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class viewBaseballCard {

    public JPanel mainPanel;
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
    private JLabel starLabel;
    private JComboBox conditionSelection;
    private JLabel playerName;
    private JTextField ageEntry;
    private JTextField yrsEntry;
    private Controller c;
    private String image;
    private ArrayList<bsbCard> cards;

    public viewBaseballCard(Controller c) {
        this.c = c;
        cards = c.getCards();
        setImage("person.png");

        //TODO remove button, select image button
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Jframe");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new viewBaseballCard(new Controller(frame)).mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void saveCard() {

        int age = Integer.parseInt(ageEntry.getText().trim());
        int yrsPlayed = Integer.parseInt(yrsEntry.getText().trim());
        bsbCard newCard = new bsbCard(playerName.getText().trim(), image, age, yrsPlayed, teamSelection.getSelectedIndex(),
                positionCombo.getSelectedIndex(), raritySlider.getValue(), conditionSelection.getSelectedIndex(), trade.isSelected());
        newCard.save();
        cards.add(newCard);
    }

    public void setValues(bsbCard card) {
        playerName.setText(card.getName());
        teamSelection.setSelectedIndex(card.getTeam().getIndex());
        positionCombo.setSelectedIndex(card.getPos().getIndex());
        raritySlider.setValue(card.getRarity());
        trade.setSelected(card.isTrade());
        conditionSelection.setSelectedIndex(card.getCondition().getIndex());
        setImage(card.getImageFileStr());
    }

    public void setImage(String image) {
        try {
            picture.setIcon(new ImageIcon(ImageIO.read(new File("Images/" + image)).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
