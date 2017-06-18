package DAO;

import exception.ErroSistema;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objetoEntity.Carro;
import util.fabricaConexao;
/* ------------- Usuario DAO e a parte do java + conexao com o banco utulizando a classe
                fabricaconexao-------------*/
public class dao implements crudDAO<Carro>{
    @Override
    public void salvar(Carro carro) throws ErroSistema {
        try {
            Connection conexao = fabricaConexao.getConexao();
            PreparedStatement stmt;
            if (carro.getId() == null) {
                stmt = conexao.prepareStatement("INSERT INTO carro (modelo,fabricante,cor,ano)VALUES(?,?,?,?)");
            } else {
                stmt = conexao.prepareStatement("update carro set modelo=?,fabricante=?, cor=?, ano=? where id=?");
                stmt.setInt(5, carro.getId());
            }
            stmt.setString(1, carro.getModelo());
            stmt.setString(2, carro.getFabricante());
            stmt.setString(3, carro.getCor());
            stmt.setString(4, carro.getAno());

            stmt.execute();
            fabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar carro", ex);
        }
    }

    
    //metodo deletar
    @Override
    public void deletar (Carro carro ) throws ErroSistema{
        try {
            Connection conexao= fabricaConexao.getConexao();
            PreparedStatement stmt= conexao.prepareStatement("DELETE FROM CARRO WHERE ID =?");
            stmt.setInt(1, carro.getId());
            stmt.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao Deletar carro", ex);
        }
    }
    @Override
    public List<Carro> buscar() throws ErroSistema {
        //Listar dados
        try {
            Connection conexao = fabricaConexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement("select * from carro;");
            ResultSet rs = stmt.executeQuery();
            List<Carro> carros = new ArrayList<>();

            while (rs.next()) {
                Carro carro = new Carro();
                carro.setId(rs.getInt("id"));
                carro.setModelo(rs.getString("modelo"));
                carro.setFabricante(rs.getString("fabricante"));
                carro.setCor(rs.getString("cor"));
                carro.setAno(rs.getString("ano"));
                carros.add(carro);
            }
            fabricaConexao.fecharConexao();
            return carros;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao listar", ex);
        }

    }
}
