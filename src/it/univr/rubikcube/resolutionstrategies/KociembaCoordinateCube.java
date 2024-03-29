package it.univr.rubikcube.resolutionstrategies;

/**
 * Representation of the cube on the coordinate level in Kociemba's method.
 * @author Herbert Kociemba
 * @author Alessandro Menti
 */
public class KociembaCoordinateCube {
    /**
     * Number of possible permutations of the FR, FL, BL, BR edges (12 choose
     * 4).
     */
    static final short N_SLICE1 = 495;
    /**
     * Number of possible permutations of the FR, FL, BL, BR edges in Phase 2
     * (4!).
     */
    static final short N_SLICE2 = 24;
    /**
     * Number of possible corner parities (2).
     */
    static final short N_PARITY = 2;
    /**
     * Number of possible permutations of the URF, UFL, ULB, UBR, DFR, DLF
     * corners (8!/(8-6)!).
     */
    static final short N_URFtoDLF = 20160;
    /**
     * Number of possible permutations of the FR, FL, BL, BR edges (12!/
     * (12-4)!).
     */
    static final short N_FRtoBR = 11880;
    /**
     * Number of possible permutations of the UR, UF, UL edges (12!/
     * (12-3)!).
     */
    static final short N_URtoUL = 1320;
    /**
     * Number of possible permutations of the UB, DR, DF edges (12!/
     * (12-3)!).
     */
    static final short N_UBtoDF = 1320;
    /**
     * Number of possible permutations of the UR, UF, UL, UB, DR, DF edges in
     * Phase 2 (8!/(8-6)!).
     */
    static final short N_URtoDF = 20160;
    /**
     * Number of possible permutations of the corners (8!).
     */
    static final int N_URFtoDLB = 40320;
    /**
     * Number of possible permutation of upper right to back right moves.
     */
    static final int N_URtoBR = 479001600;
    /**
     * Number of possible moves for each turn (column + row + lateral column
     * rotations).
     */
    static final short N_MOVE = 18;
    /**
     * Number of possible corner orientations (3^7).
     */
    private static final short N_TWIST = 2187;
    /**
     * Number of possible edge flips (2^11).
     */
    private static final short N_FLIP = 2048;
    /**
     * Twist for a single move.
     */
    short twist;
    /**
     * Flip for a single move.
     */
    short flip;
    /**
     * Parity for a single move.
     */
    short parity;
    /**
     * Front to back parameter for a single move.
     */
    short FRtoBR;
    /**
     * URF to DLF parameter for a single move.
     */
    short URFtoDLF;
    /**
     * UR to UL parameter for a single move.
     */
    short URtoUL;
    /**
     * UB to DF parameter for a single move.
     */
    short UBtoDF;
    /**
     * UR to DF parameter for a single move.
     */
    int URtoDF;

    public KociembaCoordinateCube() {
        // Empty, nothing to do
    }

    public KociembaCoordinateCube(KociembaCubieCube c) {
        this.twist = c.getTwist();
        this.flip = c.getFlip();
        this.parity = c.cornerParity();
        this.FRtoBR = c.getFRtoBR();
        this.URFtoDLF = c.getURFtoDLF();
        this.URtoUL = c.getURtoUL();
        this.UBtoDF = c.getUBtoDF();
        this.URtoDF = c.getURtoDF();
    }

    // A move on the coordinate level
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void move(int m) {
        this.twist = twistMove[this.twist][m];
        this.flip = flipMove[this.flip][m];
        this.parity = parityMove[this.parity][m];
        this.FRtoBR = FRtoBR_Move[this.FRtoBR][m];
        this.URFtoDLF = URFtoDLF_Move[this.URFtoDLF][m];
        this.URtoUL = URtoUL_Move[this.URtoUL][m];
        this.UBtoDF = UBtoDF_Move[this.UBtoDF][m];
        if (this.URtoUL < 336 && this.UBtoDF < 336)// updated only if UR,UF,UL,UB,DR,DF
            // are not in UD-slice
            this.URtoDF = MergeURtoULandUBtoDF[this.URtoUL][this.UBtoDF];
    }

    // ******************************************Phase 1 move
    // tables*****************************************************

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Move table for the twists of the corners
    // twist < 2187 in phase 2.
    // twist = 0 in phase 2.
    static short[][] twistMove = new short[N_TWIST][N_MOVE];
    static {
        final KociembaCubieCube a = new KociembaCubieCube();
        for (short i = 0; i < N_TWIST; i++) {
            a.setTwist(i);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    a.cornerMultiply(KociembaCubieCube.moveCube[j]);
                    twistMove[i][3 * j + k] = a.getTwist();
                }
                a.cornerMultiply(KociembaCubieCube.moveCube[j]);// 4. faceturn restores
            }
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Move table for the flips of the edges
    // flip < 2048 in phase 1
    // flip = 0 in phase 2.
    static short[][] flipMove = new short[N_FLIP][N_MOVE];
    static {
        KociembaCubieCube a = new KociembaCubieCube();
        for (short i = 0; i < N_FLIP; i++) {
            a.setFlip(i);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    a.edgeMultiply(KociembaCubieCube.moveCube[j]);
                    flipMove[i][3 * j + k] = a.getFlip();
                }
                a.edgeMultiply(KociembaCubieCube.moveCube[j]);
                // a
            }
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Parity of the corner permutation. This is the same as the parity for the
    // edge permutation of a valid cube.
    // parity has values 0 and 1
    static short[][] parityMove = {
        {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1},
        {0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0}};

    // ***********************************Phase 1 and 2
    // movetable********************************************************

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Move table for the four UD-slice edges FR, FL, Bl and BR
    // FRtoBRMove < 11880 in phase 1
    // FRtoBRMove < 24 in phase 2
    // FRtoBRMove = 0 for solved cube
    static short[][] FRtoBR_Move = new short[N_FRtoBR][N_MOVE];
    static {
        KociembaCubieCube a = new KociembaCubieCube();
        for (short i = 0; i < N_FRtoBR; i++) {
            a.setFRtoBR(i);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    a.edgeMultiply(KociembaCubieCube.moveCube[j]);
                    FRtoBR_Move[i][3 * j + k] = a.getFRtoBR();
                }
                a.edgeMultiply(KociembaCubieCube.moveCube[j]);
            }
        }
    }

    // *******************************************Phase 1 and 2
    // movetable************************************************

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Move table for permutation of six corners. The positions of the DBL and
    // DRB corners are determined by the parity.
    // URFtoDLF < 20160 in phase 1
    // URFtoDLF < 20160 in phase 2
    // URFtoDLF = 0 for solved cube.
    static short[][] URFtoDLF_Move = new short[N_URFtoDLF][N_MOVE];
    static {
        KociembaCubieCube a = new KociembaCubieCube();
        for (short i = 0; i < N_URFtoDLF; i++) {
            a.setURFtoDLF(i);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    a.cornerMultiply(KociembaCubieCube.moveCube[j]);
                    URFtoDLF_Move[i][3 * j + k] = a.getURFtoDLF();
                }
                a.cornerMultiply(KociembaCubieCube.moveCube[j]);
            }
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Move table for the permutation of six U-face and D-face edges in phase2.
    // The positions of the DL and DB edges are
    // determined by the parity.
    // URtoDF < 665280 in phase 1
    // URtoDF < 20160 in phase 2
    // URtoDF = 0 for solved cube.
    static short[][] URtoDF_Move = new short[N_URtoDF][N_MOVE];
    static {
        KociembaCubieCube a = new KociembaCubieCube();
        for (short i = 0; i < N_URtoDF; i++) {
            a.setURtoDF(i);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    a.edgeMultiply(KociembaCubieCube.moveCube[j]);
                    URtoDF_Move[i][3 * j + k] = (short) a.getURtoDF();
                    // Table values are only valid for phase 2 moves!
                    // For phase 1 moves, casting to short is not possible.
                }
                a.edgeMultiply(KociembaCubieCube.moveCube[j]);
            }
        }
    }

    // **************************helper move tables to compute URtoDF for the
    // beginning of phase2************************

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Move table for the three edges UR,UF and UL in phase1.
    static short[][] URtoUL_Move = new short[N_URtoUL][N_MOVE];
    static {
        KociembaCubieCube a = new KociembaCubieCube();
        for (short i = 0; i < N_URtoUL; i++) {
            a.setURtoUL(i);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    a.edgeMultiply(KociembaCubieCube.moveCube[j]);
                    URtoUL_Move[i][3 * j + k] = a.getURtoUL();
                }
                a.edgeMultiply(KociembaCubieCube.moveCube[j]);
            }
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Move table for the three edges UB,DR and DF in phase1.
    static short[][] UBtoDF_Move = new short[N_UBtoDF][N_MOVE];
    static {
        KociembaCubieCube a = new KociembaCubieCube();
        for (short i = 0; i < N_UBtoDF; i++) {
            a.setUBtoDF(i);
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    a.edgeMultiply(KociembaCubieCube.moveCube[j]);
                    UBtoDF_Move[i][3 * j + k] = a.getUBtoDF();
                }
                a.edgeMultiply(KociembaCubieCube.moveCube[j]);
            }
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Table to merge the coordinates of the UR,UF,UL and UB,DR,DF edges at the
    // beginning of phase2
    static short[][] MergeURtoULandUBtoDF = new short[336][336];
    static {
        // for i, j <336 the six edges UR,UF,UL,UB,DR,DF are not in the
        // UD-slice and the index is <20160
        for (short uRtoUL = 0; uRtoUL < 336; uRtoUL++) {
            for (short uBtoDF = 0; uBtoDF < 336; uBtoDF++) {
                MergeURtoULandUBtoDF[uRtoUL][uBtoDF] = (short) KociembaCubieCube
                        .getURtoDF(uRtoUL, uBtoDF);
            }
        }
    }

    // ****************************************Pruning tables for the
    // search*********************************************

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Pruning table for the permutation of the corners and the UD-slice edges
    // in phase2.
    // The pruning table entries give a lower estimation for the number of moves
    // to reach the solved cube.
    static byte[] Slice_URFtoDLF_Parity_Prun = new byte[N_SLICE2 * N_URFtoDLF
        * N_PARITY / 2];
    static {
        for (int i = 0; i < N_SLICE2 * N_URFtoDLF * N_PARITY / 2; i++)
            Slice_URFtoDLF_Parity_Prun[i] = -1;
        int depth = 0;
        setPruning(Slice_URFtoDLF_Parity_Prun, 0, (byte) 0);
        int done = 1;
        while (done != N_SLICE2 * N_URFtoDLF * N_PARITY) {
            for (int i = 0; i < N_SLICE2 * N_URFtoDLF * N_PARITY; i++) {
                int parity = i % 2;
                int URFtoDLF = (i / 2) / N_SLICE2;
                int slice = (i / 2) % N_SLICE2;
                if (getPruning(Slice_URFtoDLF_Parity_Prun, i) == depth) {
                    for (int j = 0; j < 18; j++) {
                        switch (j) {
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 12:
                            case 14:
                            case 15:
                            case 17:
                                continue;
                            default:
                                int newSlice = FRtoBR_Move[slice][j];
                                int newURFtoDLF = URFtoDLF_Move[URFtoDLF][j];
                                int newParity = parityMove[parity][j];
                                if (getPruning(Slice_URFtoDLF_Parity_Prun,
                                               (N_SLICE2 * newURFtoDLF + newSlice)
                                                   * 2 + newParity) == 0x0f) {
                                    setPruning(Slice_URFtoDLF_Parity_Prun,
                                               (N_SLICE2 * newURFtoDLF + newSlice)
                                                   * 2 + newParity,
                                               (byte) (depth + 1));
                                    done++;
                                }
                        }
                    }
                }
            }
            depth++;
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Pruning table for the permutation of the edges in phase2.
    // The pruning table entries give a lower estimation for the number of moves
    // to reach the solved cube.
    static byte[] Slice_URtoDF_Parity_Prun = new byte[N_SLICE2 * N_URtoDF
        * N_PARITY / 2];
    static {
        for (int i = 0; i < N_SLICE2 * N_URtoDF * N_PARITY / 2; i++)
            Slice_URtoDF_Parity_Prun[i] = -1;
        int depth = 0;
        setPruning(Slice_URtoDF_Parity_Prun, 0, (byte) 0);
        int done = 1;
        while (done != N_SLICE2 * N_URtoDF * N_PARITY) {
            for (int i = 0; i < N_SLICE2 * N_URtoDF * N_PARITY; i++) {
                int parity = i % 2;
                int URtoDF = (i / 2) / N_SLICE2;
                int slice = (i / 2) % N_SLICE2;
                if (getPruning(Slice_URtoDF_Parity_Prun, i) == depth) {
                    for (int j = 0; j < 18; j++) {
                        switch (j) {
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 12:
                            case 14:
                            case 15:
                            case 17:
                                continue;
                            default:
                                int newSlice = FRtoBR_Move[slice][j];
                                int newURtoDF = URtoDF_Move[URtoDF][j];
                                int newParity = parityMove[parity][j];
                                if (getPruning(Slice_URtoDF_Parity_Prun,
                                               (N_SLICE2 * newURtoDF + newSlice)
                                                   * 2 + newParity) == 0x0f) {
                                    setPruning(Slice_URtoDF_Parity_Prun,
                                               (N_SLICE2 * newURtoDF + newSlice)
                                                   * 2 + newParity,
                                               (byte) (depth + 1));
                                    done++;
                                }
                        }
                    }
                }
            }
            depth++;
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Pruning table for the twist of the corners and the position (not
    // permutation) of the UD-slice edges in phase1
    // The pruning table entries give a lower estimation for the number of moves
    // to reach the H-subgroup.
    static byte[] Slice_Twist_Prun = new byte[N_SLICE1 * N_TWIST / 2 + 1];
    static {
        for (int i = 0; i < N_SLICE1 * N_TWIST / 2 + 1; i++)
            Slice_Twist_Prun[i] = -1;
        int depth = 0;
        setPruning(Slice_Twist_Prun, 0, (byte) 0);
        int done = 1;
        while (done != N_SLICE1 * N_TWIST) {
            for (int i = 0; i < N_SLICE1 * N_TWIST; i++) {
                int twist = i / N_SLICE1, slice = i % N_SLICE1;
                if (getPruning(Slice_Twist_Prun, i) == depth) {
                    for (int j = 0; j < 18; j++) {
                        int newSlice = FRtoBR_Move[slice * 24][j] / 24;
                        int newTwist = twistMove[twist][j];
                        if (getPruning(Slice_Twist_Prun, N_SLICE1 * newTwist
                            + newSlice) == 0x0f) {
                            setPruning(Slice_Twist_Prun, N_SLICE1 * newTwist
                                + newSlice, (byte) (depth + 1));
                            done++;
                        }
                    }
                }
            }
            depth++;
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Pruning table for the flip of the edges and the position (not
    // permutation) of the UD-slice edges in phase1
    // The pruning table entries give a lower estimation for the number of moves
    // to reach the H-subgroup.
    static byte[] Slice_Flip_Prun = new byte[N_SLICE1 * N_FLIP / 2];
    static {
        for (int i = 0; i < N_SLICE1 * N_FLIP / 2; i++)
            Slice_Flip_Prun[i] = -1;
        int depth = 0;
        setPruning(Slice_Flip_Prun, 0, (byte) 0);
        int done = 1;
        while (done != N_SLICE1 * N_FLIP) {
            for (int i = 0; i < N_SLICE1 * N_FLIP; i++) {
                int flip = i / N_SLICE1, slice = i % N_SLICE1;
                if (getPruning(Slice_Flip_Prun, i) == depth) {
                    for (int j = 0; j < 18; j++) {
                        int newSlice = FRtoBR_Move[slice * 24][j] / 24;
                        int newFlip = flipMove[flip][j];
                        if (getPruning(Slice_Flip_Prun, N_SLICE1 * newFlip
                            + newSlice) == 0x0f) {
                            setPruning(Slice_Flip_Prun, N_SLICE1 * newFlip
                                + newSlice, (byte) (depth + 1));
                            done++;
                        }
                    }
                }
            }
            depth++;
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Set pruning value in table. Two values are stored in one byte.
    static void setPruning(byte[] table, int index, byte value) {
        if ((index & 1) == 0) {
            table[index / 2] &= 0xf0 | value;
        } else {
            table[index / 2] &= 0x0f | (value << 4);
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Extract pruning value
    /**
     * Extract the pruning value for the IDA* algorithm from the lookup table.
     * @param table Lookup table to be checked.
     * @param index Index to be checked.
     * @return The desired pruning value.
     */
    static byte getPruning(final byte[] table, final int index) {
        if ((index & 1) == 0) {
            return (byte) (table[index / 2] & 0x0f);
        }
        return (byte) ((table[index / 2] & 0xf0) >>> 4);
    }
}
