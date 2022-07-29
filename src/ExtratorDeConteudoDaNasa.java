import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {
	
	@Override
	public List<ItemNasa> extraiConteudos(String url) {
		
		var json = new ClienteHttp();
		
		ItemNasa itemNasa = null;
		try {
			itemNasa = new Gson().fromJson(json.buscaDados(url), ItemNasa.class);
		} catch (ClienteHttpException e) {
			e.printStackTrace();
		}
		
		List<ItemNasa> listaDaNasa = new ArrayList<>();
		listaDaNasa.add(itemNasa);
		
		return listaDaNasa;
		
	} 

}
