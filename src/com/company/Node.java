package com.company;

public class Node {

    public Node parent;
    public boolean visited;
    public int row, col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.parent = null;
        this.visited = false;
    }

    public void setParent(Node node) {

        parent = node;
    }
}
