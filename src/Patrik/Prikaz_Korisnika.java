package Patrik;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Prikaz_Korisnika extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JTextArea textAreaPrikazKorisnika;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Prikaz_Korisnika dialog = new Prikaz_Korisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void newscreen() {
		try {
			Prikaz_Korisnika dialog = new Prikaz_Korisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Prikaz_Korisnika() {
		setTitle("Prikaz Korisnika");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 414, 217);
		contentPanel.add(scrollPane);
		{
			textAreaPrikazKorisnika = new JTextArea();
			scrollPane.setViewportView(textAreaPrikazKorisnika);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Close");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		selectPrikazKorisnika();
		
	}
	private void selectPrikazKorisnika() {
		// TODO Auto-generated method stub
		try {
		  	  Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			  Connection conn = 
		DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/pmocibob?user=pmocibob&password=11");
			  
			  String sql = "SELECT * FROM Korisnik";
			  java.sql.Statement stmt = conn.createStatement();
			  ResultSet rs = stmt.executeQuery(sql);
			  String tekst = "";
			  while (rs.next()) {
				tekst += "Ime: "+rs.getString("Ime_Korisnika")+"\t";				
				tekst += "Prezime: "+rs.getString("Prezime_Korisnika")+"\t";
				tekst += "Email: "+rs.getString("Email_Korisnika")+"\t";
				tekst += "Telefon: "+rs.getString("Telefon_Korisnika")+"\n";
			  }	
			  textAreaPrikazKorisnika.setText(tekst);
			  conn.close();
			
		} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),"Greška",
		 JOptionPane.ERROR_MESSAGE);
			}
		}

	}

