import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class GreyscaleConverter {

        public ImageResource makeGrey(ImageResource inImage) {
            //Create a blank image the same size of Image you want to convert
            ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

            // For each Pixel in outImage...
            for (Pixel pixel : outImage.pixels()) {
                Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

                int average = ((inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3);

                pixel.setRed(average);
                pixel.setGreen(average);
                pixel.setBlue(average);
            }
            return outImage;
        }

        public void doSave() {
            DirectoryResource dir = new DirectoryResource();
            for (File file : dir.selectedFiles()) {
                ImageResource image = new ImageResource(file);
                String fileName = image.getFileName();
                String newName = "Copy_of_" + fileName;
//                System.getProperty("user.home"), "/Desktop")
//                File.getParentFile(): gray.setFileName(f.getParentFile() + “/” + grayFileName)
//                image.setFileName(System.getProperty("user.home")+"/Desktop" + newName);
                image.setFileName(file.getParentFile() + "/" + newName);
                image.draw();
                image.save();
            }
        }

        public void selectAndConvert() {
            DirectoryResource dir = new DirectoryResource();
            for (File file : dir.selectedFiles()) {
                ImageResource inImage = new ImageResource(file);
                ImageResource grey = makeGrey(inImage);
                String fileName = inImage.getFileName();
                String newName = "grey_" + fileName;
                grey.setFileName(newName);
                grey.setFileName(file.getParentFile() + "/" + newName);
                grey.save();
                grey.draw();

            }
        }

    }



