package Patrik;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;

public class Unos_Oglasa extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textFieldAdresaNekretnine;
    private JTextField textFieldKvadraturaNekretnine;
    private JTextField textFieldTipNekretnine;
    private JTextField textFieldCijenaNekretnine;
    private JTextField textFieldDatumObjave;
    private JTextField textFieldDatumIsteka;

    /**
     * Launch the application.
     */
    public static void newscreen() {
        try {
            Unos_Oglasa dialog = new Unos_Oglasa();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public Unos_Oglasa() {
        setTitle("Nova Nekretnina");
        setBounds(100, 100, 850, 585);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);

        JLabel lblAdresaNekretnine = new JLabel("Adresa nekretnine");
        lblAdresaNekretnine.setBounds(24, 41, 120, 14);
        contentPanel.add(lblAdresaNekretnine);

        JLabel lblKvadraturaNekretnine = new JLabel("Kvadratura nekretnine");
        lblKvadraturaNekretnine.setBounds(24, 125, 120, 14);
        contentPanel.add(lblKvadraturaNekretnine);

        JLabel lblCijenaNekretnine = new JLabel("Cijena nekretnine");
        lblCijenaNekretnine.setBounds(24, 167, 120, 14);
        contentPanel.add(lblCijenaNekretnine);

        JLabel lblOpisNekretnine = new JLabel("Opis nekretnine");
        lblOpisNekretnine.setBounds(24, 283, 120, 14);
        contentPanel.add(lblOpisNekretnine);

        JLabel lblTipNekretnine = new JLabel("Tip nekretnine");
        lblTipNekretnine.setBounds(24, 83, 120, 14);
        contentPanel.add(lblTipNekretnine);

        JLabel lblDatumObjaveOglasa = new JLabel("Datum objave oglasa");
        lblDatumObjaveOglasa.setBounds(24, 206, 120, 14);
        contentPanel.add(lblDatumObjaveOglasa);

        JLabel lblDatumIstekaOglasa = new JLabel("Datum isteka oglasa");
        lblDatumIstekaOglasa.setBounds(24, 241, 120, 14);
        contentPanel.add(lblDatumIstekaOglasa);

        textFieldAdresaNekretnine = new JTextField();
        textFieldAdresaNekretnine.setColumns(10);
        textFieldAdresaNekretnine.setBounds(154, 33, 296, 31);
        contentPanel.add(textFieldAdresaNekretnine);

        textFieldKvadraturaNekretnine = new JTextField();
        textFieldKvadraturaNekretnine.setColumns(10);
        textFieldKvadraturaNekretnine.setBounds(154, 117, 65, 31);
        contentPanel.add(textFieldKvadraturaNekretnine);

        textFieldTipNekretnine = new JTextField();
        textFieldTipNekretnine.setColumns(10);
        textFieldTipNekretnine.setBounds(154, 75, 296, 31);
        contentPanel.add(textFieldTipNekretnine);

        textFieldCijenaNekretnine = new JTextField();
        textFieldCijenaNekretnine.setColumns(10);
        textFieldCijenaNekretnine.setBounds(154, 159, 99, 31);
        contentPanel.add(textFieldCijenaNekretnine);

        JLabel lblM = new JLabel("m2");
        lblM.setBounds(229, 125, 120, 14);
        contentPanel.add(lblM);

        JLabel lblM_1 = new JLabel("€");
        lblM_1.setBounds(263, 167, 120, 14);
        contentPanel.add(lblM_1);

        JTextArea textAreaOpisNekretnine = new JTextArea();
        textAreaOpisNekretnine.setFont(new Font("Tahoma", Font.PLAIN, 11));
        textAreaOpisNekretnine.setBounds(154, 278, 296, 175);
        contentPanel.add(textAreaOpisNekretnine);

        textFieldDatumObjave = new JTextField();
        textFieldDatumObjave.setColumns(10);
        textFieldDatumObjave.setBounds(154, 198, 99, 31);
        contentPanel.add(textFieldDatumObjave);

        textFieldDatumIsteka = new JTextField();
        textFieldDatumIsteka.setColumns(10);
        textFieldDatumIsteka.setBounds(154, 233, 99, 31);
        contentPanel.add(textFieldDatumIsteka);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Apply");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String Adresa_nekretnine = textFieldAdresaNekretnine.getText();
                        String Tip_nekretnine = textFieldTipNekretnine.getText();
                        String Kvadratura_nekretnine = textFieldKvadraturaNekretnine.getText();
                        String Cijena_nekretnine = textFieldCijenaNekretnine.getText();
                        String Datum_Objave = textFieldDatumObjave.getText();
                        String Datum_Isteka = textFieldDatumIsteka.getText();
                        String Opis_nekretnine = textAreaOpisNekretnine.getText();
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                            Connection conn = DriverManager.getConnection("jdbc:mysql://ucka.veleri.hr/pmocibob?user=pmocibob&password=11");
                            String sql = "INSERT INTO Nekretnina_na_oglasu (Adresa_nekretnine, Kvadratura_nekretnine, Cijena_nekretnine, Opis_nekretnine, Tip_nekretnine, Datum_objave_oglasa, Datum_isteka_oglasa) VALUES (?,?,?,?,?,?,?)";
                            
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            stmt.setString(1, Adresa_nekretnine);
                            stmt.setString(2, Kvadratura_nekretnine);
                            stmt.setString(3, Cijena_nekretnine);
                            stmt.setString(4, Opis_nekretnine);
                            stmt.setString(5, Tip_nekretnine);
                            stmt.setString(6, Datum_Objave);
                            stmt.setString(7, Datum_Isteka);
                            
                            stmt.execute(); 
                            
                            conn.close();
                            
                            textFieldAdresaNekretnine.setText("");
                            textFieldTipNekretnine.setText("");
                            textFieldKvadraturaNekretnine.setText("");
                            textFieldCijenaNekretnine.setText("");
                            textAreaOpisNekretnine.setText("");
                            textFieldDatumObjave.setText("");
                            textFieldDatumIsteka.setText("");
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