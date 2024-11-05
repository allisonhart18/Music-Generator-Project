/*
 * Allison Hart 11/4
 * MelodyManager class manages a collection of melody players and MIDI files, 
 * allowing for the loading, playback, and reset.
 * It also lets methods to check if a melody has finished playing.
 * 
 */


package com.linked_list_music_template;


import java.util.ArrayList;


public class MelodyManager {
    ArrayList<MelodyPlayer> players; 
    ArrayList<MidiFileToNotes> midiNotes; 

 
    MelodyManager() {
        players = new ArrayList<>();
        midiNotes = new ArrayList<>();
    }

    // Plays all melodies 
    public void playMelodies() {
        assert(players != null); // makes sure players list is not null

      
        for(int i = 0; i < players.size(); i++) {
            players.get(i).play();
        }
    }

  
    void addMidiFile(String filePath) {
        int index = players.size(); 

      
        players.add(new MelodyPlayer(120, "Microsoft GS Wavetable Synth"));
        midiNotes.add(new MidiFileToNotes(filePath)); 
        int noteCount = midiNotes.get(index).getPitchArray().size(); 
        assert(noteCount > 0); 

        // Set up melody, rhythm, and start times 
        players.get(index).setMelody(midiNotes.get(index).getPitchArray());
        players.get(index).setRhythm(midiNotes.get(index).getRhythmArray());
        players.get(index).setStartTimes(midiNotes.get(index).getStartTimeArray());
    }

    
    void start(int index) {
        players.get(index).reset();
    }

    
    boolean atEnd(int index) {
        return players.get(index).atEndOfMelody();
    }
}