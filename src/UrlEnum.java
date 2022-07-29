
public enum UrlEnum {
	
	
	
	IMDBFilme("https://api.mocki.io/v2/549a5d8b/Top250Movies"),
	IMDBSeries("https://api.mocki.io/v2/549a5d8b/Top250TVs"),
	Nasa("https://api.nasa.gov/planetary/apod"),
	Marvel("https://gateway.marvel.com/v1/public/characters");


	// API do IMDB fora do ar!
	//	var propertiesAluraStickers = new PropertiesAluraStickers(); 
	//	String urlNasa = "https://api.nasa.gov/planetary/apod?api_key="+properties.getChaveAPINasa();
	
	private String url;

	UrlEnum(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
	
	

}
