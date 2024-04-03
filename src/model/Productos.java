package model;

/**
 *
 * @author Martin
 */
public class Productos implements Comparable<Productos>{
    private int codigo;
    private String descripcion;
    private Double precio;
    private int stock;
    private Categorias rubro;

    public Productos(int codigo, String descripcion,Double precio, int stock, Categorias rubro) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio=precio;
        this.stock = stock;
        this.rubro = rubro;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categorias getRubro() {
        return rubro;
    }

    public void setRubro(Categorias rubro) {
        this.rubro = rubro;
    }

    @Override
    public String toString() {
        return "Productos{" + "codigo=" + codigo + ", descripcion=" + descripcion + ", precio=" + precio + ", stock=" + stock + ", rubro=" + rubro + '}';
    }

   
    
 

    @Override
    public int compareTo(Productos o) {
   
        if(codigo==o.codigo){
            return 0;
        }
        else if(codigo>o.codigo){
            return 1;
        }
        else {
            return -1;
        }
    }
    
       public static String[] modeloClase(){
        return  new String[]{"Codigo","Descripcion","Precio","Stock"};
       }
    
    
    
    
}
