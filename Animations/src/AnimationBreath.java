import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AnimationBreath extends AnimationIfc {

    private double sinFreq[];

    public AnimationBreath(int numberOfPixels) {
        sinFreq = new double[numberOfPixels];
        for(int i=0; i<numberOfPixels; i++) {
            sinFreq[i] = ThreadLocalRandom.current().nextDouble(0.3, 1.0);
        }
    }

    @Override
    void setHSBColor(HSBColor c, int globalI, int controllerI, int stripI, int pixelI, double timePercent, double absTimeSeconds) {
        c.brightness = Math.sin(absTimeSeconds * sinFreq[globalI]) / 2.0 + 0.5;
        c.hue = timePercent;
        c.saturation = 1.0;
    }
}
