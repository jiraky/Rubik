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
    URF(RubikCubeSide.FRONT, RubikCubeSide.RIGHT, RubikCubeSide.UP, 2, 2, 2),
    /**
     * Corner between the up, front and left faces.
     */
    UFL(RubikCubeSide.LEFT, RubikCubeSide.FRONT, RubikCubeSide.UP, 2, 2, 0),
    /**
     * Corner between the up, left and back faces.
     */
    ULB(RubikCubeSide.BACK, RubikCubeSide.LEFT, RubikCubeSide.UP, 0, 2, 0),
    /**
     * Corner between the up, back and right faces.
     */
    UBR(RubikCubeSide.RIGHT, RubikCubeSide.BACK, RubikCubeSide.UP, 0, 2, 2),
    /**
     * Corner between the down, front and right faces.
     */
    DFR(RubikCubeSide.FRONT, RubikCubeSide.RIGHT, RubikCubeSide.DOWN, 2, 0, 2),
    /**
     * Corner between the down, left and front faces.
     */
    DLF(RubikCubeSide.LEFT, RubikCubeSide.FRONT, RubikCubeSide.DOWN, 2, 0, 2),
    /**
     * Corner between the down, back and left faces.
     */
    DBL(RubikCubeSide.BACK, RubikCubeSide.LEFT, RubikCubeSide.DOWN, 0, 0, 0),
    /**
     * Corner between the down, right and back faces.
     */
    DRB(RubikCubeSide.RIGHT, RubikCubeSide.BACK, RubikCubeSide.DOWN, 0, 0, 2);
    /**
     * Left side of the corner (as seen in an anticlockwise rotation from the
     * top).
     */
    private RubikCubeSide firstSide;
    /**
     * Right side of the corner (as seen in an anticlockwise rotation from the
     * top).
     */
    private RubikCubeSide secondSide;
    /**
     * Up/down side of the corner.
     */
    private RubikCubeSide thirdSide;
    /**
     * X coordinate in the "X-Y-Z at bottom" system.
     */
    private int xCoord;
    /**
     * Y coordinate in the "X-Y-Z at bottom" system.
     */
    private int yCoord;
    /**
     * Z coordinate in the "X-Y-Z at bottom" system.
     */
    private int zCoord;
    /**
     * Creates a new Rubik cube corner.
     * @param l Left side.
     * @param r Right side.
     * @param u Up/down side.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     */
    RubikCubeCorner(final RubikCubeSide l, final RubikCubeSide r, final RubikCubeSide u,
            final int x, final int y, final int z) {
        this.firstSide = l;
        this.secondSide = r;
        this.thirdSide = u;
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }
    /**
     * Gets the left side of the cube as seen in an anticlockwise rotation from
     * the top.
     * @return Left side of the cube.
     */
    public final RubikCubeSide getLeftSide() {
        return this.firstSide;
    }
    /**
     * Gets the right side of the cube as seen in an anticlockwise rotation
     * from the top.
     * @return Right side of the cube.
     */
    public final RubikCubeSide getRightSide() {
        return this.secondSide;
    }
    /**
     * Gets the up/down side of the cube as seen in an anticlockwise rotation
     * from the top.
     * @return Up/down side of the cube.
     */
    public final RubikCubeSide getUpDownSide() {
        return this.thirdSide;
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
     * Gets the face of the corner on an axis.
     * @param a Axis to be considered.
     * @return Face of the corner on the specified axis.
     */
    public final RubikCubeSide getFaceOnAxis(final RubikCubeModelAxis a) {
        if (a == RubikCubeModelAxis.X) {
            if (this.firstSide == RubikCubeSide.LEFT || this.firstSide == RubikCubeSide.RIGHT) {
                return this.firstSide;
            } else if (this.secondSide == RubikCubeSide.LEFT || this.secondSide == RubikCubeSide.RIGHT) {
                return this.secondSide;
            } else {
                return this.thirdSide;
            }
        } else if (a == RubikCubeModelAxis.Y) {
            if (this.firstSide == RubikCubeSide.UP || this.firstSide == RubikCubeSide.DOWN) {
                return this.firstSide;
            } else if (this.secondSide == RubikCubeSide.UP || this.secondSide == RubikCubeSide.DOWN) {
                return this.secondSide;
            } else {
                return this.thirdSide;
            }
        } else if (a == RubikCubeModelAxis.Z) {
            if (this.firstSide == RubikCubeSide.FRONT || this.firstSide == RubikCubeSide.BACK) {
                return this.firstSide;
            } else if (this.secondSide == RubikCubeSide.FRONT || this.secondSide == RubikCubeSide.BACK) {
                return this.secondSide;
            } else {
                return this.thirdSide;
            }
        } else {
            return null;
        }
    }
    /**
     * Gets the standard corner given three colors.
     * @param a First color.
     * @param b Second color.
     * @param c Third color.
     * @return Standard corner, or <tt>null</tt> if the standard corner does
     * not exist.
     */
    public static final RubikCubeCorner getStandardCorner(final RubikCubeFaceColor a,
            final RubikCubeFaceColor b, final RubikCubeFaceColor c) {
        if (a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()) {
            return RubikCubeCorner.DBL;
        } else if (a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()) {
            return RubikCubeCorner.DFR;
        } else if (a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()) {
            return RubikCubeCorner.DLF;
        } else if (a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.DOWN.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.DOWN.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.DOWN.getStandardColor()) {
            return RubikCubeCorner.DRB;
        } else if (a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()) {
            return RubikCubeCorner.UBR;
        } else if (a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()) {
            return RubikCubeCorner.UFL;
        } else if (a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.BACK.getStandardColor()
                || a == RubikCubeSide.LEFT.getStandardColor() && b == RubikCubeSide.BACK.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.LEFT.getStandardColor()
                || a == RubikCubeSide.BACK.getStandardColor() && b == RubikCubeSide.LEFT.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()) {
            return RubikCubeCorner.ULB;
        } else if (a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.UP.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.FRONT.getStandardColor()
                || a == RubikCubeSide.RIGHT.getStandardColor() && b == RubikCubeSide.FRONT.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.UP.getStandardColor() && c == RubikCubeSide.RIGHT.getStandardColor()
                || a == RubikCubeSide.FRONT.getStandardColor() && b == RubikCubeSide.RIGHT.getStandardColor() && c == RubikCubeSide.UP.getStandardColor()) {
            return RubikCubeCorner.URF;
        } else {
            return null;
        }
    }
}
