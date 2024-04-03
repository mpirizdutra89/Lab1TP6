package controller;

import java.util.TreeSet;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.Categorias;
import model.EncabesadoProducto;
import model.Productos;
import view.ViewMenuPrincipal;

/**
 *
 * @author Martin
 */
public class CtrlViewMenuPrincipal {

    public static ViewMenuPrincipal vmp = new ViewMenuPrincipal();
    public static final EscritorioPersonalizado escritorio = new EscritorioPersonalizado();
    public static TreeSet<Productos> listaProductos;
    //Tablas
    public static DefaultTableModel modeloTable;
    private static DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
    private static DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    

    public static void mostrarView() {
        listaProductos = new TreeSet<>();
        escritorio.setSize(890, 590);
        vmp.setContentPane(escritorio);

        vmp.setLocationRelativeTo(null);
        vmp.setVisible(true);
    }

    public static void jMenuItemProductoActionPerformed() {
        view.ViewGestionProducto vgp = new view.ViewGestionProducto();
        //seteo el comboBox
        vgp.getjCBxRubro().addItem("Seleccione Rubro");
        for (Categorias rubro : Categorias.values()) {
            vgp.getjCBxRubro().addItem(rubro.toString());

        }
        //cargo la vista
        cargarViewInternas(vgp);

    }

    public static void jMenuItemNombreActionPerformed() {
        view.ViewConsultaNombre cn = new view.ViewConsultaNombre();
        
        armarEncabesados();
        cn.getjTblDatos().setModel(modeloTable);
        alinearCabeceras(2, "right", cn.getjTblDatos());
        alinearCabeceras(3, "center", cn.getjTblDatos());
        //cargo la vista
        cargarViewInternas(cn);

    }

    public static void jMenuItemPrecioActionPerformed() {
        view.ViewConsultaPrecio cp = new view.ViewConsultaPrecio();
        
        armarEncabesados();
        cp.getjTblDatos().setModel(modeloTable);
        alinearCabeceras(2, "right", cp.getjTblDatos());
        alinearCabeceras(3, "center", cp.getjTblDatos());
        //cargo la vista
        cargarViewInternas(cp);

    }

    public static void jMenuItemRubroActionPerformed() {

        view.ViewConsultaRubro cr = new view.ViewConsultaRubro();
        
        cr.getjCBxRubro().addItem("Seleccione Rubro");
        for (Categorias rubro : Categorias.values()) {
            cr.getjCBxRubro().addItem(rubro.toString());

        }
        //tabla
         
        armarEncabesados();
        cr.getjTblDatos().setModel(modeloTable);
        alinearCabeceras(2, "right", cr.getjTblDatos());
        alinearCabeceras(3, "center", cr.getjTblDatos());
        //cargo la vista
        cargarViewInternas(cr);

    }

    private static void cargarViewInternas(JInternalFrame view) {
        escritorio.removeAll();
        escritorio.repaint();
        view.setVisible(true);
        escritorio.add(view);
        escritorio.moveToFront(view);

    }

    public static void viewDialogo(String msj, String titulo, int tipo) {
        JOptionPane.showMessageDialog(escritorio, msj, titulo, tipo);
    }

    public static void viewDialogo(String msj, int tipo) {
        JOptionPane.showMessageDialog(escritorio, msj, "", tipo);
    }
    
    //TAblas
    
    //Tablas alinear
          private static void alinearCabeceras(int indiceColumna, String dir, JTable tablet) {
        //  columnAlign.setHorizontalAlignment(SwingConstants.RIGHT);
        // jTblDatos.getColumnModel().getColumn(1).setCellRenderer(columnAlign);
        switch (dir) {
            case "center":
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                tablet.getColumnModel().getColumn(indiceColumna).setCellRenderer(centerRenderer);
                break;
            case "right":
                rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
                tablet.getColumnModel().getColumn(indiceColumna).setCellRenderer(rightRenderer);
                break;

            default:
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                tablet.getColumnModel().getColumn(indiceColumna).setCellRenderer(centerRenderer);
                break;
        }

    }
    
          
     private static void armarEncabesados(){
         
          modeloTable= new DefaultTableModel(){
              public boolean isCellEditable(int f,int c){
                  return false;
              }
          };
         
          for (EncabesadoProducto cellProducto : EncabesadoProducto.values()) {
           
                 modeloTable.addColumn(cellProducto);
        }
     }  
     
     private static void resetColumn(JTable tablet){
          for (int i = tablet.getColumnCount() - 1; i >= 0; i--) {
                tablet.removeColumn(tablet.getColumnModel().getColumn(i));
            }
     }
          

}
