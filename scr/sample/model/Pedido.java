package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedido {
    private Cliente cliente;
    private ObservableList<Pizza> pizzas;
    private Double valorTotal;

    public Pedido(){
        pizzas = FXCollections.observableArrayList();
    }

    public void incluir(Pizza p){
        pizzas.add(p);
    }

    public void incluirCliente(Cliente c){
        this.cliente = c;
    }

    public Double getValorTotal(){
        valorTotal=0.0;

        for (Pizza p:pizzas){
            valorTotal += p.getValor();
        }

        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ObservableList<Pizza> listaPizzas() {
        return pizzas;
    }

    @Override
    public String toString() {
        return "Valor Total:"+ valorTotal;
    }
}
