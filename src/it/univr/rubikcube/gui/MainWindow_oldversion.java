package it.univr.rubikcube.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Main window for the Rubik Cube Solver.
 *
 * @author Alessandro Menti
 */
public class MainWindow_oldversion extends JApplet implements ActionListener {
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of moves.
     */
    private JList movesList;

    /**
     * Rubik cube control.
     */
    private JRubikCube rubikCube;

    /**
     * Entry point for the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocale(new Locale("en", "US"));
        // FIXME Set the correct minimum dimension below and in the manifest
        mainWindow.setBounds(100, 100, 450, 300);
        mainWindow.setResizable(true);
        mainWindow.setTitle("Rubik Cube Solver");
        JApplet applet = new MainWindow_oldversion();
        applet.init();
        applet.start();
        mainWindow.add(applet);
        mainWindow.setVisible(true);
        mainWindow.pack();
    }

    /**
     * Initializes the applet.
     */
    @Override
    public final void init() {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    GridBagLayout gbl = new GridBagLayout();
                    GridBagConstraints gbc;
                    JRootPane appRootPane = MainWindow_oldversion.this.getRootPane();
                    // Set the native look and feel for the platform
                    try {
                        UIManager.setLookAndFeel(UIManager
                                .getSystemLookAndFeelClassName());
                        SwingUtilities.updateComponentTreeUI(MainWindow_oldversion.this);
                    } catch (Exception e) {
                        // Nothing to do
                    }
                    // FIXME - do we need to call pack() afterwards?
                    gbl.columnWidths = new int[] {200, 200};
                    gbl.rowHeights = new int[] {50, 50};
                    gbl.columnWeights = new double[]{1.0, 1.0};
                    gbl.rowWeights = new double[]{1.0, 1.0};
                    appRootPane.setLayout(gbl);

                    // Rubik cube
                    JRubikCube jrcCube = new JRubikCube(3);
                    gbc = new GridBagConstraints();
                    gbc.insets = new Insets(0, 0, 5, 5);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    appRootPane.add(jrcCube, gbc);

                    // Options frame
                    JPanel pnlOptions = new JPanel();
                    gbc = new GridBagConstraints();
                    gbc.insets = new Insets(0, 0, 5, 0);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    appRootPane.add(pnlOptions, gbc);
                    GridBagLayout gblOptions = new GridBagLayout();
                    gblOptions.columnWidths = new int[] {0, 0, 0};
                    gblOptions.rowHeights = new int[] {0, 0, 0};
                    gblOptions.columnWeights = new double[] {0.0, 1.0,
                        Double.MIN_VALUE};
                    gblOptions.rowWeights = new double[] {0.0, 0.0,
                        Double.MIN_VALUE};
                    pnlOptions.setLayout(gblOptions);

                    JLabel lblAlgorithm = new JLabel("Algorithm to use");
                    gbc = new GridBagConstraints();
                    gbc.insets = new Insets(0, 0, 5, 5);
                    gbc.anchor = GridBagConstraints.EAST;
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    pnlOptions.add(lblAlgorithm, gbc);

                    JComboBox cmbAlgorithm = new JComboBox();
                    gbc = new GridBagConstraints();
                    gbc.insets = new Insets(0, 0, 5, 0);
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 1;
                    gbc.gridy = 0;
                    pnlOptions.add(cmbAlgorithm, gbc);

                    JLabel lblFaces = new JLabel("Number of faces");
                    gbc = new GridBagConstraints();
                    gbc.anchor = GridBagConstraints.EAST;
                    gbc.insets = new Insets(0, 0, 0, 5);
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    pnlOptions.add(lblFaces, gbc);

                    JComboBox cmbFaces = new JComboBox();
                    gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    pnlOptions.add(cmbFaces, gbc);

                    JList lstMoves = new JList();
                    gbc = new GridBagConstraints();
                    gbc.insets = new Insets(0, 0, 0, 5);
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    appRootPane.add(lstMoves, gbc);

                    JPanel pnlCommands = new JPanel();
                    gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.BOTH;
                    gbc.gridx = 1;
                    gbc.gridy = 1;
                    appRootPane.add(pnlCommands, gbc);

                    JButton cmdExecute = new JButton("Execute");
                    cmdExecute.setMnemonic('e');
                    cmdExecute.setActionCommand("execute");
                    cmdExecute.addActionListener(MainWindow_oldversion.this);
                    pnlCommands.add(cmdExecute);

                    JButton cmdQuit = new JButton("Quit");
                    cmdQuit.setMnemonic('q');
                    cmdQuit.setActionCommand("quit");
                    cmdQuit.addActionListener(MainWindow_oldversion.this);
                    pnlCommands.add(cmdQuit);
                }
            });
        } catch (Exception e) {
            System.err.println("Unable to create the GUI");
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        if ("execute".equals(e.getActionCommand())) {
            // FIXME
        } else if ("quit".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }
}
