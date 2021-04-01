package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener, MouseListener {

    private int[][] maze;
    private int width;
    private int height;
    private int widthRect;
    private int heightRect;
    private GamePlayer player;

    public GamePanel() {

    }

    public GamePanel(int[][] maze, int width, int height, GamePlayer player) {

        this.maze = maze;
        this.width = width;
        this.height = height;
        this.player = player;
        widthRect = width / 11;
        heightRect = height / 11;
    }

    public void setMaze(int[][] maze) {

        this.maze = maze;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, width, height);
        drawMaze(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) { //a
            Enemy.playerHasMoved = true;
            player.setDirection(Direction.LEFT);
            player.move(0, -1);
        } else if (key == KeyEvent.VK_D) { //d
            Enemy.playerHasMoved = true;
            player.setDirection(Direction.RIGHT);
            player.move(0, 1);
        } else if (key == KeyEvent.VK_S) { //s
            Enemy.playerHasMoved = true;
            player.setDirection(Direction.DOWN);
            player.move(1, 0);
        } else if (key == KeyEvent.VK_W) { //w
            Enemy.playerHasMoved = true;
            player.setDirection(Direction.UP);
            player.move(-1, 0);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        player.attack();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void drawMaze (Graphics2D g2d) {
        for (int i = player.getRow() - 5, indexRow = 0; i < player.getRow() + 5; i++, indexRow++) {
            for (int j = player.getCol() - 5, indexCol = 0; j < player.getCol() + 5; j++, indexCol++) {
                if (maze[i][j] == 1) { //wall

                    g2d.setColor(Color.RED);
                    g2d.fillRect(indexCol * widthRect, indexRow * heightRect, widthRect, heightRect);
                    //try {
//
                    //    //Image up = ImageIO.read(new File("D:/Java Projects/SwordsAndJava/src/com/company/PlayerUp.PNG"));
                    //    Image up = ImageIO.read(new File("Wall.JPG"));
                    //    g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);
                    //}
                    // catch (IOException e) {
//
                    //     e.printStackTrace();
                    // }
//
                } else if (maze[i][j] == 2) { //player

                    JLabel playerName = new JLabel(player.getName());
                    playerName.setBounds(300, 285, 65, 30);
                    playerName.setHorizontalAlignment(SwingConstants.CENTER);
                    playerName.setForeground(Color.GREEN);

                    if (player.getHp() > 0) {
                        this.add(playerName);
                    }

                    switch (player.getDirection()) {

                        case UP:
                            try {

                                Image up = ImageIO.read(new File("PlayerUp.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                        case DOWN:
                            try {

                                Image up = ImageIO.read(new File("PlayerDown.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                        case LEFT:
                            try {

                                Image up = ImageIO.read(new File("PlayerLeft.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                        case RIGHT:
                            try {

                                Image up = ImageIO.read(new File("PlayerRight.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                    }

                } else if (maze[i][j] == 3) { //enemy

                    switch (player.getEnemy(i, j).getDirection()) {

                        case UP:
                            try {

                                Image up = ImageIO.read(new File("EnemyUp.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                        case DOWN:
                            try {

                                Image up = ImageIO.read(new File("EnemyDown.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                        case LEFT:
                            try {

                                Image up = ImageIO.read(new File("EnemyLeft.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                        case RIGHT:
                            try {

                                Image up = ImageIO.read(new File("EnemyRight.PNG"));
                                g2d.drawImage(up, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                            break;

                    }
                } else if(maze[i][j] == 4) { //coin

                    try {

                        Image coin = ImageIO.read(new File("Coin.PNG"));
                        g2d.drawImage(coin, indexCol * widthRect, indexRow * heightRect, widthRect, heightRect, this);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        }
    }
}