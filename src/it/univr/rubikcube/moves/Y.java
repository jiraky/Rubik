package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.CubeRotation;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.ThreeDimensionalMove;

/**
 * Clockwise rotation of the cube around the Y axis.
 * @author Alessandro Menti
 */
public class Y extends ThreeDimensionalMove {
    /**
     * Creates a new "Y" move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public Y(final RubikCubeModel m)
            throws NullPointerException, IllegalArgumentException {
        super(m);
    }
    /**
     * Creates a new "Y" move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public Y(final RubikCubeModel m, final boolean reversed)
            throws NullPointerException, IllegalArgumentException {
        super(m, reversed);
    }
    /**
     * Returns a string describing this move.
     * @return <tt>Y'</tt> if this move is reversed, <tt>Y</tt> otherwise.
     */
    @Override
    public final String toString() {
        if (this.isReversed()) {
            return "Y'";
        }
        return "Y";
    }
    /**
     * Performs the move.
     */
    @Override
    public final void performInternal() {
        this.getModel().rotateCube(CubeRotation.CLOCKWISE);
    }
    /**
     * Performs the reversed move.
     */
    @Override
    public final void reverseInternal() {
        this.getModel().rotateCube(CubeRotation.ANTICLOCKWISE);
    }
}
