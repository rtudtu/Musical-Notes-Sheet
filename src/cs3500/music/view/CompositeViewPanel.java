package cs3500.music.view;

import java.awt.*;

import cs3500.music.model.ReadOnlyMusicModel;

/**
 * Created by Philip on 6/24/2016.
 */
public class CompositeViewPanel extends ConcreteGuiViewPanel {
  MidiViewImpl2 midi;
  protected Rectangle viewrect;

  public CompositeViewPanel(ReadOnlyMusicModel model, MidiViewImpl2 midi) {
    super(model);
    this.midi = midi;
    viewrect = new Rectangle(0, 0, 1000, 800);

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.drawPitches(g);
    drawRedLine(g);


    if (midi.isPlaying() && (((midi.getTime() * 20 + 40) % 1000 == 0) || midi.getTime() == 0)) {
      viewrect = new Rectangle(midi.getTime() * 20, 0, 1000, 800);
      scrollRectToVisible(viewrect);
    }
  }

  /**
   * draws the Red Line onto the gui, depending on the midi's tick-position.
   *
   * @param g The graphics object
   */
  public void drawRedLine(Graphics g) {
    int highest = model.highestNote().pitchOrder();
    int lowest = model.lowestNote().pitchOrder();
    int totalBeats = model.totalBeats();


    int x = 40 + 20 * midi.getTime();
    g.setColor(Color.RED);
    g.drawLine(x, 20, x, ((highest - lowest) + 1) * 20 + 20);
    g.setColor(Color.BLACK);

  }

  /**
   * Scrolls to the right 80 pixels
   */
  public void scrollRight() {
    viewrect.x += 80;
    scrollRectToVisible(viewrect);
  }

  /**
   * Scrolls to the left 80 pixels
   */
  public void scrollLeft() {
    if (viewrect.x >= 80) {
      viewrect.x -= 80;

    } else {
      viewrect.x = -100;
    }
    scrollRectToVisible(viewrect);

  }

  /**
   * Scrolls down 120 pixels
   */
  public void scrollDown() {
    if ((viewrect.y + 20 + 800) < (model.highestNote().pitchOrder() -
            model.lowestNote().pitchOrder() + 1) * 20) {
      viewrect.y += 120;

    } else {
      viewrect.y = (model.highestNote().pitchOrder() -
              model.lowestNote().pitchOrder() + 1) * 20 - 600;
    }
    scrollRectToVisible(viewrect);
  }

  /**
   * scrolls up 120 pixels
   */
  public void scrollUp() {
    if (viewrect.y >= 120) {
      viewrect.y -= 120;
    } else {
      viewrect.y = -100;
    }
    scrollRectToVisible(viewrect);
  }

  /**
   * scrolls to the beginning of this song
   */
  public void scrollToStart() {
    viewrect.x = -100;
    viewrect.y = 0;
    scrollRectToVisible(viewrect);
  }

  /**
   * scrolls to the end of this song.
   */
  public void scrollToEnd() {
    viewrect.x = 40 + (model.totalBeats() + 1) * 20 - 900;
    viewrect.y = 0;
    scrollRectToVisible(viewrect);
  }


}
