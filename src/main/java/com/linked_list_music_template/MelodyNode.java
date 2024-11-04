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


 public class MelodyNode {
    private TreeMelodyManager melodies;
    private int whichMelody;
    private MelodyNode next;

    public MelodyNode(TreeMelodyManager melodies, int whichMelody) {
        this.melodies = melodies;
        this.whichMelody = whichMelody;
    }

    public TreeMelodyManager getManager() {
        return melodies;
    }

    public int getMelodyValue() {
        return whichMelody;
    }

    public MelodyNode getNext() {
        return next;
    }

    public void setNext(MelodyNode next) {
        this.next = next;
    }

    public void start() {
        melodies.start(whichMelody);
    }

    public boolean atEnd() {
        return melodies.atEnd(whichMelody);
    }
}