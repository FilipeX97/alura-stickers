import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesAluraStickers {
	
	private String chaveAPIimdb;
	private String chaveAPINasa;
	private String chaveAPIMarvel;
	private String hashAPIMarvel;
	private String entradaFiles;
	private String saidaFiles;
	
	
	PropertiesAluraStickers() throws IOException {
		Properties prop = new Properties();
		prop.load(Files.newInputStream(Paths.get("configuracao.properties")));
		chaveAPIimdb = prop.getProperty("chave.api.imdb");
		chaveAPINasa = prop.getProperty("chave.api.nasa");
		chaveAPIMarvel = prop.getProperty("chave.api.marvel");
		hashAPIMarvel = prop.getProperty("hash.api.marvel");
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


	public String getChaveAPINasa() {
		return chaveAPINasa;
	}


	public String getChaveAPIMarvel() {
		return chaveAPIMarvel;
	}


	public String getHashAPIMarvel() {
		return hashAPIMarvel;
	}

}
