
public class RandomAnimations {

    int numberOfControllers = 4;
    int strandsPerController = 40;
    int pixelsPerStrand = 250;
    int totalPixelsInStrand = pixelsPerStrand + 1 ;
    int totalPixels = numberOfControllers * strandsPerController * totalPixelsInStrand;

    public void run() throws InterruptedException {

        intRGBArrays();
        initAnimations();

        final Network network = new Network();
        network.configure();

        while (true) {

            long currTime = System.currentTimeMillis();
            double absTimeSeconds = currTime / 1000.0;
            double timePercent = (currTime % animationLoopTimeMs) / animationLoopTimeMs;

            checkForAnChange(currTime);
            applyAnimation(timePercent, absTimeSeconds, currentAnIndex, 0);
            applyAnimation(timePercent, absTimeSeconds, prevAnIndex, 1);
            UpdateRGBColors(currTime);

            for(int controllerI = 0; controllerI < 1; controllerI++) {
                for (int stripI = 0; stripI  < strandsPerController; stripI++) {
                    network.addSegment("test", rgbArrays[controllerI][stripI], stripI , 0);
                }
            }
            network.send();
            Thread.sleep(10);
        }
    }

    void checkForAnChange(long currTime) {
        if(currTime - m_lastChangeTime > m_currentAnimationLengthMs) {
            prevAnIndex = currentAnIndex;
            currentAnIndex = (currentAnIndex + 1) % m_an.length;
            m_lastChangeTime = currTime;
        }
    }

    void applyAnimation(double timePercent, double absTimeSeconds, int anIndex, int ledObjectIndex) {

        HSBColor hsbArr[] = m_randomPixels[ledObjectIndex].GetAllPixels();

        int hsbI;
        hsbI = 0;

        for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI  < strandsPerController; stripI++) {

                for(int pixelI = 0; pixelI < totalPixelsInStrand; pixelI++) {
                    m_an[anIndex].setHSBColor(hsbArr[hsbI], hsbI, controllerI, stripI, pixelI, timePercent, absTimeSeconds);
                    hsbI++;
                }
            }
        }
    }

    void UpdateRGBColors(long currTime) {

        double amount1 = 1.0;
        double amount2 = 0.0;

        long msSinceLastChange = currTime - m_lastChangeTime;
        if(msSinceLastChange < m_fadeAnimationsTime) {
            amount1 = msSinceLastChange / (double)m_fadeAnimationsTime;
            amount2 = 1.0 - amount1;
        }

        HSBColor currHsbArr[] = m_randomPixels[0].GetAllPixels();
        HSBColor prevHsbArr[] = m_randomPixels[1].GetAllPixels();

        // create once to reduce memory usage
        RGBColor currRGB = new RGBColor();
        RGBColor prevRGB = new RGBColor();

        int hsbI = 0;
        for (int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI < strandsPerController; stripI++) {
                for(int pixelI = 0; pixelI < totalPixelsInStrand; pixelI++) {
                    currHsbArr[hsbI].toRGBColor(currRGB);
                    prevHsbArr[hsbI].toRGBColor(prevRGB);
                    rgbArrays[controllerI][stripI][pixelI].mixTwoColors(currRGB, amount1, prevRGB, amount2);
                    hsbI++;
                }
            }
        }
    }

    void intRGBArrays() {
        for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
            for (int stripI = 0; stripI < strandsPerController; stripI++) {
                for(int pixelI = 0; pixelI < totalPixelsInStrand; pixelI++) {
                    rgbArrays[controllerI][stripI][pixelI] = new RGBColor();
                }
            }
        }
    }

    void initAnimations() {
        m_an = new AnimationIfc[6];
        m_an[0] = new AnimationSingleColor();
        m_an[1] = new AnimationDamka();
        m_an[2] = new AnimationRandomHue(totalPixels);
        m_an[3] = new AnimationWaves();
        m_an[4] = new AnimationFoox();
        m_an[5] = new AnimationBreath(totalPixels);
    }

    RandomPixels m_randomPixels[] = new RandomPixels[] {new RandomPixels(totalPixels), new RandomPixels(totalPixels)};
    RGBColor rgbArrays[][][] = new RGBColor[numberOfControllers][strandsPerController][totalPixelsInStrand];
    double animationLoopTimeMs = 10.0 * 1000.0;
    long m_currentAnimationLengthMs = 20 * 1000;
    long m_fadeAnimationsTime = 2 * 1000;

    AnimationIfc m_an[];
    int currentAnIndex = 0;
    int prevAnIndex = 0;
    long m_lastChangeTime;
}