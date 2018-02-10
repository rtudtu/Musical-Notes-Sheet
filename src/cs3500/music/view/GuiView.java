package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Created by Philip on 6/22/2016.
 */
public interface GuiView extends View {
  /**
   * Adds the keyListener to this view
   * @param listener the keyListener
   */
  void addKeyListener(KeyListener listener);

  /**
   * Adds the MouseListener to this view
   * @param listener the MouseListener
   */
  void addMouseListener(MouseListener listener);

}
