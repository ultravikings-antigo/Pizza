package sample.model;

import java.sql.SQLException;
import java.util.List;

public interface PizzaDAO {

    Pizza insere(String sabor, double valor) throws SQLException;
    Pizza atualiza(Pizza p) throws SQLException;
    boolean remove(Pizza p) throws SQLException;


    Pizza buscaId(int id) throws SQLException;
    List<Pizza> lista() throws SQLException;

}
