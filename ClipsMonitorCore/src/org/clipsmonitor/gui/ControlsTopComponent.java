/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clipsmonitor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import javax.swing.AbstractAction;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import org.clipsmonitor.clips.ClipsModel;
import org.clipsmonitor.core.MonitorCore;
import org.clipsmonitor.monitor2015.RescueImages;
import org.clipsmonitor.monitor2015.RescueMap;
import org.clipsmonitor.monitor2015.RescueModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.modules.InstalledFileLocator;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.clipsmonitor.core//Controls//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "ControlsTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "controls", openAtStartup = true)
@ActionID(category = "Window", id = "org.clipsmonitor.core.ControlsTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_ControlsAction",
        preferredID = "ControlsTopComponent"
)
@Messages({
    "CTL_ControlsAction=Controls",
    "CTL_ControlsTopComponent=Controls Window",
    "HINT_ControlsTopComponent=This is a Controls window"
})



public final class ControlsTopComponent extends TopComponent implements Observer,
        KeyListener ,  ActionListener{
    RescueModel model;
    MonitorCore core;
    File projectDirectory;
    
    @SuppressWarnings("rawtypes")
    public ControlsTopComponent() {
        loadPreferences();
        RescueImages.getInstance().loadImages(projectDirectory.getAbsolutePath());
        initComponents();
        setName(Bundle.CTL_ControlsTopComponent());
        setToolTipText(Bundle.HINT_ControlsTopComponent());
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
        this.setShortcut();
    
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        strategyLabel = new javax.swing.JLabel();
        envLabel = new javax.swing.JLabel();
        envsSelector = new javax.swing.JComboBox();
        CLPSelector = new javax.swing.JComboBox();
        loadDefaultFileButton = new javax.swing.JButton();
        runOneButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        stepButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        stepTextField = new javax.swing.JTextField();
        timeTextField = new javax.swing.JTextField();
        timeLeftTextField = new javax.swing.JTextField();
        strategyLabel1 = new javax.swing.JLabel();
        envLabel1 = new javax.swing.JLabel();
        envLabel2 = new javax.swing.JLabel();
        envLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(strategyLabel, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.strategyLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(envLabel, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.envLabel.text")); // NOI18N

        envsSelector.setModel(loadEnvsFolderNames());
        envsSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envsSelectorActionPerformed(evt);
            }
        });

        CLPSelector.setModel(loadCLPFolderNames());
        CLPSelector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CLPSelectorItemStateChanged(evt);
            }
        });
        CLPSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CLPSelectorActionPerformed(evt);
            }
        });

        loadDefaultFileButton.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        loadDefaultFileButton.setForeground(new java.awt.Color(0, 204, 0));
        org.openide.awt.Mnemonics.setLocalizedText(loadDefaultFileButton, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.loadDefaultFileButton.text")); // NOI18N
        loadDefaultFileButton.setToolTipText("CTRL+A");
        loadDefaultFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadDefaultFileButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(runOneButton, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.runOneButton.text")); // NOI18N
        runOneButton.setToolTipText("CTRL+T");
        runOneButton.setEnabled(false);
        runOneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runOneButtonActionPerformed(evt);
            }
        });
        runOneButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                runOneButtonKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                runOneButtonKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                runOneButtonKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(runButton, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.runButton.text")); // NOI18N
        runButton.setToolTipText("CTRL+R");
        runButton.setEnabled(false);
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(stepButton, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.stepButton.text")); // NOI18N
        stepButton.setToolTipText("CTRL+S");
        stepButton.setEnabled(false);
        stepButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepButtonActionPerformed(evt);
            }
        });

        resetButton.setFont(new java.awt.Font("Lucida Grande", 1, 12)); // NOI18N
        resetButton.setForeground(new java.awt.Color(255, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(resetButton, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.resetButton.text")); // NOI18N
        resetButton.setToolTipText("CTRL+X");
        resetButton.setEnabled(false);
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        stepTextField.setEditable(false);
        stepTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stepTextField.setText(org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.stepTextField.text")); // NOI18N
        stepTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepTextFieldActionPerformed(evt);
            }
        });
        stepTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                stepTextFieldPropertyChange(evt);
            }
        });

        timeTextField.setEditable(false);
        timeTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        timeTextField.setText(org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.timeTextField.text")); // NOI18N
        timeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeTextFieldActionPerformed(evt);
            }
        });

        timeLeftTextField.setEditable(false);
        timeLeftTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        timeLeftTextField.setText(org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.timeLeftTextField.text")); // NOI18N
        timeLeftTextField.setName(""); // NOI18N
        timeLeftTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeLeftTextFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(strategyLabel1, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.strategyLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(envLabel1, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.envLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(envLabel2, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.envLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(envLabel3, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.envLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ControlsTopComponent.class, "ControlsTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(strategyLabel)
                            .addComponent(envLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(envsSelector, 0, 55, Short.MAX_VALUE)
                            .addComponent(CLPSelector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                        .addComponent(envLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(envLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(envLabel1)
                            .addComponent(strategyLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(timeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeLeftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(stepButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(runOneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loadDefaultFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(runButton)
                            .addComponent(timeLeftTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(strategyLabel1)
                            .addComponent(CLPSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(strategyLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(runOneButton)
                            .addComponent(timeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(envLabel1)
                            .addComponent(envsSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(envLabel)))
                    .addComponent(loadDefaultFileButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepButton)
                    .addComponent(stepTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(envLabel2)
                    .addComponent(resetButton)
                    .addComponent(envLabel3)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /*
       I|O event managment functions 
    */
    
    private void timeLeftTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeLeftTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeLeftTextFieldActionPerformed

    private void timeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_timeTextFieldActionPerformed

    private void stepTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stepTextFieldActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        core.resetApplication();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void stepButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepButtonActionPerformed
        model.setMode(ClipsModel.ex_mode_STEP);
        model.resume();
        //model.step();
    }//GEN-LAST:event_stepButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        // Il tasto può essere Run oppure Stop (a seconda di cosa era attivo)
        if (runButton.getText().equals("Run")) {
            model.setMode(ClipsModel.ex_mode_RUN);
            model.resume();
            runButton.setText("Stop");
            stepButton.setEnabled(false);
            runOneButton.setEnabled(false);
            resetButton.setEnabled(false);
        } else
         {
                model.setMode(ClipsModel.ex_mode_STOP);
                runButton.setText("Run");
                stepButton.setEnabled(true);
                runOneButton.setEnabled(true);
                resetButton.setEnabled(true);
        }
    }//GEN-LAST:event_runButtonActionPerformed

    private void runOneButtonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_runOneButtonKeyReleased

    }//GEN-LAST:event_runOneButtonKeyReleased

    private void runOneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runOneButtonActionPerformed
        model.setMode(ClipsModel.ex_mode_RUNN, 1);
        model.resume();
    }//GEN-LAST:event_runOneButtonActionPerformed


    
    private void loadDefaultFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadDefaultFileButtonActionPerformed
        loadDefaultFileButton.setEnabled(false);
        runButton.setEnabled(true);
        runOneButton.setEnabled(true);
        stepButton.setEnabled(true);
        resetButton.setEnabled(true);
        CLPSelector.setEnabled(false);
        envsSelector.setEnabled(false);
        String strategyFolder_name = CLPSelector.getSelectedItem().toString(); //La strategia scelta
        String envsFolder_name = envsSelector.getSelectedItem().toString(); //La cartella di env scelta
        model = RescueModel.getInstance();
        model.addObserver(this);
        model.registerMap("envMap", new RescueMap(projectDirectory.getAbsolutePath()));
        core = MonitorCore.getInstance();
        model.startCore(projectDirectory.getAbsolutePath(), strategyFolder_name, envsFolder_name); //Diciamo al modello di partire
        model.setMode(ClipsModel.ex_mode_START);
        model.execute();
    }//GEN-LAST:event_loadDefaultFileButtonActionPerformed

    private void CLPSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CLPSelectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CLPSelectorActionPerformed

    private void CLPSelectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CLPSelectorItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CLPSelectorItemStateChanged

    private void envsSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envsSelectorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_envsSelectorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resetProjectDirectory();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void stepTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_stepTextFieldPropertyChange
        
        //Object new_val = evt.getNewValue();
        //String step= new_val.toString();
        //stepTextField.setText(step);
    }//GEN-LAST:event_stepTextFieldPropertyChange

    private void runOneButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_runOneButtonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_runOneButtonKeyPressed

    private void runOneButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_runOneButtonKeyTyped
        model.setMode(ClipsModel.ex_mode_RUNN, 1);
        model.resume();                                          
    }//GEN-LAST:event_runOneButtonKeyTyped

        private void setShortcut() {
        /**
         * Scorciatoie per RUN, RUN1, STEP, START E RESET. runButton: Alt+R
         * runOneButton: Alt+T, stepButton: Alt+S loadDefaultFileButton: Alt+A
         * resetButton: Alt+X
         */

        runButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK), "run");
        runOneButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_MASK), "run1");
        stepButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK), "step");
        loadDefaultFileButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK), "start");
        resetButton.getInputMap(JButton.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK), "reset");

        runButton.getActionMap().put("run", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runButton.doClick();
            }
        });
        runOneButton.getActionMap().put("run1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runOneButton.doClick();
            }
        });
        stepButton.getActionMap().put("step", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stepButton.doClick();
            }
        });
        loadDefaultFileButton.getActionMap().put("start", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDefaultFileButton.doClick();
            }
        });
        resetButton.getActionMap().put("reset", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetButton.doClick();
            }
        });
    }
    
    void resetProjectDirectory(){
        String prefPath = InstalledFileLocator.getDefault().locate(".", null, false).getParentFile().getAbsolutePath() + "/preferences.txt";
        File prefFile = new File(prefPath);
        if (prefFile.exists() && !prefFile.isDirectory()) {
            prefFile.delete();
        }
        infoBox("Percorso del progetto resettato. Riavvia per settare un nuovo percorso", "Riavvia");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox CLPSelector;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JLabel envLabel;
    private javax.swing.JLabel envLabel1;
    private javax.swing.JLabel envLabel2;
    private javax.swing.JLabel envLabel3;
    private javax.swing.JComboBox envsSelector;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton loadDefaultFileButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JButton runButton;
    private javax.swing.JButton runOneButton;
    private javax.swing.JButton stepButton;
    private javax.swing.JTextField stepTextField;
    private javax.swing.JLabel strategyLabel;
    private javax.swing.JLabel strategyLabel1;
    private javax.swing.JTextField timeLeftTextField;
    private javax.swing.JTextField timeTextField;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
        public JTextField getStepTextField() {
        return stepTextField;
    }

    public JTextField getTimeTextField() {
        return timeTextField;
    }

    public JTextField getLeftTimeTextField() {
        return this.timeLeftTextField;
    }

    public void setStepTextField(JTextField stepTextField) {
        this.stepTextField = stepTextField;
    }

    public void setTimeTextField(JTextField timeTextField) {
        this.timeTextField = timeTextField;
    }

    public void setLeftTimeTextField(JTextField timeLeftTextField) {
        this.timeLeftTextField = timeLeftTextField;
    }

    /**
     * Estrae il nome di un file (con l'estensione) da un initialPath.
     *
     * @param initialPath un initialPath ad un file
     * @return una stringa contenente solo il nome del file
     */
    static private String filename(String path) {
        int i = path.lastIndexOf(File.separator);
        return path.substring(i + 1, path.length());
    }

    /**
     * Carica la lista delle strategie disponibili. L'informazione è ottenuta
     * leggendo tutte le cartelle contenute dentro la cartella CLP nella root
     * del progetto. Ogni cartella contiene tutto il progetto che implementa la
     * relativa strategia.
     *
     * @return il combobox contenente tutte le strategie disponibili
     */
    private ComboBoxModel loadCLPFolderNames() {
        try{
            File folder = new File(projectDirectory.getAbsolutePath() + File.separator + "CLP");
            DefaultComboBoxModel<String> result = new DefaultComboBoxModel();
            File[] listOfFiles = folder.listFiles();
            
            Arrays.sort(listOfFiles, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.compareTo(o2);
                }
            });
            
            for (File file : listOfFiles) {
                if (file.isDirectory() && !file.isHidden() && !file.getName().startsWith(".")) {
                    result.addElement(file.getName());
                }
            }
            return result;
        }
        catch(NullPointerException ex){
            infoBox("Impossibile trovare la cartella \"CLP\" in " + projectDirectory.getAbsolutePath(), "Errore");
            resetProjectDirectory();
            LifecycleManager.getDefault().exit();
            return null;
        }
    }

    /**
     * Carica la lista degli environment disponibili. L'informazione è ottenuta
     * leggendo tutte le cartelle contenute dentro la cartella envs nella root
     * del progetto. Ogni cartella contiene tutto il progetto che implementa la
     * relativa strategia e l'environment.
     *
     * @return Il modello per far sì che venga costruita il selector.
     */
    private ComboBoxModel loadEnvsFolderNames() {
        try {
            File folder = new File(projectDirectory.getAbsolutePath() + File.separator + "envs");
            DefaultComboBoxModel<String> result = new DefaultComboBoxModel();
            File[] listOfFiles = folder.listFiles();
            
            Arrays.sort(listOfFiles, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.compareTo(o2);
                }
            });
            
            for (File file : listOfFiles) {
                if (file.isDirectory() && !file.isHidden() && !file.getName().startsWith(".")) {
                    result.addElement(file.getName());
                }
            }
            return result;
        }
        catch(NullPointerException ex){
            infoBox("Impossibile trovare la cartella \"envs\" in " + projectDirectory.getAbsolutePath(), "Errore");
            resetProjectDirectory();
            LifecycleManager.getDefault().exit();
            return null;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg.equals("finished")){
            this.runButton.doClick();
            this.runButton.setEnabled(false);
            this.runOneButton.setEnabled(false);
            this.stepButton.setEnabled(false);
        }
        else if(arg == "clearApp"){
            this.clear();
        }
        else if(arg == "startApp"){
            this.init();
        }
        else if(arg == "repaint"){
        
            Integer step = model.getStep();
            Integer time = model.getTime();
            Integer leftTime = model.getMaxDuration() - model.getTime();
            this.getTimeTextField().setText(time.toString());
            this.getLeftTimeTextField().setText(leftTime.toString());
            this.getStepTextField().setText(step.toString());
           
        }
    }

    private void clear() {
        this.model = null;
        this.core = null;
        loadDefaultFileButton.setEnabled(false);
        runButton.setEnabled(false);
        runOneButton.setEnabled(false);
        stepButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    private void init() {
        loadDefaultFileButton.setEnabled(true);
        CLPSelector.setEnabled(true);
        envsSelector.setEnabled(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       if(e.getKeyCode() == KeyEvent.VK_SPACE){
            model.setMode(ClipsModel.ex_mode_RUNN, 1);
            model.resume();
       }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    
    
    private void loadPreferences() {
        /* ------- Find the preferences file ------- */
        String prefPath = InstalledFileLocator.getDefault().locate(".", null, false).getParentFile().getAbsolutePath() + File.separator + "preferences.txt";
        File prefFile = new File(prefPath);
        boolean dirFound = false;
        if(prefFile.exists() && !prefFile.isDirectory()) {
            try{
                String pref = Files.readAllLines(prefFile.toPath()).get(0);
                File dir = new File(pref);
                if(dir.exists() && dir.isDirectory()){
                    dirFound = true;
                    this.projectDirectory = dir;
                }
            }
            catch(IOException ex){
                infoBox("Impossibile caricare le preferenze dell'utente", "Errore");
                LifecycleManager.getDefault().exit();
            }
        }
        /* ------- Preferences file does not exist ------- */
        if(!dirFound){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Seleziona la cartella del progetto CLIPS");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                this.projectDirectory = chooser.getSelectedFile();
                try {
                    FileWriter fileWriter = new FileWriter(prefFile);
                    fileWriter.write(this.projectDirectory.getAbsolutePath());
                    fileWriter.flush();
                    fileWriter.close();
                }
                catch(IOException ex){
                    infoBox(ex.getMessage(), "Errore");
                    LifecycleManager.getDefault().exit();                    
                }
            } else {
                LifecycleManager.getDefault().exit();
            }
        }
    }
    
    
    public static void infoBox(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    private class ClpFileFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String ext = null;
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            if (ext.equalsIgnoreCase("clp")) {
                return true;
            }
            return false;
        }

        @Override
        public String getDescription() {
            return "Clips files";
        }
    }
}
