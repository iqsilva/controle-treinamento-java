package View;
import java.awt.*;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;

import org.jdatepicker.impl.*;

import Bean.Edicao;
import Controller.EdicaoController;
import Model.*;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class NAEdicaoRoot extends JDialog{
	private JDatePickerImpl datePicker;
	ManipulateDates md=new ManipulateDates();
	public NAEdicaoRoot(Frame parent, boolean b, Date dataInicio, String desc,int cod_curso,int cod_edicao){
		super(parent, b);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NAEdicaoRoot.class.getResource("/Img/icon.ico")));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Edi\u00E7\u00E3o do Curso");
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		setModal(true);
		setSize(370,220);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel(desc);
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 344, 39);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 61, 344, 52);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		DateLabelFormatter d = new DateLabelFormatter();
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		model.setValue(dataInicio);
		Properties p = new Properties();
		p.put("text.today", "Dia");
		p.put("text.month", "Mês");
		p.put("text.year", "Ano");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel,d);
		datePicker.setBounds(138, 11, 196, 23);
		panel.add(datePicker);
		
		JLabel lblDataDeIncio = new JLabel("Data de In\u00EDcio:");
		lblDataDeIncio.setForeground(Color.GRAY);
		lblDataDeIncio.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataDeIncio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDataDeIncio.setBounds(10, 11, 118, 23);
		panel.add(lblDataDeIncio);
		
		JButton btnSalvar = new JButton("");
		btnSalvar.setIcon(new ImageIcon(NAEdicaoRoot.class.getResource("/Img/button_salvar.png")));
		btnSalvar.setContentAreaFilled(false);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dataInicio==null){
					onSave(cod_curso);
				}else{
					if(cod_edicao==0){
						
					}else{
						onAlt(cod_curso,cod_edicao);
					}
				}
			}
		});
		btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnSalvar.setBounds(121, 136, 117, 34);
		getContentPane().add(btnSalvar);
	}
	public void onSave(int cod_curso){
		Date selectedDate=(Date) datePicker.getModel().getValue();
		int result=0;
		if(datePicker==null){
			JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.","Erro",JOptionPane.ERROR_MESSAGE);
		}else{
			if(selectedDate.compareTo(new Date())<=0){
				JOptionPane.showMessageDialog(null, "Data Inválida.","Erro",JOptionPane.ERROR_MESSAGE);
			}else{
				DateFormat df = new SimpleDateFormat("dd/MM/YYYY");//Cria um formato de data
				String reportDate = df.format(selectedDate);
				Edicao edic = new Edicao();
				edic.setCod_Curso(cod_curso);
				edic.setData_Inicio(reportDate);
				result=new EdicaoController().addEdicao(edic);
				if(result==0){
					JOptionPane.showMessageDialog(null, "Um curso já vai começar esse dia.","Erro",JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Edição cadastrada com sucesso.");
					this.dispose();
				}
			}
		}
	}
	public void onAlt(int cod_curso, int cod_edicao){
		Date selectedDate=(Date) datePicker.getModel().getValue();
		int result=0;
		if(datePicker==null){
			JOptionPane.showMessageDialog(null, "Preencha os campos corretamente.","Erro",JOptionPane.ERROR_MESSAGE);
		}else{
			if(selectedDate.compareTo(new Date())<=0){
				JOptionPane.showMessageDialog(null, "Data Inválida.","Erro",JOptionPane.ERROR_MESSAGE);
			}else{
				DateFormat df = new SimpleDateFormat("dd/MM/YYYY");//Cria um formato de data
				String reportDate = df.format(selectedDate);
				Edicao edic=new Edicao();
				edic.setCod_Curso(cod_curso);
				edic.setData_Inicio(reportDate);
				edic.setCod_Edicao(cod_edicao);
				result=new EdicaoController().altEdicao(edic);
				if(result==0){
					JOptionPane.showMessageDialog(null, "Um curso já vai começar esse dia.","Erro",JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Edição cadastrada com sucesso.");
					this.dispose();
				}
			}
		}
	}
}
