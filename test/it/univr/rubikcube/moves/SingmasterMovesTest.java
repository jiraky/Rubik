package it.univr.rubikcube.moves;

import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeFaceColor;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.RubikCubeModelTest;
import it.univr.rubikcube.model.RubikCubeSide;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Checks that the Singmaster moves are performed correctly.
 * @author Alessandro Menti
 */
public class SingmasterMovesTest {
    /**
     * Rubik cube dimension.
     */
    private static final int CUBE_DIMENSION = 3;
    /**
     * Cube model.
     */
    private RubikCubeModel cube;
    /**
     * Creates a new instance of the test class.
     */
    public SingmasterMovesTest() {
        // Empty, nothing to do
    }
    /**
     * Creates a new cube in the standard configuration.
     */
    @Before
    public final void setUp() {
        this.cube = new RubikCubeModel(3);
    }
    /**
     * Perform a U move.
     */
    @Test
    public final void testU() {
        final Move m = new U(this.cube);
        m.perform();
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < CUBE_DIMENSION; ++i) {
                for (int j = 0; j < CUBE_DIMENSION; ++j) {
                    RubikCubeFaceColor c;
                    if (i == 0) {
                        if (s.equals(RubikCubeSide.FRONT)) {
                            c = RubikCubeFaceColor.BLUE;
                        } else if (s.equals(RubikCubeSide.LEFT)) {
                            c = RubikCubeFaceColor.RED;
                        } else if (s.equals(RubikCubeSide.BACK)) {
                            c = RubikCubeFaceColor.GREEN;
                        } else if (s.equals(RubikCubeSide.RIGHT)) {
                            c = RubikCubeFaceColor.ORANGE;
                        } else {
                            c = s.getStandardColor();
                        }
                    } else {
                        c = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + ","
                            + i + "," + j + ") face is correct", c,
                            this.cube.getFace(s, i, j));
                }
            }
        }
        Assert.assertTrue("Reversed U move brings cube back to default",
                            checkReverseToStandard(m));
    }
    /**
     * Perform a F move.
     */
    @Test
    public final void testF() {
        final Move m = new F(this.cube);
        m.perform();
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < CUBE_DIMENSION; ++i) {
                for (int j = 0; j < CUBE_DIMENSION; ++j) {
                    RubikCubeFaceColor c;
                    if (s.equals(RubikCubeSide.UP) && i == 2) {
                        c = RubikCubeFaceColor.GREEN;
                    } else if (s.equals(RubikCubeSide.RIGHT) && j == 0) {
                        c = RubikCubeFaceColor.WHITE;
                    } else if (s.equals(RubikCubeSide.DOWN) && i == 0) {
                        c = RubikCubeFaceColor.BLUE;
                    } else if (s.equals(RubikCubeSide.LEFT) && j == 2) {
                        c = RubikCubeFaceColor.YELLOW;
                    } else {
                        c = s.getStandardColor();
                    }
                    Assert.assertEquals("The (" + s.getDescription() + ","
                            + i + "," + j + ") face is correct", c,
                            this.cube.getFace(s, i, j));
                }
            }
        }
        Assert.assertTrue("Reversed F move brings cube back to default",
                            checkReverseToStandard(m));
    }
    /**
     * Performs four moves in each direction and checks that the cube goes back
     * to the standard configuration.
     */
    @Test
    public final void checkFourMoves() {
        Move move = new L(this.cube);
        for (int i = 0; i < 3; ++i) {
            move.perform();
        }
        Assert.assertTrue("L performed four times is the identity",
                          RubikCubeModelTest.isInStandardConfiguration(this.cube));
        this.cube.resetToStandardConfiguration();
        move = new R(this.cube);
        for (int i = 0; i < 3; ++i) {
            move.perform();
        }
        Assert.assertTrue("R performed four times is the identity",
                          RubikCubeModelTest.isInStandardConfiguration(this.cube));
        this.cube.resetToStandardConfiguration();
        move = new U(this.cube);
        for (int i = 0; i < 3; ++i) {
            move.perform();
        }
        Assert.assertTrue("U performed four times is the identity",
                          RubikCubeModelTest.isInStandardConfiguration(this.cube));
        this.cube.resetToStandardConfiguration();
        move = new D(this.cube);
        for (int i = 0; i < 3; ++i) {
            move.perform();
        }
        Assert.assertTrue("D performed four times is the identity",
                          RubikCubeModelTest.isInStandardConfiguration(this.cube));
        this.cube.resetToStandardConfiguration();
        move = new B(this.cube);
        for (int i = 0; i < 3; ++i) {
            move.perform();
        }
        Assert.assertTrue("B performed four times is the identity",
                          RubikCubeModelTest.isInStandardConfiguration(this.cube));
        this.cube.resetToStandardConfiguration();
        move = new F(this.cube);
        for (int i = 0; i < 3; ++i) {
            move.perform();
        }
        Assert.assertTrue("F performed four times is the identity",
                          RubikCubeModelTest.isInStandardConfiguration(this.cube));
        this.cube.resetToStandardConfiguration();
    }
    /**
     * Checks whether the reverse of the move puts the cube in the standard
     * configuration.
     * @param m Move to be checked.
     * @return <tt>true</tt> if and only if the cube is in the standard
     * configuration.
     */
    private boolean checkReverseToStandard(final Move m) {
        m.reverse();
        return RubikCubeModelTest.isInStandardConfiguration(this.cube);
    }
}