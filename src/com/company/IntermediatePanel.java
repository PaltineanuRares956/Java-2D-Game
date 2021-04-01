package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntermediatePanel extends JPanel implements ActionListener{

    JButton restart;
    JButton exit;
    JButton nextLevelButton;
    private boolean isExitPressed;
    private boolean isRestartPressed;
    private boolean isNextLevelPressed;

    public IntermediatePanel(boolean isWinner, int level) {

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.setBackground(Color.BLACK);
        int gap = 5;
        setLayout(new BorderLayout(gap, gap));
        setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        if (!isWinner) {
            deathPanel();
        }
        else if (level == 1) {
            nextLevel();
        }
        else {
            winnerPanel();
        }
    }

    public void deathPanel() {
        JLabel deathMessage = new JLabel("You died!");
        deathMessage.setForeground(Color.RED);
        deathMessage.setFont(new Font("Calibri", Font.PLAIN, 40));
        deathMessage.setBounds(0, 0, 200, 100);
        deathMessage.setHorizontalAlignment(SwingConstants.CENTER);
        deathMessage.setVerticalAlignment(SwingConstants.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());

        restart = new JButton("Restart");
        exit = new JButton("Exit");

        restart.setPreferredSize(new Dimension(80, 80));
        exit.setPreferredSize(new Dimension(80, 80));

        restart.setBackground(Color.GRAY);
        exit.setBackground(Color.GRAY);
        bottomPanel.add(exit, BorderLayout.LINE_END);
        bottomPanel.setBackground(Color.BLACK);

        restart.addActionListener(this);
        exit.addActionListener(this);

        bottomPanel.add(restart, BorderLayout.LINE_START);
        this.add(deathMessage);
        this.add(bottomPanel, BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == exit) {

            isExitPressed = true;
        } else if (e.getSource() == restart ){

            isRestartPressed = true;
        }
        else {
            isNextLevelPressed = true;
        }
    }

    public boolean isExitPressed() {
        return isExitPressed;
    }

    public boolean isRestartPressed() {
        return isRestartPressed;
    }

    public boolean isNextLevelPressed() {
        return isNextLevelPressed;
    }

    public void nextLevel() {
        JLabel levelPassed = new JLabel("You passed level 1!");
        levelPassed.setForeground(Color.RED);
        levelPassed.setFont(new Font("Calibri", Font.PLAIN, 40));
        levelPassed.setBounds(0, 0, 200, 100);
        levelPassed.setHorizontalAlignment(SwingConstants.CENTER);
        levelPassed.setVerticalAlignment(SwingConstants.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());

        nextLevelButton = new JButton("Go to next level");
        nextLevelButton.setPreferredSize(new Dimension(150, 80));
        nextLevelButton.addActionListener(this);


        exit = new JButton("Exit");
        exit.setPreferredSize(new Dimension(150, 80));
        exit.addActionListener(this);

        bottomPanel.add(nextLevelButton, BorderLayout.LINE_END);
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(exit, BorderLayout.LINE_START);

        this.add(bottomPanel, BorderLayout.PAGE_END);
        this.add(levelPassed);
    }

    public void winnerPanel() {
        JLabel winnerMsg = new JLabel("Congratulations! You won!");
        winnerMsg.setForeground(Color.RED);
        winnerMsg.setFont(new Font("Calibri", Font.PLAIN, 40));
        winnerMsg.setBounds(0, 0, 200, 100);
        winnerMsg.setHorizontalAlignment(SwingConstants.CENTER);
        winnerMsg.setVerticalAlignment(SwingConstants.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());

        restart = new JButton("Restart");
        exit = new JButton("Exit");

        restart.setPreferredSize(new Dimension(80, 80));
        exit.setPreferredSize(new Dimension(80, 80));

        restart.setBackground(Color.GRAY);
        exit.setBackground(Color.GRAY);
        bottomPanel.add(exit, BorderLayout.LINE_END);
        bottomPanel.setBackground(Color.BLACK);

        restart.addActionListener(this);
        exit.addActionListener(this);

        bottomPanel.add(restart, BorderLayout.LINE_START);
        this.add(winnerMsg);
        this.add(bottomPanel, BorderLayout.PAGE_END);
    }
}
