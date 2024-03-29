package it.univr.rubikcube.gui;

import it.univr.rubikcube.resolutionstrategies.ResolutionStrategy;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Form used to change the resolution strategy.
 * @author Mattia Zago
 */
public class ChangeAlgorithm extends javax.swing.JDialog {
    /**
     * A return status code - returned if Cancel button has been pressed.
     */
    public static final boolean RET_CANCEL = false;
    /**
     * A return status code - returned if OK button has been pressed.
     */
    public static final boolean RET_OK = true;
    /**
     * UID used for serialization.
     */
    private static final long serialVersionUID = 1L;
    /**
     * List of available resolution strategies.
     */
    private final List<ResolutionStrategy> algorithms;
    /**
     * Default return status.
     */
    private boolean returnStatus = RET_CANCEL;
    /**
     * Number of faces for the Rubik cube.
     */
    private int selectedFaces = 3;
    private ResolutionStrategy selected_option;

    public ResolutionStrategy getSelected_option() {
        return selected_option;
    }
    
    /**
     * Creates a new instance of the "Change resolution strategy" form.
     * @param parent Parent form.
     * @param modal Whether this dialog should be modal.
     * @param algs List of resolution strategies.
     */
    public ChangeAlgorithm(final java.awt.Frame parent, final boolean modal,
                           final List<ResolutionStrategy> algs) {
        super(parent, modal);
        this.algorithms = algs;
        initComponents();
        this.algorithms_list.setSelectedIndex(0);
        algorithms_listActionPerformed(null);
        
        setLocationRelativeTo(parent);
        // Close the dialog when Esc is pressed
        final String cancelName = "cancel";
        final InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        final ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(final ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    /**
     * Gets the return status of this dialog.
     * @return The return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public final boolean getReturnStatus() {
        return this.returnStatus;
    }
    /**
     * Gets the number of faces for the Rubik cube.
     * @return Number of faces for the Rubik cube.
     */
    public final int getSelectedFaces() {
        return this.selectedFaces;
    }
    /**
     * Gets a list of resolution strategies.
     * @return List of resolution strategies.
     */
    public final ComboBoxModel getAlgorithms() {
        return new DefaultComboBoxModel(this.algorithms.toArray());        
    }
    
    // CHECKSTYLE:OFF Rationale: autogenerated code
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        algorithms_list = new javax.swing.JComboBox();
        algorithm_container = new javax.swing.JScrollPane();
        algorithm_description = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit number of faces");
        setAlwaysOnTop(true);
        setIconImage(null);
        setName("NumFaces"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        title.setText("Change Algorithm");
        title.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        algorithms_list.setModel(getAlgorithms());
        algorithms_list.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithms_listActionPerformed(evt);
            }
        });

        algorithm_description.setEditable(false);
        algorithm_description.setColumns(20);
        algorithm_description.setLineWrap(true);
        algorithm_description.setRows(5);
        algorithm_description.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        algorithm_container.setViewportView(algorithm_description);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(algorithms_list, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(algorithm_container, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(algorithms_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(algorithm_container, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        this.selectedFaces = this.algorithms_list.getSelectedIndex();
        doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void algorithms_listActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithms_listActionPerformed
        this.selected_option = (ResolutionStrategy)this.algorithms_list.getSelectedItem();
        this.algorithm_description.setText(selected_option.getDescription());
    }//GEN-LAST:event_algorithms_listActionPerformed
    
    void doClose(boolean retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane algorithm_container;
    private javax.swing.JTextArea algorithm_description;
    private javax.swing.JComboBox algorithms_list;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
    // CHECKSTYLE:ON
}
