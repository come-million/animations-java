import java.lang.*;

public class AnimationWaves extends AnimationIfc {

    @Override
    void setHSBColor(HSBColor c, int globalI, int controllerI, int stripI, int pixelI, double timePercent, double absTimeSeconds) {
        c.brightness = Math.sin(timePercent * Math.PI);
        c.hue = 0.5 + Math.abs(Math.sin(timePercent)/10);
        // c.saturation = Math.abs(Math.sin(pixelI));
        c.saturation = Math.sin(timePercent * Math.PI);
    }
}
