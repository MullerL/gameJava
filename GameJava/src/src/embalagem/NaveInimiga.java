package src.embalagem;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class NaveInimiga 
{
	private Image imagem;
	private int x,y;
	private int largura, altura;
	private boolean eVisivel;
	
	private static final int LARGURA_TELA = 731;
	private static final int VELOCIDADE_INIMIGO = 1;//velocidade do inimigo
	
	private static int contador;
	
	public NaveInimiga(int x, int y) 
	{
		this.x = x;
		this.y = y;
		
		ImageIcon objImageIcon;
		
		if (contador++ % 3 == 0) 
		{
			objImageIcon = new ImageIcon("imagens\\naveInimiga.gif");
		}
		else {
			objImageIcon = new ImageIcon("imagens\\naveInimiga2.gif");
		}
		
		imagem = objImageIcon.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		
		eVisivel = true;
	}
	
	public void deslocamentoInimigo() {		
		if (this.x < 0) 
		{
			this.x = LARGURA_TELA;
		}
		else
		{
			this.x -= VELOCIDADE_INIMIGO;
		}

	}
	
	public boolean eVisivel()
	{
		return eVisivel;
	}
	
	
	public void setVisible(boolean isVisible)
	{
		this.eVisivel = isVisible;
	}

	public Image getImagem() {
		return imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Rectangle getBounds()// retorna area do inimigo
	{
		return new Rectangle(x,y,largura,altura);
	}	
}