package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;

import cs3500.music.model.MidiNote;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;
import cs3500.music.util.MockSynthesizer;

/**
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements View {
  private final Synthesizer synth;
  private final Receiver receiver;
  private ReadOnlyMusicModel model;

  public MidiViewImpl(MusicModel model) {
    Synthesizer synthTemp = null;
    Receiver receiverTemp = null;
    try {
      synthTemp = MidiSystem.getSynthesizer();
      receiverTemp = synthTemp.getReceiver();
      synthTemp.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = synthTemp;
    this.receiver = receiverTemp;
    this.model = model;
  }

  public MidiViewImpl(MusicModel model, StringBuilder builder) {
    Synthesizer synthTemp = null;
    Receiver receiverTemp = null;
    try {
      synthTemp = new MockSynthesizer(builder);
      receiverTemp = synthTemp.getReceiver();
      synthTemp.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    this.synth = synthTemp;
    this.receiver = receiverTemp;
    this.model = model;
  }

  /**
   * Relevant classes and methods from the javax.sound.midi library: <ul> <li>{@link
   * MidiSystem#getSynthesizer()}</li> <li>{@link Synthesizer} <ul> <li>{@link
   * Synthesizer#open()}</li> <li>{@link Synthesizer#getReceiver()}</li> <li>{@link
   * Synthesizer#getChannels()}</li> </ul> </li> <li>{@link Receiver} <ul> <li>{@link
   * Receiver#send(MidiMessage, long)}</li> <li>{@link Receiver#close()}</li> </ul> </li>
   * <li>{@link MidiMessage}</li> <li>{@link ShortMessage}</li> <li>{@link MidiChannel} <ul>
   * <li>{@link MidiChannel#getProgram()}</li> <li>{@link MidiChannel#programChange(int)}</li>
   * </ul> </li> </ul>
   *
   * @see <a href="https://en.wikipedia.org/wiki/General_MIDI">
   *   https://en.wikipedia.org/wiki/General_MIDI
   * </a>
   */

  public void playNote(MidiNote note) throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.getInstrument() - 1,
            note.pitchOrder() + 12, note.getVolume());
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.getInstrument() - 1,
            note.pitchOrder() + 12, note.getVolume());
    this.receiver.send(start, note.getBeat() * model.getTempo());
    this.receiver.send(stop,
            note.getBeat() * model.getTempo() + model.getTempo() * note.getDuration());
  }

  @Override
  public void initialize() {
    for (Note n : model.noteList()) {
      if (n instanceof MidiNote) {
        try {
          playNote((MidiNote) n);
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
      } else {
        MidiNote midiVersion = new MidiNote(n.getBeat(),
                n.getBeat() + n.getDuration(), 1, n.pitchOrder() + 12, 70);
        try {
          playNote(midiVersion);
        } catch (InvalidMidiDataException e) {
          e.printStackTrace();
        }
      }
    }

    this.receiver.close(); // Only call this once you're done playing *all* notes
  }

  ;
}
