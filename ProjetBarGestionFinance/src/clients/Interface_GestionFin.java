package clients;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import com.google.common.collect.Ordering;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JTextField;

public class Interface_GestionFin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCommandesAssociees;
	private JTable tableStock11;
	private JTable tableStockReserve;
	private DefaultTableModel modelCommandesAssociees = new DefaultTableModel(0, 0);
	private DefaultTableModel modelStockReserve = new DefaultTableModel(0, 0);
	
	private Context context;
	private model.Tables currentTable;
	private model.Order currentOrder;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface_GestionFin frame = new Interface_GestionFin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Interface_GestionFin() throws IOException {
		context = connexionServeurWildfly();
		
		setTitle("Interface client lourd - Préparation de la commande");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 760);
		
		Color background_color = new Color(219,219,219);
		Color background_color2 = new Color(240,240,240);
		Color background_color_foncee = new Color(86,124,127);
		Color background_color_logo_clair = new Color(80,160,160);
		Color background_color_logo_clair2 = new Color(128,184,188);
		Color background_bordure = new Color(32,81,81);
		Color background_bordure_interieur = new Color(86,124,127);
		UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.LIGHT_GRAY));
		
		JTabbedPane onglets = new JTabbedPane(JTabbedPane.TOP);
		onglets.setToolTipText("");
		onglets.setBounds(65, 0, 214, 33);
	    JPanel p2 = new JPanel();
	    JPanel p3 = new JPanel();
	    
	    		
	    	    //Créer le panneau 1
	    JPanel contentPane_Encaissement = new JPanel();
	    	    
	    onglets.add("Encaissement", contentPane_Encaissement);
	    
	    JLabel Label_tableSelected = new JLabel("");
	    Label_tableSelected.setFont(new Font("Roboto", Font.PLAIN, 30));
	    Label_tableSelected.setBounds(855, 0, 55, 77);
	    contentPane_Encaissement.add(Label_tableSelected);
	    
	    contentPane_Encaissement.setBorder(new EmptyBorder(5, 5, 5, 5));
	    contentPane_Encaissement.setLayout(null);
	    
	    JButton buttonTable1 = new JButton("");
	    
	    //************************************************
	    // ATTENTION, FOCUSABLE SUR FALSE !!!!!!!!!!!!!!!!
	    //************************************************

	    buttonTable1.setFocusable(false);
	    buttonTable1.setContentAreaFilled(false);
	    buttonTable1.setBorderPainted(false);
	    JButton buttonTable2 = new JButton("");
	    buttonTable2.setFocusable(false);
	    buttonTable2.setBorderPainted(false);
	    buttonTable2.setContentAreaFilled(false);
	    JButton buttonTable3 = new JButton("");
	    buttonTable3.setFocusable(false);
	    buttonTable3.setBorderPainted(false);
	    buttonTable3.setContentAreaFilled(false);
	    
	    

	    buttonTable3.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		Label_tableSelected.setText("3");
	    		buttonTable3.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/selected_icone_cercle_3.png")));
	    		buttonTable2.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_2.png")));
	    		buttonTable1.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_1.png")));
	    		getDrinksOfOrder(Label_tableSelected.getText());
	    	}
	    });
	    buttonTable3.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_3.png")));
	    buttonTable3.setBounds(123, 507, 112, 89);
	    buttonTable3.setBackground(background_color);
	    buttonTable3.setBorder(null);
	    contentPane_Encaissement.add(buttonTable3);
	    
	    buttonTable2.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		Label_tableSelected.setText("2");
	    		buttonTable2.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/selected_icone_cercle_2.png")));
	    		buttonTable1.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_1.png")));
	    		buttonTable3.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_3.png")));
	    		getDrinksOfOrder(Label_tableSelected.getText());
	    		}
	    });
	    buttonTable2.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_2.png")));
	    buttonTable2.setBounds(321, 288, 93, 89);
	    buttonTable2.setBackground(background_color);
	    buttonTable2.setBorder(null);
	    contentPane_Encaissement.add(buttonTable2);
	    
	    buttonTable1.setSelectedIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/selected_icone_cercle_1.png")));
	    buttonTable1.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		Label_tableSelected.setText("1");
	    		buttonTable1.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/selected_icone_cercle_1.png")));
	    		buttonTable2.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_2.png")));
	    		buttonTable3.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_3.png")));
	    		getDrinksOfOrder(Label_tableSelected.getText());
	    		}
	    });
	    buttonTable1.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_1.png")));
	    buttonTable1.setBounds(142, 114, 93, 89);
	    buttonTable1.setBackground(background_color);
	    buttonTable1.setBorder(null);
	    contentPane_Encaissement.add(buttonTable1);
	    
	    JLabel labelNumTable = new JLabel("N\u00B0 de table selectionn\u00E9e :");
	    labelNumTable.setFont(new Font("Roboto", Font.PLAIN, 25));
	    labelNumTable.setBounds(565, 0, 296, 77);
	    contentPane_Encaissement.add(labelNumTable);
	    
	    JLabel Background_img = new JLabel("");
	    Background_img.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/plan_salle_2.png")));
	    Background_img.setBounds(65, 28, 420, 650);
	    contentPane_Encaissement.add(Background_img);
	    
	    JScrollPane scrollPaneFacturationCommandesAssociees = new JScrollPane();
	    scrollPaneFacturationCommandesAssociees.setFont(new Font("Roboto", Font.PLAIN, 20));
	    scrollPaneFacturationCommandesAssociees.setBounds(495, 128, 464, 200);
	    
	    scrollPaneFacturationCommandesAssociees.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
	    scrollPaneFacturationCommandesAssociees.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
	    
	    contentPane_Encaissement.add(scrollPaneFacturationCommandesAssociees);
	    

		tableCommandesAssociees = new JTable();
		tableCommandesAssociees.setFont(new Font("Roboto", Font.PLAIN, 18));
		tableCommandesAssociees.setRowHeight(tableCommandesAssociees.getRowHeight() + 10);
		tableCommandesAssociees.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 18));
		tableCommandesAssociees.setSelectionBackground(SystemColor.controlHighlight);	
		modelCommandesAssociees.addColumn("ID");
        modelCommandesAssociees.addColumn("Prix");
        modelCommandesAssociees.addColumn("Etat");
        tableCommandesAssociees.setAutoCreateRowSorter(true);
        tableCommandesAssociees.setFillsViewportHeight(true);
        tableCommandesAssociees.setModel(modelCommandesAssociees);
		scrollPaneFacturationCommandesAssociees.setViewportView(tableCommandesAssociees);
	    	    
	    JLabel labelCommandesAssociees = new JLabel("Commandes \u00E0 payer");
	    labelCommandesAssociees.setHorizontalAlignment(SwingConstants.CENTER);
	    labelCommandesAssociees.setFont(new Font("Roboto", Font.PLAIN, 20));
	    labelCommandesAssociees.setBounds(495, 87, 464, 22);
	    contentPane_Encaissement.add(labelCommandesAssociees);
	    
	    JButton buttonRefresh = new JButton("");
	    buttonRefresh.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/refresh.png")));
	    buttonRefresh.setBackground(background_color2);
	    buttonRefresh.setBorder(null);
	    buttonRefresh.setBounds(957, 28, 41, 41);
	    contentPane_Encaissement.add(buttonRefresh);
	    
	    JLabel lblTotalRestant = new JLabel("Total restant :");
	    lblTotalRestant.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTotalRestant.setFont(new Font("Roboto", Font.PLAIN, 30));
	    lblTotalRestant.setBounds(587, 558, 203, 47);
	    contentPane_Encaissement.add(lblTotalRestant);
	    
	    JLabel labelTotalRestant = new JLabel("X");
	    labelTotalRestant.setFont(new Font("Roboto", Font.PLAIN, 30));
	    labelTotalRestant.setBounds(800, 543, 55, 77);
	    contentPane_Encaissement.add(labelTotalRestant);
	    
	    JButton btnEncaissement = new JButton("ENCAISSEMENT");
	    btnEncaissement.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnEncaissement.setForeground(new Color(255, 255, 255));
	    btnEncaissement.setBackground(new Color(0, 100, 0));
	    btnEncaissement.setBorderPainted(false);
	    btnEncaissement.setBounds(625, 441, 203, 64);
	    contentPane_Encaissement.add(btnEncaissement);
	    
	    JLabel lblTotalSelection = new JLabel("Total selection :");
	    lblTotalSelection.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTotalSelection.setFont(new Font("Roboto", Font.PLAIN, 30));
	    lblTotalSelection.setBounds(554, 338, 242, 50);
	    contentPane_Encaissement.add(lblTotalSelection);
	    
	    JLabel labelTotalSelect = new JLabel("X");
	    labelTotalSelect.setFont(new Font("Roboto", Font.PLAIN, 30));
	    labelTotalSelect.setBounds(806, 338, 55, 50);
	    contentPane_Encaissement.add(labelTotalSelect);
	    
	    /////////////////////////////////////////////////
	    ///////////////////ONGLET STOCK//////////////////
	    /////////////////////////////////////////////////
	    
	    getContentPane().add(onglets);
	    
	    JPanel contentPane_Stock = new JPanel();
	    contentPane_Stock.setLayout(null);
	    contentPane_Stock.setBorder(new EmptyBorder(5, 5, 5, 5));
	    onglets.add("Stock", contentPane_Stock);
	    
	    JScrollPane scrollPaneStock = new JScrollPane();
	    scrollPaneStock.setBounds(10, 29, 497, 657);
	    contentPane_Stock.add(scrollPaneStock);
	    
	    tableStockReserve = new JTable();
	    modelStockReserve.addColumn("ID");
	    modelStockReserve.addColumn("Prix");
	    modelStockReserve.addColumn("Etat");
	    tableStockReserve.setFont(new Font("Roboto", Font.PLAIN, 18));
	    tableStockReserve.setRowHeight(tableCommandesAssociees.getRowHeight() + 10);
	    tableStockReserve.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 18));
	    tableStockReserve.setSelectionBackground(SystemColor.inactiveCaption);
	    tableStockReserve.setAutoCreateRowSorter(true);
	    scrollPaneStock.setViewportView(tableStockReserve);
	    
	    JButton btnStockAjout1 = new JButton("AJOUT 1");
	    btnStockAjout1.setForeground(new Color(255, 255, 255));
	    btnStockAjout1.setBackground(new Color(0, 100, 0));
	    btnStockAjout1.setBorderPainted(false);
	    btnStockAjout1.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockAjout1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    btnStockAjout1.setBounds(517, 235, 150, 70);
	    contentPane_Stock.add(btnStockAjout1);
	    
	    JButton btnStockAjout5 = new JButton("AJOUT 5");
	    btnStockAjout5.setForeground(new Color(255, 255, 255));
	    btnStockAjout5.setBackground(new Color(0, 100, 0));
	    btnStockAjout5.setBorderPainted(false);
	    btnStockAjout5.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockAjout5.setBounds(677, 235, 150, 70);
	    contentPane_Stock.add(btnStockAjout5);
	    
	    JButton btnStockAjout10 = new JButton("AJOUT 10");
	    btnStockAjout10.setForeground(new Color(255, 255, 255));
	    btnStockAjout10.setBorderPainted(false);
	    btnStockAjout10.setBackground(new Color(0, 100, 0));
	    btnStockAjout10.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockAjout10.setBounds(837, 235, 150, 70);
	    contentPane_Stock.add(btnStockAjout10);
	    
	    JButton btnStockRetrait1 = new JButton("RETRAIT 1");
	    btnStockRetrait1.setForeground(Color.WHITE);
	    btnStockRetrait1.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockRetrait1.setBorderPainted(false);
	    btnStockRetrait1.setBackground(new Color(128, 0, 0));
	    btnStockRetrait1.setBounds(517, 340, 150, 70);
	    contentPane_Stock.add(btnStockRetrait1);
	    
	    JButton btnStockRetrait5 = new JButton("RETRAIT 5");
	    btnStockRetrait5.setForeground(Color.WHITE);
	    btnStockRetrait5.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockRetrait5.setBorderPainted(false);
	    btnStockRetrait5.setBackground(new Color(128, 0, 0));
	    btnStockRetrait5.setBounds(677, 340, 150, 70);
	    contentPane_Stock.add(btnStockRetrait5);
	    
	    JButton btnStockRetrait10 = new JButton("RETRAIT 10");
	    btnStockRetrait10.setForeground(Color.WHITE);
	    btnStockRetrait10.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockRetrait10.setBorderPainted(false);
	    btnStockRetrait10.setBackground(new Color(128, 0, 0));
	    btnStockRetrait10.setBounds(837, 340, 150, 70);
	    contentPane_Stock.add(btnStockRetrait10);
	    
	    JLabel lblBoissonSelectionne = new JLabel("BOISSON SELECTIONN\u00C9E  :");
	    lblBoissonSelectionne.setFont(new Font("Roboto", Font.PLAIN, 20));
	    lblBoissonSelectionne.setBounds(569, 87, 258, 38);
	    contentPane_Stock.add(lblBoissonSelectionne);
	    
	    JLabel lblStockSelectionNom = new JLabel("Ricard");
	    lblStockSelectionNom.setFont(new Font("Roboto", Font.BOLD, 20));
	    lblStockSelectionNom.setBounds(837, 92, 95, 29);
	    contentPane_Stock.add(lblStockSelectionNom);
	    
	    JLabel lblStockSelectionQtt = new JLabel("3");
	    lblStockSelectionQtt.setFont(new Font("Roboto", Font.BOLD, 20));
	    lblStockSelectionQtt.setBounds(837, 140, 95, 29);
	    contentPane_Stock.add(lblStockSelectionQtt);
	    
	    JLabel lblQuantitActuelle = new JLabel("QUANTIT\u00C9 EN STOCK :");
	    lblQuantitActuelle.setFont(new Font("Roboto", Font.PLAIN, 20));
	    lblQuantitActuelle.setBounds(569, 135, 258, 38);
	    contentPane_Stock.add(lblQuantitActuelle);
	    
	    JLabel lblQuantitApresModifications = new JLabel("QUANTIT\u00C9 APRES MODIFICATIONS  :");
	    lblQuantitApresModifications.setFont(new Font("Roboto", Font.PLAIN, 20));
	    lblQuantitApresModifications.setBounds(538, 464, 339, 38);
	    contentPane_Stock.add(lblQuantitApresModifications);
	    
	    JLabel lblStockSelectionQttAprèsModif = new JLabel("X");
	    lblStockSelectionQttAprèsModif.setFont(new Font("Roboto", Font.BOLD, 20));
	    lblStockSelectionQttAprèsModif.setBounds(892, 469, 95, 29);
	    contentPane_Stock.add(lblStockSelectionQttAprèsModif);
	    
	    JButton btnValiderLesModifications = new JButton("VALIDER LES MODIFICATIONS");
	    btnValiderLesModifications.setForeground(new Color(0, 0, 0));
	    btnValiderLesModifications.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnValiderLesModifications.setBorderPainted(false);
	    btnValiderLesModifications.setBackground(new Color(95, 158, 160));
	    btnValiderLesModifications.setBounds(517, 544, 470, 70);
	    contentPane_Stock.add(btnValiderLesModifications);
	}

	public static Context connexionServeurWildfly()
	{
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		Context context=null;
		
		try {
			context = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return context;
	}
	
	public void getDrinksOfOrder(String _tableId) {
		int tableId = Integer.parseInt(_tableId);
		
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/OrderOfTable");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true); 
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			// Envoi du nom à rechercher
			fluxsortie.writeObject(tableId);
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			// Récupération du résultat de la requête
			currentTable = (model.Tables)fluxentree.readObject();
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
			
		if (currentTable != null ) {
			if (currentTable.getOrdersOfTable() != null) {
                for (var order : currentTable.getOrdersOfTable()) {
                	if (order.getOrderState().getStateId() ==  4) {
						currentOrder = order;
						break;
					}
                }
                if (currentOrder != null) {
                	for (var d : currentOrder.getDrinksOfOrder()) {
	                    Object[] drink = { d.getDrinkName(), d.getDrinkPrice()};
	                    modelCommandesAssociees.addRow(drink);
	                }					
				}
			}
		}	
	}
}
