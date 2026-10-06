package com.finTrack;

import com.finTrack.dao.TransacaoDAO;
import com.finTrack.model.Transacao;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Bruno
 */
public class FinTrackController {

    @FXML
    private DatePicker campoData;

    @FXML
    private TextField campoDescricao;

    @FXML
    private TextField campoValor;

    @FXML
    private ComboBox<String> campoTipo;

    @FXML
    private Label labelReceitas;

    @FXML
    private Label labelDespesas;

    @FXML
    private Label labelSaldo;

    @FXML
    private TableView<Transacao> tabelaTransacoes;

    @FXML
    private TableColumn<Transacao, LocalDate> colunaData;

    @FXML
    private TableColumn<Transacao, String> colunaDescricao;

    @FXML
    private TableColumn<Transacao, Double> colunaValor;

    @FXML
    private TableColumn<Transacao, String> colunaTipo;

    private final TransacaoDAO transacaoDAO
            = new TransacaoDAO();

    private Transacao transacaoSelecionada;

    @FXML
    public void initialize() {

        campoTipo.getItems().addAll(
                "Receita",
                "Despesa"
        );

        colunaData.setCellValueFactory(
                new PropertyValueFactory<>("data")
        );

        colunaDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao")
        );

        colunaValor.setCellValueFactory(
                new PropertyValueFactory<>("valor")
        );

        colunaValor.setCellFactory(
                coluna -> new javafx.scene.control.TableCell<Transacao, Double>() {
            @Override
            protected void updateItem(
                    Double valor,
                    boolean vazio) {

                super.updateItem(valor, vazio);

                if (vazio || valor == null) {
                    setText(null);
                } else {

                    Transacao transacao
                            = getTableView()
                                    .getItems()
                                    .get(getIndex());

                    String sinal
                            = transacao.getTipo().equals("Receita")
                            ? "+ "
                            : "- ";

                    setText(
                            sinal
                            + String.format(
                                    "R$ %.2f",
                                    valor
                            )
                    );
                }
            }
        }
        );

        colunaTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo")
        );

        tabelaTransacoes
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, antiga, nova) -> selecionarTransacao(nova)
                );

        transacaoDAO.criarTabela();

        carregarTabela();
    }

    @FXML
    private void adicionar() {

        if (campoData.getValue() == null
                || campoDescricao.getText().isBlank()
                || campoValor.getText().isBlank()
                || campoTipo.getValue() == null) {

            return;
        }

        double valor;

        try {

            valor = Double.parseDouble(
                    campoValor
                            .getText()
                            .replace(",", ".")
            );

        } catch (NumberFormatException e) {

            return;
        }

        Transacao transacao
                = new Transacao(
                        campoData.getValue(),
                        campoDescricao.getText(),
                        valor,
                        campoTipo.getValue()
                );

        transacaoDAO.inserir(transacao);

        limparCampos();

        carregarTabela();
    }

    @FXML
    private void atualizar() {

        if (transacaoSelecionada == null) {
            return;
        }

        if (campoData.getValue() == null
                || campoDescricao.getText().isBlank()
                || campoValor.getText().isBlank()
                || campoTipo.getValue() == null) {

            return;
        }

        double valor;

        try {

            valor = Double.parseDouble(
                    campoValor
                            .getText()
                            .replace(",", ".")
            );

        } catch (NumberFormatException e) {

            return;
        }

        transacaoSelecionada.atualizar(
                campoData.getValue(),
                campoDescricao.getText(),
                valor,
                campoTipo.getValue()
        );

        transacaoDAO.atualizar(
                transacaoSelecionada
        );

        limparCampos();

        transacaoSelecionada = null;

        carregarTabela();
    }

    @FXML
    private void remover() {

        if (transacaoSelecionada == null) {
            return;
        }

        transacaoDAO.remover(
                transacaoSelecionada.getId()
        );

        transacaoSelecionada = null;

        limparCampos();

        carregarTabela();
    }

    private void selecionarTransacao(
            Transacao transacao) {

        if (transacao == null) {
            return;
        }

        transacaoSelecionada = transacao;

        campoData.setValue(
                transacao.getData()
        );

        campoDescricao.setText(
                transacao.getDescricao()
        );

        campoValor.setText(
                String.valueOf(
                        transacao.getValor()
                )
        );

        campoTipo.setValue(
                transacao.getTipo()
        );
    }

    private void carregarTabela() {

        tabelaTransacoes.setItems(
                FXCollections.observableArrayList(
                        transacaoDAO.listar()
                )
        );

        atualizarResumo();
    }

    private void limparCampos() {

        campoData.setValue(null);

        campoDescricao.clear();

        campoValor.clear();

        campoTipo.setValue(null);
    }

    private void atualizarResumo() {

        double receitas = 0;
        double despesas = 0;

        for (Transacao transacao : transacaoDAO.listar()) {

            if (transacao.getTipo().equals("Receita")) {
                receitas += transacao.getValor();
            }

            if (transacao.getTipo().equals("Despesa")) {
                despesas += transacao.getValor();
            }
        }

        double saldo = receitas - despesas;

        labelReceitas.setText(
                String.format("Receitas: R$ %.2f", receitas)
        );

        labelDespesas.setText(
                String.format("Despesas: R$ %.2f", despesas)
        );

        labelSaldo.setText(
                String.format("Saldo: R$ %.2f", saldo)
        );
    }

}
