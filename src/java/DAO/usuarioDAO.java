package DAO;

import exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import objetoEntity.Carro;
import objetoEntity.Usuario;
import util.fabricaConexao;


/* ------------- Usuario DAO e a parte do java + conexao com o banco utulizando a classe
                fabricaconexao-------------*/
public class usuarioDAO implements crudDAO<Usuario> {

    @Override
    public void salvar(Usuario entidade) throws ErroSistema {
        try {
            
            Connection conexao = fabricaConexao.getConexao();
            //PreparedStatement e a forma de utiliza comandos no mysql 
            PreparedStatement stmt;
            if (entidade.getId() == null) {
                stmt = conexao.prepareStatement("INSERT INTO usuario (login,senha)VALUES(?,?)");
            } else {
                stmt = conexao.prepareStatement("update usuario set login=?,senha=? where id=?");
                stmt.setInt(3, entidade.getId());
            }
            stmt.setString(1, entidade.getLogin());
            stmt.setString(2, entidade.getSenha());

            stmt.execute();
            fabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao salvar Usuario", ex);
        }
    }

    @Override
    public void deletar(Usuario entidade) throws ErroSistema {
        try {
            Connection conexao = fabricaConexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM usuario WHERE ID =?");
            stmt.setInt(1, entidade.getId());
            stmt.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao Deletar Usuario", ex);
        }
    }

    @Override
    public List<Usuario> buscar() throws ErroSistema {
        try {
            Connection conexao = fabricaConexao.getConexao();
            PreparedStatement stmt = conexao.prepareStatement("select * from usuario;");
            ResultSet rs = stmt.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));

                usuarios.add(usuario);
            }
            fabricaConexao.fecharConexao();
            return usuarios;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao listar usuario", ex);
        }

    }

}

