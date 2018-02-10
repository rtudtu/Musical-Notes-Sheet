package cs3500.music.view;

import java.awt.*;

import javax.swing.*;

import cs3500.music.model.ReadOnlyMusicModel;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements GuiView {

  private final JPanel displayPanel; // You may want to refine this to a subtype of JPanel
  private ReadOnlyMusicModel m;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(ReadOnlyMusicModel m) {
    this.displayPanel = new ConcreteGuiViewPanel(m);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    JScrollPane scrollpane = new JScrollPane(displayPanel);
    this.add(scrollpane);
    this.pack();
  }

  @Override
  public void initialize() {
    this.setVisible(true);
  }


  @Override
  public Dimension getPreferredSize() {
    return new Dimension(1000, 800);
  }

}
