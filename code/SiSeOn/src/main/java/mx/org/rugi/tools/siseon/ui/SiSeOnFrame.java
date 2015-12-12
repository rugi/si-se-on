/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mx.org.rugi.tools.siseon.core.IndexItem;

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
    public SiSeOnFrame() {
        this("");

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

        mMain = new JMenu("Config");
        mMain.setMnemonic(KeyEvent.VK_R);

        mMain.add(mCreateIndex);
        mCreateIndex.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                JDialog jdialog = new JDialog();
                jdialog.setSize(400, 400);
                jdialog.setContentPane(new JINDEXCreator());
                jdialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                jdialog.setModal(true);
                jdialog.setVisible(true);
                //UpdateSearchOptionAndDisabledCreateIndexEnabledDeleteIndex;
            }
        });

        mMain.add(mDeleteIndex);

        mDeleteIndex.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Borrar indice");
                //UpdateSearchOptionAndDisabledDeletedIndexEnabledCreateIndex;
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
        //---          
        IndexItem item1 = new IndexItem();
        item1.setName("4de97.pdf");
        item1.setPath("/home/rugi/Downloads/4de97.pdf");
        item1.setLenght("56214");
        item1.setModified("Aug 19 15:14");
        modeloGrid.addRow(item1.toArray());
        //--
        //Y ahora el layout Final.
        this.setLayout(new BorderLayout());
        //this.add(tabbed, BorderLayout.NORTH);
        this.add(scroll, BorderLayout.CENTER);
        this.add(status, BorderLayout.SOUTH);
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
                System.out.println("PATH:" + grid.getModel().getValueAt(fila, 1).toString());

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
                String openFile =grid.getModel().getValueAt(fila, 1).toString();
                System.out.println("Abrir:" + openFile);
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File(openFile);
                        Desktop.getDesktop().open(myFile);
                    } catch (IOException ex) {
                            JOptionPane.showMessageDialog(SiSeOnFrame.this, "No fue posible abrir el archivo.");
                    }
                }else{
                    System.out.println("DESKTOP NO SOPORTADO");
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
}
