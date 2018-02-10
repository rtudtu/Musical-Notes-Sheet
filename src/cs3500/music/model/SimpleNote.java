package cs3500.music.model;

/**
 * Created by Philip on 6/11/2016.
 */

/**
 * the simplest representation of a note
 */
public class SimpleNote extends Note {
  public SimpleNote(Pitch pitch, int octave, int beat, int duration) {
    setPitch(pitch);
    setOctave(octave);
    setBeat(beat);
    setDuration(duration);
  }

}
