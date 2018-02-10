package cs3500.music.view;

import java.io.IOException;

import cs3500.music.model.MusicModel;
import cs3500.music.model.ReadOnlyMusicModel;


/**
 * Created by Philip on 6/16/2016.
 */
public class ConsoleView implements View {
  ReadOnlyMusicModel model;
  Appendable app;

  public ConsoleView(MusicModel model, Appendable app) {
    this.model = model;
    this.app = app;
  }
  @Override
  public void initialize() {
    try {
      app.append(model.consoleRender());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
