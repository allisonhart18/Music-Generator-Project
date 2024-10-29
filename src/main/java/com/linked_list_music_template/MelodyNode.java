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
        if (melodies != null) {
            melodies.start(whichMelody); // Play the melody at the current index
            System.out.println("Starting melody index: " + whichMelody);
        } else {
            System.out.println("Melody manager is not initialized.");
        }
    }
    
    
    public void stop() {
        melodies.stop(whichMelody); // Call stop on the melodies manager
        System.out.println("Stopping melody index: " + whichMelody); // Print when the melody stops
    }

    public MelodyNode copy() {
        return new MelodyNode(this.melodies, this.whichMelody);
    }

    public boolean atEnd() {
        return melodies.atEnd(whichMelody);
    }
}
