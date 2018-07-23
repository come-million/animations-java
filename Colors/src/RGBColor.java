
public class RGBColor {
    public byte r;
    public byte g;
    public byte b;

    public void copyFromOther(RGBColor other) {
        r = other.r;
        g = other.g;
        b = other.b;
    }

    public void mixTwoColors(RGBColor c1, double amount1, RGBColor c2, double amount2) {
        double totalAmount = amount1 + amount2;
        r = (byte)(((c1.r & 0xFF) * amount1 + (c2.r & 0xFF) * amount2) / totalAmount);
        g = (byte)(((c1.g & 0xFF) * amount1 + (c2.g & 0xFF) * amount2) / totalAmount);
        b = (byte)(((c1.b & 0xFF) * amount1 + (c2.b & 0xFF) * amount2) / totalAmount);
    }
}
