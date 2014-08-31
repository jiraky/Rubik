package it.univr.rubikcube.model;

/**
 * A row of the Rubik cube was rotated.
 * @author Alessandro Menti
 */
public class RubikCubeModelRowRotated extends RubikCubeModelEvent {
    /**
     * Index of the rotated row.
     */
    private int index;
    /**
     * Rotation direction.
     */
    private RowRotation direction;
    /**
     * Creates a new instance of the RubikCubeModelRowRotated event.
     * @param i Index of the rotated row.
     * @param dir Rotation direction.
     */
    public RubikCubeModelRowRotated(final int i, final RowRotation dir) {
        this.index = i;
        this.direction = dir;
    }
    /**
     * Returns the index of the rotated row.
     * @return Index of the rotated row.
     */
    public final int getIndex() {
        return this.index;
    }
    /**
     * Returns the direction the row was rotated.
     * @return Rotation direction.
     */
    public final RowRotation getDirection() {
        return this.direction;
    }
}
