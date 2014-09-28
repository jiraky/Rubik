// CHECKSTYLE:OFF Rationale: package-info.java already present in src/
package it.univr.rubikcube.model;
// CHECKSTYLE:ON

import it.univr.rubikcube.moves.*;
import org.junit.Assert;
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
     * Standard class constructor.
     */
    public RubikCubeModelTest() {
        // Empty, nothing to do
    }
    /**
     * Checks that the cube is set up correctly with the specified dimension
     * and all the faces in the standard configuration.
     */
    @Test
    public final void setupTest() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        Assert.assertEquals("Correct dimension returned", c.getDimension(),
                this.standardCubeDimension);
        for (RubikCubeSide s : RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    Assert.assertEquals("Face " + s.getValue() + "][" + i
                            + "][" + j + "] has the correct color",
                            c.getFace(s, i, j), s.getStandardColor());
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
        final RubikCubeModel c = new RubikCubeModel(2);
        c.setDimension(this.standardCubeDimension);
        // FIXME Test observer!
        Assert.assertEquals("getDimension returns 3", c.getDimension(),
                     this.standardCubeDimension);
        Assert.assertEquals("getFace on the cube boundary returns the standard"
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
        final RubikCubeModel c = new RubikCubeModel(0);
    }
    /**
     * Checks that an exception is thrown when an invalid dimension is passed
     * in the <tt>setDimension</tt> method.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void invalidDimensionChangeTest() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.setDimension(1);
    }
//    ruota riga
//    ruota colonna
//    ruota colonna laterale
    
    @Test
    public final void check4Moves() {
        final RubikCubeModel original = new RubikCubeModel(standardCubeDimension);
        RubikCubeModel c = new RubikCubeModel(standardCubeDimension);
        
        Move move = new L(c);
        move.perform();
        move.perform();
        move.perform();
        move.perform();
        
        for(RubikCubeSide face : RubikCubeSide.values()) {
            for(int i=0; i<standardCubeDimension; i++) {
                for(int j=0; j<standardCubeDimension; j++) {
                    Assert.assertEquals(c.getFace(face, i, j),original.getFace(face, i, j));
                }
            }
        }
        
        c.resetToStandardConfiguration();
        move = new R(c);
        move.perform();
        move.perform();
        move.perform();
        move.perform();
        
        for(RubikCubeSide face : RubikCubeSide.values()) {
            for(int i=0; i<standardCubeDimension; i++) {
                for(int j=0; j<standardCubeDimension; j++) {
                    Assert.assertEquals(c.getFace(face, i, j),original.getFace(face, i, j));
                }
            }
        }
        
        c.resetToStandardConfiguration();
        move = new U(c);
        move.perform();
        move.perform();
        move.perform();
        move.perform();
        
        for(RubikCubeSide face : RubikCubeSide.values()) {
            for(int i=0; i<standardCubeDimension; i++) {
                for(int j=0; j<standardCubeDimension; j++) {
                    Assert.assertEquals(c.getFace(face, i, j),original.getFace(face, i, j));
                }
            }
        }
        
        c.resetToStandardConfiguration();
        move = new D(c);
        move.perform();
        move.perform();
        move.perform();
        move.perform();
        
        for(RubikCubeSide face : RubikCubeSide.values()) {
            for(int i=0; i<standardCubeDimension; i++) {
                for(int j=0; j<standardCubeDimension; j++) {
                    Assert.assertEquals(c.getFace(face, i, j),original.getFace(face, i, j));
                }
            }
        }
        
        c.resetToStandardConfiguration();
        move = new B(c);
        move.perform();
        move.perform();
        move.perform();
        move.perform();
        
        for(RubikCubeSide face : RubikCubeSide.values()) {
            for(int i=0; i<standardCubeDimension; i++) {
                for(int j=0; j<standardCubeDimension; j++) {
                    Assert.assertEquals(c.getFace(face, i, j),original.getFace(face, i, j));
                }
            }
        }
        
        c.resetToStandardConfiguration();
        move = new F(c);
        move.perform();
        move.perform();
        move.perform();
        move.perform();
        
        for(RubikCubeSide face : RubikCubeSide.values()) {
            for(int i=0; i<standardCubeDimension; i++) {
                for(int j=0; j<standardCubeDimension; j++) {
                    Assert.assertEquals(c.getFace(face, i, j),original.getFace(face, i, j));
                }
            }
        }
    }
}
