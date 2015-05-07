package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeCorner;
import it.univr.rubikcube.model.RubikCubeCornerColor;
import it.univr.rubikcube.model.RubikCubeEdgeColor;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.RubikCubeModel3Edge;
import it.univr.rubikcube.model.RubikCubeModelAxis;
import it.univr.rubikcube.model.RubikCubeSide;
import it.univr.rubikcube.moves.B;
import it.univr.rubikcube.moves.D;
import it.univr.rubikcube.moves.F;
import it.univr.rubikcube.moves.L;
import it.univr.rubikcube.moves.NullMove;
import it.univr.rubikcube.moves.R;
import it.univr.rubikcube.moves.U;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * IDA* resolution strategy inspired by the Uday Bondhugula solver.
 * @author Alessandro Menti
 */
public class IDAStar extends ResolutionStrategy {
    /**
     * Open branches hash map.
     */
    private HashMap<RubikCubeModel, IDAStarNode> openBranches;
    /**
     * Closed branches hash map.
     */
    private HashMap<RubikCubeModel, IDAStarNode> closedBranches;
    /**
     * Priority queue containing the nodes.
     */
    private TreeSet<IDAStarNode> nodesToAnalyze;
    /**
     * Creates a new instance of the IDA* method.
     * @param m Rubik cube model.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three, if the cube has not nine faces per color, if there
     * isn't a single facelet per color.
     */
    public IDAStar(final RubikCubeModel m) throws IllegalArgumentException {
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
        // Set the search threshold and min_exceed.
        float threshold = 5;
        float searchFactor = 1000;
        while (true) {
            // If it's the first time we're running the cycle or there are
            // no more open branches, (re)initialize the search.
            if (this.openBranches == null || this.openBranches.isEmpty()) {
                if (this.openBranches != null) {
                    // Increment the threshold.
                    threshold += searchFactor;
                }
                // Initialize the IDA* "open" and "closed" branches and the priority queue.
                // The comparator will keep the nodes sorted by f.
                this.openBranches = new HashMap<RubikCubeModel, IDAStarNode>();
                this.closedBranches = new HashMap<RubikCubeModel, IDAStarNode>();
                this.nodesToAnalyze = new TreeSet<IDAStarNode>(new IDAStarComparator());
                // Set the current cube configuration as the start node.
                final IDAStarNode initialNode = new IDAStarNode();
                initialNode.depth = 0;
                initialNode.f = initialNode.heuristicCoefficient = getHeuristicCoefficient(this.getModel());
                initialNode.cost = 0;
                initialNode.move = new NullMove(this.getModel());
                initialNode.parent = null;
                this.openBranches.put(this.getModel(), initialNode);
                this.nodesToAnalyze.add(initialNode);
            }
            // Remove the best node from the queue, then remove the branch from the
            // "open" list and add it to the "closed" list.
            final IDAStarNode bestNode = this.nodesToAnalyze.first();
            this.nodesToAnalyze.remove(bestNode);
            this.openBranches.remove(bestNode.move.getModel());
            this.closedBranches.put(bestNode.move.getModel(), bestNode);
            // If we've got a solution, reconstruct it and return.
            if (RubikCubeModel.isSolved(bestNode.move.getModel())) {
                final List<Move> solutionMoves = new ArrayList<Move>();
                IDAStarNode currentNode = bestNode;
                while (currentNode.parent != null) {
                    solutionMoves.add(0, currentNode.move);
                    currentNode = currentNode.parent;
                }
                // Force the search to restart next time
                this.openBranches = null;
                return solutionMoves;
            }
            // Generate all possible moves, making sure not to include already
            // open/closed branches.
            final IDAStarNode[] successors = new IDAStarNode[12];
            for (int i = 0; i < 12; ++i) {
                successors[i] = new IDAStarNode();
                switch (i) {
                    case 0:
                        successors[i].move = new U(new RubikCubeModel(bestNode.move.getModel()));
                        break;
                    case 1:
                        successors[i].move = new U(new RubikCubeModel(bestNode.move.getModel()), true);
                        break;
                    case 2:
                        successors[i].move = new D(new RubikCubeModel(bestNode.move.getModel()));
                        break;
                    case 3:
                        successors[i].move = new D(new RubikCubeModel(bestNode.move.getModel()), true);
                        break;
                    case 4:
                        successors[i].move = new L(new RubikCubeModel(bestNode.move.getModel()));
                        break;
                    case 5:
                        successors[i].move = new L(new RubikCubeModel(bestNode.move.getModel()), true);
                        break;
                    case 6:
                        successors[i].move = new R(new RubikCubeModel(bestNode.move.getModel()));
                        break;
                    case 7:
                        successors[i].move = new R(new RubikCubeModel(bestNode.move.getModel()), true);
                        break;
                    case 8:
                        successors[i].move = new F(new RubikCubeModel(bestNode.move.getModel()));
                        break;
                    case 9:
                        successors[i].move = new F(new RubikCubeModel(bestNode.move.getModel()), true);
                        break;
                    case 10:
                        successors[i].move = new B(new RubikCubeModel(bestNode.move.getModel()));
                        break;
                    case 11:
                        successors[i].move = new B(new RubikCubeModel(bestNode.move.getModel()), true);
                        break;
                    default:
                        throw new IllegalStateException();
                }
                if (!(bestNode.move instanceof NullMove)) {
                    // FIXME
                    // Prendi altra mossa : notAllowedMove= BESTNODE.move%2==0?BESTNODE.move-1:BESTNODE.move+1;
                    // if (notAllowedMove == successors[i].move) continue;
                }
                successors[i].move.perform();
                successors[i].depth = bestNode.depth + 1;
                successors[i].heuristicCoefficient = getHeuristicCoefficient(successors[i].move.getModel());
                successors[i].cost = bestNode.cost + 1;
                // Check if the node is already in the open/closed list.
                if (this.openBranches.containsKey(successors[i].move.getModel())) {
                    // If the path we're currently on has a total cost lower
                    // than the already found path, update the "old" node
                    final IDAStarNode oldNode = this.openBranches.get(successors[i].move.getModel());
                    if (successors[i].cost < oldNode.cost) {
                        oldNode.cost = successors[i].cost;
                        oldNode.f = oldNode.cost + oldNode.heuristicCoefficient;
                        oldNode.parent = bestNode;
                        oldNode.depth = successors[i].depth;
                    }
                    continue;
                }
                if (this.closedBranches.containsKey(successors[i].move.getModel())) {
                    final IDAStarNode oldNode = this.closedBranches.get(successors[i].move.getModel());
                    if (successors[i].cost < oldNode.cost) {
                        oldNode.cost = successors[i].cost;
                        oldNode.f = oldNode.cost + 1;
                        oldNode.parent = bestNode;
                        oldNode.depth = successors[i].depth;
                        // Update the successors using a DFS
                        updateSuccessorsDFS(oldNode);
                    }
                    continue;
                }
                // The node is new - update the search factor
                successors[i].f = successors[i].cost + successors[i].heuristicCoefficient;
                if (successors[i].f > threshold && successors[i].f - threshold < searchFactor) {
                    searchFactor = successors[i].f - threshold;
                } else {
                    successors[i].parent = bestNode;
                    bestNode.successors.add(successors[i]);
                    this.openBranches.put(successors[i].move.getModel(), successors[i]);
                    this.nodesToAnalyze.add(successors[i]);
                }
            }
        }
    }
    /**
     * Gets the name of this method.
     * @return <tt>IDA*</tt>
     */
    @Override
    public final String toString() {
        return "IDA*";
    }
    /**
     * Gets a textual description of this method.
     * @return <tt>IDA*-based resolution strategy.</tt>
     */
    @Override
    public final String getDescription() {
        return "IDA*-based resolution strategy.";
    }
    /**
     * Gets the heuristic coefficient for a cube.
     * @param c Cube.
     * @return Heuristic coefficient for c.
     */
    private static float getHeuristicCoefficient(final RubikCubeModel c) {
        float cornerStrength = 0;
        float edgeStrength = 0;
        if (RubikCubeModel.isSolved(c)) {
            // Nothing to do
            return 0;
        }
        for (RubikCubeCorner cv : RubikCubeCorner.values()) {
            // Get the corner
            final RubikCubeCornerColor corner = c.getCorner(cv);
            // Calculate the offset w.r.t. the standard configuration.
            final RubikCubeCorner standardCorner = RubikCubeCorner.getStandardCorner(
                    corner.getFirstColor(), corner.getSecondColor(), corner.getThirdColor());
            final int distance = Math.abs(cv.getX() - standardCorner.getX())
                    + Math.abs(cv.getY() - standardCorner.getY()) + Math.abs(cv.getZ() - standardCorner.getZ());
            if (distance == 0) {
                // If the side acting on the X/Y coordinates of the face we're
                // considering has the standard color of that face, do nothing,
                // else increase the corner strength by 2
                if (cv.getFaceOnAxis(RubikCubeModelAxis.X).getStandardColor()
                        != c.getCornerFacelet(RubikCubeModelAxis.X, cv)
                        || cv.getFaceOnAxis(RubikCubeModelAxis.Y).getStandardColor()
                        != c.getCornerFacelet(RubikCubeModelAxis.Y, cv)) {
                    cornerStrength += 2;
                }
            } else {
                cornerStrength += distance / 2.0 + 2;
            }
        }
        for (RubikCubeModel3Edge edge : RubikCubeModel3Edge.values()) {
            // Get the edge distances between the edge we're considering
            // and the edge having the same colors in the standard configuration
            final RubikCubeEdgeColor actualEdgeColors = c.get3DEdge(edge);
            // FIXME
            final RubikCubeModel3Edge standardEdge = RubikCubeModel3Edge.getStandardEdgeFromColors(
                    actualEdgeColors.getFirstColor(),
                    actualEdgeColors.getSecondColor());
            final int[] edgeDeltas = new int[3];
            edgeDeltas[0] = Math.abs(edge.getX() - standardEdge.getX());
            edgeDeltas[1] = Math.abs(edge.getY() - standardEdge.getY());
            edgeDeltas[2] = Math.abs(edge.getZ() - standardEdge.getZ());
            if (edgeDeltas[0] == 0 && edgeDeltas[1] == 0 && edgeDeltas[2] == 0)  {
                final RubikCubeSide xSide = edge.getFaceOnAxis(RubikCubeModelAxis.X);
                if (xSide != null) {
                    if (xSide.getStandardColor() != c.get3DEdgeFacelet(RubikCubeModelAxis.X, edge)) {
                        edgeStrength += 3;
                    }
                } else {
                    if (edge.getFaceOnAxis(RubikCubeModelAxis.Y).getStandardColor()
                            != c.get3DEdgeFacelet(RubikCubeModelAxis.Y, edge)) {
                        edgeStrength += 3;
                    }
                }
            } else if (edgeDeltas[0] != 0 && edgeDeltas[1] != 0 && edgeDeltas[2] != 0) {
                edgeStrength += 5;
            } else {
                if ((edgeDeltas[0] == 0 && (edge == RubikCubeModel3Edge.UL || edge == RubikCubeModel3Edge.UR || edge == RubikCubeModel3Edge.DL || edge == RubikCubeModel3Edge.DR))
                        || (edgeDeltas[1] == 0 && (edge == RubikCubeModel3Edge.FL || edge == RubikCubeModel3Edge.FR || edge == RubikCubeModel3Edge.BL || edge == RubikCubeModel3Edge.BR)) 
                        && (edgeDeltas[2] == 0 && (edge == RubikCubeModel3Edge.DB || edge == RubikCubeModel3Edge.DF || edge == RubikCubeModel3Edge.UB || edge == RubikCubeModel3Edge.UF))) {
                    edgeStrength += edgeDeltas[1] + edgeDeltas[2] + 3;
                } else {
                    edgeStrength += 1;
                }
            }
        }
        if (cornerStrength >= edgeStrength) {
            return cornerStrength / (float) 4;
        } else {
            return edgeStrength / (float) 4;
        }
    }
    /**
     * Execute a DFS on <tt>n</tt> to update its successors.
     * @param n IDA* node.
     */
    private static void updateSuccessorsDFS(final IDAStarNode n) {
        final Iterator<IDAStarNode> i = n.successors.iterator();
        while (i.hasNext()) {
            final IDAStarNode s = i.next();
            if (!s.parent.equals(n)) {
                if (s.cost > n.cost + 1) {
                    s.cost = n.cost + 1;
                    s.f = s.heuristicCoefficient + s.cost;
                    s.parent = n;
                    s.depth = n.depth + 1;
                    updateSuccessorsDFS(s);
                }
            } else {
                updateSuccessorsDFS(s);
            }
        }
    }
}
