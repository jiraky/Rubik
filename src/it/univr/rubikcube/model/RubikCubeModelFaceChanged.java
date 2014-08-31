package it.univr.rubikcube.model;

/**
 * A single face of the Rubik cube had its color changed.
 * @author Alessandro Menti
 */
public class RubikCubeModelFaceChanged extends RubikCubeModelEvent {
    /**
     * X coordinate of the changed face. 0 is on the left.
     */
    private int xCoord;
    /**
     * Y coordinate of the changed face. 0 is at the top.
     */
    private int yCoord;
    /**
     * Side of the changed face.
     */
    private RubikCubeSide faceSide;
    /**
     * Creates a new instance of the RubikCubeModelFaceChanged event.
     * @param x X coordinate of the changed face.
     * @param y Y coordinate of the changed face.
     * @param side Side of the changed face.
     */
    public RubikCubeModelFaceChanged(final int x, final int y,
                                     final RubikCubeSide side) {
        this.xCoord = x;
        this.yCoord = y;
        this.faceSide = side;
    }
    /**
     * Gets the X coordinate of the changed face.
     * @return X coordinate of the changed face. 0 is on the left.
     */
    public final int getX() {
        return this.xCoord;
    }
    /**
     * Gets the Y coordinate of the changed face.
     * @return Y coordinate of the changed face. 0 is at the top.
     */
    public final int getY() {
        return this.yCoord;
    }
    /**
     * Gets the side of the changed face.
     * @return Side of the changed face.
     */
    public final RubikCubeSide getSide() {
        return this.faceSide;
    }
}
