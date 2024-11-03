/*
 * c2024 - Allison Hart - using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: LinkedListMelody class manages a linked list of MelodyNode instances,
 *  allowing for the insertion of new nodes, sequential playback of melodies, and the weaving 
 * of nodes into the list at specified intervals. It also provides methods for starting playback,
 *  printing the melody values, and traversing the linked list.
 * 
 */ 

 package com.linked_list_music_template;
 public class LinkedListMelody implements Drawable {
    protected MelodyNode head;
    private MelodyNode curPlayingNode = null;
    private boolean loopEnabled = false;

    public void insertAtEnd(MelodyNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            MelodyNode temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
    }

    public void loop(boolean enable) {
        loopEnabled = enable;
    }

    public void draw() {
        play();
    }

    public void start() {
        if (head != null) {
            curPlayingNode = head;
            curPlayingNode.start();
        }
    }

    public void play() {
        if (curPlayingNode != null && curPlayingNode.atEnd()) {
            curPlayingNode = curPlayingNode.getNext() != null ? curPlayingNode.getNext() : (loopEnabled ? head : null);
            if (curPlayingNode != null) curPlayingNode.start();
        }
    }

    public void stop() {
        curPlayingNode = null;
        System.out.println("Playback stopped.");
    }

    public void weave(MelodyNode node, int count, int skip) {
        MelodyNode temp = head;
        int inserts = 0;

        while (temp != null && inserts < count) {
            for (int i = 0; i < skip && temp.getNext() != null; i++) {
                temp = temp.getNext();
            }

            MelodyNode copyNode = new MelodyNode(node.getManager(), node.getMelodyValue());
            copyNode.setNext(temp.getNext());
            temp.setNext(copyNode);

            temp = copyNode.getNext();
            inserts++;
        }
    }
    public void clear() {
        head = null;
        System.out.println("Melody cleared.");
    }

    //@Override
    public void print() {
        MelodyNode temp = head;
        StringBuilder melodyOutput = new StringBuilder("Melody: ");
        while (temp != null) {
            melodyOutput.append(temp.getMelodyValue()).append(", ");
            temp = temp.getNext();
        }
        if (melodyOutput.length() > 0) {
            melodyOutput.setLength(melodyOutput.length() - 2);
        }
        System.out.println(melodyOutput);
    }
}
