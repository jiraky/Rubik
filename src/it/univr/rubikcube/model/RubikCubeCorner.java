package it.univr.rubikcube.model;

/**
 * Corner of the Rubik cube. Each corner is named after the three facelets it
 * comprises.
 * @author Alessandro Menti
 */
public enum RubikCubeCorner {
    /**
     * Corner between the up, right and front faces.
     */
    URF,
    /**
     * Corner between the up, front and left faces.
     */
    UFL,
    /**
     * Corner between the up, left and back faces.
     */
    ULB,
    /**
     * Corner between the up, back and right faces.
     */
    UBR,
    /**
     * Corner between the down, front and right faces.
     */
    DFR,
    /**
     * Corner between the down, left and front faces.
     */
    DLF,
    /**
     * Corner between the down, back and left faces.
     */
    DBL,
    /**
     * Corner between the down, right and back faces.
     */
    DRB
}
