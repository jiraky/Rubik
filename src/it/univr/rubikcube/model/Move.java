package it.univr.rubikcube.model;

import it.univr.rubikcube.moves.B;
import it.univr.rubikcube.moves.D;
import it.univr.rubikcube.moves.E;
import it.univr.rubikcube.moves.F;
import it.univr.rubikcube.moves.L;
import it.univr.rubikcube.moves.M;
import it.univr.rubikcube.moves.R;
import it.univr.rubikcube.moves.S;
import it.univr.rubikcube.moves.U;
import it.univr.rubikcube.moves.X;
import it.univr.rubikcube.moves.Y;
import it.univr.rubikcube.moves.Z;

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
    @Override
    public abstract String toString();
    /**
     * Performs the move, taking into account the "reversed" status.
     */
    public final void perform() {
        if (!this.isReversed) {
            this.performInternal();
        } else {
            this.reverseInternal();
        }
    }
    /**
     * Reverses the move, taking into account the "reversed" status.
     */
    public final void reverse() {
        if (this.isReversed) {
            this.performInternal();
        } else {
            this.reverseInternal();
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
    
    public final void setCube(RubikCubeModel m) {
        this.cubeModel = m;
    }
    
    
    public static Move convertFromString(RubikCubeModel cube, String move) {
        switch (move) {
            case "L": return new L(cube);
            case "R": return new R(cube);
            case "U": return new U(cube);
            case "D": return new D(cube);
            case "F": return new F(cube);
            case "B": return new B(cube);
            case "M": return new M(cube);
            case "S": return new S(cube);
            case "E": return new E(cube);
            case "X": return new X(cube);
            case "Y": return new Y(cube);
            case "Z": return new Z(cube);
                
            case "L'": return new L(cube, true);
            case "R'": return new R(cube, true);
            case "U'": return new U(cube, true);
            case "D'": return new D(cube, true);
            case "F'": return new F(cube, true);
            case "B'": return new B(cube, true);
            case "M'": return new M(cube, true);
            case "S'": return new S(cube, true);
            case "E'": return new E(cube, true);
            case "X'": return new X(cube, true);
            case "Y'": return new Y(cube, true);
            case "Z'": return new Z(null, true);
            default: throw new UnsupportedOperationException("Cannot cast");
        }
    }
}
