package View;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;
@SuppressWarnings("serial")
public class PanelRootView extends JFrame{
	CardLayout cl=new CardLayout();
	JPanel content;
	FuncRootView frv = new FuncRootView();
	EdicaoRootView erv = new EdicaoRootView();
	CursoRootView crv = new CursoRootView();
	SolicitacaoRootView srv=new SolicitacaoRootView();

	public PanelRootView() {
		setResizable(false);
		setMinimumSize(new Dimension(660, 600));
		getContentPane().setEnabled(false);
		getContentPane().setIgnoreRepaint(true);
		setVisible(true);
		this.setTitle("Controle de treinamentos Azul - Administrador");
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		setIconImage(new ImageIcon(getClass().getResource("/Img/icon.ico")).getImage());
		getContentPane().setLayout(new MigLayout("", "[542px,grow]", "[23.00,grow][490.00,grow]"));
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		panel_2.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_2, "flowx,cell 0 0");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.WHITE);
		panel_2.add(menuBar);
		
		JMenu mnFuncionrios = new JMenu("Funcion\u00E1rios");
		mnFuncionrios.setIcon(new ImageIcon(PanelRootView.class.getResource("/Img/new.png")));
		mnFuncionrios.setForeground(new Color(0, 102, 204));
		mnFuncionrios.setOpaque(true);
		mnFuncionrios.setBackground(new Color(255, 255, 255));
		mnFuncionrios.addMouseListener(new MouseListener() {		
			public void mousePressed(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {cl.show(content, "1");frv.refreshTable();}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnFuncionrios);
		
		JMenu mnCursos = new JMenu("Cursos");
		mnCursos.setIcon(new ImageIcon(PanelRootView.class.getResource("/Img/new.png")));
		mnCursos.setForeground(new Color(0, 102, 204));
		mnCursos.setOpaque(true);
		mnCursos.setBackground(new Color(255, 255, 255));
		mnCursos.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {cl.show(content, "2");crv.refreshTable();}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnCursos);
		
		JMenu mnEdies = new JMenu("Edi\u00E7\u00F5es");
		mnEdies.setIcon(new ImageIcon(PanelRootView.class.getResource("/Img/new.png")));
		mnEdies.setForeground(new Color(0, 102, 204));
		mnEdies.setOpaque(true);
		mnEdies.setBackground(new Color(255, 255, 255));
		mnEdies.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {cl.show(content, "3");erv.RefreshT1();}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnEdies);
		
		JMenu mnSolicitaes = new JMenu("Solicita\u00E7\u00F5es");
		mnSolicitaes.setIcon(new ImageIcon(PanelRootView.class.getResource("/Img/find.png")));
		mnSolicitaes.setForeground(new Color(0, 102, 204));
		mnSolicitaes.setOpaque(true);
		mnSolicitaes.setBackground(new Color(255, 255, 255));
		mnSolicitaes.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {cl.show(content, "4");srv.refresh1();srv.refresh2();}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnSolicitaes);
		
		JMenu mnSair = new JMenu("Sair");
		mnSair.setForeground(new Color(0, 102, 204));
		mnSair.setBorder(null);
		mnSair.setBackground(new Color(255, 255, 255));
		mnSair.setOpaque(true);
		mnSair.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent arg0) {onExit();}
			public void mouseClicked(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
		});
		menuBar.add(mnSair);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, "cell 0 1,grow");
		content = new JPanel();
		panel.add(content);
		content.setLayout(cl);
		frv.setForeground(new Color(255, 255, 255));
		frv.setBackground(Color.WHITE);
		content.add(frv,"1");
		content.add(crv, "2");
		content.add(erv, "3");
		content.add(srv, "4");
		cl.show(content,"0");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void onExit(){
		this.dispose();
		new LoginView();
	}
}