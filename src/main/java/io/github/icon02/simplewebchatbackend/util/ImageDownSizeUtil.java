package io.github.icon02.simplewebchatbackend.util;

import io.github.icon02.simplewebchatbackend.entity.ProfileImage;
import io.github.icon02.simplewebchatbackend.exception.FileNotSupportedException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component
public class ImageDownSizeUtil {

    private static final int sample_width = 720;
    private static final int thumbnail_width = 72;

    public void createImageData(ProfileImage image, MultipartFile file) {
        try {
            image.setData(downSize(file.getBytes(), sample_width));
            image.setThumbnail(downSize(file.getBytes(), thumbnail_width));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotSupportedException();
        }
    }

    private byte[] downSize(byte[] data, int width) {
        ByteArrayInputStream inStream = new ByteArrayInputStream(data);
        BufferedImage image = null;
        try {
            image = ImageIO.read(inStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotSupportedException();
        }

        if(image.getWidth() > width) {
            double scaleVal = (double) width / (double) image.getWidth();
            BufferedImage scaledImage = new BufferedImage((int) (image.getWidth() * scaleVal), (int) (image.getHeight() * scaleVal), BufferedImage.TYPE_INT_ARGB);
            AffineTransform transform = new AffineTransform();
            transform.scale(scaleVal, scaleVal);

            AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
            scaledImage = transformOp.filter(image, scaledImage);

            ByteArrayOutputStream outStream = new ByteArrayOutputStream();

            try {
                // does only work if I use fileFormat = "png" although images might come as "jpg" etc..
                ImageIO.write(scaledImage, "png", outStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new FileNotSupportedException();
            }
            System.out.println(new String(Base64.getEncoder().encode(outStream.toByteArray())));
            return outStream.toByteArray();
        } else {
            return data;
        }


    }
}
