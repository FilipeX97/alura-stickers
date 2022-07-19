import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesAluraStickers {
	
	private String chaveAPIimdb;
	
	
	PropertiesAluraStickers() throws IOException {
		Properties prop = new Properties();
		prop.load(Files.newInputStream(Paths.get("configuracao.properties")));
		chaveAPIimdb = prop.getProperty("chave.api.imdb");
	}


	public String getChaveAPIimdb() {
		return chaveAPIimdb;
	}

}
