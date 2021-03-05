package PaooGame;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import static java.lang.System.exit;

/*! \public class Menu extends Tile
    \brief clasa care se ocupa de obtinerea fisierelor audio si redarea lor
 */
public class MusicPlayer
{
    /// atribut in care este stocat audio-ul
    private Clip clip;
    /*! \public MusicPlayer(String path)
    \brief constructorul de baza care preia audio-ul din fisier si il stocheaza in clip
 */
    public MusicPlayer(String path)
    {
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(path));

            AudioFormat baseFormat = ais.getFormat();

            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16,
                    baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);

            AudioInputStream decodedAis = AudioSystem.getAudioInputStream(decodeFormat, ais);

            clip = AudioSystem.getClip();
            clip.open(decodedAis);
        }
        catch(Exception e)
        {
            System.out.println("fisier audio lipsa");
        }
    }
    /*! \public void play()
    \brief functie care porneste melodia
 */
    public void play()
    {
        try {
            if (clip == null)
                return;
            stop();
            clip.start();
        }
        catch(Exception e)
        {
            System.out.println("fisier audio lipsa");
        }
    }
    /*! \public void stop()
    \brief functie care opreste melodia frumos
 */
    public void stop()
    {
        try {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
        catch(Exception e)
        {
            System.out.println("fisier audio lipsa");
        }
    }
    /*! \public void loop()
    \brief functie care reporneste melodia in bucla
 */
    public void loop()
    {
        try {
            if (clip == null)
                return;
            clip.setFramePosition(0);
            clip.loop(5);
        }
        catch(Exception e)
        {
            System.out.println("fisier audio lipsa");
        }
    }
    /*! \public void close()
    \brief functie opreste melodia fortat
 */
    public void close()
    {
        try {
            stop();
            clip.close();
        }
        catch(Exception e)
        {
            System.out.println("fisier audio lipsa");
        }
    }
    public Clip getClip()
    {
        return clip;
    }
    public void setClip(Clip clip)
    {
        this.clip=clip;
    }
}
