package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import cs3500.music.model.IMusicBuilder;
import cs3500.music.model.MidiNote;
import cs3500.music.model.MusicBuilder;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicModelImp1;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.SimpleNote;
import cs3500.music.util.MusicReader;
import cs3500.music.view.*;

/**
 * Created by Philip on 6/19/2016.
 */

/**
 * Controls interaction between the View and the Model
 */
public class MusicController {

  protected MusicModel model;
  protected IMusicBuilder modelBuilder;
  protected CompositeView view;
  protected Readable rd;
  protected Appendable ap;

  /**
   * contructs a MusicController
   * @param rd A readable source of input
   * @param ap An output for information to be appended
   */
  public MusicController(Readable rd, Appendable ap) {
    this.rd = rd;
    this.ap = ap;
    this.model = new MusicModelImp1();
    model.setTempo(100000);
    this.modelBuilder = new MusicBuilder(model);
    this.view = new CompositeView(modelBuilder);
  }

  /**
   * creates a composite view which can be modified on hte fly
   * @throws FileNotFoundException
   */
  public void makeMusicTemplate() throws FileNotFoundException {
    Scanner sc = new Scanner(rd);
    try {
      ap.append("Type Filepath or \"none\"\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    String path = sc.next();

    if (path.equals("none")) {
      model = new MusicModelImp1();
      model.setTempo(100000);
    }
    else {
      FileReader file = new FileReader(new File(path));
      MusicModel fromFile = MusicReader.parseFile(file, new MusicModelImp1.Builder());
      model = fromFile;
    }
    modelBuilder = new MusicBuilder(model);
    view = new CompositeView(modelBuilder);
    view.initialize();
    configureKeyBoardListener();
    view.addMouseListener(new MusicMouseListener());
    while (true) {
      view.refresh();
    }
  }

  /**
   * Configures the KeyBoardistener's key-function map
   */
  protected void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    keyTypes.put(' ', new playMusic());
    keyTypes.put('q', new addHigherOctave());
    keyTypes.put('a', new addLowerOctave());
    keyTypes.put('w', new removeHigherOctave());
    keyTypes.put('s', new removeLowerOctave());
    keyTypes.put('d', new addBeats());

    keyPresses.put(KeyEvent.VK_DOWN, new scrollDown());
    keyPresses.put(KeyEvent.VK_UP, new scrollUp());
    keyPresses.put(KeyEvent.VK_LEFT, new scrollLeft());
    keyPresses.put(KeyEvent.VK_RIGHT, new scrollRight());
    keyPresses.put(KeyEvent.VK_HOME, new scrollToStart());
    keyPresses.put(KeyEvent.VK_END, new scrollToEnd());

    view.addKeyListener(kbd);
  }

  /**
   * Creates a simple gui representation of a piece (hw 6).
   * @throws FileNotFoundException
   */
  public void playMusicFromFile() throws FileNotFoundException {
    Scanner sc = new Scanner(rd);
    try {
      ap.append("File path:\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    String path = sc.next();

    FileReader file = new FileReader(new File(path));

    MusicModel fromFile = MusicReader.parseFile(file, new MusicModelImp1.Builder());
    try {
      ap.append("View type:\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
    String type = sc.next();

    ViewFactory.createView(type, fromFile, ap);
    if (type.equals("midi")) {
      try {
        Thread.sleep((fromFile.totalBeats() + 1) * fromFile.getTempo() / 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Contains all the methods dealing with mouse events.
   */
  class MusicMouseListener implements MouseListener {

    private int sourceX;
    private int sourceY;

    /**
     * Removes a note from the model based off the position of the cursor.
     * @param e the mouse event
     */
    public void mouseClicked(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON3) {
        int startBeat = (e.getX() - 40) / 20;
        int ypos = e.getY();
        int pitchOrder = modelBuilder.highestNote().pitchOrder() - (ypos - 20) / 20;
        Pitch[] pitches = Pitch.values();
        Pitch pitch = pitches[pitchOrder % 12];
        int octave = pitchOrder / 12;
        model.removeNote(startBeat, octave, pitch);
        view.refresh();
      }

    }

    /**
     * Stores the initial position of the mouse when pressed prior to releasing.
     * @param e The mouse event
     */
    public void mousePressed(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
        sourceX = e.getX();
        sourceY = e.getY();
      }
    }

    /**
     * Adds a note to the model based off the position of the mouse event.
     * Only adds the note if it's within the bounds of the template.
     * @param e The mouse event
     */
    public void mouseReleased(MouseEvent e) {
      if (e.getButton() == MouseEvent.BUTTON1) {
        int changePos = (e.getX() - sourceX);
        int highestPitch = modelBuilder.highestNote().pitchOrder();
        int pitchOrder = highestPitch - (sourceY - 20) / 20;
        int startBeat = (sourceX - 40) / 20;
        int duration = changePos / 20 + 1;
        if (duration <= 0) {duration = 1;}
        Pitch[] pitches = Pitch.values();
        Pitch pitch = pitches[pitchOrder % 12];
        int octave = pitchOrder / 12;
        if (pitchOrder <= modelBuilder.highestNote().pitchOrder() &&
                pitchOrder >= modelBuilder.lowestNote().pitchOrder() &&
                startBeat + duration <= modelBuilder.totalBeats() + 4 && startBeat >= 0) {
          MidiNote toAdd = new MidiNote(startBeat, startBeat + duration, 1, pitchOrder + 12, 50);
          model.addNote(toAdd);
          view.addNote(toAdd);
        }

      }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
  }

  /**
   * Pauses/Resumes the MidiView
   */
  protected class playMusic implements Runnable {
    public void run() {
      view.playMusic();
      view.refresh();
    }
  }

  /**
   * Increases the octave ceiling of the View
   */
  protected class addHigherOctave implements Runnable {
    public void run() {
      modelBuilder.addHigherOctave();
    }
  }

  /**
   * Lowers the Octave floor of the view
   */
  protected class addLowerOctave implements Runnable {
    public void run() {
      modelBuilder.addLowerOctave();
    }
  }

  /**
   * Lowers the Octave ceiling of the view
   */
  protected class removeHigherOctave implements Runnable {
    public void run() {
      modelBuilder.removeHigherOctave();
    }
  }

  /**
   * raises the Octave floor of the view
   */
  protected class removeLowerOctave implements Runnable {
    public void run() {
      modelBuilder.removeLowerOctave();
    }
  }

  /**
   * Adds beats to the view for notes to be added
   */
  protected class addBeats implements Runnable {
    public void run() {
      modelBuilder.addBeats();
    }
  }

  /**
   * Scrolls the view down
   */
  protected class scrollDown implements Runnable {
    public void run() {
      view.scrollDown();
    }
  }

  /**
   * Scrolls the view up
   */
  protected class scrollUp implements Runnable {
    public void run() {
      view.scrollUp();
    }
  }

  /**
   * Scrolls the view to the left
   */
  protected class scrollLeft implements Runnable {
    public void run() {
      view.scrollLeft();
    }
  }

  /**
   * Scrolls the view to the right
   */
  protected class scrollRight implements Runnable {
    public void run() {
      view.scrollRight();
    }
  }

  /**
   * Scrolls to the end of the piece
   */
  protected class scrollToEnd implements Runnable {
    public void run() {
      view.scrollToEnd();
    }
  }

  /**
   * Scrolls to the beginning of the piece
   */
  protected class scrollToStart implements Runnable {
    public void run() {
      view.scrollToStart();
    }
  }
}



