package it.univr.rubikcube.gui;

import it.univr.rubikcube.model.RubikCubeModel;
import java.awt.Component;

/**
 * Rubik Cube control.
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
     * @throws IllegalArgumentException Thrown if the number of faces is less
     * than two.
     */
    public JRubikCube(final int faces) throws IllegalArgumentException {
        this.cubeModel = new RubikCubeModel(faces);
    }
    /**
     * Gets the Rubik cube model associated with this control.
     * @return Rubik cube model.
     */
    public final RubikCubeModel getModel() {
        return this.cubeModel;
    }
}
