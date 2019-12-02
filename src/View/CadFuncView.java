package View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.*;
  
import javax.swing.text.MaskFormatter;  

import Bean.Funcionario;
import Controller.FuncController;
import net.miginfocom.swing.*;
@SuppressWarnings("serial")
public class CadFuncView extends JFrame{
	private MaskFormatter ftmRe = null;
	private JPanel panelCampos;
	private JLabel lblNome,lblEmail,lblFuncao,lblRe,lblNome_user,lblSenha,lblReSenha;
	private JTextField txtNome,txtEmail,txtNome_user,txtRe;
	private JPasswordField txtSenha1=new JPasswordField(10),txtSenha2=new JPasswordField(10);
	private JButton btnCad,btnVoltar;
	private String[] funcao={"Função A","Função B","Função C","Função D"};
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox listaFunc=new JComboBox(funcao);
	public CadFuncView(){
		super("Cadastro de Usuários");
		setVisible(true);
		setTitle("Cadastro de Usu\u00E1rio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CadFuncView.class.getResource("/Img/icon.ico")));
		try {
			ftmRe = new MaskFormatter("#######");
			ftmRe.setValidCharacters("0123456789");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}  
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		panelCampos = new JPanel(new MigLayout("", "[21.00][87.00][297.00][21.00]", "[][][][][][][][][][]"));
		panelCampos.setBackground(Color.WHITE);
		getContentPane().add(panelCampos, BorderLayout.CENTER);
		
		panelCampos.add(new JLabel(""),"cell 1 0");
		
		lblNome=new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setBackground(Color.BLACK);
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNome.setForeground(Color.GRAY);
		panelCampos.add(lblNome,"cell 1 1,grow");
		txtNome=new JTextField(20);
		txtNome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panelCampos.add(txtNome,"cell 2 1,push ,grow");
		
		lblEmail=new JLabel("E-mail:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblEmail.setForeground(Color.GRAY);
		panelCampos.add(lblEmail,"cell 1 2,grow");
		txtEmail=new JTextField(20);
		txtEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panelCampos.add(txtEmail,"cell 2 2,push ,grow");
		
		lblFuncao=new JLabel("Função:");
		lblFuncao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFuncao.setForeground(Color.GRAY);
		panelCampos.add(lblFuncao,"cell 1 3,grow");
		listaFunc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panelCampos.add(listaFunc,"cell 2 3,push ,grow");
		
		lblRe=new JLabel("Registro:");
		lblRe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRe.setForeground(Color.GRAY);
		panelCampos.add(lblRe,"cell 1 4,grow");
		txtRe=new JFormattedTextField(ftmRe);
		txtRe.setFont(new Font("Segoe UI",Font.PLAIN, 12));
		panelCampos.add(txtRe,"cell 2 4,push ,grow");
		
		lblNome_user=new JLabel("Usu\u00E1rio:");
		lblNome_user.setForeground(Color.GRAY);
		lblNome_user.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelCampos.add(lblNome_user,"cell 1 5,grow");
		txtNome_user=new JTextField(20);
		txtNome_user.setFont(new Font("Segoe UI",Font.PLAIN, 12));
		panelCampos.add(txtNome_user,"cell 2 5,push ,grow");
		
		lblSenha=new JLabel("Senha:");
		lblSenha.setForeground(Color.GRAY);
		lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelCampos.add(lblSenha,"cell 1 6,grow");
		txtSenha1.setFont(new Font("Segoe UI",Font.PLAIN,12));
		panelCampos.add(txtSenha1,"cell 2 6,push ,grow");
		
		lblReSenha=new JLabel("Repita a Senha:");
		lblReSenha.setForeground(Color.GRAY);
		lblReSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelCampos.add(lblReSenha,"cell 1 7,grow");
		
		btnCad=new JButton("");
		btnCad.setContentAreaFilled(false);
		btnCad.setIcon(new ImageIcon(CadFuncView.class.getResource("/Img/button_cadastrar.png")));
		panelCampos.add(btnCad, "flowx,cell 2 8,push ,alignx center,grow");
		btnCad.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnCad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCad();
			}
		});
		txtSenha2.setFont(new Font("Segoe UI",Font.PLAIN,12));
		panelCampos.add(txtSenha2,"cell 2 7,push ,grow");
		
		btnVoltar=new JButton("");
		btnVoltar.setContentAreaFilled(false);
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setIcon(new ImageIcon(CadFuncView.class.getResource("/Img/button_voltar.png")));
		btnVoltar.setSelected(true);
		panelCampos.add(btnVoltar, "cell 2 8,push ,grow");
		btnVoltar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onBack();
			}
		});
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void onBack(){
		this.dispose();
		new LoginView();
	}
	@SuppressWarnings("deprecation")
	public void onCad(){
		int result=0,resp;
		if(txtEmail.getText().equals("") || txtNome.getText().equals("") || txtNome_user.getText().equals("") || txtRe.getText().equals("") || txtSenha1.getText().equals("") || txtSenha2.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Preencha todos os campos.","Erro",JOptionPane.ERROR_MESSAGE);
		}else if(txtSenha1.getText().equals(txtSenha2.getText())){
			Funcionario func=new Funcionario();
			func.setEmail_func(txtEmail.getText());
			func.setFuncao_func(listaFunc.getSelectedItem().toString());
			func.setNome_func(txtNome.getText());
			func.setNome_user(txtNome_user.getText());
			func.setRe_func(txtRe.getText());
			func.setSenha_user(txtSenha1.getText());
			func.setStatus_func("T");
			result=new FuncController().addFunc(func);
			if(result==0){
				JOptionPane.showMessageDialog(null, "Já existe este usuário no sistema.","Erro",JOptionPane.ERROR_MESSAGE);
			}else{
				resp=JOptionPane.showConfirmDialog(null, "Usuário cadastrado com sucesso.\nDeseja continuar?","Sucesso",JOptionPane.YES_NO_OPTION);
				if(resp==1){
					this.dispose();
					new LoginView();
				}else{
					onClean();
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "As senhas não conferem.","Erro de senha.",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void onClean(){
		txtEmail.setText("");
		txtNome.setText("");
		txtNome_user.setText("");
		txtRe.setText("");
		txtSenha1.setText("");
		txtSenha2.setText("");
	}
}
