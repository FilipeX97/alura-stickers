import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHttp {

	public String buscaDados(String url) throws ClienteHttpException {

		String body = null;

		try {
			body =  HttpClient.newHttpClient()
					.send(HttpRequest.newBuilder(URI.create(url)).GET().build(), BodyHandlers.ofString()).body();
		} catch (IOException | InterruptedException e) {
			System.out.println("Erro na busca do JSON");
			body = "{}";
			e.printStackTrace();
		}

		return body;

	}

}
