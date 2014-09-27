package it.univr.rubikcube.model;

/**
 * A "lateral" column of the Rubik cube was rotated.
 * @author Alessandro Menti
 */
public class RubikCubeModelLateralColumnRotated extends RubikCubeModelEvent {
    /**
     * Index of the rotated column.
     */
    private int index;
    /**
     * Rotation direction.
     */
    private LateralColumnRotation direction;
    /**
     * Creates a new instance of the RubikCubeModelColumnRotated event.
     * @param i Index of the rotated column.
     * @param dir Rotation direction.
     */
    public RubikCubeModelLateralColumnRotated(final int i,
                                              final LateralColumnRotation dir) {
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
    public final LateralColumnRotation getDirection() {
        return this.direction;
    }
}
