package cs3500.music.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Philip on 6/10/2016.
 */

/**
 * Represents a piece of music containing notes
 */
public interface MusicModel extends ReadOnlyMusicModel {
  /**
   * Adds a note to this MusicModel
   *
   * @param n the note to be added
   */
  void addNote(Note n);

  /**
   * Removes a note from the model
   * @param startBeat the beat the note starts on
   * @param pitch the pitch of the note
   */
  void removeNote(int startBeat, int octave, Pitch pitch);

  /**
   * Changes a note in the piece to a new note
   * @param startBeat the beat the note to be replaced starts on
   * @param pitch the pitch of the note to be replaced
   * @param newNote the note to replace it with
   */
  void changeNote(int startBeat, int octave, Pitch pitch, Note newNote);

  /**
   * Adds a song from another model to this model so that they play simultaneously
   *
   * @param m the model with the song to be added
   */
  void playSimultaneous(MusicModel m);

  /**
   * Adds a song from another model to this model so that they play consecutively
   *
   * @param m the model with the song to be played after this one
   */
  void playConsecutively(MusicModel m);

  /**
   * Provides a list of the notes in this MusicModel that can be iterated over
   *
   * @return a list of notes which can be iterated over
   */
  List<Note> noteList();

  /**
   * Creates a askee diagram of the song containing each note's beat and pitch in a table
   *
   * @return a String in the form of the diagram
   */
  String consoleRender();

  /**
   * Creates a map of notes to each beat in the song.
   *
   * @return the map
   */
  Map<Integer, List<Note>> beatMap();

  @Override
  Note highestNote();

  @Override
  int totalBeats();

  @Override
  Note lowestNote();

  @Override
  int getTempo();

  /**
   * Sets the tempo of this piece
   * @param tempo
   */
  void setTempo(int tempo);
}
