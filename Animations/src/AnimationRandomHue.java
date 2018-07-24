import java.util.Random;

public class AnimationRandomHue extends AnimationIfc {

    double hueOffsets[];

    public AnimationRandomHue(int numberOfPixels) {
        Random rand = new Random();
        hueOffsets = new double[numberOfPixels];
        for(int i=0; i<numberOfPixels; i++) {
            hueOffsets[i] = rand.nextDouble();
        }
    }

    @Override
    void setHSBColor(HSBColor c, int globalI, int controllerI, int stripI, int PixelI, double timePercent, double absTimeSeconds) {
        c.hue = hueOffsets[globalI] + timePercent;
    }

}
