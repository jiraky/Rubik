package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeModel;
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
 * IDA* resolution strategy.
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
        // FIXME
        if (RubikCubeModel.isSolved(c)) {
            return 0;
        }
        /*float cornerStrength=0, edgeStrength=0;
        Triplet correctPos;
        int which, deltaX, deltaY, deltaZ;
        for (int x = 0; x < 3; ++x) {
                for (int y = 0; y < 3; ++y) {
                        for (int z = 0; z < 3; ++z) {
                                if (x*y == 1 || y*z == 1 || z*x == 1) continue;
                                correctPos = Heuristics.getCorrectPos(cube.cubelet[x][y][z], x, y, z);
                                deltaX = Math.abs(correctPos.x -x);
                                deltaY = Math.abs(correctPos.y - y);
                                deltaZ = Math.abs(correctPos.z -z);
                                if (x != 1 && y != 1 && z != 1) { // corner cubie
                                        if (deltaX == 0 && deltaY == 0 && deltaZ == 0) {
                                                if (Heuristics.face[x][y][z].x == cube.cubelet[x][y][z].getColor(Heuristics.face[x][y][z].x) && Heuristics.face[x][y][z].y == cube.cubelet[x][y][z].getColor(Heuristics.face[x][y][z].y));
                                                else cornerStrength += 2;
                                        }
                                        else
                                                cornerStrength += (deltaX + deltaY + deltaZ)/2.0+2;
                                }
                                else{ // edge cubie
                                        if (deltaX == 0 && deltaY == 0 && deltaZ == 0) {
                                                if (Heuristics.face[x][y][z].x != -1) {
                                                        if (Heuristics.face[x][y][z].x == cube.cubelet[x][y][z].getColor(Heuristics.face[x][y][z].x));
                                                        else edgeStrength += 3;
                                                }
                                                else{
                                                        if (Heuristics.face[x][y][z].y == cube.cubelet[x][y][z].getColor(Heuristics.face[x][y][z].y));
                                                        else edgeStrength += 3;
                                                }
                                        }
                                        else if (deltaX != 0 && deltaY != 0 && deltaZ != 0) {
                                                edgeStrength += (2+3);
                                        }
                                        else{
                                                if ((deltaX == 0 && x == 1) || (deltaY == 0 && y == 1) && (deltaZ == 0 && z == 1)) {
                                                        edgeStrength += deltaY + deltaZ+3;
                                                }
                                                else edgeStrength += 1;
                                        }
                                }
                        }
                }
        }
        return (cornerStrength >= edgeStrength) ? cornerStrength/(float)4: edgeStrength/(float)4;*/
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
