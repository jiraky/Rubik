package it.univr.rubikcube.model;

import java.util.Observable;

/**
 * Rubik cube data model.
 * @author Alessandro Menti
 */
public class RubikCubeModel extends Observable {
    /**
     * Cube dimension.
     */
    private int dimension;
    /**
     * Array storing the cube configuration.
     */
    private RubikCubeFaceColor[][][] configuration;
    /**
     * Creates a new instance of a Rubik cube model.
     * @param dim The cube dimension.
     * @throws IllegalArgumentException Thrown if the cube has an unacceptable
     * dimension (less than two).
     */
    public RubikCubeModel(final int dim) {
        this.dimension = 0; // Force the data structures to be initialized
        setDimension(dim);
    }
    /**
     * Gets the dimension of this cube.
     * @return Dimension of the cube.
     */
    public final int getDimension() {
        return this.dimension;
    }
    /**
     * Sets the dimension of this cube. If the value changes, the cube is
     * reinitialized in the standard configuration.
     * @param dim Dimension of the cube.
     * @throws IllegalArgumentException Thrown if the cube has an unacceptable
     * dimension (less than two).
     */
    public final void setDimension(final int dim) {
        if (dim < 2) {
            throw new IllegalArgumentException("The dimension must be two or"
                                               + " greater.");
        }
        if (dim != this.dimension) {
            // The value has changed, reinitialize the data structures.
            this.dimension = dim;
            notifyObservers(new RubikCubeModelDimensionChanged(dim));
            this.configuration = new RubikCubeFaceColor[RubikCubeSide.values()
                                                        .length][dim][dim];
            resetToStandardConfiguration();
        }
    }
    /**
     * Gets the color of a face.
     * @param s Side of the cube.
     * @param x X coordinate of the face.
     * @param y Y coordinate of the face.
     * @return Color of the specified face.
     */
    public final RubikCubeFaceColor getFace(final RubikCubeSide s, final int x,
                                            final int y) {
        return this.configuration[s.getValue()][x][y];
    }
    /**
     * <p>Resets the cube to the <em>standard configuration</em>:</p>
     * <ul>
     * <li>all faces on each side have the same color;</li>
     * <li>the top face is white;</li>
     * <li>the front face is red;</li>
     * <li>the left face is green;</li>
     * <li>the right face is blue;</li>
     * <li>the bottom face is yellow;</li>
     * <li>the back face is orange.</li>
     * </ul>
     */
    public final void resetToStandardConfiguration() {
        for (RubikCubeSide s : RubikCubeSide.values()) {
            for (int i = 0; i < this.dimension; ++i) {
                for (int j = 0; j < this.dimension; ++j) {
                    this.configuration[s.getValue()][i][j] = s
                            .getStandardColor();
                    notifyObservers(new RubikCubeModelFaceChanged(i, j, s));
                }
            }
        }
    }
    /**
     * Rotates a row of the Rubik cube.
     * @param index Row index.
     * @param rotation Direction of the rotation.
     * @throws IndexOutOfBoundsException Thrown if <tt>index</tt> is greater or
     * equal to the dimension of the cube or is less than zero.
     * @throws IllegalArgumentException Thrown if <tt>rotation</tt> has an
     * invalid value.
     */
    // FIXME: check the orientation (refer to http://www.cems.uvm.edu/~rsnapp/teaching/cs32/lectures/rubik.pdf)!
    public final void rotateRow(final int index, final RowRotation rotation) {
        RubikCubeFaceColor[] tmpRow;
        if (index >= this.dimension || index < 0) {
            throw new IndexOutOfBoundsException("The row index must be between"
                    + " 0 and dimension - 1.");
        }
        tmpRow = new RubikCubeFaceColor[this.dimension];
        // Backup the front row and rotate the row in the specified direction
        tmpRow = this.configuration[RubikCubeSide.FRONT.getValue()][index];
        if (rotation == RowRotation.ANTICLOCKWISE) {
            this.configuration[RubikCubeSide.FRONT.getValue()][index] =
                    this.configuration[RubikCubeSide.LEFT.getValue()][index];
            this.configuration[RubikCubeSide.LEFT.getValue()][index] =
                    this.configuration[RubikCubeSide.BACK.getValue()][index];
            this.configuration[RubikCubeSide.BACK.getValue()][index] =
                    this.configuration[RubikCubeSide.RIGHT.getValue()][index];
            this.configuration[RubikCubeSide.RIGHT.getValue()][index] = tmpRow;
        } else if (rotation == RowRotation.CLOCKWISE) {
            this.configuration[RubikCubeSide.FRONT.getValue()][index] =
                    this.configuration[RubikCubeSide.RIGHT.getValue()][index];
            this.configuration[RubikCubeSide.RIGHT.getValue()][index] =
                    this.configuration[RubikCubeSide.BACK.getValue()][index];
            this.configuration[RubikCubeSide.BACK.getValue()][index] =
                    this.configuration[RubikCubeSide.LEFT.getValue()][index];
            this.configuration[RubikCubeSide.LEFT.getValue()][index] = tmpRow;
        } else {
            throw new IllegalArgumentException();
        }
        // Notify the listeners that the row has changed
        notifyObservers(new RubikCubeModelRowRotated(index, rotation));
    }
    /**
     * Rotates a column of the Rubik cube.
     * @param index Column index.
     * @param rotation Direction of the rotation.
     * @throws IndexOutOfBoundsException Thrown if <tt>index</tt> is greater or
     * equal to the dimension of the cube or is less than zero.
     * @throws IllegalArgumentException Thrown if <tt>rotation</tt> has an
     * invalid value.
     */
    // FIXME: check the orientation (refer to http://www.cems.uvm.edu/~rsnapp/teaching/cs32/lectures/rubik.pdf)!
    public final void rotateColumn(final int index,
                                   final ColumnRotation rotation) {
        RubikCubeFaceColor[] tmpCol;
        if (index >= this.dimension || index < 0) {
            throw new IndexOutOfBoundsException("The column index must be"
                    + " between 0 and dimension - 1.");
        }
        tmpCol = new RubikCubeFaceColor[this.dimension];
        // Backup the front column and rotate the column in the specified
        // direction
        for (int i = 0; i < this.dimension; ++i) {
            tmpCol[i] = this.configuration[RubikCubeSide.FRONT.getValue()][i]
                    [index];
        }
        if (rotation == ColumnRotation.BOTTOM) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.FRONT.getValue()][i][index]
                        = this.configuration[RubikCubeSide.UP.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.getValue()][i][index]
                        = this.configuration[RubikCubeSide.BACK.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.BACK.getValue()][i][index]
                        = this.configuration[RubikCubeSide.DOWN.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.getValue()][i][index]
                        = tmpCol[i];
            }
        } else if (rotation == ColumnRotation.TOP) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.FRONT.getValue()][i][index]
                        = this.configuration[RubikCubeSide.DOWN.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.getValue()][i][index]
                        = this.configuration[RubikCubeSide.BACK.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.BACK.getValue()][i][index]
                        = this.configuration[RubikCubeSide.UP.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.getValue()][i][index]
                        = tmpCol[i];
            }
        } else {
            throw new IllegalArgumentException();
        }
        // Notify the listeners that the column has changed.
        notifyObservers(new RubikCubeModelColumnRotated(index, rotation));
    }
    /**
     * Rotates the entire cube in the specified direction.
     * @param rotation Direction of the rotation.
     * @throws IllegalArgumentException Thrown if <tt>rotation</tt> is an
     * invalid direction.
     */
    // FIXME: check the orientation (refer to http://www.cems.uvm.edu/~rsnapp/teaching/cs32/lectures/rubik.pdf)!
    public final void rotateCube(final CubeRotation rotation) {
        // Save the front side in a temporary variable since this move is the
        // same for every rotation
        RubikCubeFaceColor[][] tmp = this.configuration[RubikCubeSide.FRONT
                                                        .getValue()];
        switch (rotation) {
            case UPWISE:
                this.configuration[RubikCubeSide.FRONT.getValue()]
                        = this.configuration[RubikCubeSide.DOWN.getValue()];
                this.configuration[RubikCubeSide.DOWN.getValue()]
                        = this.configuration[RubikCubeSide.BACK.getValue()];
                this.configuration[RubikCubeSide.BACK.getValue()]
                        = this.configuration[RubikCubeSide.UP.getValue()];
                this.configuration[RubikCubeSide.UP.getValue()] = tmp;
                break;
            case DOWNWISE:
                this.configuration[RubikCubeSide.FRONT.getValue()]
                        = this.configuration[RubikCubeSide.UP.getValue()];
                this.configuration[RubikCubeSide.UP.getValue()]
                        = this.configuration[RubikCubeSide.BACK.getValue()];
                this.configuration[RubikCubeSide.BACK.getValue()]
                        = this.configuration[RubikCubeSide.DOWN.getValue()];
                this.configuration[RubikCubeSide.DOWN.getValue()] = tmp;
                break;
            case CLOCKWISE:
                this.configuration[RubikCubeSide.FRONT.getValue()]
                        = this.configuration[RubikCubeSide.LEFT.getValue()];
                this.configuration[RubikCubeSide.LEFT.getValue()]
                        = this.configuration[RubikCubeSide.BACK.getValue()];
                this.configuration[RubikCubeSide.BACK.getValue()]
                        = this.configuration[RubikCubeSide.RIGHT.getValue()];
                this.configuration[RubikCubeSide.RIGHT.getValue()] = tmp;
                break;
            case ANTICLOCKWISE:
                this.configuration[RubikCubeSide.FRONT.getValue()]
                        = this.configuration[RubikCubeSide.RIGHT.getValue()];
                this.configuration[RubikCubeSide.RIGHT.getValue()]
                        = this.configuration[RubikCubeSide.BACK.getValue()];
                this.configuration[RubikCubeSide.BACK.getValue()]
                        = this.configuration[RubikCubeSide.LEFT.getValue()];
                this.configuration[RubikCubeSide.LEFT.getValue()] = tmp;
                break;
            default:
                throw new IllegalArgumentException();
        }
        // Notify the listeners that the cube was rotated.
        notifyObservers(new RubikCubeModelCubeRotated(rotation));
    }
    // FIXME Rotate lateral column
}
