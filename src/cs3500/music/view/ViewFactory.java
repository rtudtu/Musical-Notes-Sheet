package cs3500.music.view;

import cs3500.music.model.MusicModel;

/**
 * Created by Philip on 6/19/2016.
 */
public class ViewFactory {
  /**
   * Initializes the view specified
   * @param type the view type
   * @param model the model
   * @param ap the output to append data (necessary for console view)
   */
  public static void createView(String type, MusicModel model, Appendable ap) {
    switch (type) {
      case "console":
        ConsoleView consoleView = new ConsoleView(model, ap);
        consoleView.initialize();
        break;
      case "gui":
        GuiViewFrame guiView = new GuiViewFrame(model);
        guiView.initialize();
        break;
      case "midi":
        MidiViewImpl midiView = new MidiViewImpl(model);
        midiView.initialize();
        break;
    }


  }
}
