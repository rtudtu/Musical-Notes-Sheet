package cs3500.music;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.controller.MusicController;


public class MusicEditor {


  public static void main(String[] args) throws IOException, InvalidMidiDataException {

    MusicController controller = new MusicController(new InputStreamReader(System.in), System.out);
    controller.makeMusicTemplate();
  }
}
