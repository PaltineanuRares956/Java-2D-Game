package com.company;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MediaPlayer implements Runnable {
    private String path;
    @Override
    public void run() {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            Player player = new Player(fileInputStream);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void setPath (String path) {
        this.path = path;
    }
}
