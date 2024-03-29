package sample.model;

public class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private String ano_nascimento;

    public Cliente(int id, String nome, String telefone, String ano_nascimento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.ano_nascimento = ano_nascimento;
    }

    public Cliente(String nome, String telefone, String ano_nascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.ano_nascimento = ano_nascimento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getAno_nascimento() {
        return ano_nascimento;
    }

    public void setAno_nascimento(String ano_nascimento) {
        this.ano_nascimento = ano_nascimento;
    }

    @Override
    public String toString() {
        return  nome;
    }
}
