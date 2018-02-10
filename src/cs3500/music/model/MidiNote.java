package cs3500.music.model;

/**
 * Created by Philip on 6/18/2016.
 */

/**
 * Represents a note in the context of a midi file
 */
public class MidiNote extends Note {
  protected int instrument;
  protected int volume;

  /**
   * Creates a midiNote
   * @param startBeat the beat the note starts on
   * @param endBeat the beat the note is cut-off on (exclusive)
   * @param instrument the instrument oof the note
   * @param pitch the note's pitch Order with respect to Midi's interpreation (0 is C-1)
   * @param volume the note's volume.
   */
  public MidiNote(int startBeat, int endBeat, int instrument, int pitch, int volume) {
    setBeat(startBeat);
    try {
      setDuration(endBeat - startBeat);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("illegal start/end beat: "
              + Integer.toString(startBeat) + " " + Integer.toString(endBeat));
    }
    this.instrument = instrument;
    setPitch(pitchFromMidi(pitch));
    setOctave(octaveFromMidi(pitch));
    this.volume = volume;
  }

  /**
   * Finds a midiNote's pitch from its midi-pitchOrder
   * @param midiPitch Midi's pitch order of this note
   * @return the Pitch of this note
   */
  protected Pitch pitchFromMidi(int midiPitch) {
    Pitch[] pitches = Pitch.values();
    int ordinal = midiPitch % 12;
    return pitches[ordinal];
  }

  /**
   * Gets the octave of this note from the midi pitch ordinal
   * @param midiPitch midi's pitch ordinal of this note
   * @return the octave of this note
   */
  protected int octaveFromMidi(int midiPitch) {
    return (midiPitch / 12) - 1;
  }

  /**
   * the instrument of this note
   * @return the instrument in terms of its channel
   */
  public int getInstrument() {
    return this.instrument;
  }

  /**
   * the volume of this note
   * @return the volume from 0 to 120
   */
  public int getVolume() {
    return this.volume;
  }
}
