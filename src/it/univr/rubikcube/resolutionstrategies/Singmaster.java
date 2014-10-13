package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.CubeRotation;
import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeCorner;
import it.univr.rubikcube.model.RubikCubeCornerColor;
import it.univr.rubikcube.model.RubikCubeEdgeColor;
import it.univr.rubikcube.model.RubikCubeFaceColor;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.RubikCubeModel3Edge;
import it.univr.rubikcube.model.RubikCubeSide;
import it.univr.rubikcube.moves.B;
import it.univr.rubikcube.moves.D;
import it.univr.rubikcube.moves.F;
import it.univr.rubikcube.moves.L;
import it.univr.rubikcube.moves.R;
import it.univr.rubikcube.moves.U;
import it.univr.rubikcube.moves.X;
import it.univr.rubikcube.moves.Y;
import it.univr.rubikcube.moves.Z;
import java.lang.management.MemoryType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Singmaster resolution strategy.
 * @author Alessandro Menti
 * @see http://www.cs.swarthmore.edu/~knerr/helps/rcube.html
 */
public class Singmaster extends ResolutionStrategy {
    /**
     * Creates a new instance of the Kociemba method.
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
        final List<Move> listMoves = new ArrayList<>();
        Move currentMove;
        // Get a clone of the existing cube model so that we'll be able to
        // perform any required moves without altering the existing one.
        final RubikCubeModel m = new RubikCubeModel(this.getModel());
        // Check if the green cross is solved: get the green central facelet
        // and check the other relevant facelets on the same side (plus the
        // edges).
        final RubikCubeSide greenSide = m.getSide(RubikCubeFaceColor.GREEN);
        if (greenSide == null) {
            throw new NoSolutionException("Malformed cube, no green side"
                    + " found");
        }
        final RubikCubeModel mGreenUp = new RubikCubeModel(m);
        moveGreenSideUp(mGreenUp);
        if (!areCrossAndEdgesDoneOnFaceUp(mGreenUp, RubikCubeFaceColor.GREEN)) {
            // Move the green side up
            listMoves.addAll(moveGreenSideUp(m));
            // Until the green cross on the top is formed and the edges are done...
            while (!areCrossAndEdgesDoneOnFaceUp(m, RubikCubeFaceColor.GREEN)) {
                // ...find a green edge piece that is not already in place...
                RubikCubeEdgeColor c;
                RubikCubeModel3Edge edge = null;
                for (RubikCubeModel3Edge e : RubikCubeModel3Edge.values()) {
                    edge = e;
                    c = m.get3DEdge(e);
                    if (c.getFirstColor() != RubikCubeFaceColor.GREEN
                            && c.getSecondColor() != RubikCubeFaceColor.GREEN) {
                        continue;
                    }
                    if (c.getFirstColor() == RubikCubeFaceColor.GREEN) {
                        if (e == RubikCubeModel3Edge.UB
                                && m.getFace(RubikCubeSide.BACK, 1, 1)
                                == c.getSecondColor()) {
                            continue;
                        }
                        if (e == RubikCubeModel3Edge.UF
                                && m.getFace(RubikCubeSide.FRONT, 1, 1)
                                == c.getSecondColor()) {
                            continue;
                        }
                        if (e == RubikCubeModel3Edge.UL
                                && m.getFace(RubikCubeSide.LEFT, 1, 1)
                                == c.getSecondColor()) {
                            continue;
                        }
                        if (e == RubikCubeModel3Edge.UR
                                && m.getFace(RubikCubeSide.RIGHT, 1, 1)
                                == c.getSecondColor()) {
                            continue;
                        }
                    }
                    break;
                }
                if (edge == null) {
                    // FIXME Just need to perform an equator move to get the edges right
                }
                // ...move it to the down side...
                RubikCubeModel3Edge edgeOnDown;
                RubikCubeSide lateralSide;
                switch (edge) {
                    case UR:
                        currentMove = new R(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new R(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DR;
                        lateralSide = RubikCubeSide.RIGHT;
                        break;
                    case UL:
                        currentMove = new L(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new L(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DL;
                        lateralSide = RubikCubeSide.LEFT;
                        break;
                    case UF:
                        currentMove = new F(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new F(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DF;
                        lateralSide = RubikCubeSide.FRONT;
                        break;
                    case UB:
                        currentMove = new B(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new B(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DB;
                        lateralSide = RubikCubeSide.BACK;
                        break;
                    case DR:
                        lateralSide = RubikCubeSide.RIGHT;
                        edgeOnDown = edge;
                        break;
                    case DF:
                        lateralSide = RubikCubeSide.FRONT;
                        edgeOnDown = edge;
                        break;
                    case DL:
                        lateralSide = RubikCubeSide.LEFT;
                        edgeOnDown = edge;
                        break;
                    case DB:
                        lateralSide = RubikCubeSide.BACK;
                        edgeOnDown = edge;
                        break;
                    case FR:
                        currentMove = new F(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DF;
                        lateralSide = RubikCubeSide.FRONT;
                        break;
                    case FL:
                        currentMove = new F(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DF;
                        lateralSide = RubikCubeSide.FRONT;
                        break;
                    case BL:
                        currentMove = new B(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DB;
                        lateralSide = RubikCubeSide.BACK;
                        break;
                    case BR:
                        currentMove = new B(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        edgeOnDown = RubikCubeModel3Edge.DB;
                        lateralSide = RubikCubeSide.BACK;
                        break;
                    default:
                        throw new NoSolutionException("Green edge in unforeseen position");
                }
                // ...rotate the down side until the other color on the edge
                // matches the central facelet...
                RubikCubeEdgeColor downEdgeColor = m.get3DEdge(edgeOnDown);
                RubikCubeFaceColor downEdgeOtherColor = downEdgeColor.getFirstColor();
                int numberOfDownRotations = 0;
                if (downEdgeOtherColor == RubikCubeFaceColor.GREEN) {
                    downEdgeOtherColor = downEdgeColor.getSecondColor();
                }
                while (downEdgeOtherColor != m.getFace(lateralSide, 1, 1)) {
                    if (edgeOnDown == RubikCubeModel3Edge.DF) {
                        edgeOnDown = RubikCubeModel3Edge.DR;
                    } else if (edgeOnDown == RubikCubeModel3Edge.DR) {
                        edgeOnDown = RubikCubeModel3Edge.DB;
                    } else if (edgeOnDown == RubikCubeModel3Edge.DB) {
                        edgeOnDown = RubikCubeModel3Edge.DL;
                    } else {
                        edgeOnDown = RubikCubeModel3Edge.DF;
                    }
                    ++numberOfDownRotations;
                    downEdgeColor = m.get3DEdge(edgeOnDown);
                    downEdgeOtherColor = downEdgeColor.getFirstColor();
                    if (downEdgeOtherColor == RubikCubeFaceColor.GREEN) {
                        downEdgeOtherColor = downEdgeColor.getSecondColor();
                    }
                }
                // This optimization avoids using three clockwise rotations
                // when the right center facelet is on the left of the
                // original position of the edge.
                if (numberOfDownRotations == 1) {
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (numberOfDownRotations == 2) {
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (numberOfDownRotations == 3) {
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
                // ...and twist the side 180 degrees to get the green edge on
                // the green side: if it's flipped, perform F' U L' U' (or
                // the equivalent move in case we're not working on the front
                // face)...
                if (edgeOnDown == RubikCubeModel3Edge.DF) {
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    if (m.getFace(RubikCubeSide.UP, 2, 1) != RubikCubeFaceColor.GREEN) {
                        currentMove = new F(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new L(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                } else if (edgeOnDown == RubikCubeModel3Edge.DR) {
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    if (m.getFace(RubikCubeSide.UP, 1, 2) != RubikCubeFaceColor.GREEN) {
                        currentMove = new R(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new F(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                } else if (edgeOnDown == RubikCubeModel3Edge.DB) {
                    currentMove = new B(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new B(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    if (m.getFace(RubikCubeSide.UP, 0, 1) != RubikCubeFaceColor.GREEN) {
                        currentMove = new B(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new R(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                } else {
                    currentMove = new L(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new L(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    if (m.getFace(RubikCubeSide.UP, 1, 0) != RubikCubeFaceColor.GREEN) {
                        currentMove = new L(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new B(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                }
                // ...and then move on to the next edge.
            }
        }
        // Check the first layer green side corners: rotate a clone of the
        // model and check the corners.
        final RubikCubeModel mClone = new RubikCubeModel(m);
        moveGreenSideUp(mClone);
        if (!RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.UFL)
                || !RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.URF)
                || !RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.UBR)
                || !RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.ULB)) {
            // Rotate the cube to get the green side up, if necessary.
            listMoves.addAll(moveGreenSideUp(m));
            // Fix the green side corners:
            do {
                // * find a green corner that is not already in place
                RubikCubeCorner foundGreenCorner = null;
                RubikCubeCornerColor cornerColor;
                for (RubikCubeCorner c : RubikCubeCorner.values()) {
                    cornerColor = m.getCorner(c);
                    if (!RubikCubeModel.isCornerInPlace(m, c)
                            && (cornerColor.getFirstColor() == RubikCubeFaceColor.GREEN
                            || cornerColor.getSecondColor() == RubikCubeFaceColor.GREEN
                            | cornerColor.getThirdColor() == RubikCubeFaceColor.GREEN)) {
                        foundGreenCorner = c;
                        break;
                    }
                }
                if (foundGreenCorner == null) {
                    throw new NoSolutionException("No green corners found");
                }
                // * get it directly under where it should go (match the other two
                //   colors on the corner piece to the other two side colors).
                //   As corners remain in corner positions:
                //   - if it's in the top row but it's wrongly placed, perform
                //     R' D' R (for the UFR case) or a similar move, then the cube
                //     will be moved to the bottom row;
                //   - if it's in the bottom row, just rotate the bottom row (D)
                //     until the central facelets match, then rotate the cube
                //     so that the other colors of the angle are on the front and
                //     right sides.
                // First, move the corner to DFR.
                if (foundGreenCorner.getUpDownSide() == RubikCubeSide.UP) {
                    switch (foundGreenCorner.getLeftSide()) {
                        case FRONT:
                            // Nothing to do
                            break;
                        case RIGHT:
                            currentMove = new Y(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        case BACK:
                            currentMove = new Y(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            currentMove = new Y(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        case LEFT:
                            currentMove = new Y(m, true);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        default:
                            throw new NoSolutionException("Left side on top or bottom");
                    }
                    // FIXME If on left...?
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    foundGreenCorner = RubikCubeCorner.DFR;
                }
                // * Do R' D' R D 1-6 times (keeping the same face forward at
                //   all times) until the facelets are oriented correctly
                switch (foundGreenCorner.getLeftSide()) {
                    case FRONT:
                        for (int i = 0; i < 6; ++i) {
                            currentMove = new R(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new R(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            if (m.getFace(RubikCubeSide.FRONT, 0, 2) == m.getFace(RubikCubeSide.FRONT, 1, 1)
                                    && m.getFace(RubikCubeSide.RIGHT, 0, 0) == m.getFace(RubikCubeSide.RIGHT, 1, 1)
                                    && m.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.GREEN) {
                                break;
                            }
                        }
                        break;
                    case RIGHT:
                        for (int i = 0; i < 6; ++i) {
                            currentMove = new B(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new B(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            if (m.getFace(RubikCubeSide.BACK, 0, 0) == m.getFace(RubikCubeSide.BACK, 1, 1)
                                    && m.getFace(RubikCubeSide.RIGHT, 0, 2) == m.getFace(RubikCubeSide.RIGHT, 1, 1)
                                    && m.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.GREEN) {
                                break;
                            }
                        }
                        break;
                    case BACK:
                        for (int i = 0; i < 6; ++i) {
                            currentMove = new L(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new L(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            if (m.getFace(RubikCubeSide.BACK, 0, 2) == m.getFace(RubikCubeSide.BACK, 1, 1)
                                    && m.getFace(RubikCubeSide.LEFT, 0, 0) == m.getFace(RubikCubeSide.LEFT, 1, 1)
                                    && m.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.GREEN) {
                                break;
                            }
                        }
                        break;
                    case LEFT:
                        for (int i = 0; i < 6; ++i) {
                            currentMove = new F(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m, true);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new F(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            currentMove = new D(m);
                            currentMove.perform();
                            listMoves.add(currentMove);
                            if (m.getFace(RubikCubeSide.LEFT, 0, 2) == m.getFace(RubikCubeSide.LEFT, 1, 1)
                                    && m.getFace(RubikCubeSide.FRONT, 0, 0) == m.getFace(RubikCubeSide.FRONT, 1, 1)
                                    && m.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.GREEN) {
                                break;
                            }
                        }
                        break;
                    default:
                        throw new NoSolutionException("The corner is not where we expect it to be");
                }
                // * Repeat the algorithm until all corners are in place
            } while (!RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.UFL)
                    || !RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.URF)
                    || !RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.UBR)
                    || !RubikCubeModel.isCornerInPlace(mClone, RubikCubeCorner.ULB));
        }
        // Check if the second layer is solved
        if (!isSecondLayerSolved(m)) {
            // Move the green side down
            listMoves.addAll(moveGreenSideDown(m));
            // Until the second layer is done...
            while (!isSecondLayerSolved(m)) {
                // * Find an edge piece without blue in it, if there is one.
                RubikCubeModel3Edge edgeWithoutBlue = null;
                RubikCubeEdgeColor tmp = m.get3DEdge(RubikCubeModel3Edge.UF);
                if (tmp.getFirstColor() != RubikCubeFaceColor.BLUE && tmp.getSecondColor() != RubikCubeFaceColor.BLUE) {
                    edgeWithoutBlue = RubikCubeModel3Edge.UF;
                } else {
                    tmp = m.get3DEdge(RubikCubeModel3Edge.UR);
                    if (tmp.getFirstColor() != RubikCubeFaceColor.BLUE && tmp.getSecondColor() != RubikCubeFaceColor.BLUE) {
                        edgeWithoutBlue = RubikCubeModel3Edge.UR;
                    } else {
                        tmp = m.get3DEdge(RubikCubeModel3Edge.UB);
                        if (tmp.getFirstColor() != RubikCubeFaceColor.BLUE && tmp.getSecondColor() != RubikCubeFaceColor.BLUE) {
                            edgeWithoutBlue = RubikCubeModel3Edge.UB;
                        } else {
                            tmp = m.get3DEdge(RubikCubeModel3Edge.UL);
                            if (tmp.getFirstColor() != RubikCubeFaceColor.BLUE && tmp.getSecondColor() != RubikCubeFaceColor.BLUE) {
                                edgeWithoutBlue = RubikCubeModel3Edge.UL;
                            }
                        }
                    }
                }
                if (edgeWithoutBlue != null) {
                    // twist the top blue side so edge piece matches side color
                    final RubikCubeFaceColor edgeColor = m.get3DEdge(edgeWithoutBlue).getSecondColor();
                    int numberOfRotations = 0;
                    RubikCubeSide edgeSide = edgeWithoutBlue.getLateralSide();
                    while (m.getFace(edgeSide, 1, 1) != edgeColor) {
                        ++numberOfRotations;
                        switch (edgeSide) {
                            case LEFT:
                                edgeSide = RubikCubeSide.FRONT;
                                break;
                            case FRONT:
                                edgeSide = RubikCubeSide.RIGHT;
                                break;
                            case RIGHT:
                                edgeSide = RubikCubeSide.BACK;
                                break;
                            case BACK:
                                edgeSide = RubikCubeSide.LEFT;
                                break;
                            default:
                                throw new NoSolutionException("Edge side in an unconsistent state");
                        }
                    }
                    switch (numberOfRotations) {
                        case 3:
                            currentMove = new U(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        case 2:
                            currentMove = new U(m, true);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            // fall through
                        case 1:
                            currentMove = new U(m, true);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        case 0:
                        default:
                            // Nothing to do
                            break;
                    }
                    // Put that piece front and center (rotate the cube)
                    switch (edgeSide) {
                        case LEFT:
                            currentMove = new Y(m, true);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        case BACK:
                            currentMove = new Y(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            // fall through
                        case RIGHT:
                            currentMove = new Y(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        case FRONT:
                        default:
                            // Nothing to do
                            break;
                    }
                    if (m.get3DEdge(RubikCubeModel3Edge.UF).getFirstColor()
                            == m.getFace(RubikCubeSide.RIGHT, 1, 1)) {
                        // if top matches right side: U R U' R' U' F' U F
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new R(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new R(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new F(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new F(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    } else {
                        // if top matches left side: U' L' U L U F U' F'
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new L(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new L(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new F(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new F(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                } else {
                    // Find an edge without blue in the middle layer (better if
                    // it's not in place)
                    RubikCubeModel3Edge edgeInMiddle = null;
                    for (RubikCubeModel3Edge e : new RubikCubeModel3Edge[]
                            {RubikCubeModel3Edge.FR, RubikCubeModel3Edge.BR,
                             RubikCubeModel3Edge.BL, RubikCubeModel3Edge.FL, }) {
                        if (m.get3DEdge(e).getFirstColor() == RubikCubeFaceColor.BLUE
                                || m.get3DEdge(e).getSecondColor() == RubikCubeFaceColor.BLUE) {
                            continue;
                        }
                        if (RubikCubeModel.isEdgeInPlace(m, e)) {
                            if (edgeInMiddle == null) {
                                // TODO: is this acceptable (even if the edge is in place)?
                                edgeInMiddle = e;
                            }
                        } else {
                            edgeInMiddle = e;
                        }
                    }
                    if (edgeInMiddle == null) {
                        throw new NoSolutionException("Must have a suitable edge in middle");
                    }
                    // ...put it in the FR edge by rotating the cube...
                    switch (edgeInMiddle) {
                        case FR:
                            // Nothing to do
                            break;
                        case BL:
                            currentMove = new Y(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            // fall through
                        case BR:
                            currentMove = new Y(m);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        case FL:
                            currentMove = new Y(m, true);
                            listMoves.add(currentMove);
                            currentMove.perform();
                            break;
                        default:
                            throw new NoSolutionException("Edge seems not to be in the middle layer");
                    }
                    // ...then perform U R U' R' U' F' U F
                    currentMove = new U(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new U(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new U(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new U(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new F(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
            }
        }
        // Check if the blue cross is solved
        final RubikCubeModel mBlueClone = new RubikCubeModel(m);
        moveGreenSideDown(mBlueClone);
        if (!isBlueCrossSolved(mBlueClone)) {
            // Rotate the blue layer on the top
            listMoves.addAll(moveGreenSideDown(m));
            // Look at the top blue layer and check for a small L. If it is
            // there, make it top back left.
            // Since in each case one blue face must be in (1,0) or (1,2),
            // just check those facelets.
            if (m.getFace(RubikCubeSide.UP, 1, 0) != RubikCubeFaceColor.BLUE
                    && m.getFace(RubikCubeSide.UP, 1, 2) != RubikCubeFaceColor.BLUE) {
                // Do F R U R' U' F' to get small L
                currentMove = new F(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new F(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
            }
            // Search for the small L and make it top back left
            boolean foundBlueSmallL = false;
            if (!foundBlueSmallL
                    && m.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.BLUE) {
                if (m.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.BLUE) {
                    // Top right
                    foundBlueSmallL = true;
                    currentMove = new Y(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (m.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE) {
                    // Right bottom
                    foundBlueSmallL = true;
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
            }
            if (!foundBlueSmallL
                    && m.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE) {
                // Left bottom
                foundBlueSmallL = true;
                currentMove = new Y(m);
                listMoves.add(currentMove);
                currentMove.perform();
            }
            // Do F R U R' U' F'
            currentMove = new F(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new R(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new U(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new R(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new U(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new F(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
            // Make the three pieces in a row go left to right across the top
            if (m.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.BLUE) {
                currentMove = new Y(m);
                listMoves.add(currentMove);
                currentMove.perform();
            }
            // and again do F R U R' U' F' to get blue cross
            currentMove = new F(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new R(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new U(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new R(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new U(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new F(m, true);
            listMoves.add(currentMove);
            currentMove.perform();
            if (!twistBlueSideTwoEdges(m, listMoves)) {
                // Can't get two edges lined up, do R U R' U R 2U R'
                currentMove = new R(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                // FIXME - and check again?
                twistBlueSideTwoEdges(m, listMoves);
            }
            // The blue cross is on top, twist the top to get the pieces lined up
            // Might need to do it more than once.
            int matchingEdges = 0;
            do {
                final RubikCubeModel mCloneTop = new RubikCubeModel(m);
                int i;
                for (i = 0; i < 4; ++i) {
                    matchingEdges = 0;
                    for (RubikCubeSide s : new RubikCubeSide[]{RubikCubeSide.FRONT, RubikCubeSide.RIGHT, RubikCubeSide.BACK, RubikCubeSide.LEFT, }) {
                        if (m.getFace(s, 0, 1) == m.getFace(s, 1, 1)) {
                            ++matchingEdges;
                        }
                    }
                    if (matchingEdges == 4) {
                        break;
                    }
                    new U(mCloneTop, true).perform();
                }
                switch (i) {
                    case 3:
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        break;
                    case 2:
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        // fall through
                    case 1:
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        break;
                    case 0:
                    default:
                        // Nothing to do
                        break;
                }
            } while (matchingEdges != 4);
        }
        // Check the final four corners on the blue side
        int correctCorners;
        do {
            final boolean[] flippedCorners = new boolean[4];
            final RubikCubeCorner[] cornersToCheck = new RubikCubeCorner[] {RubikCubeCorner.URF, RubikCubeCorner.UFL, RubikCubeCorner.ULB, RubikCubeCorner.UBR, };
            correctCorners = 0;
            for (RubikCubeCorner c : cornersToCheck) {
                if (RubikCubeModel.isCornerInPlace(m, c)) {
                    ++correctCorners;
                    continue;
                }
                if (RubikCubeModel.isCornerInPlaceMaybeFlipped(m, c)) {
                    flippedCorners[c.ordinal()] = true;
                    ++correctCorners;
                }
            }
            // Check the number of corners in their correct locations
            switch (correctCorners) {
                case 1:
                    // If 1: put the corner as upper right of front face...
                    int flippedCorner;
                    for (flippedCorner = 0; flippedCorner < 4; ++flippedCorner) {
                        if (flippedCorners[flippedCorner]) {
                            break;
                        }
                    }
                    if (flippedCorner == RubikCubeCorner.UFL.ordinal()) {
                        currentMove = new Y(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    } else if (flippedCorner == RubikCubeCorner.ULB.ordinal()) {
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    } else if (flippedCorner == RubikCubeCorner.UBR.ordinal()) {
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                    // ... and perform U R U' L' U R' U' L. This might either get
                    // all four corners correct, or less than four, in which case
                    // we repeat the procedure.
                    // fall through
                case 2:
                    // TODO: check if there is a possible loop here.
                    // Specifically, in case two corners are correct and the two other
                    // ones are flipped, there might be an infinite loop. Could rotating
                    // the cube be a solution?
                case 0:
                case 3:
                    // Do U R U' L' U R' U' L to get at least one corner correct
                    currentMove = new U(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new U(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new L(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new U(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new U(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new L(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    break;
                case 4:
                    // Nothing to do.
                    break;
                default:
                    throw new NoSolutionException("Inconsistent state");
            }
        } while (correctCorners != 4);
        if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.UBR)
                || !RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.UFL)
                || !RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.ULB)
                || !RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.URF)) {
            // If some corners are flipped, put one of the corners in the
            // URF angle.
            if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.UBR)) {
                currentMove = new Y(m);
                listMoves.add(currentMove);
                currentMove.perform();
            } else if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.UFL)) {
                currentMove = new Y(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
            } else if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.ULB)) {
                currentMove = new Y(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new Y(m);
                listMoves.add(currentMove);
                currentMove.perform();
            }
            do {
                // Do R' D' R D twice and see if that corner is flipped correctly
                currentMove = new R(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new D(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new D(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new D(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new D(m);
                listMoves.add(currentMove);
                currentMove.perform();
                if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.URF)) {
                    // If not, do R' D' R D twice again
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new R(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new D(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
                // Turn the up face 90 degrees until we find another suitable
                // corner
                if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.UBR)) {
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.UFL)) {
                    currentMove = new Y(m, true);
                    listMoves.add(currentMove);
                    currentMove.perform();
                } else if (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.ULB)) {
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                    currentMove = new Y(m);
                    listMoves.add(currentMove);
                    currentMove.perform();
                }
            } while (!RubikCubeModel.isCornerInPlace(m, RubikCubeCorner.URF));
        }
        // Twist the top if necessary and the cube is solved
        final RubikCubeFaceColor frontColor = m.getFace(RubikCubeSide.FRONT, 1, 1);
        if (frontColor == m.getFace(RubikCubeSide.LEFT, 0, 1)) {
            currentMove = new Y(m, true);
            listMoves.add(currentMove);
            currentMove.perform();            
        } else if (frontColor == m.getFace(RubikCubeSide.RIGHT, 0, 1)) {
            currentMove = new Y(m);
            listMoves.add(currentMove);
            currentMove.perform();
        } else if (frontColor == m.getFace(RubikCubeSide.BACK, 0, 1)) {
            currentMove = new Y(m);
            listMoves.add(currentMove);
            currentMove.perform();
            currentMove = new Y(m);
            listMoves.add(currentMove);
            currentMove.perform();
        }
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
     * Checks if the cross and the edges on the up face of the cube are done.
     * @param m Model to be checked.
     * @param c Cross color.
     * @return <tt>true</tt> only if the cross and edges are done.
     */
    public static boolean areCrossAndEdgesDoneOnFaceUp(final RubikCubeModel m,
                                                       final RubikCubeFaceColor c) {
       if (m.getFace(RubikCubeSide.UP, 0, 1) != c
           || m.getFace(RubikCubeSide.UP, 1, 0) != c
           || m.getFace(RubikCubeSide.UP, 1, 1) != c
           || m.getFace(RubikCubeSide.UP, 1, 2) != c
           || m.getFace(RubikCubeSide.UP, 2, 1) != c) {
           return false;
       }
       for (RubikCubeSide s: new RubikCubeSide[]{RubikCubeSide.FRONT,
           RubikCubeSide.RIGHT, RubikCubeSide.BACK, RubikCubeSide.LEFT, }) {
           if (m.getFace(s, 0, 1) != m.getFace(s, 1, 1)) {
               return false;
           }
       }
       return true;
   }
    private static boolean isSecondLayerSolved(final RubikCubeModel m) {
        boolean secondLayerSolved = true;
        switch (m.getSide(RubikCubeFaceColor.GREEN)) {
            case UP:
            case DOWN:
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.FRONT, 1, i) != m.getFace(RubikCubeSide.FRONT, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.RIGHT, 1, i) != m.getFace(RubikCubeSide.RIGHT, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.BACK, 1, i) != m.getFace(RubikCubeSide.BACK, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.LEFT, 1, i) != m.getFace(RubikCubeSide.LEFT, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                break;
            case LEFT:
            case RIGHT:
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.FRONT, i, 1) != m.getFace(RubikCubeSide.FRONT, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.DOWN, i, 1) != m.getFace(RubikCubeSide.DOWN, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.BACK, i, 1) != m.getFace(RubikCubeSide.BACK, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.UP, i, 1) != m.getFace(RubikCubeSide.UP, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                break;
            case FRONT:
            case BACK:
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.UP, 1, i) != m.getFace(RubikCubeSide.UP, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.RIGHT, i, 1) != m.getFace(RubikCubeSide.RIGHT, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.DOWN, 1, i) != m.getFace(RubikCubeSide.DOWN, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                for (int i = 0; i < 3; ++i) {
                    if (m.getFace(RubikCubeSide.LEFT, i, 1) != m.getFace(RubikCubeSide.LEFT, 1, 1)) {
                        secondLayerSolved = false;
                    }
                }
                break;
        }
        return secondLayerSolved;
    }
    private static boolean isBlueCrossSolved(final RubikCubeModel m) {
        final RubikCubeSide blueSide = m.getSide(RubikCubeFaceColor.BLUE);
        return m.getFace(blueSide, 0, 1) == RubikCubeFaceColor.BLUE
               && m.getFace(blueSide, 1, 0) == RubikCubeFaceColor.BLUE
               && m.getFace(blueSide, 1, 1) == RubikCubeFaceColor.BLUE
               && m.getFace(blueSide, 1, 2) == RubikCubeFaceColor.BLUE
               && m.getFace(blueSide, 2, 1) == RubikCubeFaceColor.BLUE;
    }
    private static List<Move> moveGreenSideUp(RubikCubeModel m) {
        Move currentMove;
        final List<Move> listMoves = new ArrayList<>();
        switch (m.getSide(RubikCubeFaceColor.GREEN)) {
            case BACK:
                currentMove = new X(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case DOWN:
                currentMove = new X(m);
                listMoves.add(currentMove);
                currentMove.perform();
                // fall through
            case FRONT:
                currentMove = new X(m);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case LEFT:
                currentMove = new Z(m);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case RIGHT:
                currentMove = new Z(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case UP:
            default:
                // Nothing to do
                break;
        }
        return listMoves;
    }
    private static List<Move> moveGreenSideDown(final RubikCubeModel m) {
        Move currentMove;
        final List<Move> listMoves = new ArrayList<>();
        switch (m.getSide(RubikCubeFaceColor.GREEN)) {
            case BACK:
                currentMove = new X(m);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case UP:
                currentMove = new X(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                // fall through
            case FRONT:
                currentMove = new X(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case LEFT:
                currentMove = new Z(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case RIGHT:
                currentMove = new Z(m);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            case DOWN:
            default:
                // Nothing to do
                break;
        }
        return listMoves;
    }
    private static boolean twistBlueSideTwoEdges(RubikCubeModel m, List<Move> listMoves) {
        Move currentMove;
        // Try twisting the blue side so two edge pieces line up with their
        // correct sides.
        int matchingEdges = 0;
        final RubikCubeModel mBlue = new RubikCubeModel(m);
        final RubikCubeSide[] edgesToCheck = new RubikCubeSide[]{RubikCubeSide.FRONT, RubikCubeSide.RIGHT, RubikCubeSide.BACK, RubikCubeSide.LEFT, };
        for (int i = 0; i < 4; ++i) {
            final boolean[] edges = new boolean[4];
            for (int j = 0; j < 4; ++j) {
                edges[i] = false;
            }
            matchingEdges = 0;
            for (RubikCubeSide s : edgesToCheck) {
                if (m.getFace(s, 0, 1) == m.getFace(s, 1, 1)) {
                    ++matchingEdges;
                    edges[s.ordinal()] = true;
                }
            }
            // If we get four lined up, we're lucky. Just rotate the blue side if needed.
            if (matchingEdges == 4) {
                switch (i) {
                    case 2:
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        // fall through
                    case 1:
                        currentMove = new U(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        break;
                    case 3:
                        currentMove = new U(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        break;
                    case 0:
                    default:
                        // Nothing to do
                        break;
                }
                break;
            }
            if (matchingEdges == 2) {
                // Check if the matching edges are across from each other or adjacent.
                if (edges[0] == edges[2]) {
                    // Make the lined-up edges the front and back sides
                    if (!edges[0]) {
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                } else {
                    // If the two lined up are adjacent, make them the right and back sides
                    if (edges[3] && edges[0]) {
                        // Left-front
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    } else if (edges[3] && edges[2]) {
                        // Left-back
                        currentMove = new Y(m);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    } else if (edges[0] && edges[1]) {
                        // Front-right
                        currentMove = new Y(m, true);
                        listMoves.add(currentMove);
                        currentMove.perform();
                    }
                }
                // In each case, do R U R' U R 2U R'
                currentMove = new R(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new U(m);
                listMoves.add(currentMove);
                currentMove.perform();
                currentMove = new R(m, true);
                listMoves.add(currentMove);
                currentMove.perform();
                break;
            }
            // We did not find any matching edges - rotate the top side.
            new U(mBlue).perform();
        }
        return matchingEdges != 0;
    }
}
