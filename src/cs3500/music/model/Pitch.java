package cs3500.music.model;

/**
 * Created by Philip on 6/9/2016.
 */

/**
 * represents the pitches of the C-major scale
 */
public enum Pitch {
  C("C"), CSHARP("C#"), D("D"), DSHARP("D#"), E("E"), F("F"), FSHARP("F#"), G("G"), GSHARP("G#"),
  A("A"), ASHARP("A#"), B("B");
  private String symbol;

  Pitch(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return this.symbol;
  }
}
