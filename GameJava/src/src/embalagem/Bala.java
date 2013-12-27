package src.embalagem;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bala 
{
	private Image imagem;
	private int x,y;
	private int altura, largura;
	private boolean eVisivel;
	
	private static final int LARGURA_TELA = 731;
	private static final int VELOCIDADE_BALA = 2;
	
	public Bala(int x, int y) 
	{
		this.x = x;
		this.y = y;
		
		ImageIcon objImageIcon = new ImageIcon("imagens\\bolavermelha.png");
		imagem = objImageIcon.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
		
		eVisivel = true;
	}
	
	public void deslocamentoBala() {
		this.x += VELOCIDADE_BALA;
		if (this.x > LARGURA_TELA) {
			eVisivel = false;
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
	
	public Rectangle getBounds() // retorna area da bala
	{
		return new Rectangle(x,y,largura,altura);
	}	
}