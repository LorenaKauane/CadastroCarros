
package Bean;


import DAO.crudDAO;
import DAO.dao;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import objetoEntity.Carro;



@ManagedBean
@SessionScoped 
public class carroBean extends crudBean<Carro, dao>{

    private dao carroDao;
    
    
    @Override
    public dao getDao() {
        if(carroDao == null){
            //gera um novo carro
            carroDao = new dao();
        }
        return carroDao;
    }

    @Override
    public Carro criarNovaEntidade() {
        return new Carro();
    }



    
}
