import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {

		boolean exit = false;
		Scanner sc = new Scanner(System.in);

		// API do IMDB fora do ar!
//		var propertiesAluraStickers = new PropertiesAluraStickers(); 

		// Fazer uma conexão http e buscar somente os dados dos 250 filmes
		String urlFilmes = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
		String urlSeries = "https://api.mocki.io/v2/549a5d8b/Top250TVs";
		

		var clientFilmes = HttpClient.newHttpClient()
				.send(HttpRequest.newBuilder(URI.create(urlFilmes)).GET().build(), BodyHandlers.ofString()).body();
		var clientSeries = HttpClient.newHttpClient()
				.send(HttpRequest.newBuilder(URI.create(urlSeries)).GET().build(), BodyHandlers.ofString()).body();

		// extrair somente os dados que interessam (titulo, poster, classificação)
		List<Item> listaDeFilmes = new Gson().fromJson(clientFilmes, Root.class).items.stream().collect(Collectors.toList());
		List<Item> listaDeSeries = new Gson().fromJson(clientSeries, Root.class).items.stream().collect(Collectors.toList());

		// Exibir e manipular os dados do jeito que queremos

//		for(Filme filme : listaDeFilmes) {
//			System.out.println(filme.getTitle());
//			System.out.println(filme.getImage());
//			System.out.println("Classificação: " + convertRating(filme.getImDbRating()));
//			System.out.println();
//		}

		do {

			System.out.println("Deseja realizar qual opção?");
			System.out.println("1 - Exibir catálogo de filmes");
			System.out.println("2 - Exibir informações de um filme");
			System.out.println("3 - Inserir/Alterar avaliação em um filme");
			System.out.println("4 - Gerar figurinhas dos filmes");
			System.out.println("5 - Exibir catálogo de séries");
			System.out.println("6 - Exibir informações de uma série");
			System.out.println("7 - Inserir/Alterar avaliação em uma série");
			System.out.println("8 - Gerar figurinhas das séries");
			System.out.println("9 - Sair");
			var opcao = sc.nextLine();

			switch (opcao) {
			case "1":
				getLista(listaDeFilmes);
				break;
			case "2":
				find(listaDeFilmes, sc);
				break;
			case "3":
				findAndRate(listaDeFilmes, sc);
				break;
			case "4":
				geraFigurinha(listaDeFilmes);
				break;
			case "5":
				getLista(listaDeSeries);
				break;
			case "6":
				find(listaDeSeries, sc);
				break;
			case "7":
				findAndRate(listaDeSeries, sc);
				break;
			case "8":
				geraFigurinha(listaDeSeries);
				break;
			case "9":
				exit = true;
				break;
			default:
				System.out.println("Opção inválida!");
			}

		} while (!exit);
		
		sc.close();
	}

	private static String convertRating(String rating) {

		var ratingValue = (int) (Double.parseDouble(rating));
		String emoji = Character.toString(11088);
		String emojis = "";

		for (int i = 0; i < ratingValue; i++) {
			emojis += emoji;
		}

		return emojis;

	}

	private static void getLista(List<Item> lista) {
		for (Item info : lista) {
			System.out.println("Código: " + info.getRank());
			System.out.println("Título: " + info.getTitle());
			System.out.println("Imagem: " + info.getImage());
			System.out.println("Avaliação Geral: " + convertRating(info.getImDbRating()));
			String avaliacaoPessoal = info.getImDbRatingUser() != null ? convertRating(info.getImDbRatingUser())
					: "Sem Nota";
			System.out.println("Avaliação Pessoal: " + avaliacaoPessoal);
			System.out.println();
		}
	}

	private static boolean encontrar(List<Item> lista, String codigo) {
		var find = false;

		for (Item info : lista) {

			if (info.getRank().contains(codigo)) {
				find = true;
			}

		}

		return find;
	}
	
	private static void geraFigurinha(List<Item> lista) throws MalformedURLException, IOException {

		var propertiesAluraStickers = new PropertiesAluraStickers();
		System.out.println("Gerando imagens...");
		
		var geradora = new GeradoraDeFigurinhas();
		
		for (Item info : lista) {
			
			String urlImagem = info.getImage();
			String titulo = info.getTitle();
			
			InputStream inputStream = new URL(urlImagem).openStream();
			
			String frase = escolheFrase(info.getImDbRating());
			
			geradora.cria(
					inputStream, 
					propertiesAluraStickers.getSaidaFiles().concat(File.separator).concat(titulo).concat(".png"), 
					frase);
			
			System.out.println("Titulo: " + info.getTitle());
			System.out.println();
		}
		
		System.out.println("Imagens geradas com sucesso!");
		
	}
	
	private static String escolheFrase(String imDbRating) {
		double rating = Double.parseDouble(imDbRating);
		
		if (rating >= 8) {			
			return "TOPZERA";
		} else if(rating >= 6) {
			return "MEDIANO";
		} else if(rating >= 4) {
			return "RUIM";
		} else {
			return "RUINZÃO";
		}
		
	}

	private static void findAndRate(List<Item> lista, Scanner sc) {
		System.out.println("Digite o código do filme: ");
		String codigo = sc.nextLine();

		if (encontrar(lista, codigo)) {
			System.out.println("Filme encontrado!");
			boolean validacao = false;
			int valorInfo = Integer.parseInt(codigo)-1;
			
			if(valorInfo > 0) {

				do {
	
					System.out.println("Qual a nota para o filme? Digite o valor de 1 a 10");
					String nota = sc.nextLine();
					
					try {
						if (((int) (Double.parseDouble(nota)) <= 10)) {
							validacao = true;
							lista.get(Integer.parseInt(codigo)-1).setImDbRatingUser(nota);
							System.out.println("Nota inserida com sucesso!");
						} else {
							System.out.println("O valor não é válido para a nota do filme!");
						}
	
					} catch (NumberFormatException e) {
						System.out.println("Digite um valor númerico entre 1 - 10");
					}
				} while (!validacao);
				
			}
			
		} else {
			System.out.println("Filme não encontrado!");
		}
	}
	
	
	private static void find(List<Item> lista, Scanner sc) {
		System.out.println("Digite o código do filme: ");
		String codigo = sc.nextLine();
		
		if (encontrar(lista, codigo)) {
			System.out.println("Filme encontrado!");
			int valorInfo = Integer.parseInt(codigo)-1;
			
			if(valorInfo > 0) {
			
				var info = lista.get(valorInfo);
			
				System.out.println("Código: " + info.getRank());
				System.out.println("Título: " + info.getTitle());
				System.out.println("Imagem: " + info.getImage());
				System.out.println("Avaliação Geral: " + convertRating(info.getImDbRating()));
				String avaliacaoPessoal = info.getImDbRatingUser() != null ? convertRating(info.getImDbRatingUser())
						: "Sem Nota";
				System.out.println("Avaliação Pessoal: " + avaliacaoPessoal);
				System.out.println();
			}
			
		} else {
			System.out.println("Filme não encontrado!");
		}
	}

}
