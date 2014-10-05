package it.univr.rubikcube.model;

import java.awt.Color;

/**
 * Color of a face of a Rubik cube.
 * @author Alessandro Menti
 */
public enum RubikCubeFaceColor {
    // DO NOT ALTER THE ORDER as the array storing the colors depends on it!
    // Add new colors to the bottom.
    /** White face. */
    WHITE(new Color(255, 255, 255), 'W'),
    /** Red face. */
    RED(new Color(255, 0, 0), 'R'),
    /** Blue face. */
    BLUE(new Color(0, 51, 255), 'B'),
    /** Orange face. **/
    ORANGE(new Color(255, 153, 0), 'O'),
    /** Green face. */
    GREEN(new Color(0, 153, 0), 'G'),
    /** Yellow face. */
    YELLOW(new Color(255, 255, 0), 'Y');
    /**
     * RGB color associated with the color.
     */
    private Color rgbColor;
    /**
     * Color letter associated with this color.
     */
    private char colorLetter;
    /**
     * Creates a new Rubik cube face color associated with the specified RGB
     * color.
     * @param c RGB color associated with the color.
     * @param l Letter identifying the color.
     */
    RubikCubeFaceColor(final Color c, final char l) {
        this.rgbColor = c;
        this.colorLetter = l;
    }
    /**
     * Gets the color associated with this color.
     * @return RGB color associated with this color.
     */
    public final Color getColor() {
        return this.rgbColor;
    }
    /**
     * Gets the letter identifying the color.
     * @return Letter identifying the color.
     */
    @Override
    public final String toString() {
        return Character.toString(this.colorLetter);
    }
    /**
     * Gets the face of the Rubik cube corresponding to a given color.
     * @param c Color to be matched.
     * @return Face corresponding to the given color.
     * @throws IllegalArgumentException Thrown if the color does not correspond
     * to any face.
     */
    public static final RubikCubeFaceColor getFace(final Color c)
            throws IllegalArgumentException {
        for (RubikCubeFaceColor fc : RubikCubeFaceColor.values()) {
            if (c.equals(fc.getColor())) {
                return fc;
            }
        }
        throw new IllegalArgumentException("The color does not correspond to"
                                           + " any face");
    }
    /**
     * Gets the face of the Rubik cube corresponding to a given integer array
     * value.
     * @param i Integer array value to be matched.
     * @return Face corresponding to the given array value.
     * @throws IllegalArgumentException Thrown if the value does not correspond
     * to any face.
     */
    public static final RubikCubeFaceColor getFace(final int i)
            throws IllegalArgumentException {
        for (RubikCubeFaceColor fc : RubikCubeFaceColor.values()) {
            if (fc.ordinal() == i) {
                return fc;
            }
        }
        throw new IllegalArgumentException("The value does not correspond to"
                                           + " any face");
    }
}
