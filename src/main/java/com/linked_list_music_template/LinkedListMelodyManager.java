/*
 * 10/29
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


// Class managing a collection of melody players and MIDI files
public class LinkedListMelodyManager implements Drawable {
    // List of MelodyPlayer instances that play the melodies
    ArrayList<MelodyPlayer> players;
    // List of MidiFileToNotes instances that convert MIDI files to notes
    ArrayList<MidiFileToNotes> midiNotes;

    // Static variables for constructing MIDI file paths
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid" + sys.getSeparator();

    // Array of MIDI file names to be loaded
    String[] files = {"piano1", "piano2", "piano3", "piano4", "piano5", "piano6"};

    // Constructor initializing the players and midiNotes lists
    public LinkedListMelodyManager() {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

    // Method to set up the manager by adding MIDI files from the predefined list
    public void setup() {
        for (int i = 0; i < files.length; i++) {
            addMidiFile(prependPath + files[i] + appendType); // Load each MIDI file
        }
    }

    // Returns the number of melody players
    public int size() {
        return players.size();
    }

    // Plays all melodies using their respective players
    public void playMelodies() {
        for (MelodyPlayer player : players) {
            player.play(); // start play on each MelodyPlayer
        }
    }

    // Adds a MIDI file to the manager, creating a corresponding MelodyPlayer
    public void addMidiFile(String filePath) {
        int index = players.size(); // Get the current index for the new player
        players.add(new MelodyPlayer(120, "Microsoft GS Wavetable Synth")); // Create and add a new MelodyPlayer
        midiNotes.add(new MidiFileToNotes(filePath)); // Create and add a MidiFileToNotes instance
        // Set the melody, rhythm, and start times for the player based on the MIDI file
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());
    }

    // Starts the melody associated with the player at the given index
    public void start(int index) {
        players.get(index).reset(); // Reset the player to start playback
    }

    // Checks if the melody at the given index has reached its end
    public boolean atEnd(int index) {
        return players.get(index).atEndOfMelody(); // Return whether the melody has ended
    }

    // Draw method for implementing Drawable interface (currently empty)
    public void draw() {
        playMelodies();
    }
}
