package it.univr.rubikcube.model;

/**
 * Move needed to take the cube from one configuration to another.
 * @author Alessandro Menti
 */
public abstract class Move {
    /**
     * Rubik cube model.
     */
    private RubikCubeModel cubeModel;
    /**
     * Specifies if the move is a reversed one.
     */
    private boolean isReversed;
    /**
     * Creates a new move acting on the specified model.
     * @param m Rubik cube model.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public Move(final RubikCubeModel m) throws NullPointerException {
        this(m, false);
    }
    /**
     * Creates a new move acting on the specified model.
     * @param m Rubik cube model.
     * @param reversed Specifies if the move is a reversed one (prime '
     * notation).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public Move(final RubikCubeModel m, final boolean reversed)
            throws NullPointerException {
        if (m == null) {
            throw new NullPointerException();
        }
        this.cubeModel = m;
        this.isReversed = reversed;
    }
    /**
     * Gets the Rubik cube model associated with this move.
     * @return The cube model associated with this move.
     */
    public final RubikCubeModel getModel() {
        return this.cubeModel;
    }
    /**
     * Checks if this move is a reversed one.
     * @return <tt>true</tt> if and only if this move is a reversed one.
     */
    public final boolean isReversed() {
        return this.isReversed;
    }
    /**
     * Returns a string representing the move.
     * @return String representing the move.
     */
    public abstract String toString();
    /**
     * Performs the move, taking into account the "reversed" status.
     */
    public final void perform() {
        if (!this.isReversed) {
            performInternal();
        } else {
            reverseInternal();
        }
    }
    /**
     * Reverses the move, taking into account the "reversed" status.
     */
    public final void reverse() {
        if (this.isReversed) {
            performInternal();
        } else {
            reverseInternal();
        }
    }
    /**
     * Performs the move.
     */
    protected abstract void performInternal();
    /**
     * Reverses the move.
     */
    protected abstract void reverseInternal();
}
