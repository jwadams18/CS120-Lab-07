/*
        James Adams
        Lab-05-jadams18
        ClassifierMenu.java
        @username jwadams18
         */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ClassifierMenu extends JPanel {

    public JPanel mainPanel;
    private JButton addBtn;
    private JButton viewBtn;
    private JButton quitButton;
    private JLabel pictureLabel;
    private Controller c;

    public ClassifierMenu(Controller c) {
        this.c = c;

        try {
            pictureLabel.setIcon(new ImageIcon(ImageIO.read(new File("Images/baseball.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addBtn.addActionListener(event -> {
            c.openPanel(Model.Panel.newCard);
        });

        viewBtn.addActionListener(event -> {
            c.openPanel(Model.Panel.tableView);
        });

        quitButton.addActionListener(event -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Jframe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.add(new ClassifierMenu(new Controller(frame)).mainPanel);
        frame.setVisible(true);
    }
}

