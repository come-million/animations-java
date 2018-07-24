import java.lang.*;

public class AnimationFoox extends AnimationIfc {

    @Override
    void setHSBColor(HSBColor c, int globalI, int controllerI, int stripI, int pixelI, double timePercent, double absTimeSeconds) {
        // c.brightness = Math.sin(timePercent * Math.PI);
        c.brightness = 0.6;
        c.hue = getHue(pixelI, timePercent);
        c.saturation = 1.0;
    }

    double getHue(int pixelI, double timePercent) {
        if(pixelI % 4 == 0) {
            return 0.4 + timePercent;
        }

        if(pixelI % 4 == 1) {
            return 0.6 + timePercent;
        }

        if(pixelI % 4 == 2) {
            return 0.8 + timePercent;
        }

        if(pixelI % 4 == 3) {
            return 0.2 + timePercent;
        }
        return 1.0;
    }
}
