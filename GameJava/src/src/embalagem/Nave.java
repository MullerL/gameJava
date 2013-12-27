package src.embalagem;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {
	private int x, y;
	private int dx, dy;
	private int altura, largura;
	private boolean isVisivel;
	
	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	private Image imagem;
	private List<Bala> misseis;
	
	public Nave() {
		ImageIcon objImageIcon = new ImageIcon("imagens\\nave7.png");
		imagem = objImageIcon.getImage();

		largura = imagem.getWidth(null);
		altura = imagem.getHeight(null);
		
		misseis = new ArrayList<Bala>();
		
		this.x = 100;
		this.y = 100;
		
	}
	
	public List<Bala> getMisseis() {
		return misseis;
	}

	public void deslocamento()
	{		
		x += dx;
		y += dy;
		
		//evitar da nave transcender a tela
		x = x < 1 ? 1 : x;
		x = x > 731 ? 731 : x;
		
		y = y < 1 ? 1 : y;
		y = y > 528 ? 528 : y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}
	
	public void atira() {// dispara a bala de acordo com a posição da nave
		this.misseis.add(new Bala(x + largura, y + altura / 2));

	}
	
	public Rectangle getBounds()//retorna area da nave
	{
		return new Rectangle(x,y,largura,altura);
	}
	
	public void keyPressed(KeyEvent tecla) //movimentação da nave
	{
		int codTecla = tecla.getKeyCode();
		
		if (codTecla == KeyEvent.VK_SPACE) {
			atira();
		}
		
		if (codTecla == KeyEvent.VK_UP) {
			dy = -2;			
		}
		
		if (codTecla == KeyEvent.VK_DOWN) {
			dy = 2;
		}
		
		if (codTecla == KeyEvent.VK_LEFT) {
			dx = -2;			
		}
		
		if (codTecla == KeyEvent.VK_RIGHT) {
			dx = 2;
		}		
	}
	
	public void keyReleased(KeyEvent tecla)//paralisando a nave
	{
		int codTecla = tecla.getKeyCode();
		
		if (codTecla == KeyEvent.VK_UP) {
			dy = 0;			
		}
		
		if (codTecla == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		
		if (codTecla == KeyEvent.VK_LEFT) {
			dx = 0;			
		}
		
		if (codTecla == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
	}		
}