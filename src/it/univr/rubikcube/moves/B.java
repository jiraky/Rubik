package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.LateralColumnRotation;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.ThreeDimensionalMove;

/**
 * Quarter turn of the back face in the clockwise direction.
 * @author Alessandro Menti
 */
public class B extends ThreeDimensionalMove {
    /**
     * Creates a new "B" move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public B(final RubikCubeModel m)
            throws NullPointerException, IllegalArgumentException {
        super(m);
    }
    /**
     * Creates a new "B" move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public B(final RubikCubeModel m, final boolean reversed)
            throws NullPointerException, IllegalArgumentException {
        super(m, reversed);
    }
    /**
     * Returns a string describing this move.
     * @return <tt>B'</tt> if this move is reversed, <tt>B</tt> otherwise.
     */
    @Override
    public final String toString() {
        if (this.isReversed()) {
            return "B'";
        }
        return "B";
    }
    /**
     * Performs the move.
     */
    @Override
    public final void performInternal() {
        this.getModel().rotateLateralColumn(2, LateralColumnRotation.LEFT);
    }
    /**
     * Performs the reversed move.
     */
    @Override
    public final void reverseInternal() {
        this.getModel().rotateLateralColumn(2, LateralColumnRotation.RIGHT);
    }
}
