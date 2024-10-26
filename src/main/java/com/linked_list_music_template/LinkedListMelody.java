/*
 * c2024  [your name] using a template by Dr. Courtney Brown
 * Class: LinkedListMelody
 * Description: [you fille in]
 * 
 */

 package com.linked_list_music_template;

 public class LinkedListMelody implements Drawable {
     private MelodyNode head;
     private MelodyNode curPlayingNode = null;
 
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
 
     public void draw() {
         play();
     }
 
     public void start() {
        if (head != null) {
            curPlayingNode = head;
            curPlayingNode.start();
            play(); // Directly call play after starting
        }
    }
    
 
     public void play() {
         if (curPlayingNode != null && curPlayingNode.atEnd()) {
             MelodyNode next = curPlayingNode.getNext();
             if (next != null) {
                 curPlayingNode = next;
                 curPlayingNode.start();
             }
         }
     }
 }