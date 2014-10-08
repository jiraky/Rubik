package it.univr.rubikcube.model;
/**
 * Colors of the facelets making an edge.
 * @author Alessandro Menti
 */
public class RubikCubeCornerColor {
    /**
     * Color of the first face.
     */
    private RubikCubeFaceColor firstFace;
    /**
     * Color of the second face.
     */
    private RubikCubeFaceColor secondFace;
    /**
     * Color of the third face.
     */
    private RubikCubeFaceColor thirdFace;
    /**
     * Creates a new corner color triplet.
     * @param c1 Color of the first facelet of the corner.
     * @param c2 Color of the second facelet of the corner.
     * @param c3 Color of the third facelet of the corner.
     */
    public RubikCubeCornerColor(final RubikCubeFaceColor c1,
                                final RubikCubeFaceColor c2,
                                final RubikCubeFaceColor c3) {
        this.firstFace = c1;
        this.secondFace = c2;
        this.thirdFace = c3;
    }
    /**
     * Gets the color of the first face.
     * @return Color of the first face.
     */
    public final RubikCubeFaceColor getFirstColor() {
        return this.firstFace;
    }
    /**
     * Gets the color of the second face.
     * @return Color of the second face.
     */
    public final RubikCubeFaceColor getSecondColor() {
        return this.secondFace;
    }
    /**
     * Gets the color of the third face.
     * @return Color of the third face.
     */
    public final RubikCubeFaceColor getThirdColor() {
        return this.thirdFace;
    }
}
