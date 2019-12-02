package View;
import java.awt.Dimension;
import java.util.List;
import Table.*;
import Controller.*;
import Model.*;
import javax.swing.*;
import Bean.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.SystemColor;
@SuppressWarnings("serial")
public class FuncRootView extends JPanel{
	Funcionario func=new Funcionario();
	private JTable table;
	private JTable table2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane2;
	private static int cod_func;
	private List<Funcionario> funcList;
	private List<Participa> concluidoList;
	public FuncRootView() {
		setBackground(Color.WHITE);
		
		JPanel panelBt = new JPanel();
		panelBt.setBackground(Color.WHITE);
		
		JButton btnAlterar = new JButton("");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setPreferredSize(new Dimension(33, 30));
		btnAlterar.setIcon(new ImageIcon(FuncRootView.class.getResource("/Img/button_alterar.png")));
		btnAlterar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onAlterar();
				refreshTable();
			}
		});
		panelBt.setLayout(new MigLayout("", "[181.00px][65px][65px]", "[30px]"));
		panelBt.add(btnAlterar, "cell 0 0,push ,grow");
		
		JButton btnVerPerfil = new JButton("");
		btnVerPerfil.setContentAreaFilled(false);
		btnVerPerfil.setPreferredSize(new Dimension(33, 30));
		btnVerPerfil.setIcon(new ImageIcon(FuncRootView.class.getResource("/Img/button_visualizar.png")));
		//btnVerPerfil.setIcon(new ImageIcon(FuncRootView.class.getResource("/Img/find.png")));
		btnVerPerfil.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnVerPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onPerfil();
			}
		});
		panelBt.add(btnVerPerfil, "cell 1 0,grow,push");
		
		JPanel panel1 = new JPanel();
		panel1.setForeground(new Color(0, 0, 0));
		panel1.setBackground(Color.WHITE);
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Funcion\u00E1rios", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		panel1.setLayout(new GridLayout(0, 1, 0, 0));
		table=new JTable();
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane=new JScrollPane(table);
		scrollPane.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(255, 255, 255));
		panel1.add(scrollPane);
		refreshTable();
		setLayout(new MigLayout("", "[491px,grow]", "[204px][35px][139px]"));
		add(panelBt, "cell 0 1,pushx ,grow");
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setPreferredSize(new Dimension(33, 30));
		btnExcluir.setIcon(new ImageIcon(FuncRootView.class.getResource("/Img/button_excluir.png")));
		//btnExcluir.setIcon(new ImageIcon(FuncRootView.class.getResource("/Img/trash.png")));
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onRemove();
			}
		});
		panelBt.add(btnExcluir, "cell 2 0,grow,push");
		add(panel1, "cell 0 0,push ,grow");
		setMinimumSize(new Dimension(500,500));
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cursos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		panel2.setLayout(new GridLayout(0, 1, 0, 0));
		table2=new JTable();
		table2.setBackground(SystemColor.inactiveCaptionBorder);
		table2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane2=new JScrollPane(table2);
		scrollPane2.setBorder(null);
		scrollPane2.setBackground(new Color(255, 255, 255));
		scrollPane2.setForeground(new Color(255, 255, 255));
		panel2.add(scrollPane2);
		add(panel2, "cell 0 2,push ,grow");
		
	}
	public void refreshTable(){
		funcList = new FuncDao().findAll();
		if(funcList!=null){
			table.setModel(new FuncTableModel(funcList));
		}
	}
	public void onRemove(){
		int rowIndex=table.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione o funcionário a ser removido.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Funcionario func=new FuncTableModel(funcList).get(rowIndex);
		int confirm=JOptionPane.showConfirmDialog(null, "Deseja excluir este funcionário?", "Excluir Curso",JOptionPane.YES_NO_OPTION);
		if(confirm != 0){
			return;
		}		
		cod_func=func.getCod_user();
		int result=new FuncController().removeFunc(cod_func);
		if(result==1){
			JOptionPane.showMessageDialog(null, "Funcionário removido com sucesso");
			refreshTable();
		}else{
			JOptionPane.showMessageDialog(null, "Tente Novamente","Erro", JOptionPane.ERROR_MESSAGE);
			refreshTable();
		}
	}
	public void onPerfil(){
		int rowIndex=table.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione o funcionário para ver os cursos.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Funcionario func=new FuncTableModel(funcList).get(rowIndex);
		concluidoList=new ParticipaDao().findC("C", func.getCod_user());
		table2.setModel(new PConcluidoTableModel(concluidoList));
	}
	public void onAlterar(){
		int rowIndex = table.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione o funcionário para altera-lo.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Funcionario funci =new FuncTableModel(funcList).get(rowIndex);
		EditFuncRoot c = new EditFuncRoot(funci.getNome_func(), funci.getFuncao_func(), funci.getRe_func(), funci.getCod_user(),new PanelRootView(),true);
		c.setVisible(true);
	}
}
