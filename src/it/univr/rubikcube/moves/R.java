package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.ColumnRotation;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.ThreeDimensionalMove;

/**
 * Quarter turn of the right face in the clockwise direction.
 * @author Alessandro Menti
 */
public class R extends ThreeDimensionalMove {
    /**
     * Creates a new "R" move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public R(final RubikCubeModel m)
            throws NullPointerException, IllegalArgumentException {
        super(m);
    }
    /**
     * Creates a new "R" move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public R(final RubikCubeModel m, final boolean reversed)
            throws NullPointerException, IllegalArgumentException {
        super(m, reversed);
    }
    /**
     * Returns a string describing this move.
     * @return <tt>R'</tt> if this move is reversed, <tt>R</tt> otherwise.
     */
    @Override
    public final String toString() {
        if (this.isReversed()) {
            return "R'";
        }
        return "R";
    }
    /**
     * Performs the move.
     */
    @Override
    public final void performInternal() {
        this.getModel().rotateColumn(2, ColumnRotation.TOP);
    }
    /**
     * Performs the reversed move.
     */
    @Override
    public final void reverseInternal() {
        this.getModel().rotateColumn(2, ColumnRotation.BOTTOM);
    }
}
