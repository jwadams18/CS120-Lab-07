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
    private String imagePath;
    private JFileChooser fileChooser;

    public ViewBaseballCard(Controller c) {
        this.c = c;
        cards = c.getCards();
//        setImage("person.png");

        imgBtn.addActionListener(event -> {
            imagePath = uploadFile();
            System.out.println("[NewBaseballCard.java - 62] " + imagePath);
            try {
                picture.setIcon(new ImageIcon(ImageIO.read(new File(imagePath)).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
            } catch (NullPointerException | IOException e) {
                JOptionPane.showMessageDialog(this, Controller.errorLoadingImage, "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

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

        deleteBtn.addActionListener(event -> {
            int selection = JOptionPane.showConfirmDialog(this, "Delete " + currentCard.getName() + "'s card?", "Confrim delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (selection == JOptionPane.YES_OPTION) {
                c.delete(currentCard);
                c.openPanel(Model.Panel.tableView);
            }
        });

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

    public void setValues(BsbCard card) {
        System.out.println("[VBC.java-69] " + card.getName() + " " + card.getTeam() + " " + card.getImageFileStr());
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
        if (card.getImageFileStr() != null) {

        }

    }

    public void setImage(String image) {
        try {
            picture.setIcon(new ImageIcon(ImageIO.read(new File("Images/" + image)).getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCard() {
        currentCard.setAge(Integer.parseInt(ageEntry.getText().trim()));
        currentCard.setYrs(Integer.parseInt(yrsEntry.getText().trim()));
        c.updateTable();
    }

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

    public String uploadFile() {
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Select an image for your new card");
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG and PNG images", "png", "jpg"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int selection = fileChooser.showOpenDialog(this);

        if (selection == JFileChooser.APPROVE_OPTION) {

            File temp = fileChooser.getSelectedFile();
            System.out.println(temp.toPath());
            File clone = new File("Images/" + temp.getName());
            try {
                Files.copy(temp.toPath(), clone.toPath(), REPLACE_EXISTING);
                System.out.println("Files copied");
            } catch (IOException e) {
                e.printStackTrace();
            }


            System.out.println(clone.getPath());
            return clone.getPath();
        }
        return null;
    }
}
