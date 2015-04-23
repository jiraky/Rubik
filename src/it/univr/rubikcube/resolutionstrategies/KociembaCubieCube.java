package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.RubikCubeFaceColor;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.RubikCubeModel3Edge;
import it.univr.rubikcube.model.RubikCubeModelCorner;
import it.univr.rubikcube.model.RubikCubeModelFacelet;
import it.univr.rubikcube.model.RubikCubeSide;

/**
 * Cube seen at the "cubie" level in the Kociemba resolution strategy.
 * @author Herbert Kociemba
 * @author Alessandro Menti
 */
public class KociembaCubieCube {
    /**
     * Basic cube moves.
     */
    static KociembaCubieCube[] moveCube = new KociembaCubieCube[6];
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
    private static RubikCubeModel3Edge[] epU = {
        RubikCubeModel3Edge.UB,
        RubikCubeModel3Edge.UR,
        RubikCubeModel3Edge.UF,
        RubikCubeModel3Edge.UL,
        RubikCubeModel3Edge.DR,
        RubikCubeModel3Edge.DF,
        RubikCubeModel3Edge.DL,
        RubikCubeModel3Edge.DB,
        RubikCubeModel3Edge.FR,
        RubikCubeModel3Edge.FL,
        RubikCubeModel3Edge.BL,
        RubikCubeModel3Edge.BR,
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
    private static RubikCubeModel3Edge[] epR = {
        RubikCubeModel3Edge.FR,
        RubikCubeModel3Edge.UF,
        RubikCubeModel3Edge.UL,
        RubikCubeModel3Edge.UB,
        RubikCubeModel3Edge.BR,
        RubikCubeModel3Edge.DF,
        RubikCubeModel3Edge.DL,
        RubikCubeModel3Edge.DB,
        RubikCubeModel3Edge.DR,
        RubikCubeModel3Edge.FL,
        RubikCubeModel3Edge.BL,
        RubikCubeModel3Edge.UR,
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
    private static RubikCubeModel3Edge[] epF = {
        RubikCubeModel3Edge.UR,
        RubikCubeModel3Edge.FL,
        RubikCubeModel3Edge.UL,
        RubikCubeModel3Edge.UB,
        RubikCubeModel3Edge.DR,
        RubikCubeModel3Edge.FR,
        RubikCubeModel3Edge.DL,
        RubikCubeModel3Edge.DB,
        RubikCubeModel3Edge.UF,
        RubikCubeModel3Edge.DF,
        RubikCubeModel3Edge.BL,
        RubikCubeModel3Edge.BR,
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
    private static RubikCubeModel3Edge[] epD = {
        RubikCubeModel3Edge.UR,
        RubikCubeModel3Edge.UF,
        RubikCubeModel3Edge.UL,
        RubikCubeModel3Edge.UB,
        RubikCubeModel3Edge.DF,
        RubikCubeModel3Edge.DL,
        RubikCubeModel3Edge.DB,
        RubikCubeModel3Edge.DR,
        RubikCubeModel3Edge.FR,
        RubikCubeModel3Edge.FL,
        RubikCubeModel3Edge.BL,
        RubikCubeModel3Edge.BR,
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
    private static RubikCubeModel3Edge[] epL = {
        RubikCubeModel3Edge.UR,
        RubikCubeModel3Edge.UF,
        RubikCubeModel3Edge.BL,
        RubikCubeModel3Edge.UB,
        RubikCubeModel3Edge.DR,
        RubikCubeModel3Edge.DF,
        RubikCubeModel3Edge.FL,
        RubikCubeModel3Edge.DB,
        RubikCubeModel3Edge.FR,
        RubikCubeModel3Edge.UL,
        RubikCubeModel3Edge.DL,
        RubikCubeModel3Edge.BR,
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
    private static RubikCubeModel3Edge[] epB = {
        RubikCubeModel3Edge.UR,
        RubikCubeModel3Edge.UF,
        RubikCubeModel3Edge.UL,
        RubikCubeModel3Edge.BR,
        RubikCubeModel3Edge.DR,
        RubikCubeModel3Edge.DF,
        RubikCubeModel3Edge.DL,
        RubikCubeModel3Edge.BL,
        RubikCubeModel3Edge.FR,
        RubikCubeModel3Edge.FL,
        RubikCubeModel3Edge.UB,
        RubikCubeModel3Edge.DB,
    };
    /**
     * Edge orientation on the bottom face.
     */
    private static byte[] eoB = {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1};
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
    private RubikCubeModel3Edge[] ep = {
        RubikCubeModel3Edge.UR,
        RubikCubeModel3Edge.UF,
        RubikCubeModel3Edge.UL,
        RubikCubeModel3Edge.UB,
        RubikCubeModel3Edge.DR,
        RubikCubeModel3Edge.DF,
        RubikCubeModel3Edge.DL,
        RubikCubeModel3Edge.DB,
        RubikCubeModel3Edge.FR,
        RubikCubeModel3Edge.FL,
        RubikCubeModel3Edge.BL,
        RubikCubeModel3Edge.BR,
    };
    /**
     * Edge orientation.
     */
    private byte[] eo = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
    /**
     * Static initializer for the basic moves.
     */
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
                             final RubikCubeModel3Edge[] eperm, final byte[] eor) {
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
    private static int chooseNK(final int n, final int k) {
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
    private static void rotateLeft(RubikCubeModel3Edge[] arr, final int l,
                                   final int r) {
    // CHECKSTYLE:ON
        final RubikCubeModel3Edge temp = arr[l];
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
    private static void rotateRight(RubikCubeModel3Edge[] arr, final int l,
                                    final int r) {
    // CHECKSTYLE:ON
        final RubikCubeModel3Edge temp = arr[r];
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
    final void cornerMultiply(final KociembaCubieCube b) {
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
    final void edgeMultiply(final KociembaCubieCube b) {
        final RubikCubeModel3Edge[] ePerm = new RubikCubeModel3Edge[12];
        final byte[] eOri = new byte[12];
        for (RubikCubeModel3Edge edge : RubikCubeModel3Edge.values()) {
            ePerm[edge.ordinal()] = this.ep[b.ep[edge.ordinal()].ordinal()];
            eOri[edge.ordinal()] = (byte) ((b.eo[edge.ordinal()] + this.eo[b.ep[edge.ordinal()].ordinal()]) % 2);
        }
        for (RubikCubeModel3Edge e : RubikCubeModel3Edge.values()) {
            this.ep[e.ordinal()] = ePerm[e.ordinal()];
            this.eo[e.ordinal()] = eOri[e.ordinal()];
        }
    }
    /**
     * Get the twist of the eight corners.
     * @return The twist of the corners (0 <= twist < 3^7).
     */
    final short getTwist() {
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
    final void setTwist(final short twist) {
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
    final short getFlip() {
        short ret = 0;
        for (int i = RubikCubeModel3Edge.UR.ordinal();
                i < RubikCubeModel3Edge.BR.ordinal(); ++i) {
            ret = (short) (2 * ret + this.eo[i]);
        }
        return ret;
    }
    /**
     * Sets the flip of the twelve edges.
     * @param flip The flip of the edges.
     */
    final void setFlip(final short flip) {
        short flipTmp = flip;
        int flipParity = 0;
        for (int i = RubikCubeModel3Edge.BR.ordinal() - 1;
                i >= RubikCubeModel3Edge.UR.ordinal(); --i) {
            this.eo[i] = (byte) (flipTmp % 2);
            flipParity += this.eo[i];
            flipTmp /= 2;
        }
        this.eo[RubikCubeModel3Edge.BR.ordinal()]
                = (byte) ((2 - flipParity % 2) % 2);
    }
    /**
     * Gets the corner parity for this cube.
     * @return Corner parity for this cube.
     */
    final short cornerParity() {
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
     * Gets the permutation of the UD-slice edges FR, FL, BL and BR.
     * @return The requested permutation.
     */
    final short getFRtoBR() {
        int a = 0;
        int x = 0;
        final RubikCubeModel3Edge[] edge4 = new RubikCubeModel3Edge[4];
        for (int j = RubikCubeModel3Edge.BR.ordinal();
                j >= RubikCubeModel3Edge.UR.ordinal(); --j) {
            if (RubikCubeModel3Edge.FR.ordinal() <= this.ep[j].ordinal()
                    && this.ep[j].ordinal() <= RubikCubeModel3Edge.BR.ordinal()) {
                a += chooseNK(11 - j, x + 1);
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
    /**
     * Permutes the UD-slice edges FR, FL, BL and BR.
     * @param idx The index identifying the permutation.
     */
    final void setFRtoBR(final short idx) {
        int x;
        final RubikCubeModel3Edge[] sliceEdge = {
            RubikCubeModel3Edge.FR,
            RubikCubeModel3Edge.FL,
            RubikCubeModel3Edge.BL,
            RubikCubeModel3Edge.BR,
        };
        final RubikCubeModel3Edge[] otherEdge = {
            RubikCubeModel3Edge.UR,
            RubikCubeModel3Edge.UF,
            RubikCubeModel3Edge.UL,
            RubikCubeModel3Edge.UB,
            RubikCubeModel3Edge.DR,
            RubikCubeModel3Edge.DF,
            RubikCubeModel3Edge.DL,
            RubikCubeModel3Edge.DB,
        };
        // Permutation index
        int b = idx % 24;
        // Combination index
        int a = idx / 24;
        // Invalidate all edges
        for (RubikCubeModel3Edge e : RubikCubeModel3Edge.values()) {
            this.ep[e.ordinal()] = RubikCubeModel3Edge.DB;
        }
        int k;
        for (int j = 1; j < 4; ++j) {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0) {
                rotateRight(sliceEdge, 0, j);
            }
        }
        // Generate combinations and set slice edges
        x = 3;
        for (int j = RubikCubeModel3Edge.UR.ordinal();
                j <= RubikCubeModel3Edge.BR.ordinal(); ++j) {
            if (a - chooseNK(11 - j, x + 1) >= 0) {
                this.ep[j] = sliceEdge[3 - x];
                a -= chooseNK(11 - j, x-- + 1);
            }
        }
        // Set the remaining edges UR..DB
        x = 0;
        for (int j = RubikCubeModel3Edge.UR.ordinal();
                j <= RubikCubeModel3Edge.BR.ordinal(); ++j) {
            if (this.ep[j] == RubikCubeModel3Edge.DB) {
                this.ep[j] = otherEdge[x++];
            }
        }
    }
    /**
     * Gets the permutation of all corners except DBL and DRB.
     * @return The requested permutation.
     */
    final short getURFtoDLF() {
        int a = 0;
        int x = 0;
        final RubikCubeModelCorner[] corner6 = new RubikCubeModelCorner[6];
        // Compute the index a < (8 choose 6) and the corner permutation.
        for (int j = RubikCubeModelCorner.URF.ordinal();
                j <= RubikCubeModelCorner.DRB.ordinal(); ++j) {
            if (this.cp[j].ordinal() <= RubikCubeModelCorner.DLF.ordinal()) {
                a += chooseNK(j, x + 1);
                corner6[x++] = this.cp[j];
            }
        }
        int b = 0;
        // compute the index b < 6! for the permutation in corner6
        for (int j = 5; j > 0; --j) {
            int k = 0;
            while (corner6[j].ordinal() != j) {
                rotateLeft(corner6, 0, j);
                ++k;
            }
            b = (j + 1) * b + k;
        }
        return (short) (720 * a + b);
    }
    /**
     * Permutes all corners except DBL and DRB.
     * @param idx The requested index.
     */
    final void setURFtoDLF(final short idx) {
        int x;
        final RubikCubeModelCorner[] corner6 = {
            RubikCubeModelCorner.URF,
            RubikCubeModelCorner.UFL,
            RubikCubeModelCorner.ULB,
            RubikCubeModelCorner.UBR,
            RubikCubeModelCorner.DFR,
            RubikCubeModelCorner.DLF,
        };
        final RubikCubeModelCorner[] otherCorner = {
            RubikCubeModelCorner.DBL,
            RubikCubeModelCorner.DRB,
        };
        // Permutation
        int b = idx % 720;
        // Combination
        int a = idx / 720;
        // Use DRB to invalidate all corners
        for (RubikCubeModelCorner c : RubikCubeModelCorner.values()) {
            this.cp[c.ordinal()] = RubikCubeModelCorner.DRB;
        }
        // generate permutation from index b
        int k;
        for (int j = 1; j < 6; ++j) {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0) {
                rotateRight(corner6, 0, j);
            }
        }
        // Generate combination and set corners
        x = 5;
        for (int j = RubikCubeModelCorner.DRB.ordinal(); j >= 0; --j) {
            if (a - chooseNK(j, x + 1) >= 0) {
                this.cp[j] = corner6[x];
                a -= chooseNK(j, x-- + 1);
            }
        }
        x = 0;
        for (int j = RubikCubeModelCorner.URF.ordinal();
                j <= RubikCubeModelCorner.DRB.ordinal(); ++j) {
            if (this.cp[j] == RubikCubeModelCorner.DRB) {
                this.cp[j] = otherCorner[x++];
            }
        }
    }
    /**
     * Gets the permutation of the six edges UR, UF, UL, UB, DR, DF.
     * @return The requested permutation.
     */
    final int getURtoDF() {
        int a = 0;
        int x = 0;
        final RubikCubeModel3Edge[] edge6 = new RubikCubeModel3Edge[6];
        // Compute the index a < (12 choose 6) and the edge permutation.
        for (int j = RubikCubeModel3Edge.UR.ordinal();
                j <= RubikCubeModel3Edge.BR.ordinal(); ++j) {
            if (this.ep[j].ordinal() <= RubikCubeModel3Edge.DF.ordinal()) {
                a += chooseNK(j, x + 1);
                edge6[x++] = this.ep[j];
            }
        }
        int b = 0;
        // Compute the index b < 6! for the permutation in edge6
        for (int j = 5; j > 0; --j) {
            int k = 0;
            while (edge6[j].ordinal() != j) {
                rotateLeft(edge6, 0, j);
                k++;
            }
            b = (j + 1) * b + k;
        }
        return 720 * a + b;
    }
    /**
     * Permutes the six edges UR, UF, UL, UB, DR, DF.
     * @param idx The requested index.
     */
    final void setURtoDF(final int idx) {
        int x;
        final RubikCubeModel3Edge[] edge6 = {
            RubikCubeModel3Edge.UR,
            RubikCubeModel3Edge.UF,
            RubikCubeModel3Edge.UL,
            RubikCubeModel3Edge.UB,
            RubikCubeModel3Edge.DR,
            RubikCubeModel3Edge.DF,
        };
        final RubikCubeModel3Edge[] otherEdge = {
            RubikCubeModel3Edge.DL,
            RubikCubeModel3Edge.DB,
            RubikCubeModel3Edge.FR,
            RubikCubeModel3Edge.FL,
            RubikCubeModel3Edge.BL,
            RubikCubeModel3Edge.BR
        };
        int b = idx % 720; // Permutation
        int a = idx / 720; // Combination
        for (RubikCubeModel3Edge e : RubikCubeModel3Edge.values())
            this.ep[e.ordinal()] = RubikCubeModel3Edge.BR;// Use BR to invalidate all edges

        for (int j = 1, k; j < 6; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(edge6, 0, j);
        }
        x = 5;// generate combination and set edges
        for (int j = RubikCubeModel3Edge.BR.ordinal(); j >= 0; j--)
            if (a - chooseNK(j, x + 1) >= 0) {
                this.ep[j] = edge6[x];
                a -= chooseNK(j, x-- + 1);
            }
        x = 0; // set the remaining edges DL..BR
        for (int j = RubikCubeModel3Edge.UR.ordinal(); j <= RubikCubeModel3Edge.BR.ordinal(); j++)
            if (this.ep[j] == RubikCubeModel3Edge.BR)
                this.ep[j] = otherEdge[x++];
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Permutation of the six edges UR,UF,UL,UB,DR,DF
    public static int getURtoDF(short idx1, short idx2) {
        KociembaCubieCube a = new KociembaCubieCube();
        KociembaCubieCube b = new KociembaCubieCube();
        a.setURtoUL(idx1);
        b.setUBtoDF(idx2);
        for (int i = 0; i < 8; i++) {
            if (a.ep[i] != RubikCubeModel3Edge.BR)
                if (b.ep[i] != RubikCubeModel3Edge.BR)// collision
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
        RubikCubeModel3Edge[] edge3 = new RubikCubeModel3Edge[3];
        // compute the index a < (12 choose 3) and the edge permutation.
        for (int j = RubikCubeModel3Edge.UR.ordinal(); j <= RubikCubeModel3Edge.BR.ordinal(); j++)
            if (this.ep[j].ordinal() <= RubikCubeModel3Edge.UL.ordinal()) {
                a += chooseNK(j, x + 1);
                edge3[x++] = this.ep[j];
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
        RubikCubeModel3Edge[] edge3 = {RubikCubeModel3Edge.UR, RubikCubeModel3Edge.UF, RubikCubeModel3Edge.UL};
        int b = idx % 6; // Permutation
        int a = idx / 6; // Combination
        for (RubikCubeModel3Edge e : RubikCubeModel3Edge.values())
            this.ep[e.ordinal()] = RubikCubeModel3Edge.BR;// Use BR to invalidate all edges

        for (int j = 1, k; j < 3; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(edge3, 0, j);
        }
        x = 2;// generate combination and set edges
        for (int j = RubikCubeModel3Edge.BR.ordinal(); j >= 0; j--)
            if (a - chooseNK(j, x + 1) >= 0) {
                this.ep[j] = edge3[x];
                a -= chooseNK(j, x-- + 1);
            }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Permutation of the three edges UB,DR,DF
    short getUBtoDF() {
        int a = 0, x = 0;
        RubikCubeModel3Edge[] edge3 = new RubikCubeModel3Edge[3];
        // compute the index a < (12 choose 3) and the edge permutation.
        for (int j = RubikCubeModel3Edge.UR.ordinal(); j <= RubikCubeModel3Edge.BR.ordinal(); j++)
            if (RubikCubeModel3Edge.UB.ordinal() <= this.ep[j].ordinal() && this.ep[j].ordinal() <= RubikCubeModel3Edge.DF.ordinal()) {
                a += chooseNK(j, x + 1);
                edge3[x++] = this.ep[j];
            }

        int b = 0;
        for (int j = 2; j > 0; j--)// compute the index b < 3! for the
        // permutation in edge3
        {
            int k = 0;
            while (edge3[j].ordinal() != RubikCubeModel3Edge.UB.ordinal() + j) {
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
        RubikCubeModel3Edge[] edge3 = {RubikCubeModel3Edge.UB, RubikCubeModel3Edge.DR, RubikCubeModel3Edge.DF};
        int b = idx % 6; // Permutation
        int a = idx / 6; // Combination
        for (RubikCubeModel3Edge e : RubikCubeModel3Edge.values())
            this.ep[e.ordinal()] = RubikCubeModel3Edge.BR;// Use BR to invalidate all edges

        for (int j = 1, k; j < 3; j++)// generate permutation from index b
        {
            k = b % (j + 1);
            b /= j + 1;
            while (k-- > 0)
                rotateRight(edge3, 0, j);
        }
        x = 2;// generate combination and set edges
        for (int j = RubikCubeModel3Edge.BR.ordinal(); j >= 0; j--)
            if (a - chooseNK(j, x + 1) >= 0) {
                this.ep[j] = edge3[x];
                a -= chooseNK(j, x-- + 1);
            }
    }
    public static final KociembaCubieCube modelToCubieCube(final RubikCubeModel m) {
        final RubikCubeModelFacelet[][] cornerFacelet = {
            {RubikCubeModelFacelet.U9, RubikCubeModelFacelet.R1, RubikCubeModelFacelet.F3, },
            {RubikCubeModelFacelet.U7, RubikCubeModelFacelet.F1, RubikCubeModelFacelet.L3, },
            {RubikCubeModelFacelet.U1, RubikCubeModelFacelet.L1, RubikCubeModelFacelet.B3, },
            {RubikCubeModelFacelet.U3, RubikCubeModelFacelet.B1, RubikCubeModelFacelet.R3, },
            {RubikCubeModelFacelet.D3, RubikCubeModelFacelet.F9, RubikCubeModelFacelet.R7, },
            {RubikCubeModelFacelet.D1, RubikCubeModelFacelet.L9, RubikCubeModelFacelet.F7, },
            {RubikCubeModelFacelet.D7, RubikCubeModelFacelet.B9, RubikCubeModelFacelet.L7, },
            {RubikCubeModelFacelet.D9, RubikCubeModelFacelet.R9, RubikCubeModelFacelet.B7 },
        };
        final RubikCubeModelFacelet[][] edgeFacelet = {
            {RubikCubeModelFacelet.U6, RubikCubeModelFacelet.R2, },
            {RubikCubeModelFacelet.U8, RubikCubeModelFacelet.F2, },
            {RubikCubeModelFacelet.U4, RubikCubeModelFacelet.L2, },
            {RubikCubeModelFacelet.U2, RubikCubeModelFacelet.B2, },
            {RubikCubeModelFacelet.D6, RubikCubeModelFacelet.R8, },
            {RubikCubeModelFacelet.D2, RubikCubeModelFacelet.F8, },
            {RubikCubeModelFacelet.D4, RubikCubeModelFacelet.L8, },
            {RubikCubeModelFacelet.D8, RubikCubeModelFacelet.B8, },
            {RubikCubeModelFacelet.F6, RubikCubeModelFacelet.R4, },
            {RubikCubeModelFacelet.F4, RubikCubeModelFacelet.L6, },
            {RubikCubeModelFacelet.B6, RubikCubeModelFacelet.L4, },
            {RubikCubeModelFacelet.B4, RubikCubeModelFacelet.R6, },
        };
        final RubikCubeFaceColor[][] cornerColor = {
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), RubikCubeSide.FRONT.getStandardColor(), },
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.FRONT.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), },
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), RubikCubeSide.BACK.getStandardColor(), },
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.BACK.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.FRONT.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), RubikCubeSide.FRONT.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.BACK.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), RubikCubeSide.BACK.getStandardColor(), },
        };
        final RubikCubeFaceColor[][] edgeColor = {
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), },
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.FRONT.getStandardColor(), },
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), },
            {RubikCubeSide.UP.getStandardColor(), RubikCubeSide.BACK.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.FRONT.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), },
            {RubikCubeSide.DOWN.getStandardColor(), RubikCubeSide.BACK.getStandardColor(), },
            {RubikCubeSide.FRONT.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), },
            {RubikCubeSide.FRONT.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), },
            {RubikCubeSide.BACK.getStandardColor(), RubikCubeSide.LEFT.getStandardColor(), },
            {RubikCubeSide.BACK.getStandardColor(), RubikCubeSide.RIGHT.getStandardColor(), },
        };
        final KociembaCubieCube ccRet = new KociembaCubieCube();
        byte ori;
        RubikCubeFaceColor col1;
        RubikCubeFaceColor col2;
        // Invalidate corners and edges
        for (int i = 0; i < 8; ++i) {
            ccRet.cp[i] = RubikCubeModelCorner.URF;
        }
        for (int i = 0; i < 12; ++i) {
            ccRet.ep[i] = RubikCubeModel3Edge.UR;
        }
        for (RubikCubeModelCorner i : RubikCubeModelCorner.values()) {
            for (ori = 0; ori < 3; ori++) {
                if (mapModelToKociemba(m, cornerFacelet[i.ordinal()][ori].ordinal()) == RubikCubeSide.UP.getStandardColor()
                        || mapModelToKociemba(m, cornerFacelet[i.ordinal()][ori].ordinal()) == RubikCubeSide.DOWN.getStandardColor()) {
                    break;
                }
            }
            
            col1 = mapModelToKociemba(m, cornerFacelet[i.ordinal()][(ori + 1) % 3].ordinal());
            col2 = mapModelToKociemba(m, cornerFacelet[i.ordinal()][(ori + 2) % 3].ordinal());

            for (RubikCubeModelCorner j : RubikCubeModelCorner.values()) {
                if (col1 == cornerColor[j.ordinal()][1] && col2 == cornerColor[j.ordinal()][2]) {
                    ccRet.cp[i.ordinal()] = j;
                    ccRet.co[i.ordinal()] = (byte) (ori % 3);
                    break;
                }
            }
        }
        for (RubikCubeModel3Edge i : RubikCubeModel3Edge.values()) {
            for (RubikCubeModel3Edge j : RubikCubeModel3Edge.values()) {
                if (mapModelToKociemba(m, edgeFacelet[i.ordinal()][0].ordinal()) == edgeColor[j.ordinal()][0]
                        && mapModelToKociemba(m, edgeFacelet[i.ordinal()][1].ordinal()) == edgeColor[j.ordinal()][1]) {
                    ccRet.ep[i.ordinal()] = j;
                    ccRet.eo[i.ordinal()] = 0;
                    break;
                }
                if (mapModelToKociemba(m, edgeFacelet[i.ordinal()][0].ordinal()) == edgeColor[j.ordinal()][1]
                        && mapModelToKociemba(m, edgeFacelet[i.ordinal()][1].ordinal()) == edgeColor[j.ordinal()][0]) {
                    ccRet.ep[i.ordinal()] = j;
                    ccRet.eo[i.ordinal()] = 1;
                    break;
                }
            }
        }
        return ccRet;
    }
    /**
     * Maps a facelet of the Rubik cube model to the corresponding one in the
     * Kociemba model.
     * @param m Rubik cube model.
     * @param ordinal Kociemba ordinal of the face to be retrieved.
     * @return Color of the desired facelet.
     */
    private static RubikCubeFaceColor mapModelToKociemba(
           final RubikCubeModel m, final int ordinal) {
        // The facelets in the Kociemba model are stored in the
        // following format:
        // UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB
        // See RubikCubeModelFacelet for the face orientation
        // (it's the same we use in our model).
        RubikCubeSide s;
        switch (ordinal / 9) {
            case 0:
                s = RubikCubeSide.UP;
                break;
            case 1:
                s = RubikCubeSide.RIGHT;
                break;
            case 2:
                s = RubikCubeSide.FRONT;
                break;
            case 3:
                s = RubikCubeSide.DOWN;
                break;
            case 4:
                s = RubikCubeSide.LEFT;
                break;
            case 5:
                s = RubikCubeSide.BACK;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return m.getFace(s, (ordinal % 9) / 3, (ordinal % 9) % 3);
    }
}

