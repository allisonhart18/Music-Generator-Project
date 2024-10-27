package com.linked_list_music_template;

// Node class for the melody linked list
public class MelodyNode {
    private LinkedListMelodyManager melodies;
    private int whichMelody;
    private MelodyNode next;

    public MelodyNode(LinkedListMelodyManager melodies, int whichMelody) {
        this.melodies = melodies;
        this.whichMelody = whichMelody;
    }

    public MelodyNode getNext() {
        return next;
    }

    public void setNext(MelodyNode next) {
        this.next = next;
    }

    public int getMelodyIndex() {
        return whichMelody;
    }

    public void start() {
        melodies.start(whichMelody);
    }

    public MelodyNode copy() {
        return new MelodyNode(this.melodies, this.whichMelody);
    }

    public boolean atEnd() {
        return melodies.atEnd(whichMelody);
    }
}
