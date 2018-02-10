package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;

import cs3500.music.model.MidiNote;
import cs3500.music.model.ReadOnlyMusicModel;

/**
 * Created by Philip on 6/24/2016.
 */
public class CompositeView extends JFrame implements GuiView {
  protected GuiView2 gui;
  protected MidiViewImpl2 midi;

  public CompositeView(ReadOnlyMusicModel model) {

    this.midi = new MidiViewImpl2(model);
    this.gui = new GuiView2(model, midi);
  }
  @Override
  public void initialize() {
    gui.initialize();
  }

  /**
   * Stops or starts the music depending on whether it is currently running
   */
  public void playMusic() {
    if (midi.isOpen()) {
      try {
        midi.togglePlaying();
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    } else {
      midi.initialize();
      try {
        midi.togglePlaying();
      } catch (InvalidMidiDataException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Adds a note to the Midi-Sequencer
   * @param note the note to be added
   */
  public void addNote(MidiNote note) {
    try {
      midi.addNote(note);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    gui.refresh();
  }

  @Override
  public void addKeyListener(KeyListener k) {
    gui.addKeyListener(k);
  }

  @Override
  public void addMouseListener(MouseListener m) {
    gui.addMouseListener(m);
  }

  /**
   * Refreshes the gui so that it represents the updated model
   */
  public void refresh() {
    gui.refresh();
  }

  /**
   * Scrolls the gui up
   */
  public void scrollUp() {
    gui.scrollUp();
  }

  /**
   * Scrolls the gui down
   */
  public void scrollDown() {
    gui.scrollDown();
  }

  /**
   * Scrolls the gui right
   */
  public void scrollRight() {
    gui.scrollRight();
  }

  /**
   * scrolls the gui left
   */
  public void scrollLeft() {
    gui.scrollLeft();
  }

  /**
   * scrolls to the start of the gui
   */
  public void scrollToStart() {
    gui.scrollToStart();
  }

  /*
  scrolls to the end of the gui
   */
  public void scrollToEnd() {
    gui.scrollToEnd();
  }


}
