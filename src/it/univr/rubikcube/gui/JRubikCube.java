package it.univr.rubikcube.gui;

import java.awt.Component;
import it.univr.rubikcube.model.RubikCubeModel;

/**
 * 3D Rubik Cube control.
 * @author Alessandro Menti
 */
public class JRubikCube extends Component {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Cube model.
     */
    private RubikCubeModel cubeModel;

    /**
     * Creates a new instance of the Rubik cube control.
     * @param faces Number of faces.
     */
    public JRubikCube(final int faces) {
        this.cubeModel = new RubikCubeModel(faces);
    }
}
