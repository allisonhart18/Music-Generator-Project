/*
 * c2024  [your name] using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: [you fille in]
 * 
 */ package com.linked_list_music_template;
 public class LinkedListMelody implements Drawable {
    private MelodyNode head;
    private MelodyNode curPlayingNode;

    public void insertAtEnd(MelodyNode newNode) {
        if (head == null) {
            head = newNode;
        } else {
            MelodyNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void start() {
        curPlayingNode = head;
        if (curPlayingNode != null) {
            curPlayingNode.start();
        }
    }

    public void play() {
        if (curPlayingNode != null && curPlayingNode.atEnd()) {
            curPlayingNode = curPlayingNode.getNext();
            if (curPlayingNode == null) {
                curPlayingNode = head;  // Loop back to the beginning
            }
            curPlayingNode.start();
        }
    }

    @Override
    public void draw() {
        play();
    }
}