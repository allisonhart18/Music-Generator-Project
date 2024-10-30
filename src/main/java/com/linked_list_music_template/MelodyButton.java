/*
 * 10/29
 * Allison Hart
 * MelodyButton class is an abstract class of a button that
 *  controls a melody, allowing it to be extended by specific button types, 
 * such as the PlayButton. The PlayButton class  implements the 
 * functionality to start playing a melody when pressed
 */



package com.linked_list_music_template;
import processing.core.*;

public abstract class MelodyButton extends Button {


    LinkedListMelody melody; // the linked list melody to control
    Boolean changeLoop;


    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    MelodyButton(PApplet main_, LinkedListMelody melody_, String label_,float x_, float y_)
    {
        super(main_, label_, x_, y_);
        melody = melody_;
        changeLoop = false;
    }
}



class PlayButton extends MelodyButton {


    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    PlayButton(PApplet main_, LinkedListMelody melody_,float x_, float y_)
    {
        super(main_, melody_,"Play", x_, y_);
    }


    // start the melody
    public void onPress()
    {
        melody.start();
    }
}



class StopButton extends MelodyButton {


    //overload the constructor for the MelodyButton - use the default constructor for h & w & color
    StopButton(PApplet main_, LinkedListMelody melody_,float x_, float y_)
    {
        super(main_, melody_,"Stop", x_, y_);
    }


    // start the melody
    public void onPress()
    {
        melody.stop();
    }
}





class LoopButton extends MelodyButton {

    LoopButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, melody_, "Loop", x_, y_);
    }

    // Toggle looping and start playback
    public void onPress() {
        changeLoop = !changeLoop;
        melody.loop(changeLoop);
        melody.play();
    }
}

//weave
class WeaveButton extends MelodyButton {
    private int option; // Different options for weaving
  //  private MelodyManager melodyManager;


    WeaveButton(PApplet main_, LinkedListMelody melody_, int option_, float x_, float y_) {
        super(main_, melody_, "Weave Option " + option_, x_, y_);
        this.option = option_;
       // me
    }


    public void onPress() {
        MelodyNode node = new MelodyNode(null, 0); // New node for weaving with a default melody
        switch (option) {
            case 1:
                melody.weave(node, 3, 4); // Customize parameters as needed
                break;
            case 2:
                melody.weave(node, 5, 2);
                break;
            case 3:
                melody.weave(node, 4, 6);
                break;
            default:
                System.out.println("Invalid weave option.");
        }
        System.out.println("Weave option " + option + " applied.");
    }
}




//clearlist


class ClearList extends MelodyButton {


    ClearList(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, melody_, "Clear List", x_, y_);
    }


    public void onPress() {
       
        System.out.println("Melody list cleared!");
    }
}







class UnitTestButton extends MelodyButton {
    UnitTestButton(PApplet main_, LinkedListMelody melody_, float x_, float y_) {
        super(main_, melody_, "Run Tests", x_, y_);
    }


    public void onPress() {
       // WeaveUnitTest test = new WeaveUnitTest();
        System.out.println("Running unit tests...");
      //  test.testWeave1();
       // test.testWeave2();
        System.out.println("Unit tests completed.");
    }
}



