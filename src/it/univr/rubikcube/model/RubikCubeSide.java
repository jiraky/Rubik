package it.univr.rubikcube.model;

/**
 * <p>Enumeration of the sides of a Rubik cube using Singmaster's notation.</p>
 * <p>Remark: when drawing the cube in an isometric projection, the player
 * should be able to view the front side on the left, the right side on the
 * right and the up side at the top.</p>
 * @author Alessandro Menti
 */
public enum RubikCubeSide {
    // DO NOT ALTER THE ORDER as the array storing the sides depends on it!
    // Add new sides to the bottom.
    /** The side facing the solver. */
    FRONT("front", RubikCubeFaceColor.RED),
    /** The side opposite the front. */
    BACK("back", RubikCubeFaceColor.ORANGE),
    /** The side on top of the front side. */
    UP("upper", RubikCubeFaceColor.WHITE),
    /** The side opposite the top. */
    DOWN("down", RubikCubeFaceColor.YELLOW),
    /** The side directly to the left of the front. */
    LEFT("left", RubikCubeFaceColor.GREEN),
    /** The side directly to the right of the front. */
    RIGHT("right", RubikCubeFaceColor.BLUE);
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
     * @param d Textual description of the side.
     * @param c Color of the side in the standard configuration.
     */
    RubikCubeSide(final String d, final RubikCubeFaceColor c) {
        this.description = d;
        this.standardColor = c;
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
