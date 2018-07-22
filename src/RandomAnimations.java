
public class RandomAnimations {

    public static void run() throws InterruptedException {
        System.out.println("hello1");
        final Network network = new Network();
        network.configure();
        RGBColor rgbArr[] = getRGBColors(0, 51);

        while (true) {
            for (int i = 0; i < 48; i++) {
                network.addSegment("test", rgbArr , i, 0);
            }
            network.send();
            Thread.sleep(1000);
        }
    }

    public static RGBColor[] getRGBColors(int begin, int end) {
        // TODO: handle bad input
        int numberOfPixels = end-begin;
        RGBColor rgbArr[] = new RGBColor[numberOfPixels];

        for(int i=0; i<numberOfPixels; i++) {
            rgbArr[i] = new RGBColor();
            if (i == 50) {
                rgbArr[i].r = (byte)(0x00);
                rgbArr[i].g = (byte)(0x00);
                rgbArr[i].b = (byte)(0xFF);
            }
        }
        return rgbArr;
    }
}