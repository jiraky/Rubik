package it.univr.rubikcube.model;

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
        Assert.assertEquals("Correct dimension returned",
                this.standardCubeDimension, c.getDimension());
        for (RubikCubeSide s : RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    Assert.assertEquals("Face " + s.ordinal() + "][" + i
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
        Assert.assertEquals("getDimension returns 3",
                     this.standardCubeDimension, c.getDimension());
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
        c.rotateRow(0, RowRotation.ANTICLOCKWISE);
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    RubikCubeFaceColor col;
                    if (i == 0) {
                        if (s.equals(RubikCubeSide.FRONT)) {
                            col = RubikCubeFaceColor.GREEN;
                        } else if (s.equals(RubikCubeSide.RIGHT)) {
                            col = RubikCubeFaceColor.RED;
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
        c.rotateRow(0, RowRotation.CLOCKWISE);
        Assert.assertTrue("Opposite row rotation brings back to standard",
                          RubikCubeModel.isInStandardConfiguration(c));
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
                          RubikCubeModel.isInStandardConfiguration(c));
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
                          + " standard", RubikCubeModel.isInStandardConfiguration(c));
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
     * Checks that cube rotation works properly.
     */
    @Test
    public final void rotateCube() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateCube(CubeRotation.CLOCKWISE_FROM_FRONT);
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    RubikCubeFaceColor col;
                    if (s.equals(RubikCubeSide.UP)) {
                        col = RubikCubeFaceColor.GREEN;
                    } else if (s.equals(RubikCubeSide.RIGHT)) {
                        col = RubikCubeFaceColor.WHITE;
                    } else if (s.equals(RubikCubeSide.DOWN)) {
                        col = RubikCubeFaceColor.BLUE;
                    } else if (s.equals(RubikCubeSide.LEFT)) {
                        col = RubikCubeFaceColor.YELLOW;
                    } else {
                        col = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + "," + i
                                        + "," + j + ") face is correct", col,
                                        c.getFace(s, i, j));
                }
            }
        }
        c.rotateCube(CubeRotation.ANTICLOCKWISE_FROM_FRONT);
        Assert.assertTrue("Anticlockwise from front cube rotation brings back"
                + " to standard", RubikCubeModel.isInStandardConfiguration(c));
        c.rotateCube(CubeRotation.UPWISE);
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    RubikCubeFaceColor col;
                    if (s.equals(RubikCubeSide.UP)) {
                        col = RubikCubeFaceColor.RED;
                    } else if (s.equals(RubikCubeSide.BACK)) {
                        col = RubikCubeFaceColor.WHITE;
                    } else if (s.equals(RubikCubeSide.DOWN)) {
                        col = RubikCubeFaceColor.ORANGE;
                    } else if (s.equals(RubikCubeSide.FRONT)) {
                        col = RubikCubeFaceColor.YELLOW;
                    } else {
                        col = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + "," + i
                                        + "," + j + ") face is correct", col,
                                        c.getFace(s, i, j));
                }
            }
        }
        c.rotateCube(CubeRotation.DOWNWISE);
        Assert.assertTrue("Downwise cube rotation brings back to"
                + " standard", RubikCubeModel.isInStandardConfiguration(c));
        c.rotateCube(CubeRotation.CLOCKWISE);
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < this.standardCubeDimension; ++i) {
                for (int j = 0; j < this.standardCubeDimension; ++j) {
                    RubikCubeFaceColor col;
                    if (s.equals(RubikCubeSide.BACK)) {
                        col = RubikCubeFaceColor.GREEN;
                    } else if (s.equals(RubikCubeSide.RIGHT)) {
                        col = RubikCubeFaceColor.ORANGE;
                    } else if (s.equals(RubikCubeSide.FRONT)) {
                        col = RubikCubeFaceColor.BLUE;
                    } else if (s.equals(RubikCubeSide.LEFT)) {
                        col = RubikCubeFaceColor.RED;
                    } else {
                        col = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + "," + i
                                        + "," + j + ") face is correct", col,
                                        c.getFace(s, i, j));
                }
            }
        }
        c.rotateCube(CubeRotation.ANTICLOCKWISE);
        Assert.assertTrue("Anticlockwise cube rotation brings back to"
                + " standard", RubikCubeModel.isInStandardConfiguration(c));
    }
    /**
     * Check that a cube in the standard configuration is sane.
     */
    @Test
    public final void standardConfigurationIsSane() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        Assert.assertTrue("A cube in standard configuration is sane",
                          RubikCubeModel.isWithSaneColors(c));
        c.rotateColumn(0, ColumnRotation.BOTTOM);
        Assert.assertTrue("A simple rotation keeps the cube sane",
                          RubikCubeModel.isWithSaneColors(c));
    }
    /**
     * Check that a cube in the standard configuration is solved and vice versa.
     */
    @Test
    public final void standardConfigurationIsSolved() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        Assert.assertTrue("A cube in standard configuration is solved",
                          RubikCubeModel.isSolved(c));
        c.rotateColumn(0, ColumnRotation.BOTTOM);
        Assert.assertFalse("A simple rotation marks the cube as unsolved",
                           RubikCubeModel.isSolved(c));
    }
    /**
     * Check that getSide() returns the correct side.
     */
    @Test
    public final void getSide() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        Assert.assertTrue("Up side in standard configuration has standard color",
                          c.getSide(RubikCubeSide.UP.getStandardColor()) == RubikCubeSide.UP);
        Assert.assertTrue("Down side in standard configuration has standard color",
                          c.getSide(RubikCubeSide.DOWN.getStandardColor()) == RubikCubeSide.DOWN);
        Assert.assertTrue("Left side in standard configuration has standard color",
                          c.getSide(RubikCubeSide.LEFT.getStandardColor()) == RubikCubeSide.LEFT);
        Assert.assertTrue("Right side in standard configuration has standard color",
                          c.getSide(RubikCubeSide.RIGHT.getStandardColor()) == RubikCubeSide.RIGHT);
        Assert.assertTrue("Front side in standard configuration has standard color",
                          c.getSide(RubikCubeSide.FRONT.getStandardColor()) == RubikCubeSide.FRONT);
        Assert.assertTrue("Back side in standard configuration has standard color",
                          c.getSide(RubikCubeSide.BACK.getStandardColor()) == RubikCubeSide.BACK);
        Assert.assertTrue("The red side in the standard configuration is not the top one",
                          c.getSide(RubikCubeFaceColor.RED) != RubikCubeSide.UP);
        // Rotate the cube to the left
        c.rotateCube(CubeRotation.ANTICLOCKWISE_FROM_FRONT);
        Assert.assertTrue("Front side stays red",
                          c.getSide(RubikCubeFaceColor.RED) == RubikCubeSide.FRONT);
        Assert.assertTrue("Back side stays orange",
                          c.getSide(RubikCubeFaceColor.ORANGE) == RubikCubeSide.BACK);
        Assert.assertTrue("Left side is now white",
                          c.getSide(RubikCubeFaceColor.WHITE) == RubikCubeSide.LEFT);
        Assert.assertTrue("Down side is now green",
                          c.getSide(RubikCubeFaceColor.GREEN) == RubikCubeSide.DOWN);
        Assert.assertTrue("Right side is now yellow",
                          c.getSide(RubikCubeFaceColor.YELLOW) == RubikCubeSide.RIGHT);
        Assert.assertTrue("Up side is now blue",
                          c.getSide(RubikCubeFaceColor.BLUE) == RubikCubeSide.UP);
        c.resetToStandardConfiguration();
        // Rotate the cube to the top
        c.rotateCube(CubeRotation.UPWISE);
        Assert.assertTrue("Front side is now yellow",
                          c.getSide(RubikCubeFaceColor.YELLOW) == RubikCubeSide.FRONT);
        Assert.assertTrue("Back side is now white",
                          c.getSide(RubikCubeFaceColor.WHITE) == RubikCubeSide.BACK);
        Assert.assertTrue("Left side stays green",
                          c.getSide(RubikCubeFaceColor.GREEN) == RubikCubeSide.LEFT);
        Assert.assertTrue("Down side is now orange",
                          c.getSide(RubikCubeFaceColor.ORANGE) == RubikCubeSide.DOWN);
        Assert.assertTrue("Right side stays blue",
                          c.getSide(RubikCubeFaceColor.BLUE) == RubikCubeSide.RIGHT);
        Assert.assertTrue("Up side is now red",
                          c.getSide(RubikCubeFaceColor.RED) == RubikCubeSide.UP);
        c.resetToStandardConfiguration();
        // Rotate the equator row
        c.rotateLateralColumn(1, LateralColumnRotation.LEFT);
        Assert.assertTrue("Front side stays red",
                          c.getSide(RubikCubeFaceColor.RED) == RubikCubeSide.FRONT);
        Assert.assertTrue("Back side stays orange",
                          c.getSide(RubikCubeFaceColor.ORANGE) == RubikCubeSide.BACK);
        Assert.assertTrue("Left side is now white",
                          c.getSide(RubikCubeFaceColor.WHITE) == RubikCubeSide.LEFT);
        Assert.assertTrue("Down side is now green",
                          c.getSide(RubikCubeFaceColor.GREEN) == RubikCubeSide.DOWN);
        Assert.assertTrue("Right side is now yellow",
                          c.getSide(RubikCubeFaceColor.YELLOW) == RubikCubeSide.RIGHT);
        Assert.assertTrue("Up side is now blue",
                          c.getSide(RubikCubeFaceColor.BLUE) == RubikCubeSide.UP);
    }
}
