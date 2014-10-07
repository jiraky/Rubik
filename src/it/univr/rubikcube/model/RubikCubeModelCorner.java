package it.univr.rubikcube.model;

/**
 * Rubik cube corner. Each corner is named after the three faces it comprises.
 * @author Alessandro Menti
 */
public enum RubikCubeModelCorner {
    // DO NOT ALTER the order here as some arrays rely on it.
    // Add new members to the bottom.
    /**
     * Corner including the upper, right and front faces.
     */
    URF,
    /**
     * Corner including the upper, right and left faces.
     */
    UFL,
    /**
     * Corner including the upper, right and back faces.
     */
    ULB,
    /**
     * Corner including the upper, back and right faces.
     */
    UBR,
    /**
     * Corner including the down, front and right faces.
     */
    DFR,
    /**
     * Corner including the down, left and front faces.
     */
    DLF,
    /**
     * Corner including the down, back and left faces.
     */
    DBL,
    /**
     * Corner including the down, right and back faces.
     */
    DRB
}
