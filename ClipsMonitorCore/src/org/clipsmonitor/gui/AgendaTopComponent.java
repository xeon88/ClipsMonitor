/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clipsmonitor.gui;

import java.util.Observable;
import java.util.Observer;
import org.clipsmonitor.clips.ClipsConsole;
import org.clipsmonitor.core.MonitorModel;
import org.clipsmonitor.monitor2015.RescueModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.clipsmonitor.gui//Agenda//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "AgendaTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "agenda", openAtStartup = true)
@ActionID(category = "Window", id = "org.clipsmonitor.gui.AgendaTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_AgendaAction",
        preferredID = "AgendaTopComponent"
)
@Messages({
    "CTL_AgendaAction=Agenda",
    "CTL_AgendaTopComponent=Agenda Window",
    "HINT_AgendaTopComponent=This is a Agenda window"
})
public final class AgendaTopComponent extends TopComponent implements Observer {
    private MonitorModel model;
    private ClipsConsole console;
    
    public AgendaTopComponent() {
        initComponents();
        setName(Bundle.CTL_AgendaTopComponent());
        setToolTipText(Bundle.HINT_AgendaTopComponent());
        init();
    }
    
    private void init(){
        model = RescueModel.getInstance();
        console = ClipsConsole.getInstance();
        model.addObserver(this);
    }
    
    private void clear(){
        model = null;
        console = null;
        this.jTextPane1.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jTextPane1.setEditable(false);
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
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
    
    @Override
    public void update(Observable o, Object arg) {
        if(arg == "actionDone" || arg == "cmd" || arg == "disposeDone" || arg == "setupDone"){
            this.updateAgenda();
        }
        else if(arg == "clearApp"){
            this.clear();
        }
        else if(arg == "startApp"){
            this.init();
        }
    }
    
    private void updateAgenda(){
        this.jTextPane1.setText(model.getAgenda() + "(module " + model.getFocus() + ")");
        this.jTextPane1.setCaretPosition(0);
    }
}
