import java.io.IOException;
import java.net.URI;
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
		String url = "https://raw.githubusercontent.com/alexfelipe/imersao-java/json/top250.json";

		var client = HttpClient.newHttpClient()
				.send(HttpRequest.newBuilder(URI.create(url)).GET().build(), BodyHandlers.ofString()).body();

		// extrair somente os dados que interessam (titulo, poster, classificação)
		List<Filme> listaDeFilmes = new Gson().fromJson(client, Root.class).items.stream().collect(Collectors.toList());

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
			System.out.println("2 - Inserir/Alterar avaliação em um filme");
			System.out.println("3 - Sair");
			var opcao = sc.nextLine();

			switch (opcao) {
			case "1":
				getFilmes(listaDeFilmes);
				break;
			case "2":
				System.out.println("Digite o código do filme: ");
				String codigo = sc.nextLine();

				if (encontrarFilme(listaDeFilmes, codigo)) {
					System.out.println("Filme encontrado!");
					boolean validacao = false;

					do {

						System.out.println("Qual a nota para o filme? Digite o valor de 1 a 10");
						String nota = sc.nextLine();
						
						try {
							if (((int) (Double.parseDouble(nota)) <= 10)) {
								validacao = true;
								listaDeFilmes.get(Integer.parseInt(codigo)-1).setImDbRatingUser(nota);
								System.out.println("Nota inserida com sucesso!");
							} else {
								System.out.println("O valor não é válido para a nota do filme!");
							}

						} catch (NumberFormatException e) {
							System.out.println("Digite um valor númerico entre 1 - 10");
						}
					} while (!validacao);
					
					break;
				} else {
					System.out.println("Filme não encontrado!");
					break;
				}
			case "3":
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

	private static void getFilmes(List<Filme> listaDeFilmes) {
		for (Filme filme : listaDeFilmes) {
			System.out.println("Código: " + filme.getRank());
			System.out.println("Título: " + filme.getTitle());
			System.out.println("Imagem: " + filme.getImage());
			System.out.println("Avaliação Geral: " + convertRating(filme.getImDbRating()));
			String avaliacaoPessoal = filme.getImDbRatingUser() != null ? convertRating(filme.getImDbRatingUser())
					: "Sem Nota";
			System.out.println("Avaliação Pessoal: " + avaliacaoPessoal);
			System.out.println();
		}
	}

	private static boolean encontrarFilme(List<Filme> listaDeFilmes, String codigo) {
		var find = false;

		for (Filme filme : listaDeFilmes) {

			if (filme.getRank().contains(codigo)) {
				find = true;
			}

		}

		return find;
	}

}
