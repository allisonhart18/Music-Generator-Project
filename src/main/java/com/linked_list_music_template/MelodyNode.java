/*
 * Allison Hart - 10/29/2024
 * 
 *  MelodyNode class represents a node in a linked list of melodies,
 *  where each node stores a melody identifier (whichMelody) and a reference 
 * to the LinkedListMelodyManager responsible for handling playback. It includes methods to 
 * start the melody, check if it has reached its end, and access the next node in the sequence.
 * 
 */

 package com.linked_list_music_template;

 // Node class for the melody linked list
 public class MelodyNode {
     private MelodyManager melodies; // Generalized manager for the melody
     private int whichMelody;        // Value representing the melody
     private MelodyNode next;        // Reference to the next node in the list
 
     // Constructor for MelodyNode
     public MelodyNode(MelodyManager melodies, int whichMelody) {
         this.melodies = melodies;
         this.whichMelody = whichMelody;
     }
 
     // Returns the MelodyManager associated with this node
     public MelodyManager getManager() {
         return melodies;
     }
 
     // Returns the melody value of this node
     public int getMelodyValue() {
         return whichMelody;
     }
 
     // Getter for the next node
     public MelodyNode getNext() {
         return next;
     }
 
     // Setter for the next node
     public void setNext(MelodyNode next) {
         this.next = next;
     }
 
     // Starts the melody in the MelodyManager
     public void start() {
         melodies.start(whichMelody);
     }
 
     // Checks if the melody is at the end
     public boolean atEnd() {
         return melodies.atEnd(whichMelody);
     }
 }
 