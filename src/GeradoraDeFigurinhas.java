import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

	public void cria(InputStream inputStream, String nomeArquivo, String frase) throws IOException {

		// Leitura da imagem
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// Cria nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		var novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// Copiar a imagem original para a nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		// Configurar a fonte
		Font font;
		int novaAlturaFonte = 0;
		if (largura <= 250) {			
			font = new Font("Impact", Font.BOLD, 18);			
			novaAlturaFonte = novaAltura - 175;
		}
		else if (largura <= 500) {
			font = new Font("Impact", Font.BOLD, 28);			
			novaAlturaFonte = novaAltura - 150;
		} else {
			font = new Font("Impact", Font.BOLD, 64);
			novaAlturaFonte = novaAltura - 100;
		}
		graphics.setColor(Color.BLACK);
		graphics.setFont(font);
		graphics.translate(setTextCenter(graphics, frase, novaImagem), novaAlturaFonte);
		graphics.setStroke(new BasicStroke(4.0f));
		GlyphVector glyphVector = font.createGlyphVector(graphics.getFontRenderContext(), frase);
		Shape textShape = glyphVector.getOutline();
		
		// Escrever uma frase na nova imagem
		graphics.draw(textShape);
		graphics.setColor(Color.ORANGE);
		graphics.fill(textShape);
		
		// Escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem, "png", new File(nomeArquivo));
		

	}

	private static int setTextCenter(Graphics2D graphics, String frase,
            BufferedImage novaImagem) {
		
		int stringWidthLength = (int)
				graphics.getFontMetrics().getStringBounds(frase, graphics).getWidth();
		
		return novaImagem.getWidth() / 2 - stringWidthLength / 2;

	}
}
