package controller;

import model.Productos;
import view.ViewConsultaNombre;



/**
 *
 * @author Martin
 */
public class CtrlConsultaNombre {
  
     public static ViewConsultaNombre cn;
     
      public static void CargarInstancia(ViewConsultaNombre cn2) {
          cn=cn2;
        }
 

    public static void jTxtBucarKeyReleased() {
        if (CtrlViewMenuPrincipal.listaProductos.size() > 0) {
            borrarFilas();
            for (Productos producto : CtrlViewMenuPrincipal.listaProductos) {

                if (producto.getDescripcion().startsWith(cn.getjTxtBucar().getText())) {

                    CtrlViewMenuPrincipal.modeloTable.addRow(new Object[]{
                        producto.getCodigo(),
                        producto.getDescripcion(),
                        producto.getPrecio(),
                        producto.getStock()
                    });
                }

            }
        }else{
            CtrlViewMenuPrincipal.viewDialogo("La lista esta vacia. Valla a la gestion de productos", 1);
        }
    }
    
    private static void borrarFilas(){
        
        int fila=cn.getjTblDatos().getRowCount()-1;
        
        for (int f=fila;f>=0;f--) {
            CtrlViewMenuPrincipal.modeloTable.removeRow(f);
        }
        
    }
    
    //fin
}
