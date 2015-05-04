package it.univr.rubikcube.resolutionstrategies;

import java.util.Comparator;

/**
 * Compare two IDA* nodes.
 * @author Alessandro Menti
 */
public class IDAStarComparator implements Comparator<IDAStarNode> {
    /**
     * Empty constructor.
     */
    public IDAStarComparator() {
    }
    /**
     * Compare two IDA* nodes.
     * @param o1 First node.
     * @param o2 Second node.
     * @return 0 if the objects are equal, 
     */
    @Override
    public final int compare(final IDAStarNode o1, final IDAStarNode o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o1.f > o2.f) {
            return 1;
        }
        return -1;
    }
}
