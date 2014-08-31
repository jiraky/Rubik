package it.univr.rubikcube.model;

/**
 * Move needed to take the cube from one configuration to another.
 * @author Alessandro Menti
 */
public interface Move {
    /**
     * Returns a string representing the move using the Singmaster notation.
     * @return Move in Singmaster notation.
     */
    String toText();
    /**
     * Performs the move.
     */
    void perform();
    /**
     * Reverses the move.
     */
    void reverse();
}
