package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;
import java.util.List;
import java.util.concurrent.TimeoutException;

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
     * Gets a list of next moves to be performed to get to the next phase of
     * the algorithm.
     * @return List of next moves.
     * @throws NoSolutionException Thrown in case the resolution strategy fails
     * to find a solution.
     * @throws TimeoutException Thrown in case the resolution strategy fails to
     * find a solution before the specified timeout (if any) or was interrupted.
     */
    public abstract List<Move> getNextMoves() throws NoSolutionException,
        TimeoutException;
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
