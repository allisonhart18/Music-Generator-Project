/*
* c2017-2024 Courtney Brown 
* Class: Main Class for Hello World for CC3 Class Projects streaming MIDI, etc.
* Description: Demonstration of MIDI file manipulations, etc. & 'MelodyPlayer' sequencer, 2024 - add processing/interactivity
* Ahhhhhhhhh
*/
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

    // Instantiate Manager and Melody Linked List
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
        for(int i = 0; i < manager.size(); i++) {
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
        float spacer = 8;


        PlayButton play = new PlayButton(this, melody, centerX, centerY);
        draws.add(play);
        presses.add(play);
    }

    public void setup() {
        background(0);
    }

    public void draw() {
        for(Drawable drawer : draws) {
            drawer.draw();
        }
    }

    public void mousePressed() {
        for(OnMousePress press : presses) {
            press.mousePressed(mouseX, mouseY);
        }
    }
    
}