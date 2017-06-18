
package util;

import exception.ErroSistema;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*------------- Aqui e a conexao com o banco -------------*/
public class fabricaConexao {
    //conexao com o banco
    private static Connection conexao;
    private static final String URL_CONEXAO="jdbc:mysql://localhost:3307/carros?useSSL=false"; 
    private static final String USUARIO="root"; 
    private static final String SENHA="admin"; 
            
            
    public static Connection getConexao() throws ErroSistema{
        if (conexao == null ){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection(URL_CONEXAO, USUARIO,SENHA);              
            }  catch (SQLException ex) {
                //lançar um erro no java
                throw new ErroSistema("Não foi possivel conectar ao banco de dados", ex);
            }  catch (ClassNotFoundException ex) {
                throw new ErroSistema("Driver do banco não foi encontrado", ex);
            }        
        }
        return conexao;
    }   
    
    public static void fecharConexao() throws ErroSistema{
        if (conexao != null){
            try {
                conexao.close();
                conexao = null;
            } catch (SQLException ex) {
                throw new ErroSistema("Erro ao fechar conexãoo com o banco", ex);
            }
        }
    }
}



