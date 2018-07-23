public class AnimationDamka extends AnimationIfc {

    @Override
    void setHSBColor(HSBColor c, int globalI, int controllerI, int stripI, int pixelI, double timePercent) {
        c.brightness = (pixelI % 2) == 0 ? 0.0 : 1.0;
        c.hue = timePercent;
        c.saturation = 1.0;
    }
}
