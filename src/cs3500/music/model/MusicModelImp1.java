package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs3500.music.util.CompositionBuilder;

/**
 * Created by Philip on 6/9/2016.
 */
public class MusicModelImp1 implements MusicModel {
  List<Note> notes;
  int tempo;

  public MusicModelImp1() {
    notes = new ArrayList<Note>();
  }

  /**
   * @return the number of beats in the song
   */
  public int totalBeats() {
    if (notes.isEmpty()) {
      throw new IllegalArgumentException("there are no beats in this song");
    }
    int maxBeat = 0;
    for (Note n : notes) {
      maxBeat = Math.max(maxBeat, n.getBeat() + n.getDuration() - 1);
    }
    return maxBeat;
  }

  /**
   * @return the lowest Note in the song by pitch
   */
  public Note lowestNote() {
    if (notes.isEmpty()) {
      throw new IllegalArgumentException("there are no beats in this song");
    }
    Note lowestNote = notes.get(0);
    for (Note n : notes) {
      if (n.compareByPitch(lowestNote) < 0) {
        lowestNote = n;
      }
    }
    return lowestNote;
  }

  /**
   * @return the highest note in the song by pitch
   */
  public Note highestNote() {
    if (notes.isEmpty()) {
      throw new IllegalArgumentException("there are no beats in this song");
    }
    Note highestNote = notes.get(0);
    for (Note n : notes) {
      if (n.compareByPitch(highestNote) > 0) {
        highestNote = n;
      }
    }
    return highestNote;
  }

  @Override
  public void addNote(Note n) {
    notes.add(n);
    Collections.sort(notes);
  }

  @Override
  public void removeNote(int startBeat, int octave, Pitch pitch) {
    if (startBeat < 0) {
      throw new IllegalArgumentException("Invalid beat,");
    }
    for (Note n : notes) {
      if (startBeat == n.getBeat() && n.getPitch() == pitch && n.getOctave() == octave) {
        notes.remove(n);
        break;
      }
    }
  }

  @Override
  public void changeNote(int startBeat, int octave, Pitch pitch, Note newNote) {
    if (startBeat < 0 || startBeat >= totalBeats()) {
      throw new IllegalArgumentException("Note beat is out of bounds");
    }
    removeNote(startBeat, octave, pitch);
    addNote(newNote);
  }

  @Override
  public void playSimultaneous(MusicModel m) {
    for (Note n : m.noteList()) {
      addNote(n);
    }

  }

  @Override
  public void playConsecutively(MusicModel m) {
    int totalBeats = this.totalBeats();
    for (Note n : m.noteList()) {
      int originalBeat = n.getBeat();
      n.setBeat(originalBeat + totalBeats + 1);
      addNote(n);
    }
  }

  @Override
  public List<Note> noteList() {
    return new ArrayList<Note>(notes);
  }

  @Override
  public String consoleRender() {
    StringBuilder result = new StringBuilder();
    int totalBeats = this.totalBeats();
    int beatColumnWidth = Integer.toString(totalBeats).length();
    int lowestPitchOrder = lowestNote().pitchOrder();
    //makes first row
    result.append('║');
    for (int i = 0; i < beatColumnWidth; i++) {
      result.append(' ');
    }
    for (int i = lowestPitchOrder; i <= highestNote().pitchOrder(); i++) {
      String noteString = Note.stringFromNoteOrder(i);
      if (noteString.length() == 2) {
        result.append("  " + noteString + " ");
      } else if (noteString.length() == 3) {
        result.append(" " + noteString + " ");
      } else if (noteString.length() == 4) {
        result.append(" " + noteString);
      } else {
        result.append(noteString);
      }
    }
    result.append('║');
    result.append('\n');
    int rowLength = result.length();
    StringBuilder borderCeiling = new StringBuilder();
    //makes the top of the box.
    borderCeiling.append('╔');
    for (int i = 0; i < rowLength - 3; i++) {
      borderCeiling.append('═');
    }
    borderCeiling.append("╗\n");
    result.insert(0, borderCeiling);
    //make the rest of the rows
    for (int i = 0; i <= totalBeats; i++) {
      result.append('║');
      int spacesBeforeNumber = beatColumnWidth - Integer.toString(i).length();
      for (int s = 0; s < spacesBeforeNumber; s++) {
        result.append(' ');
      }
      result.append(Integer.toString(i));
      for (int s = 0; s < rowLength - beatColumnWidth - 3; s++) {
        result.append(' ');
      }
      result.append("║\n");
    }
    //for each note, I must mark the correct row and column.
    for (Note n : notes) {
      int rowStart = (n.getBeat() + 2) * rowLength;
      int column = (beatColumnWidth + 1 + 2 + 5 * (n.pitchOrder() - lowestPitchOrder));
      int targetIndex = rowStart + column;
      result.setCharAt(targetIndex, 'X');
      for (int i = 1; i < n.getDuration(); i++) {
        targetIndex = targetIndex + rowLength;
        result.setCharAt(targetIndex, '|');
      }
    }
    StringBuilder borderFloor = new StringBuilder();
    borderFloor.append('╚');
    for (int i = 0; i < rowLength - 3; i++) {
      borderFloor.append('═');
    }
    borderFloor.append("╝\n");
    result.append(borderFloor);

    return result.toString();
  }

  @Override
  public Map<Integer, List<Note>> beatMap() {
    Map<Integer, List<Note>> map = new HashMap<Integer, List<Note>>();
    int totalBeats = totalBeats();
    for (int i = 0; i <= totalBeats; i++) {
      map.put(i, new ArrayList<Note>());
    }
    for (Note n : notes) {
      map.get(n.getBeat()).add(n);
    }
    return map;
  }

  public int getTempo() {
    return this.tempo;
  }

  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  public static final class Builder implements CompositionBuilder<MusicModel> {
    protected List<Note> notes;
    protected int tempo;

    public Builder() {
      notes = new ArrayList<Note>();
    }


    @Override
    public MusicModel build() {
      MusicModelImp1 model = new MusicModelImp1();
      model.setTempo(this.tempo);

      for (Note n : notes) {
        model.addNote(n);
      }

      return model;
    }

    @Override
    public CompositionBuilder<MusicModel> setTempo(int tempo) {
      this.tempo = tempo;
      return this;

    }

    @Override
    public CompositionBuilder<MusicModel> addNote(int start, int end,
                                                  int instrument, int pitch, int volume) {
      notes.add(new MidiNote(start, end, instrument, pitch, volume));
      return this;
    }
  }
}
