package it.univr.rubikcube.resolutionstrategies;

import java.util.List;
import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;

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
    public Fridrich(final RubikCubeModel m) {
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
        RubikCubeModel m = this.getModel();
        // Identify the phase the cube is in and generate a complete list of
        // next moves.
        // Phase 1 (pre-cross): the central faces on the top, front and right
        // cube faces must be respectively yellow, red and green.
        return null;
    }
    
    @Override
    public String toString() {
        return "Fridrich";
    }
}
