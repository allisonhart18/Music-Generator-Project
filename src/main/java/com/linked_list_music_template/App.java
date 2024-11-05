/*
 * 10/29
 * Allison Hart
 * manages MIDI melodies using a linked list structure. It sets up the interface with a play button 
 * to control melody playback, while handling mouse events 
 */

 package com.linked_list_music_template;

 import processing.core.PApplet;
 import java.util.ArrayList;
 import java.nio.file.FileSystem;
 import java.nio.file.FileSystems;
 
 public class App extends PApplet {
     static FileSystem sys = FileSystems.getDefault();
     static String prependPath = "mid" + sys.getSeparator();
     static String appendType = ".mid" + sys.getSeparator();
 
     ArrayList<OnMousePress> presses = new ArrayList<>();
     ArrayList<Drawable> draws = new ArrayList<>();
 
     TreeMelodyManager manager = new TreeMelodyManager();
     LinkedListMelody melody = new LinkedListMelody(manager);
     TreeMelody treeMelody = new TreeMelody(manager); // Initialize TreeMelody object
 
     public static void main(String[] args) 
     {
         PApplet.main("com.linked_list_music_template.App");
     }
 
        // Method to set the window size and initialize components
     public void settings() 
     {
         size(500, 500);
         manager.setup();
         setupButtons();
         addMelodyDraw();
         manager.print();
         melody.print();
     }

     // Method to load and add MIDI files to the manager
     void setupManager() {
        manager.setup();
        manager.addMidiFile(prependPath + "BachInvention.mid");
        manager.addMidiFile(prependPath + "HarpMidi.mid");
    }
    

 
     public void addMelodyDraw() 
     {
         draws.add(melody);
         draws.add(manager);
     }
 
     public void setupButtons() 
     {
         float centerX = width / 2;
         float centerY = height / 4; 
         float spacer = 50;
 
            // Create and add a Play button
         PlayButton play = new PlayButton(this, melody, "Play", centerX, centerY) 
         {
             public void onPress() 
             {
                 melody.start();
             }
         };
         draws.add(play);
         presses.add(play);
 
         StopButton stop = new StopButton(this, melody, centerX, centerY + spacer);
         draws.add(stop);
         presses.add(stop);
 
         LoopButton loop = new LoopButton(this, melody, centerX, centerY + 2 * spacer);
         draws.add(loop);
         presses.add(loop);
 
         PrintMelodyButton printMelody = new PrintMelodyButton(this, melody, centerX, centerY + 3 * spacer);
         draws.add(printMelody);
         presses.add(printMelody);
 
         RetrainMelodyButton retrainMelody = new RetrainMelodyButton(this, melody, treeMelody, centerX, centerY + 4 * spacer);
         draws.add(retrainMelody);
         presses.add(retrainMelody);
 
         RetrainMelodyAtZeroButton retrainMelodyAtZero = new RetrainMelodyAtZeroButton(this, melody, treeMelody, centerX, centerY + 5 * spacer);
         draws.add(retrainMelodyAtZero);
         presses.add(retrainMelodyAtZero);
 
         ClearMelodyButton clearMelody = new ClearMelodyButton(this, melody, centerX, centerY + 6 * spacer);
         draws.add(clearMelody);
         presses.add(clearMelody);
 
         TestMelodyTreeButton testMelodyTree = new TestMelodyTreeButton(this, melody, treeMelody, centerX, centerY + 7 * spacer);
         draws.add(testMelodyTree);
         presses.add(testMelodyTree);
     }
 
     public void setup() 
     {
         background(0);
     }
 
     public void draw() 
     {
         for (Drawable drawer : draws) 
         {
             drawer.draw();
         }
     }
 
     public void mousePressed() 
     {
         for (OnMousePress press : presses) 
         {
             press.mousePressed(mouseX, mouseY);
         }
     }
 }