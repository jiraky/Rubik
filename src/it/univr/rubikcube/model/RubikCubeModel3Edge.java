package it.univr.rubikcube.model;

/**
 * Edge of the Rubik cube. The edge is named after the faces it comprises.
 * @author Alessandro Menti
 */
public enum RubikCubeModel3Edge {
    // DO NOT ALTER the order here as some arrays rely on it.
    // Add new members to the bottom.
    /**
     * Edge comprising the upper and right faces.
     */
    UR,
    /**
     * Edge comprising the upper and front faces.
     */
    UF,
    /**
     * Edge comprising the upper and left faces.
     */
    UL,
    /**
     * Edge comprising the upper and back faces.
     */
    UB,
    /**
     * Edge comprising the down and right faces.
     */
    DR,
    /**
     * Edge comprising the down and front faces.
     */
    DF,
    /**
     * Edge comprising the down and left faces.
     */
    DL,
    /**
     * Edge comprising the down and back faces.
     */
    DB,
    /**
     * Edge comprising the front and right faces.
     */
    FR,
    /**
     * Edge comprising the front and left faces.
     */
    FL,
    /**
     * Edge comprising the back and left faces.
     */
    BL,
    /**
     * Edge comprising the back and right faces.
     */
    BR
}
