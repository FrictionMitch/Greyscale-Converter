import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class BlackTrigger {

    public ImageResource makeBlack(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for(Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            if(inPixel.getRed() == 0 && inPixel.getGreen() >= 170 && inPixel.getBlue() >= 235) {
                pixel.setGreen(0);
                pixel.setBlue(0);
//            }
//            if(inPixel.getX()<= 20 && inPixel.getY()<= 20) {
//                pixel.setRed(0);
//                pixel.setGreen(0);
//                pixel.setBlue(0);
            } else {
                pixel.setRed(inPixel.getRed());
                pixel.setGreen(inPixel.getGreen());
                pixel.setBlue(inPixel.getBlue());
            }
        }
        return outImage;
    }

    public void selectAndConvert() {
        DirectoryResource dir = new DirectoryResource();
        for (File file : dir.selectedFiles()) {
            ImageResource inImage = new ImageResource(file);
            ImageResource trigger = makeBlack(inImage);
            String fileName = inImage.getFileName();
            String newName = "black_trigger_" + fileName;
            trigger.setFileName(file.getParentFile() + "/" + newName);
            trigger.save();
            trigger.draw();
        }
    }

    public void selectAndConvertLocal() {
//        FileResource fr = new FileResource("1-300_11638_BOPP.png");
        File file = new File("CMYK.tif");
        ImageResource inImage = new ImageResource(file);
        ImageResource trigger = makeBlack(inImage);
        String fileName = inImage.getFileName();
        String newName = "black_trigger_" + fileName;
        trigger.setFileName(newName);
        trigger.save();
        trigger.draw();
    }

}
