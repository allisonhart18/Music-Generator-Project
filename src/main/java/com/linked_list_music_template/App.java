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
     static FileSystem sys = FileSystems.getDefault();
     static String prependPath = "mid" + sys.getSeparator();
     static String appendType = ".mid";  
 
     ArrayList<OnMousePress> presses = new ArrayList<>();
     ArrayList<Drawable> draws = new ArrayList<>();
 
     TreeMelodyManager treeManager = new TreeMelodyManager(); // TreeMelodyManager instance
     TreeMelody treeMelody = new TreeMelody(treeManager); // TreeMelodyList instance
 
     public static void main(String[] args) {
         PApplet.main("com.linked_list_music_template.App");      
     }
 
     public void settings() {
         size(500, 500); 
         setupManager(); // Set up the melody manager and load MIDI files
         setupTreeMelody(); // Generate motives for testing
         setupButtons(); 
         addMelodyDraw(); 
     }
 
     // Set up the TreeMelodyManager and add MIDI files
     void setupManager() {
         treeManager.setup();
         // Add your MIDI files here
         treeManager.addMidiFile(prependPath + "BachInvention.mid");
         treeManager.addMidiFile(prependPath + "AnotherMIDIFile.mid");
     }
 
     // Generate motives and train the tree melody
     void setupTreeMelody() {
         int motiveNoteCount = 4; // Set the number of notes for each motive
         treeMelody.train(-1, motiveNoteCount); // Train the tree with random index and given motive count
     }
 
     public void addMelodyDraw() {
         draws.add(treeMelody); // Add the tree melody to the draw list
         draws.add(treeManager); // Add the manager to the draw list
     }
 
     public void setupButtons() {
         float centerX = width / 2;
         float centerY = height / 2;
         float spacer = 45; 
 
         PlayButton play = new PlayButton(this, treeMelody, centerX + 1, centerY);
         draws.add(play);
         presses.add(play);
 
         StopButton stop = new StopButton(this, treeMelody, centerX, centerY + spacer);
         draws.add(stop);
         presses.add(stop);
 
         LoopButton loop = new LoopButton(this, treeMelody, centerX, centerY + 2 * spacer);
         draws.add(loop);
         presses.add(loop);
 
         ClearList clear = new ClearList(this, treeMelody, centerY, spacer * 2 + centerY + 40); 
         draws.add(clear);
         presses.add(clear);
 
         UnitTestButton test = new UnitTestButton(this, treeMelody, centerY, spacer);
         draws.add(test);
         presses.add(test);
     }
 
     public void setup() {
         background(0);
     }
 
     public void draw() {
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
 