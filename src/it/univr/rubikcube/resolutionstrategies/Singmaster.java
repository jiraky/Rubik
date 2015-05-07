package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeCorner;
import it.univr.rubikcube.model.RubikCubeCornerColor;
import it.univr.rubikcube.model.RubikCubeEdgeColor;
import it.univr.rubikcube.model.RubikCubeFaceColor;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.RubikCubeModel3Edge;
import it.univr.rubikcube.model.RubikCubeModelAxis;
import it.univr.rubikcube.model.RubikCubeModelCorner;
import it.univr.rubikcube.model.RubikCubeSide;
import it.univr.rubikcube.moves.D;
import it.univr.rubikcube.moves.F;
import it.univr.rubikcube.moves.R;
import it.univr.rubikcube.moves.X;
import it.univr.rubikcube.moves.Y;
import it.univr.rubikcube.moves.Z;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.sun.prism.PhongMaterial.MapType;

/**
 * Singmaster resolution strategy.
 * @author Alessandro Menti
 * @see http://robertovillari.eu/rubik/default.htm
 */
public class Singmaster extends ResolutionStrategy {
    /**
     * Creates a new instance of the Singmaster method.
     * @param m Rubik cube model.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three, if the cube has not nine faces per color, if there
     * isn't a single facelet per color.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public Singmaster(final RubikCubeModel m)
            throws IllegalArgumentException, NullPointerException {
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
        final List<Move> listMoves = new ArrayList<Move>();
        // Create a copy of the current model so that the original one remains
        // untouched
        final RubikCubeModel m = new RubikCubeModel(this.getModel());
        this.stepOne(listMoves, m);
        this.stepTwo(listMoves, m);
        this.stepThree(listMoves, m);
        return listMoves;
    }
    /**
     * Gets the name of this method.
     * @return <tt>Singmaster</tt>
     */
    @Override
    public final String toString() {
        return "Singmaster";
    }
    /**
     * Gets a textual description of this method.
     * @return <tt>Singmaster's method solves the cube layer by layer.</tt>
     */
    @Override
    public final String getDescription() {
        return "Singmaster's method solves the cube layer by layer.";
    }
    /**
     * Perform step 1 of the method.
     * @param listMoves List of moves.
     * @param m Cube model.
     */
    private void stepOne(final List<Move> listMoves, final RubikCubeModel m) {
        Move currentMove;
        // Move the white side up.
        if (m.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE) {
            currentMove = new X(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
        } else if (m.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.WHITE) {
            currentMove = new X(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new X(m);
            listMoves.add(currentMove);
            currentMove.perform();
        } else if (m.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.WHITE) {
            currentMove = new X(m);
            listMoves.add(currentMove);
            currentMove.perform();
        } else if (m.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.WHITE) {
            currentMove = new Z(m);
            listMoves.add(currentMove);
            currentMove.perform();
        } else if (m.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.WHITE) {
            currentMove = new Z(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
        }
        // For each edge in the first layer, check if it has a white face and
        // is not positioned correctly. If it does:
        boolean edgesPositioned;
        do {
            edgesPositioned = true;
            for (RubikCubeModel3Edge ev: RubikCubeModel3Edge.values()) {
                RubikCubeModel3Edge e = ev;
                final RubikCubeEdgeColor ec = m.get3DEdge(e);
                if (ec.getFirstColor() != RubikCubeFaceColor.WHITE
                        && ec.getSecondColor() != RubikCubeFaceColor.WHITE) {
                    continue;
                }
                if ((e == RubikCubeModel3Edge.UB || e == RubikCubeModel3Edge.UF
                        || e == RubikCubeModel3Edge.UL || e == RubikCubeModel3Edge.UR)
                        && ec.getSecondColor() == m.getFace(e.getLateralSide(), 1, 1)) {
                    continue;
                }
                edgesPositioned = false;
                // - perform Y to put the piece (ax) on the front face (it needs to be in UF, DF, FR);
                if (e == RubikCubeModel3Edge.UB || e == RubikCubeModel3Edge.DB || e == RubikCubeModel3Edge.BL) {
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (e == RubikCubeModel3Edge.UL || e == RubikCubeModel3Edge.DL) {
                    currentMove = new Y(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (e == RubikCubeModel3Edge.UR || e == RubikCubeModel3Edge.BR || e == RubikCubeModel3Edge.DR) {
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
                if (e == RubikCubeModel3Edge.UB || e == RubikCubeModel3Edge.UL || e == RubikCubeModel3Edge.UR) {
                    e = RubikCubeModel3Edge.UF;
                } else if (e == RubikCubeModel3Edge.DB || e == RubikCubeModel3Edge.DL || e == RubikCubeModel3Edge.DR) {
                    e = RubikCubeModel3Edge.DF;
                } else if (e == RubikCubeModel3Edge.BL || e == RubikCubeModel3Edge.BR) {
                    e = RubikCubeModel3Edge.FR;
                }
                // - if ax is in the first row, perform F2 to move it to D;
                // - if ax is in the second row, apply F D' F' D.
                if (e == RubikCubeModel3Edge.UF) {
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (e == RubikCubeModel3Edge.FR) {
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
                e = RubikCubeModel3Edge.DF;
                // Rotate D to make the edge match the standard facelet.
                final RubikCubeFaceColor centerFacelet;
                if (m.get3DEdge(e).getFirstColor() == RubikCubeFaceColor.WHITE) {
                    centerFacelet = m.get3DEdge(e).getSecondColor();
                } else {
                    centerFacelet = m.get3DEdge(e).getFirstColor();
                }
                if (centerFacelet == m.getFace(RubikCubeSide.RIGHT, 1, 1)) {
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (centerFacelet == m.getFace(RubikCubeSide.BACK, 1, 1)) {
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (centerFacelet == m.getFace(RubikCubeSide.LEFT, 1, 1)) {
                    currentMove = new Y(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
                // - If white is on D, apply F2;
                // - else apply D R F' R'.
                if (m.get3DEdge(e).getFirstColor() == RubikCubeFaceColor.WHITE) {
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else {
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
            }
        } while (!edgesPositioned);
    }
    /**
     * Perform step 2 of the method.
     * @param listMoves List of moves.
     * @param m Cube model.
     */
    private void stepTwo(final List<Move> listMoves, final RubikCubeModel m) {
        Move currentMove;
        // Get an incorrectly placed corner having a white side.
        boolean cornersPositioned;
        do {
            cornersPositioned = true;
            for (RubikCubeCorner cv: RubikCubeCorner.values()) {
                RubikCubeCorner c = cv;
                final RubikCubeCornerColor cc = m.getCorner(c);
                if (cc.getFirstColor() != RubikCubeFaceColor.WHITE
                        && cc.getSecondColor() != RubikCubeFaceColor.WHITE
                        && cc.getThirdColor() != RubikCubeFaceColor.WHITE) {
                    continue;
                }
                if ((c == RubikCubeCorner.UBR || c == RubikCubeCorner.UFL || c == RubikCubeCorner.ULB || c == RubikCubeCorner.URF)
                        && RubikCubeModel.isCornerInPlace(m, c)) {
                    continue;
                }
                cornersPositioned = false;
                // Move the piece to URF or DRF. If the corner is in URF but is
                // incorrectly positioned, apply F D F' D' to bring it to DRF.
                switch (c) {
                    case ULB:
                    case DBL:
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        break;
                    case DRB:
                    case UBR:
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        break;
                    case DLF:
                    case UFL:
                        currentMove = new Y(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        break;
                    case DFR:
                    case URF:
                    default:
                        break;
                }
                if (c == RubikCubeCorner.ULB || c == RubikCubeCorner.UBR || c == RubikCubeCorner.UFL || c == RubikCubeCorner.URF) {
                    c = RubikCubeCorner.URF;
                } else {
                    c = RubikCubeCorner.DFR;
                }
                if (c == RubikCubeCorner.URF) {
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    c = RubikCubeCorner.DFR;
                }
                // Rotate D until U, R and D have the same colors as the corner.
                while (!RubikCubeModel.isCornerInPlaceMaybeFlipped(m, c)) {
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
                // If the bottom facelet is white, perform R' D2 R D.
                if (m.getCorner(c).getFirstColor() == RubikCubeFaceColor.WHITE) {
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
                // If white is on the right facelet, perform R' D' R, else if white is in the front facelet, perform D' R' D R.
                if (m.getCorner(c).getThirdColor() == RubikCubeFaceColor.WHITE) {
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (m.getCorner(c).getSecondColor() == RubikCubeFaceColor.WHITE) {
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
            }
        } while (!cornersPositioned);
    }
    /**
     * Perform step 3 of the method.
     * @param listMoves List of moves.
     * @param m Cube model.
     */
    private void stepThree(final List<Move> listMoves, final RubikCubeModel m) {
        Move currentMove;
        // While the edges in the 2nd layer are not positioned:
        RubikCubeModel3Edge edgeToFix;
        do {
            edgeToFix = null;
            for (RubikCubeModel3Edge ev: new RubikCubeModel3Edge[]{RubikCubeModel3Edge.FL, RubikCubeModel3Edge.FR, RubikCubeModel3Edge.BL, RubikCubeModel3Edge.BR}) {
                if (!RubikCubeModel.isEdgeInPlace(m, ev)) {
                    edgeToFix = ev;
                }
            }
            if (edgeToFix != null) {
                // Get an edge (in the 2nd or 3rd layer) that is not partially yellow and bring it to the front face.
                RubikCubeModel3Edge moveToFront;
            }
        } while (edgeToFix != null);
        
        
        // - If the edge is correctly positioned, do nothing.
        // - If it's not and it's in the 2nd layer, choose an edge in the 3rd layer and apply the move below.
        // If xy is on B, let x be the color of the bottom facelet:
        // * Ruotare il Cubo sul proprio asse verticale fino a vedere il centro di colore x sulla faccia F, mantenendo la faccia A in alto.
        // * Ruotare B per posizionare xy sulla faccia R (in posizione BR).
        // * Se y è il colore della faccia S, applicare F' B' F B S B S'
        // * Se y è il colore della faccia D, applicare F B F' B' D' B' D
    }
}
