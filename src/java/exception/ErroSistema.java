package exception;
/*------------- Classe para tratar os erros do expetion do mysql -------------*/
public class ErroSistema extends Exception {
    /* ------------- Message e referente a classe do exception do proprio java disponibiliza */
    public ErroSistema(String message) {
        super(message);
    }
    
    public ErroSistema(String message, Throwable cause) {
        super(message, cause);
    }
    
}