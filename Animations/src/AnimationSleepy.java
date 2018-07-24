import java.lang.*;
import java.util.Random;

public class AnimationSleepy extends AnimationIfc {

    int randomPixels[];
    AnimationSleepy(int totalPixels) {
        Random rand = new Random();
        int arrSize = (int) (totalPixels*0.2);
        randomPixels = new int[arrSize];
        for (int r = 0; r < randomPixels.length; r++) {
            randomPixels[r] = rand.nextInt(totalPixels);
        }
    }

    @Override
    void setHSBColor(HSBColor c, int globalI, int controllerI, int stripI, int pixelI, double timePercent, double absTimeSeconds) {
        c.brightness = 0.5;
        c.hue = generateRandomHue(pixelI, timePercent);
        c.saturation = 0.7;
    }

    double generateRandomHue(int pixelI, double timePercent) {
        double hue =  getHue(pixelI, timePercent);
        for (int i = 0; i <randomPixels.length; i++) {
            if (pixelI == randomPixels[i]) {
                return hue + 0.3;
            }
        }
        return hue;
    }

    double getHue(int pixelI, double timePercent) {
        if(pixelI % 4 == 0) {
            return 0.42 + timePercent;
        }

        if(pixelI % 4 == 1) {
            return 0.44 + timePercent;
        }

        if(pixelI % 4 == 2) {
            return 0.46 + timePercent;
        }

        if(pixelI % 4 == 3) {
            return 0.48 + timePercent;
        }
        return 1.0;
    }
}
