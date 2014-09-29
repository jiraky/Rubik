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
     * 
     * The array is structured as follows.
     * <ul>
     * <li>The first index is the side index (use
     * <tt>RubikCubeSide.getValue()</tt> to get the array index corresponding
     * to a side).</li>
     * <li>The second index is the row index (<tt>0</tt> to <tt>getDimension()
     * - 1</tt>).</li>
     * <li>The third index is the column index (<tt>0</tt> to
     * <tt>getDimension() - 1</tt>).</li>
     * </ul>
     * 
     * Rows are numbered progressively from top to bottom and columns are
     * numbered from the left to the right.
     */
    private RubikCubeFaceColor[][][] configuration;
    /**
     * Creates a new instance of a Rubik cube model.
     * @param dim The cube dimension.
     * @throws IllegalArgumentException Thrown if the cube has an unacceptable
     * dimension (less than two).
     */
    public RubikCubeModel(final int dim) throws IllegalArgumentException {
        // Force the data structures to be initialized
        this.dimension = 0;
        this.setDimension(dim);
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
    public final void setDimension(final int dim)
            throws IllegalArgumentException {
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
            this.resetToStandardConfiguration();
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
    public final void rotateRow(final int index, final RowRotation rotation)
            throws IndexOutOfBoundsException, IllegalArgumentException {
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
    public final void rotateColumn(final int index,
                                   final ColumnRotation rotation)
            throws IndexOutOfBoundsException, IllegalArgumentException {
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
                        = this.configuration[RubikCubeSide.BACK.getValue()][this.dimension - 1 - i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.BACK.getValue()][this.dimension - 1 - i][this.dimension - 1 - index]
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
                        = this.configuration[RubikCubeSide.BACK.getValue()][this.dimension - 1 - i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.BACK.getValue()][this.dimension - 1 - i][this.dimension - 1 - index]
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
     * Rotates a lateral column of the Rubik cube.
     * @param index Column index (counted from front to back on the right side).
     * @param rotation Direction of the rotation.
     * @throws IndexOutOfBoundsException Thrown if <tt>index</tt> is greater or
     * equal to the dimension of the cube or is less than zero.
     * @throws IllegalArgumentException Thrown if <tt>rotation</tt> has an
     * invalid value.
     */
    public final void rotateLateralColumn(final int index,
                                          final LateralColumnRotation rotation)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        this.rotateLateralColumnInternal(index, rotation, true);
    }
    /**
     * Rotates a lateral column of the Rubik cube.
     * @param index Column index (counted from front to back on the right side).
     * @param rotation Direction of the rotation.
     * @param fireEvents Whether the <tt>RubikCubeModelLateralColumnRotated</tt>
     * event should be fired or not.
     * @throws IndexOutOfBoundsException Thrown if <tt>index</tt> is greater or
     * equal to the dimension of the cube or is less than zero.
     * @throws IllegalArgumentException Thrown if <tt>rotation</tt> has an
     * invalid value.
     */
    private void rotateLateralColumnInternal(final int index,
                                             final LateralColumnRotation rotation,
                                             final boolean fireEvents)
            throws IndexOutOfBoundsException, IllegalArgumentException {
        RubikCubeFaceColor[] tmpLatCol;
        if (index >= this.dimension || index < 0) {
            throw new IndexOutOfBoundsException("The lateral column index must"
                    + " be between 0 and dimension - 1.");
        }
        tmpLatCol = new RubikCubeFaceColor[this.dimension];
        // Backup the row on the upper face and rotate the lateral column in
        // the specified direction
        for (int i = 0; i < this.dimension; ++i) {
            tmpLatCol[i] = this.configuration[RubikCubeSide.UP.getValue()]
                    [this.dimension - 1 - index][i];
        }
        if (rotation == LateralColumnRotation.LEFT) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.getValue()][this.dimension - 1 - index][i] =
                        this.configuration[RubikCubeSide.RIGHT.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.RIGHT.getValue()][this.dimension - 1 - i][index] =
                        this.configuration[RubikCubeSide.DOWN.getValue()][index][i];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.getValue()][index][i] =
                        this.configuration[RubikCubeSide.LEFT.getValue()][i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.LEFT.getValue()][this.dimension - 1 - i][this.dimension - 1 - index] =
                        tmpLatCol[i];
            }
        } else if (rotation == LateralColumnRotation.RIGHT) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.getValue()][this.dimension - 1 - index][i] =
                        this.configuration[RubikCubeSide.LEFT.getValue()][this.dimension - 1 - i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.LEFT.getValue()][i][this.dimension - 1 - index] =
                        this.configuration[RubikCubeSide.DOWN.getValue()][index][i];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.getValue()][index][this.dimension - 1 - i] =
                        this.configuration[RubikCubeSide.RIGHT.getValue()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.RIGHT.getValue()][i][index] =
                        tmpLatCol[i];
            }
        } else {
            throw new IllegalArgumentException();
        }
        // Notify the listeners that the lateral column has changed, if needed.
        if (fireEvents) {
            notifyObservers(new RubikCubeModelLateralColumnRotated(index,
                                                                   rotation));
        }
    }
    /**
     * Rotates the entire cube in the specified direction.
     * @param rotation Direction of the rotation.
     * @throws IllegalArgumentException Thrown if <tt>rotation</tt> is an
     * invalid direction.
     */
    public final void rotateCube(final CubeRotation rotation)
            throws IllegalArgumentException {
        // Save the front side in a temporary variable since this move is the
        // same for every rotation
        final RubikCubeFaceColor[][] tmp =
                new RubikCubeFaceColor[this.dimension][this.dimension];
        this.copyArray(this.configuration[RubikCubeSide.FRONT.getValue()], tmp);
        switch (rotation) {
            case UPWISE:
                this.copyArray(this.configuration[RubikCubeSide.DOWN.getValue()],
                               this.configuration[RubikCubeSide.FRONT.getValue()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.getValue()],
                               this.configuration[RubikCubeSide.DOWN.getValue()],
                               true);
                this.copyArray(this.configuration[RubikCubeSide.UP.getValue()],
                               this.configuration[RubikCubeSide.BACK.getValue()],
                               true);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.UP.getValue()]);
                break;
            case DOWNWISE:
                this.copyArray(this.configuration[RubikCubeSide.UP.getValue()],
                               this.configuration[RubikCubeSide.FRONT.getValue()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.getValue()],
                               this.configuration[RubikCubeSide.UP.getValue()],
                               true);
                this.copyArray(this.configuration[RubikCubeSide.DOWN.getValue()],
                               this.configuration[RubikCubeSide.BACK.getValue()],
                               true);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.DOWN.getValue()]);
                break;
            case CLOCKWISE:
                this.copyArray(this.configuration[RubikCubeSide.RIGHT.getValue()],
                               this.configuration[RubikCubeSide.FRONT.getValue()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.getValue()],
                               this.configuration[RubikCubeSide.RIGHT.getValue()]);
                this.copyArray(this.configuration[RubikCubeSide.LEFT.getValue()],
                               this.configuration[RubikCubeSide.BACK.getValue()]);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.LEFT.getValue()]);
                break;
            case ANTICLOCKWISE:
                this.copyArray(this.configuration[RubikCubeSide.LEFT.getValue()],
                               this.configuration[RubikCubeSide.FRONT.getValue()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.getValue()],
                               this.configuration[RubikCubeSide.LEFT.getValue()]);
                this.copyArray(this.configuration[RubikCubeSide.RIGHT.getValue()],
                               this.configuration[RubikCubeSide.BACK.getValue()]);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.RIGHT.getValue()]);
                break;
            case CLOCKWISE_FROM_FRONT:
                for (int i = 0; i < this.dimension; ++i) {
                    this.rotateLateralColumnInternal(i,
                                                     LateralColumnRotation.RIGHT,
                                                     false);
                }
                break;
            case ANTICLOCKWISE_FROM_FRONT:
                for (int i = 0; i < this.dimension; ++i) {
                    this.rotateLateralColumnInternal(i,
                                                     LateralColumnRotation.LEFT,
                                                     false);
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        // Notify the listeners that the cube was rotated.
        notifyObservers(new RubikCubeModelCubeRotated(rotation));
    }
    /**
     * Gets a textual representation of the cube, suitable for printing.
     * @return Textual representation of the cube.
     */
    @Override
    public final String toString() {
        final StringBuilder sb = new StringBuilder("");
        for (int i = this.dimension; i >= 0; --i) {
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(" ");
            }
            sb.append(" | ");
            for (int j = this.dimension; j >= 0; --j) {
                sb.append(this.configuration[RubikCubeSide.BACK.getValue()][i][j]);
            }
            sb.append(" |\n");
        }
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < this.dimension; ++k) {
                sb.append("-");
            }
            sb.append("---");
        }
        for (int j = 0; j < this.dimension; ++j) {
            sb.append("-");
        }
        sb.append("\n");
        for (int i = 0; i <= this.dimension; ++i) {
            for (int j = this.dimension; j >= 0; --j) {
                sb.append(this.configuration[RubikCubeSide.LEFT.getValue()][j][i]);
            }
            sb.append(" | ");
            for (int j = 0; j <= this.dimension; ++j) {
                System.out.print(this.configuration[RubikCubeSide.UP.getValue()][i][j]);
            }
            sb.append(" | ");
            for (int j = 0; j <= this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.RIGHT.getValue()][j][this.dimension - i]);
            }
            sb.append(" | ");
            for (int j = this.dimension; j >= 0; --j) {
                sb.append(this.configuration[RubikCubeSide.DOWN.getValue()][this.dimension - i][j]);
            }
            sb.append("\n");
        }
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < this.dimension; ++k) {
                sb.append("-");
            }
            sb.append("---");
        }
        for (int j = 0; j < this.dimension; ++j) {
            sb.append("-");
        }
        sb.append("\n");
        for (int i = 0; i <= this.dimension; ++i) {
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(" ");
            }
            sb.append(" | ");
            for (int j = 0; j <= this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.FRONT.getValue()][i][j]);
            }
            sb.append(" |\n");
        }
        return sb.toString();
    }
    /**
     * Deep copies a face of the Rubik cube to another face.
     * @param src Source face.
     * @param dst Destination face.
     */
    private void copyArray(final RubikCubeFaceColor[][] src,
                           final RubikCubeFaceColor[][] dst) {
        this.copyArray(src, dst, false);
    }
    /**
     * Deep copies a face of the Rubik cube to another face.
     * @param src Source face.
     * @param dst Destination face.
     * @param sw Whether to switch the face (perform the back rotation).
     */
    private void copyArray(final RubikCubeFaceColor[][] src,
                           final RubikCubeFaceColor[][] dst,
                           final boolean sw) {
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension; ++j) {
                if (sw) {
                    dst[this.dimension - 1 - i][this.dimension - 1 - j]
                            = src[i][j];
                } else {
                    dst[i][j] = src[i][j];
                }
            }
        }
    }
}
