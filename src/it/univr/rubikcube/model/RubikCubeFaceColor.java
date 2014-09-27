package it.univr.rubikcube.model;

import java.awt.Color;

/**
 * Color of a face of a Rubik cube.
 * @author Alessandro Menti
 */
public enum RubikCubeFaceColor {
    /** White face. */
    WHITE(new Color(255, 255, 255)),
    /** Red face. */
    RED(new Color(255, 0, 0)),
    /** Blue face. */
    BLUE(new Color(0, 51, 255)),
    /** Orange face. **/
    ORANGE(new Color(255, 153, 0)),
    /** Green face. */
    GREEN(new Color(0, 153, 0)),
    /** Yellow face. */
    YELLOW(new Color(255, 255, 0));
    /**
     * RGB color associated with the face.
     */
    private Color rgbColor;
    /**
     * Creates a new Rubik cube face color associated with the specified RGB
     * color.
     * @param c RGB color associated with the face.
     */
    RubikCubeFaceColor(final Color c) {
        this.rgbColor = c;
    }
    /**
     * Gets the color associated with this face.
     * @return RGB color associated with this face.
     */
    public final Color getColor() {
        return this.rgbColor;
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
