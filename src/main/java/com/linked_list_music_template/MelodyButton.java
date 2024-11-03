/*
 * 10/29
 * Allison Hart
 * MelodyButton class is an abstract class of a button that
 *  controls a melody, allowing it to be extended by specific button types, 
 * such as the PlayButton. The PlayButton class  implements the 
 * functionality to start playing a melody when pressed
 */


 package com.linked_list_music_template;

 import processing.core.*;
 
 public abstract class MelodyButton extends Button {
     LinkedListMelody melody;
     boolean changeLoop;
 
     // Constructor
     MelodyButton(PApplet main_, LinkedListMelody melody_, String label_, float x_, float y_) {
         super(main_, label_, x_, y_);
         this.melody = melody_;
         this.changeLoop = false;
     }
 }
 
 class PlayButton extends MelodyButton {
     PlayButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
         super(main_, melody_, "Play", x_, y_);
     }
 
     @Override
     public void onPress() {
         if (melody != null) {
             melody.start();
         }
     }
 }
 
 class StopButton extends MelodyButton {
     StopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
         super(main_, melody_, "Stop", x_, y_);
     }
 
     @Override
     public void onPress() {
         if (melody != null) {
             melody.stop();
         }
     }
 }
 
 class LoopButton extends MelodyButton {
     LoopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
         super(main_, melody_, "Loop", x_, y_);
     }
 
     @Override
     public void onPress() {
         changeLoop = !changeLoop;
         if (melody != null) {
             melody.loop(changeLoop);
             melody.play();
         }
     }
 }
 
 class WeaveButton extends MelodyButton {
     private int option;
 
     WeaveButton(PApplet main_, LinkedListMelody melody_, int option_, float x_, float y_) {
         super(main_, melody_, "Weave Option " + option_, x_, y_);
         this.option = option_;
     }
 
     @Override
     public void onPress() {
         if (melody != null) {
             MelodyNode node = new MelodyNode(null, 0); // Adjust as needed
             switch (option) {
                 case 1:
                     melody.weave(node, 3, 4);
                     break;
                 case 2:
                     melody.weave(node, 5, 2);
                     break;
                 case 3:
                     melody.weave(node, 4, 6);
                     break;
                 default:
                     System.out.println("Invalid weave option.");
             }
             System.out.println("Weave option " + option + " applied.");
         }
     }
 }
 
 class ClearList extends MelodyButton {
     ClearList(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
         super(main_, melody_, "Clear List", x_, y_);
     }
     
 
     @Override
     public void onPress() {
         if (melody != null) {
             melody.clear();
             System.out.println("Melody list cleared!");
         }
     }
 }
 
 class UnitTestButton extends MelodyButton {
     UnitTestButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
         super(main_, melody_, "Run Tests", x_, y_);
     }
 
     @Override
     public void onPress() {
         System.out.println("Running unit tests...");
         // Add test logic here if needed
         System.out.println("Unit tests completed.");
     }
 }
 