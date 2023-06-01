import Entities.Producto;
import uy.edu.um.adt.linkedlist.MyList;

public interface SistemaDeAfinidad {

    void afiliarCliente(String nombre, String cedula) throws exceptions.EntidadYaExiste;

    long procesarProximaAfiliacion() throws exceptions.EntidadNoExiste;

    void registrarCompra(long nroCliente, long nroCompra, MyList<Producto> productos) throws exceptions.EntidadNoExiste;

    Producto buscarProducto() throws exceptions.EntidadNoExiste;

}
