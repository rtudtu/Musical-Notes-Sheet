package cs3500.music.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Philip on 6/23/2016.
 */
public class MusicBuilder implements IMusicBuilder {
  protected int maxOctave;
  protected int minOctave;
  protected int maxBeat;
  ReadOnlyMusicModel model;

  public MusicBuilder(ReadOnlyMusicModel model) {
    this.model = model;
    if (model.noteList().size() > 0) {
      this.maxOctave = model.highestNote().pitchOrder() / 12;
      this.minOctave = model.lowestNote().pitchOrder() / 12;
      this.maxBeat = model.totalBeats();
    }
    else {
      maxOctave = 5;
      minOctave = 3;
      maxBeat = 8;
    }
  }

  @Override
  public void addHigherOctave() {
    maxOctave++;
  }

  @Override
  public void addLowerOctave() {
    minOctave--;
  }

  @Override
  public void removeHigherOctave() {
    if (maxOctave > (minOctave + 1) && (model.noteList().size() == 0 ||
            maxOctave > model.highestNote().getOctave())) {
      maxOctave--;
    }
  }

  @Override
  public void removeLowerOctave() {
    if (minOctave < (maxOctave - 1) && (model.noteList().size() == 0 ||
            minOctave < model.lowestNote().getOctave())) {
      minOctave++;
    }
  }

  @Override
  public void addBeats() {
    maxBeat+= 4;
  }

  @Override
  public int getHighestOctave() {
    return maxOctave;
  }

  @Override
  public int getLowestOctave() {
    return minOctave;
  }

  @Override
  public int getHighestBeat() {
    return maxBeat;
  }

  public List<Note> noteList() {
    return model.noteList();
  }

  @Override
  public String consoleRender() {
    return model.consoleRender();
  }

  @Override
  public Map<Integer, List<Note>> beatMap() {
    return model.beatMap();
  }

  @Override
  public Note highestNote() {
    return new SimpleNote(Pitch.B, maxOctave, 0, 1);
  }

  @Override
  public int totalBeats() {
    return maxBeat;
  }

  @Override
  public Note lowestNote() {
    return new SimpleNote(Pitch.C, minOctave, 0, 1);
  }

  @Override
  public int getTempo() {
    return model.getTempo();
  }


}
