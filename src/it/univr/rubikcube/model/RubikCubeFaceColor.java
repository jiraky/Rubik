package it.univr.rubikcube.model;

import java.awt.Color;

/**
 * Color of a face of a Rubik cube.
 * @author Alessandro Menti
 */
public enum RubikCubeFaceColor {
    /** White face. */
    WHITE,
    /** Red face. */
    RED,
    /** Blue face. */
    BLUE,
    /** Orange face. **/
    ORANGE,
    /** Green face. */
    GREEN,
    /** Yellow face. */
    YELLOW;
    
    public static Color getColor(RubikCubeFaceColor c) {
        switch(c) {
            case WHITE: return RubikCubeFaceColor.rgb_white;
            case YELLOW: return RubikCubeFaceColor.rgb_yellow;
            case RED: return RubikCubeFaceColor.rgb_red;
            case ORANGE: return RubikCubeFaceColor.rgb_orange;
            case BLUE: return RubikCubeFaceColor.rgb_blue;
            case GREEN: return RubikCubeFaceColor.rgb_green;
        }
        throw new UnsupportedOperationException("Cannot convert this face");
    }
    public static RubikCubeFaceColor getFace(Color c) {
        if(c.getRGB()==rgb_white.getRGB()) return WHITE;
        if(c.getRGB()==rgb_yellow.getRGB()) return YELLOW;
        if(c.getRGB()==rgb_red.getRGB()) return RED;
        if(c.getRGB()==rgb_orange.getRGB()) return ORANGE;
        if(c.getRGB()==rgb_blue.getRGB()) return BLUE;
        if(c.getRGB()==rgb_green.getRGB()) return GREEN;
        throw new UnsupportedOperationException("Cannot convert this face");
    }
    
    public static final Color rgb_white = new Color(255, 255, 255);
    public static final Color rgb_yellow = new Color(255, 255, 0);
    public static final Color rgb_red = new Color(255, 0, 0);
    public static final Color rgb_orange = new Color(255, 153, 0);
    public static final Color rgb_blue = new Color(0, 51, 255);
    public static final Color rgb_green = new Color(0, 153, 0);
}
