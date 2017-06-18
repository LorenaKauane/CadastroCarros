
package Bean;

import DAO.crudDAO;
import DAO.usuarioDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import objetoEntity.Usuario;

@ManagedBean
@SessionScoped 
//PRIMEIRO SEMPRE VAI SER ENTIDADE E DEPOIS O DAO DA ENTIDADE
public class usuarioBEAN extends crudBean<Usuario, usuarioDAO> {

    private usuarioDAO UsuarioDao;
    @Override
    public usuarioDAO getDao() {
       if(UsuarioDao == null){
           UsuarioDao = new usuarioDAO();
       }
       return UsuarioDao;
    }

    @Override
    public Usuario criarNovaEntidade() {
        return new Usuario();
    }
    
}
