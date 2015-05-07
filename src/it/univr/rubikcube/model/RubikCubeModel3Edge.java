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
    UR(RubikCubeSide.UP, RubikCubeSide.RIGHT, 1, 2, 2),
    /**
     * Edge comprising the upper and front faces.
     */
    UF(RubikCubeSide.UP, RubikCubeSide.FRONT, 2, 2, 1),
    /**
     * Edge comprising the upper and left faces.
     */
    UL(RubikCubeSide.UP, RubikCubeSide.LEFT, 1, 2, 0),
    /**
     * Edge comprising the upper and back faces.
     */
    UB(RubikCubeSide.UP, RubikCubeSide.BACK, 0, 2, 1),
    /**
     * Edge comprising the down and right faces.
     */
    DR(RubikCubeSide.DOWN, RubikCubeSide.RIGHT, 1, 0, 2),
    /**
     * Edge comprising the down and front faces.
     */
    DF(RubikCubeSide.DOWN, RubikCubeSide.FRONT, 2, 0, 1),
    /**
     * Edge comprising the down and left faces.
     */
    DL(RubikCubeSide.DOWN, RubikCubeSide.LEFT, 1, 0, 0),
    /**
     * Edge comprising the down and back faces.
     */
    DB(RubikCubeSide.DOWN, RubikCubeSide.BACK, 0, 0, 1),
    /**
     * Edge comprising the front and right faces.
     */
    FR(RubikCubeSide.FRONT, RubikCubeSide.RIGHT, 2, 1, 2),
    /**
     * Edge comprising the front and left faces.
     */
    FL(RubikCubeSide.FRONT, RubikCubeSide.LEFT, 2, 1, 0),
    /**
     * Edge comprising the back and left faces.
     */
    BL(RubikCubeSide.BACK, RubikCubeSide.LEFT, 0, 1, 0),
    /**
     * Edge comprising the back and right faces.
     */
    BR(RubikCubeSide.BACK, RubikCubeSide.RIGHT, 0, 1, 2);
    /**
     * Top side of the edge.
     */
    private RubikCubeSide topSide;
    /**
     * Lateral side of the edge.
     */
    private RubikCubeSide lateralSide;
    /**
     * X coordinate.
     */
    private int xCoord;
    /**
     * Y coordinate.
     */
    private int yCoord;
    /**
     * Z coordinate.
     */
    private int zCoord;
    /**
     * Creates a new edge of a 3-dimensional Rubik cube.
     * @param t Top side.
     * @param l Lateral side.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    RubikCubeModel3Edge(final RubikCubeSide t, final RubikCubeSide l,
            final int x, final int y, final int z) {
        this.topSide = t;
        this.lateralSide = l;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
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
    /**
     * Gets the face on the specified axis, if it exists.
     * @param a Axis.
     * @return Face on the specified axis, or <tt>null</tt>.
     */
    public RubikCubeSide getFaceOnAxis(final RubikCubeModelAxis a) {
        if (a == RubikCubeModelAxis.X) {
            if (this.lateralSide != RubikCubeSide.LEFT && this.lateralSide != RubikCubeSide.RIGHT) {
                return null;
            } else {
                return this.lateralSide;
            }
        } else if (a == RubikCubeModelAxis.Y) {
            if (this.topSide != RubikCubeSide.UP && this.topSide != RubikCubeSide.DOWN) {
                return null;
            } else {
                return this.topSide;
            }
        } else if (a == RubikCubeModelAxis.Z) {
            if (this.topSide == RubikCubeSide.FRONT || this.topSide == RubikCubeSide.BACK) {
                return this.topSide;
            } else if (this.lateralSide == RubikCubeSide.FRONT || this.lateralSide == RubikCubeSide.BACK) {
                return this.lateralSide;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    /**
     * Gets the X coordinate.
     * @return X coordinate.
     */
    public final int getX() {
        return this.xCoord;
    }
    /**
     * Gets the Y coordinate.
     * @return Y coordinate.
     */
    public final int getY() {
        return this.yCoord;
    }
    /**
     * Gets the Z coordinate.
     * @return Z coordinate.
     */
    public final int getZ() {
        return this.zCoord;
    }
    /**
     * Gets a standard 3D edge given two colors.
     * @param c1 First color.
     * @param c2 Second color.
     * @return The standard 3D edge having the specified colors, or
     * <tt>null</tt>.
     */
    public static RubikCubeModel3Edge getStandardEdgeFromColors(
            final RubikCubeFaceColor c1, final RubikCubeFaceColor c2) {
        for (RubikCubeModel3Edge e: RubikCubeModel3Edge.values()) {
            if ((c1 == e.getLateralSide().getStandardColor()
                    && c2 == e.getTopSide().getStandardColor())
                || (c2 == e.getLateralSide().getStandardColor()
                && c1 == e.getTopSide().getStandardColor())) {
                return e;
            }
        }
        return null;
    }
}
