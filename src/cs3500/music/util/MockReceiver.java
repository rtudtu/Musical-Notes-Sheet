package cs3500.music.util;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * Created by Philip on 6/19/2016.
 */
public class MockReceiver implements Receiver {

  StringBuilder result;

  public MockReceiver(StringBuilder result) {
    this.result = result;
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    byte[] data = message.getMessage();


    for (byte b : data) {
      result.append(Byte.toString(b) + " ");
    }
    result.append(Long.toString(timeStamp) + "\n");


  }

  @Override
  public void close() {

  }
}
