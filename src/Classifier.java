/*
        James Adams
        Lab-07-jadams18
        Classifier.java
        @username jwadams18
         */

import javax.swing.*;

public class Classifier {

    private JFrame frame;
    private Controller c;

    public Classifier(){

        frame = new JFrame("Lab-07-jadams18");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        c = new Controller(frame);
        c.openPanel(Model.Panel.menu);
        frame.setVisible(true);
    }
}
