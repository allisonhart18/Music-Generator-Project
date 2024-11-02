/*
 * c2024 Courtney Brown 
 * 
 * Class: TreeMelodyManager
 * Description: Handles all the melody files for the TreeMelodyProject
 * 
 * Provides additional methods to help you in your project (YAY!!):
 * 
 * void convertToMotivesAndReplace(int noteCount) -- reparses all the files into melodies (ie, motives) with noteCount notes (or less)
 * NOTE: the original melody then will not play, it will be REPLACED by all the motives.
 *
 * void popNoteFromMelody(int i) -- removes the first note from the melody in the player at index i
 * String melodyToString(int i) -- returns the melody as a string (ie, midi pitch numbers) for player at index i -- for debugging
 * ArrayList<Integer> getMelodyPitches(int i) -- get the midi note numbers of the melody of the player at index i
 * int melodySize() -- size of the players -- CALL THIS INSTEAD OF FILESIZE when trying to get motives
 * int fileSize() -- note: this was size() before, but now changing for clarity -- as # of melodies won't always match # of files now
 * void setFiles() -- you can add a new Files String array here to overwrite previous.
 * 
 * 
 * Methods that will help with the extra credit:
 * void addPlayers() -- adds an ArrayList of MelodyPlayer to players
 * ArrayList<MelodyPlayer> copyPlayers() -- returns a copy of players
 * void stopAll() - sends noteOffs for all the playing notes at once
 *  ArrayList<MelodyPlayer> convertToMotives(int noteCount) -- this is needed for the actual project, BUT only needs to be called by itself for extra credit
 *          it converts all the files to motives of size noteCount. the last motive may be less than noteCount long
 * 
 */
package com.linked_list_music_template;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.ArrayList;

public class TreeMelodyManager extends MelodyManager implements Drawable {

    // Used for loading files
    static FileSystem sys = FileSystems.getDefault();
    static String prependPath = "mid" + sys.getSeparator();
    static String appendType = ".mid";

    // Array of file names
    String[] files = {"MaryHadALittleLamb"};

    // Default tempo and MIDI bus
    float tempo = 120.0f; // Set a default tempo (beats per minute)
    String bus = "Default Bus"; // Replace with the correct MIDI bus name if needed

    TreeMelodyManager() {
        super();
    }

    // Method to set custom files
    void setFiles(String[] files_) {
        files = files_;
    }

    // Add more melody players to the manager
    void addPlayers(ArrayList<MelodyPlayer> p) {
        players.addAll(p);
    }

    // Return a copy/clone of all the players
    ArrayList<MelodyPlayer> copyPlayers() {
        ArrayList<MelodyPlayer> list = new ArrayList<>();
        list.addAll(players);
        return list;
    }

    // Load all the files. Make sure to set the files you want first.
    void setup() {
        for (int i = 0; i < files.length; i++) {
            addMidiFile(prependPath + files[i] + appendType);
        }
    }

    // Clear all players
    void clear() {
        players.clear();
    }

    // Size of the files array
    int fileSize() {
        return files.length;
    }

    // Size of the melodies
    int melodySize() {
        return players.size();
    }

    // Get the MIDI note numbers of the melody at index i
    ArrayList<Integer> getMelodyPitches(int i) {
        return players.get(i).getMelody();
    }

    // New method to get MIDI notes for a given index
   // New method to get MIDI notes for a given index as an ArrayList of integers
ArrayList<Integer> getMidiNotes(int i) {
    if (i >= 0 && i < midiNotes.size()) {
        return midiNotes.get(i).getPitchArray(); // Extracts the pitch (note numbers) from the specified MidiFileToNotes
    } else {
        throw new IndexOutOfBoundsException("Invalid index for MIDI notes.");
    }
}

    // Convert files to motives with a specified note count
    ArrayList<MelodyPlayer> convertToMotives(int noteCount) {
        System.out.println("Converting to motives with " + noteCount + " notes. This will take time...");
        ArrayList<MelodyPlayer> newPlayers = new ArrayList<>();

        for (MidiFileToNotes notes : midiNotes) {
            ArrayList<Double> rhythms = notes.getRhythmArray();
            ArrayList<Double> times = notes.getStartTimeArray();
            ArrayList<Integer> pitches = notes.getPitchArray();

            double startTime = 0;
            for (int i = 0; i < pitches.size(); i++) {
                MelodyPlayer player = new MelodyPlayer(tempo, bus); // Use initialized variables

                ArrayList<Double> playingRhythms = new ArrayList<>();
                ArrayList<Double> playingTimes = new ArrayList<>();
                ArrayList<Integer> playingPitches = new ArrayList<>();

                double curZero = startTime;
                for (int j = 0; j < noteCount && i + j < pitches.size(); j++) {
                    playingRhythms.add(rhythms.get(i + j));
                    playingPitches.add(pitches.get(i + j));

                    if (j == 0) {
                        playingTimes.add(0.0);
                        curZero += (times.get(i + j) - curZero);
                    } else {
                        playingTimes.add(times.get(i + j) - curZero);
                    }
                    startTime = times.get(i + j);
                }

                player.setStartTimes(playingTimes);
                player.setMelody(playingPitches);
                player.setRhythm(playingRhythms);
                newPlayers.add(player);

                i += (noteCount - 1);
            }
        }
        System.out.println("Finished converting. There are now " + players.size() + " melodies.");
        return newPlayers;
    }

    void convertToMotivesAndReplace(int noteCount) {
        players = convertToMotives(noteCount);
    }

    String melodyToString(int i) {
        ArrayList<Integer> pitches = players.get(i).getMelody();
        return pitches.toString();
    }

    String startTimesToString(int i) {
        return players.get(i).getStartTimes().toString();
    }

    void popNoteFromMelody(int i) {
        players.get(i).getMelody().remove(0);
        players.get(i).getStartTimes().remove(0);
        players.get(i).getRhythm().remove(0);
    }

    void stopAll() {
        for (MelodyPlayer player : players) {
            player.stopAllNotes();
        }
    }

    public void draw() {
        playMelodies();
    }

        // Method to check if any melody is currently playing
        public boolean isPlaying() {
            for (MelodyPlayer player : players) {
                if (player.isPlaying()) { // Assuming MelodyPlayer has an isPlaying() method
                    return true; // Return true if any melody is playing
                }
            }
            return false; // Return false if none are playing
        }
}
