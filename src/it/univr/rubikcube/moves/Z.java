package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.CubeRotation;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.ThreeDimensionalMove;

/**
 * Clockwise rotation of the cube around the Z axis.
 * @author Alessandro Menti
 */
public class Z extends ThreeDimensionalMove {
    /**
     * Creates a new "Z" move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public Z(final RubikCubeModel m)
            throws NullPointerException, IllegalArgumentException {
        super(m);
    }
    /**
     * Creates a new "Z" move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public Z(final RubikCubeModel m, final boolean reversed)
            throws NullPointerException, IllegalArgumentException {
        super(m, reversed);
    }
    /**
     * Returns a string describing this move.
     * @return <tt>Z'</tt> if this move is reversed, <tt>Z</tt> otherwise.
     */
    @Override
    public final String toString() {
        if (this.isReversed()) {
            return "Z'";
        }
        return "Z";
    }
    /**
     * Performs the move.
     */
    @Override
    public final void performInternal() {
        this.getModel().rotateCube(CubeRotation.CLOCKWISE_FROM_FRONT);
    }
    /**
     * Performs the reversed move.
     */
    @Override
    public final void reverseInternal() {
        this.getModel().rotateCube(CubeRotation.ANTICLOCKWISE_FROM_FRONT);
    }
}
