package com.linked_list_music_template;

import java.util.ArrayList;
import java.util.Random;

// Extending MelodyNode for tree-based functionality
public class TreeMelodyNode extends MelodyNode {
    private ArrayList<TreeMelodyNode> nodes; // List of child nodes
    private TreeMelodyManager manager; // Use TreeMelodyManager instead
    private int index;
    private TreeNode root;
    private Random random;
    private ArrayList<Integer> midiNotes; // List of MIDI pitches

    // Constructor for TreeMelodyNode
    public TreeMelodyNode(TreeMelodyManager manager, int whichMelody, ArrayList<Integer> midiNotes) {
        super(manager, whichMelody); // Use manager here
        this.manager = manager; // Initialize the manager field
        this.midiNotes = midiNotes;
        this.nodes = new ArrayList<>();
        this.root = null;
        this.random = new Random();
    }

    // Adds nodes to the tree
    public void addNextNodes(ArrayList<TreeMelodyNode> motives) {
        for (int i = 0; i < motives.size(); i++) {
            TreeMelodyNode curNode = motives.get(i);
            int lastPitch = this.midiNotes.get(this.midiNotes.size() - 1);

            if (!curNode.midiNotes.isEmpty() && lastPitch == curNode.midiNotes.get(0)) {
                // Pop the first note from curNode's midiNotes
                curNode.midiNotes.remove(0);
                // Add curNode as a child node
                nodes.add(curNode);
                // Remove curNode from motives
                motives.remove(i);
                i--; // Adjust index after removal
            }
        }

        // Recursively call addNextNodes on child nodes
        for (TreeMelodyNode node : nodes) {
            node.addNextNodes(motives);
        }
    }

    // Overrides getNext() to return a random child node
    @Override
    public TreeMelodyNode getNext() {
        if (nodes.isEmpty()) {
            return null; // No next node available
        }
        Random random = new Random();
        return nodes.get(random.nextInt(nodes.size()));
    }

    // Print the tree in tree form with indentation
    public void print(int spacesBefore) {
        // Print indentation
        for (int i = 0; i < spacesBefore; i++) {
            System.out.print(" ");
        }
        // Print current node's data
        System.out.println("— " + getMelodyValue() + " " + midiNotes);

        // Recursively print child nodes with increased indentation
        for (TreeMelodyNode node : nodes) {
            node.print(spacesBefore + 1);
        }
    }

    // Overloaded print method that starts with zero indentation for the root
    public void print() {
        print(0);
    }
 // Method to train the tree with motives
public void train(int index, int motiveNoteCount) {
    // If the root is null, initialize it with a random melody
    if (root == null) {
        ArrayList<Integer> initialMelody = generateRandomMelody(motiveNoteCount);
        root = new TreeNode(initialMelody);
    } else {
        // Generate a new motive to add to the tree
        ArrayList<Integer> newMotive = generateRandomMelody(motiveNoteCount);
        addMotiveToTree(root, newMotive);
    }
}


    private ArrayList<Integer> generateRandomMelody(int noteCount) {
        ArrayList<Integer> melody = new ArrayList<>();
        for (int i = 0; i < noteCount; i++) {
            melody.add(random.nextInt(128)); // Generate random MIDI note numbers
        }
        return melody;
    }

    private void addMotiveToTree(TreeNode node, ArrayList<Integer> melody) {

        TreeNode newNode = new TreeNode(melody);
        node.addChild(newNode);
    }

    
}
