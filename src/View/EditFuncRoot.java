package View;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import Bean.Funcionario;
import Controller.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class EditFuncRoot extends JDialog{
	Funcionario func = new Funcionario();
	private MaskFormatter ftmRe = null;
	private JTextField nome_func;
	private JTextField re_func;
	private String[] funcao={"Função A","Função B","Função C","Função D"};
	@SuppressWarnings("rawtypes")
	private JComboBox funcao_func;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditFuncRoot(String nome,String funcao_funci, String re, int cod, Frame parent, boolean modal) {
		super(parent,modal);
		getContentPane().setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(EditFuncRoot.class.getResource("/Img/icon.ico")));
		setTitle("Editar Funcion\u00E1rio");
		setResizable(true);
		setModal(true);
		this.setMinimumSize(new Dimension(450, 300));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 414, 239);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNomeFuncionrio = new JLabel("Funcion\u00E1rio:");
		lblNomeFuncionrio.setForeground(Color.GRAY);
		lblNomeFuncionrio.setBackground(Color.WHITE);
		lblNomeFuncionrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeFuncionrio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNomeFuncionrio.setBounds(10, 11, 136, 27);
		panel.add(lblNomeFuncionrio);
		
		nome_func = new JTextField();
		nome_func.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		nome_func.setBounds(145, 11, 259, 27);
		nome_func.setText(nome);
		panel.add(nome_func);
		nome_func.setColumns(10);
		JButton btnAlterar = new JButton("");
		btnAlterar.setIcon(new ImageIcon(EditFuncRoot.class.getResource("/Img/button_alterar.png")));
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result=0,resp=0;
				if(nome_func.equals("") || re_func.equals("")){
					JOptionPane.showMessageDialog(null, "Preencha todos os campos.");
				}else{
					func.setCod_user(cod);
					func.setNome_func(nome_func.getText());
					func.setRe_func(re_func.getText());
					func.setFuncao_func(funcao_func.getSelectedItem().toString());
					result=new FuncController().alteraFunc(func);
				}
				if(result==1){
					resp=JOptionPane.showConfirmDialog(null, "Usuário alterado com sucesso.\nDeseja continuar?","Sucesso",JOptionPane.YES_NO_OPTION);
					if(resp==1){
						onSair();
					}else{
						nome_func.setText(func.getNome_func());
						re_func.setText(func.getRe_func());
						funcao_func.setSelectedItem(func.getFuncao_func());
					}
				}else{
					JOptionPane.showMessageDialog(null, "Erro ao alterar.");
				}
			}
		});
		btnAlterar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnAlterar.setBounds(140, 187, 144, 26);
		panel.add(btnAlterar);
		
		try {
			ftmRe = new MaskFormatter("#######");
			ftmRe.setValidCharacters("0123456789");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		re_func = new JFormattedTextField(ftmRe);
		re_func.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		re_func.setBounds(145, 67, 259, 27);
		re_func.setColumns(10);
		re_func.setText(re);
		panel.add(re_func);
		
		
		JLabel lblRegistroDoEmpregado = new JLabel("Registro:");
		lblRegistroDoEmpregado.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroDoEmpregado.setForeground(Color.GRAY);
		lblRegistroDoEmpregado.setBackground(Color.GRAY);
		lblRegistroDoEmpregado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblRegistroDoEmpregado.setBounds(10, 67, 136, 27);
		panel.add(lblRegistroDoEmpregado);
		
		
		funcao_func= new JComboBox(funcao);
		funcao_func.setBackground(Color.GRAY);
		funcao_func.setBounds(145, 120, 259, 20);
		funcao_func.setSelectedItem(funcao_funci);
		panel.add(funcao_func);
		
		JLabel lblFuno = new JLabel("Fun\u00E7\u00E3o:");
		lblFuno.setForeground(Color.GRAY);
		lblFuno.setBackground(Color.GRAY);
		lblFuno.setHorizontalAlignment(SwingConstants.CENTER);
		lblFuno.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFuno.setBounds(20, 105, 121, 45);
		panel.add(lblFuno);
		
		setLocationRelativeTo(null);
	}
	public void onSair(){
		this.dispose();
	}
}
