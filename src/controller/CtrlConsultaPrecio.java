package controller;

import static controller.CtrlConsultaNombre.cn;
import java.awt.event.KeyEvent;
import model.Productos;
import view.ViewConsultaPrecio;

/**
 *
 * @author Martin
 */
public class CtrlConsultaPrecio {
    public static ViewConsultaPrecio cp;
     
      public static void CargarInstancia(ViewConsultaPrecio cp2) {
         cp=cp2;
     }
      
    public static void jTxtInicioKeyReleased() {
        try {

            if (CtrlViewMenuPrincipal.listaProductos.size() > 0) {

                borrarFilas();
                for (Productos producto : CtrlViewMenuPrincipal.listaProductos) {
                    if (!cp.getjTxtFin().getText().isEmpty() && !cp.getjTxtInicio().getText().isEmpty()) {

                        Double inicio = Double.valueOf(cp.getjTxtInicio().getText());
                        Double fin = Double.valueOf(cp.getjTxtFin().getText());
                        if (inicio >= producto.getPrecio() || fin <= producto.getPrecio()) {

                            addRows(producto);

                        }
                    } else {
                        if (!cp.getjTxtInicio().getText().isEmpty()) {
                            Double inicio = Double.valueOf(cp.getjTxtInicio().getText());
                            System.out.println("VAlor de inicio:"+inicio);
                            if (inicio >= producto.getPrecio()) {

                                addRows(producto);

                            }
                        }
                    }

                }
            } else {
                CtrlViewMenuPrincipal.viewDialogo("La lista esta vacia. Valla a la gestion de productos", 1);
            }

        } catch (Exception e) {
            CtrlViewMenuPrincipal.viewDialogo("Ocurrio un problema. " + e.getMessage(), 1);
        }
    }
      
      
      
       public static void jTxtFinKeyReleased(){
             if (CtrlViewMenuPrincipal.listaProductos.size() > 0) {
            borrarFilas();
            String inicio=cp.getjTxtInicio().getText();
            for (Productos producto : CtrlViewMenuPrincipal.listaProductos) {

//                if (producto.getPrecio().startsWith(cp.getjTxtFin().getText()) && !inicio.isEmpty()) {
//
//                    CtrlViewMenuPrincipal.modeloTable.addRow(new Object[]{
//                        producto.getCodigo(),
//                        producto.getDescripcion(),
//                        producto.getPrecio(),
//                        producto.getStock()
//                    });
//                }

            }
        }else{
            CtrlViewMenuPrincipal.viewDialogo("La lista esta vacia. Valla a la gestion de productos", 1);
        }
      }
      
      
      
      
      
      
    private static void borrarFilas() {

        int fila = cp.getjTblDatos().getRowCount() - 1;

        for (int f = fila; f >= 0; f--) {
            CtrlViewMenuPrincipal.modeloTable.removeRow(f);
        }

    }
    
    private static void addRows(Productos producto) {
        CtrlViewMenuPrincipal.modeloTable.addRow(new Object[]{
            producto.getCodigo(),
            producto.getDescripcion(),
            producto.getPrecio(),
            producto.getStock()
        });
    }
      
    //Eventos de teclado control de caracteres
    public static void jTxtInicioKeyTyped(java.awt.event.KeyEvent evt){
         char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '.') {
            evt.consume();
        } else if (c == '.' && cp.getjTxtFin().getText().isEmpty()) {
            evt.consume();
        } else if (c == '.' && cp.getjTxtFin().getText().contains(".")) {

            evt.consume();
        } else if (Character.isDigit(c) && cp.getjTxtFin().getText().contains(".")) {

            String text = cp.getjTxtFin().getText();
            int dotIndex = text.indexOf('.');
            int digitsAfterDot = text.length() - dotIndex - 1;
            if (digitsAfterDot >= 2) {
                evt.consume();
            }
        }
    }
    
    
    public static void jTxtFinKeyTyped(java.awt.event.KeyEvent evt){
         char c = evt.getKeyChar();
      
        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '.') {
            evt.consume();
        } else if (c == '.' && cp.getjTxtFin().getText().isEmpty()) {
            evt.consume();
        } else if (c == '.' && cp.getjTxtFin().getText().contains(".")) {

            evt.consume();
        } else if (Character.isDigit(c) && cp.getjTxtFin().getText().contains(".")) {

            String text = cp.getjTxtFin().getText();
            int dotIndex = text.indexOf('.');
            int digitsAfterDot = text.length() - dotIndex - 1;
            if (digitsAfterDot >= 2) {
                evt.consume();
            }
        }
    }
    
    
    
    //fin
}
