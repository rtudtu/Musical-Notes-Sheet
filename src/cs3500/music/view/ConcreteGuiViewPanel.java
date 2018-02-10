package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.Note;
import cs3500.music.model.ReadOnlyMusicModel;

/**
 * A dummy view that simply draws a string
 */

public class ConcreteGuiViewPanel extends JPanel {

  protected ReadOnlyMusicModel model;
  int rowHeight;


  public ConcreteGuiViewPanel(ReadOnlyMusicModel model) {
    this.model = model;
    rowHeight = 20;

  }

  @Override
  public void paintComponent(Graphics g) {
    // Handle the default painting
    super.paintComponent(g);
    // Look for more documentation about the Graphics class,
    // and methods on it that may be useful
    drawPitches(g);
    drawNotes(g);
    drawHorizontalLines(g);
    drawVerticalLines(g);
    drawMeasureNumbers(g);
  }


  /**
   * draws the pitches on the left side of the table
   *
   * @param g the graphics object
   */
  protected void drawPitches(Graphics g) {
    int highest = model.highestNote().pitchOrder();
    int lowest = model.lowestNote().pitchOrder();

    for (int i = highest; i >= lowest; i--) {
      int position = highest - i;
      g.drawString(Note.stringFromNoteOrder(i), 0, position * rowHeight + rowHeight + 15);
    }
  }

  /**
   * Draws the lines between the pitches
   *
   * @param g the graphics object
   */
  protected void drawHorizontalLines(Graphics g) {
    int highest = model.highestNote().pitchOrder();
    int lowest = model.lowestNote().pitchOrder();
    int totalBeats = model.totalBeats();

    int length = (totalBeats + (4 - (totalBeats % 4))) * rowHeight; //length of each line

    length = length + 40;
    for (int i = highest; i >= lowest; i--) {
      int position = highest - i + 1;
      if (i % 12 != 11) {
        g.drawLine(40, rowHeight * position, length, rowHeight * position);
      } else {
        //Draw three lines representing a line of a larger size
        g.drawLine(40, rowHeight * position - 1, length, rowHeight * position - 1);
        g.drawLine(40, rowHeight * position, length, rowHeight * position);
        g.drawLine(40, rowHeight * position + 1, length, rowHeight * position + 1);
      }
      if (i == lowest) {
        g.drawLine(40, rowHeight * position + rowHeight, length, rowHeight * position + rowHeight);
      }
    }

  }

  /**
   * draws the lines vertically between beats (taking into account the beats per measure)
   *
   * @param g the graphics object
   */
  protected void drawVerticalLines(Graphics g) {
    int highest = model.highestNote().pitchOrder();
    int lowest = model.lowestNote().pitchOrder();
    int totalBeats = model.totalBeats();
    int length = rowHeight * (totalBeats + (4 - totalBeats % 4));
    int height = (highest - lowest + 2) * rowHeight;
    while (length % 80 != 0) {
      length++;
    }
    for (int i = 0; i <= length / 80; i++) {
      g.drawLine(40 + i * 80, rowHeight, 40 + i * 80, height);
    }
  }

  /**
   * Draws the notes in the table. The head should be black, its duration green
   *
   * @param g the graphics object
   */
  protected void drawNotes(Graphics g) {
    int highest = model.highestNote().pitchOrder();

    g.setColor(Color.BLACK);
    for (Note n : model.noteList()) {
      int x = 40 + rowHeight * n.getBeat();
      int y = rowHeight + rowHeight * (highest - n.pitchOrder());
      g.drawRect(x, y, rowHeight, rowHeight);
      g.fillRect(x, y, rowHeight, rowHeight);
      for (int i = 1; i < n.getDuration(); i++) {
        x = x + rowHeight;
        g.setColor(Color.GREEN);
        g.drawRect(x, y, rowHeight, rowHeight);
        g.fillRect(x, y, rowHeight, rowHeight);
        g.setColor(Color.BLACK);
      }
    }
  }

  /**
   * Draws the beats above the table. Can either be drawn every number of measures or every
   * measure.
   *
   * @param g the graphics object
   */
  protected void drawMeasureNumbers(Graphics g) {

    int totalBeats = model.totalBeats();
    for (int i = 0; i <= totalBeats; i += 4) {
      g.drawString(Integer.toString(i), 40 + i * rowHeight, rowHeight - 5);
    }

  }

  @Override
  public Dimension getPreferredSize() {
    int highest = model.highestNote().pitchOrder();
    int lowest = model.lowestNote().pitchOrder();
    int totalBeats = model.totalBeats();
    return new Dimension(75 + (totalBeats + (4 - (totalBeats % 4))) * 20,
            (1 + (highest - lowest)) * 20 + 50);
  }


}
