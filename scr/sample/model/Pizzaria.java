package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Pizzaria {

    PizzaDAO pizzaDAO = new PizzaDAOImpl();
    ClienteDAO clienteDAO = new ClienteDAOImpl();

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
        if(pedido != null){
            pedido.incluir(p);
        }else{
            throw new Exception("Pedido fechado!!");
        }
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
        if(pedido == null){
            pedido = new Pedido();
        }else{
            throw new Exception("Pedido já aberto!!");

        }
    }

    public void fecharPedido() throws Exception{
        if(pedido != null){
            pedido.getValorTotal();
            pedido = null;
        }else{
            throw new Exception("Pedido Fechado");
        }
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

    public ObservableList<Pizza> getListaPedido(){
        if(pedido != null){
            return pedido.listaPizzas();
        }

        return FXCollections.emptyObservableList();
    }





    public ObservableList pesquisarSabor(String sabor){
        sabores.clear();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");
            PreparedStatement stm = con.prepareStatement("SELECT * FROM pizza where sabor like ?");
            stm.setString(1,sabor);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                int idP = rs.getInt("id");
                String saborP = rs.getString("sabor");
                Double valorP = rs.getDouble("valor");
                Pizza p = new Pizza(idP, saborP, valorP);
                sabores.add(p);
            }
            rs.close();
            stm.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sabores;
    }

    public ObservableList pesquisarNome(String nome){
        clientes.clear();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");
            PreparedStatement stm = con.prepareStatement("SELECT * FROM cliente where nome like ?");
            stm.setString(1,nome);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String nomeC = rs.getString("nome");
                String telefoneC = rs.getString("telefone");
                String anoC = rs.getString("ano");
                Cliente c = new Cliente(nomeC,telefoneC,anoC);
                clientes.add(c);
            }
            rs.close();
            stm.close();
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }
}
