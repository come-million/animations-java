
public class RandomAnimations {

    static int numberOfControllers = 4;
    static int strandsPerController = 40;
    static int pixelsPerStrand = 250;
    static int TotalPixelsInStrand = pixelsPerStrand + 1 ;
    static int totalFixels = numberOfControllers * strandsPerController * TotalPixelsInStrand;

    public static void run() throws InterruptedException {

        System.out.println("hello1");

        intRGBArrays();
        initAnimations();

        final Network network = new Network();
        network.configure();

        while (true) {

            long currTime = System.currentTimeMillis();
            double timePercent = (currTime % animationLoopTimeMs) / animationLoopTimeMs;

            applyAnimation(timePercent);
            UpdateRGBColors();

            for(int controllerI = 0; controllerI < 1; controllerI++) {
                for (int stripI = 0; stripI  < strandsPerController; stripI++) {
                    network.addSegment("test", rgbArrays[controllerI][stripI], stripI , 0);
                }
            }
            network.send();
            Thread.sleep(10);
        }
    }

    static void applyAnimation(double timePercent) {

        HSBColor hsbArr[] = m_randomPixels.GetAllPixels();

        int hsbI;
        hsbI = 0;

        for(int anI = 0; anI < m_an.length; anI++) {
            for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
                for (int stripI = 0; stripI  < strandsPerController; stripI++) {

                    for(int pixelI = 0; pixelI < TotalPixelsInStrand; pixelI++) {
                        m_an[anI].setHSBColor(hsbArr[hsbI], controllerI, stripI, pixelI, timePercent);
                        hsbI++;
                        rgbArrays[controllerI][stripI][pixelI] = new RGBColor();
                    }
                }
            }
        }
    }

    static void UpdateRGBColors() {

        HSBColor hsbArr[] = m_randomPixels.GetAllPixels();

        int hsbI = 0;
        for (int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI < strandsPerController; stripI++) {
                for(int pixelI = 0; pixelI < TotalPixelsInStrand; pixelI++) {
                    hsbArr[hsbI].toRGBColor(rgbArrays[controllerI][stripI][pixelI]);
                    hsbI++;
                }
            }
        }
    }

    static void intRGBArrays() {
        for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI < strandsPerController; stripI++) {
                for(int pixelI = 0; pixelI < TotalPixelsInStrand; pixelI++) {
                    rgbArrays[controllerI][stripI][pixelI] = new RGBColor();
                }
            }
        }
    }

    static void initAnimations() {
        m_an = new AnimationIfc[1];
        m_an[0] = new AnimationDamka();
    }

    static RandomPixels m_randomPixels = new RandomPixels(totalFixels);
    static RGBColor rgbArrays[][][] = new RGBColor[numberOfControllers][strandsPerController][TotalPixelsInStrand];
    static double animationLoopTimeMs = 5.0 * 1000.0;
    static AnimationIfc m_an[];
}