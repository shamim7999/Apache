package org.apache.helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class ImageUtils {
    private ImageUtils() {} // Prevent instantiation

    public static float[] calculateSize(String imageFilePath, float targetWidth) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(imageFilePath));
        float originalWidth = bufferedImage.getWidth();
        float originalHeight = bufferedImage.getHeight();

        float targetHeight = targetWidth * (originalHeight / originalWidth);
        return new float[]{targetWidth, targetHeight};
    }
}