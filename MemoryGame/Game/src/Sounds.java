import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;



//Ezt a classt egy másik projekt segítségével valósítottam meg, hogy
//a játék színesebb lehessen.
public class Sounds {

    private AudioFormat format;
    private byte[] samples;

    public Sounds(URL fn){
        try{
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fn);
            format = audioInputStream.getFormat();
            samples = getSamples(audioInputStream);
        }catch(UnsupportedAudioFileException uafe){
            System.out.println(uafe);
        }catch(IOException ex){
            System.out.println(ex);
        }
    }

    public byte[] getSamples(){return samples;}

    private byte[] getSamples(AudioInputStream ais){
        int length = (int) (ais.getFrameLength()
                * format.getFrameSize());

        byte[] samples = new byte[length];
        DataInputStream is = new DataInputStream(ais);
        try {
            is.readFully(samples);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return samples;
    }

    public void play(InputStream is){
        int bufferSize = format.getFrameSize()
                * Math.round(format.getSampleRate() / 10);
        byte[] buffer = new byte[bufferSize];

        SourceDataLine line;
        try {
            DataLine.Info info
                    = new DataLine.Info(SourceDataLine.class, format);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format, bufferSize);
        } catch (LineUnavailableException ex) {
            System.out.println(ex);
            return;
        }

        line.start();

        try {
            int numBytesRead = 0;
            while (numBytesRead != -1) {
                numBytesRead = is.read(buffer, 0, buffer.length);
                if (numBytesRead != -1) {
                    line.write(buffer, 0, numBytesRead);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

        line.drain();

        line.close();
    }


}
