package it.univr.rubikcube.resolutionstrategies;

import it.univr.rubikcube.model.CubeRotation;
import it.univr.rubikcube.model.Move;
import it.univr.rubikcube.model.RubikCubeFaceColor;
import it.univr.rubikcube.model.RubikCubeModel;
import it.univr.rubikcube.model.RubikCubeSide;
import it.univr.rubikcube.moves.B;
import it.univr.rubikcube.moves.D;
import it.univr.rubikcube.moves.F;
import it.univr.rubikcube.moves.L;
import it.univr.rubikcube.moves.R;
import it.univr.rubikcube.moves.U;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javax.swing.JOptionPane;
import org.kociemba.twophase.Facelet;
import org.kociemba.twophase.Search;
import org.kociemba.twophase.Tools;

/**
 * Kociemba two-phase algorithm for 3x3 cubes.
 * @author Herbert Kociemba
 * @author Alessandro Menti
 */
public class KociembaLib extends ResolutionStrategy {

    /**
     * Creates a new instance of the Kociemba method.
     * @param m Rubik cube model.
     * @throws IllegalArgumentException Thrown if the dimension of the cube is
     * not equal to three, if the cube has not nine faces per color, if there
     * isn't a single facelet per color.
     */
    public KociembaLib(final RubikCubeModel m)
            throws IllegalArgumentException {
        super(m);
        if (m.getDimension() != 3) {
            throw new IllegalArgumentException("The dimension of the cube must"
                                               + " be equal to three");
        }
        if (!RubikCubeModel.isWithSaneColors(m)) {
            throw new IllegalArgumentException("The cube has not sane colors");
        }
    }

    /**
     * Gets the name of this method.
     * @return <tt>Kociemba</tt>
     */
    @Override
    public final String toString() {
        return "Kociemba";
    }
    /**
     * Gets a textual description of this method.
     * @return <tt>The Kociemba two-phase algorithm searches for solutions in a
     * game state tree using two groups of moves.</tt>
     */
    @Override
    public final String getDescription() {
        return "The Kociemba two-phase algorithm searches for solutions in a"
               + " game state tree using two groups of moves.";
    }

    @Override
    public List<Move> getNextMoves() throws NoSolutionException, TimeoutException {
        List<Move> result = new LinkedList<>();
        String solution = Search.solution(KociembaLib.mapOurCubeModelToKociemba(getModel()), 21, 6000, true);
        JOptionPane.showMessageDialog(null, solution);
        if(solution.contains("rror"))
            if (solution.contains("1")) throw new NoSolutionException("There is not exactly one facelet of each colour");
            else if(solution.contains("2")) throw new NoSolutionException("Not all 12 edges exist exactly once");
            else if(solution.contains("3")) throw new NoSolutionException("Flip error: One edge has to be flipped");
            else if(solution.contains("4")) throw new NoSolutionException("Not all corners exist exactly once");
            else if(solution.contains("5")) throw new NoSolutionException("Twist error: One corner has to be twisted");
            else if(solution.contains("6")) throw new NoSolutionException("Parity error: Two corners or two edges have to be exchanged");
            else if(solution.contains("7")) throw new NoSolutionException("No solution exists for the given maxDepth");
            else if(solution.contains("8")) throw new NoSolutionException("Timeout, no solution within given time");
            else throw new NoSolutionException("Generic Exception");
        
        String[] sols = solution.split(" ");
        
        for (String sol : sols) {
            JOptionPane.showMessageDialog(null, sol);
            if(sol.contains("2")) {
                sol = sol.replaceAll("2", "");
                result.add(Move.convertFromString(this.getModel(),sol));
            }
            if(sol.contains(".")) continue;
            result.add(Move.convertFromString(this.getModel(),sol));
        }
        
        return result;
    }
    
    public static void main(String args[]) {
        JOptionPane.showMessageDialog(null, Search.solution("UUUUUUUUURRRRRRRRRFFFFFFFFFDDDDDDDDDLLLLLLLLLBBBBBBBBB", 21, 5000, true));
    }
    
    public static String mapOurCubeModelToKociemba(RubikCubeModel cube) {   
        String result = "";
        for(RubikCubeSide side = RubikCubeSide.UP;;) { 
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    result += mapOurCubeColorToKociemba(cube.getFace(side, i, j));
            
            if(side==RubikCubeSide.UP) side = RubikCubeSide.RIGHT;
            else if(side==RubikCubeSide.RIGHT) side = RubikCubeSide.FRONT;
            else if(side==RubikCubeSide.FRONT) side = RubikCubeSide.DOWN;
            else if(side==RubikCubeSide.DOWN) side = RubikCubeSide.LEFT;
            else if(side==RubikCubeSide.LEFT) side = RubikCubeSide.BACK;
            else if(side==RubikCubeSide.BACK) break;
        }
        return result;
    }
    
    public static RubikCubeModel mapKociembaModelToOurCube(String kociemba_model) {
        RubikCubeModel cube = new RubikCubeModel(3);
        
        RubikCubeSide[] sides = {RubikCubeSide.UP, RubikCubeSide.RIGHT ,RubikCubeSide.FRONT ,RubikCubeSide.DOWN ,RubikCubeSide.LEFT ,RubikCubeSide.BACK};
        for(int f=0; f<6; f++)
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    cube.setFace(sides[f], i, j, mapKociembaColorToOurCube(kociemba_model.charAt(f*6 + i*3 + j)));
        
        return cube;
    }
    
    public static String mapOurCubeColorToKociemba(RubikCubeFaceColor color) {
        switch(color) {
            case BLUE: return "R";
            case WHITE: return "U";
            case RED: return "F";
            case ORANGE: return "B";
            case GREEN: return "L";
            case YELLOW: return "D";
            default: throw new UnsupportedOperationException("Cannot convert this color");
        }
    }
    public static RubikCubeFaceColor mapKociembaColorToOurCube(char color) {
        color = Character.toUpperCase(color);
        switch(color) {
            case 'L': return RubikCubeFaceColor.GREEN;
            case 'F': return RubikCubeFaceColor.RED;
            case 'U': return RubikCubeFaceColor.WHITE;
            case 'D': return RubikCubeFaceColor.YELLOW;
            case 'R': return RubikCubeFaceColor.BLUE;
            case 'B': return RubikCubeFaceColor.ORANGE;
            default: throw new UnsupportedOperationException("Cannot convert this color");
        }
    }
    
    
    public static Facelet mapFaceAndColorWithFacelet(RubikCubeSide side, int x, int y) {
        switch(side) {
            case BACK: {
                if(x==0 && y==0) return Facelet.B1;
                if(x==0 && y==1) return Facelet.B2;
                if(x==0 && y==2) return Facelet.B3;
                if(x==1 && y==0) return Facelet.B4;
                if(x==1 && y==1) return Facelet.B5;
                if(x==1 && y==2) return Facelet.B6;
                if(x==2 && y==0) return Facelet.B7;
                if(x==2 && y==1) return Facelet.B8;
                if(x==2 && y==2) return Facelet.B9;
            } 
            case DOWN:{
                if(x==0 && y==0) return Facelet.D1;
                if(x==0 && y==1) return Facelet.D2;
                if(x==0 && y==2) return Facelet.D3;
                if(x==1 && y==0) return Facelet.D4;
                if(x==1 && y==1) return Facelet.D5;
                if(x==1 && y==2) return Facelet.D6;
                if(x==2 && y==0) return Facelet.D7;
                if(x==2 && y==1) return Facelet.D8;
                if(x==2 && y==2) return Facelet.D9;
            } 
            case FRONT:{
                if(x==0 && y==0) return Facelet.F1;
                if(x==0 && y==1) return Facelet.F2;
                if(x==0 && y==2) return Facelet.F3;
                if(x==1 && y==0) return Facelet.F4;
                if(x==1 && y==1) return Facelet.F5;
                if(x==1 && y==2) return Facelet.F6;
                if(x==2 && y==0) return Facelet.F7;
                if(x==2 && y==1) return Facelet.F8;
                if(x==2 && y==2) return Facelet.F9;
            } 
            case LEFT: {
                if(x==0 && y==0) return Facelet.L1;
                if(x==0 && y==1) return Facelet.L2;
                if(x==0 && y==2) return Facelet.L3;
                if(x==1 && y==0) return Facelet.L4;
                if(x==1 && y==1) return Facelet.L5;
                if(x==1 && y==2) return Facelet.L6;
                if(x==2 && y==0) return Facelet.L7;
                if(x==2 && y==1) return Facelet.L8;
                if(x==2 && y==2) return Facelet.L9;
            } 
            case RIGHT:{
                if(x==0 && y==0) return Facelet.L1;
                if(x==0 && y==1) return Facelet.L2;
                if(x==0 && y==2) return Facelet.L3;
                if(x==1 && y==0) return Facelet.L4;
                if(x==1 && y==1) return Facelet.L5;
                if(x==1 && y==2) return Facelet.L6;
                if(x==2 && y==0) return Facelet.L7;
                if(x==2 && y==1) return Facelet.L8;
                if(x==2 && y==2) return Facelet.L9;
            } 
            case UP:{
                if(x==0 && y==0) return Facelet.B1;
                if(x==0 && y==1) return Facelet.B2;
                if(x==0 && y==2) return Facelet.B3;
                if(x==1 && y==0) return Facelet.B4;
                if(x==1 && y==1) return Facelet.B5;
                if(x==1 && y==2) return Facelet.B6;
                if(x==2 && y==0) return Facelet.B7;
                if(x==2 && y==1) return Facelet.B8;
                if(x==2 && y==2) return Facelet.B9;
            } 
            default: throw new UnsupportedOperationException("Cannot convert this face: "+side.toString());
        }
    }
}
