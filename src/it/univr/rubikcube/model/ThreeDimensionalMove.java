package it.univr.rubikcube.model;

/**
 * Move acting on a cube of dimension three.
 * @author Alessandro Menti
 */
public abstract class ThreeDimensionalMove extends Move {
    /**
     * Creates a new move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public ThreeDimensionalMove(final RubikCubeModel m) {
        this(m, false);
    }
    /**
     * Creates a new move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public ThreeDimensionalMove(final RubikCubeModel m, final boolean reversed) {
        super(m, reversed);
        if (m.getDimension() != 3) {
            throw new IllegalArgumentException("The dimension of the cube must"
                                               + " be equal to three");
        }
    }
}
