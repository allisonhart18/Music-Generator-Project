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

 //import java.util.ArrayList;

 public class LinkedListMelody implements Drawable 
 
 {
     private MelodyNode head;
     private MelodyNode curPlayNode = null;
     private boolean loopEnabled = false;
     private TreeMelodyManager manager;
 
     public LinkedListMelody(TreeMelodyManager manager)
      {
         this.manager = manager;
     }
 
     public TreeMelodyManager getManager() 
     {
         return manager;
     }
 
     public boolean isEmpty() 
     {
         return head == null;
     }


 // Inserts a node at a specific index in the list
     public void insert(int index, MelodyNode node) 
     {
         if (index == 0) 
         {
             insertAtStart(node);
         } else 
         {  // Traverse to the node before the insertion point
             MelodyNode current = head;
             for (int i = 0; i < index - 1 && current != null; i++) 
             {
                 current = current.getNext();
             }// Insert the node if the current node is not null
             if (current != null) 
             {
                 node.setNext(current.getNext());
                 current.setNext(node);
             }
         }
     }

 // Inserts a node at the start of the list
     public void insertAtStart(MelodyNode node) 
     {
         if (isEmpty()) 
         {
             head = node;
         } else 
         {
             node.setNext(head);
             head = node;
         }
     }
 
     public void insertAtEnd(MelodyNode node) 
     {
         if (isEmpty()) 
         {
             head = node;
         } else 
         {
             MelodyNode current = head;
             while (current.getNext() != null) 
             {
                 current = current.getNext();
             }
             current.setNext(node);
         }
     }
 
     public void loop(boolean enable) 
     {
         loopEnabled = enable;
     }
 
     // Called to draw the object (starts playback)
     public void draw() 
     {
         play();
     }
 
     public void start()
      {
         if (head != null) 
         {
             curPlayNode = head;
             curPlayNode.start();
             System.out.println("Playback started.");
         }
     }
 
     public void play() 
     {
         if (curPlayNode != null && curPlayNode.atEnd()) 
         {
 
             MelodyNode next = curPlayNode.getNext();
             if (next != null) 
             {
                 curPlayNode = next;
                 curPlayNode.start();
             } else if (loopEnabled) 
             {
                 curPlayNode = head;
                 curPlayNode.start();
             }
         }
     }
 
     public void stop()
      {
         curPlayNode = null;
         System.out.println("Playback stopped.");
     }
 
     
     public void print() 
     {
         MelodyNode temp = head;
         StringBuilder melodyOutput = new StringBuilder("Melody: ");
         while (temp != null) 
         {
             melodyOutput.append(temp.getMelodyValue()).append(", ");
             temp = temp.getNext();
         }
         if (melodyOutput.length() > 0)
          {
             melodyOutput.setLength(melodyOutput.length() - 2);
         }
         System.out.println(melodyOutput.toString());
     }
 
     public void clear() 
     {
         head = null;
         System.out.println("The Melody List Is Cleared");
     }
 
 // Adds nodes to the linked list using the manager's data
     public void addNodes() 
     {
         for (int i = 0; i < manager.size(); i++)
          {
             insertAtEnd(new MelodyNode(manager, i));
         }
     }
 }