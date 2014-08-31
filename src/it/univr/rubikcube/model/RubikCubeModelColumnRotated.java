package it.univr.rubikcube.model;

/**
 * A column of the Rubik cube was rotated.
 * @author Alessandro Menti
 */
public class RubikCubeModelColumnRotated extends RubikCubeModelEvent {
    /**
     * Index of the rotated column.
     */
    private int index;
    /**
     * Rotation direction.
     */
    private ColumnRotation direction;
    /**
     * Creates a new instance of the RubikCubeModelColumnRotated event.
     * @param i Index of the rotated column.
     * @param dir Rotation direction.
     */
    public RubikCubeModelColumnRotated(final int i, final ColumnRotation dir) {
        this.index = i;
        this.direction = dir;
    }
    /**
     * Returns the index of the rotated column.
     * @return Index of the rotated column.
     */
    public final int getIndex() {
        return this.index;
    }
    /**
     * Returns the direction the column was rotated.
     * @return Rotation direction.
     */
    public final ColumnRotation getDirection() {
        return this.direction;
    }
}
