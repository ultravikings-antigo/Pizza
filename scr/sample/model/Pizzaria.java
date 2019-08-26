package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Pizzaria {

    PizzaDAO pizzaDAO = new PizzaDAOImpl();
    ClienteDAO clienteDAO = new ClienteDAOImpl();
    PedidoDAO pedidoDAO = new PedidoDAOImpl();

    private ObservableList<Pizza> sabores;
    private Pedido pedido;
    private ObservableList<Cliente> clientes;

    private static Pizzaria instance = new Pizzaria();

    private Pizzaria(){
        sabores = FXCollections.observableArrayList();
        clientes = FXCollections.observableArrayList();
    }

    public static Pizzaria getInstance(){
        return instance;
    }


    public void cadastrarPizza(String sabor, Double valor) throws SQLException {
        pizzaDAO.insere(sabor,valor);
    }

    public void incluirPizza(Pizza p) throws Exception{

        pedido.incluirPizza(p);
    }

    public void incluirCliente(Cliente c){
        pedido.incluirCliente(c);
    }

    public ObservableList listarPizzas() throws SQLException {
        sabores.clear();
        sabores.addAll(pizzaDAO.lista());

        return sabores;
    }


    public void cadastrarCliente(String nome, String telefone, String ano) throws SQLException {
        clienteDAO.insere(nome,telefone,ano);
    }

    public ObservableList listarCliente() throws SQLException {

        clientes.clear();

        clientes.addAll(clienteDAO.lista());

        return clientes;
    }


    public void abrirPedido() throws Exception{

        pedido = new Pedido();

    }

    public void fecharPedido() throws Exception{
        pedidoDAO.insere(pedido);
        pedido.getValorTotal();
        pedido = null;



    }

    public Pedido verPedido(){
        return pedido;
    }

    public Double valorPedido(){
        if(pedido != null){
            return pedido.getValorTotal();
        }

        return 0.0;
    }

    public Cliente clientePedido()
    {
        return pedido.getCliente();
    }

    public ObservableList<Pizza> getListaPedido(){
        if(pedido != null){
            return pedido.listaPizzas();
        }

        return FXCollections.emptyObservableList();
    }

    public ObservableList pesquisarSabor(String sabor) throws SQLException {
        sabores.clear();

        sabores.addAll(pizzaDAO.buscaSabor(sabor));

        return sabores;
    }

    public ObservableList pesquisarNome(String nome) throws SQLException {
        clientes.clear();

        clientes.addAll(clienteDAO.buscaNome(nome));

        return clientes;
    }
}
