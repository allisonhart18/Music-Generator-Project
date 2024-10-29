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
    static String appendType = ".mid";  // Removed extra separator


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
        LinkedListMelody melody = new LinkedListMelody(); // Ensure melody is defined
        for(int i = 0; i < manager.size(); i++) {
            melody.insertAtEnd(new MelodyNode(manager, i)); // Add nodes to the list
        }
    }
    
      


    public void addMelodyDraw() {
        draws.add(melody);
        draws.add(manager);
    }


    public void setupButtons() {
        float centerX = width / 2;
        float centerY = height / 2;
    
        // Create an instance of MelodyButton instead of PlayButton
        MelodyButton playButton = new MelodyButton(this, melody, "Play", centerX, centerY) {
            @Override
            public void onPress() {
                melody.start(); // Call the start method to play the melody
            }
        };
        
    
        draws.add(playButton);  // Add to the drawable list
        presses.add(playButton); // Add to the mouse press list
    }
    


    public void setup() {
        background(0);
    }

    public void draw() {
        background(0); // Clear the background each frame
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
