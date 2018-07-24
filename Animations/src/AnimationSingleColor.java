public class AnimationSingleColor extends AnimationIfc {

    @Override
    void setHSBColor(HSBColor c, int globalI, int controllerI, int stripI, int PixelI, double timePercent, double absTimeSeconds) {
        c.hue = timePercent;
        c.saturation = 1.0;
        c.brightness = 1.0;
    }
}
