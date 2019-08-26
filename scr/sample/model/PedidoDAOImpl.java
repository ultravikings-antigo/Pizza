package sample.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    private static String INSERT = "INSERT INTO pedido(idCliente,valorTotal,data) VALUES(?,?,?)";
    private static String ULTIMO_ID = "SELECT seq from sqlite_sequence where name='pedido'";
    private static String INSERE_PEDIDO_PIZZA = "INSERT INTO pedido_pizza(idPedido,idPizza,valor) VALUES(?,?,?)";
    private static String LISTA = "SELECT * FROM pedido";
    private static String LISTA_PEDIDOPIZZA = "SELECT * FROM pedido_pizza WHERE idPedido=?";


    @Override
    public Pedido insere(Pedido p) throws SQLException {

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(INSERT);

        stm.setInt(1,p.getCliente().getId());
        stm.setDouble(2,p.getValorTotal());
        stm.setDate(3, new Date(System.currentTimeMillis()));
        stm.executeUpdate();

        stm.close();

        //buscar ultimo id

        stm = con.prepareStatement(ULTIMO_ID);

        ResultSet rs = stm.executeQuery();
        rs.next();
        int id = rs.getInt("seq");
        p.setId(id);

        rs.close();
        stm.close();


        //inserir todas as pizzas do pedido na tabela pedido pizza

        stm = con.prepareStatement(INSERE_PEDIDO_PIZZA);

        for (Pizza pizza:p.listaPizzas()){

            stm.setInt(1,p.getId());
            stm.setInt(2,pizza.getId());
            stm.setDouble(3,pizza.getValor());

            stm.executeUpdate();
        }

        stm.close();
        con.close();

        return p;
    }

    @Override
    public Pedido buscaId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Pedido> lista() throws SQLException {

        ArrayList<Pedido> pedidos = new ArrayList<>();

        Connection con = FabricaConexao.getConnection();

        PreparedStatement stm = con.prepareStatement(LISTA);

        ResultSet rs = stm.executeQuery();

        ClienteDAO clienteDAO = new ClienteDAOImpl();
        PizzaDAO pizzaDAO = new PizzaDAOImpl();

        PreparedStatement stm2 = con.prepareStatement(LISTA_PEDIDOPIZZA);

        while (rs.next()){
            int id = rs.getInt("id");
            int idCliente = rs.getInt("idCliente");
            Date data = rs.getDate("data");
            double valorTotal = rs.getDouble("valorTotal");

            Cliente c = clienteDAO.buscaId(idCliente);

            Pedido pedido = new Pedido();

            pedido.setId(id);
            pedido.setCliente(c);
            pedido.setValorTotal(valorTotal);
            pedido.setData(LocalDateTime.from(data.toLocalDate()));

            //buscando as pizza do pedido
            stm2.setInt(1,pedido.getId());
            ResultSet rs2 = stm2.executeQuery();

            while (rs2.next()){
                int idPizza = rs2.getInt("idPizza");
                double valor = rs2.getDouble("valor");

                Pizza p = pizzaDAO.buscaId(idPizza);
                p.setValor(valor);

                pedido.incluirPizza(p);
            }

            rs2.close();

            pedidos.add(pedido);

        }

        rs.close();
        stm2.close();
        stm.close();
        con.close();

        return pedidos;
    }

    @Override
    public Pedido atualiza(Pedido p) throws SQLException {
        return null;
    }

    @Override
    public boolean remove(Pedido p) throws SQLException {
        return false;
    }
}
