/**
 * Created by Philip on 6/19/2016.
 */

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import cs3500.music.model.*;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MidiViewTest {

  MusicModel initChromaticScale() {
    MusicModel result = new MusicModelImp1();
    Pitch[] pitches = Pitch.values();
    for (int i = 0; i < pitches.length; i++) {
      result.addNote(new MidiNote(2 * i, 2* i + 2, 1, 60 + i, 100));
    }
    return result;
  }

  MusicModel initChromaticScaleSimple() {
    MusicModel result = new MusicModelImp1();
    Pitch[] pitches = Pitch.values();
    for (int i = 0; i < pitches.length; i++) {
      result.addNote(new SimpleNote(pitches[i], 4, 2 * i, 2));
    }
    result.setTempo(10);
    return result;
  }

  @Test
  public void testInitialize() {
    MusicModel model = initChromaticScale();
    model.setTempo(10);
    StringBuilder result = new StringBuilder();
    MidiViewImpl midiView = new MidiViewImpl(model, result);
    midiView.initialize();
    assertEquals("-112 60 100 0\n" +
            "-128 60 100 20\n" +
            "-112 61 100 20\n" +
            "-128 61 100 40\n" +
            "-112 62 100 40\n" +
            "-128 62 100 60\n" +
            "-112 63 100 60\n" +
            "-128 63 100 80\n" +
            "-112 64 100 80\n" +
            "-128 64 100 100\n" +
            "-112 65 100 100\n" +
            "-128 65 100 120\n" +
            "-112 66 100 120\n" +
            "-128 66 100 140\n" +
            "-112 67 100 140\n" +
            "-128 67 100 160\n" +
            "-112 68 100 160\n" +
            "-128 68 100 180\n" +
            "-112 69 100 180\n" +
            "-128 69 100 200\n" +
            "-112 70 100 200\n" +
            "-128 70 100 220\n" +
            "-112 71 100 220\n" +
            "-128 71 100 240\n", result.toString());
  }

  @Test
  public void maryHadALittleLamb() {
    MusicModel model = new MusicModelImp1();
    model.addNote(new MidiNote(0, 2, 1, 64, 72));
    model.addNote(new MidiNote(0, 7, 1, 55, 70));
    model.addNote(new MidiNote(2, 4, 1, 62, 72));
    model.addNote(new MidiNote(4, 6, 1, 60, 71));
    model.addNote(new MidiNote(6, 8, 1, 62, 79));
    model.addNote(new MidiNote(8, 15, 1, 55, 79));
    model.addNote(new MidiNote(8, 10, 1, 64, 85));
    model.addNote(new MidiNote(10, 12, 1, 64, 78));
    model.addNote(new MidiNote(12, 15, 1, 64, 74));
    model.addNote(new MidiNote(16, 24, 1, 55, 77));
    model.addNote(new MidiNote(16, 18, 1, 62, 75));
    model.addNote(new MidiNote(18, 20, 1, 62, 77));
    model.addNote(new MidiNote(20, 24, 1, 62, 75));
    model.addNote(new MidiNote(24, 26, 1, 55, 79));
    model.addNote(new MidiNote(24, 26, 1, 64, 82));
    model.addNote(new MidiNote(26, 28, 1, 67, 84));
    model.addNote(new MidiNote(28, 32, 1, 67, 75));
    model.addNote(new MidiNote(32, 40, 1, 55, 78));
    model.addNote(new MidiNote(32, 34, 1, 64, 73));
    model.addNote(new MidiNote(34, 36, 1, 62, 69));
    model.addNote(new MidiNote(36, 38, 1, 60, 71));
    model.addNote(new MidiNote(38, 40, 1, 62, 80));
    model.addNote(new MidiNote(40, 48, 1, 55, 79));
    model.addNote(new MidiNote(40, 42, 1, 64, 84));
    model.addNote(new MidiNote(42, 44, 1, 64, 76));
    model.addNote(new MidiNote(44, 46, 1, 64, 74));
    model.addNote(new MidiNote(46, 48, 1, 64, 77));
    model.addNote(new MidiNote(48, 56, 1, 55, 78));
    model.addNote(new MidiNote(48, 50, 1, 62, 75));
    model.addNote(new MidiNote(50, 52, 1, 62, 74));
    model.addNote(new MidiNote(52, 54, 1, 64, 81));
    model.addNote(new MidiNote(54, 56, 1, 62, 70));
    model.addNote(new MidiNote(56, 64, 1, 52, 72));
    model.addNote(new MidiNote(56, 64, 1, 60, 73));
    model.setTempo(200000);
    StringBuilder result = new StringBuilder();
    MidiViewImpl midiView = new MidiViewImpl(model, result);
    midiView.initialize();
    assertEquals("-112 55 70 0\n" +
            "-128 55 70 1400000\n" +
            "-112 64 72 0\n" +
            "-128 64 72 400000\n" +
            "-112 62 72 400000\n" +
            "-128 62 72 800000\n" +
            "-112 60 71 800000\n" +
            "-128 60 71 1200000\n" +
            "-112 62 79 1200000\n" +
            "-128 62 79 1600000\n" +
            "-112 55 79 1600000\n" +
            "-128 55 79 3000000\n" +
            "-112 64 85 1600000\n" +
            "-128 64 85 2000000\n" +
            "-112 64 78 2000000\n" +
            "-128 64 78 2400000\n" +
            "-112 64 74 2400000\n" +
            "-128 64 74 3000000\n" +
            "-112 55 77 3200000\n" +
            "-128 55 77 4800000\n" +
            "-112 62 75 3200000\n" +
            "-128 62 75 3600000\n" +
            "-112 62 77 3600000\n" +
            "-128 62 77 4000000\n" +
            "-112 62 75 4000000\n" +
            "-128 62 75 4800000\n" +
            "-112 55 79 4800000\n" +
            "-128 55 79 5200000\n" +
            "-112 64 82 4800000\n" +
            "-128 64 82 5200000\n" +
            "-112 67 84 5200000\n" +
            "-128 67 84 5600000\n" +
            "-112 67 75 5600000\n" +
            "-128 67 75 6400000\n" +
            "-112 55 78 6400000\n" +
            "-128 55 78 8000000\n" +
            "-112 64 73 6400000\n" +
            "-128 64 73 6800000\n" +
            "-112 62 69 6800000\n" +
            "-128 62 69 7200000\n" +
            "-112 60 71 7200000\n" +
            "-128 60 71 7600000\n" +
            "-112 62 80 7600000\n" +
            "-128 62 80 8000000\n" +
            "-112 55 79 8000000\n" +
            "-128 55 79 9600000\n" +
            "-112 64 84 8000000\n" +
            "-128 64 84 8400000\n" +
            "-112 64 76 8400000\n" +
            "-128 64 76 8800000\n" +
            "-112 64 74 8800000\n" +
            "-128 64 74 9200000\n" +
            "-112 64 77 9200000\n" +
            "-128 64 77 9600000\n" +
            "-112 55 78 9600000\n" +
            "-128 55 78 11200000\n" +
            "-112 62 75 9600000\n" +
            "-128 62 75 10000000\n" +
            "-112 62 74 10000000\n" +
            "-128 62 74 10400000\n" +
            "-112 64 81 10400000\n" +
            "-128 64 81 10800000\n" +
            "-112 62 70 10800000\n" +
            "-128 62 70 11200000\n" +
            "-112 52 72 11200000\n" +
            "-128 52 72 12800000\n" +
            "-112 60 73 11200000\n" +
            "-128 60 73 12800000\n", result.toString());
  }

  @Test
  public void testSimpleModel() {
    MusicModel model = initChromaticScaleSimple();
    StringBuilder result = new StringBuilder();
    MidiViewImpl view = new MidiViewImpl(model, result);
    view.initialize();
    assertEquals("-112 60 70 0\n" +
            "-128 60 70 20\n" +
            "-112 61 70 20\n" +
            "-128 61 70 40\n" +
            "-112 62 70 40\n" +
            "-128 62 70 60\n" +
            "-112 63 70 60\n" +
            "-128 63 70 80\n" +
            "-112 64 70 80\n" +
            "-128 64 70 100\n" +
            "-112 65 70 100\n" +
            "-128 65 70 120\n" +
            "-112 66 70 120\n" +
            "-128 66 70 140\n" +
            "-112 67 70 140\n" +
            "-128 67 70 160\n" +
            "-112 68 70 160\n" +
            "-128 68 70 180\n" +
            "-112 69 70 180\n" +
            "-128 69 70 200\n" +
            "-112 70 70 200\n" +
            "-128 70 70 220\n" +
            "-112 71 70 220\n" +
            "-128 71 70 240\n", result.toString());
  }



}
