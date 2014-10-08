package it.univr.rubikcube.model;
/**
 * Colors of the facelets making an edge.
 * @author Alessandro Menti
 */
public class RubikCubeEdgeColor {
    /**
     * Color of the first face.
     */
    private RubikCubeFaceColor firstFace;
    /**
     * Color of the second face.
     */
    private RubikCubeFaceColor secondFace;
    /**
     * Creates a new Edge color pair.
     * @param c1 Color of the first facelet of the edge.
     * @param c2 Color of the second facelet of the edge.
     */
    public RubikCubeEdgeColor(final RubikCubeFaceColor c1,
                              final RubikCubeFaceColor c2) {
        this.firstFace = c1;
        this.secondFace = c2;
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
}
