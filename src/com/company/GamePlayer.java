package com.company;

public class GamePlayer {

    private int hp = 100;
    private String name;
    private int row;
    private int col;
    private int[][] maze;
    private Direction direction = Direction.RIGHT;

    public GamePlayer(String name, int row, int col, int[][] maze) {

        this.name = name;
        this.row = row;
        this.col = col;
        this.maze = maze;
        this.maze[row][col] = 2;
    }

    public void move(int r, int c) {

        if(row + r < 0 || row + r >= maze.length)
            return;

        if(col + c < 0 || col + c >= maze[0].length)
            return;

        if(maze[row + r][col + c] == 0 || maze[row + r][col + c] == 4) {

            if(maze[row + r][col + c] == 4)
                Game.setNrOfCoins(Game.getNrOfCoins() - 1);
            maze[row][col] = 0;
            row += r;
            col += c;
            maze[row][col] = 2;
        }
    }

    public Enemy getEnemy(int row, int col) {
        for (Enemy enemy: Game.enemies) {
            if (enemy.getRow() == row && enemy.getCol() == col) {
                return enemy;
            }
        }

        return null;

    }

    public void playSound(String path) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setPath(path);
        Thread thread = new Thread(mediaPlayer);
        thread.start();
    }

    public void attack() {

        if (direction == Direction.DOWN) {
            if (maze[row + 1][col] == 3) {
                Enemy currentEnemy = getEnemy(row + 1, col);

                if (currentEnemy != null) {
                    currentEnemy.getHit();
                    if (currentEnemy.getHp() <= 0) {
                        maze[currentEnemy.getRow()][currentEnemy.getCol()] = 0;
                        Game.enemies.remove(currentEnemy);
                        Game.setEnemiesLeft();
                        playSound("killEnemy.mp3");
                    }
                    else
                    {
                        playSound("hit.mp3");
                    }
                }


            }
            else if (maze[row + 1][col] == 1) {
                playSound("hitWall.mp3");
            }
            else if (maze[row + 1][col] == 0) {
                playSound("voidHit.mp3");
            }
        }
        else if (direction == Direction.UP) {
            if (maze[row - 1][col] == 3) {
                Enemy currentEnemy = getEnemy(row - 1, col);

                if (currentEnemy != null) {
                    currentEnemy.getHit();
                    if (currentEnemy.getHp() <= 0) {
                        maze[currentEnemy.getRow()][currentEnemy.getCol()] = 0;
                        Game.enemies.remove(currentEnemy);
                        Game.setEnemiesLeft();
                        playSound("killEnemy.mp3");
                    }
                    else
                    {
                        playSound("hit.mp3");
                    }
                }

            } else if (maze[row - 1][col] == 1) {
                playSound("hitWall.mp3");
            }
            else if (maze[row - 1][col] == 0) {
                playSound("voidHit.mp3");
            }
        }
        else if (direction == Direction.LEFT) {
            if (maze[row][col - 1] == 3) {
                Enemy currentEnemy = getEnemy(row, col - 1);

                if (currentEnemy != null) {
                    currentEnemy.getHit();
                    if (currentEnemy.getHp() <= 0) {
                        maze[currentEnemy.getRow()][currentEnemy.getCol()] = 0;
                        Game.enemies.remove(currentEnemy);
                        Game.setEnemiesLeft();
                        playSound("killEnemy.mp3");
                    }
                    else
                    {
                        playSound("hit.mp3");
                    }
                }
            }
            else if (maze[row][col - 1] == 1) {
                playSound("hitWall.mp3");
            }
            else if (maze[row][col - 1] == 0) {
                playSound("voidHit.mp3");
            }
        }
        else if (direction == Direction.RIGHT) {
            if (maze[row][col + 1] == 3) {
                Enemy currentEnemy = getEnemy(row, col + 1);

                if (currentEnemy != null) {
                    currentEnemy.getHit();
                    if (currentEnemy.getHp() <= 0) {
                        maze[currentEnemy.getRow()][currentEnemy.getCol()] = 0;
                        Game.enemies.remove(currentEnemy);
                        Game.setEnemiesLeft();
                        playSound("killEnemy.mp3");
                    }
                    else
                    {
                        playSound("hit.mp3");
                    }
                }
            }
            else if (maze[row][col + 1] == 1) {
                playSound("hitWall.mp3");
            }
            else if (maze[row][col + 1] == 0) {
                playSound("voidHit.mp3");
            }

        }

    }

    public void getHit() {

        hp -= 15;
        Game.setHp(String.valueOf(hp));

        if (hp <= 0) {
            playSound("playerDead.mp3");
        }
        else {
            playSound("hit.mp3");
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
