package it.univr.rubikcube.model;

/**
 * The Rubik cube was rotated.
 * @author Alessandro Menti
 */
public class RubikCubeModelCubeRotated extends RubikCubeModelEvent {
    /**
     * Rotation direction.
     */
    private CubeRotation direction;
    /**
     * Creates a new instance of the RubikCubeModelCubeRotated event.
     * @param dir Rotation direction.
     */
    public RubikCubeModelCubeRotated(final CubeRotation dir) {
        this.direction = dir;
    }
    /**
     * Returns the direction the cube was rotated.
     * @return Rotation direction.
     */
    public final CubeRotation getDirection() {
        return this.direction;
    }
}
