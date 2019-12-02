package View;
import javax.swing.*;
import java.awt.GridLayout;
import java.util.List;
import Bean.*;
import Controller.ParticipaController;
import Model.ParticipaDao;
import Table.PEsperaTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
@SuppressWarnings("serial")
public class SolicitacaoRootView extends JPanel{
	private JTable tbl1,tbl2;
	private JScrollPane slp1,slp2;
	private List<Participa> esperaList;
	private List<Participa> aprovaList;
	public SolicitacaoRootView() {
		setBackground(Color.WHITE);
		setSize(521,447);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cursos em Espera", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		tbl1=new JTable();
		tbl1.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {tbl2.clearSelection();}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		slp1=new JScrollPane(tbl1);
		panel1.add(slp1);
		refresh1();
		setLayout(new MigLayout("", "[502px]", "[187px][47px][175px]"));
		add(panel1, "cell 0 0,grow,push");
		panel1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panelBt = new JPanel();
		panelBt.setBackground(Color.WHITE);
		add(panelBt, "cell 0 1,growx,pushx");
		
		JButton btnOk = new JButton("");
		btnOk.setContentAreaFilled(false);
		btnOk.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				onAprova();
			}
		});
		panelBt.setLayout(new GridLayout(0, 3, 0, 0));
		btnOk.setIcon(new ImageIcon(SolicitacaoRootView.class.getResource("/Img/button_aprovar.png")));
		panelBt.add(btnOk);
		
		JButton btnAtu = new JButton("");
		btnAtu.setContentAreaFilled(false);
		btnAtu.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh1();
				refresh2();
			}
		});
		
		JButton btnCancel = new JButton("");
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tbl1.getSelectedRowCount()!=0){
					onReprova1();
				}else if(tbl2.getSelectedRowCount()!=0){
					onReprova2();
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um funcionário para reprovar!","Erro",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnCancel.setIcon(new ImageIcon(SolicitacaoRootView.class.getResource("/Img/button_reprovar.png")));
		panelBt.add(btnCancel);
		btnAtu.setIcon(new ImageIcon(SolicitacaoRootView.class.getResource("/Img/button_atualizar.png")));
		panelBt.add(btnAtu);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cursos Aprovados", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		add(panel2, "cell 0 2,grow,push");
		panel2.setLayout(new GridLayout(0, 1, 0, 0));
		tbl2=new JTable();
		tbl2.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {tbl1.clearSelection();}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		slp2=new JScrollPane(tbl2);
		refresh2();
		panel2.add(slp2);
	}
	
	//métodos
	public void refresh1() {
		esperaList=new ParticipaDao().findE("E",0);
		tbl1.setModel(new PEsperaTableModel(esperaList));
	}
	public void onAprova(){
		int rowIndex = tbl1.getSelectedRow();
		if(rowIndex==-1){
			JOptionPane.showMessageDialog(null, "Selecione algo para aprovar.","Erro",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Participa part=new PEsperaTableModel(esperaList).get(rowIndex);
		int result=new ParticipaController().aprovaPart(part.getCod_participa(), "A");
		if(result==1){
			JOptionPane.showMessageDialog(null, "Aprovado");
			refresh1();
			refresh2();
		}else{
			JOptionPane.showMessageDialog(null, "Tente Novamente","Erro", JOptionPane.ERROR_MESSAGE);
			refresh1();
			refresh2();
		}
	}
	public void refresh2(){
		aprovaList=new ParticipaDao().findE("A",0);
		tbl2.setModel(new PEsperaTableModel(aprovaList));
	}
	private void onReprova1(){
		Participa part=new PEsperaTableModel(esperaList).get(tbl1.getSelectedRow());
		int confirm=JOptionPane.showConfirmDialog(null, "Deseja reprovar este funcionário?", "Excluir Curso",JOptionPane.YES_NO_OPTION);
		if(confirm != 0){
			return;
		}		
		int result=new ParticipaController().reprovaPart(part.getCod_participa(), "R");
		if(result==1){
			refresh1();
		}else{
			JOptionPane.showMessageDialog(null, "Tente Novamente","Erro", JOptionPane.ERROR_MESSAGE);
			refresh1();
		}
	}
	private void onReprova2(){
		Participa part=new PEsperaTableModel(aprovaList).get(tbl2.getSelectedRow());
		int confirm=JOptionPane.showConfirmDialog(null, "Deseja reprovar este funcionário?", "Excluir Curso",JOptionPane.YES_NO_OPTION);
		if(confirm != 0){
			return;
		}		
		int result=new ParticipaController().reprovaPart(part.getCod_participa(), "R");
		if(result==1){
			refresh2();
		}else{
			JOptionPane.showMessageDialog(null, "Tente Novamente","Erro", JOptionPane.ERROR_MESSAGE);
			refresh2();
		}
	}
}
