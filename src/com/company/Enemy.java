package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class Enemy extends GamePanel {

    private int hp = 100;
    private int row;
    private int col;
    private boolean active;
    private int[][] maze;
    private Node[][] nodeMaze = new Node[100][100];
    private GamePlayer player;
    private Direction direction = Direction.RIGHT;
    private Stack<Node> stack;
    public static boolean playerHasMoved = true;

    public Enemy(int row, int col, int[][] maze, GamePlayer player) {

        this.row = row;
        this.col = col;
        this.maze = maze;
        maze[row][col] = 3;
        active = false;
        this.player = player;
        initNode();
    }

    public void initNode() {
        stack = new Stack<>();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                nodeMaze[i][j] = new Node(i, j);
            }
        }
    }

    public void isActive() {

        if (Math.abs(player.getRow() - row) <= 3 && Math.abs(player.getCol() - col) <= 3)
            active = true;

    }

    public void move() {
        isActive();
        if (!active) {
            Random rand = new Random();
            int row = rand.nextInt(4);
            boolean isValid = false;
            while (!isValid) {
                switch (row) {

                    case 0: //up
                        updatePos(-1, 0);
                        isValid = isAvailable(-1, 0);
                        break;

                    case 1: //down
                        updatePos(1, 0);
                        isValid = isAvailable(1, 0);

                        break;

                    case 2: //left
                        updatePos(0, -1);
                        isValid = isAvailable(0, -1);

                        break;

                    case 3: //right
                        updatePos(0, 1);
                        isValid = isAvailable(0, 1);
                        break;
                }
                row = rand.nextInt(4);
            }

        } else {

            initNode();
            Node start = nodeMaze[this.row][this.col];
            Node end = nodeMaze[player.getRow()][player.getCol()];
            shortestPath(start, end);

            if (!stack.isEmpty()) {
                Node node = stack.pop();
                int row = node.row - this.row;
                int col = node.col - this.col;
                updatePos(row, col);
            } else {
                updateDir(player.getRow() - this.row, player.getCol() - this.col);
                attack();
            }
        }
    }

    public void shortestPath(Node start, Node end) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};
        while (!queue.isEmpty()) {
            Node currNode = queue.poll();
            for (int i = 0; i < 4; i++) {
                if ((maze[currNode.row + dx[i]][currNode.col + dy[i]] == 0 || maze[currNode.row + dx[i]][currNode.col + dy[i]] == 2 || maze[currNode.row + dx[i]][currNode.col + dy[i]] == 3)
                        && !nodeMaze[currNode.row + dx[i]][currNode.col + dy[i]].visited) {
                    queue.add(nodeMaze[currNode.row + dx[i]][currNode.col + dy[i]]);
                    nodeMaze[currNode.row + dx[i]][currNode.col + dy[i]].visited = true;
                    nodeMaze[currNode.row + dx[i]][currNode.col + dy[i]].parent = currNode;
                }
            }
        }
        Node node = end.parent;
        while (node != null && node != start) {
            stack.push(node);
            node = node.parent;
        }
    }

    public void updateDir(int r, int c) {
        if (r == -1) {
            direction = Direction.UP;
        } else if (r == 1) {
            direction = Direction.DOWN;
        } else if (c == -1) {
            direction = Direction.LEFT;
        } else {
            direction = Direction.RIGHT;
        }
    }

    public boolean isAvailable(int r, int c) {
        return maze[row + r][col + c] == 0;
    }

    public void updatePos(int r, int c) {
        if (!isAvailable(r, c)) {
            return;
        }
        updateDir(r, c);
        maze[row][col] = 0;
        row += r;
        col += c;
        maze[row][col] = 3;
    }

    public void attack() {
        player.getHit();
    }

    public void getHit() {
        hp -= 34;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Direction getDirection() {
        return direction;
    }
}
