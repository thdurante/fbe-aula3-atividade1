package br.ufg.inf.dao;

import br.ufg.inf.dto.FuncionarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO {

    private Connection connection;

    public FuncionarioDAO() {
        this.connection = new ConnectionManager().getConnection();
    }

    public ArrayList<FuncionarioDTO> getFuncionarios() {
        ArrayList<FuncionarioDTO> funcionariosLista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = this.connection.prepareStatement("SELECT * FROM funcionarios");
            rs = ps.executeQuery();
            while (rs.next()) {
                FuncionarioDTO funcionario = new FuncionarioDTO(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("nascimento"),
                        rs.getString("telefone")
                );
                funcionariosLista.add(funcionario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { this.connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return funcionariosLista;
    }
}
