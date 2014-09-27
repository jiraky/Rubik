package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;
import java.util.List;

/**
 * Interface for Rubik cube resolution strategies.
 * @author Alessandro Menti
 */
public abstract class ResolutionStrategy {
    /**
     * Rubik cube model.
     */
    private RubikCubeModel cubeModel;
    /**
     * Creates a new resolution strategy that acts on the specified model.
     * @param m Model to be acted upon.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public ResolutionStrategy(final RubikCubeModel m)
            throws NullPointerException {
        if (m == null) {
            throw new NullPointerException();
        }
        this.cubeModel = m;
    }
    /**
     * Gets the cube model associated with this specific strategy.
     * @return Cube model on which this strategy acts.
     */
    public final RubikCubeModel getModel() {
        return this.cubeModel;
    }
    /**
     * Gets a list of next moves to be performed.
     * @return List of next moves.
     */
    public abstract List<Move> getNextMoves();
    /**
     * Returns the name of the resolution strategy.
     * @return Name of the resolution strategy.
     */
    @Override
    public abstract String toString();
    /**
     * Returns a textual description of the resolution strategy.
     * @return Description of the resolution strategy.
     */
    public abstract String getDescription();
}
