/*
 * 10/29
 * Allison Hart
 * manages MIDI melodies using a linked list structure. It sets up the interface with a play button 
 * to control melody playback, while handling mouse events 
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
    // File system object for managing file paths
    static FileSystem sys = FileSystems.getDefault();
    // Path prefix for MIDI files
    static String prependPath = "mid" + sys.getSeparator();
    // MIDI file extension
    static String appendType = ".mid";  // Removed extra separator

    // Lists for handling mouse presses and drawable objects
    ArrayList<OnMousePress> presses = new ArrayList<>();
    ArrayList<Drawable> draws = new ArrayList<>();

    // Instantiate Manager and Melody Linked List
    LinkedListMelodyManager manager = new LinkedListMelodyManager();
    LinkedListMelody melody = new LinkedListMelody();

    // Main method to launch the application
    public static void main(String[] args) {
        PApplet.main("com.linked_list_music_template.App");      
    }

    // Set up the application settings, including window size and initial setup
    public void settings() {
        size(500, 500); // Set the size of the application window
        manager.setup(); // Set up the melody manager (load MIDI files)
        addNodes(); // Add melody nodes to the linked list
        setupButtons(); // Create and set up buttons for user interaction
        addMelodyDraw(); // Add drawable elements to the draw list
    }

    // Add melody nodes to the linked list based on the manager's size
    void addNodes() {
        for(int i = 0; i < manager.size(); i++) {
            melody.insertAtEnd(new MelodyNode(manager, i)); // Insert new MelodyNodes
        }
    }

    // Add drawable elements to the draws list
    public void addMelodyDraw() {
        draws.add(melody); // Add the melody to the draw list
        draws.add(manager); // Add the manager to the draw list
    }

    // Set up the buttons for the application interface
    public void setupButtons() {
        float centerX = width / 2; // Calculate center x position
        float centerY = height / 2; // Calculate center y position
        float spacer = 8; 

        // Create a play button and add it to the presses and draws lists
        PlayButton play = new PlayButton(this, melody, centerX, centerY);
        draws.add(play); // Add play button to the drawable list
        presses.add(play); // Add play button to the pressable list
    }

    // Set the initial background for the application
    public void setup() {
        background(0); // Set background color to black
    }

    // Main draw loop to render drawable elements
    public void draw() {
        for(Drawable drawer : draws) {
            drawer.draw(); // Call the draw method for each drawable element
        }
    }

    // Handle mouse press events
    public void mousePressed() {
        for(OnMousePress press : presses) {
            press.mousePressed(mouseX, mouseY); // Call mousePressed on each pressable element
        }
    }
}
