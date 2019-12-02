package View;
import java.util.List;
import javax.swing.*;
import Bean.Curso;
import Bean.Edicao;
import Controller.EdicaoController;
import Model.*;
import Table.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;

import net.miginfocom.swing.MigLayout;
@SuppressWarnings("serial")
public class EdicaoRootView extends JPanel{
	Curso curso=new Curso();
	private JTable table1;
	private JScrollPane scrollPane1;
	private List<Curso> cursoList;
	private List<Edicao> edicaoList;
	private JPanel pnlBut;
	private JButton btnNovaEdio;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JPanel pnlTable2;
	private JScrollPane scrollPane2;
	private JTable table2;
	private JButton btnCarCurso;
	public EdicaoRootView(){
		setBackground(new Color(255, 255, 255));
		JPanel pnlTable1 = new JPanel();
		pnlTable1.setBackground(new Color(255, 255, 255));
		pnlTable1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cursos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		setSize(557, 457);
		pnlTable1.setLayout(new GridLayout(0, 1, 0, 0));
		
		table1=new JTable();
		table1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {RefreshT2();}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		scrollPane1=new JScrollPane(table1);
		scrollPane1.setBounds(0, 154, 539, -154);
		pnlTable1.add(scrollPane1);
		RefreshT1();
		setLayout(new MigLayout("", "[485px]", "[156px][42px][180px]"));
		add(pnlTable1, "cell 0 0,grow,push");
		
		pnlBut = new JPanel();
		pnlBut.setBackground(new Color(255, 255, 255));
		add(pnlBut, "cell 0 1,growx,pushx");
		
		btnNovaEdio = new JButton("");
		btnNovaEdio.setBackground(UIManager.getColor("240,240,240"));
		btnNovaEdio.setContentAreaFilled(false);
		btnNovaEdio.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnNovaEdio.setIcon(new ImageIcon(EdicaoRootView.class.getResource("/Img/button_novo.png")));
		btnNovaEdio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = onNew();
				if(i==0){
					
				}else{
					RefreshT2();
				}
				
			}
		});
		pnlBut.setLayout(new GridLayout(0, 4, 0, 0));
		pnlBut.add(btnNovaEdio);
		
		btnExcluir = new JButton("");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnExcluir.setIcon(new ImageIcon(EdicaoRootView.class.getResource("/Img/button_excluir.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onRemove();
			}
		});
		pnlBut.add(btnExcluir);
		
		btnAlterar = new JButton("");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAlterar.setIcon(new ImageIcon(EdicaoRootView.class.getResource("/Img/button_alterar.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAlt();
			}
		});
		pnlBut.add(btnAlterar);
		
		btnCarCurso = new JButton("");
		btnCarCurso.setForeground(new Color(255, 255, 255));
		btnCarCurso.setContentAreaFilled(false);
		btnCarCurso.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCarCurso.setIcon(new ImageIcon(EdicaoRootView.class.getResource("/Img/button_atualizar.png")));
		btnCarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RefreshT1();
			}
		});
		pnlBut.add(btnCarCurso);
		
		pnlTable2 = new JPanel();
		pnlTable2.setBackground(new Color(255, 255, 255));
		pnlTable2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Edi\u00E7\u00F5es", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		pnlTable2.setLayout(new GridLayout(0, 1, 0, 0));
		
		table2=new JTable();
		scrollPane2 = new JScrollPane(table2);
		pnlTable2.add(scrollPane2);		
		add(pnlTable2, "cell 0 2,grow,push");
	}
	public void RefreshT1(){
		cursoList=new CursoDao().findAll();
		table1.setModel(new CursoTableModel(cursoList));
	}
	public void RefreshT2(){
		int rowIndex=table1.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione um curso.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}else{
			Curso cursos=new CursoTableModel(cursoList).get(rowIndex);
			new EdicaoController().AtualizarEd(cursos.getCod_curso());
			
			edicaoList=new EdicaoDao().findAll(cursos.getCod_curso());
			table2.setModel(new EdicaoTableModel(edicaoList));
		}
		
	}
	private int onNew() {
		int result=0;
		int rowIndex=table1.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione um curso.","Erro",JOptionPane.ERROR_MESSAGE);
			result=0;
		}else{
			Curso cursos=new CursoTableModel(cursoList).get(rowIndex);
			NAEdicaoRoot na=new NAEdicaoRoot(new PanelRootView(), true, null, cursos.getDesc_curso(),cursos.getCod_curso(),0);
			na.setVisible(true);
			result=1;
		}
		return result;
	}
	private void onAlt(){
		ManipulateDates md= new ManipulateDates();
		int rowIndex=table1.getSelectedRow();
		int rowIndex2=table2.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione um curso.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(rowIndex2==-1){
			JOptionPane.showMessageDialog(null, "Selecione uma Edição.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Curso cursos=new CursoTableModel(cursoList).get(rowIndex);
		Edicao edic=new EdicaoTableModel(edicaoList).get(rowIndex2);
		NAEdicaoRoot na=new NAEdicaoRoot(new PanelRootView(), true,md.StrToDate(edic.getData_Inicio()), cursos.getDesc_curso(),cursos.getCod_curso(),edic.getCod_Edicao());
		na.setVisible(true);
		RefreshT2();
	}
	public void onRemove(){
		int result=0;
		int rowIndex=table1.getSelectedRow();
		int rowIndex2=table2.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione um curso.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(rowIndex2==-1){
			JOptionPane.showMessageDialog(null, "Selecione uma Edição.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Edicao edic=new EdicaoTableModel(edicaoList).get(rowIndex2);
		int confirm=JOptionPane.showConfirmDialog(null, "Deseja excluir esta edição?", "Excluir Curso",JOptionPane.YES_NO_OPTION);
		if(confirm!=0){
			return;
		}
		System.out.println(edic.getCod_Edicao());
		result=new EdicaoDao().remove(edic.getCod_Edicao());
		if(result==1){
			JOptionPane.showMessageDialog(null, "Edição removida com sucesso");
			RefreshT2();
		}else{
			JOptionPane.showMessageDialog(null, "Tente Novamente","Erro", JOptionPane.ERROR_MESSAGE);
			RefreshT2();
		}
	}
}
