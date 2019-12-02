package View;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
@SuppressWarnings("serial")
public class PanelUserView extends JFrame{
	CardLayout cl=new CardLayout();
	JPanel content;
	CursoUserView cuv = new CursoUserView();
	MeusCursosUserView mcuv= new MeusCursosUserView();
	PerfilUserView puv=new PerfilUserView();
	public PanelUserView() {
		setMinimumSize(new Dimension(660, 600));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(PanelUserView.class.getResource("/Img/icon.ico")));
		setTitle("Controle de Treinamentos - Azul");
		getContentPane().setBackground(new Color(255, 255, 255));
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		getContentPane().setLayout(new MigLayout("", "[543px]", "[21px][467px]"));
		content = new JPanel();
		content.setBorder(null);
		content.setBackground(Color.WHITE);
		content.setLayout(cl);
		getContentPane().add(content, "cell 0 1,grow,push");
		content.add(cuv,"1");
		content.add(mcuv, "2");
		content.add(puv, "3");
		
		cl.show(content, "0");
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.WHITE);
		getContentPane().add(menuBar, "cell 0 0,pushx ,aligny top");
		
		JMenu mnCursos = new JMenu("Cursos");
		mnCursos.setBorder(null);
		mnCursos.setForeground(new Color(0, 102, 204));
		mnCursos.setIcon(new ImageIcon(PanelUserView.class.getResource("/Img/new.png")));
		mnCursos.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent arg0) {cl.show(content, "1");}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnCursos);
		
		JMenu mnMeusCursos = new JMenu("Meus Cursos");
		mnMeusCursos.setBorder(null);
		mnMeusCursos.setForeground(new Color(0, 102, 204));
		mnMeusCursos.setIcon(new ImageIcon(PanelUserView.class.getResource("/Img/find.png")));
		mnMeusCursos.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent arg0) {cl.show(content, "2");}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnMeusCursos);
		
		JMenu mnPerfil = new JMenu("Perfil");
		mnPerfil.setBorder(null);
		mnPerfil.setForeground(new Color(0, 102, 204));
		mnPerfil.setIcon(new ImageIcon(PanelUserView.class.getResource("/Img/edit.png")));
		mnPerfil.addMouseListener(new MouseListener(){
			public void mousePressed(MouseEvent arg0) {cl.show(content, "3");}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnPerfil);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setBorder(null);
		mnSair.setForeground(new Color(0, 102, 204));
		mnSair.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent arg0) {onExit();}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnSair);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void onExit(){
		this.dispose();
		new LoginView().setVisible(true);
	}
}

