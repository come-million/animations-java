

public class RandomPixels extends LEDObject {

    public HSBColor pixels[];
    public int[] allIndexes = CreateIndexRange(6, 300 - 1);

    public RandomPixels(int numberOfPixels) {
        pixels = CreateHSBArray(numberOfPixels);
    }

    @Override
    public HSBColor[] GetAllPixels() {
        return this.pixels;
    }
}
