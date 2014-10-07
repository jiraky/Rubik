package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.RubikCubeModelCorner;
import it.univr.rubikcube.model.RubikCubeModelEdge;

/**
 * Cube seen at the "cubie" level in the Kociemba resolution strategy.
 * @author Herbert Kociemba
 * @author Alessandro Menti
 */
public class KociembaCubieCube {
    /**
     * Corner permutation on the upper face.
     */
    private static RubikCubeModelCorner[] cpU = {
        RubikCubeModelCorner.UBR,
        RubikCubeModelCorner.URF,
        RubikCubeModelCorner.UFL,
        RubikCubeModelCorner.ULB,
        RubikCubeModelCorner.DFR,
        RubikCubeModelCorner.DLF,
        RubikCubeModelCorner.DBL,
        RubikCubeModelCorner.DRB,
    };
    /**
     * Corner orientation on the upper face.
     */
    private static byte[] coU = {0, 0, 0, 0, 0, 0, 0, 0};
    /**
     * Edge permutation on the upper face.
     */
    private static RubikCubeModelEdge[] epU = {
        RubikCubeModelEdge.UB,
        RubikCubeModelEdge.UR,
        RubikCubeModelEdge.UF,
        RubikCubeModelEdge.UL,
        RubikCubeModelEdge.DR,
        RubikCubeModelEdge.DF,
        RubikCubeModelEdge.DL,
        RubikCubeModelEdge.DB,
        RubikCubeModelEdge.FR,
        RubikCubeModelEdge.FL,
        RubikCubeModelEdge.BL,
        RubikCubeModelEdge.BR,
    };
    /**
     * Edge orientation on the upper face.
     */
    private static byte[] eoU = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    /**
     * Corner permutation on the right face.
     */
    private static RubikCubeModelCorner[] cpR = {
        RubikCubeModelCorner.DFR,
        RubikCubeModelCorner.UFL,
        RubikCubeModelCorner.ULB,
        RubikCubeModelCorner.URF,
        RubikCubeModelCorner.DRB,
        RubikCubeModelCorner.DLF,
        RubikCubeModelCorner.DBL,
        RubikCubeModelCorner.UBR,
    };
    /**
     * Corner orientation on the right face.
     */
    private static byte[] coR = {2, 0, 0, 1, 1, 0, 0, 2};
    /**
     * Edge permutation on the right face.
     */
    private static RubikCubeModelEdge[] epR = {
        RubikCubeModelEdge.FR,
        RubikCubeModelEdge.UF,
        RubikCubeModelEdge.UL,
        RubikCubeModelEdge.UB,
        RubikCubeModelEdge.BR,
        RubikCubeModelEdge.DF,
        RubikCubeModelEdge.DL,
        RubikCubeModelEdge.DB,
        RubikCubeModelEdge.DR,
        RubikCubeModelEdge.FL,
        RubikCubeModelEdge.BL,
        RubikCubeModelEdge.UR,
    };
    /**
     * Edge orientation on the right face.
     */
    private static byte[] eoR = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    /**
     * Corner permutation on the front face.
     */
    private static RubikCubeModelCorner[] cpF = {
        RubikCubeModelCorner.UFL,
        RubikCubeModelCorner.DLF,
        RubikCubeModelCorner.ULB,
        RubikCubeModelCorner.UBR,
        RubikCubeModelCorner.URF,
        RubikCubeModelCorner.DFR,
        RubikCubeModelCorner.DBL,
        RubikCubeModelCorner.DRB,
    };
    /**
     * Corner orientation on the front face.
     */
    private static byte[] coF = {1, 2, 0, 0, 2, 1, 0, 0};
    /**
     * Edge permutation on the front face.
     */
    private static RubikCubeModelEdge[] epF = {
        RubikCubeModelEdge.UR,
        RubikCubeModelEdge.FL,
        RubikCubeModelEdge.UL,
        RubikCubeModelEdge.UB,
        RubikCubeModelEdge.DR,
        RubikCubeModelEdge.FR,
        RubikCubeModelEdge.DL,
        RubikCubeModelEdge.DB,
        RubikCubeModelEdge.UF,
        RubikCubeModelEdge.DF,
        RubikCubeModelEdge.BL,
        RubikCubeModelEdge.BR,
    };
    /**
     * Edge orientation on the front face.
     */
    private static byte[] eoF = {0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0};
    /**
     * Corner permutation on the down face.
     */
    private static RubikCubeModelCorner[] cpD = {
        RubikCubeModelCorner.URF,
        RubikCubeModelCorner.UFL,
        RubikCubeModelCorner.ULB,
        RubikCubeModelCorner.UBR,
        RubikCubeModelCorner.DLF,
        RubikCubeModelCorner.DBL,
        RubikCubeModelCorner.DRB,
        RubikCubeModelCorner.DFR,
    };
    /**
     * Corner orientation on the down face.
     */    
    private static byte[] coD = {0, 0, 0, 0, 0, 0, 0, 0};
    /**
     * Edge permutation on the down face.
     */
    private static RubikCubeModelEdge[] epD = {
        RubikCubeModelEdge.UR,
        RubikCubeModelEdge.UF,
        RubikCubeModelEdge.UL,
        RubikCubeModelEdge.UB,
        RubikCubeModelEdge.DF,
        RubikCubeModelEdge.DL,
        RubikCubeModelEdge.DB,
        RubikCubeModelEdge.DR,
        RubikCubeModelEdge.FR,
        RubikCubeModelEdge.FL,
        RubikCubeModelEdge.BL,
        RubikCubeModelEdge.BR,
    };
    /**
     * Edge orientation on the down face.
     */
    private static byte[] eoD = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    /**
     * Corner permutation on the left face.
     */
    private static RubikCubeModelCorner[] cpL = {
        RubikCubeModelCorner.URF,
        RubikCubeModelCorner.ULB,
        RubikCubeModelCorner.DBL,
        RubikCubeModelCorner.UBR,
        RubikCubeModelCorner.DFR,
        RubikCubeModelCorner.UFL,
        RubikCubeModelCorner.DLF,
        RubikCubeModelCorner.DRB,
    };
    /**
     * Corner orientation on the left face.
     */
    private static byte[] coL = {0, 1, 2, 0, 0, 2, 1, 0};
    /**
     * Edge permutation on the left face.
     */
    private static RubikCubeModelEdge[] epL = {
        RubikCubeModelEdge.UR,
        RubikCubeModelEdge.UF,
        RubikCubeModelEdge.BL,
        RubikCubeModelEdge.UB,
        RubikCubeModelEdge.DR,
        RubikCubeModelEdge.DF,
        RubikCubeModelEdge.FL,
        RubikCubeModelEdge.DB,
        RubikCubeModelEdge.FR,
        RubikCubeModelEdge.UL,
        RubikCubeModelEdge.DL,
        RubikCubeModelEdge.BR,
    };
    /**
     * Edge orientation on the left face.
     */
    private static byte[] eoL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    /**
     * Corner permutation on the bottom face.
     */
    private static RubikCubeModelCorner[] cpB = {
        RubikCubeModelCorner.URF,
        RubikCubeModelCorner.UFL,
        RubikCubeModelCorner.UBR,
        RubikCubeModelCorner.DRB,
        RubikCubeModelCorner.DFR,
        RubikCubeModelCorner.DLF,
        RubikCubeModelCorner.ULB,
        RubikCubeModelCorner.DBL,
    };
    /**
     * Corner orientation on the bottom face.
     */
    private static byte[] coB = {0, 0, 1, 2, 0, 0, 2, 1};
    /**
     * Edge permutation on the bottom face.
     */
    private static RubikCubeModelEdge[] epB = {
        RubikCubeModelEdge.UR,
        RubikCubeModelEdge.UF,
        RubikCubeModelEdge.UL,
        RubikCubeModelEdge.BR,
        RubikCubeModelEdge.DR,
        RubikCubeModelEdge.DF,
        RubikCubeModelEdge.DL,
        RubikCubeModelEdge.BL,
        RubikCubeModelEdge.FR,
        RubikCubeModelEdge.FL,
        RubikCubeModelEdge.UB,
        RubikCubeModelEdge.DB,
    };
    /**
     * Edge orientation on the bottom face.
     */
    private static byte[] eoB = {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1};

    /**
     * Basic cube moves.
     */
    static KociembaCubieCube[] moveCube = new KociembaCubieCube[6];

    static {
        moveCube[0] = new KociembaCubieCube();
        moveCube[0].cp = cpU;
        moveCube[0].co = coU;
        moveCube[0].ep = epU;
        moveCube[0].eo = eoU;

        moveCube[1] = new KociembaCubieCube();
        moveCube[1].cp = cpR;
        moveCube[1].co = coR;
        moveCube[1].ep = epR;
        moveCube[1].eo = eoR;

        moveCube[2] = new KociembaCubieCube();
        moveCube[2].cp = cpF;
        moveCube[2].co = coF;
        moveCube[2].ep = epF;
        moveCube[2].eo = eoF;

        moveCube[3] = new KociembaCubieCube();
        moveCube[3].cp = cpD;
        moveCube[3].co = coD;
        moveCube[3].ep = epD;
        moveCube[3].eo = eoD;

        moveCube[4] = new KociembaCubieCube();
        moveCube[4].cp = cpL;
        moveCube[4].co = coL;
        moveCube[4].ep = epL;
        moveCube[4].eo = eoL;

        moveCube[5] = new KociembaCubieCube();
        moveCube[5].cp = cpB;
        moveCube[5].co = coB;
        moveCube[5].ep = epB;
        moveCube[5].eo = eoB;

    }
    /**
     * Corner permutations.
     */
    private RubikCubeModelCorner[] cp = {
        RubikCubeModelCorner.URF,
        RubikCubeModelCorner.UFL,
        RubikCubeModelCorner.ULB,
        RubikCubeModelCorner.UBR,
        RubikCubeModelCorner.DFR,
        RubikCubeModelCorner.DLF,
        RubikCubeModelCorner.DBL,
        RubikCubeModelCorner.DRB,
    };
    /**
     * Corner orientation.
     */
    private byte[] co = {0, 0, 0, 0, 0, 0, 0, 0, };
    /**
     * Edge permutation.
     */
    private RubikCubeModelEdge[] ep = {
        RubikCubeModelEdge.UR,
        RubikCubeModelEdge.UF,
        RubikCubeModelEdge.UL,
        RubikCubeModelEdge.UB,
        RubikCubeModelEdge.DR,
        RubikCubeModelEdge.DF,
        RubikCubeModelEdge.DL,
        RubikCubeModelEdge.DB,
        RubikCubeModelEdge.FR,
        RubikCubeModelEdge.FL,
        RubikCubeModelEdge.BL,
        RubikCubeModelEdge.BR,
    };
    /**
     * Edge orientation.
     */
    private byte[] eo = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
    /**
     * Initializes a new default cube.
     */
    public KociembaCubieCube() {
        // Empty, nothing to do
    }
    /**
     * Initializes a new cube with the given corner permutation, corner, edge
     * permutation and edge.
     * @param cperm Corner permutation.
     * @param cor Corner orientation.
     * @param eperm Edge permutation.
     * @param eor Edge orientation.
     */
    public KociembaCubieCube(final RubikCubeModelCorner[] cperm, final byte[] cor,
                             final RubikCubeModelEdge[] eperm, final byte[] eor) {
        this();
        for (int i = 0; i < 8; ++i) {
            this.cp[i] = cperm[i];
            this.co[i] = cor[i];
        }
        for (int i = 0; i < 12; ++i) {
            this.ep[i] = eperm[i];
            this.eo[i] = eor[i];
        }
    }
    /**
     * Returns the value of <tt>n</tt> choose <tt>k</tt>.
     * @param n Value of <tt>n</tt>.
     * @param k Value of <tt>k</tt>.
     * @return <tt>n</tt> choose <tt>k</tt>
     */
    private static int Cnk(final int n, final int k) {
        int i;
        int j;
        int s;
        int kt = k;
        if (n < kt) {
            return 0;
        }
        if (kt > n / 2) {
            kt = n - kt;
        }
        for (s = 1, i = n, j = 1; i != n - kt; --i, ++j) {
            s *= i;
            s /= j;
        }
        return s;
    }
    /**
     * Rotates all array elements between l and r to the left.
     * @param arr Corner array to be rotated.
     * @param l Left index.
     * @param r Right index.
     */
    // CHECKSTYLE:OFF arr is NOT final by design
    private static void rotateLeft(RubikCubeModelCorner[] arr, final int l,
                                   final int r) {
    // CHECKSTYLE:ON
        final RubikCubeModelCorner temp = arr[l];
        for (int i = l; i < r; ++i) {
            arr[i] = arr[i + 1];
        }
        arr[r] = temp;
    }
    /**
     * Rotates all array elements between l and r to the right.
     * @param arr Corner array to be rotated.
     * @param l Left index.
     * @param r Right index.
     */
    // CHECKSTYLE:OFF arr is NOT final by design
    private static void rotateRight(RubikCubeModelCorner[] arr, final int l,
                                    final int r) {
        RubikCubeModelCorner temp = arr[r];
        for (int i = r; i > l; i--)
            arr[i] = arr[i - 1];
        arr[l] = temp;
    }
    /**
     * Rotates all array elements between l and r to the left.
     * @param arr Edge array to be rotated.
     * @param l Left index.
     * @param r Right index.
     */
    // CHECKSTYLE:OFF arr is NOT final by design
    private static void rotateLeft(RubikCubeModelEdge[] arr, final int l,
                                   final int r) {
    // CHECKSTYLE:ON
        final RubikCubeModelEdge temp = arr[l];
        for (int i = l; i < r; ++i) {
            arr[i] = arr[i + 1];
        }
        arr[r] = temp;
    }
    /**
     * Rotates all array elements between l and r to the right.
     * @param arr Edge array to be rotated.
     * @param l Left index.
     * @param r Right index.
     */
    // CHECKSTYLE:OFF arr is NOT final by design
    private static void rotateRight(RubikCubeModelEdge[] arr, final int l,
                                    final int r) {
    // CHECKSTYLE:ON
        final RubikCubeModelEdge temp = arr[r];
        for (int i = r; i > l; --i) {
            arr[i] = arr[i - 1];
        }
        arr[l] = temp;
    }
    // FIXME toFaceCube removed
    /**
     * Multiply this CubieCube with another cubiecube b, restricted to the
     * corners.
     * @param b Cube to multiply this cube with.
     */
    void cornerMultiply(final KociembaCubieCube b) {
        final RubikCubeModelCorner[] cPerm = new RubikCubeModelCorner[8];
        final byte[] cOri = new byte[8];
        for (RubikCubeModelCorner corn : RubikCubeModelCorner.values()) {
            cPerm[corn.ordinal()] = this.cp[b.cp[corn.ordinal()].ordinal()];
            final byte oriA = this.co[b.cp[corn.ordinal()].ordinal()];
            final byte oriB = b.co[corn.ordinal()];
            byte ori = 0;
            // If both cubes are regular, just perform an addition modulo 3
            if (oriA < 3 && oriB < 3) {
                ori = (byte) (oriA + oriB);
                if (ori >= 3) {
                    ori -= 3;
                }
            // If the cube b is in a mirrored state, the composition is a
            // mirrored cube
            } else if (oriA < 3 && oriB >= 3) {
                ori = (byte) (oriA + oriB);
                if (ori >= 6) {
                    ori -= 3;
                }
            // If the cube a is in a mirrored state, the composition is a
            // mirrored cube
            } else if (oriA >= 3 && oriB < 3) {
                ori = (byte) (oriA - oriB);
                if (ori < 3) {
                    ori += 3;
                }
            // If both cubes are mirrored, the composition is regular
            } else if (oriA >= 3 && oriB >= 3) {
                ori = (byte) (oriA - oriB);
                if (ori < 0) {
                    ori += 3;
                }
            }
            cOri[corn.ordinal()] = ori;
        }
        for (RubikCubeModelCorner c : RubikCubeModelCorner.values()) {
            this.cp[c.ordinal()] = cPerm[c.ordinal()];
            this.co[c.ordinal()] = cOri[c.ordinal()];
        }
    }
    /**
     * Multiply this CubieCube with another CubieCube b, restricted to the
     * edges.
     * @param b Cube to multiply this cube with.
     */
    void edgeMultiply(final KociembaCubieCube b) {
        final RubikCubeModelEdge[] ePerm = new RubikCubeModelEdge[12];
        final byte[] eOri = new byte[12];
        for (RubikCubeModelEdge edge : RubikCubeModelEdge.values()) {
            ePerm[edge.ordinal()] = this.ep[b.ep[edge.ordinal()].ordinal()];
            eOri[edge.ordinal()] = (byte) ((b.eo[edge.ordinal()] + this.eo[b.ep[edge.ordinal()].ordinal()]) % 2);
        }
        for (RubikCubeModelEdge e : RubikCubeModelEdge.values()) {
            this.ep[e.ordinal()] = ePerm[e.ordinal()];
            this.eo[e.ordinal()] = eOri[e.ordinal()];
        }
    }
    /**
     * Multiply this CubieCube with another CubieCube b.
     * @param b Cube to multiply this cube with.
     */
    private void multiply(final KociembaCubieCube b) {
        this.cornerMultiply(b);
    }
    /**
     * Compute the inverse cube.
     * @param c Cube.
     */
    private void invCubieCube(final KociembaCubieCube c) {
        for (RubikCubeModelEdge edge : RubikCubeModelEdge.values()) {
            c.ep[this.ep[edge.ordinal()].ordinal()] = edge;
        }
        for (RubikCubeModelEdge edge : RubikCubeModelEdge.values()) {
            c.eo[edge.ordinal()] = this.eo[c.ep[edge.ordinal()].ordinal()];
        }
        for (RubikCubeModelCorner corn : RubikCubeModelCorner.values()) {
            c.cp[this.cp[corn.ordinal()].ordinal()] = corn;
        }
        for (RubikCubeModelCorner corn : RubikCubeModelCorner.values()) {
            final byte ori = this.co[c.cp[corn.ordinal()].ordinal()];
            if (ori >= 3) {
                // Just for completeness. We do not invert mirrored cubes here.
                c.co[corn.ordinal()] = ori;
            } else {
                c.co[corn.ordinal()] = (byte) -ori;
                if (c.co[corn.ordinal()] < 0) {
                    c.co[corn.ordinal()] += 3;
                }
            }
        }
    }
    /**
     * Get the twist of the eight corners.
     * @return The twist of the corners (0 <= twist < 3^7).
     */
    short getTwist() {
        short ret = 0;
        for (int i = RubikCubeModelCorner.URF.ordinal();
                i < RubikCubeModelCorner.DRB.ordinal(); ++i) {
            ret = (short) (3 * ret + this.co[i]);
        }
        return ret;
    }
    /**
     * Sets the twist of the eight corners.
     * @param twist The twist of the corners.
     */
    void setTwist(final short twist) {
        short twistTmp = twist;
        int twistParity = 0;
        for (int i = RubikCubeModelCorner.DRB.ordinal() - 1;
                i >= RubikCubeModelCorner.URF.ordinal(); --i) {
            this.co[i] = (byte) (twistTmp % 3);
            twistParity += this.co[i];
            twistTmp /= 3;
        }
        this.co[RubikCubeModelCorner.DRB.ordinal()]
                = (byte) ((3 - twistParity % 3) % 3);
    }
    /**
     * Gets the flip of the twelve edges.
     * @return The flip of the edges (0<= flip < 2^11).
     */
    short getFlip() {
        short ret = 0;
        for (int i = RubikCubeModelEdge.UR.ordinal();
                i < RubikCubeModelEdge.BR.ordinal(); ++i) {
            ret = (short) (2 * ret + this.eo[i]);
        }
        return ret;
    }
    /**
     * Sets the flip of the twelve edges.
     * @param flip The flip of the edges.
     */
    void setFlip(final short flip) {
        short flipTmp = flip;
        int flipParity = 0;
        for (int i = RubikCubeModelEdge.BR.ordinal() - 1;
                i >= RubikCubeModelEdge.UR.ordinal(); --i) {
            this.eo[i] = (byte) (flipTmp % 2);
            flipParity += this.eo[i];
            flipTmp /= 2;
        }
        this.eo[RubikCubeModelEdge.BR.ordinal()]
                = (byte) ((2 - flipParity % 2) % 2);
    }
    // Parity calculation. The parity of corners and edges are the same if the
    // cube is solvable.
    /**
     * Gets the parity of the corner permutation.
     * @return The parity of the corner permutation.
     */
    private short cornerParity() {
        int s = 0;
        for (int i = RubikCubeModelCorner.DRB.ordinal();
                i >= RubikCubeModelCorner.URF.ordinal() + 1; --i) {
            for (int j = i - 1; j >= RubikCubeModelCorner.URF.ordinal(); --j) {
                if (this.cp[j].ordinal() > this.cp[i].ordinal()) {
                    ++s;
                }
            }
        }
        return (short) (s % 2);
    }
    /**
     * Gets the parity of the edge permutation.
     * @return The parity of the edge permutation.
     */
    private short edgeParity() {
        int s = 0;
        for (int i = RubikCubeModelEdge.BR.ordinal();
                i >= RubikCubeModelEdge.UR.ordinal() + 1; --i) {
            for (int j = i - 1; j >= RubikCubeModelEdge.UR.ordinal(); --j) {
                if (this.ep[j].ordinal() > this.ep[i].ordinal()) {
                    ++s;
                }
            }
        }
        return (short) (s % 2);
    }
    /**
     * Permute the UD-slice edges FR, FL, BL and BR.
     * @return The requested permutation.
     */
    short getFRtoBR() {
        int a = 0;
        int x = 0;
        final RubikCubeModelEdge[] edge4 = new RubikCubeModelEdge[4];
        for (int j = RubikCubeModelEdge.BR.ordinal();
                j >= RubikCubeModelEdge.UR.ordinal(); --j) {
            if (RubikCubeModelEdge.FR.ordinal() <= this.ep[j].ordinal()
                    && this.ep[j].ordinal() <= RubikCubeModelEdge.BR.ordinal()) {
                a += Cnk(11 - j, x + 1);
                edge4[3 - x++] = this.ep[j];
            }
        }
        int b = 0;
        for (int j = 3; j > 0; --j) {
            int k = 0;
            while (edge4[j].ordinal() != j + 8) {
                rotateLeft(edge4, 0, j);
                ++k;
            }
            b = (j + 1) * b + k;
        }
        return (short) (24 * a + b);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void setFRtoBR(short idx) {
        int x;
        RubikCubeModelEdge[] sliceEdge = {RubikCubeModelEdge.FR, RubikCubeModelEdge.FL, RubikCubeModelEdge.BL, RubikCubeModelEdge.BR};
        RubikCubeModelEdge[] otherEdge = {RubikCubeModelEdge.UR, RubikCubeModelEdge.UF, RubikCubeModelEdge.UL, RubikCubeModelEdge.UB, RubikCubeModelEdge.DR, RubikCubeModelEdge.DF, RubikCubeModelEdge.DL, RubikCubeModelEdge.DB};
        int b = idx % 24; // Permutation
        int a = idx / 24; // Combination
        for (RubikCubeModelEdge e : RubikCubeModelEdge.values())
            ep[e.ordinal()] = RubikCubeModelEdge.DB;// Use UR to invalidate all edges

        for (int j = 1, k; j < 4; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(sliceEdge, 0, j);
        }

        x = 3;// generate combination and set slice edges
        for (int j = RubikCubeModelEdge.UR.ordinal(); j <= RubikCubeModelEdge.BR.ordinal(); j++)
            if (a - Cnk(11 - j, x + 1) >= 0) {
                ep[j] = sliceEdge[3 - x];
                a -= Cnk(11 - j, x-- + 1);
            }
        x = 0; // set the remaining edges UR..DB
        for (int j = RubikCubeModelEdge.UR.ordinal(); j <= RubikCubeModelEdge.BR.ordinal(); j++)
            if (ep[j] == RubikCubeModelEdge.DB)
                ep[j] = otherEdge[x++];

    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Permutation of all corners except DBL and DRB
    short getURFtoDLF() {
        int a = 0, x = 0;
        RubikCubeModelCorner[] corner6 = new RubikCubeModelCorner[6];
        // compute the index a < (8 choose 6) and the corner permutation.
        for (int j = RubikCubeModelCorner.URF.ordinal(); j <= RubikCubeModelCorner.DRB.ordinal(); j++)
            if (cp[j].ordinal() <= RubikCubeModelCorner.DLF.ordinal()) {
                a += Cnk(j, x + 1);
                corner6[x++] = cp[j];
            }

        int b = 0;
        for (int j = 5; j > 0; j--)// compute the index b < 6! for the
        // permutation in corner6
        {
            int k = 0;
            while (corner6[j].ordinal() != j) {
                rotateLeft(corner6, 0, j);
                k++;
            }
            b = (j + 1) * b + k;
        }
        return (short) (720 * a + b);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void setURFtoDLF(short idx) {
        int x;
        RubikCubeModelCorner[] corner6 = {RubikCubeModelCorner.URF, RubikCubeModelCorner.UFL, RubikCubeModelCorner.ULB, RubikCubeModelCorner.UBR, RubikCubeModelCorner.DFR, RubikCubeModelCorner.DLF};
        RubikCubeModelCorner[] otherCorner = {RubikCubeModelCorner.DBL, RubikCubeModelCorner.DRB};
        int b = idx % 720; // Permutation
        int a = idx / 720; // Combination
        for (RubikCubeModelCorner c : RubikCubeModelCorner.values())
            cp[c.ordinal()] = RubikCubeModelCorner.DRB;// Use DRB to invalidate all corners

        for (int j = 1, k; j < 6; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(corner6, 0, j);
        }
        x = 5;// generate combination and set corners
        for (int j = RubikCubeModelCorner.DRB.ordinal(); j >= 0; j--)
            if (a - Cnk(j, x + 1) >= 0) {
                cp[j] = corner6[x];
                a -= Cnk(j, x-- + 1);
            }
        x = 0;
        for (int j = RubikCubeModelCorner.URF.ordinal(); j <= RubikCubeModelCorner.DRB.ordinal(); j++)
            if (cp[j] == RubikCubeModelCorner.DRB)
                cp[j] = otherCorner[x++];
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Permutation of the six edges UR,UF,UL,UB,DR,DF.
    int getURtoDF() {
        int a = 0, x = 0;
        RubikCubeModelEdge[] edge6 = new RubikCubeModelEdge[6];
        // compute the index a < (12 choose 6) and the edge permutation.
        for (int j = RubikCubeModelEdge.UR.ordinal(); j <= RubikCubeModelEdge.BR.ordinal(); j++)
            if (ep[j].ordinal() <= RubikCubeModelEdge.DF.ordinal()) {
                a += Cnk(j, x + 1);
                edge6[x++] = ep[j];
            }

        int b = 0;
        for (int j = 5; j > 0; j--)// compute the index b < 6! for the
        // permutation in edge6
        {
            int k = 0;
            while (edge6[j].ordinal() != j) {
                rotateLeft(edge6, 0, j);
                k++;
            }
            b = (j + 1) * b + k;
        }
        return 720 * a + b;
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void setURtoDF(int idx) {
        int x;
        RubikCubeModelEdge[] edge6 = {RubikCubeModelEdge.UR, RubikCubeModelEdge.UF, RubikCubeModelEdge.UL, RubikCubeModelEdge.UB, RubikCubeModelEdge.DR, RubikCubeModelEdge.DF};
        RubikCubeModelEdge[] otherEdge = {RubikCubeModelEdge.DL, RubikCubeModelEdge.DB, RubikCubeModelEdge.FR, RubikCubeModelEdge.FL, RubikCubeModelEdge.BL, RubikCubeModelEdge.BR};
        int b = idx % 720; // Permutation
        int a = idx / 720; // Combination
        for (RubikCubeModelEdge e : RubikCubeModelEdge.values())
            ep[e.ordinal()] = RubikCubeModelEdge.BR;// Use BR to invalidate all edges

        for (int j = 1, k; j < 6; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(edge6, 0, j);
        }
        x = 5;// generate combination and set edges
        for (int j = RubikCubeModelEdge.BR.ordinal(); j >= 0; j--)
            if (a - Cnk(j, x + 1) >= 0) {
                ep[j] = edge6[x];
                a -= Cnk(j, x-- + 1);
            }
        x = 0; // set the remaining edges DL..BR
        for (int j = RubikCubeModelEdge.UR.ordinal(); j <= RubikCubeModelEdge.BR.ordinal(); j++)
            if (ep[j] == RubikCubeModelEdge.BR)
                ep[j] = otherEdge[x++];
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Permutation of the six edges UR,UF,UL,UB,DR,DF
    public static int getURtoDF(short idx1, short idx2) {
        KociembaCubieCube a = new KociembaCubieCube();
        KociembaCubieCube b = new KociembaCubieCube();
        a.setURtoUL(idx1);
        b.setUBtoDF(idx2);
        for (int i = 0; i < 8; i++) {
            if (a.ep[i] != RubikCubeModelEdge.BR)
                if (b.ep[i] != RubikCubeModelEdge.BR)// collision
                    return -1;
                else
                    b.ep[i] = a.ep[i];
        }
        return b.getURtoDF();
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Permutation of the three edges UR,UF,UL
    short getURtoUL() {
        int a = 0, x = 0;
        RubikCubeModelEdge[] edge3 = new RubikCubeModelEdge[3];
        // compute the index a < (12 choose 3) and the edge permutation.
        for (int j = RubikCubeModelEdge.UR.ordinal(); j <= RubikCubeModelEdge.BR.ordinal(); j++)
            if (ep[j].ordinal() <= RubikCubeModelEdge.UL.ordinal()) {
                a += Cnk(j, x + 1);
                edge3[x++] = ep[j];
            }

        int b = 0;
        for (int j = 2; j > 0; j--)// compute the index b < 3! for the
        // permutation in edge3
        {
            int k = 0;
            while (edge3[j].ordinal() != j) {
                rotateLeft(edge3, 0, j);
                k++;
            }
            b = (j + 1) * b + k;
        }
        return (short) (6 * a + b);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void setURtoUL(short idx) {
        int x;
        RubikCubeModelEdge[] edge3 = {RubikCubeModelEdge.UR, RubikCubeModelEdge.UF, RubikCubeModelEdge.UL};
        int b = idx % 6; // Permutation
        int a = idx / 6; // Combination
        for (RubikCubeModelEdge e : RubikCubeModelEdge.values())
            ep[e.ordinal()] = RubikCubeModelEdge.BR;// Use BR to invalidate all edges

        for (int j = 1, k; j < 3; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(edge3, 0, j);
        }
        x = 2;// generate combination and set edges
        for (int j = RubikCubeModelEdge.BR.ordinal(); j >= 0; j--)
            if (a - Cnk(j, x + 1) >= 0) {
                ep[j] = edge3[x];
                a -= Cnk(j, x-- + 1);
            }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Permutation of the three edges UB,DR,DF
    short getUBtoDF() {
        int a = 0, x = 0;
        RubikCubeModelEdge[] edge3 = new RubikCubeModelEdge[3];
        // compute the index a < (12 choose 3) and the edge permutation.
        for (int j = RubikCubeModelEdge.UR.ordinal(); j <= RubikCubeModelEdge.BR.ordinal(); j++)
            if (RubikCubeModelEdge.UB.ordinal() <= ep[j].ordinal() && ep[j].ordinal() <= RubikCubeModelEdge.DF.ordinal()) {
                a += Cnk(j, x + 1);
                edge3[x++] = ep[j];
            }

        int b = 0;
        for (int j = 2; j > 0; j--)// compute the index b < 3! for the
        // permutation in edge3
        {
            int k = 0;
            while (edge3[j].ordinal() != RubikCubeModelEdge.UB.ordinal() + j) {
                rotateLeft(edge3, 0, j);
                k++;
            }
            b = (j + 1) * b + k;
        }
        return (short) (6 * a + b);
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    void setUBtoDF(short idx) {
        int x;
        RubikCubeModelEdge[] edge3 = {RubikCubeModelEdge.UB, RubikCubeModelEdge.DR, RubikCubeModelEdge.DF};
        int b = idx % 6; // Permutation
        int a = idx / 6; // Combination
        for (RubikCubeModelEdge e : RubikCubeModelEdge.values())
            ep[e.ordinal()] = RubikCubeModelEdge.BR;// Use BR to invalidate all edges

        for (int j = 1, k; j < 3; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(edge3, 0, j);
        }
        x = 2;// generate combination and set edges
        for (int j = RubikCubeModelEdge.BR.ordinal(); j >= 0; j--)
            if (a - Cnk(j, x + 1) >= 0) {
                ep[j] = edge3[x];
                a -= Cnk(j, x-- + 1);
            }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int getURFtoDLB() {
        RubikCubeModelCorner[] perm = new RubikCubeModelCorner[8];
        int b = 0;
        for (int i = 0; i < 8; i++)
            perm[i] = cp[i];
        for (int j = 7; j > 0; j--)// compute the index b < 8! for the permutation in perm
        {
            int k = 0;
            while (perm[j].ordinal() != j) {
                rotateLeft(perm, 0, j);
                k++;
            }
            b = (j + 1) * b + k;
        }
        return b;
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setURFtoDLB(int idx) {
        RubikCubeModelCorner[] perm = {RubikCubeModelCorner.URF, RubikCubeModelCorner.UFL, RubikCubeModelCorner.ULB, RubikCubeModelCorner.UBR, RubikCubeModelCorner.DFR, RubikCubeModelCorner.DLF, RubikCubeModelCorner.DBL, RubikCubeModelCorner.DRB};
        int k;
        for (int j = 1; j < 8; j++) {
            k = idx % (j + 1);
            idx /= j + 1;
            while (k-- > 0)
                rotateRight(perm, 0, j);
        }
        int x = 7;// set corners
        for (int j = 7; j >= 0; j--)
            cp[j] = perm[x--];
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private int getURtoBR() {
        RubikCubeModelEdge[] perm = new RubikCubeModelEdge[12];
        int b = 0;
        for (int i = 0; i < 12; i++)
            perm[i] = ep[i];
        for (int j = 11; j > 0; j--)// compute the index b < 12! for the permutation in perm
        {
            int k = 0;
            while (perm[j].ordinal() != j) {
                rotateLeft(perm, 0, j);
                k++;
            }
            b = (j + 1) * b + k;
        }
        return b;
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private void setURtoBR(int idx) {
        RubikCubeModelEdge[] perm = {RubikCubeModelEdge.UR, RubikCubeModelEdge.UF, RubikCubeModelEdge.UL, RubikCubeModelEdge.UB, RubikCubeModelEdge.DR, RubikCubeModelEdge.DF, RubikCubeModelEdge.DL, RubikCubeModelEdge.DB, RubikCubeModelEdge.FR, RubikCubeModelEdge.FL, RubikCubeModelEdge.BL, RubikCubeModelEdge.BR};
        int k;
        for (int j = 1; j < 12; j++) {
            k = idx % (j + 1);
            idx /= j + 1;
            while (k-- > 0)
                rotateRight(perm, 0, j);
        }
        int x = 11;// set edges
        for (int j = 11; j >= 0; j--)
            ep[j] = perm[x--];
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Check a cubiecube for solvability. Return the error code.
    // 0: Cube is solvable
    // -2: Not all 12 edges exist exactly once
    // -3: Flip error: One edge has to be flipped
    // -4: Not all corners exist exactly once
    // -5: Twist error: One corner has to be twisted
    // -6: Parity error: Two corners ore two edges have to be exchanged
    private int verify() {
        int sum = 0;
        int[] edgeCount = new int[12];
        for (RubikCubeModelEdge e : RubikCubeModelEdge.values())
            edgeCount[ep[e.ordinal()].ordinal()]++;
        for (int i = 0; i < 12; i++)
            if (edgeCount[i] != 1)
                return -2;

        for (int i = 0; i < 12; i++)
            sum += eo[i];
        if (sum % 2 != 0)
            return -3;

        int[] cornerCount = new int[8];
        for (RubikCubeModelCorner c : RubikCubeModelCorner.values())
            cornerCount[cp[c.ordinal()].ordinal()]++;
        for (int i = 0; i < 8; i++)
            if (cornerCount[i] != 1)
                return -4;// missing corners

        sum = 0;
        for (int i = 0; i < 8; i++)
            sum += co[i];
        if (sum % 3 != 0)
            return -5;// twisted corner

        if ((edgeParity() ^ cornerParity()) != 0)
            return -6;// parity error

        return 0;// cube ok
    }
}

