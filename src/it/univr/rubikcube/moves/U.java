package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.RowRotation;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.ThreeDimensionalMove;

/**
 * Quarter turn of the upper face in the clockwise direction.
 * @author Alessandro Menti
 */
public class U extends ThreeDimensionalMove {
    /**
     * Creates a new "U" move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public U(final RubikCubeModel m) {
        super(m);
    }
    /**
     * Creates a new "U" move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public U(final RubikCubeModel m, final boolean reversed) {
        super(m, reversed);
    }
    /**
     * Returns a string describing this move.
     * @return <tt>U'</tt> if this move is reversed, <tt>U</tt> otherwise.
     */
    @Override
    public final String toText() {
        if (this.isReversed()) {
            return "U'";
        }
        return "U";
    }
    /**
     * Performs the move.
     */
    @Override
    public final void performInternal() {
        this.getModel().rotateRow(0, RowRotation.CLOCKWISE);
    }
    /**
     * Performs the reversed move.
     */
    @Override
    public final void reverseInternal() {
        this.getModel().rotateRow(0, RowRotation.ANTICLOCKWISE);
    }
}
