package it.univr.rubikcube.model;

/**
 * Edge of the Rubik cube. The edge is named after the faces it comprises.
 * @author Alessandro Menti
 */
public enum RubikCubeModel3Edge {
    // DO NOT ALTER the order here as some arrays rely on it.
    // Add new members to the bottom.
    /**
     * Edge comprising the upper and right faces.
     */
    UR(RubikCubeSide.UP, RubikCubeSide.RIGHT),
    /**
     * Edge comprising the upper and front faces.
     */
    UF(RubikCubeSide.UP, RubikCubeSide.FRONT),
    /**
     * Edge comprising the upper and left faces.
     */
    UL(RubikCubeSide.UP, RubikCubeSide.LEFT),
    /**
     * Edge comprising the upper and back faces.
     */
    UB(RubikCubeSide.UP, RubikCubeSide.BACK),
    /**
     * Edge comprising the down and right faces.
     */
    DR(RubikCubeSide.DOWN, RubikCubeSide.RIGHT),
    /**
     * Edge comprising the down and front faces.
     */
    DF(RubikCubeSide.DOWN, RubikCubeSide.FRONT),
    /**
     * Edge comprising the down and left faces.
     */
    DL(RubikCubeSide.DOWN, RubikCubeSide.LEFT),
    /**
     * Edge comprising the down and back faces.
     */
    DB(RubikCubeSide.DOWN, RubikCubeSide.BACK),
    /**
     * Edge comprising the front and right faces.
     */
    FR(RubikCubeSide.FRONT, RubikCubeSide.RIGHT),
    /**
     * Edge comprising the front and left faces.
     */
    FL(RubikCubeSide.FRONT, RubikCubeSide.LEFT),
    /**
     * Edge comprising the back and left faces.
     */
    BL(RubikCubeSide.BACK, RubikCubeSide.LEFT),
    /**
     * Edge comprising the back and right faces.
     */
    BR(RubikCubeSide.BACK, RubikCubeSide.RIGHT);
    /**
     * Top side of the edge.
     */
    private RubikCubeSide topSide;
    /**
     * Lateral side of the edge.
     */
    private RubikCubeSide lateralSide;
    /**
     * Creates a new edge of a 3-dimensional Rubik cube.
     * @param t Top side.
     * @param l Lateral side.
     */
    RubikCubeModel3Edge(final RubikCubeSide t, final RubikCubeSide l) {
        this.topSide = t;
        this.lateralSide = l;
    }
    /**
     * Gets the top side of the edge. In case no up/down face is in the edge,
     * returns the front/back side.
     * @return Top side of the edge.
     */
    public RubikCubeSide getTopSide() {
        return this.topSide;
    }
    /**
     * Gets the lateral side of the edge. In case no up/down face is in the
     * edge, returns the left/right side.
     * @return Lateral side of the edge.
     */
    public RubikCubeSide getLateralSide() {
        return this.lateralSide;
    }
}
