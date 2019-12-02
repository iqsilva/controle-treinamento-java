package View;
import javax.swing.*;

import Bean.Funcionario;
import Bean.Sessao;
import Controller.FuncController;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PerfilUserView extends JPanel{
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	public PerfilUserView(){
		setBackground(Color.WHITE);
		setSize(526,463);
		setLayout(new MigLayout("", "[][504px][]", "[187.00][220.00px]"));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PerfilUserView.class.getResource("/Img/logo.png")));
		add(lblNewLabel, "cell 1 0,alignx center,growy");
		
		JPanel panel = new JPanel(null);
		panel.setBackground(Color.WHITE);
		add(panel, "cell 1 1,push ,grow");
		panel.setLayout(new MigLayout("", "[68px][4px][60px][52px][20px][10px][80px][5px][185px]", "[25px][25px][25px][25px][25px][25px][][34px]"));
		
		JLabel lblBemvindo = new JLabel("Usu\u00E1rio:");
		lblBemvindo.setForeground(Color.GRAY);
		lblBemvindo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblBemvindo, "cell 0 0,aligny top");
		
		JLabel lblFuno = new JLabel("Fun\u00E7\u00E3o:");
		lblFuno.setForeground(Color.GRAY);
		lblFuno.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFuno.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblFuno, "cell 0 2,grow");
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		textField_5.setText(Sessao.getFuncao_func());
		textField_5.setEditable(false);
		panel.add(textField_5, "cell 1 2 8 1,growx,aligny center");
		textField_5.setColumns(10);
		
		JLabel lblRegistro = new JLabel("Registro:");
		lblRegistro.setForeground(Color.GRAY);
		lblRegistro.setHorizontalAlignment(SwingConstants.LEFT);
		lblRegistro.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblRegistro, "cell 0 1,grow");
		
		textField = new JTextField();
		textField.setText(Sessao.getNome_func());
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(textField, "cell 1 0 8 1,pushx ,grow");
		textField.setColumns(10);
		
		textField = new JTextField();
		textField.setText(Sessao.getRe_func());
		textField.setEditable(false);
		textField.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(textField, "cell 1 1 8 1,pushx ,growx,aligny center");
		textField.setColumns(10);
		
		JLabel lblDadosEditveis = new JLabel("Dados Edit\u00E1veis");
		lblDadosEditveis.setHorizontalAlignment(SwingConstants.LEFT);
		lblDadosEditveis.setForeground(Color.GRAY);
		lblDadosEditveis.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel.add(lblDadosEditveis, "cell 0 3");
		
		JLabel lblNovaSenha = new JLabel("Nova Senha:");
		lblNovaSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovaSenha.setForeground(Color.GRAY);
		lblNovaSenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNovaSenha, "cell 0 4,grow");
		
		textField_2 = new JPasswordField();
		panel.add(textField_2, "flowx,cell 1 4 8 1,growy");
		textField_2.setColumns(10);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setBackground(new Color(255, 255, 255));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAlt();
			}
		});
		
		JLabel lblRepitaASenha = new JLabel("Repita a Senha:");
		lblRepitaASenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRepitaASenha.setForeground(Color.GRAY);
		lblRepitaASenha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblRepitaASenha, "cell 0 5,alignx left,aligny center");
		
		textField_3 = new JPasswordField();
		panel.add(textField_3, "cell 1 5 8 1,alignx left,grow");
		textField_3.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(Color.GRAY);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblEmail, "cell 0 6,grow");
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_4.setText(Sessao.getEmail_func());
		panel.add(textField_4, "cell 1 6 8 1,growx,aligny center");
		textField_4.setColumns(10);
		btnAlterar.setIcon(new ImageIcon(PerfilUserView.class.getResource("/Img/button_alterar.png")));
		btnAlterar.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		panel.add(btnAlterar, "cell 6 7,pushx ,growx");
	}
	public void onAlt(){
		int result=0;
		if(textField_2.getText().equals("") || textField_3.getText().equals("") || textField_4.getText().equals("")){
			JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(textField_2.getText().equals(textField_3.getText())){
			Funcionario func=new Funcionario();
			func.setCod_user(Sessao.getCod_user());
			func.setEmail_func(textField_4.getText());
			func.setSenha_user(textField_3.getText());
			result=new FuncController().alteraFunc2(func);
			if(result==1){
				JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
				textField_2.setText(func.getSenha_user());
				textField_3.setText(func.getSenha_user());
				textField_4.setText(func.getEmail_func());
				return;
			}else{
				JOptionPane.showMessageDialog(null, "Erro ao alterar.","Erro",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else{
			JOptionPane.showMessageDialog(null, "As senhas não conferem.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}