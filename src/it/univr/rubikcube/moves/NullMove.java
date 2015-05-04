package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.ThreeDimensionalMove;

/**
 * Null move.
 * @author Alessandro Menti
 */
public class NullMove extends ThreeDimensionalMove {
    /**
     * Creates a new null move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public NullMove(final RubikCubeModel m)
            throws NullPointerException, IllegalArgumentException {
        super(m);
    }
    /**
     * Creates a new null move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public NullMove(final RubikCubeModel m, final boolean reversed)
            throws NullPointerException, IllegalArgumentException {
        super(m, reversed);
    }
    /**
     * Returns a string describing this move.
     * @return <tt>Null</tt>
     */
    @Override
    public final String toString() {
        if (this.isReversed()) {
            return "Null";
        }
        return "Null";
    }
    /**
     * Performs the move.
     */
    @Override
    public final void performInternal() {
    }
    /**
     * Performs the reversed move.
     */
    @Override
    public final void reverseInternal() {
    }
}
