/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import mx.org.rugi.tools.siseon.core.IndexResult;
import mx.org.rugi.tools.siseon.services.DemiurgoFacade;

/**
 *
 * @author rugi
 */
public class JINDEXCreator extends JPanel {

    /**
     *
     */
    private JScrollPane scrollRepos;
    /**
     *
     */
    private JList listItems;
    /**
     *
     */
    private DefaultListModel itemsModel;
    /*
     *
     */
    private JSplitPane split;
    /**
     *
     */
    private Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    /**
     *
     */
    private TitledBorder title;
    /**
     *
     */
    private TitledBorder titleDir;
    /**
     *
     */
    private TitledBorder titleLog;
    /**
     *
     */
    private JScrollPane scrollLog;
    /**
     *
     */
    private JTextArea log;
    /**
     *
     */
    private JProgressBar progress;
    /**
     *
     */
    private JPanel panelDetail;
    /**
     *
     */
    private JPanel panelSelect;

    /**
     *
     */
    private JPanel panelItems;
    /**
     *
     */
    private JButton buttonSearch;

    /**
     *
     */
    private JButton buttonAddItem;
    /**
     *
     */
    //private JLabelFileChooser chooserDir;
    /**
     *
     */
    private JLabel status;

    /**
     *
     */
    private JFileChooser chooser;

    public JINDEXCreator() {
        super();
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout());
        status = new JLabel("Listo");
        itemsModel = new DefaultListModel();
        listItems = new JList(itemsModel);
        listItems.addKeyListener(new KeyListener() {

            // 8 back spcae
            //127 es delete
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_DELETE
                        || ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    int p = listItems.getSelectedIndex();
                    if (p >= 0) {
                        Object[] options = {"Sí",
                            "No"};
                        int n = JOptionPane.showOptionDialog(null,
                                "¿Desea eliminar el archivo seleccionado?",
                                "Eliminar archivo de la lista.",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[1]);

                        if (n == 0) {
                            //se elimina del modelo
                            itemsModel.remove(p);
                            //se actualiza la lista
                            listItems.repaint();
                        }
                    }
                }
            }
        });
        listItems.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollRepos = new JScrollPane(listItems);

        // Panel para agregar archivos
        panelItems = new JPanel();
        this.chooser = new JFileChooser();
        this.chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.chooser.setFileFilter(new FileNameExtensionFilter("PDF Docs", "pdf"));
        this.chooser.setMultiSelectionEnabled(true);
        panelItems.setLayout(new BorderLayout());
        buttonAddItem = new JButton("Agregar");
        buttonAddItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    System.out.println("->" + chooser.getSelectedFile().getAbsolutePath());
                    File[] files = chooser.getSelectedFiles();
                    if (files != null && files.length > 0) {
                        for (int i = 0; i < files.length; i++) {
                            if(files[i].getAbsolutePath().toLowerCase().endsWith(".pdf")){
                                itemsModel.addElement(files[i].getAbsolutePath());
                            }
                        }

                    }
                } else {
                    //
                }
            }
        });
        panelItems.add(buttonAddItem, BorderLayout.NORTH);
        panelItems.add(scrollRepos, BorderLayout.CENTER);

        // panel seleccion
        //chooserDir = new JLabelFileChooser();
        titleDir = BorderFactory.createTitledBorder(
                loweredetched, "Iniciar:");
        titleLog = BorderFactory.createTitledBorder(
                loweredetched, "Salida:");
        panelSelect = new JPanel();
        panelSelect.setLayout(new BorderLayout());
        //panelSelect.add(chooserDir, BorderLayout.CENTER);

        buttonSearch = new JButton("Indexar");
        buttonSearch.addActionListener(new SearchListener());
        panelSelect.add(buttonSearch, BorderLayout.SOUTH);
        panelSelect.setBorder(titleDir);
        // panel Details
        log = new JTextArea();
        log.setEditable(false);

        scrollLog = new JScrollPane(log);
        scrollLog.setBorder(titleLog);
        progress = new JProgressBar();
        panelDetail = new JPanel();
        panelDetail.setLayout(new BorderLayout());
        panelDetail.add(panelSelect, BorderLayout.NORTH);
        panelDetail.add(scrollLog, BorderLayout.CENTER);
        panelDetail.add(progress, BorderLayout.SOUTH);
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelItems, panelDetail);
        split.setOneTouchExpandable(true);
        split.setDividerLocation(150);
        this.add(split, BorderLayout.CENTER);
        title = BorderFactory.createTitledBorder(
                loweredetched, "Archivos a indexar:");
        title.setTitleJustification(TitledBorder.LEFT);
        this.setBorder(title);
        //TODO meter la seleccion de carpetas.
        this.add(status, BorderLayout.SOUTH);
    }

    class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            //String dir = chooserDir.getDirectory();

            if (itemsModel.getSize() == 0) {
                JOptionPane.showMessageDialog(JINDEXCreator.this, "Debe indicar al menos un archivo PDF.",
                        "Se requieren datos para indexar. ", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Enumeration e1 = itemsModel.elements();
            List<String> elements = new ArrayList<String>();
            while (e1.hasMoreElements()) {
                Object nextElement = e1.nextElement();
                System.out.println("->" + nextElement);
                elements.add(nextElement.toString());
            }
            SearchPDFThread sjt = new SearchPDFThread(elements);
            (new Thread(sjt)).start();            
        }
    }

    class SearchPDFThread implements Runnable {

        private int totalJars;
        private List<String> elements;

        public SearchPDFThread(List<String> elements) {
            super();
            this.elements = elements;
        }

        @Override
        public void run() {
           progress.setIndeterminate(true);
            if (this.elements != null && this.elements.size() > 0) {
                IndexResult res = DemiurgoFacade.getInstance().getService().indexWhithThisList(elements);
                log.setText(res.getMessage());
            }//if            
            progress.setIndeterminate(false);
        }//run
    }
}
