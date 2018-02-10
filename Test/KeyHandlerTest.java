import org.junit.Test;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import cs3500.music.controller.KeyboardListener;

import static org.junit.Assert.assertEquals;

/**
 * Created by Philip on 6/25/2016.
 */
public class KeyHandlerTest {


  StringBuilder output;

  KeyboardListener initKeyHandler() {
    KeyboardListener handler = new KeyboardListener();
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyTypes.put('k', new addFromType());
    keyPresses.put(KeyEvent.VK_K, new addFromPress());
    keyReleases.put(KeyEvent.VK_K, new addFromRelease());

    handler.setKeyTypedMap(keyTypes);
    handler.setKeyPressedMap(keyPresses);
    handler.setKeyReleasedMap(keyReleases);

    return handler;
  }

  class addFromType implements Runnable {
    public void run() {
      output.append("The key was typed");
    }
  }

  class addFromPress implements Runnable {
    public void run() {
      output.append("The key was pressed");
    }
  }

  class addFromRelease implements Runnable {
    public void run() {
      output.append("The key was released");
    }
  }

  @Test
  public void testKeyPress() {
    KeyboardListener keyhandler = initKeyHandler();
    output = new StringBuilder();
    keyhandler.keyPressed(new KeyEvent(new JPanel(), 1, 0, 0, KeyEvent.VK_K));
    assertEquals("The key was pressed", output.toString());
  }

  @Test
  public void testKeyType() {
    KeyboardListener keyhandler = initKeyHandler();
    output = new StringBuilder();
    keyhandler.keyTyped(new KeyEvent(new JPanel(), 1, 0, 0, KeyEvent.VK_K, 'k'));
    assertEquals("The key was typed", output.toString());
  }

  @Test
  public void testKeyRelease() {
    KeyboardListener keyhandler = initKeyHandler();
    output = new StringBuilder();
    keyhandler.keyReleased(new KeyEvent(new JPanel(), 1, 0, 0, KeyEvent.VK_K));
    assertEquals("The key was released", output.toString());
  }

  @Test
  public void testNoAvailableAction() {
    KeyboardListener keyhandler = initKeyHandler();
    output = new StringBuilder();
    keyhandler.keyReleased(new KeyEvent(new JPanel(), 1, 0, 0, KeyEvent.VK_L));
    assertEquals("", output.toString());
  }


}
