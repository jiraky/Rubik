package it.univr.rubikcube.model;

import java.util.Observable;

/**
 * Rubik cube data model.
 * @author Alessandro Menti
 */
public class RubikCubeModel extends Observable {
    /*
     * To help you visualize the cube, you might want to refer to The Cube
     * Guru (http://thecube.guru/online-3d-rubiks-cube/).
     * Import a string such as this one (standard configuration):
     * 4,4,4,4,4,4,4,4,4,7,7,7,7,7,7,7,7,7,2,2,2,2,2,2,2,2,2,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3
     * Each group of nine numbers is a side (sides are listed in this order:
     * left, back, right, front, down, up). On each side, faces are listed
     * in this order:
     * 789
     * 456
     * 123
     * The colors are:
     * 2 = blue
     * 3 = white
     * 4 = green
     * 5 = yellow
     * 6 = red
     * 7 = orange
     */
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
     * <tt>RubikCubeSide.ordinal()</tt> to get the array index corresponding
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
     * Creates a new instance of a Rubik cube model using an existing model
     * (copy constructor).
     * @param m The existing model.
     * @throws IllegalArgumentException Thrown if the cube has an unacceptable
     * dimension (less than two).
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public RubikCubeModel(final RubikCubeModel m)
            throws IllegalArgumentException, NullPointerException {
        if (m == null) {
            throw new NullPointerException("The model can not be null");
        }
        // Force the data structures to be initialized
        this.dimension = 0;
        this.setDimension(m.getDimension());
        // Copy the configuration.
        for (RubikCubeSide s: RubikCubeSide.values()) {
            for (int i = 0; i < m.getDimension(); ++i) {
                for (int j = 0; j < m.getDimension(); ++j) {
                    this.configuration[s.ordinal()][i][j] = m.getFace(s, i, j);
                }
            }
        }
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
     * Gets the side having a given color.
     * @param c The color to be searched for.
     * @return The side having color <tt>c</tt>, or <tt>null</tt> if no such
     * color was found.
     */
    public final RubikCubeSide getSide(final RubikCubeFaceColor c) {
        RubikCubeSide side = null;
        final int halfDim = this.dimension / 2;
        for (RubikCubeSide s: RubikCubeSide.values()) {
            if (this.configuration[s.ordinal()][halfDim][halfDim] == c) {
                side = s;
                break;
            }
        }
        return side;
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
        return this.configuration[s.ordinal()][x][y];
    }
    /**
     * Sets the color of a face. <strong>WARNING:</strong> this may leave the
     * cube in an inconsistent state. It is your responsibility to check that
     * what you do is valid.
     * @param s Side of the cube.
     * @param x X coordinate of the face.
     * @param y Y coordinate of the face.
     * @param c Color to be set.
     */
    public final void setFace(final RubikCubeSide s, final int x,
                              final int y, final RubikCubeFaceColor c) {
        this.configuration[s.ordinal()][x][y] = c;
    }
    /**
     * Gets the color of two edge facelets.
     * @param e Edge to be considered.
     * @return The color of the two facelets making the edge.
     * @throws IllegalArgumentException Thrown in case the edge is not in the
     * enum.
     * @throws IllegalStateException Thrown in case the cube is not a 3x3 one.
     */
    public final RubikCubeEdgeColor get3DEdge(final RubikCubeModel3Edge e)
            throws IllegalArgumentException, IllegalStateException {
        if (this.dimension != 3) {
            throw new IllegalStateException("The cube must be a 3x3 one");
        }
        switch (e) {
            case UR:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][1][2],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][0][1]);
            case UF:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][2][1],
                    this.configuration[RubikCubeSide.FRONT.ordinal()][0][1]);
            case UL:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][1][0],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][0][1]);
            case UB:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][0][1],
                    this.configuration[RubikCubeSide.BACK.ordinal()][0][1]);
            case DR:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][1][2],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][2][1]);
            case DF:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][0][1],
                    this.configuration[RubikCubeSide.FRONT.ordinal()][2][1]);
            case DL:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][1][0],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][2][1]);
            case DB:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][2][1],
                    this.configuration[RubikCubeSide.BACK.ordinal()][2][1]);
            case FR:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.FRONT.ordinal()][1][2],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][1][0]);
            case FL:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.FRONT.ordinal()][1][0],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][1][2]);
            case BL:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.BACK.ordinal()][1][2],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][1][0]);
            case BR:
                return new RubikCubeEdgeColor(
                    this.configuration[RubikCubeSide.BACK.ordinal()][1][2],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][1][0]);
            default:
                throw new IllegalArgumentException("Edge not in enum");
        }
    }
    /**
     * Gets the color of an edge facelet.
     * @param a Axis to be considered.
     * @param e Edge to be considered.
     * @return The color of the two facelets making the edge.
     * @throws IllegalArgumentException Thrown in case the edge is not in the
     * enum.
     * @throws IllegalStateException Thrown in case the cube is not a 3x3 one.
     */
    public final RubikCubeFaceColor get3DEdgeFacelet(final RubikCubeModelAxis a,
            final RubikCubeModel3Edge e) throws IllegalArgumentException, IllegalStateException {
        if (this.dimension != 3) {
            throw new IllegalStateException("The cube must be a 3x3 one");
        }
        switch (e) {
            // TODO: check
            case UR:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][1][2];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][0][1]; 
                } else {
                    return null;
                }
            case UF:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][2][1];
                } else if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][0][1]; 
                } else {
                    return null;
                }
            case UL:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][1][0];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][0][1]; 
                } else {
                    return null;
                }
            case UB:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][0][1];
                } else if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][0][1]; 
                } else {
                    return null;
                }
            case DR:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][1][2];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][2][1]; 
                } else {
                    return null;
                }
            case DF:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][0][1];
                } else if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][2][1];
                } else {
                    return null;
                }
            case DL:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][1][0];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][2][1]; 
                } else {
                    return null;
                }
            case DB:
                if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][2][1];
                } else if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][2][1]; 
                } else {
                    return null;
                }
            case FR:
                if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][1][2];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][1][0]; 
                } else {
                    return null;
                }
            case FL:
                if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][1][0];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][1][2]; 
                } else {
                    return null;
                }
            case BL:
                if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][1][2];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][1][0]; 
                } else {
                    return null;
                }
            case BR:
                if (a == RubikCubeModelAxis.Z) {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][1][2];
                } else if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][1][0]; 
                } else {
                    return null;
                }
            default:
                throw new IllegalArgumentException("Edge not in enum");
        }
    }
    /**
     * Gets the color of the facelets in a corner.
     * @param c Corner to be considered.
     * @return The color of the three facelets making the corner.
     * @throws IllegalArgumentException Thrown in case the corner is not in the
     * enum.
     * @throws IllegalStateException Thrown in case the cube is not a 3x3 one.
     */
    public final RubikCubeCornerColor getCorner(final RubikCubeCorner c)
        throws IllegalArgumentException, IllegalStateException {
        if (this.dimension != 3) {
            throw new IllegalStateException("The cube must be a 3x3 one");
        }
        switch (c) {
            case URF:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][2][2],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][0][0],
                    this.configuration[RubikCubeSide.FRONT.ordinal()][0][2]);
            case UFL:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][2][0],
                    this.configuration[RubikCubeSide.FRONT.ordinal()][0][0],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][0][2]);
            case ULB:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][0][0],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][0][0],
                    this.configuration[RubikCubeSide.BACK.ordinal()][0][2]);
            case UBR:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.UP.ordinal()][0][2],
                    this.configuration[RubikCubeSide.BACK.ordinal()][0][0],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][0][2]);
            case DFR:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][0][2],
                    this.configuration[RubikCubeSide.FRONT.ordinal()][2][2],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][2][0]);
            case DLF:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][0][0],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][2][2],
                    this.configuration[RubikCubeSide.FRONT.ordinal()][2][0]);
            case DBL:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][2][0],
                    this.configuration[RubikCubeSide.BACK.ordinal()][2][2],
                    this.configuration[RubikCubeSide.LEFT.ordinal()][2][0]);
            case DRB:
                return new RubikCubeCornerColor(
                    this.configuration[RubikCubeSide.DOWN.ordinal()][2][2],
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][2][2],
                    this.configuration[RubikCubeSide.BACK.ordinal()][2][0]);
            default:
                throw new IllegalArgumentException("Corner not in enum");
        }
    }
    /**
     * Gets the color of a facelet given an axis and a corner.
     * @param a Axis to be considered.
     * @param c Corner to be considered.
     * @return The color of the three facelets making the corner.
     * @throws IllegalArgumentException Thrown in case the corner is not in the
     * enum.
     * @throws IllegalStateException Thrown in case the cube is not a 3x3 one.
     */
    public final RubikCubeFaceColor getCornerFacelet(final RubikCubeModelAxis a,
        final RubikCubeCorner c) throws IllegalArgumentException, IllegalStateException {
        if (this.dimension != 3) {
            throw new IllegalStateException("The cube must be a 3x3 one");
        }
        if (a != RubikCubeModelAxis.X && a != RubikCubeModelAxis.Y && a != RubikCubeModelAxis.Z) {
            throw new IllegalArgumentException("Axis not in enum");
        }
        switch (c) {
            case URF:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][0][0];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][2][2];
                } else {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][0][2];
                }
            case UFL:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][0][2];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][2][0];
                } else {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][0][0];
                }
            case ULB:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][0][0];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][0][0];
                } else {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][0][2];
                }
            case UBR:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][0][2];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.UP.ordinal()][0][2];
                } else {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][0][0];
                }
            case DFR:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][2][0];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][0][2];
                } else {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][2][2];
                }
            case DLF:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][2][2];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][0][0];
                } else {
                    return this.configuration[RubikCubeSide.FRONT.ordinal()][2][0];
                }
            case DBL:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.LEFT.ordinal()][2][0];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][2][0];
                } else {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][2][2];
                }
            case DRB:
                if (a == RubikCubeModelAxis.X) {
                    return this.configuration[RubikCubeSide.RIGHT.ordinal()][2][2];
                } else if (a == RubikCubeModelAxis.Y) {
                    return this.configuration[RubikCubeSide.DOWN.ordinal()][2][2];
                } else {
                    return this.configuration[RubikCubeSide.BACK.ordinal()][2][0];
                }
            default:
                throw new IllegalArgumentException("Corner not in enum");
        }
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
                    this.configuration[s.ordinal()][i][j] = s
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
        for (int i = 0; i < this.dimension; ++i) {
            tmpRow[i] = this.configuration[RubikCubeSide.FRONT.ordinal()][index][i];
        }
        if (rotation == RowRotation.ANTICLOCKWISE) {
            this.configuration[RubikCubeSide.FRONT.ordinal()][index] =
                    this.configuration[RubikCubeSide.LEFT.ordinal()][index];
            this.configuration[RubikCubeSide.LEFT.ordinal()][index] =
                    this.configuration[RubikCubeSide.BACK.ordinal()][index];
            this.configuration[RubikCubeSide.BACK.ordinal()][index] =
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][index];
            this.configuration[RubikCubeSide.RIGHT.ordinal()][index] = tmpRow;
        } else if (rotation == RowRotation.CLOCKWISE) {
            this.configuration[RubikCubeSide.FRONT.ordinal()][index] =
                    this.configuration[RubikCubeSide.RIGHT.ordinal()][index];
            this.configuration[RubikCubeSide.RIGHT.ordinal()][index] =
                    this.configuration[RubikCubeSide.BACK.ordinal()][index];
            this.configuration[RubikCubeSide.BACK.ordinal()][index] =
                    this.configuration[RubikCubeSide.LEFT.ordinal()][index];
            this.configuration[RubikCubeSide.LEFT.ordinal()][index] = tmpRow;
        } else {
            throw new IllegalArgumentException();
        }
        // Rotate the top/bottom face if needed.
        if (index == 0) {
            final RubikCubeFaceColor[][] tmp = new RubikCubeFaceColor
                    [this.dimension][this.dimension];
            this.rotateFace(this.configuration[RubikCubeSide.UP.ordinal()],
                    tmp, rotation == RowRotation.ANTICLOCKWISE);
            this.copyArray(tmp, this.configuration[RubikCubeSide.UP.ordinal()]);
        } else if (index == this.dimension - 1) {
            final RubikCubeFaceColor[][] tmp = new RubikCubeFaceColor
                    [this.dimension][this.dimension];
            this.rotateFace(this.configuration[RubikCubeSide.DOWN.ordinal()],
                    tmp, rotation == RowRotation.CLOCKWISE);
            this.copyArray(tmp, this.configuration[RubikCubeSide.DOWN.ordinal()]);
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
            tmpCol[i] = this.configuration[RubikCubeSide.FRONT.ordinal()][i]
                    [index];
        }
        if (rotation == ColumnRotation.BOTTOM) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.FRONT.ordinal()][i][index]
                        = this.configuration[RubikCubeSide.UP.ordinal()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.ordinal()][i][index]
                        = this.configuration[RubikCubeSide.BACK.ordinal()][this.dimension - 1 - i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.BACK.ordinal()][this.dimension - 1 - i][this.dimension - 1 - index]
                        = this.configuration[RubikCubeSide.DOWN.ordinal()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.ordinal()][i][index]
                        = tmpCol[i];
            }
        } else if (rotation == ColumnRotation.TOP) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.FRONT.ordinal()][i][index]
                        = this.configuration[RubikCubeSide.DOWN.ordinal()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.ordinal()][i][index]
                        = this.configuration[RubikCubeSide.BACK.ordinal()][this.dimension - 1 - i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.BACK.ordinal()][this.dimension - 1 - i][this.dimension - 1 - index]
                        = this.configuration[RubikCubeSide.UP.ordinal()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.ordinal()][i][index]
                        = tmpCol[i];
            }
        } else {
            throw new IllegalArgumentException();
        }
        // Rotate the lateral faces if needed.
        if (index == 0) {
            final RubikCubeFaceColor[][] tmp = new RubikCubeFaceColor
                    [this.dimension][this.dimension];
            this.rotateFace(this.configuration[RubikCubeSide.LEFT.ordinal()],
                    tmp, rotation == ColumnRotation.TOP);
            this.copyArray(tmp, this.configuration[RubikCubeSide.LEFT.ordinal()]);
        } else if (index == this.dimension - 1) {
            final RubikCubeFaceColor[][] tmp = new RubikCubeFaceColor
                    [this.dimension][this.dimension];
            this.rotateFace(this.configuration[RubikCubeSide.RIGHT.ordinal()],
                    tmp, rotation == ColumnRotation.BOTTOM);
            this.copyArray(tmp, this.configuration[RubikCubeSide.RIGHT.ordinal()]);
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
            tmpLatCol[i] = this.configuration[RubikCubeSide.UP.ordinal()]
                    [this.dimension - 1 - index][i];
        }
        if (rotation == LateralColumnRotation.LEFT) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.ordinal()][this.dimension - 1 - index][i] =
                        this.configuration[RubikCubeSide.RIGHT.ordinal()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.RIGHT.ordinal()][this.dimension - 1 - i][index] =
                        this.configuration[RubikCubeSide.DOWN.ordinal()][index][i];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.ordinal()][index][i] =
                        this.configuration[RubikCubeSide.LEFT.ordinal()][i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.LEFT.ordinal()][this.dimension - 1 - i][this.dimension - 1 - index] =
                        tmpLatCol[i];
            }
        } else if (rotation == LateralColumnRotation.RIGHT) {
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.UP.ordinal()][this.dimension - 1 - index][i] =
                        this.configuration[RubikCubeSide.LEFT.ordinal()][this.dimension - 1 - i][this.dimension - 1 - index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.LEFT.ordinal()][i][this.dimension - 1 - index] =
                        this.configuration[RubikCubeSide.DOWN.ordinal()][index][i];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.DOWN.ordinal()][index][this.dimension - 1 - i] =
                        this.configuration[RubikCubeSide.RIGHT.ordinal()][i][index];
            }
            for (int i = 0; i < this.dimension; ++i) {
                this.configuration[RubikCubeSide.RIGHT.ordinal()][i][index] =
                        tmpLatCol[i];
            }
        } else {
            throw new IllegalArgumentException();
        }
        // Rotate the lateral faces if needed.
        if (index == 0) {
            final RubikCubeFaceColor[][] tmp = new RubikCubeFaceColor
                    [this.dimension][this.dimension];
            this.rotateFace(this.configuration[RubikCubeSide.FRONT.ordinal()],
                    tmp, rotation == LateralColumnRotation.LEFT);
            this.copyArray(tmp, this.configuration[RubikCubeSide.FRONT.ordinal()]);
        } else if (index == this.dimension - 1) {
            final RubikCubeFaceColor[][] tmp = new RubikCubeFaceColor
                    [this.dimension][this.dimension];
            this.rotateFace(this.configuration[RubikCubeSide.BACK.ordinal()],
                    tmp, rotation == LateralColumnRotation.RIGHT);
            this.copyArray(tmp, this.configuration[RubikCubeSide.BACK.ordinal()]);
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
        this.copyArray(this.configuration[RubikCubeSide.FRONT.ordinal()], tmp);
        switch (rotation) {
            case UPWISE:
                this.copyArray(this.configuration[RubikCubeSide.DOWN.ordinal()],
                               this.configuration[RubikCubeSide.FRONT.ordinal()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.ordinal()],
                               this.configuration[RubikCubeSide.DOWN.ordinal()],
                               true);
                this.copyArray(this.configuration[RubikCubeSide.UP.ordinal()],
                               this.configuration[RubikCubeSide.BACK.ordinal()],
                               true);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.UP.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.LEFT.ordinal()],
                                tmp, true);
                this.copyArray(tmp, this.configuration[RubikCubeSide.LEFT.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.RIGHT.ordinal()],
                        tmp, false);
                this.copyArray(tmp, this.configuration[RubikCubeSide.RIGHT.ordinal()]);
                break;
            case DOWNWISE:
                this.copyArray(this.configuration[RubikCubeSide.UP.ordinal()],
                               this.configuration[RubikCubeSide.FRONT.ordinal()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.ordinal()],
                               this.configuration[RubikCubeSide.UP.ordinal()],
                               true);
                this.copyArray(this.configuration[RubikCubeSide.DOWN.ordinal()],
                               this.configuration[RubikCubeSide.BACK.ordinal()],
                               true);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.DOWN.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.LEFT.ordinal()],
                        tmp, false);
                this.copyArray(tmp, this.configuration[RubikCubeSide.LEFT.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.RIGHT.ordinal()],
                        tmp, true);
                this.copyArray(tmp, this.configuration[RubikCubeSide.RIGHT.ordinal()]);
                break;
            case CLOCKWISE:
                this.copyArray(this.configuration[RubikCubeSide.RIGHT.ordinal()],
                               this.configuration[RubikCubeSide.FRONT.ordinal()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.ordinal()],
                               this.configuration[RubikCubeSide.RIGHT.ordinal()]);
                this.copyArray(this.configuration[RubikCubeSide.LEFT.ordinal()],
                               this.configuration[RubikCubeSide.BACK.ordinal()]);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.LEFT.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.UP.ordinal()],
                        tmp, true);
                this.copyArray(tmp, this.configuration[RubikCubeSide.UP.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.DOWN.ordinal()],
                        tmp, false);
                this.copyArray(tmp, this.configuration[RubikCubeSide.DOWN.ordinal()]);
                break;
            case ANTICLOCKWISE:
                this.copyArray(this.configuration[RubikCubeSide.LEFT.ordinal()],
                               this.configuration[RubikCubeSide.FRONT.ordinal()]);
                this.copyArray(this.configuration[RubikCubeSide.BACK.ordinal()],
                               this.configuration[RubikCubeSide.LEFT.ordinal()]);
                this.copyArray(this.configuration[RubikCubeSide.RIGHT.ordinal()],
                               this.configuration[RubikCubeSide.BACK.ordinal()]);
                this.copyArray(tmp,
                               this.configuration[RubikCubeSide.RIGHT.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.UP.ordinal()],
                        tmp, false);
                this.copyArray(tmp, this.configuration[RubikCubeSide.UP.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.DOWN.ordinal()],
                        tmp, true);
                this.copyArray(tmp, this.configuration[RubikCubeSide.DOWN.ordinal()]);
                break;
            case CLOCKWISE_FROM_FRONT:
                for (int i = 0; i < this.dimension; ++i) {
                    this.rotateLateralColumnInternal(i,
                                                     LateralColumnRotation.RIGHT,
                                                     false);
                }
                this.rotateFace(this.configuration[RubikCubeSide.FRONT.ordinal()],
                        tmp, false);
                this.copyArray(tmp, this.configuration[RubikCubeSide.FRONT.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.BACK.ordinal()],
                        tmp, true);
                this.copyArray(tmp, this.configuration[RubikCubeSide.BACK.ordinal()]);
                break;
            case ANTICLOCKWISE_FROM_FRONT:
                for (int i = 0; i < this.dimension; ++i) {
                    this.rotateLateralColumnInternal(i,
                                                     LateralColumnRotation.LEFT,
                                                     false);
                }
                this.rotateFace(this.configuration[RubikCubeSide.FRONT.ordinal()],
                        tmp, true);
                this.copyArray(tmp, this.configuration[RubikCubeSide.FRONT.ordinal()]);
                this.rotateFace(this.configuration[RubikCubeSide.BACK.ordinal()],
                        tmp, false);
                this.copyArray(tmp, this.configuration[RubikCubeSide.BACK.ordinal()]);
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
        // First separator
        for (int i = 0; i < this.dimension + 1; ++i) {
            sb.append(" ");
        }
        for (int i = 0; i < this.dimension + 2; ++i) {
            sb.append("-");
        }
        sb.append("\n");
        // Up face
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension + 1; ++j) {
                sb.append(" ");
            }
            sb.append("|");
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.UP.ordinal()][i][j]);
            }
            sb.append("|\n");
        }
        // Second separator
        for (int i = 0; i < (this.dimension + 1) * 4 + 1; ++i) {
            sb.append("-");
        }
        sb.append("\n");
        // Left, front, right, back faces
        for (int i = 0; i < this.dimension; ++i) {
            sb.append("|");
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.LEFT.ordinal()][i][j]);
            }
            sb.append("|");
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.FRONT.ordinal()][i][j]);
            }
            sb.append("|");
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.RIGHT.ordinal()][i][j]);
            }
            sb.append("|");
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.BACK.ordinal()][i][j]);
            }
            sb.append("|\n");
        }
        // Third separator
        for (int i = 0; i < (this.dimension + 1) * 4 + 1; ++i) {
            sb.append("-");
        }
        sb.append("\n");
        // Down face
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension + 1; ++j) {
                sb.append(" ");
            }
            sb.append("|");
            for (int j = 0; j < this.dimension; ++j) {
                sb.append(this.configuration[RubikCubeSide.DOWN.ordinal()][i][j]);
            }
            sb.append("|\n");
        }
        // Fourth separator
        for (int i = 0; i < (this.dimension + 1) * 4 + 1; ++i) {
            sb.append("-");
        }
        return sb.toString();
    }
    /**
     * Checks whether a cube is in the standard configuration (each face has
     * all subfaces of the same, standard face color).
     * @param m Model to be checked.
     * @return <tt>true</tt> if and only if the cube is in the standard
     * configuration.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public static boolean isInStandardConfiguration(final RubikCubeModel m)
        throws NullPointerException {
        if (m == null) {
            throw new NullPointerException("m must not be null");
        }
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
    /**
     * Checks whether a cube has sane colors (nine faces per color and one
     * facelet &ndash; central face &ndash; for each color).
     * @param m Model to be checked.
     * @return <tt>true</tt> if and only if the cube has sane colors.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public static boolean isWithSaneColors(final RubikCubeModel m)
        throws NullPointerException {
        if (m == null) {
            throw new NullPointerException("m must not be null");
        }
        final int[][] colorAndFaceletCount = new int[6][2];
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 2; ++j) {
                colorAndFaceletCount[i][j] = 0;
            }
        }
        for (RubikCubeSide s : RubikCubeSide.values()) {
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    ++colorAndFaceletCount[m.getFace(s, i, j).ordinal()][0];
                    if (i == 1 && j == 1) {
                        ++colorAndFaceletCount[m.getFace(s, i, j).ordinal()][1];
                    }
                }
            }
        }
        for (int i = 0; i < 6; ++i) {
            if (colorAndFaceletCount[i][0] != 9) {
                return false;
            }
            if (colorAndFaceletCount[i][1] != 1) {
                return false;
            }
        }
        return true;
    }
    /**
     * Checks whether a cube is solved.
     * @param m Model to be checked.
     * @return <tt>true</tt> if and only if the cube is solved and has sane
     * colors.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public static boolean isSolved(final RubikCubeModel m)
        throws NullPointerException {
        if (m == null) {
            throw new NullPointerException("m must not be null");
        }
        final boolean[] colorEncountered = new boolean[6];
        for (RubikCubeFaceColor c : RubikCubeFaceColor.values()) {
            colorEncountered[c.ordinal()] = false;
        }
        for (RubikCubeSide s : RubikCubeSide.values()) {
            final RubikCubeFaceColor sc = m.getFace(s, m.getDimension() / 2,
                                                    m.getDimension() / 2);
            if (colorEncountered[sc.ordinal()]) {
                // Duplicate color
                return false;
            }
            colorEncountered[sc.ordinal()] = true;
            for (int i = 0; i < m.getDimension(); ++i) {
                for (int j = 0; j < m.getDimension(); ++j) {
                    if (m.getFace(s, i, j) != sc) {
                        // Color different - cube not solved
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Checks whether a corner is in place (against row 0).
     * @param m Model to be checked.
     * @param c Corner to be checked.
     * @return <tt>true</tt> if and only if the corner is in place.
     * @throws IllegalArgumentException Thrown if <tt>c</tt> is not in the
     * enum.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public static boolean isCornerInPlace(final RubikCubeModel m,
        final RubikCubeCorner c) throws IllegalArgumentException,
        NullPointerException {
        if (m == null) {
            throw new NullPointerException("m must not be null");
        }
        final RubikCubeCornerColor col = m.getCorner(c);
        final int halfDim = m.getDimension() / 2;
        switch (c) {
            case URF:
                return col.getFirstColor() == m.getFace(RubikCubeSide.UP, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.RIGHT, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.FRONT, 0, halfDim);
            case UFL:
                return col.getFirstColor() == m.getFace(RubikCubeSide.UP, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.FRONT, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.LEFT, 0, halfDim);
            case ULB:
                return col.getFirstColor() == m.getFace(RubikCubeSide.UP, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.LEFT, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.BACK, 0, halfDim);
            case UBR:
                return col.getFirstColor() == m.getFace(RubikCubeSide.UP, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.BACK, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.RIGHT, 0, halfDim);
            case DFR:
                return col.getFirstColor() == m.getFace(RubikCubeSide.DOWN, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.FRONT, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.RIGHT, 0, halfDim);
            case DLF:
                return col.getFirstColor() == m.getFace(RubikCubeSide.DOWN, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.LEFT, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.FRONT, 0, halfDim);
            case DBL:
                return col.getFirstColor() == m.getFace(RubikCubeSide.DOWN, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.BACK, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.LEFT, 0, halfDim);
            case DRB:
                return col.getFirstColor() == m.getFace(RubikCubeSide.DOWN, 0, halfDim)
                    && col.getSecondColor() == m.getFace(RubikCubeSide.RIGHT, 0, halfDim)
                    && col.getThirdColor() == m.getFace(RubikCubeSide.BACK, 0, halfDim);
            default:
                throw new IllegalArgumentException("corner not in enum");
        }
    }
    /**
     * Checks whether a corner is in place (or in place but flipped, against
     * row 0).
     * @param m Model to be checked.
     * @param c Corner to be checked.
     * @return <tt>true</tt> if and only if the corner is in place/in place but
     * flipped.
     * @throws IllegalArgumentException Thrown if <tt>c</tt> is not in the
     * enum.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public static boolean isCornerInPlaceMaybeFlipped(final RubikCubeModel m,
        final RubikCubeCorner c) throws IllegalArgumentException,
        NullPointerException {
        if (m == null) {
            throw new NullPointerException("m must not be null");
        }
        final RubikCubeCornerColor col = m.getCorner(c);
        final int halfDim = m.getDimension() / 2;
        final RubikCubeSide[] faces = new RubikCubeSide[3];
        switch (c) {
            case URF:
                faces[0] = RubikCubeSide.UP;
                faces[1] = RubikCubeSide.RIGHT;
                faces[2] = RubikCubeSide.FRONT;
                break;
            case UFL:
                faces[0] = RubikCubeSide.UP;
                faces[1] = RubikCubeSide.FRONT;
                faces[2] = RubikCubeSide.LEFT;
                break;
            case ULB:
                faces[0] = RubikCubeSide.UP;
                faces[1] = RubikCubeSide.LEFT;
                faces[2] = RubikCubeSide.BACK;
                break;
            case UBR:
                faces[0] = RubikCubeSide.UP;
                faces[1] = RubikCubeSide.BACK;
                faces[2] = RubikCubeSide.RIGHT;
                break;
            case DFR:
                faces[0] = RubikCubeSide.DOWN;
                faces[1] = RubikCubeSide.FRONT;
                faces[2] = RubikCubeSide.RIGHT;
                break;
            case DLF:
                faces[0] = RubikCubeSide.DOWN;
                faces[1] = RubikCubeSide.LEFT;
                faces[2] = RubikCubeSide.FRONT;
                break;
            case DBL:
                faces[0] = RubikCubeSide.DOWN;
                faces[1] = RubikCubeSide.BACK;
                faces[2] = RubikCubeSide.LEFT;
                break;
            case DRB:
                faces[0] = RubikCubeSide.DOWN;
                faces[1] = RubikCubeSide.RIGHT;
                faces[2] = RubikCubeSide.BACK;
                break;
            default:
                throw new IllegalArgumentException("corner not in enum");
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i == j) {
                    continue;
                }
                for (int k = 0; k < 3; ++k) {
                    if (j == k) {
                        continue;
                    }
                    if (col.getFirstColor() == m.getFace(faces[i], 0, halfDim)
                        && col.getSecondColor() == m.getFace(faces[j], 0, halfDim)
                        && col.getThirdColor() == m.getFace(faces[k], 0, halfDim)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /**
     * Checks if an edge is in place.
     * @param m Cube to be checked.
     * @param e Edge to be checked.
     * @return <tt>true</tt> if and only if the edge is in place.
     * @throws IllegalArgumentException Thrown if <tt>e</tt> is not a supported
     * edge.
     * @throws NullPointerException Thrown if <tt>m</tt> is <tt>null</tt>.
     */
    public static boolean isEdgeInPlace(final RubikCubeModel m,
        final RubikCubeModel3Edge e) throws IllegalArgumentException,
        NullPointerException {
        if (m == null) {
            throw new NullPointerException("m must not be null");
        }
        switch (e) {
            case UR:
                return m.getFace(RubikCubeSide.UP, 1, 2) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.RIGHT, 0, 1) == e.getLateralSide().getStandardColor();
            case UF:
                return m.getFace(RubikCubeSide.UP, 2, 1) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.FRONT, 0, 1) == e.getLateralSide().getStandardColor();
            case UL:
                return m.getFace(RubikCubeSide.UP, 1, 0) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.LEFT, 0, 1) == e.getLateralSide().getStandardColor();
            case UB:
                return m.getFace(RubikCubeSide.UP, 0, 1) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.BACK, 0, 1) == e.getLateralSide().getStandardColor();
            case DR:
                return m.getFace(RubikCubeSide.DOWN, 1, 2) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.RIGHT, 2, 1) == e.getLateralSide().getStandardColor();
            case DF:
                return m.getFace(RubikCubeSide.DOWN, 0, 1) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.FRONT, 2, 1) == e.getLateralSide().getStandardColor();
            case DL:
                return m.getFace(RubikCubeSide.DOWN, 1, 0) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.LEFT, 2, 1) == e.getLateralSide().getStandardColor();
            case DB:
                return m.getFace(RubikCubeSide.DOWN, 2, 1) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.BACK, 2, 1) == e.getLateralSide().getStandardColor();
            case FR:
                return m.getFace(RubikCubeSide.FRONT, 1, 2) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.RIGHT, 1, 0) == e.getLateralSide().getStandardColor();
            case FL:
                return m.getFace(RubikCubeSide.FRONT, 1, 0) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.RIGHT, 1, 2) == e.getLateralSide().getStandardColor();
            case BL:
                return m.getFace(RubikCubeSide.BACK, 1, 2) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.LEFT, 1, 0) == e.getLateralSide().getStandardColor();
            case BR:
                return m.getFace(RubikCubeSide.BACK, 1, 0) == e.getTopSide().getStandardColor()
                    && m.getFace(RubikCubeSide.RIGHT, 1, 2) == e.getLateralSide().getStandardColor();
            default:
                throw new IllegalArgumentException("Edge not in enum");
        }
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
    /**
     * Rotates a face of the Rubik cube. <strong><tt>src</tt> and <tt>dst</tt>
     * must not be the same array</strong> as the routine is not designed for
     * a copy in place!
     * @param src Source face.
     * @param dst Destination face.
     * @param anticlockwise Specifies if the rotation should be anticlockwise
     * (it will be clockwise otherwise).
     * @throws IllegalArgumentException Thrown if <tt>src</tt> and <tt>dst</tt>
     * are the same array.
     */
    private void rotateFace(final RubikCubeFaceColor[][] src,
                            final RubikCubeFaceColor[][] dst,
                            final boolean anticlockwise)
        throws IllegalArgumentException {
        // The following == is intended as we're comparing references.
        if (src == dst) {
            throw new IllegalArgumentException("src and dst must be different"
                                               + " references");
        }
        for (int i = 0; i < this.dimension; ++i) {
            for (int j = 0; j < this.dimension; ++j) {
                if (anticlockwise) {
                    dst[this.dimension - 1 - j][i] = src[i][j];
                } else {
                    dst[j][this.dimension - 1 - i] = src[i][j];
                }
            }
        }
    }
}
