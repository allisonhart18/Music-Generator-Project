package com.linked_list_music_template;

import java.util.ArrayList;
import java.util.Random;

public class TreeMelody extends LinkedListMelody {
    private TreeMelodyNode root; // The root of the melody tree
    private TreeMelodyManager manager; // The manager for the tree melodies
    

    // Constructor to initialize the TreeMelody with a TreeMelodyManager
    public TreeMelody(TreeMelodyManager manager) {
        this.manager = manager;
    }

    // Print the contents of the tree starting from the root
    @Override
    public void print() {
        if (root != null) {
            root.print();
        } else {
            System.out.println("Tree is empty.");
        }
    }

  
    public void clear() {
        root = null;
        head = null;
        System.out.println("Tree cleared.");
    }

   // Train the melody tree with the melody at the specified index, -1 for random
   public void train(int index) {
    int motiveNoteCount = 4; // Example motive note count; adjust as needed
    train(index, motiveNoteCount); // Call the new overloaded train method
}

// New method to train the melody tree with specified index and motive note count
public void train(int index, int motiveNoteCount) {
    ArrayList<TreeMelodyNode> motives = new ArrayList<>();

    // Convert files to melodies of motiveNoteCount or less
    manager.convertToMotivesAndReplace(motiveNoteCount);

    // If index is -1, generate a random index
    if (index == -1) {
        Random random = new Random();
        index = random.nextInt(manager.melodySize());
    }

    // Create TreeMelodyNodes for each melody and add to motives
    for (int i = 0; i < manager.melodySize(); i++) {
        ArrayList<Integer> midiNotes = manager.getMidiNotes(i);
        motives.add(new TreeMelodyNode(manager, i, midiNotes));
    }

    // Set the root to the selected node if it is null
    if (root == null && !motives.isEmpty()) {
        root = motives.get(index);
        motives.remove(index);
    }

    // Call addNextNodes on the root
    if (root != null) {
        root.addNextNodes(motives);
    }

    // Set the inherited head to root for starting playback
    head = root;
}

// Overloaded train() method to call train with a random index
public void train() {
    train(-1);
}

    // Start playback from the root
    @Override
    public void start() {
        if (root != null) {
            head = root; // Set the inherited head to root
            super.start();
        }
    }

    // Stop playback by resetting the current playing node
    @Override
    public void stop() {
        super.stop();
    }

    // Play the melody from the root
    @Override
    public void play() {
        if (root != null) {
            head = root; // Set the inherited head to root
            super.play();
        }
    }

    // Loop the melody starting from the root
    @Override
    public void loop(boolean enable) {
        if (root != null) {
            head = root; // Set the inherited head to root
            super.loop(enable); // Call the superclass method with the boolean parameter
        }
    }


    @Override
public void weave(MelodyNode node, int count, int skip) {
    // Optionally, customize or extend the weaving logic for the tree structure
    super.weave(node, count, skip); // Call the superclass's weave method
}

    // Check if the melody is playing
    public boolean playing() {
        return manager.isPlaying();
    }

    // Stop all playing nodes using the manager's method
    public void stopAllPlayingNodes() {
        manager.stopAll();
        System.out.println("All playing nodes stopped.");
    }
    
}
