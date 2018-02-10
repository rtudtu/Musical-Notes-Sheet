package cs3500.music.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Philip on 6/23/2016.
 */
public interface ReadOnlyMusicModel {


  /**
   * A list of the notes in this piece
   * @return the list of notes
   */
  public List<Note> noteList();

  /**
   * The contents of this piece in askee-format
   * @return a askee rendering of this piece
   */
  public String consoleRender();

  /**
   * a map of each beat to the notes starting on that beat
   * @return A Beat-NoteList map
   */
  public Map<Integer, List<Note>> beatMap();

  /**
   * the highest note of this piece
   * @return the highest note
   */
  public Note highestNote();

  /**
   * the total number of beats in the piece
   * @return the total number of beats
   */
  public int totalBeats();

  /**
   * the lowest note of the piece
   * @return the lowest note
   */
  public Note lowestNote();

  /**
   * gets the tempo of the piece
   * @return the tempo in microseconds per beat.
   */
  public int getTempo();


}
