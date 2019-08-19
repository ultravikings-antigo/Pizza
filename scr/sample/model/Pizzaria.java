package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Pizzaria {

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

    public void cadastraPizza(String sabor, Double valor) {

        Pizza p = new Pizza(sabor, valor);


        try{
            Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");

            PreparedStatement stm = con.prepareStatement("INSERT INTO pizza(sabor, valor) VALUES (?,?)");

            stm.setString(1,p.getSabor());
            stm.setDouble(2,p.getValor());

            stm.executeUpdate();

            stm.close();
            con.close();

            sabores.add(p);
        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    public void abrirPedido() throws Exception{
        if(pedido == null){
            pedido = new Pedido();
        }else{
            throw new Exception("Pedido já aberto!!");

        }


    }

    public void incluirPizza(Pizza p) throws Exception{
        if(pedido != null){
            pedido.incluir(p);
        }else{
            throw new Exception("Pedido fechado!!");

        }


    }

    public Pedido verPedido(){

        return pedido;
    }

    public Double fecharPedido() throws Exception{
        Double valor=0.0;

        if(pedido != null){
            valor = pedido.getValorTotal();
            pedido = null;
            return valor;
        }else{
            throw new Exception("Pedido Fechado");

        }


    }

    public ObservableList listaSabores(){

        sabores.clear();
        try {

            Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");

            PreparedStatement stm = con.prepareStatement("SELECT * FROM pizza");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String sabor = rs.getString("sabor");
                double valor = rs.getDouble("valor");

                Pizza p = new Pizza(id, sabor, valor);

                sabores.add(p);
            }

            rs.close();
            stm.close();
            con.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return sabores;
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

    public void cadastraCliente(String nome, String telefone, String ano) {

        Cliente c = new Cliente(nome,telefone,ano);

        try{
            Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");

            PreparedStatement stm = con.prepareStatement("INSERT INTO cliente(nome,telefone,ano) VALUES (?,?,?)");

            stm.setString(1,c.getNome());
            stm.setString(2,c.getTelefone());
            stm.setString(3,c.getAno_nascimento());

            stm.executeUpdate();

            stm.close();
            con.close();

            clientes.add(c);
        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    public ObservableList listaCliente(){

        clientes.clear();
        try {

            Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");

            PreparedStatement stm = con.prepareStatement("SELECT * FROM cliente");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String ano = rs.getString("ano");

                Cliente c = new Cliente(nome,telefone,ano);

                clientes.add(c);
            }

            rs.close();
            stm.close();
            con.close();

        }catch(SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }
    public ObservableList pesquisaSabor(String sabor){
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

    public ObservableList pesquisaNome(String nome){
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
