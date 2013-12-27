package src.embalagem;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import com.sun.org.apache.xml.internal.utils.StopParseException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.transform.Templates;
import sun.awt.WindowClosingListener;
import sun.java2d.Disposer;

public class Fase extends JPanel implements ActionListener {
	private Image fundo;
	private Nave nave;
	private Timer timer;        
        	
	private boolean emJogo;
	
	private List<NaveInimiga> inimigos;
	
        //profundidade , altura
	public static int[][] coordenadas = { 
//			{ 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 780, 109 }, 
//			{ 580, 139 }, { 880, 239 }, { 790, 259 }, { 760, 50 }, 
//			{ 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
//			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
                    	{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 200 }, { 856, 328 }, { 486, 320 }, 
                        {800, 340}, {830, 380}, {860, 360},{880, 380},
                        {940, 400}, {800, 420}, {850, 480}, {800, 500},
                        {980, 400}, {880, 440}, {900, 480}, {880, 500}
};

	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);//eliminar os risquinhos		
		addKeyListener(new TecladoAdaptador());
		ImageIcon objImageIcon = new ImageIcon("imagens\\universo.jpg");
		fundo = objImageIcon.getImage();
		
		nave = new Nave();
		
		emJogo = true;
		
		inicializaInimigos();
		
		timer = new Timer(5, this);
		timer.start();
                repaint();
	}
	
	public void inicializaInimigos()
	{
		inimigos = new ArrayList<NaveInimiga>();
		
		for (int i = 0; i < coordenadas.length; i++) 
		{
			inimigos.add(new NaveInimiga(coordenadas[i][0], coordenadas[i][1]));
		}
	}
	
	public void paint (Graphics g)
	{
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		
		if (emJogo) 
		{			
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
			
			List<Bala> misseis = nave.getMisseis();
			
			for (int i = 0; i < misseis.size();	i++) {
				Bala m = (Bala) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
				
			}
			
			for (int i = 0; i < inimigos.size();i++) {
				NaveInimiga ni = inimigos.get(i);
				graficos.drawImage(ni.getImagem(), ni.getX(), ni.getY(), this);
				
			}	
			
			graficos.setColor(Color.YELLOW);// numero de inimigos atual
			graficos.drawString("Inimigos: " + inimigos.size(), 5, 15);
		}
                else if (emJogo == false && inimigos.size() > 0) //foi morto
		{
			ImageIcon objImageIcon = new ImageIcon("imagens\\GAME-OVER.png");
			 
			graficos.drawImage(objImageIcon.getImage(), 0, 0, null);
		}
                else // ganhou o jogo
                {
                    ImageIcon objImageIcon = new ImageIcon("imagens\\winner-win.jpg");
			
                    graficos.drawImage(objImageIcon.getImage(), 0, 0, null);
                }
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (inimigos.size() == 0)
		{
			emJogo = false;               
		}
		
		List<Bala> misseis = nave.getMisseis();
		
		for (int i = 0; i < misseis.size(); i++) {
			Bala m = (Bala) misseis.get(i);
			if (m.eVisivel()) { //missel esta visivel?
				m.deslocamentoBala();
			}
			else {
				misseis.remove(i);
			}
		}
		
		for (int i = 0; i < inimigos.size(); i++) {
			NaveInimiga ni = inimigos.get(i);
			
			if (ni.eVisivel()) { //missel esta visivel?
				ni.deslocamentoInimigo();
			}
			else {
				inimigos.remove(i);
			}
		}
		
		nave.deslocamento();
		checarColisoes();
		repaint();		
	}
	
	public void checarColisoes()
	{
		Rectangle formatoNave = nave.getBounds();
		Rectangle formatoInimigo;
		Rectangle formatoBala;
		
		//inicio analise se o inimigo encostou na nave
		for (int i = 0; i < inimigos.size(); i++) {
			NaveInimiga tempInimigo = inimigos.get(i);
			formatoInimigo = tempInimigo.getBounds();
			
			if (formatoNave.intersects(formatoInimigo)) //nave bateu no inimigo? 
			{
				nave.setVisivel(false);
				tempInimigo.setVisible(false);
				emJogo = false;
			}
		}
		//fim analise se o inimigo encostou na nave
		
		List<Bala> misseis = nave.getMisseis();
		
		for (int i = 0; i < misseis.size(); i++) {
			Bala tempMissel = misseis.get(i);
			formatoBala = tempMissel.getBounds();
			
			for (int k = 0; k < inimigos.size(); k++) {
				
				NaveInimiga tempInimigo = inimigos.get(k);
				formatoInimigo = tempInimigo.getBounds();
				
				if (formatoBala.intersects(formatoInimigo))//bala bateu no inimigo? 
				{
					tempInimigo.setVisible(false);
					tempMissel.setVisible(false);
					
				}
			}
		}		
	}
	
	
	private class TecladoAdaptador extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyCode() == KeyEvent.VK_ENTER && emJogo == false && inimigos.size() > 0)
			{
				emJogo = true;
				nave = new Nave();
                                inicializaInimigos();
			}
                        else if (e.getKeyCode() == KeyEvent.VK_ENTER && emJogo == false && inimigos.size() == 0)
			{
                            coordenadas = new int[][] {{ 2380, 29 }, { 2600, 59 }, { 1380, 89 }, { 780, 109 }, 
			{ 580, 139 }, { 880, 239 }, { 790, 259 }, { 760, 50 }, 
			{ 790, 150 }, { 1980, 209 }, { 560, 45 }, { 510, 70 },
			{ 930, 159 }, { 590, 80 }, { 530, 60 }, { 940, 59 }, { 990, 30 },
			{ 920, 200 }, { 900, 259 }, { 660, 50 }, { 540, 90 }, { 810, 220 },
                    	{ 860, 20 }, { 740, 180 }, { 820, 128 }, { 490, 170 }, { 700, 30 },
			{ 920, 200 }, { 856, 328 }, { 486, 320 }, 
                        {800, 340}, {830, 380}, {860, 360},{880, 380},
                        {940, 400}, {800, 420}, {850, 480}, {800, 500},
                        {980, 400}, {880, 440}, {900, 480}, {880, 500}};
                        
                            ImageIcon objImageIcon = new ImageIcon("imagens\\galaxy-universe.jpg");                            
                            fundo = objImageIcon.getImage();                            
                            emJogo = true;
                            nave = new Nave();
                            inicializaInimigos();
                        }
                        else
                        {
                            nave.keyPressed(e);
                        }
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.keyReleased(e);
		}		
	}	
}