package com.linked_list_music_template;
import java.nio.file.FileSystems;
import java.nio.file.FileSystem;

public class LinkedListMelodyManager  extends MelodyManager  implements Drawable{
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid" + sys.getSeparator();


    String [] files = {"motive1Am", "motive2Am", "motive3Am", "motive2E", "motive3E"};

    LinkedListMeoldyManager()
    {
     super();
    }

    void setup(){

        for(int i = 0; i<files.length;i++)
        {
            addMidiFile(prependPath+files[i]+appendType);
        }

    }

    int size(){
        return files.length;
    }

    public void draw()
    {

        playMelodies();
    }

}
