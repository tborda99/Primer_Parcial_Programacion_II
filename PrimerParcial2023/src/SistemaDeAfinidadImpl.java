import Entities.Cliente;
import Entities.Compra;
import Entities.Producto;
import exceptions.EntidadNoExiste;
import exceptions.EntidadYaExiste;
import exceptions.InformacionInvalida;
import uy.edu.um.adt.linkedlist.MyLinkedListImpl;
import uy.edu.um.adt.linkedlist.MyList;
import uy.edu.um.adt.queue.EmptyQueueException;
import uy.edu.um.adt.queue.MyQueue;

import java.util.Queue;

public class SistemaDeAfinidadImpl implements  SistemaDeAfinidad {

    private MyQueue<Cliente> clientesPendienteAfiliar = new MyLinkedListImpl<Cliente>(); //FIFO
    private MyLinkedListImpl<Cliente> clientesAfiliados = new MyLinkedListImpl<Cliente>();
    private long numeroAfiliados;

    @Override
    public void afiliarCliente(String nombre, String cedula) throws EntidadYaExiste {
        //Creo un nuevo cliente a afiliar
        Cliente nuevo_cliente = new Cliente(cedula, nombre);

        //Chequear que no este en afiliados ni en pendiente de afiliar
        if (clientesAfiliados.contains(nuevo_cliente)) throw new EntidadYaExiste();

        for (int i = 0; i < clientesPendienteAfiliar.size(); i++) {
            if (clientesPendienteAfiliar.get(i).equals(nuevo_cliente)) {
                throw new EntidadYaExiste();
            }

        }

        clientesPendienteAfiliar.enqueue(nuevo_cliente);


    }

    @Override
    public long procesarProximaAfiliacion() throws EntidadNoExiste {

        Cliente clienteAAfiliar = new Cliente();

        //Intento sacarlo de la Queue
        try {
            clienteAAfiliar = clientesPendienteAfiliar.dequeue();
        } catch (EmptyQueueException e) {
            System.out.println("No hay clientes pendientes de Afiliar");
            throw new EntidadNoExiste();
        }


        //Agregar client
        numeroAfiliados += 1;
        clienteAAfiliar.setNumero(numeroAfiliados);
        clientesAfiliados.add(clienteAAfiliar);

        //Devolver numero
        return numeroAfiliados;
    }

    @Override
    public void registrarCompra(long nroCliente, long nroCompra, MyList<Producto> productos) throws EntidadNoExiste {
        //Check que el usuario exista
        boolean flag_encontrado = false;
        Cliente cliente_temp = new Cliente();
        for (int i = 0; i < clientesAfiliados.size(); i++) {
            cliente_temp = clientesAfiliados.get(i);
            if (cliente_temp.getNumero() == nroCliente) {
                flag_encontrado = true;
                Compra compritas = new Compra(nroCompra, productos);
                cliente_temp.getCompras().add(compritas);

            }

        }

        if (!flag_encontrado) throw new EntidadNoExiste();


    }

    @Override
    public Producto buscarProducto() throws EntidadNoExiste {

        MyList<Float> precios = new MyLinkedListImpl<Float>();
        MyList<String> productos = new MyLinkedListImpl<String>();


        for (int i = 0; i < clientesAfiliados.size(); i++) {
            for (int j = 0; j < clientesAfiliados.get(i).getCompras().size(); j++) {
                for (int k = 0; k < clientesAfiliados.get(i).getCompras().get(j).getProductos().size(); k++) {
                    productos.add(clientesAfiliados.get(i).getCompras().get(j).getProductos().get(k).getNombre());
                    precios.add(clientesAfiliados.get(i).getCompras().get(j).getProductos().get(k).getPrecio());
                }
            }

        }

        Producto producto_ganador = new Producto("Nulo",0);
        Producto producto_desafiante = new Producto();

        for (int i = 0; i < productos.size(); i++) {
            //Para cada producto voy a iterar en toda la lista sumando las repeticiones
            producto_desafiante.setNombre(productos.get(i));
            producto_desafiante.setPrecio(precios.get(i));
            for (int j = 0; j < productos.size(); j++) {
                if(productos.get(i) == productos.get(j) && j != i) {
                    float suma_ant = producto_desafiante.getPrecio();
                    float suma_nueva = precios.get(j);
                    producto_desafiante.setPrecio(suma_ant + suma_nueva);

                }
            }

            if (producto_desafiante.getPrecio() > producto_ganador.getPrecio()){
                producto_ganador.setNombre(producto_desafiante.getNombre());
                producto_ganador.setPrecio(producto_desafiante.getPrecio());
            }


        }

        if(producto_ganador.getNombre()=="nulo") throw new EntidadNoExiste();

        return producto_ganador;
    }



    public MyQueue<Cliente> getClientesPendienteAfiliar() {
        return clientesPendienteAfiliar;
    }

    public void setClientesPendienteAfiliar(MyQueue<Cliente> clientesPendienteAfiliar) {
        this.clientesPendienteAfiliar = clientesPendienteAfiliar;
    }

    public MyLinkedListImpl<Cliente> getClientesAfiliados() {
        return clientesAfiliados;
    }

    public void setClientesAfiliados(MyLinkedListImpl<Cliente> clientesAfiliados) {
        this.clientesAfiliados = clientesAfiliados;
    }

    public long getNumeroAfiliados() {
        return numeroAfiliados;
    }

    public void setNumeroAfiliados(long numeroAfiliados) {
        this.numeroAfiliados = numeroAfiliados;
    }
}
