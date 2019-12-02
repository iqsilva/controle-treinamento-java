package View;
import javax.swing.*;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.border.TitledBorder;
import java.awt.Color;
import Bean.*;
import Model.ParticipaDao;
import Table.PAprovadoTableModel;
import Table.PConcluidoTableModel;
import Table.PEsperaTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
@SuppressWarnings("serial")
public class MeusCursosUserView extends JPanel{
	private JTable tbl1,tbl2,tbl3;
	private JScrollPane sp1,sp2,sp3;
	private List<Participa> espera;
	private List<Participa> concluido;
	private List<Participa> aprovado;
	private JPanel panel;
	public MeusCursosUserView() {
		setBackground(Color.WHITE);
		setSize(522,458);
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Espera", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		tbl1=new JTable();
		tbl1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		sp1=new JScrollPane(tbl1);
		panel1.add(sp1);
		refreshT1();
		setLayout(new MigLayout("", "[502px]", "[120px][120px][144px][27px]"));
		add(panel1, "cell 0 0,grow,push");
		panel1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Cursando", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		tbl2=new JTable();
		tbl2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		sp2=new JScrollPane(tbl2);
		panel2.add(sp2);
		refreshT2();
		add(panel2, "cell 0 1,grow,push");
		panel2.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.WHITE);
		panel3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Conclu\u00EDdo", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 102, 204)));
		panel3.setLayout(new GridLayout(0, 1, 0, 0));
		tbl3=new JTable();
		tbl3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		sp3=new JScrollPane(tbl3);
		panel3.add(sp3);
		refreshT3();
		add(panel3, "cell 0 2,grow,push");
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		add(panel, "cell 0 3,growx,pushx");
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnAtualizar = new JButton("");
		btnAtualizar.setBackground(new Color(255, 255, 255));
		btnAtualizar.setContentAreaFilled(false);
		btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		panel.add(btnAtualizar);
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshT1();
				refreshT2();
				refreshT3();
			}
		});
		btnAtualizar.setIcon(new ImageIcon(MeusCursosUserView.class.getResource("/Img/button_atualizar.png")));
	}
	public void refreshT1(){
		espera=new ParticipaDao().findE("E",Sessao.getCod_user());
		tbl1.setModel(new PEsperaTableModel(espera));
	}
	public void refreshT2(){
		aprovado=new ParticipaDao().findA("A", Sessao.getCod_user());
		tbl2.setModel(new PAprovadoTableModel(aprovado));
	}
	public void refreshT3(){
		concluido=new ParticipaDao().findC("C", Sessao.getCod_user());
		tbl3.setModel(new PConcluidoTableModel(concluido));
	}
}