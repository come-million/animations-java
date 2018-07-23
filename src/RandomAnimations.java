
public class RandomAnimations {

    static int numberOfControllers = 4;
    static int strandsPerController = 40;
    static int pixelsPerStrand = 250;
    static int totalPixelsInStrand = pixelsPerStrand + 1 ;
    static int totalPixels = numberOfControllers * strandsPerController * totalPixelsInStrand;

    public static void run() throws InterruptedException {

        intRGBArrays();
        initAnimations();

        final Network network = new Network();
        network.configure();

        while (true) {

            long currTime = System.currentTimeMillis();
            double timePercent = (currTime % animationLoopTimeMs) / animationLoopTimeMs;

            checkForAnChange(currTime);
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

    static void checkForAnChange(long currTime) {
        if(currTime - m_lastChangeTime > m_currentAnimationLengthMs) {
            currentAnIndex = (currentAnIndex + 1) % m_an.length;
            m_lastChangeTime = currTime;
        }
    }

    static void applyAnimation(double timePercent) {

        HSBColor hsbArr[] = m_randomPixels.GetAllPixels();

        int hsbI;
        hsbI = 0;

        for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI  < strandsPerController; stripI++) {

                for(int pixelI = 0; pixelI < totalPixelsInStrand; pixelI++) {
                    m_an[currentAnIndex].setHSBColor(hsbArr[hsbI], hsbI, controllerI, stripI, pixelI, timePercent);
                    hsbI++;
                    rgbArrays[controllerI][stripI][pixelI] = new RGBColor();
                }
            }
        }
    }

    static void UpdateRGBColors() {

        HSBColor hsbArr[] = m_randomPixels.GetAllPixels();

        int hsbI = 0;
        for (int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI < strandsPerController; stripI++) {
                for(int pixelI = 0; pixelI < totalPixelsInStrand; pixelI++) {
                    hsbArr[hsbI].toRGBColor(rgbArrays[controllerI][stripI][pixelI]);
                    hsbI++;
                }
            }
        }
    }

    static void intRGBArrays() {
        for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI < strandsPerController; stripI++) {
                for(int pixelI = 0; pixelI < totalPixelsInStrand; pixelI++) {
                    rgbArrays[controllerI][stripI][pixelI] = new RGBColor();
                }
            }
        }
    }

    static void initAnimations() {
        m_an = new AnimationIfc[2];
        m_an[0] = new AnimationDamka();
        m_an[1] = new AnimationRandomHue(totalPixels);
    }

    static RandomPixels m_randomPixels = new RandomPixels(totalPixels);
    static RGBColor rgbArrays[][][] = new RGBColor[numberOfControllers][strandsPerController][totalPixelsInStrand];
    static double animationLoopTimeMs = 5.0 * 1000.0;
    static long m_currentAnimationLengthMs = 10 * 1000;

    static AnimationIfc m_an[];
    static int currentAnIndex = 0;
    static long m_lastChangeTime;
}