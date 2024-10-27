package com.linked_list_music_template;
import processing.core.PApplet;

public abstract class MelodyButton extends Button {

    LinkedListMelody melody;
    
        //overload the constructor for default sizes 150 x 25
     MelodyButton(PApplet main_, LinkedListMelody melody_, String label_,float x_, float y_)
     {
         super(main_, label_, x_, y_); 
         melody = melody_;
     }
 
 }
 
   class PlayButton extends MelodyButton{
 
        PlayButton(PApplet main_,LinkedListMelody melody_,float x_, float y_)
        {
         super(main_, melody_, "Play", x_, y_); 

         }
         public void onPress()
         {
            melody.start();

         }
    }
 


