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
    /**
     * Checks that rows are rotated correctly.
     */
    @Test
    public final void rotateRow() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateRow(0, RowRotation.CLOCKWISE);
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    RubikCubeFaceColor col;
                    if (i == 0) {
                        if (s.equals(RubikCubeSide.FRONT)) {
                            col = RubikCubeFaceColor.GREEN;
                        } else if (s.equals(RubikCubeSide.RIGHT)) {
                            col = RubikCubeFaceColor.GREEN;
                        } else if (s.equals(RubikCubeSide.BACK)) {
                            col = RubikCubeFaceColor.BLUE;
                        } else if (s.equals(RubikCubeSide.LEFT)) {
                            col = RubikCubeFaceColor.ORANGE;
                        } else {
                            col = s.getStandardColor();
                        }
                    } else {
                        col = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + "," + i
                                        + "," + j + ") face is correct", col,
                                        c.getFace(s, i, j));
                }
            }
        }
        c.rotateRow(0, RowRotation.ANTICLOCKWISE);
        Assert.assertTrue("Opposite row rotation brings back to standard",
                          isInStandardConfiguration(c));
    }
    /**
     * Checks that attempting to rotate a row with a negative index fails.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void rotateRowIndexOutOfBoundsNegative() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateRow(-1, RowRotation.CLOCKWISE);
    }
    /**
     * Checks that attempting to rotate a row with a too high index fails.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void rotateRowIndexOutOfBoundsPositive() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateRow(this.standardCubeDimension + 1, RowRotation.CLOCKWISE);
    }
    /**
     * Checks that columns are rotated correctly.
     */
    @Test
    public final void rotateColumn() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateColumn(0, ColumnRotation.TOP);
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    RubikCubeFaceColor col;
                    if (s.equals(RubikCubeSide.FRONT) && j == 0) {
                        col = RubikCubeFaceColor.YELLOW;
                    } else if (s.equals(RubikCubeSide.UP) && j == 0) {
                        col = RubikCubeFaceColor.RED;
                    } else if (s.equals(RubikCubeSide.BACK)
                            && j == this.standardCubeDimension - 1) {
                        col = RubikCubeFaceColor.WHITE;
                    } else if (s.equals(RubikCubeSide.DOWN) && j == 0) {
                        col = RubikCubeFaceColor.ORANGE;
                    } else {
                        col = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + "," + i
                                        + "," + j + ") face is correct", col,
                                        c.getFace(s, i, j));
                }
            }
        }
        c.rotateColumn(0, ColumnRotation.BOTTOM);
        Assert.assertTrue("Opposite column rotation brings back to standard",
                          isInStandardConfiguration(c));
    }
    /**
     * Checks that attempting to rotate a column with a negative index fails.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void rotateColumnIndexOutOfBoundsNegative() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateColumn(-1, ColumnRotation.TOP);
    }
    /**
     * Checks that attempting to rotate a column with a too high index fails.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void rotateColumnIndexOutOfBoundsPositive() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateColumn(this.standardCubeDimension + 1, ColumnRotation.TOP);
    }
    /**
     * Checks that lateral columns are rotated correctly.
     */
    @Test
    public final void rotateLateralColumn() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateLateralColumn(0, LateralColumnRotation.LEFT);
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    RubikCubeFaceColor col;
                    if (s.equals(RubikCubeSide.RIGHT) && j == 0) {
                        col = RubikCubeFaceColor.YELLOW;
                    } else if (s.equals(RubikCubeSide.UP)
                            && i == this.standardCubeDimension - 1) {
                        col = RubikCubeFaceColor.BLUE;
                    } else if (s.equals(RubikCubeSide.LEFT)
                            && j == this.standardCubeDimension - 1) {
                        col = RubikCubeFaceColor.WHITE;
                    } else if (s.equals(RubikCubeSide.DOWN) && i == 0) {
                        col = RubikCubeFaceColor.GREEN;
                    } else {
                        col = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + "," + i
                                        + "," + j + ") face is correct", col,
                                        c.getFace(s, i, j));
                }
            }
        }
        c.rotateLateralColumn(0, LateralColumnRotation.RIGHT);
        Assert.assertTrue("Opposite lateral column rotation brings back to"
                          + " standard", isInStandardConfiguration(c));
    }
    /**
     * Checks that attempting to rotate a lateral column with a negative index
     * fails.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void rotateLateralColumnIndexOutOfBoundsNegative() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateLateralColumn(-1, LateralColumnRotation.LEFT);
    }
    /**
     * Checks that attempting to rotate a row with a too high index fails.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public final void rotateLateralColumnIndexOutOfBoundsPositive() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateLateralColumn(this.standardCubeDimension + 1,
                              LateralColumnRotation.LEFT);
    }
    /**
     * Checks whether a cube is in the standard configuration.
     * @param m Move to be checked.
     * @return <tt>true</tt> if and only if the cube is in the standard
     * configuration.
     */
    public static boolean isInStandardConfiguration(final RubikCubeModel m) {
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < m.getDimension(); ++i) {
                for (int j = 0; j < m.getDimension(); ++j) {
                    if (s.getStandardColor() != m.getFace(s, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true; 
    }
}
