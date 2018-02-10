package cs3500.music.view;

import java.awt.*;
import java.awt.event.MouseListener;

import javax.swing.*;

import cs3500.music.model.ReadOnlyMusicBuilder;
import cs3500.music.model.ReadOnlyMusicModel;


/**
 * Created by Philip on 6/23/2016.
 */


public class GuiView2 extends JFrame implements GuiView {

  private final CompositeViewPanel displayPanel;
  private ReadOnlyMusicBuilder model;


  public GuiView2(ReadOnlyMusicModel model, MidiViewImpl2 midi) {
    this.displayPanel = new CompositeViewPanel(model, midi);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JScrollPane scrollable = new JScrollPane();
    scrollable.setViewportView(displayPanel);
    this.add(scrollable);


    this.pack();
  }

  public void refresh() {
    displayPanel.repaint();
    this.setFocusable(true);
    this.requestFocus();
  }


  @Override
  public void initialize() {
    this.setVisible(true);
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 800);
  }

  @Override
  public void addMouseListener(MouseListener m) {
    displayPanel.addMouseListener(m);
  }

  /**
   * Scrolls the gui up
   */
  public void scrollUp() {
    displayPanel.scrollUp();
  }

  /**
   * Scrolls the gui down
   */
  public void scrollDown() {
    displayPanel.scrollDown();
  }

  /**
   * scrolls the gui Right
   */
  public void scrollRight() {
    displayPanel.scrollRight();
  }

  /**
   * scrolls the gui Left
   */
  public void scrollLeft() {
    displayPanel.scrollLeft();
  }

  /**
   * Scrolls to the start of the gui
   */
  public void scrollToStart() {
    displayPanel.scrollToStart();
  }

  /**
   * Scrolls to the end of the Gui
   */
  public void scrollToEnd() {
    displayPanel.scrollToEnd();
  }


}
