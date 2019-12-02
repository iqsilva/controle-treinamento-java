package View;
import java.awt.Frame;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Properties;
import Controller.*;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import org.jdatepicker.impl.*;
import Bean.Curso;
//import Model.*;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
@SuppressWarnings("serial")
public class NACursoRoot extends JDialog{
	private JTextField txtDesc;
	private JTextField txtCarga;
	//private JDatePickerImpl datePicker;
	//ManipulateDates md=new ManipulateDates();
	private JTextField txtVig;
	public NACursoRoot(Frame parent, boolean modal,String descricao,int dataVigencia,int cargaHora,int codigoCurso){
		super(parent, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(NACursoRoot.class.getResource("/Img/icon.ico")));
		setTitle("Cadastro do Curso");
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}catch(Exception e){
			e.printStackTrace();
		}
		setModal(true);
		setResizable(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setMinimumSize(new Dimension(340,220));
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 332, 178);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panelBt = new JPanel();
		panelBt.setBackground(Color.WHITE);
		panelBt.setBounds(0, 0, 314, 127);
		panel.add(panelBt);
		panelBt.setLayout(null);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setForeground(Color.GRAY);
		lblDescrio.setBounds(10, 6, 96, 24);
		panelBt.add(lblDescrio);
		lblDescrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		txtDesc= new JTextField();
		txtDesc.setBounds(87, 8, 227, 27);
		txtDesc.setText(descricao);
		panelBt.add(txtDesc);
		txtDesc.setColumns(10);
		
		JLabel lblCargaHorria = new JLabel("Carga Hor\u00E1ria:");
		lblCargaHorria.setHorizontalAlignment(SwingConstants.LEFT);
		lblCargaHorria.setForeground(Color.GRAY);
		lblCargaHorria.setBounds(10, 46, 107, 24);
		panelBt.add(lblCargaHorria);
		lblCargaHorria.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		
		txtCarga = new JTextField();
		txtCarga.setBounds(116, 49, 86, 24);
		txtCarga.setText(String.valueOf(cargaHora));
		panelBt.add(txtCarga);
		txtCarga.setColumns(10);
		
		JLabel lblDias = new JLabel("dias");
		lblDias.setForeground(Color.GRAY);
		lblDias.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblDias.setBounds(212, 49, 73, 24);
		panelBt.add(lblDias);
		
		txtVig = new JTextField();
		txtVig.setBounds(79, 81, 123, 24);
		txtVig.setText(String.valueOf(dataVigencia));
		panelBt.add(txtVig);
		txtVig.setColumns(10);
		
		JLabel lblAnos = new JLabel("anos");
		lblAnos.setForeground(Color.GRAY);
		lblAnos.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblAnos.setBounds(207, 81, 46, 24);
		panelBt.add(lblAnos);
		
		JLabel lblVigncia = new JLabel("Vig\u00EAncia:");
		lblVigncia.setForeground(Color.GRAY);
		lblVigncia.setHorizontalAlignment(SwingConstants.LEFT);
		lblVigncia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblVigncia.setBounds(10, 81, 96, 24);
		panelBt.add(lblVigncia);
		
		/*DateLabelFormatter d = new DateLabelFormatter();
		JPanel pnlDate = new JPanel();
		pnlDate.setBounds(0, 71, 322, 49);
		panel.add(pnlDate);
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		model.setValue(dataVigencia);
		
		Properties p = new Properties();
		p.put("text.today", "Dia");
		p.put("text.month", "Mês");
		p.put("text.year", "Ano");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		
		pnlDate.setLayout(null);
		datePicker = new JDatePickerImpl(datePanel,d);
		
		datePicker.setBounds(116, 11, 196, 23);
		pnlDate.add(datePicker);
		
		GridBagConstraints gbc_datePicker = new GridBagConstraints();
		gbc_datePicker.insets = new Insets(0, 0, 5, 0);
		gbc_datePicker.gridx = 3;
		gbc_datePicker.gridy = 0;

		JLabel lblVigencia = new JLabel("Vigencia:");
		lblVigencia.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVigencia.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblVigencia.setBounds(0, 11, 106, 23);
		pnlDate.add(lblVigencia);*/
	
		JButton btnSalvar = new JButton("");
		btnSalvar.setContentAreaFilled(false);
		btnSalvar.setIcon(new ImageIcon(NACursoRoot.class.getResource("/Img/button_salvar.png")));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(codigoCurso==0){
					onSave();
				}else{
					onAlt(codigoCurso);
				}
				
			}
		});
		btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		btnSalvar.setBounds(110, 138, 113, 29);
		panel.add(btnSalvar);
	}
	public void onSave(){
		//Date selectedDate=(Date) datePicker.getModel().getValue();//Pega a data JDATEPICKER
		int carga=0;
		int vigencia=0;
		String desc="";
		int result=0;
		try{
			if(txtVig.equals("") || txtDesc.equals("") || txtCarga.equals("")){
				JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
			}else{
				Curso curso=new Curso();
				vigencia=Integer.parseInt(txtVig.getText());
				carga=Integer.parseInt(txtCarga.getText());
				if(carga==0 || vigencia==0){
					JOptionPane.showMessageDialog(null, "Tanto carga quanto a vigência devem ser maiores do que 0");
					return;
				}
				desc=txtDesc.getText();
				//DateFormat df = new SimpleDateFormat("dd/MM/YYYY");//Cria um formato de data
				//String reportDate = df.format(selectedDate);//pega o tipo Date converte para o formato anterior e seta como string
				curso.setCarga_horaria(carga);
				curso.setDesc_curso(desc);
				curso.setVigencia(vigencia);
				//curso.setVigencia(reportDate);
				result= new CursoController().addCurso(curso);
				if(result==0){
					JOptionPane.showMessageDialog(null, "Já existe este curso no sistema.","Erro",JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso.");
					this.dispose();
				}
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "A carga horária deve ser em horas.");
		}
	}
	public void onAlt(int codigo){
		//Date selectedDate=(Date) datePicker.getModel().getValue();//Pega a data JDATEPICKER
		int carga=0;
		int vigencia=0;
		String desc="";
		int result=0;
		try{
			if(txtVig.equals("") || txtDesc.equals("") || txtCarga.equals("")){
				JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
			}else{
				Curso curso=new Curso();
				vigencia=Integer.parseInt(txtVig.getText());
				carga=Integer.parseInt(txtCarga.getText());
				if(carga==0 || vigencia==0){
					JOptionPane.showMessageDialog(null, "Tanto carga quanto a vigência devem ser maiores do que 0");
					return;
				}
				desc=txtDesc.getText();
				//DateFormat df = new SimpleDateFormat("dd/MM/YYYY");//Cria um formato de data
				//String reportDate = df.format(selectedDate);//pega o tipo Date converte para o formato anterior e seta como string
				curso.setCarga_horaria(carga);
				curso.setDesc_curso(desc);
				curso.setVigencia(vigencia);
				//curso.setVigencia(reportDate);
				curso.setCod_curso(codigo);
				result=new CursoController().alteraCurso(curso);
				if(result==0){
					JOptionPane.showMessageDialog(null, "Esse curso já existe","Erro",JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Curso alterado com sucesso.");
					this.dispose();
				}
			}
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "A carga horária deve ser em horas.");
		}
	}
}