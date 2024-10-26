package com.linked_list_music_template;
import java.util.ArrayList;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class LinkedListMelodyManager implements Drawable {
    ArrayList<MelodyPlayer> players;
    ArrayList<MidiFileToNotes> midiNotes;

    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid" + sys.getSeparator();

    String[] files = {"motive1Am", "motive2Am", "motive3Am", "motive1E", "motive2E", "motive3E"};


    public LinkedListMelodyManager() {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

    public void setup() {
        for( int i = 0; i <files.length; i++)
        {
            addMidiFile(prependPath+files[i]+appendType);
        }
    }

    public int size() {
        return players.size();
    }

    public void playMelodies() {
        for (MelodyPlayer player : players) {
            player.play();
        }
    }

    public void addMidiFile(String filePath) {
        int index = players.size();
        players.add( new MelodyPlayer(100, "Microsoft GS Wavetable Synth"));
        midiNotes.add(new MidiFileToNotes(filePath));
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());
    }

    public void start(int index) {
        players.get(index).reset();
    }

    public boolean atEnd(int index) {
        return players.get(index).atEndOfMelody();
    }

    public void draw()
    {
        

    }

        
    
}