package com.finTrack.service;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class RepositorioGenerico <T> {
    private final List<T> itens;

    public RepositorioGenerico() {
        itens = new ArrayList<>();
    }

    public void adicionar(T item) {
        itens.add(item);
    }

    public void remover(T item) {
        itens.remove(item);
    }

    public List<T> listar() {
        return new ArrayList<>(itens);
    }

    public void adicionarTodos(List<? extends T> itens) {
        this.itens.addAll(itens);
    }

    public void adicionarDeOrigem(List<? extends T> origem) {
        for (T item : origem) {
            this.itens.add(item);
        }
    }

    public void copiarPara(List<? super T> destino) {
        destino.addAll(itens);
    }

    public int tamanho() {
        return itens.size();
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}
