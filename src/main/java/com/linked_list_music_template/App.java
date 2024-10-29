package com.linked_list_music_template;


import jm.music.data.*;
import jm.util.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import processing.core.*;

// Main Application Class
public class App extends PApplet {
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid";

    ArrayList<OnMousePress> presses = new ArrayList<>();
    ArrayList<Drawable> draws = new ArrayList<>();
    LinkedListMelodyManager manager = new LinkedListMelodyManager();
    LinkedListMelody melody = new LinkedListMelody();

    public static void main(String[] args) {
        PApplet.main("com.linked_list_music_template.App");
    }

    public void settings() {
        size(500, 500);
        manager.setup();
        addNodes();
        setupButtons();
        addMelodyDraw();
    }

    void addNodes() {
        for (int i = 0; i < manager.size(); i++) {
            melody.insertAtEnd(new MelodyNode(manager, i));
        }
    }

    public void addMelodyDraw() {
        draws.add(melody);
        draws.add(manager);
    }

    public void setupButtons() {
        float centerX = width / 2;
        float centerY = height / 2;
        MelodyButton playButton = new MelodyButton(this, melody, "Play", centerX, centerY) {
            @Override
            public void onPress() {
                melody.start(); // Start playing melodies
            }
        };
        draws.add(playButton);
        presses.add(playButton);
    }

    public void setup() {
        background(0);
    }

    public void draw() {
        background(0);
        for (Drawable drawer : draws) {
            drawer.draw();
        }
    }

    public void mousePressed() {
        for (OnMousePress press : presses) {
            press.mousePressed(mouseX, mouseY);
        }
    }
}