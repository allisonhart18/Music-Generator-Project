/*
 * Allison Hart - 11/4
 * tree structure for managing and playing musical motives
 *  using nodes that hold melodies and link them based on musical patterns
 */

package com.linked_list_music_template;
import java.util.ArrayList;



public class TreeMelody 
{
    private TreeMelodyNode root;
    private TreeMelodyManager melodyManager;

    public TreeMelody(TreeMelodyManager melodyManager) 
    {
        this.melodyManager = melodyManager;
    }

    public TreeMelodyNode getRoot() 
    {
        return root;
    }

    public TreeMelodyManager getMelodyManager() 
    {
        return melodyManager;
    }

    public void setRoot(TreeMelodyNode root)
     {
        this.root = root;
    }

    public void setMelodyManager(TreeMelodyManager melodyManager) 
    {
        this.melodyManager = melodyManager;
    }

    // Method to train the tree by creating nodes based on given motives and linking them by matching patterns
    public void train(int noteMotiveCount, int rootIndex)
     {
        ArrayList<MelodyPlayer> motives = melodyManager.convertToMotives(noteMotiveCount);
        root = new TreeMelodyNode(melodyManager, rootIndex, motives.get(rootIndex).getMelody());

        for (int i = 0; i < motives.size(); i++) 
        {
            ArrayList<Integer> motive = motives.get(i).getMelody();
            TreeMelodyNode currentNode = root;
            // Traverse the tree and add new nodes if a matching pattern is found
            while (currentNode != null) {
                if (motive.get(0).equals(currentNode.getMelody().get(currentNode.getMelody().size() - 1))) 
                {
                    currentNode.addNextNode(new TreeMelodyNode(melodyManager, i, motive));
                }
                currentNode = currentNode.getNextNodes().isEmpty() ? null : currentNode.getNextNodes().get(0);
            }
        }
    }

     //play the melody by traversing the tree and playing nodes randomly
    public void play() 
    {
        TreeMelodyNode currentNode = root;
        while (currentNode != null) 
        {
            MelodyPlayer player = melodyManager.getPlayer(currentNode.getIndex());
            player.play();
            ArrayList<TreeMelodyNode> nextNodes = currentNode.getNextNodes();
            if (nextNodes.size() > 0) 
            {
                int nextIndex = (int) (Math.random() * nextNodes.size());
                currentNode = nextNodes.get(nextIndex);
            } else 
            {
                currentNode = null;
            }
        }
    }

    // Recursive method to print the tree structure with indentation
    public void printTree(TreeMelodyNode node, String indent) 
    {
        if (node == null) return;
        System.out.println(indent + node.getIndex() + ": " + node.getMelody());
        for (TreeMelodyNode nextNode : node.getNextNodes()) 
        {
            printTree(nextNode, indent + "    ");
        }
    }

    public void printTree() 
    {
        printTree(root, "");
    }
}