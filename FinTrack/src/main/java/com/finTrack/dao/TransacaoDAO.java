package com.finTrack.dao;

import com.finTrack.database.Conexao;
import com.finTrack.model.Transacao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruno
 */
public class TransacaoDAO {
     public void criarTabela() {

        String sql =
                "CREATE TABLE IF NOT EXISTS transacoes ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "data DATE NOT NULL, "
                + "descricao VARCHAR(255) NOT NULL, "
                + "valor DECIMAL(10,2) NOT NULL, "
                + "tipo VARCHAR(20) NOT NULL"
                + ")";

        try (
                Connection conexao = Conexao.conectar();
                Statement stmt = conexao.createStatement()
        ) {

            stmt.execute(sql);

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao criar tabela: "
                    + e.getMessage()
            );
        }
    }

    public void inserir(Transacao transacao) {

        String sql =
                "INSERT INTO transacoes "
                + "(data, descricao, valor, tipo) "
                + "VALUES (?, ?, ?, ?)";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setDate(
                    1,
                    Date.valueOf(transacao.getData())
            );

            stmt.setString(
                    2,
                    transacao.getDescricao()
            );

            stmt.setDouble(
                    3,
                    transacao.getValor()
            );

            stmt.setString(
                    4,
                    transacao.getTipo()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao inserir transação: "
                    + e.getMessage()
            );
        }
    }

    public List<Transacao> listar() {

        List<Transacao> transacoes =
                new ArrayList<>();

        String sql =
                "SELECT id, data, descricao, valor, tipo "
                + "FROM transacoes "
                + "ORDER BY data DESC, id DESC";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                Transacao transacao =
                        new Transacao();

                transacao.setId(
                        rs.getInt("id")
                );

                transacao.setData(
                        rs.getDate("data")
                                .toLocalDate()
                );

                transacao.setDescricao(
                        rs.getString("descricao")
                );

                transacao.setValor(
                        rs.getDouble("valor")
                );

                transacao.setTipo(
                        rs.getString("tipo")
                );

                transacoes.add(transacao);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao listar transações: "
                    + e.getMessage()
            );
        }

        return transacoes;
    }

    public void atualizar(Transacao transacao) {

        String sql =
                "UPDATE transacoes SET "
                + "data = ?, "
                + "descricao = ?, "
                + "valor = ?, "
                + "tipo = ? "
                + "WHERE id = ?";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setDate(
                    1,
                    Date.valueOf(transacao.getData())
            );

            stmt.setString(
                    2,
                    transacao.getDescricao()
            );

            stmt.setDouble(
                    3,
                    transacao.getValor()
            );

            stmt.setString(
                    4,
                    transacao.getTipo()
            );

            stmt.setInt(
                    5,
                    transacao.getId()
            );

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao atualizar transação: "
                    + e.getMessage()
            );
        }
    }

    public void remover(int id) {

        String sql =
                "DELETE FROM transacoes "
                + "WHERE id = ?";

        try (
                Connection conexao = Conexao.conectar();
                PreparedStatement stmt =
                        conexao.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (SQLException e) {

            System.out.println(
                    "Erro ao remover transação: "
                    + e.getMessage()
            );
        }
    }
}