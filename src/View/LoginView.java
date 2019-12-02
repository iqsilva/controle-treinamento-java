package View;
import Bean.*;
import Controller.FuncController;
import java.util.List;
import javax.swing.*;
//import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
@SuppressWarnings("serial")
public class LoginView extends JFrame {
	private JLabel lblUser,lblSenha,lblLogo;
	private JTextField txtUser;
	private JPasswordField txtSenha;
	private JButton btnLogin,btnCad;
	private List<Funcionario> loginList;
	public LoginView() {
		
		super("Controle de treinamentos - Azul");
		setTitle("Controle de Treinamentos - Azul");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/Img/icon.ico")));
		setMaximumSize(new Dimension(400, 900));
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		setContentPane(panel);
		getContentPane().setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		lblLogo=new JLabel(new ImageIcon(LoginView.class.getResource("/Img/logo.png")));
		lblLogo.setBounds(58, 29, 308, 199);
		panel.add(lblLogo);
		
		getContentPane().setBackground(new Color(255, 255, 255));
		
		btnLogin=new JButton("");
		btnLogin.setContentAreaFilled(false);
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setIcon(new ImageIcon(LoginView.class.getResource("/Img/button_entrar.png")));
		btnLogin.setBounds(101, 353, 101, 29);
		panel.add(btnLogin);
		btnLogin.setBorderPainted(false);
		btnLogin.setInheritsPopupMenu(true);
		btnLogin.setIgnoreRepaint(true);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(30, 144, 255));
		btnLogin.setBorder(UIManager.getBorder("Button.border"));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onLogin();
			}
		});
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		btnCad=new JButton("");
		btnCad.setContentAreaFilled(false);
		btnCad.setIcon(new ImageIcon(LoginView.class.getResource("/Img/button_inscrever-se.png")));
		btnCad.setBounds(210, 353, 101, 29);
		panel.add(btnCad);
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onCad();
			}
		});
		btnCad.setForeground(Color.BLACK);
		btnCad.setBackground(SystemColor.textHighlight);
		btnCad.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(42, 313, 72, 27);
		panel.add(lblSenha);
		lblSenha.setForeground(Color.GRAY);
		lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		txtSenha=new JPasswordField(50);
		txtSenha.setBounds(101, 315, 210, 27);
		panel.add(txtSenha);
		txtSenha.setToolTipText("Senha");
		txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblUser=new JLabel("Usuário:");
		lblUser.setBounds(38, 264, 76, 27);
		panel.add(lblUser);
		lblUser.setForeground(Color.GRAY);
		lblUser.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		txtUser=new JTextField(50);
		txtUser.setBounds(101, 266, 210, 27);
		panel.add(txtUser);
		txtUser.setToolTipText("Usu\u00E1rio");
		txtUser.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		
		JLabel lblAzTodosOs = new JLabel("\u00A9 Azul Corporation. Todos os direitos reservados.");
		lblAzTodosOs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAzTodosOs.setForeground(new Color(0, 102, 204));
		lblAzTodosOs.setBackground(Color.WHITE);
		lblAzTodosOs.setBounds(10, 646, 363, 25);
		panel.add(lblAzTodosOs);
		setMinimumSize(new Dimension(400, 700));
		setLocationRelativeTo(null);
		setVisible(true);
	}
	@SuppressWarnings("deprecation")
	public void onLogin(){
		int result=0;
		if(txtUser.getText().equals("root") && txtSenha.getText().equals("root")){
			this.dispose();
			PanelRootView pr = new PanelRootView();
			pr.setVisible(true);
			return;
		}
		result= new FuncController().login(txtUser.getText(), txtSenha.getText());
		if(result==0){
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}else{
			this.dispose();
			loginList=new FuncController().sessao(txtUser.getText(), txtSenha.getText());
			Sessao.setCod_user(loginList.get(0).getCod_user());
			Sessao.setEmail_func(loginList.get(0).getEmail_func());
			Sessao.setFuncao_func(loginList.get(0).getFuncao_func());
			Sessao.setNome_func(loginList.get(0).getNome_func());
			Sessao.setNome_user(loginList.get(0).getNome_user());
			Sessao.setRe_func(loginList.get(0).getRe_func());
			Sessao.setSenha_user(loginList.get(0).getSenha_user());
			Sessao.setStatus_func(loginList.get(0).getStatus_func());
			PanelUserView pu= new PanelUserView();
			pu.setVisible(true);
		}
	}
	public void onCad(){
		new CadFuncView();
		this.dispose();
	}
}
