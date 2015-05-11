package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.moves.B;
import it.univr.rubikcube.moves.D;
import it.univr.rubikcube.moves.F;
import it.univr.rubikcube.moves.L;
import it.univr.rubikcube.moves.R;
import it.univr.rubikcube.moves.U;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for the Kociemba resolution strategy.
 * @author Alessandro Menti
 */
public class Kociemba {
    /**
     * Empty default constructor.
     */
    public Kociemba() {
        // Empty, nothing to do
    }
    /**
     * Tests the Kociemba resolution strategy on a random cube.
     * @throws TimeoutException Thrown if a timeout occurs.
     * @throws NoSolutionException Thrown if there is no solution.
     */
    @Test
    public final void testOnRandomCube() throws NoSolutionException, TimeoutException {
        final RubikCubeModel m = new RubikCubeModel(3);
        final Map<Character, Character[]> successors = new HashMap<Character, Character[]>();

        successors.put('L', new Character[]{'F', 'B', 'U', 'D'});
        successors.put('R', new Character[]{'F', 'B', 'U', 'D'});
        successors.put('U', new Character[]{'F', 'B', 'L', 'R'});
        successors.put('D', new Character[]{'F', 'B', 'L', 'R'});
        successors.put('F', new Character[]{'L', 'R', 'U', 'D'});
        successors.put('B', new Character[]{'L', 'R', 'U', 'D'});

        Character[] words = {'L', 'R', 'U', 'D', 'F', 'B'};

        final SecureRandom secRand = new SecureRandom();
        final int numMoves = secRand.nextInt(100);
        char prevMove = words[secRand.nextInt(words.length)];
        
        this.performMove(m, prevMove, secRand.nextBoolean());

        for (int i = 1; i < numMoves; ++i) {
            words = successors.get(prevMove);
            prevMove = words[secRand.nextInt(words.length)];
            this.performMove(m, prevMove, secRand.nextBoolean());
        }

        final KociembaLib kocStrat = new KociembaLib(m);
        final List<Move> resolutionMoves = kocStrat.getNextMoves();
        for (Move i: resolutionMoves) {
            i.perform();
        }
        
        Assert.assertTrue(RubikCubeModel.isSolved(m));
    }
    /**
     * Performs a move on a cube given the identification character.
     * @param m Model to act on.
     * @param move Move to perform.
     * @param inverted Specifies if the move should be inverted.
     */
    private void performMove(final RubikCubeModel m, final char move, final boolean inverted) {
        switch (move) {
            case 'L':
                new L(m, inverted).perform();
                break;
            case 'R':
                new R(m, inverted).perform();
                break;
            case 'U':
                new U(m, inverted).perform();
                break;
            case 'D':
                new D(m, inverted).perform();
                break;
            case 'F':
                new F(m, inverted).perform();
                break;
            case 'B':
                new B(m, inverted).perform();
                break;
            default:
        }
    }
}
