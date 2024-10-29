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
     private MelodyNode head;
     private MelodyNode curPlayingNode = null;
 
     // Insert a new melody node at the end of the list
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
 
     // Draw method for initiating playback
     public void draw() {
         play();
     }
 
     // Start playback from the head of the list
     public void start() {
         if (head != null) {
             curPlayingNode = head;
             curPlayingNode.start();
             play(); // Directly call play after starting
         }
     }
 
     // Play each node sequentially
     public void play() {
         if (curPlayingNode != null && curPlayingNode.atEnd()) {
             MelodyNode next = curPlayingNode.getNext();
             if (next != null) {
                 curPlayingNode = next;
                 curPlayingNode.start();
             }
         }
     }
 
     /*Explanation for comment below :)
      *comment explains the method's functionality is to "weave" (insert) a given node into an 
      existing melody list at specified intervals.

@param node: This parameter represents the specific MelodyNode that you want to insert into the melody list.
@param count: This indicates how many times the node should be inserted into the list.
@param skip: This specifies the interval (or number of nodes to skip) between each insertion of the node.

      */
     /**
      * Weave a specified node into the melody list at intervals.
      * @param node The MelodyNode to weave into the list.
      * @param count The number of times to insert the node.
      * @param skip The interval between insertions.
      */
     public void weave(MelodyNode node, int count, int skip) {
         MelodyNode temp = head;
         int index = 0;
         int inserts = 0;
 
         // Traverse the list and insert the node at intervals
         while (temp != null && inserts < count) {
             // Move 'skip' steps forward in the list
             for (int i = 0; i < skip && temp.getNext() != null; i++) {
                 temp = temp.getNext();
                 index++;
             }
 
             // Insert a copy of the node at the current position
             MelodyNode copyNode = new MelodyNode(node.getManager(), node.getMelodyValue());
             copyNode.setNext(temp.getNext());
             temp.setNext(copyNode);
 
             // Advance temp to the new node and increment inserts
             temp = copyNode.getNext();
             inserts++;
         }
     }
 
     // Print method to display the melody list's content
     public void print() {
         MelodyNode temp = head;
         StringBuilder melodyOutput = new StringBuilder("Melody: ");
         while (temp != null) {
             melodyOutput.append(temp.getMelodyValue()).append(", ");
             temp = temp.getNext();
         }
         if (melodyOutput.length() > 0) {
             melodyOutput.setLength(melodyOutput.length() - 2); // Remove trailing comma
         }
         System.out.println(melodyOutput.toString());
     }
 }
 