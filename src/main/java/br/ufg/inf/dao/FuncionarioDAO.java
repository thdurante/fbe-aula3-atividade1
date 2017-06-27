package br.ufg.inf.dao;

import br.ufg.inf.dto.FuncionarioDTO;

import java.sql.*;
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
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { this.connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return funcionariosLista;
    }

    public FuncionarioDTO getFuncionario(Integer id) {
        FuncionarioDTO funcionario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (id == 0 || id == null) return null;

        try {
            ps = this.connection.prepareStatement("SELECT * FROM funcionarios WHERE id = ?");
            ps.setInt(1, id.intValue());
            rs = ps.executeQuery();
            while (rs.next()) {
                funcionario = new FuncionarioDTO(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("nascimento"),
                        rs.getString("telefone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { this.connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return funcionario;
    }

    public boolean addFuncionario(FuncionarioDTO funcionario) {
        PreparedStatement ps = null;
        String sql = "INSERT INTO funcionarios (nome, cpf, email, nascimento, telefone) VALUES (?, ?, ?, ?, ?)";

        if (funcionario == null) return false;

        try {
            ps = this.connection.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getEmail());
            ps.setDate(4, new Date(funcionario.getNascimento().getTime()));
            ps.setString(5, funcionario.getTelefone());

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { this.connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return true;
    }

    public boolean updateFuncionario(Integer id, FuncionarioDTO funcionario) {
        PreparedStatement ps = null;
        String sql = "UPDATE funcionarios SET nome=?, cpf=?, email=?, nascimento=?, telefone=? WHERE id=?";

        if (funcionario == null) return false;

        try {
            ps = this.connection.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getCpf());
            ps.setString(3, funcionario.getEmail());
            ps.setDate(4, new Date(funcionario.getNascimento().getTime()));
            ps.setString(5, funcionario.getTelefone());
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { this.connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return true;
    }

    public boolean deleteFuncionario(Integer id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM funcionarios WHERE id=?";

        if (id == 0 || id == null) return false;

        try {
            ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id.intValue());
            ps.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        } finally {
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { this.connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return true;
    }

    public FuncionarioDTO getFuncionarioPorCpf(String cpf) {
        FuncionarioDTO funcionario = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (cpf == null || cpf.equals("")) return null;

        try {
            ps = this.connection.prepareStatement("SELECT * FROM funcionarios WHERE cpf = ?");
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while (rs.next()) {
                funcionario = new FuncionarioDTO(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("nascimento"),
                        rs.getString("telefone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { this.connection.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return funcionario;
    }
}
