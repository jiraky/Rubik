package it.univr.rubikcube.model;

/**
 * The dimension of the Rubik cube has changed.
 * @author Alessandro Menti
 */
public class RubikCubeModelDimensionChanged extends RubikCubeModelEvent {
    /**
     * The new dimension.
     */
    private int dimension;
    /**
     * Creates a new instance of the RubikCubeModelDimensionChanged event.
     * @param dim New cube dimension.
     */
    public RubikCubeModelDimensionChanged(final int dim) {
        this.dimension = dim;
    }
    /**
     * Returns the new cube dimension.
     * @return The new cube dimension.
     */
    public final int getDimension() {
        return this.dimension;
    }
}
