package com.linked_list_music_template;
import java.util.ArrayList;
import java.util.Random;

public class TreeMelody extends LinkedListMelody {
    private TreeMelodyNode root;
    private TreeMelodyManager manager;

    public TreeMelody(TreeMelodyManager manager) {
        this.manager = manager;
    }

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

    public void train(int index) {
        train(index, 4);
    }

    public void train(int index, int motiveNoteCount) {
        ArrayList<TreeMelodyNode> motives = new ArrayList<>();
        manager.convertToMotivesAndReplace(motiveNoteCount);

        if (index == -1) {
            Random random = new Random();
            index = random.nextInt(manager.melodySize());
        }

        for (int i = 0; i < manager.melodySize(); i++) {
            motives.add(new TreeMelodyNode(manager, i, manager.getMidiNotes(i)));
        }

        if (root == null && !motives.isEmpty()) {
            root = motives.get(index);
            motives.remove(index);
        }

        if (root != null) {
            root.addNextNodes(motives);
        }

        head = root;
    }

    @Override
    public void start() {
        if (root != null) {
            head = root;
            super.start();
        }
    }

    @Override
    public void play() {
        if (root != null) {
            head = root;
            super.play();
        }
    }

    @Override
    public void loop(boolean enable) {
        if (root != null) {
            head = root;
            super.loop(enable);
        }
    }

    @Override
    public void weave(MelodyNode node, int count, int skip) {
        super.weave(node, count, skip);
    }

    public boolean playing() {
        return manager.isPlaying();
    }

    public void stopAllPlayingNodes() {
        manager.stopAll();
        System.out.println("All playing nodes stopped.");
    }
}
