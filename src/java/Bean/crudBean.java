package Bean;


import DAO.crudDAO;
import exception.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/*------------ Classe responsavel por alterar os estados da tela, tipo um esconde e esconde*/
public abstract class crudBean<E, D extends crudDAO> {

    //3 estados:: Inserindo, e'dita e busca
    private String estadoTela = "buscar";
    //trafegar os dados
    private E entidade;
    //referente ao buscar
    private List<E> entidades;

    public void novo() {
        //zerar entidade
        entidade = criarNovaEntidade();
        //mudar o estado da tela
        mudarParaInseri();
    }

    public void salvar() {
        try {
            getDao().salvar(entidade);
            //limpar entidade
            entidade = criarNovaEntidade();
            adicionarMensagem("Salvo com sucesso", FacesMessage.SEVERITY_INFO);
            mudarParaBusca();
        } catch (ErroSistema ex) {
            Logger.getLogger(crudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void editar(E entidade) {
        this.entidade= entidade;
        mudarParaEdita();
    }

    public void deletar(E entidade) {
        try {
            getDao().deletar(entidade);
            //removendo
            entidades.remove(entidade);
            adicionarMensagem("Deletado com sucesso", FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(crudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void buscar() {
        if(isBusca()== false){
            mudarParaBusca();
            return;
        }
        try {
            entidades=getDao().buscar();
            if(entidades == null || entidades.size() <1){
                adicionarMensagem("Ops! Nada cadastrado", FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(crudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
    }

    public void adicionarMensagem(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

    public String getEstadoTela() {
        return estadoTela;
    }

    public void setEstadoTela(String estadoTela) {
        this.estadoTela = estadoTela;
    }

    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }

    //metodos relacionado a ocrud
    //responsavel por criar metodos na classe bean 
    public abstract D getDao();

    //vai dar uma nova entidade 
    public abstract E criarNovaEntidade();

    //alterar o estado, sempre vai iniciar em busca
    //metodo de consulta para controle de tela 
    public boolean isInseri() {
        //se inseri e igual a tela 
        return "inserir".equals(estadoTela);
    }

    public boolean isEdita() {
        //se inseri e igual a tela 
        return "editar".equals(estadoTela);
    }

    public boolean isBusca() {
        //se inseri e igual a tela 
        return "buscar".equals(estadoTela);
    }

    //controlar o estado da tela 
    public void mudarParaInseri() {
        estadoTela = "inserir";
    }

    public void mudarParaEdita() {
        estadoTela = "editar";
    }

    public void mudarParaBusca() {
        estadoTela = "buscar";
    }
}
