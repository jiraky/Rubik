package it.univr.rubikcube.model;

/**
 * <p>Enumeration of the sides of a Rubik cube using Singmaster's notation.</p>
 * <p>Remark: when drawing the cube in an isometric projection, the player
 * should be able to view the front side on the left, the right side on the
 * right and the up side at the top.</p>
 * @author Alessandro Menti
 */
public enum RubikCubeSide {
    /** The side facing the solver. */
    FRONT(0, "front", RubikCubeFaceColor.RED),
    /** The side opposite the front. */
    BACK(1, "back", RubikCubeFaceColor.ORANGE),
    /** The side on top of the front side. */
    UP(2, "upper", RubikCubeFaceColor.WHITE),
    /** The side opposite the top. */
    DOWN(3, "down", RubikCubeFaceColor.YELLOW),
    /** The side directly to the left of the front. */
    LEFT(4, "left", RubikCubeFaceColor.GREEN),
    /** The side directly to the right of the front. */
    RIGHT(5, "right", RubikCubeFaceColor.BLUE);

    /**
     * Numeric value of the constant identifying the side in the configuration
     * array.
     */
    private int value;
    /**
     * Textual description of the side.
     */
    private String description;
    /**
     * <p>Color associated with the specified side in the standard
     * configuration:</p>
     * <ul>
     * <li>the top face is white;</li>
     * <li>the front face is red;</li>
     * <li>the left face is green;</li>
     * <li>the right face is blue;</li>
     * <li>the bottom face is yellow;</li>
     * <li>the back face is orange.</li>
     * </ul>
     */
    private RubikCubeFaceColor standardColor;
    /**
     * Creates a new RubikCubeSide constant.
     * @param v Numeric value associated with the face.
     * @param d Textual description of the side.
     * @param c Color of the side in the standard configuration.
     */
    RubikCubeSide(final int v, final String d, final RubikCubeFaceColor c) {
        this.value = v;
        this.description = d;
        this.standardColor = c;
    }
    /**
     * Gets the numeric value of the constant.
     * @return Numeric value associated with the face.
     */
    public int getValue() {
        return this.value;
    }
    /**
     * Gets the textual description of the side.
     * @return Textual description of the side.
     */
    public String getDescription() {
        return this.description;
    }
    /**
     * Gets the standard color associated with the specified side in the
     * standard configuration.
     * @return The standard color.
     */
    public final RubikCubeFaceColor getStandardColor() {
        return this.standardColor;
    }
}
