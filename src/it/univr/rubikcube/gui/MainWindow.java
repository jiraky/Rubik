package it.univr.rubikcube.gui;

import it.univr.rubikcube.model.*;
import it.univr.rubikcube.moves.*;
import it.univr.rubikcube.resolutionstrategies.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Main Rubik Cube Solver window.
 * @author Mattia Zago
 */
public class MainWindow extends javax.swing.JFrame {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of the main window.
     */
    public MainWindow() {
        initComponents();
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2,
                         dim.height / 2 - this.getSize().height / 2);
        initCube();
    }
    /**
     * Updates the interface.
     */
    public final void updateInterface() {
        this.rp_cubedimension_value.setText("" + this.RubikCubeDimension);
        this.rp_algorithm_value.setText("" + this.actualStrategy.toString());
        this.rp_nummoves_value.setText("" + this.MovesCounter);
 
        
        this.F11.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 0, 0).getColor());
        this.F12.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 0, 1).getColor());
        this.F13.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 0, 2).getColor());
        this.F21.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 1, 0).getColor());
        this.F22.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 1, 1).getColor());
        this.F23.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 1, 2).getColor());
        this.F31.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 2, 0).getColor());
        this.F32.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 2, 1).getColor());
        this.F33.setBackground(this.cube.getFace(RubikCubeSide.FRONT, 2, 2).getColor());
        
        this.B11.setBackground(this.cube.getFace(RubikCubeSide.BACK, 0, 2).getColor());
        this.B12.setBackground(this.cube.getFace(RubikCubeSide.BACK, 0, 1).getColor());
        this.B13.setBackground(this.cube.getFace(RubikCubeSide.BACK, 0, 0).getColor());
        this.B21.setBackground(this.cube.getFace(RubikCubeSide.BACK, 1, 2).getColor());
        this.B22.setBackground(this.cube.getFace(RubikCubeSide.BACK, 1, 1).getColor());
        this.B23.setBackground(this.cube.getFace(RubikCubeSide.BACK, 1, 0).getColor());
        this.B31.setBackground(this.cube.getFace(RubikCubeSide.BACK, 2, 2).getColor());
        this.B32.setBackground(this.cube.getFace(RubikCubeSide.BACK, 2, 1).getColor());
        this.B33.setBackground(this.cube.getFace(RubikCubeSide.BACK, 2, 0).getColor());
        
        this.R11.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 0, 0).getColor());
        this.R12.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 0, 1).getColor());
        this.R13.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 0, 2).getColor());
        this.R21.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 1, 0).getColor());
        this.R22.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 1, 1).getColor());
        this.R23.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 1, 2).getColor());
        this.R31.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 2, 0).getColor());
        this.R32.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 2, 1).getColor());
        this.R33.setBackground(this.cube.getFace(RubikCubeSide.RIGHT, 2, 2).getColor());
        
        this.L11.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 0, 0).getColor());
        this.L12.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 0, 1).getColor());
        this.L13.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 0, 2).getColor());
        this.L21.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 1, 0).getColor());
        this.L22.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 1, 1).getColor());
        this.L23.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 1, 2).getColor());
        this.L31.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 2, 0).getColor());
        this.L32.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 2, 1).getColor());
        this.L33.setBackground(this.cube.getFace(RubikCubeSide.LEFT, 2, 2).getColor());
        
        this.U11.setBackground(this.cube.getFace(RubikCubeSide.UP, 0, 0).getColor());
        this.U12.setBackground(this.cube.getFace(RubikCubeSide.UP, 0, 1).getColor());
        this.U13.setBackground(this.cube.getFace(RubikCubeSide.UP, 0, 2).getColor());
        this.U21.setBackground(this.cube.getFace(RubikCubeSide.UP, 1, 0).getColor());
        this.U22.setBackground(this.cube.getFace(RubikCubeSide.UP, 1, 1).getColor());
        this.U23.setBackground(this.cube.getFace(RubikCubeSide.UP, 1, 2).getColor());
        this.U31.setBackground(this.cube.getFace(RubikCubeSide.UP, 2, 0).getColor());
        this.U32.setBackground(this.cube.getFace(RubikCubeSide.UP, 2, 1).getColor());
        this.U33.setBackground(this.cube.getFace(RubikCubeSide.UP, 2, 2).getColor());
        
        this.D11.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 0, 0).getColor());
        this.D12.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 0, 1).getColor());
        this.D13.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 0, 2).getColor());
        this.D21.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 1, 0).getColor());
        this.D22.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 1, 1).getColor());
        this.D23.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 1, 2).getColor());
        this.D31.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 2, 0).getColor());
        this.D32.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 2, 1).getColor());
        this.D33.setBackground(this.cube.getFace(RubikCubeSide.DOWN, 2, 2).getColor());
    }
    /* CHECKSTYLE:OFF Rationale: autogenerated code */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lp_move_useinverse_btngroup = new javax.swing.ButtonGroup();
        MainLeftPanel = new javax.swing.JPanel();
        lp_cube = new javax.swing.JPanel();
        rotate_X = new javax.swing.JPanel();
        rotate_X_title = new javax.swing.JLabel();
        rotate_X_CW = new javax.swing.JButton();
        rotate_X_CCW = new javax.swing.JButton();
        U = new javax.swing.JPanel();
        U11 = new javax.swing.JLabel();
        U12 = new javax.swing.JLabel();
        U13 = new javax.swing.JLabel();
        U21 = new javax.swing.JLabel();
        U22 = new javax.swing.JLabel();
        U23 = new javax.swing.JLabel();
        U31 = new javax.swing.JLabel();
        U32 = new javax.swing.JLabel();
        U33 = new javax.swing.JLabel();
        B = new javax.swing.JPanel();
        B11 = new javax.swing.JLabel();
        B12 = new javax.swing.JLabel();
        B13 = new javax.swing.JLabel();
        B21 = new javax.swing.JLabel();
        B22 = new javax.swing.JLabel();
        B23 = new javax.swing.JLabel();
        B31 = new javax.swing.JLabel();
        B32 = new javax.swing.JLabel();
        B33 = new javax.swing.JLabel();
        L = new javax.swing.JPanel();
        L11 = new javax.swing.JLabel();
        L12 = new javax.swing.JLabel();
        L13 = new javax.swing.JLabel();
        L21 = new javax.swing.JLabel();
        L22 = new javax.swing.JLabel();
        L23 = new javax.swing.JLabel();
        L31 = new javax.swing.JLabel();
        L32 = new javax.swing.JLabel();
        L33 = new javax.swing.JLabel();
        F = new javax.swing.JPanel();
        F11 = new javax.swing.JLabel();
        F12 = new javax.swing.JLabel();
        F13 = new javax.swing.JLabel();
        F21 = new javax.swing.JLabel();
        F22 = new javax.swing.JLabel();
        F23 = new javax.swing.JLabel();
        F31 = new javax.swing.JLabel();
        F32 = new javax.swing.JLabel();
        F33 = new javax.swing.JLabel();
        R = new javax.swing.JPanel();
        R11 = new javax.swing.JLabel();
        R12 = new javax.swing.JLabel();
        R13 = new javax.swing.JLabel();
        R21 = new javax.swing.JLabel();
        R22 = new javax.swing.JLabel();
        R23 = new javax.swing.JLabel();
        R31 = new javax.swing.JLabel();
        R32 = new javax.swing.JLabel();
        R33 = new javax.swing.JLabel();
        rotate_Y = new javax.swing.JPanel();
        rotate_Y_title = new javax.swing.JLabel();
        rotate_Y_CW = new javax.swing.JButton();
        rotate_Y_CCW = new javax.swing.JButton();
        D = new javax.swing.JPanel();
        D11 = new javax.swing.JLabel();
        D12 = new javax.swing.JLabel();
        D13 = new javax.swing.JLabel();
        D21 = new javax.swing.JLabel();
        D22 = new javax.swing.JLabel();
        D23 = new javax.swing.JLabel();
        D31 = new javax.swing.JLabel();
        D32 = new javax.swing.JLabel();
        D33 = new javax.swing.JLabel();
        rotate_Z = new javax.swing.JPanel();
        rotate_Z_title = new javax.swing.JLabel();
        rotate_Z_CW = new javax.swing.JButton();
        rotate_Z_CCW = new javax.swing.JButton();
        lp_move = new javax.swing.JPanel();
        lp_move_basic = new javax.swing.JPanel();
        lp_move_L = new javax.swing.JButton();
        lp_move_U = new javax.swing.JButton();
        lp_move_R = new javax.swing.JButton();
        lp_move_D = new javax.swing.JButton();
        lp_move_F = new javax.swing.JButton();
        lp_move_B = new javax.swing.JButton();
        lp_move_advanced = new javax.swing.JPanel();
        lp_move_M = new javax.swing.JButton();
        lp_move_E = new javax.swing.JButton();
        lp_move_S = new javax.swing.JButton();
        lp_move_X = new javax.swing.JButton();
        lp_move_Y = new javax.swing.JButton();
        lp_move_Z = new javax.swing.JButton();
        lp_move_details = new javax.swing.JPanel();
        lp_move_inverse_title = new javax.swing.JLabel();
        lp_move_inverse_yes = new javax.swing.JRadioButton();
        lp_move_inverse_no = new javax.swing.JRadioButton();
        lp_move_preview = new javax.swing.JPanel();
        lp_move_preview_image = new javax.swing.JLabel();
        MainRightPanel = new javax.swing.JPanel();
        rp_cubedimension_title = new javax.swing.JLabel();
        rp_algorithm_title = new javax.swing.JLabel();
        rp_nummoves_title = new javax.swing.JLabel();
        rp_cubedimension_value = new javax.swing.JLabel();
        rp_algorithm_value = new javax.swing.JLabel();
        rp_nummoves_value = new javax.swing.JLabel();
        rp_sep1 = new javax.swing.JSeparator();
        rp_previousmoves_title = new javax.swing.JLabel();
        rp_previousmoves_container = new javax.swing.JScrollPane();
        rp_previousmoves_value = new javax.swing.JTextArea();
        rp_nextmoves_title = new javax.swing.JLabel();
        rp_nextmoves_container = new javax.swing.JScrollPane();
        rp_nextmoves_value = new javax.swing.JTextArea();
        rp_control = new javax.swing.JPanel();
        rp_control_reset = new javax.swing.JButton();
        rp_control_shuffle = new javax.swing.JButton();
        rp_control_autosolve = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        menu_file_exit = new javax.swing.JMenuItem();
        menu_edit = new javax.swing.JMenu();
        menu_edit_numfaces = new javax.swing.JMenuItem();
        menu_edit_algorithm = new javax.swing.JMenuItem();
        menu_help = new javax.swing.JMenu();
        menu_help_credits = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rubik Cube Solver");
        setName("MainFrame"); // NOI18N
        setResizable(false);

        lp_cube.setBorder(javax.swing.BorderFactory.createTitledBorder("Cube"));
        lp_cube.setLayout(new java.awt.GridLayout(3, 3, 1, 1));

        rotate_X.setBackground(new java.awt.Color(255, 255, 255));
        rotate_X.setMaximumSize(new java.awt.Dimension(100, 100));
        rotate_X.setMinimumSize(new java.awt.Dimension(100, 100));
        rotate_X.setName(""); // NOI18N
        rotate_X.setPreferredSize(new java.awt.Dimension(100, 100));
        rotate_X.setLayout(new java.awt.GridLayout(3, 1));

        rotate_X_title.setBackground(new java.awt.Color(255, 255, 255));
        rotate_X_title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rotate_X_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rotate_X_title.setText("Rotate on X");
        rotate_X_title.setOpaque(true);
        rotate_X.add(rotate_X_title);

        rotate_X_CW.setBackground(new java.awt.Color(255, 255, 255));
        rotate_X_CW.setText("CW");
        rotate_X_CW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotate_X_CWActionPerformed(evt);
            }
        });
        rotate_X.add(rotate_X_CW);

        rotate_X_CCW.setBackground(new java.awt.Color(255, 255, 255));
        rotate_X_CCW.setText("CCW");
        rotate_X_CCW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotate_X_CCWActionPerformed(evt);
            }
        });
        rotate_X.add(rotate_X_CCW);

        lp_cube.add(rotate_X);

        U.setMaximumSize(new java.awt.Dimension(100, 100));
        U.setMinimumSize(new java.awt.Dimension(100, 100));
        U.setName(""); // NOI18N
        U.setPreferredSize(new java.awt.Dimension(100, 100));
        U.setLayout(new java.awt.GridLayout(3, 3));

        U11.setBackground(new java.awt.Color(255, 0, 0));
        U11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U11.setText(" ");
        U11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U11.setMaximumSize(new java.awt.Dimension(20, 20));
        U11.setMinimumSize(new java.awt.Dimension(20, 20));
        U11.setOpaque(true);
        U11.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U11);

        U12.setBackground(new java.awt.Color(255, 0, 0));
        U12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U12.setText(" ");
        U12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U12.setMaximumSize(new java.awt.Dimension(20, 20));
        U12.setMinimumSize(new java.awt.Dimension(20, 20));
        U12.setOpaque(true);
        U12.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U12);

        U13.setBackground(new java.awt.Color(255, 0, 0));
        U13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U13.setText(" ");
        U13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U13.setMaximumSize(new java.awt.Dimension(20, 20));
        U13.setMinimumSize(new java.awt.Dimension(20, 20));
        U13.setOpaque(true);
        U13.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U13);

        U21.setBackground(new java.awt.Color(255, 0, 0));
        U21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U21.setText(" ");
        U21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U21.setMaximumSize(new java.awt.Dimension(20, 20));
        U21.setMinimumSize(new java.awt.Dimension(20, 20));
        U21.setOpaque(true);
        U21.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U21);

        U22.setBackground(new java.awt.Color(255, 0, 0));
        U22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        U22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U22.setText("U");
        U22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U22.setMaximumSize(new java.awt.Dimension(20, 20));
        U22.setMinimumSize(new java.awt.Dimension(20, 20));
        U22.setOpaque(true);
        U22.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U22);

        U23.setBackground(new java.awt.Color(255, 0, 0));
        U23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U23.setText(" ");
        U23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U23.setMaximumSize(new java.awt.Dimension(20, 20));
        U23.setMinimumSize(new java.awt.Dimension(20, 20));
        U23.setOpaque(true);
        U23.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U23);

        U31.setBackground(new java.awt.Color(255, 0, 0));
        U31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U31.setText(" ");
        U31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U31.setMaximumSize(new java.awt.Dimension(20, 20));
        U31.setMinimumSize(new java.awt.Dimension(20, 20));
        U31.setOpaque(true);
        U31.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U31);

        U32.setBackground(new java.awt.Color(255, 0, 0));
        U32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U32.setText(" ");
        U32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U32.setMaximumSize(new java.awt.Dimension(20, 20));
        U32.setMinimumSize(new java.awt.Dimension(20, 20));
        U32.setOpaque(true);
        U32.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U32);

        U33.setBackground(new java.awt.Color(255, 0, 0));
        U33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        U33.setText(" ");
        U33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        U33.setMaximumSize(new java.awt.Dimension(20, 20));
        U33.setMinimumSize(new java.awt.Dimension(20, 20));
        U33.setOpaque(true);
        U33.setPreferredSize(new java.awt.Dimension(20, 20));
        U.add(U33);

        lp_cube.add(U);

        B.setMaximumSize(new java.awt.Dimension(100, 100));
        B.setMinimumSize(new java.awt.Dimension(100, 100));
        B.setName(""); // NOI18N
        B.setPreferredSize(new java.awt.Dimension(100, 100));
        B.setLayout(new java.awt.GridLayout(3, 3));

        B11.setBackground(new java.awt.Color(255, 255, 0));
        B11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B11.setText(" ");
        B11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B11.setMaximumSize(new java.awt.Dimension(20, 20));
        B11.setMinimumSize(new java.awt.Dimension(20, 20));
        B11.setOpaque(true);
        B11.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B11);

        B12.setBackground(new java.awt.Color(255, 255, 0));
        B12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B12.setText(" ");
        B12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B12.setMaximumSize(new java.awt.Dimension(20, 20));
        B12.setMinimumSize(new java.awt.Dimension(20, 20));
        B12.setOpaque(true);
        B12.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B12);

        B13.setBackground(new java.awt.Color(255, 255, 0));
        B13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B13.setText(" ");
        B13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B13.setMaximumSize(new java.awt.Dimension(20, 20));
        B13.setMinimumSize(new java.awt.Dimension(20, 20));
        B13.setOpaque(true);
        B13.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B13);

        B21.setBackground(new java.awt.Color(255, 255, 0));
        B21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B21.setText(" ");
        B21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B21.setMaximumSize(new java.awt.Dimension(20, 20));
        B21.setMinimumSize(new java.awt.Dimension(20, 20));
        B21.setOpaque(true);
        B21.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B21);

        B22.setBackground(new java.awt.Color(255, 255, 0));
        B22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        B22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B22.setText("B");
        B22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B22.setMaximumSize(new java.awt.Dimension(20, 20));
        B22.setMinimumSize(new java.awt.Dimension(20, 20));
        B22.setOpaque(true);
        B22.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B22);

        B23.setBackground(new java.awt.Color(255, 255, 0));
        B23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B23.setText(" ");
        B23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B23.setMaximumSize(new java.awt.Dimension(20, 20));
        B23.setMinimumSize(new java.awt.Dimension(20, 20));
        B23.setOpaque(true);
        B23.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B23);

        B31.setBackground(new java.awt.Color(255, 255, 0));
        B31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B31.setText(" ");
        B31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B31.setMaximumSize(new java.awt.Dimension(20, 20));
        B31.setMinimumSize(new java.awt.Dimension(20, 20));
        B31.setOpaque(true);
        B31.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B31);

        B32.setBackground(new java.awt.Color(255, 255, 0));
        B32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B32.setText(" ");
        B32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B32.setMaximumSize(new java.awt.Dimension(20, 20));
        B32.setMinimumSize(new java.awt.Dimension(20, 20));
        B32.setOpaque(true);
        B32.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B32);

        B33.setBackground(new java.awt.Color(255, 255, 0));
        B33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        B33.setText(" ");
        B33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        B33.setMaximumSize(new java.awt.Dimension(20, 20));
        B33.setMinimumSize(new java.awt.Dimension(20, 20));
        B33.setOpaque(true);
        B33.setPreferredSize(new java.awt.Dimension(20, 20));
        B.add(B33);

        lp_cube.add(B);

        L.setMaximumSize(new java.awt.Dimension(100, 100));
        L.setMinimumSize(new java.awt.Dimension(100, 100));
        L.setName(""); // NOI18N
        L.setPreferredSize(new java.awt.Dimension(100, 100));
        L.setLayout(new java.awt.GridLayout(3, 3));

        L11.setBackground(new java.awt.Color(0, 51, 255));
        L11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L11.setText(" ");
        L11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L11.setMaximumSize(new java.awt.Dimension(20, 20));
        L11.setMinimumSize(new java.awt.Dimension(20, 20));
        L11.setOpaque(true);
        L11.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L11);

        L12.setBackground(new java.awt.Color(0, 51, 255));
        L12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L12.setText(" ");
        L12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L12.setMaximumSize(new java.awt.Dimension(20, 20));
        L12.setMinimumSize(new java.awt.Dimension(20, 20));
        L12.setOpaque(true);
        L12.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L12);

        L13.setBackground(new java.awt.Color(0, 51, 255));
        L13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L13.setText(" ");
        L13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L13.setMaximumSize(new java.awt.Dimension(20, 20));
        L13.setMinimumSize(new java.awt.Dimension(20, 20));
        L13.setOpaque(true);
        L13.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L13);

        L21.setBackground(new java.awt.Color(0, 51, 255));
        L21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L21.setText(" ");
        L21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L21.setMaximumSize(new java.awt.Dimension(20, 20));
        L21.setMinimumSize(new java.awt.Dimension(20, 20));
        L21.setOpaque(true);
        L21.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L21);

        L22.setBackground(new java.awt.Color(0, 51, 255));
        L22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        L22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L22.setText("L");
        L22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L22.setMaximumSize(new java.awt.Dimension(20, 20));
        L22.setMinimumSize(new java.awt.Dimension(20, 20));
        L22.setOpaque(true);
        L22.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L22);

        L23.setBackground(new java.awt.Color(0, 51, 255));
        L23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L23.setText(" ");
        L23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L23.setMaximumSize(new java.awt.Dimension(20, 20));
        L23.setMinimumSize(new java.awt.Dimension(20, 20));
        L23.setOpaque(true);
        L23.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L23);

        L31.setBackground(new java.awt.Color(0, 51, 255));
        L31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L31.setText(" ");
        L31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L31.setMaximumSize(new java.awt.Dimension(20, 20));
        L31.setMinimumSize(new java.awt.Dimension(20, 20));
        L31.setOpaque(true);
        L31.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L31);

        L32.setBackground(new java.awt.Color(0, 51, 255));
        L32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L32.setText(" ");
        L32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L32.setMaximumSize(new java.awt.Dimension(20, 20));
        L32.setMinimumSize(new java.awt.Dimension(20, 20));
        L32.setOpaque(true);
        L32.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L32);

        L33.setBackground(new java.awt.Color(0, 51, 255));
        L33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L33.setText(" ");
        L33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        L33.setMaximumSize(new java.awt.Dimension(20, 20));
        L33.setMinimumSize(new java.awt.Dimension(20, 20));
        L33.setOpaque(true);
        L33.setPreferredSize(new java.awt.Dimension(20, 20));
        L.add(L33);

        lp_cube.add(L);

        F.setMaximumSize(new java.awt.Dimension(100, 100));
        F.setMinimumSize(new java.awt.Dimension(100, 100));
        F.setName(""); // NOI18N
        F.setPreferredSize(new java.awt.Dimension(100, 100));
        F.setLayout(new java.awt.GridLayout(3, 3));

        F11.setBackground(new java.awt.Color(255, 255, 255));
        F11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F11.setText(" ");
        F11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F11.setMaximumSize(new java.awt.Dimension(20, 20));
        F11.setMinimumSize(new java.awt.Dimension(20, 20));
        F11.setOpaque(true);
        F11.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F11);

        F12.setBackground(new java.awt.Color(255, 255, 255));
        F12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F12.setText(" ");
        F12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F12.setMaximumSize(new java.awt.Dimension(20, 20));
        F12.setMinimumSize(new java.awt.Dimension(20, 20));
        F12.setOpaque(true);
        F12.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F12);

        F13.setBackground(new java.awt.Color(255, 255, 255));
        F13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F13.setText(" ");
        F13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F13.setMaximumSize(new java.awt.Dimension(20, 20));
        F13.setMinimumSize(new java.awt.Dimension(20, 20));
        F13.setOpaque(true);
        F13.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F13);

        F21.setBackground(new java.awt.Color(255, 255, 255));
        F21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F21.setText(" ");
        F21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F21.setMaximumSize(new java.awt.Dimension(20, 20));
        F21.setMinimumSize(new java.awt.Dimension(20, 20));
        F21.setOpaque(true);
        F21.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F21);

        F22.setBackground(new java.awt.Color(255, 255, 255));
        F22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        F22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F22.setText("F");
        F22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F22.setMaximumSize(new java.awt.Dimension(20, 20));
        F22.setMinimumSize(new java.awt.Dimension(20, 20));
        F22.setOpaque(true);
        F22.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F22);

        F23.setBackground(new java.awt.Color(255, 255, 255));
        F23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F23.setText(" ");
        F23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F23.setMaximumSize(new java.awt.Dimension(20, 20));
        F23.setMinimumSize(new java.awt.Dimension(20, 20));
        F23.setOpaque(true);
        F23.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F23);

        F31.setBackground(new java.awt.Color(255, 255, 255));
        F31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F31.setText(" ");
        F31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F31.setMaximumSize(new java.awt.Dimension(20, 20));
        F31.setMinimumSize(new java.awt.Dimension(20, 20));
        F31.setOpaque(true);
        F31.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F31);

        F32.setBackground(new java.awt.Color(255, 255, 255));
        F32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F32.setText(" ");
        F32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F32.setMaximumSize(new java.awt.Dimension(20, 20));
        F32.setMinimumSize(new java.awt.Dimension(20, 20));
        F32.setOpaque(true);
        F32.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F32);

        F33.setBackground(new java.awt.Color(255, 255, 255));
        F33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        F33.setText(" ");
        F33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        F33.setMaximumSize(new java.awt.Dimension(20, 20));
        F33.setMinimumSize(new java.awt.Dimension(20, 20));
        F33.setOpaque(true);
        F33.setPreferredSize(new java.awt.Dimension(20, 20));
        F.add(F33);

        lp_cube.add(F);

        R.setMaximumSize(new java.awt.Dimension(100, 100));
        R.setMinimumSize(new java.awt.Dimension(100, 100));
        R.setName(""); // NOI18N
        R.setPreferredSize(new java.awt.Dimension(100, 100));
        R.setLayout(new java.awt.GridLayout(3, 3));

        R11.setBackground(new java.awt.Color(0, 153, 0));
        R11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R11.setText(" ");
        R11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R11.setMaximumSize(new java.awt.Dimension(20, 20));
        R11.setMinimumSize(new java.awt.Dimension(20, 20));
        R11.setOpaque(true);
        R11.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R11);

        R12.setBackground(new java.awt.Color(0, 153, 0));
        R12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R12.setText(" ");
        R12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R12.setMaximumSize(new java.awt.Dimension(20, 20));
        R12.setMinimumSize(new java.awt.Dimension(20, 20));
        R12.setOpaque(true);
        R12.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R12);

        R13.setBackground(new java.awt.Color(0, 153, 0));
        R13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R13.setText(" ");
        R13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R13.setMaximumSize(new java.awt.Dimension(20, 20));
        R13.setMinimumSize(new java.awt.Dimension(20, 20));
        R13.setOpaque(true);
        R13.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R13);

        R21.setBackground(new java.awt.Color(0, 153, 0));
        R21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R21.setText(" ");
        R21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R21.setMaximumSize(new java.awt.Dimension(20, 20));
        R21.setMinimumSize(new java.awt.Dimension(20, 20));
        R21.setOpaque(true);
        R21.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R21);

        R22.setBackground(new java.awt.Color(0, 153, 0));
        R22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        R22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R22.setText("R");
        R22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R22.setMaximumSize(new java.awt.Dimension(20, 20));
        R22.setMinimumSize(new java.awt.Dimension(20, 20));
        R22.setOpaque(true);
        R22.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R22);

        R23.setBackground(new java.awt.Color(0, 153, 0));
        R23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R23.setText(" ");
        R23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R23.setMaximumSize(new java.awt.Dimension(20, 20));
        R23.setMinimumSize(new java.awt.Dimension(20, 20));
        R23.setOpaque(true);
        R23.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R23);

        R31.setBackground(new java.awt.Color(0, 153, 0));
        R31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R31.setText(" ");
        R31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R31.setMaximumSize(new java.awt.Dimension(20, 20));
        R31.setMinimumSize(new java.awt.Dimension(20, 20));
        R31.setOpaque(true);
        R31.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R31);

        R32.setBackground(new java.awt.Color(0, 153, 0));
        R32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R32.setText(" ");
        R32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R32.setMaximumSize(new java.awt.Dimension(20, 20));
        R32.setMinimumSize(new java.awt.Dimension(20, 20));
        R32.setOpaque(true);
        R32.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R32);

        R33.setBackground(new java.awt.Color(0, 153, 0));
        R33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        R33.setText(" ");
        R33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        R33.setMaximumSize(new java.awt.Dimension(20, 20));
        R33.setMinimumSize(new java.awt.Dimension(20, 20));
        R33.setOpaque(true);
        R33.setPreferredSize(new java.awt.Dimension(20, 20));
        R.add(R33);

        lp_cube.add(R);

        rotate_Y.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Y.setMaximumSize(new java.awt.Dimension(100, 100));
        rotate_Y.setMinimumSize(new java.awt.Dimension(100, 100));
        rotate_Y.setName(""); // NOI18N
        rotate_Y.setPreferredSize(new java.awt.Dimension(100, 100));
        rotate_Y.setLayout(new java.awt.GridLayout(3, 1));

        rotate_Y_title.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Y_title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rotate_Y_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rotate_Y_title.setText("Rotate on Y");
        rotate_Y_title.setOpaque(true);
        rotate_Y.add(rotate_Y_title);

        rotate_Y_CW.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Y_CW.setText("CW");
        rotate_Y_CW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotate_Y_CWActionPerformed(evt);
            }
        });
        rotate_Y.add(rotate_Y_CW);

        rotate_Y_CCW.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Y_CCW.setText("CCW");
        rotate_Y_CCW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotate_Y_CCWActionPerformed(evt);
            }
        });
        rotate_Y.add(rotate_Y_CCW);

        lp_cube.add(rotate_Y);

        D.setMaximumSize(new java.awt.Dimension(100, 100));
        D.setMinimumSize(new java.awt.Dimension(100, 100));
        D.setName(""); // NOI18N
        D.setPreferredSize(new java.awt.Dimension(100, 100));
        D.setLayout(new java.awt.GridLayout(3, 3));

        D11.setBackground(new java.awt.Color(255, 153, 0));
        D11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D11.setText(" ");
        D11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D11.setMaximumSize(new java.awt.Dimension(20, 20));
        D11.setMinimumSize(new java.awt.Dimension(20, 20));
        D11.setOpaque(true);
        D11.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D11);

        D12.setBackground(new java.awt.Color(255, 153, 0));
        D12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D12.setText(" ");
        D12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D12.setMaximumSize(new java.awt.Dimension(20, 20));
        D12.setMinimumSize(new java.awt.Dimension(20, 20));
        D12.setOpaque(true);
        D12.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D12);

        D13.setBackground(new java.awt.Color(255, 153, 0));
        D13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D13.setText(" ");
        D13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D13.setMaximumSize(new java.awt.Dimension(20, 20));
        D13.setMinimumSize(new java.awt.Dimension(20, 20));
        D13.setOpaque(true);
        D13.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D13);

        D21.setBackground(new java.awt.Color(255, 153, 0));
        D21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D21.setText(" ");
        D21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D21.setMaximumSize(new java.awt.Dimension(20, 20));
        D21.setMinimumSize(new java.awt.Dimension(20, 20));
        D21.setOpaque(true);
        D21.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D21);

        D22.setBackground(new java.awt.Color(255, 153, 0));
        D22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        D22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D22.setText("D");
        D22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D22.setMaximumSize(new java.awt.Dimension(20, 20));
        D22.setMinimumSize(new java.awt.Dimension(20, 20));
        D22.setOpaque(true);
        D22.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D22);

        D23.setBackground(new java.awt.Color(255, 153, 0));
        D23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D23.setText(" ");
        D23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D23.setMaximumSize(new java.awt.Dimension(20, 20));
        D23.setMinimumSize(new java.awt.Dimension(20, 20));
        D23.setOpaque(true);
        D23.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D23);

        D31.setBackground(new java.awt.Color(255, 153, 0));
        D31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D31.setText(" ");
        D31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D31.setMaximumSize(new java.awt.Dimension(20, 20));
        D31.setMinimumSize(new java.awt.Dimension(20, 20));
        D31.setOpaque(true);
        D31.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D31);

        D32.setBackground(new java.awt.Color(255, 153, 0));
        D32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D32.setText(" ");
        D32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D32.setMaximumSize(new java.awt.Dimension(20, 20));
        D32.setMinimumSize(new java.awt.Dimension(20, 20));
        D32.setOpaque(true);
        D32.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D32);

        D33.setBackground(new java.awt.Color(255, 153, 0));
        D33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        D33.setText(" ");
        D33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        D33.setMaximumSize(new java.awt.Dimension(20, 20));
        D33.setMinimumSize(new java.awt.Dimension(20, 20));
        D33.setOpaque(true);
        D33.setPreferredSize(new java.awt.Dimension(20, 20));
        D.add(D33);

        lp_cube.add(D);

        rotate_Z.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Z.setMaximumSize(new java.awt.Dimension(100, 100));
        rotate_Z.setMinimumSize(new java.awt.Dimension(100, 100));
        rotate_Z.setName(""); // NOI18N
        rotate_Z.setPreferredSize(new java.awt.Dimension(100, 100));
        rotate_Z.setLayout(new java.awt.GridLayout(3, 3));

        rotate_Z_title.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Z_title.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rotate_Z_title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rotate_Z_title.setText("Rotate on Z");
        rotate_Z_title.setOpaque(true);
        rotate_Z.add(rotate_Z_title);

        rotate_Z_CW.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Z_CW.setText("CW");
        rotate_Z_CW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotate_Z_CWActionPerformed(evt);
            }
        });
        rotate_Z.add(rotate_Z_CW);

        rotate_Z_CCW.setBackground(new java.awt.Color(255, 255, 255));
        rotate_Z_CCW.setText("CCW");
        rotate_Z_CCW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rotate_Z_CCWActionPerformed(evt);
            }
        });
        rotate_Z.add(rotate_Z_CCW);

        lp_cube.add(rotate_Z);

        lp_move.setBorder(javax.swing.BorderFactory.createTitledBorder("Perform move"));

        lp_move_basic.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Basic", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        lp_move_basic.setToolTipText("");

        lp_move_L.setText("L");
        lp_move_L.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_LMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_LMouseExited(evt);
            }
        });
        lp_move_L.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_LActionPerformed(evt);
            }
        });

        lp_move_U.setText("U");
        lp_move_U.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_UMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_UMouseExited(evt);
            }
        });
        lp_move_U.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_UActionPerformed(evt);
            }
        });

        lp_move_R.setText("R");
        lp_move_R.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_RMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_RMouseExited(evt);
            }
        });
        lp_move_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_RActionPerformed(evt);
            }
        });

        lp_move_D.setText("D");
        lp_move_D.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_DMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_DMouseExited(evt);
            }
        });
        lp_move_D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_DActionPerformed(evt);
            }
        });

        lp_move_F.setText("F");
        lp_move_F.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_FMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_FMouseExited(evt);
            }
        });
        lp_move_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_FActionPerformed(evt);
            }
        });

        lp_move_B.setText("B");
        lp_move_B.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_BMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_BMouseExited(evt);
            }
        });
        lp_move_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_BActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lp_move_basicLayout = new javax.swing.GroupLayout(lp_move_basic);
        lp_move_basic.setLayout(lp_move_basicLayout);
        lp_move_basicLayout.setHorizontalGroup(
            lp_move_basicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_basicLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lp_move_basicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp_move_L, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lp_move_U)
                    .addComponent(lp_move_R)
                    .addComponent(lp_move_D)
                    .addComponent(lp_move_F)
                    .addComponent(lp_move_B))
                .addContainerGap())
        );

        lp_move_basicLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lp_move_B, lp_move_D, lp_move_F, lp_move_L, lp_move_R, lp_move_U});

        lp_move_basicLayout.setVerticalGroup(
            lp_move_basicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_basicLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lp_move_L)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_R)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_U)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_D)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_F)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_B)
                .addContainerGap())
        );

        lp_move_basicLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lp_move_B, lp_move_D, lp_move_F, lp_move_L, lp_move_R, lp_move_U});

        lp_move_advanced.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Advanced", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        lp_move_advanced.setToolTipText("");

        lp_move_M.setText("M");
        lp_move_M.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_MMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_MMouseExited(evt);
            }
        });
        lp_move_M.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_MActionPerformed(evt);
            }
        });

        lp_move_E.setText("E");
        lp_move_E.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_EMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_EMouseExited(evt);
            }
        });
        lp_move_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_EActionPerformed(evt);
            }
        });

        lp_move_S.setText("S");
        lp_move_S.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_SMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_SMouseExited(evt);
            }
        });
        lp_move_S.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_SActionPerformed(evt);
            }
        });

        lp_move_X.setText("X");
        lp_move_X.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_XMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_XMouseExited(evt);
            }
        });
        lp_move_X.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_XActionPerformed(evt);
            }
        });

        lp_move_Y.setText("Y");
        lp_move_Y.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_YMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_YMouseExited(evt);
            }
        });
        lp_move_Y.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_YActionPerformed(evt);
            }
        });

        lp_move_Z.setText("Z");
        lp_move_Z.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lp_move_ZMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lp_move_ZMouseExited(evt);
            }
        });
        lp_move_Z.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lp_move_ZActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lp_move_advancedLayout = new javax.swing.GroupLayout(lp_move_advanced);
        lp_move_advanced.setLayout(lp_move_advancedLayout);
        lp_move_advancedLayout.setHorizontalGroup(
            lp_move_advancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_advancedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lp_move_advancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp_move_E)
                    .addComponent(lp_move_S)
                    .addComponent(lp_move_X)
                    .addComponent(lp_move_Y)
                    .addComponent(lp_move_Z)
                    .addComponent(lp_move_M))
                .addContainerGap())
        );

        lp_move_advancedLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lp_move_E, lp_move_M, lp_move_S, lp_move_X, lp_move_Y, lp_move_Z});

        lp_move_advancedLayout.setVerticalGroup(
            lp_move_advancedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_advancedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lp_move_M)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_S)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_E)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_X)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_Y)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_Z)
                .addContainerGap())
        );

        lp_move_advancedLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lp_move_E, lp_move_M, lp_move_S, lp_move_X, lp_move_Y, lp_move_Z});

        lp_move_details.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        lp_move_inverse_title.setText("Inverse?");

        lp_move_useinverse_btngroup.add(lp_move_inverse_yes);
        lp_move_inverse_yes.setText("yes");

        lp_move_useinverse_btngroup.add(lp_move_inverse_no);
        lp_move_inverse_no.setSelected(true);
        lp_move_inverse_no.setText("no");

        javax.swing.GroupLayout lp_move_detailsLayout = new javax.swing.GroupLayout(lp_move_details);
        lp_move_details.setLayout(lp_move_detailsLayout);
        lp_move_detailsLayout.setHorizontalGroup(
            lp_move_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lp_move_inverse_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_inverse_yes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lp_move_inverse_no)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lp_move_detailsLayout.setVerticalGroup(
            lp_move_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_detailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lp_move_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lp_move_inverse_title)
                    .addComponent(lp_move_inverse_yes)
                    .addComponent(lp_move_inverse_no))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lp_move_preview.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preview", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        lp_move_preview_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moves/shuffle.png"))); // NOI18N
        lp_move_preview_image.setText(" ");
        lp_move_preview_image.setToolTipText("");

        javax.swing.GroupLayout lp_move_previewLayout = new javax.swing.GroupLayout(lp_move_preview);
        lp_move_preview.setLayout(lp_move_previewLayout);
        lp_move_previewLayout.setHorizontalGroup(
            lp_move_previewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_previewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lp_move_preview_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        lp_move_previewLayout.setVerticalGroup(
            lp_move_previewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_move_previewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lp_move_preview_image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout lp_moveLayout = new javax.swing.GroupLayout(lp_move);
        lp_move.setLayout(lp_moveLayout);
        lp_moveLayout.setHorizontalGroup(
            lp_moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_moveLayout.createSequentialGroup()
                .addComponent(lp_move_basic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_advanced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lp_moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lp_move_preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lp_move_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        lp_moveLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lp_move_advanced, lp_move_basic});

        lp_moveLayout.setVerticalGroup(
            lp_moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lp_moveLayout.createSequentialGroup()
                .addComponent(lp_move_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp_move_preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lp_move_basic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(lp_move_advanced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        lp_moveLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lp_move_advanced, lp_move_basic});

        javax.swing.GroupLayout MainLeftPanelLayout = new javax.swing.GroupLayout(MainLeftPanel);
        MainLeftPanel.setLayout(MainLeftPanelLayout);
        MainLeftPanelLayout.setHorizontalGroup(
            MainLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lp_cube, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lp_move, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        MainLeftPanelLayout.setVerticalGroup(
            MainLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainLeftPanelLayout.createSequentialGroup()
                .addComponent(lp_cube, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lp_move, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        MainRightPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rp_cubedimension_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rp_cubedimension_title.setText("Cube dimension:");

        rp_algorithm_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rp_algorithm_title.setText("Algorithm for ints:");

        rp_nummoves_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rp_nummoves_title.setText("Number of moves:");

        rp_cubedimension_value.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rp_cubedimension_value.setText(" ");

        rp_algorithm_value.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rp_algorithm_value.setText(" ");

        rp_nummoves_value.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        rp_nummoves_value.setText(" ");

        rp_previousmoves_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rp_previousmoves_title.setText("Previous moves");

        rp_previousmoves_value.setEditable(false);
        rp_previousmoves_value.setColumns(20);
        rp_previousmoves_value.setRows(5);
        rp_previousmoves_value.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rp_previousmoves_value.setEnabled(false);
        rp_previousmoves_container.setViewportView(rp_previousmoves_value);

        rp_nextmoves_title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rp_nextmoves_title.setText("Next suggested moves");

        rp_nextmoves_value.setEditable(false);
        rp_nextmoves_value.setColumns(20);
        rp_nextmoves_value.setRows(5);
        rp_nextmoves_value.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rp_nextmoves_value.setEnabled(false);
        rp_nextmoves_container.setViewportView(rp_nextmoves_value);

        rp_control_reset.setText("Reset");
        rp_control_reset.setMaximumSize(new java.awt.Dimension(85, 30));
        rp_control_reset.setMinimumSize(new java.awt.Dimension(85, 30));
        rp_control_reset.setPreferredSize(new java.awt.Dimension(85, 30));
        rp_control_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rp_control_resetActionPerformed(evt);
            }
        });
        rp_control.add(rp_control_reset);

        rp_control_shuffle.setText("Shuffle");
        rp_control_shuffle.setMaximumSize(new java.awt.Dimension(85, 30));
        rp_control_shuffle.setMinimumSize(new java.awt.Dimension(85, 30));
        rp_control_shuffle.setPreferredSize(new java.awt.Dimension(85, 30));
        rp_control_shuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rp_control_shuffleActionPerformed(evt);
            }
        });
        rp_control.add(rp_control_shuffle);

        rp_control_autosolve.setText("Auto Solve");
        rp_control_autosolve.setMaximumSize(new java.awt.Dimension(85, 30));
        rp_control_autosolve.setMinimumSize(new java.awt.Dimension(85, 30));
        rp_control_autosolve.setPreferredSize(new java.awt.Dimension(85, 30));
        rp_control_autosolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rp_control_autosolveActionPerformed(evt);
            }
        });
        rp_control.add(rp_control_autosolve);

        javax.swing.GroupLayout MainRightPanelLayout = new javax.swing.GroupLayout(MainRightPanel);
        MainRightPanel.setLayout(MainRightPanelLayout);
        MainRightPanelLayout.setHorizontalGroup(
            MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainRightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rp_nextmoves_title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(MainRightPanelLayout.createSequentialGroup()
                        .addGroup(MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(rp_control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rp_sep1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MainRightPanelLayout.createSequentialGroup()
                                .addGroup(MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(rp_algorithm_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rp_cubedimension_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rp_nummoves_title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rp_cubedimension_value, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rp_algorithm_value, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rp_nummoves_value, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(rp_previousmoves_title, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(rp_previousmoves_container, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(rp_nextmoves_container, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        MainRightPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rp_algorithm_title, rp_cubedimension_title, rp_nummoves_title});

        MainRightPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rp_algorithm_value, rp_cubedimension_value, rp_nummoves_value});

        MainRightPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rp_nextmoves_container, rp_previousmoves_container, rp_previousmoves_title});

        MainRightPanelLayout.setVerticalGroup(
            MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainRightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp_cubedimension_title)
                    .addComponent(rp_cubedimension_value))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp_algorithm_title)
                    .addComponent(rp_algorithm_value))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rp_nummoves_title)
                    .addComponent(rp_nummoves_value))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rp_sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rp_previousmoves_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rp_previousmoves_container, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rp_nextmoves_title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rp_nextmoves_container, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rp_control, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        MainRightPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {rp_algorithm_title, rp_algorithm_value, rp_cubedimension_title, rp_cubedimension_value, rp_nextmoves_title, rp_nummoves_title, rp_nummoves_value, rp_previousmoves_title});

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo.png"))); // NOI18N
        Logo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        menu_file.setMnemonic('f');
        menu_file.setText("File");

        menu_file_exit.setMnemonic('x');
        menu_file_exit.setText("Exit");
        menu_file_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_file_exitActionPerformed(evt);
            }
        });
        menu_file.add(menu_file_exit);

        menuBar.add(menu_file);

        menu_edit.setMnemonic('e');
        menu_edit.setText("Edit");

        menu_edit_numfaces.setText("Number of Faces");
        menu_edit_numfaces.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_edit_numfacesActionPerformed(evt);
            }
        });
        menu_edit.add(menu_edit_numfaces);

        menu_edit_algorithm.setText("Algorithm");
        menu_edit_algorithm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_edit_algorithmActionPerformed(evt);
            }
        });
        menu_edit.add(menu_edit_algorithm);

        menuBar.add(menu_edit);

        menu_help.setText("?");

        menu_help_credits.setText("Credits");
        menu_help_credits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_help_creditsActionPerformed(evt);
            }
        });
        menu_help.add(menu_help_credits);

        menuBar.add(menu_help);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MainLeftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(MainRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MainLeftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Logo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MainRightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_file_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_file_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menu_file_exitActionPerformed

    private void menu_edit_numfacesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_edit_numfacesActionPerformed
        NumFaces n = new NumFaces(this, true);
        n.setVisible(true);
        if(n.getReturnStatus()) {
            this.setCubeDimension(n.getSelectedFaces());
        }
        
    }//GEN-LAST:event_menu_edit_numfacesActionPerformed

    private void menu_edit_algorithmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_edit_algorithmActionPerformed
        new ChangeAlgorithm(this, true, this.availableStrategy).setVisible(true);
    }//GEN-LAST:event_menu_edit_algorithmActionPerformed
    
    private void lp_move_LMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_LMouseEntered
        lp_move_preview_LoadImage("L");
    }//GEN-LAST:event_lp_move_LMouseEntered

    private void lp_move_LMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_LMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_LMouseExited

    private void lp_move_RMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_RMouseEntered
        lp_move_preview_LoadImage("R");
    }//GEN-LAST:event_lp_move_RMouseEntered

    private void lp_move_RMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_RMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_RMouseExited

    private void lp_move_UMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_UMouseEntered
        lp_move_preview_LoadImage("U");
    }//GEN-LAST:event_lp_move_UMouseEntered

    private void lp_move_UMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_UMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_UMouseExited

    private void lp_move_DMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_DMouseEntered
        lp_move_preview_LoadImage("D");
    }//GEN-LAST:event_lp_move_DMouseEntered

    private void lp_move_DMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_DMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_DMouseExited

    private void lp_move_FMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_FMouseEntered
        lp_move_preview_LoadImage("F");
    }//GEN-LAST:event_lp_move_FMouseEntered

    private void lp_move_FMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_FMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_FMouseExited

    private void lp_move_BMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_BMouseEntered
        lp_move_preview_LoadImage("B");
    }//GEN-LAST:event_lp_move_BMouseEntered

    private void lp_move_BMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_BMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_BMouseExited

    private void lp_move_MMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_MMouseEntered
        lp_move_preview_LoadImage("M");
    }//GEN-LAST:event_lp_move_MMouseEntered

    private void lp_move_MMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_MMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_MMouseExited

    private void lp_move_SMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_SMouseEntered
        lp_move_preview_LoadImage("S");
    }//GEN-LAST:event_lp_move_SMouseEntered

    private void lp_move_SMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_SMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_SMouseExited

    private void lp_move_EMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_EMouseEntered
        lp_move_preview_LoadImage("E");
    }//GEN-LAST:event_lp_move_EMouseEntered

    private void lp_move_EMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_EMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_EMouseExited

    private void lp_move_XMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_XMouseEntered
        lp_move_preview_LoadImage("X");
    }//GEN-LAST:event_lp_move_XMouseEntered

    private void lp_move_XMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_XMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_XMouseExited

    private void lp_move_YMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_YMouseEntered
        lp_move_preview_LoadImage("Y");
    }//GEN-LAST:event_lp_move_YMouseEntered

    private void lp_move_YMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_YMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_YMouseExited

    private void lp_move_ZMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_ZMouseEntered
        lp_move_preview_LoadImage("Z");
    }//GEN-LAST:event_lp_move_ZMouseEntered

    private void lp_move_ZMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lp_move_ZMouseExited
        lp_move_preview_UnloadImage();
    }//GEN-LAST:event_lp_move_ZMouseExited

    private void rp_control_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rp_control_resetActionPerformed
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                rootPane, 
                "This action will reset the cube. Are you sure?", 
                "Confirm reset", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE)) {
            initCube();
        }
    }//GEN-LAST:event_rp_control_resetActionPerformed

    private void rp_control_shuffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rp_control_shuffleActionPerformed
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                rootPane, 
                "This action will shuffle the cube. Are you sure?", 
                "Confirm shuffle", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE)) {
            initCube();
        }
    }//GEN-LAST:event_rp_control_shuffleActionPerformed

    private void rp_control_autosolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rp_control_autosolveActionPerformed
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                rootPane, 
                "This action will autosolve the cube. Are you sure?", 
                "Confirm AutoSolve", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE)) {
            initCube();
        }
    }//GEN-LAST:event_rp_control_autosolveActionPerformed

    private void lp_move_LActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_LActionPerformed
        performMove("L");
    }//GEN-LAST:event_lp_move_LActionPerformed

    private void lp_move_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_RActionPerformed
        performMove("R");
    }//GEN-LAST:event_lp_move_RActionPerformed

    private void lp_move_UActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_UActionPerformed
        performMove("U");
    }//GEN-LAST:event_lp_move_UActionPerformed

    private void lp_move_DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_DActionPerformed
        performMove("D");
    }//GEN-LAST:event_lp_move_DActionPerformed

    private void lp_move_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_FActionPerformed
        performMove("F");
    }//GEN-LAST:event_lp_move_FActionPerformed

    private void lp_move_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_BActionPerformed
        performMove("B");
    }//GEN-LAST:event_lp_move_BActionPerformed

    private void lp_move_MActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_MActionPerformed
        performMove("M");
    }//GEN-LAST:event_lp_move_MActionPerformed

    private void lp_move_SActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_SActionPerformed
        performMove("S");
    }//GEN-LAST:event_lp_move_SActionPerformed

    private void lp_move_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_EActionPerformed
        performMove("E");
    }//GEN-LAST:event_lp_move_EActionPerformed

    private void lp_move_XActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_XActionPerformed
        performMove("X");
    }//GEN-LAST:event_lp_move_XActionPerformed

    private void lp_move_YActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_YActionPerformed
        performMove("Y");
    }//GEN-LAST:event_lp_move_YActionPerformed

    private void lp_move_ZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lp_move_ZActionPerformed
        performMove("Z");
    }//GEN-LAST:event_lp_move_ZActionPerformed

    private void menu_help_creditsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_help_creditsActionPerformed
        new Credits(this, true).setVisible(true);
    }//GEN-LAST:event_menu_help_creditsActionPerformed

    private void rotate_X_CWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotate_X_CWActionPerformed
        performMove("X");
    }//GEN-LAST:event_rotate_X_CWActionPerformed

    private void rotate_X_CCWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotate_X_CCWActionPerformed
        this.lp_move_inverse_yes.setSelected(true);
        performMove("X");
        this.lp_move_inverse_no.setSelected(true);
    }//GEN-LAST:event_rotate_X_CCWActionPerformed

    private void rotate_Y_CWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotate_Y_CWActionPerformed
        performMove("Y");
    }//GEN-LAST:event_rotate_Y_CWActionPerformed

    private void rotate_Y_CCWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotate_Y_CCWActionPerformed
        this.lp_move_inverse_yes.setSelected(true);
        performMove("Y");
        this.lp_move_inverse_no.setSelected(true);
    }//GEN-LAST:event_rotate_Y_CCWActionPerformed

    private void rotate_Z_CWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotate_Z_CWActionPerformed
        performMove("Z");
    }//GEN-LAST:event_rotate_Z_CWActionPerformed

    private void rotate_Z_CCWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rotate_Z_CCWActionPerformed
        this.lp_move_inverse_yes.setSelected(true);
        performMove("Z");
        this.lp_move_inverse_no.setSelected(true);
    }//GEN-LAST:event_rotate_Z_CCWActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel B;
    private javax.swing.JLabel B11;
    private javax.swing.JLabel B12;
    private javax.swing.JLabel B13;
    private javax.swing.JLabel B21;
    private javax.swing.JLabel B22;
    private javax.swing.JLabel B23;
    private javax.swing.JLabel B31;
    private javax.swing.JLabel B32;
    private javax.swing.JLabel B33;
    private javax.swing.JPanel D;
    private javax.swing.JLabel D11;
    private javax.swing.JLabel D12;
    private javax.swing.JLabel D13;
    private javax.swing.JLabel D21;
    private javax.swing.JLabel D22;
    private javax.swing.JLabel D23;
    private javax.swing.JLabel D31;
    private javax.swing.JLabel D32;
    private javax.swing.JLabel D33;
    private javax.swing.JPanel F;
    private javax.swing.JLabel F11;
    private javax.swing.JLabel F12;
    private javax.swing.JLabel F13;
    private javax.swing.JLabel F21;
    private javax.swing.JLabel F22;
    private javax.swing.JLabel F23;
    private javax.swing.JLabel F31;
    private javax.swing.JLabel F32;
    private javax.swing.JLabel F33;
    private javax.swing.JPanel L;
    private javax.swing.JLabel L11;
    private javax.swing.JLabel L12;
    private javax.swing.JLabel L13;
    private javax.swing.JLabel L21;
    private javax.swing.JLabel L22;
    private javax.swing.JLabel L23;
    private javax.swing.JLabel L31;
    private javax.swing.JLabel L32;
    private javax.swing.JLabel L33;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel MainLeftPanel;
    private javax.swing.JPanel MainRightPanel;
    private javax.swing.JPanel R;
    private javax.swing.JLabel R11;
    private javax.swing.JLabel R12;
    private javax.swing.JLabel R13;
    private javax.swing.JLabel R21;
    private javax.swing.JLabel R22;
    private javax.swing.JLabel R23;
    private javax.swing.JLabel R31;
    private javax.swing.JLabel R32;
    private javax.swing.JLabel R33;
    private javax.swing.JPanel U;
    private javax.swing.JLabel U11;
    private javax.swing.JLabel U12;
    private javax.swing.JLabel U13;
    private javax.swing.JLabel U21;
    private javax.swing.JLabel U22;
    private javax.swing.JLabel U23;
    private javax.swing.JLabel U31;
    private javax.swing.JLabel U32;
    private javax.swing.JLabel U33;
    private javax.swing.JPanel lp_cube;
    private javax.swing.JPanel lp_move;
    private javax.swing.JButton lp_move_B;
    private javax.swing.JButton lp_move_D;
    private javax.swing.JButton lp_move_E;
    private javax.swing.JButton lp_move_F;
    private javax.swing.JButton lp_move_L;
    private javax.swing.JButton lp_move_M;
    private javax.swing.JButton lp_move_R;
    private javax.swing.JButton lp_move_S;
    private javax.swing.JButton lp_move_U;
    private javax.swing.JButton lp_move_X;
    private javax.swing.JButton lp_move_Y;
    private javax.swing.JButton lp_move_Z;
    private javax.swing.JPanel lp_move_advanced;
    private javax.swing.JPanel lp_move_basic;
    private javax.swing.JPanel lp_move_details;
    private javax.swing.JRadioButton lp_move_inverse_no;
    private javax.swing.JLabel lp_move_inverse_title;
    private javax.swing.JRadioButton lp_move_inverse_yes;
    private javax.swing.JPanel lp_move_preview;
    private javax.swing.JLabel lp_move_preview_image;
    private javax.swing.ButtonGroup lp_move_useinverse_btngroup;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menu_edit;
    private javax.swing.JMenuItem menu_edit_algorithm;
    private javax.swing.JMenuItem menu_edit_numfaces;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenuItem menu_file_exit;
    private javax.swing.JMenu menu_help;
    private javax.swing.JMenuItem menu_help_credits;
    private javax.swing.JPanel rotate_X;
    private javax.swing.JButton rotate_X_CCW;
    private javax.swing.JButton rotate_X_CW;
    private javax.swing.JLabel rotate_X_title;
    private javax.swing.JPanel rotate_Y;
    private javax.swing.JButton rotate_Y_CCW;
    private javax.swing.JButton rotate_Y_CW;
    private javax.swing.JLabel rotate_Y_title;
    private javax.swing.JPanel rotate_Z;
    private javax.swing.JButton rotate_Z_CCW;
    private javax.swing.JButton rotate_Z_CW;
    private javax.swing.JLabel rotate_Z_title;
    private javax.swing.JLabel rp_algorithm_title;
    private javax.swing.JLabel rp_algorithm_value;
    private javax.swing.JPanel rp_control;
    private javax.swing.JButton rp_control_autosolve;
    private javax.swing.JButton rp_control_reset;
    private javax.swing.JButton rp_control_shuffle;
    private javax.swing.JLabel rp_cubedimension_title;
    private javax.swing.JLabel rp_cubedimension_value;
    private javax.swing.JScrollPane rp_nextmoves_container;
    private javax.swing.JLabel rp_nextmoves_title;
    private javax.swing.JTextArea rp_nextmoves_value;
    private javax.swing.JLabel rp_nummoves_title;
    private javax.swing.JLabel rp_nummoves_value;
    private javax.swing.JScrollPane rp_previousmoves_container;
    private javax.swing.JLabel rp_previousmoves_title;
    private javax.swing.JTextArea rp_previousmoves_value;
    private javax.swing.JSeparator rp_sep1;
    // End of variables declaration//GEN-END:variables
    // CHECKSTYLE:ON

    /**
     * Rubik cube model.
     */
    private RubikCubeModel cube;
    /**
     * List of available resolution strategies.
     */
    private List<ResolutionStrategy> availableStrategy;
    /**
     * Chosen resolution strategy.
     */
    private ResolutionStrategy actualStrategy;
    /**
     * Rubik cube dimension.
     */
    private int RubikCubeDimension = 3;
    /**
     * Number of performed moves.
     */
    private int MovesCounter = 0;

    /**
     * Initializes the Rubik cube.
     */
    private void initCube() {
        this.cube = new RubikCubeModel(this.getCubeDimension());
        
        this.availableStrategy = new LinkedList<ResolutionStrategy>();
        this.availableStrategy.add(new Fridrich(this.cube));
        this.actualStrategy = this.availableStrategy.get(0);
        
        this.MovesCounter = 0;
        
        updateInterface();
    }
    
    public int getCubeDimension() {
        return this.RubikCubeDimension;
    }
    public void setCubeDimension(int n) {
        if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(
                rootPane, 
                "This action will reset the cube. Are you sure?", 
                "Confirm reset", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE)) {
            this.RubikCubeDimension = n;
            initCube();
        }
        updateInterface();
    }
    public int getMovesCounter() {
        return this.MovesCounter;
    }
    public void setMovesCounter(int n) {
        this.MovesCounter = n;
        updateInterface();
    }
    
    private void lp_move_preview_LoadImage(String move) {
        if(lp_move_inverse_yes.isSelected())
            lp_move_preview_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moves/"+move+"_inv.png")));
        else
            lp_move_preview_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moves/"+move+".png")));
    }
    private void lp_move_preview_UnloadImage() {
        lp_move_preview_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moves/shuffle.png")));
    }
    
    private void performMove(String move) {
        this.MovesCounter++;
        if(lp_move_inverse_yes.isSelected()) {
            JOptionPane.showMessageDialog(rootPane, "Perform "+move+" inverse move");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Perform "+move+" move");
        }
        
        if(move.equals("L")) new L(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("R")) new R(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("U")) new U(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("D")) new D(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("F")) new F(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("B")) new B(cube, lp_move_inverse_yes.isSelected()).perform();
        //else if(move.equals("M")) new M(cube, lp_move_inverse_yes.isSelected()).perform();
        //else if(move.equals("S")) new S(cube, lp_move_inverse_yes.isSelected()).perform();
        //else if(move.equals("E")) new E(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("X")) new X(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("Y")) new Y(cube, lp_move_inverse_yes.isSelected()).perform();
        else if(move.equals("Z")) new Z(cube, lp_move_inverse_yes.isSelected()).perform();

        updateInterface();
    }
}
