package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.moves.B;
import it.univr.rubikcube.moves.D;
import it.univr.rubikcube.moves.F;
import it.univr.rubikcube.moves.L;
import it.univr.rubikcube.moves.R;
import it.univr.rubikcube.moves.U;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Kociemba two-phase algorithm for 3x3 cubes.
 * @author Herbert Kociemba
 * @author Alessandro Menti
 */
public class Kociemba extends ResolutionStrategy {
    /**
     * Maximum array search depth.
     */
    private static int arraySearchDepth = 31;
    /**
     * Maximum allowed maneuver length.
     */
    private int maxDepth;
    /**
     * Search timeout in seconds.
     */
    private int timeout;
    /**
     * The axis of the move.
     */
    private int[] ax;
    /**
     * The power of the move.
     */
    private int[] po;
    /**
     * Phase 1 flip.
     */
    private int[] flip;
    /**
     * Phase 1 twist.
     */
    private int[] twist;
    /**
     * Phase 1 slice.
     */
    private int[] slice;
    /**
     * Phase 2 parity.
     */
    private int[] parity;
    /**
     * Phase 2 upper right front to down left front.
     */
    private int[] URFtoDLF;
    /**
     * Phase 2 front right to back right.
     */
    private int[] FRtoBR;
    /**
     * Phase 2 up right to up left.
     */
    private int[] URtoUL;
    /**
     * Phase 2 up back to down front.
     */
    private int[] UBtoDF;
    /**
     * Phase 2 up right to down front.
     */
    private int[] URtoDF;
    /**
     * IDA* distance estimations for Phase 1.
     */
    private int[] minDistPhase1;
    /**
     * IDA* distance estimations for Phase 2.
     */
    private int[] minDistPhase2;
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
     * @throws NoSolutionException Thrown in case the algorithm does not find a
     * solution.
     * @throws TimeoutException Thrown in case the algorithm fails to find a
     * solution before the timeout.
     */
    @Override
    public final List<Move> getNextMoves() throws NoSolutionException,
        TimeoutException {
        /*
         * ax = axis of move - values (0-5) correspond to URFDLB
         * po = power of move - values 1-3
         * flip, twist, slice are in phase 1
         * parity, URFtoDLF, FRtoBR, URtoUL, UBtoDF, URtoDF are in phase 2
         * minDistPhase{1,2} are IDA* goal estimations
         */
        this.ax = new int[arraySearchDepth];
        this.po = new int[arraySearchDepth];
        this.flip = new int[arraySearchDepth];
        this.twist = new int[arraySearchDepth];
        this.slice = new int[arraySearchDepth];
        this.parity = new int[arraySearchDepth];
        this.URFtoDLF = new int[arraySearchDepth];
        this.FRtoBR = new int[arraySearchDepth];
        this.URtoUL = new int[arraySearchDepth];
        this.UBtoDF = new int[arraySearchDepth];
        this.URtoDF = new int[arraySearchDepth];
        this.minDistPhase1 = new int[arraySearchDepth];
        this.minDistPhase2 = new int[arraySearchDepth];
        final KociembaCubieCube cc = KociembaCubieCube.modelToCubieCube(
                this.getModel());
        final KociembaCoordinateCube c = new KociembaCoordinateCube(cc);
        this.po[0] = 0;
        this.ax[0] = 0;
        this.flip[0] = c.flip;
        this.twist[0] = c.twist;
        this.parity[0] = c.parity;
        this.slice[0] = c.FRtoBR / 24;
        this.URFtoDLF[0] = c.URFtoDLF;
        this.FRtoBR[0] = c.FRtoBR;
        this.URtoUL[0] = c.URtoUL;
        this.UBtoDF[0] = c.UBtoDF;
        // Prevent the algorithm from failing if depth = 1 and n = 0.
        this.minDistPhase1[1] = 1;
        int mv = 0;
        int n = 0;
        boolean busy = false;
        int depthPhase1 = 1;
        int s;
        final long tStart = System.currentTimeMillis();
        // If the cube is solved, the algorithm will not find a solution.
        // Check for that before starting.
        if (RubikCubeModel.isSolved(this.getModel())) {
            return new ArrayList<>();
        }
        do {
            do {
                if ((depthPhase1 - n > this.minDistPhase1[n + 1]) && !busy) {
                    if (this.ax[n] == 0 || this.ax[n] == 3) {
                        this.ax[++n] = 1;
                    } else {
                        this.ax[++n] = 0;
                    }
                    this.po[n] = 1;
                } else if (++this.po[n] > 3) {
                    do {
                        if (++this.ax[n] > 5) {
                            if (System.currentTimeMillis() - tStart > this.timeout << 10) {
                                throw new TimeoutException("Failed to find a"
                                        + " solution in " + this.timeout
                                        + " seconds");
                            }
                            if (n == 0) {
                                if (depthPhase1 >= this.maxDepth) {
                                    throw new NoSolutionException("Failed to"
                                            + " find a solution in a tree of depth "
                                            + this.maxDepth);
                                }
                                ++depthPhase1;
                                this.ax[n] = 0;
                                this.po[n] = 1;
                                busy = false;
                                break;
                            }
                            --n;
                            busy = true;
                            break;
                        }
                        this.po[n] = 1;
                        busy = false;
                    } while (n != 0 && (this.ax[n - 1] == this.ax[n]
                            || this.ax[n - 1] - 3 == this.ax[n]));
                } else {
                    busy = false;
                }
            } while (busy);
            mv = 3 * this.ax[n] + this.po[n] - 1;
            this.flip[n + 1] = KociembaCoordinateCube
                    .flipMove[this.flip[n]][mv];
            this.twist[n + 1] = KociembaCoordinateCube
                    .twistMove[this.twist[n]][mv];
            this.slice[n + 1] = KociembaCoordinateCube
                    .FRtoBR_Move[this.slice[n] * 24][mv] / 24;
            this.minDistPhase1[n + 1] = Math.max(KociembaCoordinateCube
                    .getPruning(KociembaCoordinateCube.Slice_Flip_Prun,
                    KociembaCoordinateCube.N_SLICE1 * this.flip[n + 1]
                    + this.slice[n + 1]), KociembaCoordinateCube
                    .getPruning(KociembaCoordinateCube.Slice_Twist_Prun,
                    KociembaCoordinateCube.N_SLICE1 * this.twist[n + 1]
                    + this.slice[n + 1]));
            if (this.minDistPhase1[n + 1] == 0 && n >= depthPhase1 - 5) {
                // Any value greater than five is possible (as per Kociemba's
                // findings).
                this.minDistPhase1[n + 1] = 10;
                if (n == depthPhase1 - 1) {
                    s = totalDepth(depthPhase1);
                    if (s >= 0 && (s == depthPhase1
                            || (this.ax[depthPhase1 - 1] != this.ax[depthPhase1]
                            && this.ax[depthPhase1 - 1] != this.ax[depthPhase1]
                            + 3))) {
                        final List<Move> moveList = new ArrayList<>();
                        for (int i = 0; i < s; ++i) {
                            Move moveToAdd;
                            switch (this.ax[i]) {
                                case 0:
                                    moveToAdd = new U(this.getModel());
                                    break;
                                case 1:
                                    moveToAdd = new R(this.getModel());
                                    break;
                                case 2:
                                    moveToAdd = new F(this.getModel());
                                    break;
                                case 3:
                                    moveToAdd = new D(this.getModel());
                                    break;
                                case 4:
                                    moveToAdd = new L(this.getModel());
                                    break;
                                case 5:
                                    moveToAdd = new B(this.getModel());
                                    break;
                                default:
                                    throw new NoSolutionException("Invalid solution");
                            }
                            for (int j = 1; j <= this.po[i]; ++j) {
                                moveList.add(moveToAdd);
                            }
                        }
                        return moveList;
                    }
                }

            }
        } while (true);
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
    /**
     * Gets the total depth for the search tree.
     * @param depthPhase1 Depth reached in Phase 1.
     * @return Total depth for the search tree.
     */
    private int totalDepth(final int depthPhase1) {
        int mv = 0;
        int d1 = 0;
        int d2 = 0;
        // Allow 10 moves max. in Phase 2 to prevent the tree from growing.
        final int maxDepthPhase2 = Math.min(10, this.maxDepth - depthPhase1);
        for (int i = 0; i < depthPhase1; ++i) {
            mv = 3 * this.ax[i] + this.po[i] - 1;
            this.URFtoDLF[i + 1] = KociembaCoordinateCube
                    .URFtoDLF_Move[this.URFtoDLF[i]][mv];
            this.FRtoBR[i + 1] = KociembaCoordinateCube
                    .FRtoBR_Move[this.FRtoBR[i]][mv];
            this.parity[i + 1] = KociembaCoordinateCube
                    .parityMove[this.parity[i]][mv];
        }
        d1 = KociembaCoordinateCube.getPruning(KociembaCoordinateCube
            .Slice_URFtoDLF_Parity_Prun, (KociembaCoordinateCube.N_SLICE2
            * this.URFtoDLF[depthPhase1] + this.FRtoBR[depthPhase1]) * 2
            + this.parity[depthPhase1]);
        if (d1 > maxDepthPhase2) {
            return -1;
        }
        for (int i = 0; i < depthPhase1; ++i) {
            mv = 3 * this.ax[i] + this.po[i] - 1;
            this.URtoUL[i + 1] = KociembaCoordinateCube
                    .URtoUL_Move[this.URtoUL[i]][mv];
            this.UBtoDF[i + 1] = KociembaCoordinateCube
                    .UBtoDF_Move[this.UBtoDF[i]][mv];
        }
        this.URtoDF[depthPhase1] = KociembaCoordinateCube
                .MergeURtoULandUBtoDF[this.URtoUL[depthPhase1]]
                        [this.UBtoDF[depthPhase1]];
        d2 = KociembaCoordinateCube.getPruning(KociembaCoordinateCube
            .Slice_URtoDF_Parity_Prun, (KociembaCoordinateCube.N_SLICE2
            * this.URtoDF[depthPhase1] + this.FRtoBR[depthPhase1]) * 2
            + this.parity[depthPhase1]);
        if (d2 > maxDepthPhase2) {
            return -1;
        }
        this.minDistPhase2[depthPhase1] = Math.max(d1, d2);
        if (this.minDistPhase2[depthPhase1] == 0) {
            // The cube is already solved
            return depthPhase1;
        }
        // Set up the search
        int depthPhase2 = 1;
        int n = depthPhase1;
        boolean busy = false;
        this.po[depthPhase1] = 0;
        this.ax[depthPhase1] = 0;
        // Initialize the minimum distance so that it won't fail for
        // depthPhase2 = 1 and n = 0
        this.minDistPhase2[n + 1] = 1;
        do {
            do {
                if ((depthPhase1 + depthPhase2 - n > this.minDistPhase2[n + 1])
                        && !busy) {
                    if (this.ax[n] == 0 || this.ax[n] == 3) {
                        this.ax[++n] = 1;
                        this.po[n] = 2;
                    } else {
                        this.ax[++n] = 0;
                        this.po[n] = 1;
                    }
                } else if ((this.ax[n] == 0 || this.ax[n] == 3) ? (++this.po[n] > 3) : ((this.po[n] = this.po[n] + 2) > 3)) {
                    do {
                        // Increment the axis
                        if (++this.ax[n] > 5) {
                            if (n == depthPhase1) {
                                if (depthPhase2 >= maxDepthPhase2) {
                                    return -1;
                                }
                                depthPhase2++;
                                this.ax[n] = 0;
                                this.po[n] = 1;
                                busy = false;
                                break;
                            }
                            n--;
                            busy = true;
                            break;
                        }
                        if (this.ax[n] == 0 || this.ax[n] == 3) {
                            this.po[n] = 1;
                        } else {
                            this.po[n] = 2;
                        }
                        busy = false;
                    } while (n != depthPhase1 && (this.ax[n - 1] == this.ax[n]
                            || this.ax[n - 1] - 3 == this.ax[n]));
                } else {
                    busy = false;
                }
            } while (busy);
            mv = 3 * this.ax[n] + this.po[n] - 1;
            this.URFtoDLF[n + 1] = KociembaCoordinateCube
                    .URFtoDLF_Move[this.URFtoDLF[n]][mv];
            this.FRtoBR[n + 1] = KociembaCoordinateCube
                    .FRtoBR_Move[this.FRtoBR[n]][mv];
            this.parity[n + 1] = KociembaCoordinateCube
                    .parityMove[this.parity[n]][mv];
            this.URtoDF[n + 1] = KociembaCoordinateCube
                    .URtoDF_Move[this.URtoDF[n]][mv];
            this.minDistPhase2[n + 1] = Math.max(KociembaCoordinateCube
                .getPruning(KociembaCoordinateCube.Slice_URtoDF_Parity_Prun,
                (KociembaCoordinateCube.N_SLICE2 * this.URtoDF[n + 1]
                + this.FRtoBR[n + 1]) * 2 + this.parity[n + 1]),
                KociembaCoordinateCube.getPruning(KociembaCoordinateCube
                .Slice_URFtoDLF_Parity_Prun, (KociembaCoordinateCube.N_SLICE2
                * this.URFtoDLF[n + 1] + this.FRtoBR[n + 1]) * 2
                + this.parity[n + 1]));
        } while (this.minDistPhase2[n + 1] != 0);
        return depthPhase1 + depthPhase2;
    }
}
