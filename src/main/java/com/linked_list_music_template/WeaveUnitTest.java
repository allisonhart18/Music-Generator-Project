/*package com.linked_list_music_template;

public class WeaveUnitTest {
    private MelodyManager manager;
    private LinkedListMelody melodyList;

    public WeaveUnitTest(MelodyManager manager) {
        this.manager = manager;
        this.melodyList = new LinkedListMelody();
        // Assuming `manager` is an instance of LinkedListMelodyManager
int whichMelody = 1; // Replace with the actual int value needed

MelodyNode node = new MelodyNode(manager, whichMelody); // This uses LinkedListMelodyManager and int

    }

    public void testWeave1() {
        for (int i = 0; i < 12; i++) {
            melodyList.insertAtEnd(new MelodyNode(manager, 3));
        }
        MelodyNode node = new MelodyNode(manager, 0);
        melodyList.weave(node, 3, 4);
        melodyList.print(); // Expected output: Melody: 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0, 3, 3, 3, 0
    }

    public void testWeave2() {
        for (int i = 0; i < 12; i++) {
            melodyList.insertAtEnd(new MelodyNode(manager, 3));
        }
        MelodyNode node = new MelodyNode(manager, 0);
        melodyList.weave(node, 5, 10);
        melodyList.print(); // Expected output: Melody: 3, 3, 3, 3, 3, 0, 3, 3, 3, 3, 3, 0, 3, 3
    }
}
    */

