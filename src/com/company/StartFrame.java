package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame implements ActionListener {

    private JFrame startFrame;
    private JPanel panel;
    private JButton game;
    private JTextField insertName;
    private GamePlayer player;
    private boolean buttonPressed;
    private JLabel emptyName;
    private JLabel longName;
    private JLabel shortName;

    public StartFrame(GamePlayer player) {

        this.player = player;
        startFrame = new JFrame("SwordsAndJava");
        startFrame.setIconImage((new ImageIcon("Sword.JFIF")).getImage());
        panel = new JPanel();
        panel.setLayout(null);
        startFrame.add(panel);
        startFrame.setSize(500, 500);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startScreen();
        startFrame.setVisible(true);
        game.addActionListener(this);
    }

    public boolean isButtonPressed() {

        return buttonPressed;
    }

    public void startScreen() {

        panel.setBackground(Color.BLACK);

        JLabel title = new JLabel();
        title.setText("SwordsAndJava");
        title.setFont(new Font("Calibri", Font.PLAIN, 24));
        title.setForeground(Color.RED);
        title.setBounds(165, 20, 200, 27);

        JLabel name = new JLabel();
        name.setText("Insert your player's name: ");
        name.setFont(new Font("Calibri", Font.PLAIN, 14));
        name.setForeground(Color.RED);
        name.setBounds(25, 50, 160, 50);

        emptyName = new JLabel();
        emptyName.setText("The name must not be empty.");
        emptyName.setBounds(25, 120, 200, 30);
        emptyName.setForeground(Color.RED);
        emptyName.setVisible(false);

        longName = new JLabel();
        longName.setText("The name is too long.");
        longName.setBounds(25, 120, 200, 30);
        longName.setForeground(Color.RED);
        longName.setVisible(false);

        shortName = new JLabel();
        shortName.setText("The name is too short.");
        shortName.setBounds(25, 120, 200, 30);
        shortName.setForeground(Color.RED);
        shortName.setVisible(false);

        insertName = new JTextField();
        insertName.setBounds(25, 90, 160, 25);

        game = new JButton("Start");
        game.setBounds(400, 400, 80, 50);

        ImageIcon image = new ImageIcon("BackGround2.png");
        JLabel imgLabel = new JLabel(image);
        imgLabel.setBounds(70, 100, 350, 350);

        panel.add(imgLabel);
        panel.add(game);
        panel.add(name);
        panel.add(title);
        panel.add(insertName);
        panel.add(emptyName);
        panel.add(longName);
        panel.add(shortName);
    }

    public void actionPerformed(ActionEvent e) {
        if (insertName.getText().isEmpty()) {
            emptyName.setVisible(true);
            longName.setVisible(false);
            shortName.setVisible(false);
            return;
        }
        if (insertName.getText().length() > 8) {
            longName.setVisible(true);
            emptyName.setVisible(false);
            shortName.setVisible(false);
            return;
        }

        if (insertName.getText().length() < 4) {
            longName.setVisible(false);
            emptyName.setVisible(false);
            shortName.setVisible(true);
            return;
        }
        player.setName(insertName.getText());
        Game.setAuxName(insertName.getText());
        buttonPressed = true;
        startFrame.setVisible(false);
    }
}