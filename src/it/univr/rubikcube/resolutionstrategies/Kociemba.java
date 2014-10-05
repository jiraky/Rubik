package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;
import java.util.List;

/**
 * Kociemba two-phase algorithm for 3x3 cubes.
 * @author Alessandro Menti
 */
public class Kociemba extends ResolutionStrategy {
    /**
     * Maximum allowed maneuver length.
     */
    private int maxDepth;
    /**
     * Search timeout in seconds.
     */
    private int timeout;
    /**
     * Creates a new instance of the Kociemba method.
     * @param m Rubik cube model.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three, if the cube has not nine faces per color, if there
     * isn't a single facelet per color.
     */
    public Kociemba(final RubikCubeModel m)
            throws IllegalArgumentException {
        super(m);
        if (m.getDimension() != 3) {
            throw new IllegalArgumentException("The dimension of the cube must"
                                               + " be equal to three");
        }
        if (!RubikCubeModel.isWithSaneColors(m)) {
            throw new IllegalArgumentException("The cube has not sane colors");
        }
    }
    /**
     * Gets a list of next moves.
     * @return List of next moves.
     */
    @Override
    public final List<Move> getNextMoves() {
        /*
         * ax = axis of move - values (0-5) correspond to URFDLB
         * po = power of move - values 1-3
         * flip, twist, slice are in phase 1
         * parity, URFtoDLF, FRtoBR, URtoUL, UBtoDF, URtoDF are in phase 2
         * minDistPhase{1,2} are IDA* goal estimations
         * facelets is the cube definition string
         */
        // FIXME
        /*do {
            do {
                if ((depthPhase1 - n > minDistPhase1[n + 1]) && !busy) {

                    if (ax[n] == 0 || ax[n] == 3)// Initialize next move
                        ax[++n] = 1;
                    else
                        ax[++n] = 0;
                    po[n] = 1;
                } else if (++po[n] > 3) {
                    do {// increment axis
                        if (++ax[n] > 5) {

                            if (System.currentTimeMillis() - tStart > timeOut << 10)
                                return "Error 8";

                            if (n == 0) {
                                if (depthPhase1 >= MAX_DEPTH)
                                    return "Error 7";
                                else {
                                    depthPhase1++;
                                    ax[n] = 0;
                                    po[n] = 1;
                                    busy = false;
                                    break;
                                }
                            } else {
                                n--;
                                busy = true;
                                break;
                            }

                        } else {
                            po[n] = 1;
                            busy = false;
                        }
                    } while (n != 0 && (ax[n - 1] == ax[n] || ax[n - 1] - 3 == ax[n]));
                } else
                    busy = false;
            } while (busy);

            // +++++++++++++ compute new coordinates and new minDistPhase1 ++++++++++
            // if minDistPhase1 =0, the H subgroup is reached
            mv = 3 * ax[n] + po[n] - 1;
            flip[n + 1] = CoordCube.flipMove[flip[n]][mv];
            twist[n + 1] = CoordCube.twistMove[twist[n]][mv];
            slice[n + 1] = CoordCube.FRtoBR_Move[slice[n] * 24][mv] / 24;
            minDistPhase1[n + 1] = Math.max(CoordCube.getPruning(CoordCube.Slice_Flip_Prun, CoordCube.N_SLICE1 * flip[n + 1]
                    + slice[n + 1]), CoordCube.getPruning(CoordCube.Slice_Twist_Prun, CoordCube.N_SLICE1 * twist[n + 1]
                    + slice[n + 1]));
            // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            if (minDistPhase1[n + 1] == 0 && n >= depthPhase1 - 5) {
                minDistPhase1[n + 1] = 10;// instead of 10 any value >5 is possible
                if (n == depthPhase1 - 1 && (s = totalDepth(depthPhase1, MAX_DEPTH)) >= 0) {
                    if (s == depthPhase1
                            || (ax[depthPhase1 - 1] != ax[depthPhase1] && ax[depthPhase1 - 1] != ax[depthPhase1] + 3))
                        return useSeparator ? solutionToString(s, depthPhase1) : solutionToString(s);
                }

            }
        } while (true);*/
        // FIXME
        return null;
    }
    /**
     * Gets the name of this method.
     * @return <tt>Kociemba</tt>
     */
    @Override
    public final String toString() {
        return "Kociemba";
    }
    /**
     * Gets a textual description of this method.
     * @return <tt>The Kociemba two-phase algorithm searches for solutions in a
     * game state tree using two groups of moves.</tt>
     */
    @Override
    public final String getDescription() {
        return "The Kociemba two-phase algorithm searches for solutions in a"
               + " game state tree using two groups of moves.";
    }
    /**
     * Gets the maximal allowed maneuver length (depth of the search tree).
     * @return The maximal allowed maneuver length.
     */
    public final int getMaxDepth() {
        return this.maxDepth;
    }
    /**
     * Sets the maximal allowed maneuver length (depth of the search tree).
     * @param d The new maximal allowed maneuver length. The optimal value for
     * this parameter, as per Kociemba's findings, is 21; greater values
     * increase the amount of needed memory, but allow the program to search
     * for solutions more effectively; smaller values are not recommended as
     * they require substantially more time for the cube to be solved (or may
     * fail to find a solution).
     * @throws IllegalArgumentException Thrown if <tt>d</tt> is less or equal
     * to zero.
     */
    public final void setMaxDepth(final int d)
            throws IllegalArgumentException {
        if (d <= 0) {
            throw new IllegalArgumentException("The depth must be greater than"
                                               + " zero");
        }
        this.maxDepth = d;
    }
    /**
     * Gets the search timeout.
     * @return The search timeout in seconds.
     */
    public final int getTimeout() {
        return this.timeout;
    }
    /**
     * Sets the search timeout.
     * @param t The new search timeout in seconds.
     * @throws IllegalArgumentException Thrown if <tt>t</tt> is less or equal
     * to zero.
     */
    public final void setTimeout(final int t)
            throws IllegalArgumentException {
        if (t <= 0) {
            throw new IllegalArgumentException("The timeout must be greater"
                                               + " than zero");
        }
        this.timeout = t;
    }
}
