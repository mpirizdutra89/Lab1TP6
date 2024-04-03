package controller;

import java.awt.event.ActionEvent;
import model.Productos;
import view.ViewConsultaRubro;

/**
 *
 * @author Martin
 */
public class CtrlConsultaRubro {
    public static ViewConsultaRubro cr;
     
      public static void CargarInstancia(ViewConsultaRubro cr2) {
            cr=cr2;
        }
      
      
      public static void jCBxRubroActionPerformed() {
        // System.out.println(cr.getjCBxRubro().getSelectedItem().toString());
        if (CtrlViewMenuPrincipal.listaProductos.size() > 0) {
            int index = cr.getjCBxRubro().getSelectedIndex();
            
            if (index > 0) {
                borrarFilas();
                for (Productos producto : CtrlViewMenuPrincipal.listaProductos) {
                    model.Categorias rubro =  model.Categorias.valueOf(cr.getjCBxRubro().getSelectedItem().toString());

                    if (producto.getRubro() == rubro) {

                        CtrlViewMenuPrincipal.modeloTable.addRow(new Object[]{
                            producto.getCodigo(),
                            producto.getDescripcion(),
                            producto.getPrecio(),
                            producto.getStock()
                        });

                    }

                }
            }
        } else {
            CtrlViewMenuPrincipal.viewDialogo("No hay datos. Valla a la seccion de gestion de productos", 1);
        }
    }
        
       private static void borrarFilas(){
        
        int fila=cr.getjTblDatos().getRowCount()-1;
        
        for (int f=fila;f>=0;f--) {
            CtrlViewMenuPrincipal.modeloTable.removeRow(f);
        }
        
    }  
      
}
