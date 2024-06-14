package Patrik;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Prikaz_Oglasa extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ArrayList<String> favorites = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Prikaz_Oglasa dialog = new Prikaz_Oglasa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void newscreen() {
		try {
			Prikaz_Oglasa dialog = new Prikaz_Oglasa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Prikaz_Oglasa() {
		setTitle("Prikaz Oglasa");
		setBounds(100, 100, 650, 385);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(contentPanel);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton prikaziFavoriteButton = new JButton("Prikazi Favorite");
		prikaziFavoriteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showFavorites();
			}
		});
		buttonPane.add(prikaziFavoriteButton);
		
		JButton okButton = new JButton("Close");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		selectPrikazOglasa();
	}

	private void selectPrikazOglasa() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/pmocibob?user=pmocibob&password=11");

			String sql = "SELECT * FROM Nekretnina_na_oglasu";
			java.sql.Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				JPanel listingPanel = new JPanel();
				listingPanel.setLayout(new BorderLayout());

				final String tekst = "Sifra: " + rs.getString("Sifra_oglasa") + "\t" +
						"Adresa: " + rs.getString("Adresa_nekretnine") + "\t" +
						"Kvadratura: " + rs.getString("Kvadratura_nekretnine") + "\t" +
						"Cijena: " + rs.getString("Cijena_nekretnine") + "\t" +
						"Opis: " + rs.getString("Opis_nekretnine") + "\t" +
						"Tip: " + rs.getString("Tip_nekretnine") + "\t" +
						"Datum_objave: " + rs.getString("Datum_objave_oglasa") + "\t" +
						"Datum_isteka: " + rs.getString("Datum_isteka_oglasa") + "\n";

				JTextArea textArea = new JTextArea(tekst);
				textArea.setEditable(false);
				listingPanel.add(textArea, BorderLayout.CENTER);

				JButton favoriteButton = new JButton("☆");
				favoriteButton.setPreferredSize(new Dimension(150, 25)); 
				favoriteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						favorites.add(tekst);
						addToFavorites(rs);
						JOptionPane.showMessageDialog(null, "Dodano u  favorite!", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				});
				listingPanel.add(favoriteButton, BorderLayout.EAST);

				contentPanel.add(listingPanel);
			}

			conn.close();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addToFavorites(ResultSet rs) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/pmocibob?user=pmocibob&password=11");

			String sql = "INSERT INTO Favoriti (Sifra_oglasa, Adresa_nekretnine, Kvadratura_nekretnine, Cijena_nekretnine, Opis_nekretnine, Tip_nekretnine, Datum_objave_oglasa, Datum_isteka_oglasa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, rs.getString("Sifra_oglasa"));
			stmt.setString(2, rs.getString("Adresa_nekretnine"));
			stmt.setString(3, rs.getString("Kvadratura_nekretnine"));
			stmt.setString(4, rs.getString("Cijena_nekretnine"));
			stmt.setString(5, rs.getString("Opis_nekretnine"));
			stmt.setString(6, rs.getString("Tip_nekretnine"));
			stmt.setString(7, rs.getString("Datum_objave_oglasa"));
			stmt.setString(8, rs.getString("Datum_isteka_oglasa"));

			stmt.execute();
			conn.close();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void showFavorites() {
		JDialog favoritesDialog = new JDialog(this, "Favoriti", true);
		favoritesDialog.setBounds(100, 100, 650, 385);
		favoritesDialog.setLayout(new BorderLayout());

		JTextArea textArea = new JTextArea();
		for (String favorite : favorites) {
			textArea.append(favorite + "\n");
		}
		textArea.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textArea);
		favoritesDialog.add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				favoritesDialog.dispose();
			}
		});
		buttonPane.add(closeButton);

		favoritesDialog.add(buttonPane, BorderLayout.SOUTH);
		favoritesDialog.setVisible(true);
	}
}
