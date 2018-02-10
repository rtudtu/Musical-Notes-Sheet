package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import cs3500.music.model.MidiNote;
import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;

/**
 * Created by Philip on 6/24/2016.
 */
public class MidiViewImpl2 implements View {

  ReadOnlyMusicModel model;
  private final Sequencer sequencer;
  protected Sequence sequence;
  protected Track track;

  /**
   * Sets up the sequencer and loads the Notes to the sequence
   *
   * @param model the read-only model where the notes and tempo is taken from
   */
  public MidiViewImpl2(ReadOnlyMusicModel model) {
    this.model = model;
    Sequencer tempSequencer = null;
    try {
      tempSequencer = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    sequencer = tempSequencer;
    Sequence tempsequence = null;
    try {
      tempsequence = new Sequence(Sequence.PPQ, 1);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.sequence = tempsequence;
    this.track = sequence.createTrack();
    try {
      sequencer.setSequence(sequence);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    try {
      refreshSequence();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }


  }

  /**
   * refreshes the Sequencer's track
   */
  void refreshSequence() throws InvalidMidiDataException {
    Sequence newSequence = new Sequence(Sequence.PPQ, 1);
    this.track = newSequence.createTrack();
    for (Note n : model.noteList()) {
      if (n instanceof MidiNote) {
        addNote((MidiNote) n);
      }
    }
    sequencer.setSequence(newSequence);
    sequencer.setTempoInMPQ(model.getTempo());

  }

  @Override
  public void initialize() {
    try {
      sequencer.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
  }

  /**
   * toggles the music so its stops or starts playing. If the music is over, it moves to the
   * beginning.
   */
  public void togglePlaying() throws InvalidMidiDataException {
    if (sequencer.isRunning()) {
      sequencer.stop();
    } else if (sequencer.getTickPosition() >= sequencer.getTickLength()) {

      sequencer.setTickPosition(0);
      sequencer.start();
      refreshSequence();

    } else {
      long tick = sequencer.getTickPosition();
      refreshSequence();
      sequencer.start();
      sequencer.setTickPosition(tick);
      sequencer.setTempoInMPQ(model.getTempo());


    }
  }

  /**
   * gets the tick position of this piece.
   * @return
   */
  public int getTime() {
    if (sequencer.isOpen()) {
      return (int) sequencer.getTickPosition();
    } else {
      return 0;
    }
  }

  /**
   * Adds a note to the sequence
   * @param note The note to be added
   * @throws InvalidMidiDataException
   */
  public void addNote(MidiNote note) throws InvalidMidiDataException {

    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
            note.pitchOrder() + 12, note.getVolume());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
            note.pitchOrder() + 12, note.getVolume());

    track.add(new MidiEvent(start, note.getBeat()));
    track.add(new MidiEvent(stop, note.getBeat() + note.getDuration()));
  }

  public boolean isOpen() {
    return sequencer.isOpen();
  }

  public boolean isPlaying() {
    return sequencer.isRunning();
  }
}
