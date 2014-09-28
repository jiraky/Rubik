package it.univr.rubikcube.model;

import java.awt.Color;

/**
 * Color of a face of a Rubik cube.
 * @author Alessandro Menti
 */
public enum RubikCubeFaceColor {
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
     * RGB color associated with the face.
     */
    private Color rgbColor;
    /**
     * Color letter associated with this face.
     */
    private char colorLetter;
    /**
     * Creates a new Rubik cube face color associated with the specified RGB
     * color.
     * @param c RGB color associated with the face.
     * @param l Letter identifying the color.
     */
    RubikCubeFaceColor(final Color c, final char l) {
        this.rgbColor = c;
        this.colorLetter = l;
    }
    /**
     * Gets the color associated with this face.
     * @return RGB color associated with this face.
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
}
