package it.univr.rubikcube.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Rubik cube model test cases.
 * @author Alessandro Menti
 */
public class RubikCubeModelTest {
    /**
     * Standard cube dimension.
     */
    private final int standardCubeDimension = 3;
    /**
     * Checks that the cube is set up correctly with the specified dimension
     * and all the faces in the standard configuration.
     */
    @Test
    public final void setupTest() {
        RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        assertEquals("Correct dimension returned", c.getDimension(),
                     this.standardCubeDimension);
        for (RubikCubeSide s : RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    assertEquals("Face [" + s.getValue() + "][" + i + "][" + j
                                 + "] has the correct color", c.getFace(s, i,
                                 j), s.getStandardColor());
                }
            }
        }
    }
    /**
     * Checks that a dimension change triggers the appropriate event, that the
     * correct dimension is returned and that the face on the cube boundary is
     * returned correctly.
     */
    @Test
    public final void dimensionChangeTest() {
        RubikCubeModel c = new RubikCubeModel(2);
        c.setDimension(this.standardCubeDimension);
        // FIXME Test observer!
        assertEquals("getDimension returns 3", c.getDimension(),
                     this.standardCubeDimension);
        assertEquals("getFace on the cube boundary returns the standard"
                     + " configuration facelet", c.getFace(RubikCubeSide.UP, 2,
                     2), RubikCubeFaceColor.WHITE);
    }
    /**
     * Checks that an exception is thrown when an invalid dimension is passed
     * at creation time.
     */
    @SuppressWarnings("static-method")
    @Test(expected = IllegalArgumentException.class)
    public final void invalidDimensionCreationTest() {
        @SuppressWarnings("unused")
        RubikCubeModel c = new RubikCubeModel(0);
    }
    /**
     * Checks that an exception is thrown when an invalid dimension is passed
     * in the <tt>setDimension</tt> method.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void invalidDimensionChangeTest() {
        RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.setDimension(1);
    }
//    ruota riga
//    ruota colonna
//    ruota colonna laterale
}
