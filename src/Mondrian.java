import java.util.*;
import java.awt.*;

// Creates either a basic or complex random artwork in the style of Mondrian.
public class Mondrian {

    // Behavior:
    //   - Paints a basic Mondrian-style artwork. Random rectangular regions are created
    //     and are filled randomly with either red, cyan, yellow, or white and have a black
    //     border.
    // Exceptions:
    //   - Throws an IllegalArgumentException if the given pixels are null
    //   - Throws an IllegalArgumentException if either the width or height of the pixels
    //     is less than 300
    // Parameters:
    //   - Color[][] pixels - a 2D grid of pixels, each pixel represented by a color
    public void paintBasicMondrian(Color[][] pixels) {
        if (pixels == null || pixels.length < 300 || pixels[0].length < 300) {
            throw new IllegalArgumentException("Pixels cannot be null or have length or "
                                               + "width < 300");
        }
        paintBasicMondrian(pixels, 0, pixels[0].length, 0, pixels.length);
    }

    // Behavior:
    //   - Paints a basic Mondrian-style artwork. Random rectangular regions are created
    //     and are filled randomly with either red, cyan, yellow, or white and have a black
    //     border.
    // Parameters:
    //   - Color[][] pixels - a 2D grid of pixels, each pixel represented by a color
    //   - int x1 - the leftmost point in the grid (inclusive)
    //   - int x2 - the rightmost point in the grid (exclusive)
    //   - int y1 - the highest point in the grid (inclusive)
    //   - int y2 - the lowest point in the grid (exclusive)
    private void paintBasicMondrian(Color[][] pixels, int x1, int x2, int y1, int y2) {
        int fullWidth = pixels[0].length;
        int fullHeight = pixels.length;
        int width = x2 - x1;
        int height = y2 - y1;
        if (width < fullWidth / 4 && height < fullHeight / 4) {
            fill(pixels, x1, x2, y1, y2);
        } else if (width >= fullWidth / 4 && height >= fullHeight / 4) {
            int splitX = (int) (Math.random() * (width - 19)) + x1 + 10;
            int splitY = (int) (Math.random() * (height - 19)) + y1 + 10;
            paintBasicMondrian(pixels, x1, splitX, y1, splitY);
            paintBasicMondrian(pixels, splitX, x2, y1, splitY);
            paintBasicMondrian(pixels, x1, splitX, splitY, y2);
            paintBasicMondrian(pixels, splitX, x2, splitY, y2);
        } else if (width >= fullWidth / 4) {
            int splitX = (int) (Math.random() * (width - 19)) + x1 + 10;
            paintBasicMondrian(pixels, x1, splitX, y1, y2);
            paintBasicMondrian(pixels, splitX, x2, y1, y2);
        } else {
            int splitY = (int) (Math.random() * (height - 19)) + y1 + 10;
            paintBasicMondrian(pixels, x1, x2, y1, splitY);
            paintBasicMondrian(pixels, x1, x2, splitY, y2);
        }
    }

    // Behavior:
    //   - Paints a complex Mondrian-style artwork. Random rectangular, square, or circular
    //     regions are created and are filled randomly with either red, cyan, yellow, or 
    //     white and have a black border.
    // Exceptions:
    //   - Throws an IllegalArgumentException if the given pixels are null
    //   - Throws an IllegalArgumentException if either the width or height of the pixels
    //     is less than 300
    // Parameters:
    //   - Color[][] pixels - a 2D grid of pixels, each pixel represented by a color
    public void paintComplexMondrian(Color[][] pixels) {
        if (pixels == null || pixels.length < 300 || pixels[0].length < 300) {
            throw new IllegalArgumentException("Pixels cannot be null or have length or "
                                               + "width < 300");
        }
        paintComplexMondrian(pixels, 0, pixels[0].length, 0, pixels.length);
    }
        
    // Behavior:
    //   - Paints a complex Mondrian-style artwork. Random rectangular, square, or circular
    //     regions are created and are filled randomly with either red, cyan, yellow, or 
    //     white and have a black border.
    // Parameters:
    //   - Color[][] pixels - a 2D grid of pixels, each pixel represented by a color
    //   - int x1 - the leftmost point in the grid (inclusive)
    //   - int x2 - the rightmost point in the grid (exclusive)
    //   - int y1 - the highest point in the grid (inclusive)
    //   - int y2 - the lowest point in the grid (exclusive)
    private void paintComplexMondrian(Color[][] pixels, int x1, int x2, int y1, int y2) {
        int fullWidth = pixels[0].length;
        int fullHeight = pixels.length;
        int width = x2 - x1;
        int height = y2 - y1;
        if (width < fullWidth / 4 && height < fullHeight / 4) {
            int shapeNumber = (int) (Math.random() * 3);
            if (shapeNumber == 0) {
                fill(pixels, x1, x2, y1, y2);
            } else if (shapeNumber == 1) {
                fillCircle(pixels, x1, x2, y1, y2);
            } else {
                fillSquare(pixels, x1, x2, y1, y2);
            }
        } else if (width >= fullWidth / 4 && height >= fullHeight / 4) {
            int splitX = (int) (Math.random() * (width - 19)) + x1 + 10;
            int splitY = (int) (Math.random() * (height - 19)) + y1 + 10;
            paintComplexMondrian(pixels, x1, splitX, y1, splitY);
            paintComplexMondrian(pixels, splitX, x2, y1, splitY);
            paintComplexMondrian(pixels, x1, splitX, splitY, y2);
            paintComplexMondrian(pixels, splitX, x2, splitY, y2);
        } else if (width >= fullWidth / 4) {
            int splitX = (int) (Math.random() * (width - 19)) + x1 + 10;
            paintComplexMondrian(pixels, x1, splitX, y1, y2);
            paintComplexMondrian(pixels, splitX, x2, y1, y2);
        } else {
            int splitY = (int) (Math.random() * (height - 19)) + y1 + 10;
            paintComplexMondrian(pixels, x1, x2, y1, splitY);
            paintComplexMondrian(pixels, x1, x2, splitY, y2);
        }
    }

    // Behavior:
    //   - Fills the given rectangular region with a random color chosen from either red,
    //     yellow, cyan, or white, leaving a single-pixel black border around the edge of
    //     the region.
    // Parameters:
    //   - Color[][] pixels - the grid of pixels that contains the region to be filled
    //   - int x1 - the leftmost point of the region (inclusive)
    //   - int x2 - the rightmost point of the region (exclusive)
    //   - int y1 - the highest point of the region (inclusive)
    //   - int y2 - the lowest point of the region (exclusive)
    private void fill(Color[][] pixels, int x1, int x2, int y1, int y2) {
        Color fillColor = randomFillColor();
        for (int i = y1 + 1; i < y2 - 1; i++) {
            for (int j = x1 + 1; j < x2 - 1; j++) {
                pixels[i][j] = fillColor;
            }
        }
    }

    // Behavior:
    //   - Fills the largest possible square in the given rectangular region with a random
    //     color chosen from either red, yellow, cyan, or white. A single-pixel border
    //     around the edge of the region and any additional parts of the region outside of
    //     the square remain black.
    // Parameters:
    //   - Color[][] pixels - the grid of pixels that contains the region to be filled
    //   - int x1 - the leftmost point of the region (inclusive)
    //   - int x2 - the rightmost point of the region (exclusive)
    //   - int y1 - the highest point of the region (inclusive)
    //   - int y2 - the lowest point of the region (exclusive)
    private void fillSquare(Color[][] pixels, int x1, int x2, int y1, int y2) {
        if (y2 - y1 > x2 - x1) {
            int change = ((y2 - y1) - (x2 - x1)) / 2;
            y1 = y1 + change;
            y2 = y2 - change;
            fill(pixels, x1, x2, y1, y2);
        } else {
            int change = ((x2 - x1) - (y2 - y1)) / 2;
            x1 = x1 + change;
            x2 = x2 - change;
            fill(pixels, x1, x2, y1, y2);
        }
    }

    // Behavior:
    //   - Fills the largest possible circle in the given rectangular region with a random
    //     color chosen from either red, yellow, cyan, or white. A single-pixel border
    //     around the edge of the region and any additional parts of the region outside of
    //     the circle remain black.
    // Parameters:
    //   - Color[][] pixels - the grid of pixels that contains the region to be filled
    //   - int x1 - the leftmost point of the region (inclusive)
    //   - int x2 - the rightmost point of the region (exclusive)
    //   - int y1 - the highest point of the region (inclusive)
    //   - int y2 - the lowest point of the region (exclusive)
    private void fillCircle(Color[][] pixels, int x1, int x2, int y1, int y2) {
        Color fillColor = randomFillColor();
        int radius = 0;
        if (x2 - x1 < y2 - y1) {
            radius = (x2 - x1) / 2 - 1;
        } else {
            radius = (y2 - y1) / 2 - 1;
        }
        int centerX = (x2 - x1) / 2 + x1;
        int centerY = (y2 - y1) / 2 + y1;
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                if (Math.pow(i - centerY, 2) + Math.pow(j - centerX, 2) <= Math.pow(radius, 2)) {
                    pixels[i][j] = fillColor;
                }
            }
        }
    }

    // Returns:
    //   - A random color out of the options: red, cyan, yellow, or white
    private Color randomFillColor() {
        int colorNumber = (int) (Math.random() * 4);
        Color fillColor = null;
        if (colorNumber == 0) {
            fillColor = Color.RED;
        } else if (colorNumber == 1) {
            fillColor = Color.YELLOW;
        } else if (colorNumber == 2) {
            fillColor = Color.CYAN;
        } else {
            fillColor = Color.WHITE;
        }
        return fillColor;
    } 
}
