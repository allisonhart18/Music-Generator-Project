

package com.linked_list_music_template;

// WeaveUnitTest class for testing the weave functionality
public class WeaveUnitTest {
    private LinkedListMelody melodyList;
    private LinkedListMelodyManager manager; // Assuming you have a MelodyManager class

    // Constructor
    public WeaveUnitTest(LinkedListMelodyManager manager) {
        this.manager = manager;
        this.melodyList = new LinkedListMelody();
    }

    // Test the weave function with a basic use case
    public void testWeave1() {
        MelodyNode node = new MelodyNode(manager, 0); // Create a new melody node
        for (int i = 0; i < 12; i++) {
            melodyList.insertAtEnd(new MelodyNode(manager, 3)); // Adding melody nodes 3, 3, 3...
        }

        melodyList.weave(node, 3, 4); // Weave the node
        melodyList.print(); // Expecting: Melody: 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0
    }

    // Test the weave function with count * skip greater than melody length
    public void testWeave2() {
        MelodyNode node = new MelodyNode(manager, 0); // Create a new melody node
        for (int i = 0; i < 12; i++) {
            melodyList.insertAtEnd(new MelodyNode(manager, 3)); // Adding melody nodes 3, 3, 3...
        }

        melodyList.weave(node, 5, 10); // Weave the node
        melodyList.print(); // Expecting: Melody: 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 3, 3
    }
}
 