/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzaheaven.helpers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Base64;
import javax.imageio.ImageIO;
import pizzaheaven.models.Image;


/**
 *
 * @author dansc
 */
public class ImageHelpers {
    public static String base64Encode(BufferedImage image) {
        // get DataBufferBytes from Raster
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            os.flush();
            String base64 = Base64.getEncoder().encodeToString(os.toByteArray());
            os.close();
            return base64;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
    
    public static BufferedImage base64Decode(Image image) {
        String source = image.getBase64();
        BufferedImage buffImage = null;
        byte[] imageByte = Base64.getDecoder().decode(image.getBase64());
        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
            buffImage = ImageIO.read(bis);
        } catch (Exception e) {
        
        }
        return buffImage;
    }
    
    public static BufferedImage resize(BufferedImage inputImage, int scaledWidth, int scaledHeight){
        try {
                // creates output image
            BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

                // scales the input image to the output image
            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
            g2d.dispose();
            return outputImage;
        } catch (Exception e) {
            System.out.println("[ERR] Error during image resize: " + e.getMessage());
            return null;
        }
    }
}
