package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Pedido {
    private ObservableList<Pizza> pizzas;
    private Double valorTotal;

    public Pedido(){
        pizzas = FXCollections.observableArrayList();
    }

    public void incluir(Pizza p){
        pizzas.add(p);
    }

    public Double getValorTotal(){
        valorTotal=0.0;

        for (Pizza p:pizzas){
            valorTotal += p.getValor();
        }

        return valorTotal;
    }

    public ObservableList<Pizza> listaPizzas() {
        return pizzas;
    }

    @Override
    public String toString() {
        return "Valor Total:"+ valorTotal;
    }
}
