package com.linked_list_music_template;

import java.util.ArrayList;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
public class LinkedListMelodyManager implements Drawable {
    ArrayList<MelodyPlayer> players = new ArrayList<>();

    public void setup() {
        String[] files = {"motive1Am", "motive2Am", "motive3Am", "motive1E", "motive2E", "motive3E"};
        for (String file : files) {
            addMidiFile("mid/" + file + ".mid");
        }
    }

    public int size() {
        return players.size();
    }

    public void addMidiFile(String filePath) {
        MelodyPlayer player = new MelodyPlayer(120, "Microsoft GS Wavetable Synth");
        player.setMelody(new MidiFileToNotes(filePath).getPitchArray());
        player.setRhythm(new MidiFileToNotes(filePath).getRhythmArray());
        players.add(player);
    }

    public void start(int index) {
        players.get(index).play();
    }

    public boolean atEnd(int index) {
        return players.get(index).atEndOfMelody();
    }

    @Override
    public void draw() {
        // Additional functionality if required
    }
}