package com.linked_list_music_template;

import java.util.ArrayList;

public class TreeNode {
    private ArrayList<Integer> melody; // Holds the melody data
    private ArrayList<TreeNode> children; // Holds child nodes

    public TreeNode(ArrayList<Integer> melody) {
        this.melody = melody;
        this.children = new ArrayList<>();
    }

    public ArrayList<Integer> getMelody() {
        return melody;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
