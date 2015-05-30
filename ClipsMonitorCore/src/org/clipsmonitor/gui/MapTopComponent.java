/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.clipsmonitor.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.clipsmonitor.clips.ClipsConsole;
import org.clipsmonitor.core.MonitorCore;
import org.clipsmonitor.monitor2015.RescueMap;
import org.clipsmonitor.monitor2015.RescueModel;
import org.openide.windows.TopComponent;

public abstract class MapTopComponent extends TopComponent implements Observer {
    private MapPanel mapPanel;
    protected RescueModel model;
    protected RescueMap map;
    protected String target;
    protected ClipsConsole console;
    
    public MapTopComponent(){
        initComponents();
        init();
    }
    
    private void init(){
        this.model = RescueModel.getInstance();
        this.model.addObserver(this);
        console = ClipsConsole.getInstance();
    }
    
    private void clear(){
        this.model = null;
        RescueModel.clearInstance();
        this.console = null;
        this.mapPanel.map = null;
        this.mapPanel.model = null;
        this.map = null;
        this.mapPanel = null;
        this.containerPanel.removeAll();
        this.containerPanel.repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerPanel = new javax.swing.JPanel();

        containerPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(containerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel containerPanel;
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
        if(arg.equals(this.target)){
            console.debug("Target map " + arg);
            this.map = model.getMapToRegister();
            this.map.addObserver(this);
        }
        else if(arg.equals("repaint")){
            this.mapPanel.repaint();
        }
        else if(arg.equals("initializeMap")){
            this.initializeMap();
        }
        else if(arg.equals("advise")){
            MonitorCore.getInstance().finished();
            JOptionPane.showMessageDialog(this.containerPanel, model.getAdvise(), "Termine Esecuzione", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(arg == "clearApp"){
            this.clear();
        }
        else if(arg == "startApp"){
            this.init();
        }
    }
    
    
    /**
     * Crea la prima versione della mappa, quella corrispondente all'avvio
     * dell'ambiente. Inserisce in ogni elemento del grid (mappa) la corretta
     * immagine.
     *
     */
    private void initializeMap() {
        String[][] mapString = map.getMap();

        int x = mapString.length;
        int y = mapString[0].length;
        int cellDimension = Math.round(map.MAP_DIMENSION / x);

        // bloccata la dimensione massima delle singole immagini
        if (cellDimension > map.DEFAULT_IMG_SIZE) {
            cellDimension = map.DEFAULT_IMG_SIZE;
        }

        mapPanel = new MapPanel(map);

        javax.swing.GroupLayout mapPanelLayout = new javax.swing.GroupLayout(mapPanel);
        mapPanel.setLayout(mapPanelLayout);
        mapPanelLayout.setHorizontalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mapPanelLayout.setVerticalGroup(
            mapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        containerPanel.add(mapPanel, java.awt.BorderLayout.CENTER);
        this.containerPanel.validate();
    }
    
    protected class MapPanel extends JPanel {
        RescueModel model;
        RescueMap map;
        
        public MapPanel(RescueMap map) {
            
            super();
            model = RescueModel.getInstance();
            this.map = map;
        }
        
        /**
        * Restituisce l'immagine che è la sovrapposizione fra object e background.
        * La dimensione è quella dell'immagine più piccola
        *
        * @param object
        * @param background
        * @return
        */
       private BufferedImage overlapImages(BufferedImage object, BufferedImage background) {
           BufferedImage combined;
           Graphics g;
           // crea una nuova immagine, la dimensione è quella più grande tra le 2 img
           int w = Math.max(background.getWidth(), object.getWidth());
           int h = Math.max(background.getHeight(), object.getHeight());
           combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

           // SOVRAPPONE le immagini, preservando i canali alpha per le trasparenze (figo eh?)
           g = combined.getGraphics();
           g.drawImage(background, 0, 0, null);
           g.drawImage(object, 0, 0, null);

           return combined;
       }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            String[][] mapString = map.getMap();
            Map<String, BufferedImage> map_img = map.getMapImg();
            Map<String, BufferedImage> map_img_robot = map.getMapImgRobot();

            int cellWidth = Math.round((this.getWidth() - 20) / mapString.length);
            int cellHeight = Math.round((this.getHeight() - 20) / mapString[0].length);

            if (cellWidth > cellHeight) {
                cellWidth = cellHeight;
            } else {
                cellHeight = cellWidth;
            }

            int x0 = (this.getWidth() - cellWidth * mapString.length) / 2;
            int y0 = (this.getHeight() - 30 - cellHeight * mapString[0].length) / 2;

            for (int i = mapString.length - 1; i >= 0; i--) {
                g2.drawString((i + 1) + "", x0 - cellWidth, y0 + cellHeight / 2 + cellHeight * (mapString.length - i));
                for (int j = 0; j < mapString[0].length; j++) {
                    if (i == 0) {
                        g2.drawString((j + 1) + "", x0 + cellWidth / 2 + cellWidth * j, y0 + cellHeight / 2);
                    }
                    @SuppressWarnings("UnusedAssignment")
                    String direction = "";
                    BufferedImage icon;
                    BufferedImage background;
                    BufferedImage robot;

                    // cerca se, nei primi 6 caratteri (se ce ne sono almeno 6), c'è la stringa "agent_", vedere metodo updateMap in MonitorModel.java
                    // Nel modello si ha una stringa del tipo agent_empty se l'agent si trova su una cella empty.
                    // In modo da inserire l'icona del robot sopra la cella in cui si trova (le due immagini vengono sovrapposte)
                    // ##### SE AGENTE #####
                    if (mapString[i][j].length() >= 6 && mapString[i][j].substring(0, 6).equals("agent_")) {
                        direction = model.getDirection();
                        // ...nel, caso prosegue dal 6° carattere in poi.

                        background = map_img.get(mapString[i][j].substring(6, mapString[i][j].length()));
                        robot = map_img_robot.get("agent_" + direction);

                        icon = overlapImages(robot, background);

                        //Imposta il tooltip
                        //map[i][j].setToolTipText("Agent (" + (i + 1) + ", " + (j + 1) + ")");
                        // ##### SE PERSONA #####
                    } else if (mapString[i][j].length() >= 7 && mapString[i][j].substring(0, 7).equals("person_")) {
                        //Nella forma person_<Background>_<ident>
                        String map_contains = mapString[i][j];
                        String[] person_info = map_contains.split("_"); //prendiamo i tre campi
                        //path dell'immagine apposita (se esiste) per la persona

                        if (map_img.get(person_info[2] + "-" + person_info[0] + "_" + person_info[1]) != null) { //se esiste immagine apposita per quell'id
                            icon = map_img.get(person_info[2] + "-" + person_info[0] + "_" + person_info[1]);
                        } else { //Se il file non esiste si usa quello di default (senza ident davanti)
                            icon = map_img.get(person_info[0] + "_" + person_info[1]);
                        }
                        //Imposta il tooltip
                        //map[i][j].setToolTipText("Client " + person_info[2] + " " + "(" + (i + 1) + ", " + (j + 1) + ")");

                        // ##### SE TAVOLO ####
                    } else if (mapString[i][j].length() >= 5 && mapString[i][j].substring(0, 5).equals("Table")) {
                        //Nella forma Table_<status>_<table-id>
                        String map_contains = mapString[i][j];
                        if (!map_contains.equals("Table")) {
                            String[] table_info = map_contains.split("_"); //prendiamo i tre campi
                            icon = map_img.get(table_info[0] + "_" + table_info[1]);
                        } else {
                            icon = map_img.get("table_clean");
                        }

                        //Imposta il tooltip
                        //map[i][j].setToolTipText("Table " + table_info[2] + " " + "(" + (i + 1) + ", " + (j + 1) + ")");
                        // ##### ALTRIMENTI ####
                        // Era una cella che non aveva bisogno di sovrapposizioni e non è una persona
                    } else {
                        icon = map_img.get(mapString[i][j]);
                        //map[i][j].setToolTipText("(" + (i + 1) + ", " + (j + 1) + ")");
                    }

                    g2.drawImage(icon, x0 + cellWidth * j, y0 + cellHeight * (mapString.length - i), cellWidth, cellHeight, this);

                }
            }

        }
    }
}
