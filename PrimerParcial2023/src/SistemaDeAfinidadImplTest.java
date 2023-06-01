
import Entities.Cliente;
import Entities.Producto;
import exceptions.EntidadNoExiste;
import exceptions.EntidadYaExiste;
import org.junit.jupiter.api.Test;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import uy.edu.um.adt.queue.EmptyQueueException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaDeAfinidadImplTest {

    @Test
    public void afiliarCliente() {

        SistemaDeAfinidadImpl sistema_prueba  = new SistemaDeAfinidadImpl();

        Cliente cliente_prueba = new Cliente("51434026","Tomas");
        Cliente cliente_prueba_2 = new Cliente("12345","Juan");
        try {
            sistema_prueba.afiliarCliente("Tomas", "51434026");
            sistema_prueba.afiliarCliente("Juan", "12345");

            assertTrue(sistema_prueba.getClientesPendienteAfiliar().size()!=0);
            assertEquals(cliente_prueba, sistema_prueba.getClientesPendienteAfiliar().dequeue());
            assertEquals(cliente_prueba_2.getNombre(), sistema_prueba.getClientesPendienteAfiliar().dequeue().getNombre());


        } catch (EntidadYaExiste e) {
            System.out.println("Cliente ya existe");
            fail();

        }catch (EmptyQueueException e) {
            System.out.println("Problema con la Queue");
            fail();
        }


    }

    @Test
    public void procesarProximaAfiliacion() {
        SistemaDeAfinidadImpl sistema_prueba  = new SistemaDeAfinidadImpl();

        Cliente cliente_prueba = new Cliente("51434026","Tomas");
        try {
            sistema_prueba.afiliarCliente("Tomas", "51434026");
            sistema_prueba.procesarProximaAfiliacion();

            assertEquals(cliente_prueba,sistema_prueba.getClientesAfiliados().get(0));
            assertTrue(sistema_prueba.getClientesAfiliados().contains(cliente_prueba));
            assertEquals(sistema_prueba.getNumeroAfiliados(),1);

        } catch (EntidadYaExiste e) {
            System.out.println("Cliente ya existe");
            fail();
        } catch (EntidadNoExiste e) {
            fail();
        }

    }

    @Test
    public void registrarCompra() {

        SistemaDeAfinidadImpl sistema_prueba  = new SistemaDeAfinidadImpl();

        Cliente cliente_prueba = new Cliente("51434026","Tomas");
        try {
            sistema_prueba.afiliarCliente("Tomas", "51434026");
            sistema_prueba.procesarProximaAfiliacion();

            MyList<Producto> productos_prueba = new MyLinkedListImpl<Producto>();
            Producto leche = new Producto("Leche",20);
            productos_prueba.add(leche);
            Producto agua = new Producto("Agua", 50);
            productos_prueba.add(agua);
            Producto pan = new Producto("Pan", 25);
            productos_prueba.add(pan);

            sistema_prueba.registrarCompra(1,1,productos_prueba);

            assertEquals(pan, sistema_prueba.getClientesAfiliados().get(0).getCompras().get(0).getProductos().get(2));
            assertEquals(leche, sistema_prueba.getClientesAfiliados().get(0).getCompras().get(0).getProductos().get(0));
            assertEquals(agua, sistema_prueba.getClientesAfiliados().get(0).getCompras().get(0).getProductos().get(1));

            assertTrue(sistema_prueba.getClientesAfiliados().get(0).getCompras().size()!=0);

        } catch (EntidadYaExiste e) {
            System.out.println("Cliente ya existe");
            fail();
        } catch (EntidadNoExiste e) {
            fail();
        }

    }



    @Test
    public void buscarProducto() {

        SistemaDeAfinidadImpl sistema_prueba  = new SistemaDeAfinidadImpl();
        SistemaDeAfinidadImpl sistema_prueba_2  = new SistemaDeAfinidadImpl();

        Cliente cliente_prueba = new Cliente("51434026","Tomas");
        try {

            //Test Caso simple
            sistema_prueba.afiliarCliente("Tomas", "51434026");
            sistema_prueba.procesarProximaAfiliacion();

            MyList<Producto> productos_prueba = new MyLinkedListImpl<Producto>();
            Producto leche = new Producto("Leche",20);
            productos_prueba.add(leche);
            Producto agua = new Producto("Agua", 50);
            productos_prueba.add(agua);
            Producto pan = new Producto("Pan", 25);
            productos_prueba.add(pan);

            sistema_prueba.registrarCompra(1,1,productos_prueba);

            assertEquals("Agua",sistema_prueba.buscarProducto().getNombre());


            ///Test Caso Productos Repetidos
            sistema_prueba_2.afiliarCliente("Tomas", "51434026");
            sistema_prueba_2.procesarProximaAfiliacion();

            MyList<Producto> productos_prueba_2 = new MyLinkedListImpl<Producto>();
            Producto tarta = new Producto("Tarta",20);
            productos_prueba_2.add(tarta);
            Producto hamburgesa = new Producto("Hamburgesa", 50);
            productos_prueba_2.add(hamburgesa);
            Producto arroz = new Producto("Arroz", 25);
            productos_prueba_2.add(arroz);
            productos_prueba_2.add(arroz);
            productos_prueba_2.add(arroz);
            productos_prueba_2.add(arroz);
            productos_prueba_2.add(arroz);

            sistema_prueba_2.registrarCompra(1,1,productos_prueba_2);

            assertEquals("Arroz",sistema_prueba_2.buscarProducto().getNombre());

        } catch (EntidadYaExiste e) {
            System.out.println("Cliente ya existe");
            fail();
        } catch (EntidadNoExiste e) {
            fail();
        }

    }

    }
