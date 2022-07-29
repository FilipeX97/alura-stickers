import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {
	
	public List<ItemIMDB> extraiConteudos(String url){
		
		var json = new ClienteHttp();
		List<ItemIMDB> listaItemIMDB = null;
		
		try {
			listaItemIMDB = new Gson().fromJson(json.buscaDados(url), Root.class).items.stream().collect(Collectors.toList());
		} catch (ClienteHttpException e) {
			e.printStackTrace();
		}
		
		return listaItemIMDB;
		
	}

}
