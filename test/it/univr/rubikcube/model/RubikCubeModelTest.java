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
     * Cube Guru string: 4,4,4,4,4,4,4,4,4,7,7,7,7,7,7,7,7,7,2,2,2,2,2,2,2,2,2,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3
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
    /**
     * Check that the URUF sequence can be inverted.
     */
    @Test
    public final void checkURUFWorks() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        c.rotateRow(0, RowRotation.ANTICLOCKWISE);
        c.rotateColumn(2, ColumnRotation.TOP);
        c.rotateRow(0, RowRotation.ANTICLOCKWISE);
        c.rotateLateralColumn(0, LateralColumnRotation.RIGHT);
        c.rotateLateralColumn(0, LateralColumnRotation.LEFT);
        c.rotateRow(0, RowRotation.CLOCKWISE);
        c.rotateColumn(2, ColumnRotation.BOTTOM);
        c.rotateRow(0, RowRotation.CLOCKWISE);
        Assert.assertTrue("URUF*(URUF)^(-1) = identity",
                RubikCubeModel.isInStandardConfiguration(c));
    }
    /**
     * Starting from a randomly mixed cube, rotate the three rows and check
     * that each move performs correctly.
     */
    @Test
    public final void rotateRowMixed() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        this.mixCube(c);
        c.rotateRow(0, RowRotation.ANTICLOCKWISE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.RED);
        c.rotateRow(0, RowRotation.CLOCKWISE);
        Assert.assertTrue(this.isInMixedConfiguration(c));
        c.rotateRow(1, RowRotation.ANTICLOCKWISE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.RED);
        c.rotateRow(1, RowRotation.CLOCKWISE);
        Assert.assertTrue(this.isInMixedConfiguration(c));
        c.rotateRow(2, RowRotation.ANTICLOCKWISE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.WHITE);
        c.rotateRow(2, RowRotation.CLOCKWISE);
        Assert.assertTrue(this.isInMixedConfiguration(c));
    }
    /**
     * Starting from a randomly mixed cube, rotate the three columns and check
     * that each move performs correctly.
     */
    @Test
    public final void rotateColumnMixed() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        this.mixCube(c);
        c.rotateColumn(0, ColumnRotation.TOP);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.RED);
        c.rotateColumn(0, ColumnRotation.BOTTOM);
        Assert.assertTrue(this.isInMixedConfiguration(c));
        c.rotateColumn(1, ColumnRotation.TOP);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.RED);
        c.rotateColumn(1, ColumnRotation.BOTTOM);
        Assert.assertTrue(this.isInMixedConfiguration(c));
        c.rotateColumn(2, ColumnRotation.TOP);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.WHITE);
        c.rotateColumn(2, ColumnRotation.BOTTOM);
        Assert.assertTrue(this.isInMixedConfiguration(c));
    }
    /**
     * Starting from a randomly mixed cube, rotate the three lateral columns
     * and check that each move performs correctly.
     */
    @Test
    public final void rotateLateralColumnMixed() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        this.mixCube(c);
        c.rotateLateralColumn(0, LateralColumnRotation.LEFT);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.GREEN);
        c.rotateLateralColumn(0, LateralColumnRotation.RIGHT);
        Assert.assertTrue(this.isInMixedConfiguration(c));
        c.rotateLateralColumn(1, LateralColumnRotation.LEFT);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.RED);
        c.rotateLateralColumn(1, LateralColumnRotation.RIGHT);
        Assert.assertTrue(this.isInMixedConfiguration(c));
        c.rotateLateralColumn(2, LateralColumnRotation.LEFT);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.RED);
        c.rotateLateralColumn(2, LateralColumnRotation.RIGHT);
        Assert.assertTrue(this.isInMixedConfiguration(c));
    }
    /**
     * Perform a sample sequence of moves (URUF) and check that the cube
     * ends in the correct state.
     */
    @Test
    public final void checkSequenceMoves1() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        this.mixCube(c);
        c.rotateRow(0, RowRotation.CLOCKWISE);
        c.rotateColumn(2, ColumnRotation.TOP);
        c.rotateRow(0, RowRotation.CLOCKWISE);
        c.rotateLateralColumn(0, LateralColumnRotation.RIGHT);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.BLUE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.RED);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.GREEN);
        Assert.assertTrue(c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.YELLOW);
    }
    /**
     * Checks that the 3D edges of a cube in standard configuration are
     * returned correctly.
     */
    @Test
    public final void check3DEdge() {
        final RubikCubeModel c = new RubikCubeModel(this.standardCubeDimension);
        RubikCubeEdgeColor ec;
        ec = c.get3DEdge(RubikCubeModel3Edge.BL);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.GREEN);
        ec = c.get3DEdge(RubikCubeModel3Edge.BR);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.ORANGE);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.BLUE);
        ec = c.get3DEdge(RubikCubeModel3Edge.DB);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.ORANGE);
        ec = c.get3DEdge(RubikCubeModel3Edge.DF);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.RED);
        ec = c.get3DEdge(RubikCubeModel3Edge.DL);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.GREEN);
        ec = c.get3DEdge(RubikCubeModel3Edge.DR);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.YELLOW);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.BLUE);
        ec = c.get3DEdge(RubikCubeModel3Edge.FL);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.RED);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.GREEN);
        ec = c.get3DEdge(RubikCubeModel3Edge.FR);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.RED);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.BLUE);
        ec = c.get3DEdge(RubikCubeModel3Edge.UB);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.ORANGE);
        ec = c.get3DEdge(RubikCubeModel3Edge.UF);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.RED);
        ec = c.get3DEdge(RubikCubeModel3Edge.UL);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.GREEN);
        ec = c.get3DEdge(RubikCubeModel3Edge.UR);
        Assert.assertTrue(ec.getFirstColor() == RubikCubeFaceColor.WHITE);
        Assert.assertTrue(ec.getSecondColor() == RubikCubeFaceColor.BLUE);
    }
    /**
     * Put the 3x3 cube c in a "good" random state.
     * @param c 3x3 cube to be mixed.
     */
    private void mixCube(final RubikCubeModel c) {
        // For easy visualization, go to http://thecube.guru/online-3d-rubiks-cube/
        // and import the following string:
        // 3,3,2,5,6,6,2,3,4,6,3,2,4,3,4,3,5,5,7,5,3,7,7,6,4,2,4,6,2,4,2,5,6,5,3,5,7,7,2,7,4,4,5,6,3,6,7,6,4,2,5,7,2,7
        c.setFace(RubikCubeSide.UP, 0, 0, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.UP, 0, 1, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.UP, 0, 2, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.UP, 1, 0, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.UP, 1, 1, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.UP, 1, 2, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.UP, 2, 0, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.UP, 2, 1, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.UP, 2, 2, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.LEFT, 0, 0, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.LEFT, 0, 1, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.LEFT, 0, 2, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.LEFT, 1, 0, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.LEFT, 1, 1, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.LEFT, 1, 2, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.LEFT, 2, 0, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.LEFT, 2, 1, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.LEFT, 2, 2, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.RIGHT, 0, 0, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.RIGHT, 0, 1, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.RIGHT, 0, 2, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.RIGHT, 1, 0, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.RIGHT, 1, 1, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.RIGHT, 1, 2, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.RIGHT, 2, 0, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.RIGHT, 2, 1, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.RIGHT, 2, 2, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.BACK, 0, 0, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.BACK, 0, 1, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.BACK, 0, 2, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.BACK, 1, 0, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.BACK, 1, 1, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.BACK, 1, 2, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.BACK, 2, 0, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.BACK, 2, 1, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.BACK, 2, 2, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.DOWN, 0, 0, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.DOWN, 0, 1, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.DOWN, 0, 2, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.DOWN, 1, 0, RubikCubeFaceColor.ORANGE);
        c.setFace(RubikCubeSide.DOWN, 1, 1, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.DOWN, 1, 2, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.DOWN, 2, 0, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.DOWN, 2, 1, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.DOWN, 2, 2, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.FRONT, 0, 0, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.FRONT, 0, 1, RubikCubeFaceColor.WHITE);
        c.setFace(RubikCubeSide.FRONT, 0, 2, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.FRONT, 1, 0, RubikCubeFaceColor.RED);
        c.setFace(RubikCubeSide.FRONT, 1, 1, RubikCubeFaceColor.YELLOW);
        c.setFace(RubikCubeSide.FRONT, 1, 2, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.FRONT, 2, 0, RubikCubeFaceColor.GREEN);
        c.setFace(RubikCubeSide.FRONT, 2, 1, RubikCubeFaceColor.BLUE);
        c.setFace(RubikCubeSide.FRONT, 2, 2, RubikCubeFaceColor.RED);
    }
    /**
     * Checks if <tt>c</tt> is in the "good" random state specified in <tt>mixCube</tt>.
     * @param c 3x3 cube to check.
     * @return <tt>true</tt> if and only if the cube is in the "good" random state.
     */
    private boolean isInMixedConfiguration(final RubikCubeModel c) {
        return c.getFace(RubikCubeSide.UP, 0, 0) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.UP, 0, 1) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.UP, 0, 2) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.UP, 1, 0) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.UP, 1, 1) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.UP, 1, 2) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.UP, 2, 0) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.UP, 2, 1) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.UP, 2, 2) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.LEFT, 0, 0) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.LEFT, 0, 1) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.LEFT, 0, 2) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.LEFT, 1, 0) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.LEFT, 1, 1) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.LEFT, 1, 2) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.LEFT, 2, 0) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.LEFT, 2, 1) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.LEFT, 2, 2) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.RIGHT, 0, 0) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.RIGHT, 0, 1) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.RIGHT, 0, 2) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.RIGHT, 1, 0) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.RIGHT, 1, 1) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.RIGHT, 1, 2) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.RIGHT, 2, 0) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.RIGHT, 2, 1) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.RIGHT, 2, 2) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.BACK, 0, 0) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.BACK, 0, 1) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.BACK, 0, 2) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.BACK, 1, 0) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.BACK, 1, 1) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.BACK, 1, 2) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.BACK, 2, 0) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.BACK, 2, 1) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.BACK, 2, 2) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.DOWN, 0, 0) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.DOWN, 0, 1) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.DOWN, 0, 2) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.DOWN, 1, 0) == RubikCubeFaceColor.ORANGE
               && c.getFace(RubikCubeSide.DOWN, 1, 1) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.DOWN, 1, 2) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.DOWN, 2, 0) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.DOWN, 2, 1) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.DOWN, 2, 2) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.FRONT, 0, 0) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.FRONT, 0, 1) == RubikCubeFaceColor.WHITE
               && c.getFace(RubikCubeSide.FRONT, 0, 2) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.FRONT, 1, 0) == RubikCubeFaceColor.RED
               && c.getFace(RubikCubeSide.FRONT, 1, 1) == RubikCubeFaceColor.YELLOW
               && c.getFace(RubikCubeSide.FRONT, 1, 2) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.FRONT, 2, 0) == RubikCubeFaceColor.GREEN
               && c.getFace(RubikCubeSide.FRONT, 2, 1) == RubikCubeFaceColor.BLUE
               && c.getFace(RubikCubeSide.FRONT, 2, 2) == RubikCubeFaceColor.RED;
    }
}
