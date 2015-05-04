package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.ThreeDimensionalMove;
import java.util.HashSet;

/**
 * Node in the IDA* tree.
 * @author Alessandro Menti
 */
public class IDAStarNode {
    public float f;
    /**
     * Cost of getting from the original configuration to this node.
     */
    public float cost; // ex g
    /**
     * Heuristic coefficient for IDA*.
     */
    public float heuristicCoefficient;
    /**
     * Parent node in the IDA* tree.
     */
    public IDAStarNode parent;
    /**
     * Children in the IDA* tree.
     */
    public HashSet<IDAStarNode> successors;
    /**
     * Move used to bring the parent cube to this configuration.
     */
    public ThreeDimensionalMove move;
    /**
     * Tree depth for this node.
     */
    public int depth;
}
