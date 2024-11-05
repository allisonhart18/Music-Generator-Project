/*
 * 11/4
 * Allison Hart
 * 
 * LinkedListMelodyManager class manages a collection of MelodyPlayer 
 * instances and the MIDI files, allowing for the loading, playing, 
 * and checking of melodies. It initializes with a predefined list of MIDI file paths, 
 * sets up the melody players, and provides methods to control playback and check if melodies have ended.
 * 
 * 
 */



package com.linked_list_music_template;


import java.util.ArrayList;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;


public class LinkedListMelodyManager implements Drawable {
    
    ArrayList<MelodyPlayer> players;
   
    ArrayList<MidiFileToNotes> midiNotes;

    // Static variables for constructing MIDI file paths
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid" + sys.getSeparator();

    // Array of MIDI file names to be loaded
    String[] files = {"HarpMidi", "BachInvention"};

  
    public LinkedListMelodyManager() {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

  
    public void setup() {
        for (int i = 0; i < files.length; i++) {
            addMidiFile(prependPath + files[i] + appendType); // Load MIDI file
        }
    }

    // Returns the number of melody players
    public int size() {
        return players.size();
    }

    // Plays all melodies using their players
    public void playMelodies() {
        for (MelodyPlayer player : players) {
            player.play(); 
    }
}

    // Adds a MIDI file to the manager
    public void addMidiFile(String filePath) {
        int index = players.size(); 
        players.add(new MelodyPlayer(120, "Microsoft GS Wavetable Synth")); 
        midiNotes.add(new MidiFileToNotes(filePath)); 
       
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());
    }

    //starts at index
    public void start(int index)
    {
        players.get(index).reset(); //resets to start
    }


    public boolean atEnd(int index)
    {
        return players.get(index).atEndOfMelody(); 
    }

    public void draw()
    {
        playMelodies();
    }

    //prints melody list 
    public void print()
    {
        StringBuilder melodyOutput = new StringBuilder("Melody Manager: ");
        for (int i = 0; i < players.size(); i++) {
            melodyOutput.append("Melody ").append(i).append(", ");
        }
        if (melodyOutput.length() > 0)
        {
            melodyOutput.setLength(melodyOutput.length() - 2);
        }
        System.out.println(melodyOutput.toString());
    }
}