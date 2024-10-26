package com.linked_list_music_template;

public class MelodyNode {
    private LinkedListMelodyManager melodies;
    private int whichMelody;
    private MelodyNode next;

    public MelodyNode(LinkedListMelodyManager melodies, int whichMelody) {
        this.melodies = melodies;
        this.whichMelody = whichMelody;
    }

    public int getMelodyIndex() {
        return whichMelody;
    }

    public boolean atEnd() {
        return melodies.atEnd(whichMelody);
    }

    public MelodyNode getNext() {
        return next;
    }

    public void setNext(MelodyNode next) {
        this.next = next;
    }

    public void start() {
        melodies.start(whichMelody);
    }
}