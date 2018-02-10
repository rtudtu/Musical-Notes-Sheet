///**
// * Created by Philip on 6/11/2016.
// */
//import org.junit.Test;
//
//import cs3500.music.model.*;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class MusicModelTest {
//  MusicModel initChromaticScale() {
//    MusicModel result = new MusicModelImp1();
//    Pitch[] pitches = Pitch.values();
//    for (int i = 0; i < pitches.length; i++) {
//      result.addNote(new SimpleNote(pitches[i], 4, 2 * i, 2));
//    }
//    return result;
//  }
//
//  MusicModel initBackwardsChromaticScale() {
//    MusicModel result = new MusicModelImp1();
//    Pitch[] pitches = Pitch.values();
//    for (int i = pitches.length - 1; i >= 0; i--) {
//      result.addNote(new SimpleNote(pitches[i], 4, 2 * (pitches.length - 1 - i), 2));
//    }
//    return result;
//  }
//
//  @Test
//  public void testConsoleRender() {
//    //note that although it doesn't look aligned here in intellij,
//    // it is aligned on notepad/in console
//    assertEquals(
//            "╔════════════════════════════════" +
//                    "══════════════════════════════╗\n" +
//                    "║    C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4 ║\n" +
//                    "║ 0  X                                                         ║\n" +
//                    "║ 1  |                                                         ║\n" +
//                    "║ 2       X                                                    ║\n" +
//                    "║ 3       |                                                    ║\n" +
//                    "║ 4            X                                               ║\n" +
//                    "║ 5            |                                               ║\n" +
//                    "║ 6                 X                                          ║\n" +
//                    "║ 7                 |                                          ║\n" +
//                    "║ 8                      X                                     ║\n" +
//                    "║ 9                      |                                     ║\n" +
//                    "║10                           X                                ║\n" +
//                    "║11                           |                                ║\n" +
//                    "║12                                X                           ║\n" +
//                    "║13                                |                           ║\n" +
//                    "║14                                     X                      ║\n" +
//                    "║15                                     |                      ║\n" +
//                    "║16                                          X                 ║\n" +
//                    "║17                                          |                 ║\n" +
//                    "║18                                               X            ║\n" +
//                    "║19                                               |            ║\n" +
//                    "║20                                                    X       ║\n" +
//                    "║21                                                    |       ║\n" +
//                    "║22                                                         X  ║\n" +
//                    "║23                                                         |  ║\n" +
//                    "╚════════════════════════════════" +
//                    "══════════════════════════════╝\n",
//            initChromaticScale().consoleRender());
//  }
//
//  @Test
//  public void testAddNote() {
//    MusicModel ChromaticScale2 = initChromaticScale();
//    ChromaticScale2.addNote(new SimpleNote(Pitch.CSHARP, 5, 24, 80));
//    assertEquals("╔════════════════════════════════" +
//                    "═════════════════════════════════════════╗\n" +
//                    "║     C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5 ║\n" +
//                    "║  0  X                                                                   ║\n" +
//                    "║  1  |                                                                   ║\n" +
//                    "║  2       X                                                              ║\n" +
//                    "║  3       |                                                              ║\n" +
//                    "║  4            X                                                         ║\n" +
//                    "║  5            |                                                         ║\n" +
//                    "║  6                 X                                                    ║\n" +
//                    "║  7                 |                                                    ║\n" +
//                    "║  8                      X                                               ║\n" +
//                    "║  9                      |                                               ║\n" +
//                    "║ 10                           X                                          ║\n" +
//                    "║ 11                           |                                          ║\n" +
//                    "║ 12                                X                                     ║\n" +
//                    "║ 13                                |                                     ║\n" +
//                    "║ 14                                     X                                ║\n" +
//                    "║ 15                                     |                                ║\n" +
//                    "║ 16                                          X                           ║\n" +
//                    "║ 17                                          |                           ║\n" +
//                    "║ 18                                               X                      ║\n" +
//                    "║ 19                                               |                      ║\n" +
//                    "║ 20                                                    X                 ║\n" +
//                    "║ 21                                                    |                 ║\n" +
//                    "║ 22                                                         X            ║\n" +
//                    "║ 23                                                         |            ║\n" +
//                    "║ 24                                                                   X  ║\n" +
//                    "║ 25                                                                   |  ║\n" +
//                    "║ 26                                                                   |  ║\n" +
//                    "║ 27                                                                   |  ║\n" +
//                    "║ 28                                                                   |  ║\n" +
//                    "║ 29                                                                   |  ║\n" +
//                    "║ 30                                                                   |  ║\n" +
//                    "║ 31                                                                   |  ║\n" +
//                    "║ 32                                                                   |  ║\n" +
//                    "║ 33                                                                   |  ║\n" +
//                    "║ 34                                                                   |  ║\n" +
//                    "║ 35                                                                   |  ║\n" +
//                    "║ 36                                                                   |  ║\n" +
//                    "║ 37                                                                   |  ║\n" +
//                    "║ 38                                                                   |  ║\n" +
//                    "║ 39                                                                   |  ║\n" +
//                    "║ 40                                                                   |  ║\n" +
//                    "║ 41                                                                   |  ║\n" +
//                    "║ 42                                                                   |  ║\n" +
//                    "║ 43                                                                   |  ║\n" +
//                    "║ 44                                                                   |  ║\n" +
//                    "║ 45                                                                   |  ║\n" +
//                    "║ 46                                                                   |  ║\n" +
//                    "║ 47                                                                   |  ║\n" +
//                    "║ 48                                                                   |  ║\n" +
//                    "║ 49                                                                   |  ║\n" +
//                    "║ 50                                                                   |  ║\n" +
//                    "║ 51                                                                   |  ║\n" +
//                    "║ 52                                                                   |  ║\n" +
//                    "║ 53                                                                   |  ║\n" +
//                    "║ 54                                                                   |  ║\n" +
//                    "║ 55                                                                   |  ║\n" +
//                    "║ 56                                                                   |  ║\n" +
//                    "║ 57                                                                   |  ║\n" +
//                    "║ 58                                                                   |  ║\n" +
//                    "║ 59                                                                   |  ║\n" +
//                    "║ 60                                                                   |  ║\n" +
//                    "║ 61                                                                   |  ║\n" +
//                    "║ 62                                                                   |  ║\n" +
//                    "║ 63                                                                   |  ║\n" +
//                    "║ 64                                                                   |  ║\n" +
//                    "║ 65                                                                   |  ║\n" +
//                    "║ 66                                                                   |  ║\n" +
//                    "║ 67                                                                   |  ║\n" +
//                    "║ 68                                                                   |  ║\n" +
//                    "║ 69                                                                   |  ║\n" +
//                    "║ 70                                                                   |  ║\n" +
//                    "║ 71                                                                   |  ║\n" +
//                    "║ 72                                                                   |  ║\n" +
//                    "║ 73                                                                   |  ║\n" +
//                    "║ 74                                                                   |  ║\n" +
//                    "║ 75                                                                   |  ║\n" +
//                    "║ 76                                                                   |  ║\n" +
//                    "║ 77                                                                   |  ║\n" +
//                    "║ 78                                                                   |  ║\n" +
//                    "║ 79                                                                   |  ║\n" +
//                    "║ 80                                                                   |  ║\n" +
//                    "║ 81                                                                   |  ║\n" +
//                    "║ 82                                                                   |  ║\n" +
//                    "║ 83                                                                   |  ║\n" +
//                    "║ 84                                                                   |  ║\n" +
//                    "║ 85                                                                   |  ║\n" +
//                    "║ 86                                                                   |  ║\n" +
//                    "║ 87                                                                   |  ║\n" +
//                    "║ 88                                                                   |  ║\n" +
//                    "║ 89                                                                   |  ║\n" +
//                    "║ 90                                                                   |  ║\n" +
//                    "║ 91                                                                   |  ║\n" +
//                    "║ 92                                                                   |  ║\n" +
//                    "║ 93                                                                   |  ║\n" +
//                    "║ 94                                                                   |  ║\n" +
//                    "║ 95                                                                   |  ║\n" +
//                    "║ 96                                                                   |  ║\n" +
//                    "║ 97                                                                   |  ║\n" +
//                    "║ 98                                                                   |  ║\n" +
//                    "║ 99                                                                   |  ║\n" +
//                    "║100                                                                   |  ║\n" +
//                    "║101                                                                   |  ║\n" +
//                    "║102                                                                   |  ║\n" +
//                    "║103                                                                   |  ║\n" +
//                    "╚═════════════════════════════════" +
//                    "════════════════════════════════════════╝\n",
//            ChromaticScale2.consoleRender());
//  }
//
//  @Test
//  public void testRemoveNote() {
//    MusicModel scale = initChromaticScale();
//    scale.removeNote(0);
//    assertEquals("╔═════════════════════════" +
//            "════════════════════════════════╗\n" +
//            "║   C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4 ║\n" +
//            "║ 0                                                       ║\n" +
//            "║ 1                                                       ║\n" +
//            "║ 2  X                                                    ║\n" +
//            "║ 3  |                                                    ║\n" +
//            "║ 4       X                                               ║\n" +
//            "║ 5       |                                               ║\n" +
//            "║ 6            X                                          ║\n" +
//            "║ 7            |                                          ║\n" +
//            "║ 8                 X                                     ║\n" +
//            "║ 9                 |                                     ║\n" +
//            "║10                      X                                ║\n" +
//            "║11                      |                                ║\n" +
//            "║12                           X                           ║\n" +
//            "║13                           |                           ║\n" +
//            "║14                                X                      ║\n" +
//            "║15                                |                      ║\n" +
//            "║16                                     X                 ║\n" +
//            "║17                                     |                 ║\n" +
//            "║18                                          X            ║\n" +
//            "║19                                          |            ║\n" +
//            "║20                                               X       ║\n" +
//            "║21                                               |       ║\n" +
//            "║22                                                    X  ║\n" +
//            "║23                                                    |  ║\n" +
//            "╚════════════════════════════════" +
//            "═════════════════════════╝\n", scale.consoleRender());
//    scale.removeNote(10);
//    assertEquals("╔════════════════════════" +
//            "════════════════════════════╗\n" +
//            "║   C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4 ║\n" +
//            "║ 0                                                  ║\n" +
//            "║ 1                                                  ║\n" +
//            "║ 2  X                                               ║\n" +
//            "║ 3  |                                               ║\n" +
//            "║ 4       X                                          ║\n" +
//            "║ 5       |                                          ║\n" +
//            "║ 6            X                                     ║\n" +
//            "║ 7            |                                     ║\n" +
//            "║ 8                 X                                ║\n" +
//            "║ 9                 |                                ║\n" +
//            "║10                      X                           ║\n" +
//            "║11                      |                           ║\n" +
//            "║12                           X                      ║\n" +
//            "║13                           |                      ║\n" +
//            "║14                                X                 ║\n" +
//            "║15                                |                 ║\n" +
//            "║16                                     X            ║\n" +
//            "║17                                     |            ║\n" +
//            "║18                                          X       ║\n" +
//            "║19                                          |       ║\n" +
//            "║20                                               X  ║\n" +
//            "║21                                               |  ║\n" +
//            "╚════════════════════════════" +
//            "════════════════════════╝\n", scale.consoleRender());
//    scale.removeNote(4);
//    assertEquals("╔════════════════════════" +
//            "════════════════════════════╗\n" +
//            "║   C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4 ║\n" +
//            "║ 0                                                  ║\n" +
//            "║ 1                                                  ║\n" +
//            "║ 2  X                                               ║\n" +
//            "║ 3  |                                               ║\n" +
//            "║ 4       X                                          ║\n" +
//            "║ 5       |                                          ║\n" +
//            "║ 6            X                                     ║\n" +
//            "║ 7            |                                     ║\n" +
//            "║ 8                 X                                ║\n" +
//            "║ 9                 |                                ║\n" +
//            "║10                                                  ║\n" +
//            "║11                                                  ║\n" +
//            "║12                           X                      ║\n" +
//            "║13                           |                      ║\n" +
//            "║14                                X                 ║\n" +
//            "║15                                |                 ║\n" +
//            "║16                                     X            ║\n" +
//            "║17                                     |            ║\n" +
//            "║18                                          X       ║\n" +
//            "║19                                          |       ║\n" +
//            "║20                                               X  ║\n" +
//            "║21                                               |  ║\n" +
//            "╚══════════════════════════" +
//            "══════════════════════════╝\n", scale.consoleRender());
//  }
//
//  @Test
//  public void testChangeNote() {
//    MusicModel scale = initChromaticScale();
//    Note longer = new SimpleNote(Pitch.C, 4, 0, 4);
//    scale.changeNote(0, longer);
//    assertEquals("╔════════════════════════════" +
//            "══════════════════════════════════╗\n" +
//            "║    C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4 ║\n" +
//            "║ 0  X                                                         ║\n" +
//            "║ 1  |                                                         ║\n" +
//            "║ 2  |    X                                                    ║\n" +
//            "║ 3  |    |                                                    ║\n" +
//            "║ 4            X                                               ║\n" +
//            "║ 5            |                                               ║\n" +
//            "║ 6                 X                                          ║\n" +
//            "║ 7                 |                                          ║\n" +
//            "║ 8                      X                                     ║\n" +
//            "║ 9                      |                                     ║\n" +
//            "║10                           X                                ║\n" +
//            "║11                           |                                ║\n" +
//            "║12                                X                           ║\n" +
//            "║13                                |                           ║\n" +
//            "║14                                     X                      ║\n" +
//            "║15                                     |                      ║\n" +
//            "║16                                          X                 ║\n" +
//            "║17                                          |                 ║\n" +
//            "║18                                               X            ║\n" +
//            "║19                                               |            ║\n" +
//            "║20                                                    X       ║\n" +
//            "║21                                                    |       ║\n" +
//            "║22                                                         X  ║\n" +
//            "║23                                                         |  ║\n" +
//            "╚══════════════════════════════════" +
//            "════════════════════════════╝\n", scale.consoleRender());
//  }
//
//  @Test
//  public void playSimultaneousTest() {
//    MusicModel scale = initChromaticScale();
//    MusicModel backwardsScale = initBackwardsChromaticScale();
//    scale.playSimultaneous(backwardsScale);
//    assertEquals("╔═════════════════════════" +
//            "═════════════════════════════════════╗\n" +
//            "║    C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4 ║\n" +
//            "║ 0  X                                                      X  ║\n" +
//            "║ 1  |                                                      |  ║\n" +
//            "║ 2       X                                            X       ║\n" +
//            "║ 3       |                                            |       ║\n" +
//            "║ 4            X                                  X            ║\n" +
//            "║ 5            |                                  |            ║\n" +
//            "║ 6                 X                        X                 ║\n" +
//            "║ 7                 |                        |                 ║\n" +
//            "║ 8                      X              X                      ║\n" +
//            "║ 9                      |              |                      ║\n" +
//            "║10                           X    X                           ║\n" +
//            "║11                           |    |                           ║\n" +
//            "║12                           X    X                           ║\n" +
//            "║13                           |    |                           ║\n" +
//            "║14                      X              X                      ║\n" +
//            "║15                      |              |                      ║\n" +
//            "║16                 X                        X                 ║\n" +
//            "║17                 |                        |                 ║\n" +
//            "║18            X                                  X            ║\n" +
//            "║19            |                                  |            ║\n" +
//            "║20       X                                            X       ║\n" +
//            "║21       |                                            |       ║\n" +
//            "║22  X                                                      X  ║\n" +
//            "║23  |                                                      |  ║\n" +
//            "╚══════════════════════════════════" +
//            "════════════════════════════╝\n", scale.consoleRender());
//  }
//  @Test
//  public void playConsecutivelyTest() {
//    MusicModel scale = initChromaticScale();
//    MusicModel backwardsScale = initBackwardsChromaticScale();
//    scale.playConsecutively(backwardsScale);
//    assertEquals("╔═══════════════════════" +
//            "═══════════════════════════════════════╗\n" +
//            "║    C4  C#4   D4  D#4   E4   F4  F#4   G4  G#4   A4  A#4   B4 ║\n" +
//            "║ 0  X                                                         ║\n" +
//            "║ 1  |                                                         ║\n" +
//            "║ 2       X                                                    ║\n" +
//            "║ 3       |                                                    ║\n" +
//            "║ 4            X                                               ║\n" +
//            "║ 5            |                                               ║\n" +
//            "║ 6                 X                                          ║\n" +
//            "║ 7                 |                                          ║\n" +
//            "║ 8                      X                                     ║\n" +
//            "║ 9                      |                                     ║\n" +
//            "║10                           X                                ║\n" +
//            "║11                           |                                ║\n" +
//            "║12                                X                           ║\n" +
//            "║13                                |                           ║\n" +
//            "║14                                     X                      ║\n" +
//            "║15                                     |                      ║\n" +
//            "║16                                          X                 ║\n" +
//            "║17                                          |                 ║\n" +
//            "║18                                               X            ║\n" +
//            "║19                                               |            ║\n" +
//            "║20                                                    X       ║\n" +
//            "║21                                                    |       ║\n" +
//            "║22                                                         X  ║\n" +
//            "║23                                                         |  ║\n" +
//            "║24                                                         X  ║\n" +
//            "║25                                                         |  ║\n" +
//            "║26                                                    X       ║\n" +
//            "║27                                                    |       ║\n" +
//            "║28                                               X            ║\n" +
//            "║29                                               |            ║\n" +
//            "║30                                          X                 ║\n" +
//            "║31                                          |                 ║\n" +
//            "║32                                     X                      ║\n" +
//            "║33                                     |                      ║\n" +
//            "║34                                X                           ║\n" +
//            "║35                                |                           ║\n" +
//            "║36                           X                                ║\n" +
//            "║37                           |                                ║\n" +
//            "║38                      X                                     ║\n" +
//            "║39                      |                                     ║\n" +
//            "║40                 X                                          ║\n" +
//            "║41                 |                                          ║\n" +
//            "║42            X                                               ║\n" +
//            "║43            |                                               ║\n" +
//            "║44       X                                                    ║\n" +
//            "║45       |                                                    ║\n" +
//            "║46  X                                                         ║\n" +
//            "║47  |                                                         ║\n" +
//            "╚═════════════════════════════════" +
//            "═════════════════════════════╝\n", scale.consoleRender());
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void badCardIndexTest() {
//    MusicModel scale = initChromaticScale();
//    scale.changeNote(12, new SimpleNote(Pitch.F, 5, 0, 1));
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void emptyPieceTest() {
//    MusicModel empty = new MusicModelImp1();
//    empty.consoleRender();
//  }
//
//
//}
