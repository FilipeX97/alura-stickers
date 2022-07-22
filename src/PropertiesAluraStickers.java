import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesAluraStickers {
	
	private String chaveAPIimdb;
	private String entradaFiles;
	private String saidaFiles;
	
	
	PropertiesAluraStickers() throws IOException {
		Properties prop = new Properties();
		prop.load(Files.newInputStream(Paths.get("configuracao.properties")));
		chaveAPIimdb = prop.getProperty("chave.api.imdb");
		entradaFiles = prop.getProperty("entrada.files");
		saidaFiles = prop.getProperty("saida.files");
	}


	public String getChaveAPIimdb() {
		return chaveAPIimdb;
	}


	public String getEntradaFiles() {
		return entradaFiles;
	}


	public String getSaidaFiles() {
		return saidaFiles;
	}

}
