package controller;

import java.awt.Component;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import model.Categorias;
import model.Productos;

import view.ViewGestionProducto;

/**
 * La idea de este controlador, es manejar la vista desde sus evento Eventos de
 * la vista no delegan a cada uno de nuestro metodo tenemos acceso a los
 * componentes de la vista gracias get y set publicos Lo mas importnte devemos
 * resibir la instancia, aca no podemos crear una nueva. La intancia la crea
 * MenuPRincipal cuando llama a nuestra vista. Por eso es que devemos resivir la
 * instancia de la vista, eso lo hace el contructor de la misma y llama a
 * nuestro metodo cargarInstancia
 *
 * @author Martin
 */
public class CtrlGestionProducto {

    public static ViewGestionProducto vgp;
    private static Productos productoBuscado = null;
    
    //LA vista nos pasa su instancia para poder trabajr con ella
    public static void CargarInstancia(ViewGestionProducto vgp2) {
        vgp = vgp2;
        resetearBotones(0);
         vgp.getjTxtPrecio().setText("0.00");
      
    }

    public static void botonNuevoProducto() {

        try {

            int codigo = Integer.parseInt(vgp.getjTxtCodigo().getText());
            String descripcion = vgp.getjTxtDescripcion().getText();
            Double precio = Double.valueOf(vgp.getjTxtPrecio().getText());
            int stock = Integer.parseInt(vgp.getjTxtStock().getText());
          
            Categorias rubro = Categorias.valueOf(vgp.getjCBxRubro().getSelectedItem().toString());

            if (!descripcion.isEmpty() && precio > 0 && stock >= 0 && vgp.getjCBxRubro().getSelectedIndex() > 0) {

                CtrlViewMenuPrincipal.listaProductos.add(new Productos(codigo, descripcion, precio, stock, rubro));

                resetearForm();
            }

        } catch (Exception e) {
            CtrlViewMenuPrincipal.viewDialogo("Todo los campos son obligatorios.", "Advertencia", 1);
        }
    }

    public static void btnBuscarXCodigo() {
        // lo yamo si exite si no no resetearBotones(1);
        try {
            int CodigoBuscado = Integer.parseInt(vgp.getjTxtCodigo().getText());
            productoBuscado = buscarXCodigo(CodigoBuscado);

            if (productoBuscado != null) {
               vgp.getjTxtCodigo().setText(String.valueOf(productoBuscado.getCodigo()));
                vgp.getjTxtDescripcion().setText(productoBuscado.getDescripcion());
                vgp.getjTxtPrecio().setText(String.valueOf(productoBuscado.getPrecio()));
                vgp.getjTxtStock().setText(String.valueOf(productoBuscado.getStock()));
                vgp.getjCBxRubro().setSelectedIndex(seleccionarCbx(String.valueOf(productoBuscado.getRubro())));
                resetearBotones(1);
            } else {
                CtrlViewMenuPrincipal.viewDialogo("EL codigo " + CodigoBuscado + " no se encuentra en la lista", "Informacion", 0);
            }
        } catch (Exception e) {
            CtrlViewMenuPrincipal.viewDialogo("Ingrese un codigo numerico", "Faltan datos", 1);
        }
    }
    
    public static void btnEditarXProducto() {
         if(productoBuscado!=null){
            if( EditarXProducto()){
                CtrlViewMenuPrincipal.viewDialogo("Editado correctamente", "Ok", 1);
                resetearForm();
                resetearBotones(1);
                
            }
        }
        else{
            CtrlViewMenuPrincipal.viewDialogo("No se puede editar", "Fatal erro", 1);
            resetearForm();
            resetearBotones(1);
        }
    }
    
    
    private static boolean EditarXProducto(){
         boolean res = false;
        for (Productos producto : CtrlViewMenuPrincipal.listaProductos) {
            if (producto.equals(productoBuscado)) {
                try {

                    int cod = Integer.parseInt(vgp.getjTxtCodigo().getText());
                    String descripcion = vgp.getjTxtDescripcion().getText();
                    Double precio = Double.valueOf(vgp.getjTxtPrecio().getText());

                    int stock = Integer.parseInt(vgp.getjTxtStock().getText());
                    Categorias rubro = Categorias.valueOf(vgp.getjCBxRubro().getSelectedItem().toString());;

                    if (!descripcion.isEmpty() && precio > 0 && stock >= 0 && vgp.getjCBxRubro().getSelectedIndex() > 0) {
                        producto.setCodigo(cod);
                        producto.setDescripcion(descripcion);
                        producto.setPrecio(precio);
                        producto.setStock(stock);
                        producto.setRubro(rubro);
                        res = true;
                    } else {
                        CtrlViewMenuPrincipal.viewDialogo("Todo los campos son obligatorio", "Edicion", 1);
                    }

                } catch (Exception e) {
                    CtrlViewMenuPrincipal.viewDialogo("Todo los campos son obligatorio", "Edicion", 1);
                }

            }
        }
        return res;
    }
    
    public static void btnBorrar(){
         if(productoBuscado!=null){
            try {
                CtrlViewMenuPrincipal.listaProductos.remove(productoBuscado);
                 CtrlViewMenuPrincipal.viewDialogo("El producto se borro con exito", "Exito", 1);
                 resetearBotones(0);
                 resetearForm();
            } catch (Exception e) {
                CtrlViewMenuPrincipal.viewDialogo("No se pudo borrar", "falla", 1);
            }
            
            
        }
    }
    
    public static void btnReset() {
        resetearForm();
        resetearBotones(0);
    }
    
    public static void btncerrar(){
         vgp.dispose();;
    }
    
    
    //eventos Teclado
    public static void jTxtPrecioKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE && c != '.') {
            evt.consume();
        } else if (c == '.' && vgp.getjTxtPrecio().getText().isEmpty()) {
            evt.consume();
        } else if (c == '.' && vgp.getjTxtPrecio().getText().contains(".")) {

            evt.consume();
        } else if (Character.isDigit(c) && vgp.getjTxtPrecio().getText().contains(".")) {

            String text = vgp.getjTxtPrecio().getText();
            int dotIndex = text.indexOf('.');
            int digitsAfterDot = text.length() - dotIndex - 1;
            if (digitsAfterDot >= 2) {
                evt.consume();
            }
        }
    }

    public static void jTxtCodigoKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != evt.VK_BACK_SPACE && c != evt.VK_DELETE) {
            evt.consume();
        }
    }

    public static void jTxtStockKeyTyped(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != evt.VK_BACK_SPACE && c != evt.VK_DELETE) {
            evt.consume();
        }
    }
    
    
    public static void jCBxRubroFocusGained(){
       
         vgp.getjTxtPrecio().setText("");
    }
    
    
    //funciones
    
    private static void resetearForm() {
        vgp.getjTxtPrecio().setName("JtxtPrecio");
        vgp.getjCBxRubro().setSelectedIndex(0);

        for (Component component : vgp.getjPnlTxtFiel().getComponents()) {
            if (component instanceof JTextField) {
                JTextField textField = (JTextField) component;
                textField.setText("");
                if ("JtxtPrecio".equals(textField.getName())) {

                    textField.setText("0.00");
                  
                }

            }
        }
    }
    
    
      private static int seleccionarCbx(String select){
        int indice=0;
         for (int i = 0; i < vgp.getjCBxRubro().getItemCount(); i++) {
            String elemento = (String) vgp.getjCBxRubro().getItemAt(i);
            if(elemento.equals(select)){
                indice=i;
            }
        }
         return indice;
    }
    
    
    private static void resetearBotones(int buscar) {
        if (buscar == 1) {
            
            vgp.getjBtnNuevo().setEnabled(false);
            vgp.getjBtnEditar().setEnabled(true);
            vgp.getjBtnBorrar().setEnabled(true);
        } else {
            vgp.getjBtnNuevo().setEnabled(true);
            vgp.getjBtnEditar().setEnabled(false);
            vgp.getjBtnBorrar().setEnabled(false);
        }
        

    }
    
     private static Productos buscarXCodigo(int codigo) {
        
        for (Productos producto :  CtrlViewMenuPrincipal.listaProductos) {
            if (producto.getCodigo() == codigo) {
                return producto;
            }
        }
        return null;
    }    
    
    //fin de la clase
}
