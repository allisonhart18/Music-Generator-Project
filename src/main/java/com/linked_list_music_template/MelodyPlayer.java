/*
 * c2017-c2023 Courtney Brown 
 * 
 * Class: MelodyPlayer
 * Description: Sends a melody of midi notes to an external player/midi channel, revised 2024 for polyphonic playing
 * 
 */

 package com.linked_list_music_template;

 import java.util.*;
 
 // send the MIDI elsewhere to play the notes
 public class MelodyPlayer {
	 MidiBusCRCP outputMidiBus; // Sends MIDI notes to an external program (e.g., Ableton, Logic, etc.)
 
	 int note_index = 0; // Where we are in the notes
	 float notems; // The value of a quaver or 1/4 note in millis
	 float last_time; // The last time we called draw()
	 boolean play; // Should we play?
	 float bpm; // Beats per minute of melody
 
	 double start_time; // Time at creation of the melody player in milliseconds
	 double playStart = 0;
 
	 double rhythm_multiplier; // Determines note length and onset of the next note
 
	 ArrayList<Integer> melody; // List of pitches in order
	 ArrayList<Double> rhythm; // List of note lengths (& thus time before next note, in order)
	 ArrayList<Double> startTimes; // List of start times
 
	 boolean hasRhythm; // Has there been a list of rhythms assigned?
	 boolean hasMelody; // Has there been a list of pitches assigned?
	 boolean hasStart = false;
 
	 String outputBus; // Bus to send MIDI to
 
	 // List of playing notes to keep track of for note offs
	 ArrayList<Double> playingRhythms = new ArrayList<>();
	 ArrayList<Double> playingTimes = new ArrayList<>();
	 ArrayList<Integer> playingPitches = new ArrayList<>();
 
	 // Constructor -- initializes data
	 MelodyPlayer(float tempo, String bus) {
		 reset();
		 setBPM(tempo);
		 play = true;
		 hasRhythm = false;
		 rhythm_multiplier = 0.5f; // Default is 1/8 notes
		 start_time = System.currentTimeMillis();
		 outputBus = bus;
		 setupMidi();
	 }
 
	 void setMelody(ArrayList<Integer> m) {
		 melody = m;
		 hasMelody = true;
	 }
 
	 void setRhythm(ArrayList<Double> r) {
		 rhythm = r;
		 hasRhythm = true;
	 }
 
	 void setStartTimes(ArrayList<Double> r) {
		 startTimes = r;
		 hasStart = true;
	 }
 
	 // List available devices
	 void listDevices() {
		 MidiBusCRCP.listDevices();
	 }
 
	 // Create the MIDI port and bus to send the notes to
	 void setupMidi() {
		 outputMidiBus = new MidiBusCRCP(outputBus);
	 }
 
	 void setBPM(float tempo) {
		 bpm = tempo;
		 notems = (float) (((1.0 / (bpm / 60.0))) * 1000); // How many ms in a 1/4 note for this tempo
	 }
 
	 // Time since creation of the melody player -- mimics the Processing function millis()
	 int millis() {
		 double millisNow = System.currentTimeMillis() - start_time;
		 return (int) millisNow;
	 }
	 void play() {
		int vel = 100; // MIDI velocity
		double cur_time = millis(); // What time is it now?
	
		// Send note off messages for any notes that have been playing
		sendNoteOff(cur_time);
	
		// Just do nothing if there is no melody (pitches)
		if (!hasMelody) {
			System.out.println("There is no melody in the notes given.");
			return;
		}
	
		// If we're at the end of the melody, reset the index to loop
		if (atEndOfMelody()) {
			reset(); // Reset to start from the beginning of the melody
		}
	
		// Playing the notes by sending the note ons at the correct times
		if (note_index < startTimes.size()) {
			// Check if the current note should be played
			boolean shouldPlayNote = cur_time - playStart >= notems * startTimes.get(note_index);
	
			// Send note on messages
			if (shouldPlayNote && note_index < startTimes.size()) {
				sendNoteOn(note_index, cur_time, vel);
				note_index++;
	
				// Handle simultaneous notes (chords)
				while (note_index < startTimes.size() && 
					   startTimes.get(note_index).equals(startTimes.get(note_index - 1))) {
					sendNoteOn(note_index, cur_time, vel);
					note_index++;
				}
	
				// Update the playStart time for the next note
				playStart = cur_time; // Update playStart to the current time after playing a note
			}
		}
	}
	
 
	void sendNoteOn(int note_index, double cur_time, int vel) {
		outputMidiBus.sendNoteOn(0, (int) melody.get(note_index), vel);
		playingRhythms.add(rhythm.get(note_index));
		playingTimes.add(cur_time);
		playingPitches.add(melody.get(note_index));
	}
	
	void sendNoteOff(double cur_time) {
		for (int i = playingRhythms.size() - 1; i >= 0; i--) {
			// Ensure we send a note off only after its duration
			if (playingRhythms.get(i) * notems <= cur_time - playingTimes.get(i)) {
				outputMidiBus.sendNoteOff(0, (int) playingPitches.get(i), 0);
				playingRhythms.remove(i);
				playingPitches.remove(i);
				playingTimes.remove(i);
			}
		}
	}

	
 
	 // Reset note to 0
	 void reset() {
		 note_index = 0;
		 playStart = millis();
		 playingRhythms.clear();
		 playingPitches.clear();
		 playingTimes.clear();
	 }
 
	 // Stop the melody playback
	 void stop() {
		 // Send note off messages for all currently playing notes
		 for (int i = playingPitches.size() - 1; i >= 0; i--) {
			 outputMidiBus.sendNoteOff(0, (int) playingPitches.get(i), 0);
		 }
		 playingRhythms.clear();
		 playingPitches.clear();
		 playingTimes.clear();
		 note_index = 0; // Reset the note index
		 System.out.println("Melody stopped.");
	 }
 
	 // Set to ends
	 void setToEnd() {
		 note_index = melody.size();
		 playingRhythms.clear();
		 playingPitches.clear();
		 playingTimes.clear();
	 }
 
	 // Have we reached the end of the melody?
	 boolean atEndOfMelody() {
		 return note_index >= melody.size() && playingRhythms.size() <= 0;
	 }
 }
 
 
 
  
 
 
 