import Entities.Producto;
import uy.edu.um.adt.linkedlist.MyList;

public interface SistemaDeAfinidad {

    void afiliarCliente(String nombre, String cedula) throws Exceptions.EntidadYaExiste;

    long procesarProximaAfiliacion() throws Exceptions.EntidadNoExiste;

    void registrarCompra(long nroCliente, long nroCompra, MyList<Producto> productos) throws Exceptions.EntidadNoExiste;

    Producto buscarProducto() throws Exceptions.EntidadNoExiste;

}
