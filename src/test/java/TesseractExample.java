import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import net.sourceforge.tess4j.*;
import javax.imageio.ImageIO;

public class TesseractExample {

    public static void main(String[] args) throws TesseractException, IOException {
        solver();
    }

    public static void solver() throws TesseractException, IOException {
        ITesseract instance = new Tesseract();
        instance.setDatapath("src/test/java/data");

        // Define the correct values of CAPTCHA images.
        String[] correctValues = {"IEDka", "NDMCC", "HyBCg", "EjNek", "PIZRZ", "C8hed"};

        for (int i = 1; i <= 6; i++) {
            boolean isCorrect = false;
            int attempt = 0;

            while (!isCorrect && attempt < 10) { // Maximum limit of 10 attempts.
                attempt++;

                // Reload the image.
                File imageFile = new File("src/test/java/image/" + i + ".png");
                BufferedImage image = ImageIO.read(imageFile);

                // Process the image.
                BufferedImage processedImage = RedToWhiteConverter(image);

                // Manipulate it: Make small changes to the image in each loop.
                processedImage = applyRandomTransform(processedImage, attempt);

                // Save the processed image to a temporary file.
                File outputFile = new File("src/test/java/convertImage/" + i + "_attempt" + attempt + ".png");
                ImageIO.write(processedImage, "png", outputFile);

                // Apply the OCR process.
                String result = instance.doOCR(processedImage).trim();

                System.out.println("Attempt " + attempt + " for image " + i + " --> " + result);

                // Check the result.
                if (result.equals(correctValues[i - 1])) {
                    System.out.println("Correct value for image " + i + ": " + result);
                    isCorrect = true;
                } else {
                    System.out.println("Incorrect value, retrying...");
                }
            }

            // If the correct value is not found after 10 attempts, issue a warning.
            if (!isCorrect) {
                System.out.println("Failed to solve captcha for image " + i + " after 10 attempts.");
            }
        }
    }

    public static BufferedImage RedToWhiteConverter(BufferedImage image) throws IOException {
        int width = image.getWidth();
        int height = image.getHeight();

        // Pixel scanning
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Get the color of the current pixel.
                int pixel = image.getRGB(x, y);
                Color color = new Color(pixel, true);

                // Check if the pixel is red.
                if (color.getRed() > 200 && color.getGreen() < 100 && color.getBlue() < 100) {
                    // Convert the pixel to white.
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        return image;
    }

    public static BufferedImage applyRandomTransform(BufferedImage image, int attempt) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage transformedImage = new BufferedImage(width, height, image.getType());
        Graphics2D g = transformedImage.createGraphics();

        // Apply a small rotation.
        double angle = (attempt % 5) * 2.0; // Change the angle every 5 attempts.
        g.rotate(Math.toRadians(angle), width / 2, height / 2);

        // Draw the image.
        g.drawImage(image, 0, 0, null);
        g.dispose();

        // Alternatively, you can adjust the contrast or brightness.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = transformedImage.getRGB(x, y);
                Color color = new Color(rgb, true);

                // Make a slight contrast adjustment.
                int red = Math.min(255, (int) (color.getRed() * 1.1));
                int green = Math.min(255, (int) (color.getGreen() * 1.1));
                int blue = Math.min(255, (int) (color.getBlue() * 1.1));

                transformedImage.setRGB(x, y, new Color(red, green, blue).getRGB());
            }
        }

        return transformedImage;
    }
}
