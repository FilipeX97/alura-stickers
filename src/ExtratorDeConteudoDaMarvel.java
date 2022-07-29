import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class ExtratorDeConteudoDaMarvel implements ExtratorDeConteudo {
	
	public List<ResultadoMarvel> extraiConteudos(String url){
		
		var json = new ClienteHttp();
		List<ResultadoMarvel> listaResultadoMarvel = null;
		
		try {
			var rootMarvel = new Gson().fromJson(json.buscaDados(url), RootMarvel.class);
			listaResultadoMarvel = rootMarvel.data.results.stream().collect(Collectors.toList());
		} catch (ClienteHttpException e) {
			e.printStackTrace();
		}
		
		return listaResultadoMarvel;
		
	}
}
