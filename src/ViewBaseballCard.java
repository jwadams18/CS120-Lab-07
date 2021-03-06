/*
        James Adams
        Lab-07-jadams18
        ViewBaseballCard.java
        @username jwadams18
         */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ViewBaseballCard extends JPanel {

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
    private JButton deleteBtn;
    private Controller c;
    private ArrayList<BsbCard> cards;
    private boolean newChanges = false;
    private BsbCard currentCard;
    private String imageName;
    private JFileChooser fileChooser;

    public ViewBaseballCard(Controller c) {
        this.c = c;
        cards = c.getCards();
        //General image when loaded
        setImage("person.png");

        //User image upload
        imgBtn.addActionListener(event -> {
            imageName = uploadFile();
            currentCard.setImage(imageName);
            setImage(imageName);
        });

        //Action listeners for combo boxes, slider, and checkbox

        teamSelection.addActionListener(event -> {
            currentCard.setTeam(teamSelection.getSelectedIndex());
            newChanges = true;
        });

        positionCombo.addActionListener(event -> {
            currentCard.setPosition(positionCombo.getSelectedIndex());
            newChanges = true;
        });

        conditionSelection.addActionListener(event -> {
            currentCard.setCondition(conditionSelection.getSelectedIndex());
            newChanges = true;
        });

        raritySlider.addChangeListener(event -> {
            currentCard.setRarity(raritySlider.getValue());
            newChanges = true;
            setStarLabel(raritySlider.getValue());

        });

        trade.addActionListener(event -> {
            currentCard.setTrade(trade.isSelected());
            newChanges = true;
        });

        //Delete button, with confirm message
        deleteBtn.addActionListener(event -> {
            int selection = JOptionPane.showConfirmDialog(this, "Delete " + currentCard.getName() + "'s card?", "Confrim delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                c.delete(currentCard);
                c.openPanel(Model.Panel.tableView);
            }
        });

        //Cancel button, ask about saving before closes
        cancelBtn.addActionListener(event -> {
            if (newChanges) {
                int selection = JOptionPane.showConfirmDialog(this, "Save recent changes?", "Save changes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (selection == JOptionPane.YES_OPTION) {
                    saveCard();
                }
            }
            c.openPanel(Model.Panel.tableView);
        });

        saveBtn.addActionListener(event -> {
            saveCard();
            c.openPanel(Model.Panel.tableView);
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Jframe");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(new ViewBaseballCard(new Controller(frame)).mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Loads the data into the GUI from the bsbcard object which was loaded from file
     *
     * @param card the object holding all data from file
     */
    public void setValues(BsbCard card) {
        currentCard = card;
        playerName.setText(card.getName());
        teamSelection.setSelectedIndex(card.getTeam().getIndex());
        positionCombo.setSelectedIndex(card.getPos().getIndex());
        raritySlider.setValue(card.getRarity());
        setStarLabel(card.getRarity());
        ageEntry.setText(Integer.toString(card.getAge()));
        yrsEntry.setText(Integer.toString(card.getYrsPlayed()));
        trade.setSelected(card.isTrade());
        conditionSelection.setSelectedIndex(card.getCondition().getIndex());
        setImage(card.getImage());

    }

    /**
     * Used to set images
     * @param image file name, assumed to be in images
     */
    public void setImage(String image) {
        //had to handle loading from file producing "null" while creation in new menu produced null object
        File imagePath;

        try {
            if (!image.equals("null")) {
                imagePath = new File("Images/" + image);
            } else {
                System.out.println("Displaying image person.png due to null loaded from card.");
                imagePath = new File("Images/person.png");
            }
            picture.setIcon(new ImageIcon(ImageIO.read(imagePath).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Displaying image person.png due to null loaded from card.");
            setImage("person.png");
        }
    }

    /**
     * Saves the data to the card object and updates table data
     */
    private void saveCard() {
        if (imageName != null)
            currentCard.setImage(imageName);

        currentCard.setAge(Integer.parseInt(ageEntry.getText().trim()));
        currentCard.setYrs(Integer.parseInt(yrsEntry.getText().trim()));
        currentCard.save();
        c.updateTable();
    }

    /**
     * Changes based on the value of the slider to display the rarity of the current card below the name
     * @param stars 0 - 5
     */
    public void setStarLabel(int stars) {
        StringBuilder sb = new StringBuilder();
        if (stars == 0) {
            sb.append("Common");
            starLabel.setText(sb.toString());
            return;
        }
        for (int i = 0; i < stars; i++) {
            sb.append("\u2605");
        }
        starLabel.setText(sb.toString());
    }

    /**
     * Prompts the user with JFilechooser to upload an image to display on the bsbcard
     * @return a string to the file located in Images/
     */
    public String uploadFile() {
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select an image for your card");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPG and PNG images", "png", "jpg"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int selection = fileChooser.showOpenDialog(this);

        if (selection == JFileChooser.APPROVE_OPTION) {

            File temp = fileChooser.getSelectedFile();
            File clone = new File("Images/" + temp.getName());
            try {
                Files.copy(temp.toPath(), clone.toPath(), REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return clone.getName();
        }
        return null;
    }
}
