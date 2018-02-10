package cs3500.music.model;

import java.util.List;

/**
 * Created by Philip on 6/23/2016.
 */

public interface IMusicBuilder extends ReadOnlyMusicBuilder
{
  /**
   * Raises the octave ceiling of the builder
   */
  void addHigherOctave();

  /**
   * Lowers the octave floor of the builder
   */
  void addLowerOctave();

  /**
   * Lowers the octave ceiling of the piece
   */
  void removeHigherOctave();

  /**
   * Raises the Octave floor of the piece
   */
  void removeLowerOctave();

  /**
   * Adds beats to the builder for notes to be added
   */
  void addBeats();

  /**
   * Gets the octave ceiling of the builder
   * @return
   */
  int getHighestOctave();

  /**
   * Gets the octave floor of this builder
   * @return the octave floor
   */
  int getLowestOctave();

  /**
   * Gets the highest beat of this octave
   * @return
   */
  int getHighestBeat();

  /**
   * Gets the list of notes in the contained model
   * @return
   */
  List<Note> noteList();
}