package sample.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PizzaDAOImpl implements PizzaDAO{


    @Override
    public Pizza insere(String sabor, double valor) throws SQLException {
        Pizza p = new Pizza(sabor,valor);
        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");


        PreparedStatement stm = con
                .prepareStatement("INSERT INTO pizza(sabor,valor) VALUES (?,?)");

        stm.setString(1,p.getSabor());
        stm.setDouble(2,p.getValor());

        stm.executeUpdate();

        stm.close();
        con.close();

        return p;
    }

    @Override
    public Pizza atualiza(Pizza p) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");


        PreparedStatement stm = con
                .prepareStatement("UPDATE pizza SET sabor=? ,valor=? WHERE id=?");

        stm.setString(1,p.getSabor());
        stm.setDouble(2,p.getValor());
        stm.setInt(3,p.getId());

        stm.executeUpdate();

        stm.close();
        con.close();

        return p;
    }

    @Override
    public boolean remove(Pizza p) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");


        PreparedStatement stm = con
                .prepareStatement("DELETE FROM pizza WHERE id=?");

        stm.setInt(1,p.getId());

        stm.executeUpdate();

        stm.close();
        con.close();

        return true;


    }

    @Override
    public Pizza buscaId(int id) throws SQLException {

        Pizza p=null;

        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");

        PreparedStatement stm = con.prepareStatement("SELECT * FROM pizza where id=?");

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String sabor = res.getString("sabor");
            Double valor = res.getDouble("valor");

            p = new Pizza(id,sabor,valor);
        }

        res.close();
        stm.close();
        con.close();

        return p;


    }

    @Override
    public List<Pizza> buscaSabor(String sabor) throws SQLException {

        ArrayList<Pizza> sabores = new ArrayList<>();

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

        return sabores;
    }

    @Override
    public List<Pizza> lista() throws SQLException {
        ArrayList<Pizza> sabores = new ArrayList<>();

        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");
        PreparedStatement stm = con.prepareStatement("SELECT * FROM pizza");

        ResultSet rs = stm.executeQuery();


        while(rs.next()){
            int id = rs.getInt("id");
            String sabor = rs.getString("sabor");
            double valor = rs.getDouble("valor");

            Pizza p = new Pizza(id,sabor,valor);

            sabores.add(p);
        }


        rs.close();
        stm.close();
        con.close();

        return sabores;
    }
}
