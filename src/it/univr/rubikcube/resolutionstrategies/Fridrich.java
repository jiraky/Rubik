package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;
import java.util.List;

/**
 * Fridrich method for 3x3 cubes.
 * @author Alessandro Menti
 */
public class Fridrich extends ResolutionStrategy {
    /**
     * Creates a new instance of the Fridrich method.
     * @param m Cube model on which the method is applied.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three.
     */
    public Fridrich(final RubikCubeModel m)
            throws IllegalArgumentException {
        super(m);
        if (m.getDimension() != 3) {
            throw new IllegalArgumentException("The dimension of the cube must"
                                               + " be equal to three");
        }
        // FIXME Check European configuration (central faces)
    }
    /**
     * Gets a list of next moves to be performed.
     * @return List of next moves.
     */
    @Override
    public final List<Move> getNextMoves() {
        final RubikCubeModel m = this.getModel();
        // Identify the phase the cube is in and generate a complete list of
        // next moves.
        // Phase 1 (pre-cross): the central faces on the top, front and right
        // cube faces must be respectively yellow, red and green.
        return null;
    }
    /**
     * Returns the name of the resolution strategy.
     * @return <tt>Fridrich</tt>
     */
    @Override
    public final String toString() {
        return "Fridrich";
    }
    /**
     * Returns a textual description of the resolution strategy.
     * @return <tt>The Fridrich method solves a 3x3 Rubik cube in European
     * configuration.</tt>
     */
    @Override
    public final String getDescription() {
        return "The Fridrich method solves a 3x3 Rubik cube in European"
               + " configuration.";
    }
}
