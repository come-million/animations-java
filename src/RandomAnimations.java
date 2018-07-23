
public class RandomAnimations {

    static int numberOfControllers = 4;
    static int strandsPerController = 40;
    static int pixelsPerStrand = 250;
    static int TotalPixelsInStrand = pixelsPerStrand + 1 ;
    static int totalFixels = numberOfControllers * strandsPerController * TotalPixelsInStrand;

    public static void run() throws InterruptedException {

        System.out.println("hello1");

        intRGBArrays();

        final Network network = new Network();
        network.configure();

        AnimationIfc damka = new AnimationDamka();

        int hsbI;
        HSBColor hsbArr[] = m_randomPixels.GetAllPixels();

        while (true) {

            hsbI = 0;
            for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
                for (int stripI = 0; stripI  < strandsPerController; stripI++) {

                    for(int pixelI = 0; pixelI < TotalPixelsInStrand; pixelI++) {
                        damka.setHSBColor(hsbArr[hsbI], controllerI, stripI, pixelI);
                        hsbI++;
                        rgbArrays[controllerI][stripI][pixelI] = new RGBColor();
                    }
                }
            }

            UpdateRGBColors();

            for(int controllerI = 0; controllerI < numberOfControllers; controllerI++) {
                for (int stripI = 0; stripI  < strandsPerController; stripI++) {
                    network.addSegment("test", rgbArrays[controllerI][stripI] , stripI , 0);
                }
            }
            network.send();
            Thread.sleep(30);
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

    static RandomPixels m_randomPixels = new RandomPixels(totalFixels);
    static RGBColor rgbArrays[][][] = new RGBColor[numberOfControllers][strandsPerController][TotalPixelsInStrand];
}