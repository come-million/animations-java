public class AnimationDamka extends AnimationIfc {

    @Override
    void setHSBColor(HSBColor c, int controllerI, int stripI, int pixelI) {
        c.brightness = (pixelI % 2) == 0 ? 0.0 : 1.0;
        c.hue = 0.8;
        c.saturation = 1.0;
    }
}
