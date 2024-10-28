/*
 * c2024  [your name] using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: [you fille in]
 * 
 */ package com.linked_list_music_template;

public class LinkedListMelody implements Drawable {
    private MelodyNode head;
    private MelodyNode curPlayingNode;

    // Start playing the melody from the head
    public void start() {
        if (head != null) {
            curPlayingNode = head; // Reset to the head
            curPlayingNode.start(); // Start playing the melody
            System.out.println("Started playing melody index: " + curPlayingNode.getMelodyIndex());
        }
    }

    public void insertAtStart(MelodyNode node) {
        node.setNext(head);
        head = node;
    }

    public void insertAtEnd(MelodyNode node) {
        if (head == null) {
            head = node;
        } else {
            MelodyNode temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(node);
        }
    }

    public void insert(int index, MelodyNode node) {
        if (index == 0) {
            insertAtStart(node);
        } else {
            MelodyNode temp = head;
            for (int i = 0; i < index - 1 && temp != null; i++) {
                temp = temp.getNext();
            }
            if (temp != null) {
                node.setNext(temp.getNext());
                temp.setNext(node);
            }
        }
    }

    public void print() {
        MelodyNode temp = head;
        System.out.print("Melody: ");
        while (temp != null) {
            System.out.print(temp.getMelodyIndex() + (temp.getNext() != null ? ", " : ""));
            temp = temp.getNext();
        }
        System.out.println();
    }

    public void loop(boolean loop_) {
        // Implement looping logic
    }

    public void stop() {
        if (curPlayingNode != null) {
            curPlayingNode.stop(); // Assuming stop method exists in MelodyNode
            curPlayingNode = null;
        }
    }

    public void weave(MelodyNode node, int count, int skip) {
        MelodyNode temp = head;
        int inserted = 0;
        int nodesTraversed = 0;

        while (temp != null && inserted < count) {
            if (nodesTraversed == skip) {
                MelodyNode copyNode = node.copy();
                copyNode.setNext(temp.getNext());
                temp.setNext(copyNode);
                inserted++;
                nodesTraversed = 0;
                temp = copyNode.getNext();
            } else {
                nodesTraversed++;
                temp = temp.getNext();
            }
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
    }

    public void reverse() {
        MelodyNode prev = null;
        MelodyNode current = head;
        MelodyNode next;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    @Override
    public void draw() {
        play();
    }

    public void play() {
        if (curPlayingNode != null) {
            curPlayingNode.start(); // Play the current node
            System.out.println("Playing melody index: " + curPlayingNode.getMelodyIndex());
        }
    }
}
