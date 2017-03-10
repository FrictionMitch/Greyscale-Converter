
/**
 * Write a description of NegativeCreator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 *
 */
import edu.duke.*;
import java.io.File;

public class NegativeCreator {

    public ImageResource makeNegative(ImageResource inImage) {
        ImageResource negativeImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel pixel : negativeImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return negativeImage;
    }


    public void selectAndConvert() {
        DirectoryResource dir = new DirectoryResource();
        for (File file : dir.selectedFiles()) {
            ImageResource image = new ImageResource(file);
            ImageResource negative = makeNegative(image);
            String fileName = image.getFileName();
            String newName = "negative_" + fileName;
            negative.setFileName(newName);
            negative.save();
            negative.draw();

        }
    }
}