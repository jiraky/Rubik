package it.univr.rubikcube.model;

/**
 * Corner of the Rubik cube. Each corner is named after the three facelets it
 * comprises.
 * @author Alessandro Menti
 */
public enum RubikCubeCorner {
    // DO NOT ALTER the order as some parts of the application depend on the
    // ordinal.
    /**
     * Corner between the up, right and front faces.
     */
    URF(RubikCubeSide.FRONT, RubikCubeSide.RIGHT, RubikCubeSide.UP),
    /**
     * Corner between the up, front and left faces.
     */
    UFL(RubikCubeSide.LEFT, RubikCubeSide.FRONT, RubikCubeSide.UP),
    /**
     * Corner between the up, left and back faces.
     */
    ULB(RubikCubeSide.BACK, RubikCubeSide.LEFT, RubikCubeSide.UP),
    /**
     * Corner between the up, back and right faces.
     */
    UBR(RubikCubeSide.RIGHT, RubikCubeSide.BACK, RubikCubeSide.UP),
    /**
     * Corner between the down, front and right faces.
     */
    DFR(RubikCubeSide.FRONT, RubikCubeSide.RIGHT, RubikCubeSide.DOWN),
    /**
     * Corner between the down, left and front faces.
     */
    DLF(RubikCubeSide.LEFT, RubikCubeSide.FRONT, RubikCubeSide.DOWN),
    /**
     * Corner between the down, back and left faces.
     */
    DBL(RubikCubeSide.BACK, RubikCubeSide.LEFT, RubikCubeSide.DOWN),
    /**
     * Corner between the down, right and back faces.
     */
    DRB(RubikCubeSide.RIGHT, RubikCubeSide.BACK, RubikCubeSide.DOWN);
    /**
     * Left side of the corner (as seen in an anticlockwise rotation from the
     * top).
     */
    private RubikCubeSide leftSide;
    /**
     * Right side of the corner (as seen in an anticlockwise rotation from the
     * top).
     */
    private RubikCubeSide rightSide;
    /**
     * Up/down side of the corner.
     */
    private RubikCubeSide upDownSide;
    /**
     * Creates a new Rubik cube corner.
     * @param l Left side.
     * @param r Right side.
     * @param u Up/down side.
     */
    RubikCubeCorner(final RubikCubeSide l, final RubikCubeSide r, final RubikCubeSide u) {
        this.leftSide = l;
        this.rightSide = r;
        this.upDownSide = u;
    }
    /**
     * Gets the left side of the cube as seen in an anticlockwise rotation from
     * the top.
     * @return Left side of the cube.
     */
    public final RubikCubeSide getLeftSide() {
        return this.leftSide;
    }
    /**
     * Gets the right side of the cube as seen in an anticlockwise rotation
     * from the top.
     * @return Right side of the cube.
     */
    public final RubikCubeSide getRightSide() {
        return this.rightSide;
    }
    /**
     * Gets the up/down side of the cube as seen in an anticlockwise rotation
     * from the top.
     * @return Up/down side of the cube.
     */
    public final RubikCubeSide getUpDownSide() {
        return this.upDownSide;
    }
}
