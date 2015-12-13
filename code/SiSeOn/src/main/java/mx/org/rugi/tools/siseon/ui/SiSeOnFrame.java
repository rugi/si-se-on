/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.ui;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mx.org.rugi.tools.siseon.core.IArray;
import mx.org.rugi.tools.siseon.services.DemiurgoFacade;
import mx.org.rugi.tools.siseon.util.FileUtil;

/**
 *
 * @author rugi
 */
public class SiSeOnFrame extends JFrame {

    /**
     *
     */
    private JMenuBar menuBar;
    /**
     *
     */
    private JMenu mMain;
    /**
     *
     */
    private JMenu mExit;
    /**
     *
     */
    private JMenuItem mIExit;
    /**
     *
     */
    private JMenuItem mCreateIndex;
    /**
     *
     */
    private JMenuItem mDeleteIndex;
    /**
     *
     */
    private StatusBar status;

    /**
     *
     */
    private SingleTableModel modeloGrid;
    /**
     *
     */
    private JTable grid;

    /**
     *
     */
    private JTabbedPane tabbed;

    /**
     *
     */
    private JLabelInput labelInput;

    /**
     *
     */
    private JButton buttonSearch;

    /**
     *
     */
    public SiSeOnFrame() {
        this("");

    }

    private void validateMenu() {
        if (!DemiurgoFacade.getInstance().getService().existRepo()) {
            mCreateIndex.setEnabled(true);
            mDeleteIndex.setEnabled(false);
        } else {
            mCreateIndex.setEnabled(false);
            mDeleteIndex.setEnabled(true);
        }
    }

    private void initComponents() {

        //primero el menu
        mIExit = new JMenuItem("Exit",
                KeyEvent.VK_E);
        mCreateIndex = new JMenuItem("CREATE INDEX",
                KeyEvent.VK_C);
        mDeleteIndex = new JMenuItem("DELETE INDEX",
                KeyEvent.VK_D);
        mDeleteIndex.setEnabled(false);
        mExit = new JMenu("Exit");
        mExit.setMnemonic(KeyEvent.VK_X);
        mExit.add(mIExit);
        mIExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        mMain = new JMenu("INDEX");
        mMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Existe el repo " + DemiurgoFacade.getInstance().getService().existRepo());

            }
        });
        mMain.setMnemonic(KeyEvent.VK_I);

        mMain.add(mCreateIndex);
        mCreateIndex.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JDialog jdialog = new JDialog();
                jdialog.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        validateMenu();
                    }
                });
                jdialog.setSize(400, 400);
                jdialog.setContentPane(new JINDEXCreator());
                jdialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jdialog.setModal(true);
                jdialog.setVisible(true);
            }
        });

        mMain.add(mDeleteIndex);

        mDeleteIndex.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                Object[] options = {"Sí",
                    "No"};
                int n = JOptionPane.showOptionDialog(null,
                        "¿Desea eliminar el INDEX actual?",
                        "Eliminar el INDEX actual..",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                if (n == 0) {
                    //se elimina del modelo
                    boolean del = DemiurgoFacade.getInstance().getService().deleteRepo();
                    if (del) {
                        JOptionPane.showMessageDialog(SiSeOnFrame.this,
                                "El INDEX se elimino satisfactoriamente, puedes volver a crear uno.",
                                "INDEX eliminado.",
                                JOptionPane.WARNING_MESSAGE);
                        validateMenu();
                    }
                }

            }
        });

        menuBar = new JMenuBar();
        menuBar.add(mMain);
        menuBar.add(mExit);
        this.setJMenuBar(menuBar);
        //luego el statusbar
        status = new StatusBar();

        modeloGrid = new SingleTableModel();
        modeloGrid.setEditable(false);
        modeloGrid.addColumn("Name:");
        modeloGrid.addColumn("Path");
        modeloGrid.addColumn("Size");
        modeloGrid.addColumn("Modified:");
        grid = new JTable(modeloGrid);
        grid.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SelectionListener listener = new SelectionListener();
        grid.getSelectionModel().addListSelectionListener(listener);
        DoubleClickMouseListener mouseListener = new DoubleClickMouseListener();
        grid.addMouseListener(mouseListener);
        //grid.getColumnModel().getSelectionModel().addListSelectionListener(listener);
        JScrollPane scroll = new JScrollPane(grid);

        tabbed = new JTabbedPane();
        JPanel tabPanel = new JPanel(new GridLayout(0, 1));
        ActionListener searhActionListener = new searhActionListener();
        labelInput = new JLabelInput("Texto: ", "");
        labelInput.addActionTextInput(searhActionListener);
        tabPanel.add(labelInput);

        //tabPanel.add(comboRepo);
        buttonSearch = new JButton("Buscar dentro del indice existente.");
        buttonSearch.addActionListener(searhActionListener);
        tabPanel.add(buttonSearch);

        tabbed.add("Buscar:", tabPanel);
        //Y ahora el layout Final.
        this.setLayout(new BorderLayout());
        this.add(tabbed, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        status.setText(FileUtil.getWorkDirectory());
        this.add(status, BorderLayout.SOUTH);
        validateMenu();
    }

    class SelectionListener implements ListSelectionListener {

        // It is necessary to keep the table since it is not possible
        // to determine the table from the event's source
        SelectionListener() {
            super();
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (grid.getSelectedRow() >= 0) {
                int fila = grid.getSelectedRow();
                System.out.println("ROW:" + fila);

            }
        }
    }

    class DoubleClickMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
            JTable table = (JTable) me.getSource();
            Point p = me.getPoint();
            int row = table.rowAtPoint(p);
            if (me.getClickCount() == 2) {
                int fila = grid.getSelectedRow();
                System.out.println("Seleccionada " + grid.getSelectedRow());
                if (grid.getModel().getValueAt(fila, 1) != null) {
                    String openFile = grid.getModel().getValueAt(fila, 1).toString();
                    System.out.println("Abrir:" + openFile);
                    if (new File(openFile).exists()) {
                        if (Desktop.isDesktopSupported()) {
                            try {
                                File myFile = new File(openFile);
                                Desktop.getDesktop().open(myFile);
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(SiSeOnFrame.this,
                                        "No fue posible abrir el archivo.",
                                        "Archivo no encontrado o dañado.",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            System.out.println("DESKTOP NO SOPORTADO");
                        }//if
                    } else {
                        JOptionPane.showMessageDialog(SiSeOnFrame.this, "El archivo ya no se encuentra en la ruta donde fue indexado.");
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    /**
     *
     */
    public SiSeOnFrame(String title) {
        super(title);
        initComponents();
    }

    private void cleanGrid() {
        int p = grid.getRowCount();
        for (int k = 0; k < p; k++) {
            modeloGrid.removeRow(0);
        }
    }

    class searhActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            cleanGrid();

            if (!DemiurgoFacade.getInstance().getService().existRepo()) {
                JOptionPane.showMessageDialog(SiSeOnFrame.this,
                        "Aun no existe un INDEX sobre el cual buscar. Es preciso primero crear un INDEX.",
                        "Debes crear un INDEX.",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (labelInput.getTextInput().trim().length() == 0) {
                JOptionPane.showMessageDialog(SiSeOnFrame.this, "Debe especificar una <palabra> de búsqueda.");
                return;
            }
            SearchThread st = new SearchThread();
            (new Thread(st)).start();
        }
    }

    class SearchThread implements Runnable {

        @Override
        public void run() {
            int j = 0;
            buttonSearch.setEnabled(false);
            Cursor c1 = buttonSearch.getCursor();
            buttonSearch.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            String searchText = labelInput.getTextInput().trim();
            Object[] r = null;

            r = DemiurgoFacade.getInstance().getService().findByContent(labelInput.getTextInput().trim(),
                    100).toArray();

            if (r == null || r.length == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron resultados para su busqueda.");
            } else {
                j = r.length;
                for (int k = 0; k < r.length; k++) {
                    modeloGrid.addRow(((IArray) r[k]).toArray());
                }
            }
            buttonSearch.setEnabled(true);
            buttonSearch.setCursor(c1);
            status.setText("Total de resultados:" + j);
            // Fin de thread
            // se restaura boton.
        }
    }

}//class
