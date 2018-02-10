import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;

import cs3500.music.controller.MusicController;

import static org.junit.Assert.assertEquals;

/**
 * Created by Richard on 6/25/2016.
 */
public class ControllerTest {


  //Tests
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
    assertEquals("", output.toString());

  }
}
