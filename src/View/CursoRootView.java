package View;
import java.awt.*;
import java.util.List;
import Table.*;
import Controller.*;
import Model.CursoDao;
import Model.ManipulateDates;

import javax.swing.*;
import Bean.Curso;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;
@SuppressWarnings("serial")
public class CursoRootView extends JPanel{
	ManipulateDates md = new ManipulateDates();
	Curso curso=new Curso();
	private JTable table;
	private JScrollPane scrollPane;
	private List<Curso> cursoList;
	public CursoRootView() {
		setBackground(Color.WHITE);
		setSize(511, 427);		
		setLayout(new MigLayout("", "[491px]", "[340px][43px]"));
		JPanel pnlBut = new JPanel();
		pnlBut.setBackground(Color.WHITE);
		add(pnlBut, "cell 0 1,growx,pushx");
		
		JButton btnNovoCurso = new JButton("");
		btnNovoCurso.setContentAreaFilled(false);
		btnNovoCurso.setIcon(new ImageIcon(CursoRootView.class.getResource("/Img/button_novo.png")));
		btnNovoCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onNewCurso();
				refreshTable();
			}
		});
		pnlBut.setLayout(new MigLayout("", "[165px][165px][165px]", "[69px]"));
		btnNovoCurso.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlBut.add(btnNovoCurso, "cell 0 0,grow,push");
		
		JButton btnAlterar = new JButton("");
		btnAlterar.setContentAreaFilled(false);
		btnAlterar.setIcon(new ImageIcon(CursoRootView.class.getResource("/Img/button_alterar.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAlterar();
				refreshTable();
			}
		});
		btnAlterar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlBut.add(btnAlterar, "cell 1 0,growx,pushx");
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setIcon(new ImageIcon(CursoRootView.class.getResource("/Img/button_excluir.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onRemove();
				
			}
		});
		btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 15));
		pnlBut.add(btnExcluir, "cell 2 0,growx,pushx");
		
		JPanel pnlView = new JPanel();
		pnlView.setForeground(new Color(0, 0, 0));
		pnlView.setBackground(new Color(255, 255, 255));
		pnlView.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cursos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		pnlView.setLayout(new GridLayout(0, 1, 0, 0));
		
		table=new JTable();
		table.setFont(new Font("Segoe UI Historic", Font.PLAIN, 15));
		scrollPane=new JScrollPane(table);
		pnlView.add(scrollPane);
		refreshTable();
		add(pnlView, "cell 0 0,grow,push");
		
	}
	public void refreshTable(){
		cursoList=new CursoDao().findAll();
		if(curso!=null){
			table.setModel(new CursoTableModel(cursoList));
		}
	}
	public void onNewCurso(){
		NACursoRoot n = new NACursoRoot(new PanelRootView(),true,"",0,0,0);
		n.setVisible(true);
	}
	public void onAlterar(){
		int rowIndex=table.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione o curso para altera-lo.");
			return;
		}
		Curso cursos=new CursoTableModel(cursoList).get(rowIndex);
		NACursoRoot na=new NACursoRoot(new PanelRootView(), true, cursos.getDesc_curso(),cursos.getVigencia(), cursos.getCarga_horaria(),cursos.getCod_curso());
		na.setVisible(true);
	}
	public void onRemove(){
		int result=0;
		int rowIndex=table.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione o curso para remover.");
			return;
		}
		Curso cursos=new CursoTableModel(cursoList).get(rowIndex);
		int confirm=JOptionPane.showConfirmDialog(null, "Deseja excluir este curso?", "Excluir Curso",JOptionPane.YES_NO_OPTION);
		if(confirm != 0){
			return;
		}		
		result=new CursoController().removerCurso(cursos.getCod_curso());
		if(result==1){
			JOptionPane.showMessageDialog(null, "Curso removido com sucesso");
			refreshTable();
		}else{
			JOptionPane.showMessageDialog(null, "Tente Novamente","Erro", JOptionPane.ERROR_MESSAGE);
			refreshTable();
		}
	}
}

