import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

import cs3500.music.model.*;
import cs3500.music.view.ConsoleView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConsoleViewTest {


  //Tests consoleRender works with a regular note, then adding notes onto the model
  @Test
  public void consoleRenderWorks() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.B, 2, 0, 10));
    StringBuffer sb1 = new StringBuffer();
    ConsoleView consoleView1 = new ConsoleView(model, sb1);
    consoleView1.initialize();
    assertTrue(sb1.toString().contains("╔══════╗\n" +
            "║   B2 ║\n" +
            "║0  X  ║\n" +
            "║1  |  ║\n" +
            "║2  |  ║\n" +
            "║3  |  ║\n" +
            "║4  |  ║\n" +
            "║5  |  ║\n" +
            "║6  |  ║\n" +
            "║7  |  ║\n" +
            "║8  |  ║\n" +
            "║9  |  ║\n" +
            "╚══════╝"));
    model.addNote(new SimpleNote(Pitch.E, 3, 0, 10));
    StringBuffer sb2 = new StringBuffer();
    ConsoleView consoleView2 = new ConsoleView(model, sb2);
    consoleView2.initialize();
    assertTrue(sb2.toString().contains("╔═════════════════════════════" +
            "══╗\n" +
            "║   B2   C3  C#3   D3  D#3   E3 ║\n" +
            "║0  X                        X  ║\n" +
            "║1  |                        |  ║\n" +
            "║2  |                        |  ║\n" +
            "║3  |                        |  ║\n" +
            "║4  |                        |  ║\n" +
            "║5  |                        |  ║\n" +
            "║6  |                        |  ║\n" +
            "║7  |                        |  ║\n" +
            "║8  |                        |  ║\n" +
            "║9  |                        |  ║\n" +
            "╚═══════════════════════════════╝"));
    model.addNote(new SimpleNote(Pitch.A, 3, 0, 10));
    StringBuffer sb3 = new StringBuffer();
    ConsoleView consoleView3 = new ConsoleView(model, sb3);
    consoleView3.initialize();
    assertTrue(sb3.toString().contains("╔═════════════════════════════" +
            "═══════════════════════════╗\n" +
            "║   B2   C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3 ║\n" +
            "║0  X                        X                        X  ║\n" +
            "║1  |                        |                        |  ║\n" +
            "║2  |                        |                        |  ║\n" +
            "║3  |                        |                        |  ║\n" +
            "║4  |                        |                        |  ║\n" +
            "║5  |                        |                        |  ║\n" +
            "║6  |                        |                        |  ║\n" +
            "║7  |                        |                        |  ║\n" +
            "║8  |                        |                        |  ║\n" +
            "║9  |                        |                        |  ║\n" +
            "╚════════════════════════════════════════════" +
            "════════════╝"));
  }

  //Tests one single Note's display
  @Test
  public void testOneSimpleNote() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.C, 4, 0, 8));
    StringBuffer sb = new StringBuffer();
    ConsoleView consoleView = new ConsoleView(model, sb);
    consoleView.initialize();
    assertTrue(sb.toString().contains("╔══════╗\n" +
            "║   C4 ║\n" +
            "║0  X  ║\n" +
            "║1  |  ║\n" +
            "║2  |  ║\n" +
            "║3  |  ║\n" +
            "║4  |  ║\n" +
            "║5  |  ║\n" +
            "║6  |  ║\n" +
            "║7  |  ║\n" +
            "╚══════╝"));
  }

  //Test that overlaps still output what they are meant to output
  @Test
  public void getMusicStateWorksWithOverLaps() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.C, 3, 3, 5));
    model.addNote(new SimpleNote(Pitch.C, 3, 4, 5));
    model.addNote(new SimpleNote(Pitch.C, 3, 5, 2));
    StringBuffer sb = new StringBuffer();
    ConsoleView consoleView = new ConsoleView(model, sb);
    consoleView.initialize();
    assertTrue(sb.toString().contains("╔══════╗\n" +
            "║   C3 ║\n" +
            "║0     ║\n" +
            "║1     ║\n" +
            "║2     ║\n" +
            "║3  X  ║\n" +
            "║4  X  ║\n" +
            "║5  X  ║\n" +
            "║6  |  ║\n" +
            "║7  |  ║\n" +
            "║8  |  ║\n" +
            "╚══════╝"));
  }

  @Test
  public void getMusicStateWorksWithOverLaps2() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.C, 3, 0, 5));
    model.addNote(new SimpleNote(Pitch.C, 3, 3, 5));
    model.addNote(new SimpleNote(Pitch.C, 3, 6, 3));
    StringBuffer sb = new StringBuffer();
    ConsoleView consoleView = new ConsoleView(model, sb);
    consoleView.initialize();
    assertTrue(sb.toString().contains("╔══════╗\n" +
            "║   C3 ║\n" +
            "║0  X  ║\n" +
            "║1  |  ║\n" +
            "║2  |  ║\n" +
            "║3  X  ║\n" +
            "║4  |  ║\n" +
            "║5  |  ║\n" +
            "║6  X  ║\n" +
            "║7  |  ║\n" +
            "║8  |  ║\n" +
            "╚══════╝"));
  }

  //consoleRender works when music is played simultaneously from 2 different models
  @Test
  public void consoleRenderWorksWithSimultaneous() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.C, 3, 0, 5));
    model.addNote(new SimpleNote(Pitch.CSHARP, 3, 1, 5));
    MusicModelImp1 model2 = new MusicModelImp1();
    model2.addNote(new SimpleNote(Pitch.D, 3, 2, 2));
    model2.addNote(new SimpleNote(Pitch.DSHARP, 3, 3, 2));
    model.playSimultaneous(model2);
    StringBuffer sb = new StringBuffer();
    ConsoleView consoleView = new ConsoleView(model, sb);
    consoleView.initialize();
    assertTrue(sb.toString().contains("╔═════════════════════╗\n" +
            "║   C3  C#3   D3  D#3 ║\n" +
            "║0  X                 ║\n" +
            "║1  |    X            ║\n" +
            "║2  |    |    X       ║\n" +
            "║3  |    |    |    X  ║\n" +
            "║4  |    |         |  ║\n" +
            "║5       |            ║\n" +
            "╚═════════════════════╝"));
  }

  //consoleRender works when music is played consecutively from 2 different models
  @Test
  public void consoleRenderWorksWithConsecutive() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.C, 3, 0, 5));
    model.addNote(new SimpleNote(Pitch.CSHARP, 3, 1, 5));
    MusicModelImp1 model2 = new MusicModelImp1();
    model2.addNote(new SimpleNote(Pitch.D, 3, 2, 2));
    model2.addNote(new SimpleNote(Pitch.DSHARP, 3, 3, 2));
    model.playConsecutively(model2);
    StringBuffer sb = new StringBuffer();
    ConsoleView consoleView = new ConsoleView(model, sb);
    consoleView.initialize();
    assertTrue(sb.toString().contains("╔══════════════════════╗\n" +
            "║    C3  C#3   D3  D#3 ║\n" +
            "║ 0  X                 ║\n" +
            "║ 1  |    X            ║\n" +
            "║ 2  |    |            ║\n" +
            "║ 3  |    |            ║\n" +
            "║ 4  |    |            ║\n" +
            "║ 5       |            ║\n" +
            "║ 6                    ║\n" +
            "║ 7                    ║\n" +
            "║ 8            X       ║\n" +
            "║ 9            |    X  ║\n" +
            "║10                 |  ║\n" +
            "╚══════════════════════╝"));
  }

  //Tests consoleRender with Mary Had a Littlle Lamb
  @Test
  public void maryHadALittleLamb() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.E, 3, 56, 8));
    model.addNote(new SimpleNote(Pitch.G, 3, 0, 7));
    model.addNote(new SimpleNote(Pitch.G, 3, 8, 7));
    model.addNote(new SimpleNote(Pitch.G, 3, 16, 8));
    model.addNote(new SimpleNote(Pitch.G, 3, 24, 2));
    model.addNote(new SimpleNote(Pitch.G, 3, 32, 8));
    model.addNote(new SimpleNote(Pitch.G, 3, 40, 8));
    model.addNote(new SimpleNote(Pitch.G, 3, 48, 8));
    model.addNote(new SimpleNote(Pitch.E, 4, 0, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 8, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 10, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 12, 3));
    model.addNote(new SimpleNote(Pitch.E, 4, 24, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 32, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 40, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 42, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 44, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 46, 2));
    model.addNote(new SimpleNote(Pitch.E, 4, 52, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 2, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 6, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 16, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 18, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 20, 4));
    model.addNote(new SimpleNote(Pitch.D, 4, 34, 11));
    model.addNote(new SimpleNote(Pitch.D, 4, 38, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 48, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 50, 2));
    model.addNote(new SimpleNote(Pitch.D, 4, 54, 2));
    model.addNote(new SimpleNote(Pitch.C, 4, 4, 2));
    model.addNote(new SimpleNote(Pitch.C, 4, 36, 2));
    model.addNote(new SimpleNote(Pitch.C, 4, 56, 8));
    model.addNote(new SimpleNote(Pitch.G, 4, 26, 2));
    model.addNote(new SimpleNote(Pitch.G, 4, 28, 4));
    StringBuffer sb = new StringBuffer();
    ConsoleView consoleView = new ConsoleView(model, sb);
    consoleView.initialize();
    assertTrue(sb.toString().contains(
            "╔═══════════════════════════════════════════" +
                    "═══════════════════════════════════════╗\n" +
                    "║    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   " +
                    "F4  F#4   G4 ║\n" +
                    "║ 0                 X                                            X    " +
                    "             ║\n" +
                    "║ 1                 |                                            |    " +
                    "             ║\n" +
                    "║ 2                 |                                  X              " +
                    "             ║\n" +
                    "║ 3                 |                                  |              " +
                    "             ║\n" +
                    "║ 4                 |                        X                        " +
                    "             ║\n" +
                    "║ 5                 |                        |                        " +
                    "             ║\n" +
                    "║ 6                 |                                  X              " +
                    "             ║\n" +
                    "║ 7                                                    |              " +
                    "             ║\n" +
                    "║ 8                 X                                            X    " +
                    "             ║\n" +
                    "║ 9                 |                                            |    " +
                    "             ║\n" +
                    "║10                 |                                            X    " +
                    "             ║\n" +
                    "║11                 |                                            |    " +
                    "             ║\n" +
                    "║12                 |                                            X    " +
                    "             ║\n" +
                    "║13                 |                                            |    " +
                    "             ║\n" +
                    "║14                 |                                            |    " +
                    "             ║\n" +
                    "║15                                                                   " +
                    "             ║\n" +
                    "║16                 X                                  X              " +
                    "             ║\n" +
                    "║17                 |                                  |              " +
                    "             ║\n" +
                    "║18                 |                                  X              " +
                    "             ║\n" +
                    "║19                 |                                  |              " +
                    "             ║\n" +
                    "║20                 |                                  X              " +
                    "             ║\n" +
                    "║21                 |                                  |              " +
                    "             ║\n" +
                    "║22                 |                                  |              " +
                    "             ║\n" +
                    "║23                 |                                  |              " +
                    "             ║\n" +
                    "║24                 X                                            X    " +
                    "             ║\n" +
                    "║25                 |                                            |    " +
                    "             ║\n" +
                    "║26                                                                   " +
                    "          X  ║\n" +
                    "║27                                                                   " +
                    "          |  ║\n" +
                    "║28                                                                   " +
                    "          X  ║\n" +
                    "║29                                                                   " +
                    "          |  ║\n" +
                    "║30                                                                   " +
                    "          |  ║\n" +
                    "║31                                                                   " +
                    "          |  ║\n" +
                    "║32                 X                                            X    " +
                    "             ║\n" +
                    "║33                 |                                            |    " +
                    "             ║\n" +
                    "║34                 |                                  X              " +
                    "             ║\n" +
                    "║35                 |                                  |              " +
                    "             ║\n" +
                    "║36                 |                        X         |              " +
                    "             ║\n" +
                    "║37                 |                        |         |              " +
                    "             ║\n" +
                    "║38                 |                                  X              " +
                    "             ║\n" +
                    "║39                 |                                  |              " +
                    "             ║\n" +
                    "║40                 X                                  |         X    " +
                    "             ║\n" +
                    "║41                 |                                  |         |    " +
                    "             ║\n" +
                    "║42                 |                                  |         X    " +
                    "             ║\n" +
                    "║43                 |                                  |         |    " +
                    "             ║\n" +
                    "║44                 |                                  |         X    " +
                    "             ║\n" +
                    "║45                 |                                            |    " +
                    "             ║\n" +
                    "║46                 |                                            X    " +
                    "             ║\n" +
                    "║47                 |                                            |    " +
                    "             ║\n" +
                    "║48                 X                                  X              " +
                    "             ║\n" +
                    "║49                 |                                  |              " +
                    "             ║\n" +
                    "║50                 |                                  X              " +
                    "             ║\n" +
                    "║51                 |                                  |              " +
                    "             ║\n" +
                    "║52                 |                                            X    " +
                    "             ║\n" +
                    "║53                 |                                            |    " +
                    "             ║\n" +
                    "║54                 |                                  X              " +
                    "             ║\n" +
                    "║55                 |                                  |              " +
                    "             ║\n" +
                    "║56  X                                       X                        " +
                    "             ║\n" +
                    "║57  |                                       |                        " +
                    "             ║\n" +
                    "║58  |                                       |                        " +
                    "             ║\n" +
                    "║59  |                                       |                        " +
                    "             ║\n" +
                    "║60  |                                       |                        " +
                    "             ║\n" +
                    "║61  |                                       |                        " +
                    "             ║\n" +
                    "║62  |                                       |                        " +
                    "             ║\n" +
                    "║63  |                                       |                        " +
                    "             ║\n" +
                    "╚═══════════════════════════════════════" +
                    "════════════════════════════════════════" +
                    "═══╝"));


  }

  //Tests consoleRender with Twinkle Twinkle Little Star
  @Test
  public void twinkleTwinkleLittleStar() {
    MusicModelImp1 model = new MusicModelImp1();
    model.addNote(new SimpleNote(Pitch.ASHARP, 2, 0, 2));
    model.addNote(new SimpleNote(Pitch.ASHARP, 2, 2, 2));
    model.addNote(new SimpleNote(Pitch.C, 3, 4, 2));
    model.addNote(new SimpleNote(Pitch.C, 3, 6, 2));
    model.addNote(new SimpleNote(Pitch.D, 3, 8, 2));
    model.addNote(new SimpleNote(Pitch.D, 3, 10, 2));
    model.addNote(new SimpleNote(Pitch.C, 3, 12, 3));
    model.addNote(new SimpleNote(Pitch.B, 3, 16, 2));
    model.addNote(new SimpleNote(Pitch.B, 3, 18, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 20, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 22, 2));
    model.addNote(new SimpleNote(Pitch.G, 3, 24, 2));
    model.addNote(new SimpleNote(Pitch.G, 3, 26, 2));
    model.addNote(new SimpleNote(Pitch.F, 3, 28, 3));
    model.addNote(new SimpleNote(Pitch.C, 4, 32, 2));
    model.addNote(new SimpleNote(Pitch.C, 4, 34, 2));
    model.addNote(new SimpleNote(Pitch.B, 3, 36, 2));
    model.addNote(new SimpleNote(Pitch.B, 3, 38, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 40, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 42, 2));
    model.addNote(new SimpleNote(Pitch.G, 3, 44, 3));
    model.addNote(new SimpleNote(Pitch.C, 4, 48, 2));
    model.addNote(new SimpleNote(Pitch.C, 4, 50, 2));
    model.addNote(new SimpleNote(Pitch.B, 3, 52, 2));
    model.addNote(new SimpleNote(Pitch.B, 3, 54, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 56, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 58, 2));
    model.addNote(new SimpleNote(Pitch.G, 3, 60, 3));
    model.addNote(new SimpleNote(Pitch.ASHARP, 2, 64, 2));
    model.addNote(new SimpleNote(Pitch.ASHARP, 2, 66, 2));
    model.addNote(new SimpleNote(Pitch.C, 3, 68, 2));
    model.addNote(new SimpleNote(Pitch.C, 3, 70, 2));
    model.addNote(new SimpleNote(Pitch.D, 3, 72, 2));
    model.addNote(new SimpleNote(Pitch.D, 3, 74, 2));
    model.addNote(new SimpleNote(Pitch.C, 3, 76, 3));
    model.addNote(new SimpleNote(Pitch.B, 3, 80, 2));
    model.addNote(new SimpleNote(Pitch.B, 3, 82, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 84, 2));
    model.addNote(new SimpleNote(Pitch.A, 3, 86, 2));
    model.addNote(new SimpleNote(Pitch.G, 3, 88, 2));
    model.addNote(new SimpleNote(Pitch.G, 3, 90, 2));
    model.addNote(new SimpleNote(Pitch.F, 3, 92, 3));
    StringBuffer sb = new StringBuffer();
    ConsoleView consoleView = new ConsoleView(model, sb);
    consoleView.initialize();
    System.out.println(sb);
    assertTrue(sb.toString().contains("╔═════════════════════════════" +
            "═════════════════════════════════════════════" +
            "═══╗\n" +
            "║   A#2   B2   C3  C#3   D3  D#3   E3   F3  F#3   G3  G#3   A3  A#3   B3   C4 ║\n" +
            "║ 0  X                                                                        ║\n" +
            "║ 1  |                                                                        ║\n" +
            "║ 2  X                                                                        ║\n" +
            "║ 3  |                                                                        ║\n" +
            "║ 4            X                                                              ║\n" +
            "║ 5            |                                                              ║\n" +
            "║ 6            X                                                              ║\n" +
            "║ 7            |                                                              ║\n" +
            "║ 8                      X                                                    ║\n" +
            "║ 9                      |                                                    ║\n" +
            "║10                      X                                                    ║\n" +
            "║11                      |                                                    ║\n" +
            "║12            X                                                              ║\n" +
            "║13            |                                                              ║\n" +
            "║14            |                                                              ║\n" +
            "║15                                                                           ║\n" +
            "║16                                                                   X       ║\n" +
            "║17                                                                   |       ║\n" +
            "║18                                                                   X       ║\n" +
            "║19                                                                   |       ║\n" +
            "║20                                                         X                 ║\n" +
            "║21                                                         |                 ║\n" +
            "║22                                                         X                 ║\n" +
            "║23                                                         |                 ║\n" +
            "║24                                               X                           ║\n" +
            "║25                                               |                           ║\n" +
            "║26                                               X                           ║\n" +
            "║27                                               |                           ║\n" +
            "║28                                     X                                     ║\n" +
            "║29                                     |                                     ║\n" +
            "║30                                     |                                     ║\n" +
            "║31                                                                           ║\n" +
            "║32                                                                        X  ║\n" +
            "║33                                                                        |  ║\n" +
            "║34                                                                        X  ║\n" +
            "║35                                                                        |  ║\n" +
            "║36                                                                   X       ║\n" +
            "║37                                                                   |       ║\n" +
            "║38                                                                   X       ║\n" +
            "║39                                                                   |       ║\n" +
            "║40                                                         X                 ║\n" +
            "║41                                                         |                 ║\n" +
            "║42                                                         X                 ║\n" +
            "║43                                                         |                 ║\n" +
            "║44                                               X                           ║\n" +
            "║45                                               |                           ║\n" +
            "║46                                               |                           ║\n" +
            "║47                                                                           ║\n" +
            "║48                                                                        X  ║\n" +
            "║49                                                                        |  ║\n" +
            "║50                                                                        X  ║\n" +
            "║51                                                                        |  ║\n" +
            "║52                                                                   X       ║\n" +
            "║53                                                                   |       ║\n" +
            "║54                                                                   X       ║\n" +
            "║55                                                                   |       ║\n" +
            "║56                                                         X                 ║\n" +
            "║57                                                         |                 ║\n" +
            "║58                                                         X                 ║\n" +
            "║59                                                         |                 ║\n" +
            "║60                                               X                           ║\n" +
            "║61                                               |                           ║\n" +
            "║62                                               |                           ║\n" +
            "║63                                                                           ║\n" +
            "║64  X                                                                        ║\n" +
            "║65  |                                                                        ║\n" +
            "║66  X                                                                        ║\n" +
            "║67  |                                                                        ║\n" +
            "║68            X                                                              ║\n" +
            "║69            |                                                              ║\n" +
            "║70            X                                                              ║\n" +
            "║71            |                                                              ║\n" +
            "║72                      X                                                    ║\n" +
            "║73                      |                                                    ║\n" +
            "║74                      X                                                    ║\n" +
            "║75                      |                                                    ║\n" +
            "║76            X                                                              ║\n" +
            "║77            |                                                              ║\n" +
            "║78            |                                                              ║\n" +
            "║79                                                                           ║\n" +
            "║80                                                                   X       ║\n" +
            "║81                                                                   |       ║\n" +
            "║82                                                                   X       ║\n" +
            "║83                                                                   |       ║\n" +
            "║84                                                         X                 ║\n" +
            "║85                                                         |                 ║\n" +
            "║86                                                         X                 ║\n" +
            "║87                                                         |                 ║\n" +
            "║88                                               X                           ║\n" +
            "║89                                               |                           ║\n" +
            "║90                                               X                           ║\n" +
            "║91                                               |                           ║\n" +
            "║92                                     X                                     ║\n" +
            "║93                                     |                                     ║\n" +
            "║94                                     |                                     ║\n" +
            "╚════════════════════════════════════════════" +
            "═════════════════════════════════╝"));
  }

}
