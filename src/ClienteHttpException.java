
public class ClienteHttpException extends Exception {

//	
//	// Mensagem
//    public ClienteHttpException(String msg){
//        super(msg);
//        this.msg = msg;
//    }
	
	private static final long serialVersionUID = 1L;

	// Mensagem e causa
    public ClienteHttpException(){
        super("Erro na requisição, não foi possível obter os dados!");
    }

}
