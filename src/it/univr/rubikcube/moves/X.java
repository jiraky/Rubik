package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.CubeRotation;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.ThreeDimensionalMove;

/**
 * Clockwise rotation of the cube around the X axis.
 * @author Alessandro Menti
 */
public class X extends ThreeDimensionalMove {
    /**
     * Creates a new "X" move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public X(final RubikCubeModel m)
            throws NullPointerException, IllegalArgumentException {
        super(m);
    }
    /**
     * Creates a new "X" move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public X(final RubikCubeModel m, final boolean reversed)
            throws NullPointerException, IllegalArgumentException {
        super(m, reversed);
    }
    /**
     * Returns a string describing this move.
     * @return <tt>X'</tt> if this move is reversed, <tt>X</tt> otherwise.
     */
    @Override
    public final String toString() {
        if (this.isReversed()) {
            return "X'";
        }
        return "X";
    }
    /**
     * Performs the move.
     */
    @Override
    public final void performInternal() {
        this.getModel().rotateCube(CubeRotation.UPWISE);
    }
    /**
     * Performs the reversed move.
     */
    @Override
    public final void reverseInternal() {
        this.getModel().rotateCube(CubeRotation.DOWNWISE);
    }
}
