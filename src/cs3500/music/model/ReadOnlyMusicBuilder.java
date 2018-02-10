package cs3500.music.model;

import java.util.List;

/**
 * Created by Philip on 6/23/2016.
 */
public interface ReadOnlyMusicBuilder extends ReadOnlyMusicModel {

  /**
   * Gets the highest octave of this builder
   * @return the highest octave
   */
  int getHighestOctave();

  /**
   * gets the lowest octave of this builder
   * @return the lowest octave
   */
  int getLowestOctave();

  /**
   * gets the last beat of this builder
   * @return the last beat
   */
  int getHighestBeat();

  /**
   * List of notes in the Model
   * @return the list of notes
   */
  List<Note> noteList();
}
