/*
 * c2024  [your name] using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: [you fille in]
 * 
 */ package com.linked_list_music_template;

public class LinkedListMelody implements Drawable {
    private MelodyNode head; // The head of the linked list
    private MelodyNode curPlayingNode; // The currently playing melody
    private int size; // Keep track of the number of nodes

    public LinkedListMelody() {
        this.head = null; // Initialize head to null
        this.size = 0; // Initialize size to zero
    }

    // Method to insert a new MelodyNode at the end of the linked list
    public void insertAtEnd(MelodyNode newNode) {
        if (head == null) {
            head = newNode; // If the list is empty, set head to the new node
        } else {
            MelodyNode current = head; // Start from the head
            while (current.getNext() != null) { // Traverse to the end of the list
                current = current.getNext();
            }
            current.setNext(newNode); // Link the new node at the end
        }
        size++; // Increment the size of the list
    }

    public int size() {
        return size; // Return the current size of the list
    }

    // Start playing the melody from the head
    public void start() {
        if (head != null) {
            curPlayingNode = head; // Reset to the head
            curPlayingNode.start(); // Start playing the melody
            System.out.println("Started playing melody index: " + curPlayingNode.getMelodyIndex());
        }
    }

    public void play() {
        if (curPlayingNode != null) {
            curPlayingNode.start(); // Play the current node
            System.out.println("Playing melody index: " + curPlayingNode.getMelodyIndex());

            // Check if the current melody has ended
            if (curPlayingNode.atEnd()) {
                // Move to the next node
                curPlayingNode = curPlayingNode.getNext();
                if (curPlayingNode == null) {
                    // If reached the end, loop back to the head
                    curPlayingNode = head;
                }
                // Start the next melody
                if (curPlayingNode != null) {
                    curPlayingNode.start();
                }
            }
        }
    }

    @Override
    public void draw() {
        play(); // Call play each frame to ensure continuous playing
    }
}
