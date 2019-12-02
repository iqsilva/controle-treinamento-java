package View;
import java.util.List;

import javax.swing.*;

import Bean.Curso;
import Bean.Edicao;
import Bean.Participa;
import Bean.Sessao;
import Controller.EdicaoController;
import Controller.ParticipaController;
import Model.CursoDao;
import Model.EdicaoDao;
import Table.CursoTableModel;
import Table.EdicaoTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
@SuppressWarnings("serial")
public class CursoUserView extends JPanel{
	private JTable table1;
	private JScrollPane scrollPane1;
	private List<Curso> cursoList;
	private List<Edicao> edicaoList;
	private JScrollPane scrollPane2;
	private JTable table2;
	public CursoUserView() {
		setBackground(Color.WHITE);
		setSize(530,460);
		JPanel panelCursos = new JPanel();
		panelCursos.setBackground(Color.WHITE);
		panelCursos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cursos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		panelCursos.setLayout(new GridLayout(0, 1, 0, 0));
		
		table1=new JTable();
		table1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		table1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {RefreshT2();}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		setLayout(new MigLayout("", "[512px]", "[178px][189px][45px]"));
		scrollPane1=new JScrollPane(table1);
		scrollPane1.setSize(200,200);
	
		scrollPane1.setBounds(0, 154, 539, -154);
		panelCursos.add(scrollPane1);
		
		add(panelCursos, "cell 0 0,grow,push");
	
		JPanel panelEdicao = new JPanel();
		panelEdicao.setBackground(Color.WHITE);
		panelEdicao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Edi\u00E7\u00F5es", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		
		table2=new JTable();
		table2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		scrollPane2=new JScrollPane(table2);
		add(panelEdicao, "cell 0 1,grow");
		panelEdicao.setLayout(new GridLayout(0, 1, 0, 0));
		panelEdicao.add(scrollPane2);
	
		JPanel panelBt = new JPanel();
		panelBt.setBackground(Color.WHITE);
		add(panelBt, "cell 0 2,growx,pushx");
		
		JButton btnParticipar = new JButton("");
		btnParticipar.setContentAreaFilled(false);
		btnParticipar.setBackground(new Color(255, 255, 255));
		btnParticipar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnParticipar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onCadastrar();
			}
		});
		panelBt.setLayout(new GridLayout(0, 1, 0, 0));
		btnParticipar.setIcon(new ImageIcon(CursoUserView.class.getResource("/Img/button_participar.png")));
		panelBt.add(btnParticipar);
		RefreshT1();
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
	private void onCadastrar() {
		int rowIndex=table2.getSelectedRow();
		int rowIndex1=table1.getSelectedRow();
		int result=0;
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione uma edição para participar.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}else{
			int confirm=JOptionPane.showConfirmDialog(null, "Deseja fazer este curso?", "Excluir Curso",JOptionPane.YES_NO_OPTION);
			if(confirm != 0){
				return;
			}	
			Edicao edic = new EdicaoTableModel(edicaoList).get(rowIndex);
			Curso cursos=new CursoTableModel(cursoList).get(rowIndex1);
			Participa part= new Participa();
			part.setCod_func(Sessao.getCod_user());
			part.setCod_edicao(edic.getCod_Edicao());
			part.setStatus("E");
			part.setCod_curso(cursos.getCod_curso());
			result=new ParticipaController().solicitaPart(part,cursos.getCod_curso());
			if(result==1){
				JOptionPane.showMessageDialog(null, "Uma solicitação fora enviada.\n Aguarde aprovação!");
			}else{
				JOptionPane.showMessageDialog(null, "Você já solicitou este curso!","Erro",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}

