package src.embalagem;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class caixa extends JFrame 
{
	public caixa() {
            JMenuBar objJMenuBar = new JMenuBar();
            JMenu objJMenu = new JMenu("Menu");
            JMenuItem objJMenuItemSobre = new JMenuItem("Sobre");
            objJMenuItemSobre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));  
            
            objJMenuItemSobre.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    JOptionPane.showMessageDialog(null, "Jogo desenvolvido por \nMüller \nAlander \nJhonny \nAndre ", "Sobre", JOptionPane.INFORMATION_MESSAGE);
                }
            });
            objJMenu.add(objJMenuItemSobre);
            objJMenuBar.add(objJMenu);
            setJMenuBar(objJMenuBar);
            
            add(new Fase());
            setTitle("A Nave");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setMaximumSize(null);
            setSize(800,620);
            setResizable(false);// não deixa maximizar redimensionar
            setLocationRelativeTo(null);// exibe tela no centro do monitor

            setVisible(true);
	}
	
	public static void main(String[] args) {
		new caixa();
	}
}