
package DAO;



import exception.ErroSistema;
import java.util.List;
//< tipos genericos para criar a interface > E representa a entidade 
//ela vou passar no metodo 
/* ------------- CRUD = CREATE READ UPDATE DELETE------------*/
public interface crudDAO <E>{
    //criar a interface 
    //interface nao pode ter metedo, nao serao criados na interface 
    //tipo um ponto .h em c
    public void salvar (E entidade) throws ErroSistema;
    public void deletar (E entidade) throws ErroSistema;
    public List<E> buscar() throws ErroSistema;
}
