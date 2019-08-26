package sample.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public Cliente insere(String nome, String telefone, String ano) throws SQLException {
        Cliente c = new Cliente(nome,telefone,ano);
        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");


        PreparedStatement stm = con
                .prepareStatement("INSERT INTO cliente(nome,telefone,ano) VALUES (?,?,?)");


        stm.setString(1,c.getNome());
        stm.setString(2,c.getTelefone());
        stm.setString(3,c.getAno_nascimento());

        stm.executeUpdate();

        stm.close();
        con.close();

        return c;
    }

    @Override
    public Cliente atualiza(Cliente c) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");


        PreparedStatement stm = con
                .prepareStatement("UPDATE cliente SET nome=? ,telefone=?, ano=? WHERE id=?");

        stm.setString(1,c.getNome());
        stm.setString(2,c.getTelefone());
        stm.setString(3,c.getAno_nascimento());
        stm.setInt(3,c.getId());

        stm.executeUpdate();

        stm.close();
        con.close();

        return c;
    }

    @Override
    public boolean remove(Cliente c) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");


        PreparedStatement stm = con
                .prepareStatement("DELETE FROM cliente WHERE id=?");

        stm.setInt(1,c.getId());

        stm.executeUpdate();

        stm.close();
        con.close();

        return true;
    }

    @Override
    public Cliente buscaId(int id) throws SQLException {

        Cliente c=null;

        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");

        PreparedStatement stm = con.prepareStatement("SELECT * FROM cliente where id=?");

        stm.setInt(1,id);

        ResultSet res = stm.executeQuery();

        while(res.next()){
            String nome = res.getString("nome");
            String telefone = res.getString("telefone");
            String ano = res.getString("ano");


            c = new Cliente(id,nome,telefone,ano);
        }

        res.close();
        stm.close();
        con.close();

        return c;
    }

    @Override
    public List<Cliente> buscaNome(String nome) throws SQLException {

        ArrayList<Cliente> clientes = new ArrayList<>();

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

        return clientes;
    }

    @Override
    public List<Cliente> lista() throws SQLException {
        ArrayList<Cliente> clientes = new ArrayList<>();

        Connection con = DriverManager.getConnection("jdbc:sqlite:pizza.sqlite");
        PreparedStatement stm = con.prepareStatement("SELECT * FROM cliente");

        ResultSet rs = stm.executeQuery();

        while(rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            String ano = rs.getString("ano");

            Cliente c = new Cliente(id,nome,telefone,ano);

            clientes.add(c);
        }

        rs.close();
        stm.close();
        con.close();

        return clientes;
    }
}
