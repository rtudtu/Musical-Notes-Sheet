package cs3500.music.model;

/**
 * Created by Philip on 6/9/2016.
 */

import java.util.Objects;

/**
 * Represents a note in a piece of music
 */
public abstract class Note implements Comparable<Note> {
  protected Pitch pitch;
  protected int octave;
  protected int beat;
  protected int duration;

  /**
   * Creates a String representation of a pitch and octave without requiring a note object.
   *
   * @param i The pitch order of the note represented. 0 is C0, 12 is C1, 13 is C#1, etc
   * @return the String representing the pitch/octave of the note of the given degree.
   */
  public static String stringFromNoteOrder(int i) {
    Pitch[] pitches = Pitch.values();
    int pitchIndex = i % 12;
    int octave = i / 12;
    if (pitchIndex < 0) {
      pitchIndex += 12;
    }
    if (i < 0) {
      octave = ((i + 1) / 12) - 1;
    }
    return pitches[pitchIndex].getSymbol() + Integer.toString(octave);
  }

  /**
   * @return the pitch of this note
   */
  public Pitch getPitch() {
    return this.pitch;
  }

  /**
   * sets the pitch of this note
   *
   * @param p the desired pitch
   */
  public void setPitch(Pitch p) {
    this.pitch = p;
  }

  /**
   * @return the duration of this note
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * sets the duration of this note
   *
   * @param d the desired duration. Cannot be negative.
   */
  public void setDuration(int d) {
    if (d < 0) {
      throw new IllegalArgumentException("Duration must be a positive number");
    }

    this.duration = d;
    if (d == 0) {
      this.duration = 1;
    }
  }

  /**
   * @return the octave of this note
   */
  public int getOctave() {
    return this.octave;
  }

  /**
   * sets the octave of this note
   *
   * @param o the desired octave
   */
  public void setOctave(int o) {
    this.octave = o;
  }

  /**
   * @return the beat of this note
   */
  public int getBeat() {
    return this.beat;
  }

  /**
   * sets the beat of this note
   *
   * @param beat the beat this note starts on. Must be 0 or greater
   */
  public void setBeat(int beat) {
    if (beat < 0) {
      throw new IllegalArgumentException("Beat most be positive number");
    }
    this.beat = beat;
  }

  /**
   * returns the string representation of the note
   *
   * @return the string representation of the note
   */
  public String toString() {
    return pitch.getSymbol() + Integer.toString(octave);
  }

  public int pitchOrder() {
    return getPitch().ordinal() + 12 * getOctave();
  }

  /**
   * compares two notes by pitch
   *
   * @param n the note to compare this note with
   * @return negative number if this is lower than that, 0 if they are the same, positive if this
   * is higher than that
   */
  public int compareByPitch(Note n) {
    return this.pitchOrder() - n.pitchOrder();
  }

  /**
   * compares two notes by order they occur in a song
   *
   * @param n the note to be compared to this one
   * @return a negative integer if this note comes before this one, 0 if they occur at the same
   * time and are the same duration and pitch, this note comes after that one. If two notes occur
   * on the same beat but are different notes, they are sorted by pitch, then duration.
   */
  public int compareTo(Note n) {
    if (this.getBeat() != n.getBeat()) {
      return this.getBeat() - n.getBeat();
    }
    if (this.pitchOrder() != n.pitchOrder()) {
      return compareByPitch(n);
    } else {
      return getDuration() - n.getDuration();
    }
  }

  /**
   * whether or not the given object is the same is this note
   *
   * @param o the object to be compared to this note
   * @return whether or not the given object is the same as this note
   */
  public boolean equals(Object o) {
    return ((o instanceof Note) && (0 == compareTo((Note) o)));
  }

  /**
   * Gives a unique integer value to distinct notes
   *
   * @return the unique integer value of this note
   */
  public int hashcode() {
    return Objects.hash(getBeat(), pitchOrder(), getDuration());
  }


}