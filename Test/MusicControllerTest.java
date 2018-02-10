/**
 * Created by Philip on 6/19/2016.
 */

package cs3500.music.controller;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.io.StringWriter;

import cs3500.music.controller.MusicController;
import cs3500.music.model.*;
import cs3500.music.util.MusicReader;
import cs3500.music.view.ConsoleView;
import cs3500.music.view.MidiViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MusicControllerTest {

  @Test
  public void playFromFileTest() {
    StringWriter output = new StringWriter();
    StringReader input = new StringReader("twinkle-twinkle2.txt console");
    MusicController controller = new MusicController(input, output);
    try {
      controller.playMusicFromFile();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals("File path:\n" +
            "View type:\n" +
            "╔════════════════════════════════════════════════════╗\n" +
            "║    C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4 ║\n" +
            "║ 0  X                                               ║\n" +
            "║ 1  |                                               ║\n" +
            "║ 2  X                                               ║\n" +
            "║ 3  |                                               ║\n" +
            "║ 4                                     X            ║\n" +
            "║ 5                                     |            ║\n" +
            "║ 6                                     X            ║\n" +
            "║ 7                                     |            ║\n" +
            "║ 8                                               X  ║\n" +
            "║ 9                                               |  ║\n" +
            "║10                                               X  ║\n" +
            "║11                                               |  ║\n" +
            "║12                                     X            ║\n" +
            "║13                                     |            ║\n" +
            "║14                                     |            ║\n" +
            "║15                                     |            ║\n" +
            "║16                           X                      ║\n" +
            "║17                           |                      ║\n" +
            "║18                           X                      ║\n" +
            "║19                           |                      ║\n" +
            "║20                      X                           ║\n" +
            "║21                      |                           ║\n" +
            "║22                      X                           ║\n" +
            "║23                      |                           ║\n" +
            "║24            X                                     ║\n" +
            "║25            |                                     ║\n" +
            "║26            X                                     ║\n" +
            "║27            |                                     ║\n" +
            "║28  X                                               ║\n" +
            "║29  |                                               ║\n" +
            "║30  |                                               ║\n" +
            "║31  |                                               ║\n" +
            "║32                                     X            ║\n" +
            "║33                                     |            ║\n" +
            "║34                                     X            ║\n" +
            "║35                                     |            ║\n" +
            "║36                           X                      ║\n" +
            "║37                           |                      ║\n" +
            "║38                           X                      ║\n" +
            "║39                           |                      ║\n" +
            "║40                      X                           ║\n" +
            "║41                      |                           ║\n" +
            "║42                      X                           ║\n" +
            "║43                      |                           ║\n" +
            "║44            X                                     ║\n" +
            "║45            |                                     ║\n" +
            "║46            |                                     ║\n" +
            "║47            |                                     ║\n" +
            "║48                                     X            ║\n" +
            "║49                                     |            ║\n" +
            "║50                                     X            ║\n" +
            "║51                                     |            ║\n" +
            "║52                           X                      ║\n" +
            "║53                           |                      ║\n" +
            "║54                           X                      ║\n" +
            "║55                           |                      ║\n" +
            "║56                      X                           ║\n" +
            "║57                      |                           ║\n" +
            "║58                      X                           ║\n" +
            "║59                      |                           ║\n" +
            "║60            X                                     ║\n" +
            "║61            |                                     ║\n" +
            "║62            |                                     ║\n" +
            "║63            |                                     ║\n" +
            "║64  X                                               ║\n" +
            "║65  |                                               ║\n" +
            "║66  X                                               ║\n" +
            "║67  |                                               ║\n" +
            "║68                                     X            ║\n" +
            "║69                                     |            ║\n" +
            "║70                                     X            ║\n" +
            "║71                                     |            ║\n" +
            "║72                                               X  ║\n" +
            "║73                                               |  ║\n" +
            "║74                                               X  ║\n" +
            "║75                                               |  ║\n" +
            "║76                                     X            ║\n" +
            "║77                                     |            ║\n" +
            "║78                                     |            ║\n" +
            "║79                                     |            ║\n" +
            "║80                           X                      ║\n" +
            "║81                           |                      ║\n" +
            "║82                           X                      ║\n" +
            "║83                           |                      ║\n" +
            "║84                      X                           ║\n" +
            "║85                      |                           ║\n" +
            "║86                      X                           ║\n" +
            "║87                      |                           ║\n" +
            "║88            X                                     ║\n" +
            "║89            |                                     ║\n" +
            "║90            X                                     ║\n" +
            "║91            |                                     ║\n" +
            "║92  X                                               ║\n" +
            "║93  |                                               ║\n" +
            "║94  |                                               ║\n" +
            "║95  |                                               ║\n" +
            "╚════════════════════════════════════════════════════╝\n", output.toString());

  }

  @Test
  public void manipulateOctavesTest() throws FileNotFoundException {
    StringWriter output = new StringWriter();
    StringReader input = new StringReader("mary-little-lamb.txt");
    MusicController controller = new MusicController(input, output);
    MusicModel mary = MusicReader.parseFile(new FileReader( new File("mary-little-lamb.txt")),
            new MusicModelImp1.Builder());
    controller.model = mary;
    controller.modelBuilder = new MusicBuilder(mary);
    assertEquals(4, controller.modelBuilder.getHighestOctave());
    controller.modelBuilder.addHigherOctave();
    assertEquals(5, controller.modelBuilder.getHighestOctave());
    controller.modelBuilder.addLowerOctave();
    assertEquals(2, controller.modelBuilder.getLowestOctave());
    controller.modelBuilder.removeHigherOctave();
    controller.modelBuilder.removeHigherOctave();
    //should not remove octave 4 since there are notes within that range
    assertEquals(4, controller.modelBuilder.getHighestOctave());
  }

  @Test
  public void emptyModelTest() {
    StringWriter output = new StringWriter();
    StringReader input = new StringReader("none");
    MusicController controller = new MusicController(input, output);
    //by default, controller's musicBuilder's octave should range from 3 to 5 and go from beats
    // 0 to 8
    assertEquals(5, controller.modelBuilder.getHighestOctave());
    assertEquals(3, controller.modelBuilder.getLowestOctave());
    assertEquals(8, controller.modelBuilder.getHighestBeat());
  }



}
