/*
 * 10/29
 * Allison Hart
 * MelodyButton class is an abstract class of a button that
 *  controls a melody, allowing it to be extended by specific button types, 
 * such as the PlayButton. The PlayButton class  implements the 
 * functionality to start playing a melody when pressed
 */



package com.linked_list_music_template;

import processing.core.PApplet;

// Abstract class representing a button that controls melodies
public abstract class MelodyButton extends Button {
    
    // LinkedListMelody instance associated with this button
    LinkedListMelody melody;

    // Constructor for MelodyButton, allows default button sizes (150 x 25)
    MelodyButton(PApplet main_, LinkedListMelody melody_, String label_, float x_, float y_) {
        // Call to the superclass constructor to initialize the button with its label and position
        super(main_, label_, x_, y_);
        melody = melody_; // Store the reference to the melody
    }
}

// Class representing a specific Play button that extends MelodyButton
class PlayButton extends MelodyButton {

    // Constructor for PlayButton, sets up the button with "Play" label
    PlayButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, melody_, "Play", x_, y_); // Initialize the MelodyButton with label "Play"
    }

    // Method to execute when the Play button is pressed
    public void onPress() {
        melody.start(); // Start playing the melody associated with this button
    }
}
