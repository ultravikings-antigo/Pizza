package sample.model;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {

    Cliente insere(String nome,String telefone ,String ano) throws SQLException;
    Cliente atualiza(Cliente c) throws SQLException;
    boolean remove() throws SQLException;


    Cliente buscaId(int id) throws SQLException;
    List<Cliente> buscaNome(String nome) throws SQLException;
    List<Cliente> lista() throws SQLException;

}
