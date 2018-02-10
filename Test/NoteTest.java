/**
 * Created by Philip on 6/11/2016.
 */

import org.junit.Test;

import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.SimpleNote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NoteTest {
  Note Csharp = new SimpleNote(Pitch.CSHARP, 4, 20, 4);
  Note Csharp2 = new SimpleNote(Pitch.CSHARP, 4, 20, 4);
  Note Fsharp = new SimpleNote(Pitch.FSHARP, 3, 25, 3);

  @Test
  public void toStringTest() {

    assertEquals("C#4", Csharp.toString());
  }

  @Test
  public void equalsTest() {
    assertEquals(Csharp, Csharp2);
  }

  @Test
  public void pitchOrderTest() {
    assertEquals(49, Csharp.pitchOrder());
  }

  @Test
  public void stringFromPitchOrderTest() {
    assertEquals(Csharp.toString(), Note.stringFromNoteOrder(49));
    assertEquals("C0", Note.stringFromNoteOrder(0));

  }

  @Test
  public void compareByPitchTest() {
    //Fsharp is in 3rd octave, csharp is in 4th
    assertTrue(0 > Fsharp.compareByPitch(Csharp));
    //Fsharp comes after Csharp by beat
    assertTrue(0 < Fsharp.compareTo(Csharp));
  }

  @Test(expected = IllegalArgumentException.class)
  public void badBeatTest() {
    Note badBeat = new SimpleNote(Pitch.A, 4, -5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void badDurationTest() {
    Note badDuration = new SimpleNote(Pitch.A, 0, 0, -4);
  }

  //I included negative octaves as valid because it is consistent with how octaves work.
}
