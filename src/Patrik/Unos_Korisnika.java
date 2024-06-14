package Patrik;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Unos_Korisnika extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldImeKorisnika;
	private JTextField textFieldPrezimeKorisnika;
	private JTextField textFieldEmailKorisnika;
	private JTextField textFieldBrojTelefona;
	private JTextField textFieldLozinka;

	/**
	 * Launch the application.
	 */
	public static void newscreen() {
		try {
			Unos_Korisnika dialog = new Unos_Korisnika();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public Unos_Korisnika() {
		setTitle("Unos Korisnika");
		setBounds(100, 100, 450, 385);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 315);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		textFieldImeKorisnika = new JTextField();
		textFieldImeKorisnika.setBounds(115, 22, 296, 31);
		contentPanel.add(textFieldImeKorisnika);
		textFieldImeKorisnika.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ime Korisnika");
		lblNewLabel.setBounds(26, 30, 79, 14);
		contentPanel.add(lblNewLabel);
		
		textFieldPrezimeKorisnika = new JTextField();
		textFieldPrezimeKorisnika.setColumns(10);
		textFieldPrezimeKorisnika.setBounds(115, 73, 296, 31);
		contentPanel.add(textFieldPrezimeKorisnika);
		
		textFieldEmailKorisnika = new JTextField();
		textFieldEmailKorisnika.setColumns(10);
		textFieldEmailKorisnika.setBounds(115, 124, 296, 31);
		contentPanel.add(textFieldEmailKorisnika);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setColumns(10);
		textFieldBrojTelefona.setBounds(115, 172, 296, 31);
		contentPanel.add(textFieldBrojTelefona);
		
		JLabel lblPrezimeKorisnika = new JLabel("Prezime Korisnika");
		lblPrezimeKorisnika.setBounds(26, 81, 92, 14);
		contentPanel.add(lblPrezimeKorisnika);
		
		JLabel lblEmailKorisnika = new JLabel("Email Korisnika");
		lblEmailKorisnika.setBounds(26, 132, 79, 14);
		contentPanel.add(lblEmailKorisnika);
		
		JLabel lblBrojTelefona = new JLabel("Broj Telefona");
		lblBrojTelefona.setBounds(26, 180, 79, 14);
		contentPanel.add(lblBrojTelefona);
		
		JLabel lblLozinkaKorisnika = new JLabel("Lozinka");
		lblLozinkaKorisnika.setBounds(26, 230, 79, 14);
		contentPanel.add(lblLozinkaKorisnika);
		
		textFieldLozinka = new JTextField();
		textFieldLozinka.setColumns(10);
		textFieldLozinka.setBounds(115, 222, 296, 31);
		contentPanel.add(textFieldLozinka);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 313, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Apply");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String Ime_Korisnika = textFieldImeKorisnika.getText();
						String Prezime_Korisnika = textFieldPrezimeKorisnika.getText();
						String Email_Korisnika = textFieldEmailKorisnika.getText();
						String Telefon_Korisnika = textFieldBrojTelefona.getText();
						String Lozinka = textFieldLozinka.getText();
						
						try {
						    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
						    Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/pmocibob?user=pmocibob&password=11");
						    String sql = "INSERT INTO Korisnik VALUES (Null,?,?,?,?,?)";
						    PreparedStatement stmt = conn.prepareStatement(sql);
						    stmt.setString(1, Ime_Korisnika);
						    stmt.setString(2, Prezime_Korisnika);
						    stmt.setString(3, Email_Korisnika);
						    stmt.setString(4, Telefon_Korisnika);
						    stmt.setString(5, Lozinka);
						    
						    stmt.execute(); 
						    
						    conn.close();
						    
						    textFieldImeKorisnika.setText("");
						    textFieldPrezimeKorisnika.setText("");
						    textFieldEmailKorisnika.setText("");
						    textFieldBrojTelefona.setText("");
						    textFieldLozinka.setText("");
						}
						
						catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex, "Greska", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
