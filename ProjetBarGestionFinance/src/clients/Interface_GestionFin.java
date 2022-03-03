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
import java.util.Iterator;
import java.util.List;

import dtos.*;
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

import dtos.TablesDto;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
	private JTable tableBoissonsSelectionnees;
	private JTable tableStockReserve;
	private DefaultTableModel modelCommandesAssociees = new DefaultTableModel() {
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
	private DefaultTableModel modelStockReserve = new DefaultTableModel() {
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
	private DefaultTableModel modelBoissonsSelectionnees = new DefaultTableModel() {
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
	private JLabel labelTotalSelect = new JLabel("0");
	private JLabel labelTotalRestant = new JLabel("0");

		
	private Context context;
	private TablesDto currentTable;
	private OrderDto currentOrder;
	private List<DrinkDto> drinks;
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
		modelCommandesAssociees.addColumn("Nom");
        modelCommandesAssociees.addColumn("Prix");
	    	    
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
	    modelStockReserve.addColumn("Nom");
	    modelStockReserve.addColumn("Prix");
	    modelStockReserve.addColumn("Quantité");
	    tableStockReserve.setFont(new Font("Roboto", Font.PLAIN, 18));
	    tableStockReserve.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 18));
	    tableStockReserve.setSelectionBackground(SystemColor.inactiveCaption);
	    tableStockReserve.setAutoCreateRowSorter(true);
	    tableStockReserve.setModel(modelStockReserve);
	    scrollPaneStock.setViewportView(tableStockReserve);
	    	    
	    JButton btnStockAjout1 = new JButton("AJOUT 1");
	    btnStockAjout1.setForeground(new Color(255, 255, 255));
	    btnStockAjout1.setBackground(new Color(0, 100, 0));
	    btnStockAjout1.setBorderPainted(false);
	    btnStockAjout1.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockAjout1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		addStock(1);	  
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
	    btnStockAjout5.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		addStock(5);	  
	    	}
	    });
	    contentPane_Stock.add(btnStockAjout5);
	    
	    JButton btnStockAjout10 = new JButton("AJOUT 10");
	    btnStockAjout10.setForeground(new Color(255, 255, 255));
	    btnStockAjout10.setBorderPainted(false);
	    btnStockAjout10.setBackground(new Color(0, 100, 0));
	    btnStockAjout10.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockAjout10.setBounds(837, 235, 150, 70);
	    btnStockAjout10.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		addStock(10);	  
	    	}
	    });
	    contentPane_Stock.add(btnStockAjout10);
	    
	    JButton btnStockRetrait1 = new JButton("RETRAIT 1");
	    btnStockRetrait1.setForeground(Color.WHITE);
	    btnStockRetrait1.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockRetrait1.setBorderPainted(false);
	    btnStockRetrait1.setBackground(new Color(128, 0, 0));
	    btnStockRetrait1.setBounds(517, 340, 150, 70);
	    btnStockRetrait1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		removeStock(1);	  
	    	}
	    });
	    contentPane_Stock.add(btnStockRetrait1);
	    
	    JButton btnStockRetrait5 = new JButton("RETRAIT 5");
	    btnStockRetrait5.setForeground(Color.WHITE);
	    btnStockRetrait5.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockRetrait5.setBorderPainted(false);
	    btnStockRetrait5.setBackground(new Color(128, 0, 0));
	    btnStockRetrait5.setBounds(677, 340, 150, 70);
	    btnStockRetrait5.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		removeStock(5);	  
	    	}
	    });
	    contentPane_Stock.add(btnStockRetrait5);
	    
	    JButton btnStockRetrait10 = new JButton("RETRAIT 10");
	    btnStockRetrait10.setForeground(Color.WHITE);
	    btnStockRetrait10.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnStockRetrait10.setBorderPainted(false);
	    btnStockRetrait10.setBackground(new Color(128, 0, 0));
	    btnStockRetrait10.setBounds(837, 340, 150, 70);
	    btnStockRetrait10.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		removeStock(10);	  
	    	}
	    });
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
	    
	    JLabel lblStockSelectionQttAprèsModif = new JLabel("X");
	    lblStockSelectionQttAprèsModif.setFont(new Font("Roboto", Font.BOLD, 20));
	    lblStockSelectionQttAprèsModif.setBounds(892, 469, 95, 29);
	    contentPane_Stock.add(lblStockSelectionQttAprèsModif);
	    
	    		
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
	    		if (tableBoissonsSelectionnees.getRowCount() == 0) {						
		    		Label_tableSelected.setText("3");
		    		buttonTable3.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/selected_icone_cercle_3.png")));
		    		buttonTable2.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_2.png")));
		    		buttonTable1.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_1.png")));
		    		getDrinksOfOrder(Label_tableSelected.getText());
				}
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
	    		if (tableBoissonsSelectionnees.getRowCount() == 0) {
		    		Label_tableSelected.setText("2");
		    		buttonTable2.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/selected_icone_cercle_2.png")));
		    		buttonTable1.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_1.png")));
		    		buttonTable3.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_3.png")));
		    		getDrinksOfOrder(Label_tableSelected.getText());
	    		}
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
	    		if (tableBoissonsSelectionnees.getRowCount() == 0) {
		    		Label_tableSelected.setText("1");
		    		buttonTable1.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/selected_icone_cercle_1.png")));
		    		buttonTable2.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_2.png")));
		    		buttonTable3.setIcon(new ImageIcon(Interface_GestionFin.class.getResource("/img/icone_cercle_3.png")));
		    		getDrinksOfOrder(Label_tableSelected.getText());
	    		}
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
		tableCommandesAssociees.setAutoCreateRowSorter(true);
		tableCommandesAssociees.setFillsViewportHeight(true);
		tableCommandesAssociees.setModel(modelCommandesAssociees);
		scrollPaneFacturationCommandesAssociees.setViewportView(tableCommandesAssociees);
		

	    /******************************************************************/
	    ///////////////////TABLE2 : BOISSONS SELECTIONNEES/////////////////
        /******************************************************************/
		JScrollPane scrollPaneFacturationBoissonsAssociees = new JScrollPane();
		scrollPaneFacturationBoissonsAssociees.setFont(new Font("Roboto", Font.PLAIN, 20));
		scrollPaneFacturationBoissonsAssociees.setBounds(495, 436, 464, 160);
	    
		scrollPaneFacturationBoissonsAssociees.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
		scrollPaneFacturationBoissonsAssociees.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
	    
	    contentPane_Encaissement.add(scrollPaneFacturationBoissonsAssociees);
	    
	    tableBoissonsSelectionnees = new JTable();
	    tableBoissonsSelectionnees.setFont(new Font("Roboto", Font.PLAIN, 18));
	    tableBoissonsSelectionnees.setRowHeight(tableBoissonsSelectionnees.getRowHeight() + 10);
	    tableBoissonsSelectionnees.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 18));
	    tableBoissonsSelectionnees.setSelectionBackground(SystemColor.inactiveCaption);
	    modelBoissonsSelectionnees.addColumn("Boisson");
	    modelBoissonsSelectionnees.addColumn("Prix");
	    tableBoissonsSelectionnees.setAutoCreateRowSorter(true);
	    tableBoissonsSelectionnees.setFillsViewportHeight(true);
	    tableBoissonsSelectionnees.setModel(modelBoissonsSelectionnees);
	    scrollPaneFacturationBoissonsAssociees.setViewportView(tableBoissonsSelectionnees);
        /******************************************************************/
		
	    JLabel labelCommandesAssociees = new JLabel("Boissons \u00E0 payer");
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
	    lblTotalRestant.setBounds(565, 385, 203, 57);
	    contentPane_Encaissement.add(lblTotalRestant);
	    
	    labelTotalRestant.setFont(new Font("Roboto", Font.PLAIN, 30));
	    labelTotalRestant.setBounds(769, 383, 163, 61);
	    contentPane_Encaissement.add(labelTotalRestant);
	    
	    JButton btnEncaissement = new JButton("ENCAISSEMENT");
	    btnEncaissement.setFont(new Font("Roboto", Font.BOLD, 20));
	    btnEncaissement.setForeground(new Color(255, 255, 255));
	    btnEncaissement.setBackground(new Color(0, 100, 0));
	    btnEncaissement.setBorderPainted(false);
	    btnEncaissement.setBounds(756, 621, 203, 57);
	    contentPane_Encaissement.add(btnEncaissement);
	    
	    btnEncaissement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (tableBoissonsSelectionnees.getRowCount() > 0) {
					pay();
				
					if (tableCommandesAssociees.getRowCount() == 0) {
						setOrderToFinished();
					}
            	}
            }
        });
	    
	    JLabel lblTotalSelection = new JLabel("Total :");
	    lblTotalSelection.setHorizontalAlignment(SwingConstants.CENTER);
	    lblTotalSelection.setFont(new Font("Roboto", Font.PLAIN, 30));
	    lblTotalSelection.setBounds(495, 628, 124, 50);
	    contentPane_Encaissement.add(lblTotalSelection);
	    
	    labelTotalSelect.setFont(new Font("Roboto", Font.PLAIN, 30));
	    labelTotalSelect.setBounds(606, 628, 154, 50);
	    contentPane_Encaissement.add(labelTotalSelect);
	 	    
	    tableCommandesAssociees.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect doble click events
	            	try {
            			double totalSelection = Double.parseDouble(labelTotalSelect.getText());
            			double totalRestant = Double.parseDouble(labelTotalRestant.getText());
            			int indexOfRow = tableCommandesAssociees.getSelectedRow();
            			var drinkName = (String)tableCommandesAssociees.getValueAt(tableCommandesAssociees.getSelectedRow(), 0);
        				var prixBoisson = (double)tableCommandesAssociees.getValueAt(tableCommandesAssociees.getSelectedRow(), 1);
        				totalSelection += prixBoisson;
        				totalRestant -= prixBoisson;
                        Object[] drink = {drinkName, prixBoisson};
                        modelBoissonsSelectionnees.addRow(drink);
                        modelCommandesAssociees.removeRow(indexOfRow);
                        tableCommandesAssociees.repaint();
            			labelTotalSelect.setText(String.valueOf(totalSelection));
            			labelTotalRestant.setText(String.valueOf(totalRestant));
					} catch (Exception e) {
						System.out.println("erreur : " + e.getMessage());
					}
	            }
	         }
	      });
	    
	    tableBoissonsSelectionnees.addMouseListener(new MouseAdapter() {
	         public void mouseClicked(MouseEvent me) {
	            if (me.getClickCount() == 2) {     // to detect doble click events
	            	try {
	            		double totalSelection = Double.parseDouble(labelTotalSelect.getText());
            			double totalRestant = Double.parseDouble(labelTotalRestant.getText());
	           			int indexOfRow = tableBoissonsSelectionnees.getSelectedRow();
	           			var drinkName = (String)tableBoissonsSelectionnees.getValueAt(tableBoissonsSelectionnees.getSelectedRow(), 0);
	       				var prixBoisson = (double)tableBoissonsSelectionnees.getValueAt(tableBoissonsSelectionnees.getSelectedRow(), 1);
	       				totalSelection -= prixBoisson;
        				totalRestant += prixBoisson;
	       				Object[] drink = {drinkName, prixBoisson};
	       				modelCommandesAssociees.addRow(drink);
	       				modelBoissonsSelectionnees.removeRow(indexOfRow);
	       				tableCommandesAssociees.repaint();
	       				tableBoissonsSelectionnees.repaint();
	       				labelTotalSelect.setText(String.valueOf(totalSelection));
            			labelTotalRestant.setText(String.valueOf(totalRestant));
					} catch (Exception e) {
						System.out.println("erreur : " + e.getMessage());
					}
	            }
	         }
	      });
	    
	    JLabel lblBoissonsSelectionnes = new JLabel("Boissons selectionn\u00E9es");
	    lblBoissonsSelectionnes.setHorizontalAlignment(SwingConstants.CENTER);
	    lblBoissonsSelectionnes.setFont(new Font("Dialog", Font.PLAIN, 20));
	    lblBoissonsSelectionnes.setBounds(495, 351, 464, 22);
	    contentPane_Encaissement.add(lblBoissonsSelectionnes);
	    tableStockReserve.setRowHeight(tableCommandesAssociees.getRowHeight() + 10);
	    
	    getDrinks();
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
		modelCommandesAssociees.setRowCount(0);
		modelBoissonsSelectionnees.setRowCount(0);
		labelTotalRestant.setText("0");
		labelTotalSelect.setText("0");
		
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/OrderToPay");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true); 
			
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			
			// Envoi du nom à rechercher
			fluxsortie.writeObject(tableId);
			
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			
			// Récupération du résultat de la requête
			currentTable = (TablesDto)fluxentree.readObject();
			if (currentTable != null) {
				if (currentTable.getOrdersOfTable().toArray().length > 0) {
					currentOrder = currentTable.getOrdersOfTable().get(0);	
				}else {
					currentOrder = null;
					modelCommandesAssociees.setRowCount(0);
					modelBoissonsSelectionnees.setRowCount(0);
				}
			}else {
				currentOrder = null;
				modelCommandesAssociees.setRowCount(0);
				modelBoissonsSelectionnees.setRowCount(0);
			}
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
			
		if (currentOrder != null ) {
			if (currentOrder.getDrinksOfOrder() != null) {
                double totalResant = 0;
				for (var d : currentOrder.getDrinksOfOrder()) {
                    Object[] drink = { d.getDrinkName(), d.getDrinkPrice()};
                    modelCommandesAssociees.addRow(drink);
                    totalResant += d.getDrinkPrice();
                }
				labelTotalRestant.setText(String.valueOf(totalResant));
			}
		}		
	}
	
	public void getDrinks() {
		modelStockReserve.setRowCount(0);
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/GetDrinks");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true); 
			
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			
			// Envoi du nom à rechercher
			fluxsortie.writeObject("");
			
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			
			// Récupération du résultat de la requête
			drinks = (List<DrinkDto>)fluxentree.readObject();
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
			
		if (drinks != null ) {
		    for (var d : drinks) {
                Object[] drink = { d.getDrinkId(), d.getDrinkName(), d.getDrinkPrice(), d.getDrinkQuantity()};
                modelStockReserve.addRow(drink);
            }			
		}	
	}
	
	public void pay() {	
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/Pay");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true); 
			
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			BillDto bill = new BillDto();
			bill.setOrder(currentOrder);
			bill.setBillAmount(labelTotalSelect.getText());
			fluxsortie.writeObject(bill);
			
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());

			// Récupération du résultat de la requête
			bill = (BillDto)fluxentree.readObject();
			
			if (bill.getBillId() > 0) {
				modelBoissonsSelectionnees.setRowCount(0);
				labelTotalSelect.setText("0");				
			}
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}				
	}

	public void setOrderToFinished() {
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/SetOrderFisnished");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true); 
			
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			
			// Envoi du nom à rechercher
			fluxsortie.writeObject(currentOrder);
			
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			
			// Récupération du résultat de la requête
			currentTable = (TablesDto)fluxentree.readObject();
			if (currentTable != null) {
				if (currentTable.getOrdersOfTable().toArray().length > 0) {
					currentOrder = currentTable.getOrdersOfTable().get(0);	
				}else {
					currentOrder = null;
					modelCommandesAssociees.setRowCount(0);
					modelBoissonsSelectionnees.setRowCount(0);
				}
			}else {
				currentOrder = null;
				modelCommandesAssociees.setRowCount(0);
				modelBoissonsSelectionnees.setRowCount(0);
			}
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
	}
	
	public void addStock(int addQty) {
		int drinkId = (int)tableStockReserve.getValueAt(tableStockReserve.getSelectedRow(), 0);
		DrinkDto currentDrink = drinks.stream().filter(d -> drinkId == d.getDrinkId()).findAny().orElse(null);
		currentDrink.setDrinkQuantity(currentDrink.getDrinkQuantity() + addQty);
		
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/AddStock");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true); 
			
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			
			// Envoi du nom à rechercher
			fluxsortie.writeObject(currentDrink);
			
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			
			getDrinks();			
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
	}
	
	public void removeStock(int removeQty) {
		int drinkId = (int)tableStockReserve.getValueAt(tableStockReserve.getSelectedRow(), 0);
		DrinkDto currentDrink = drinks.stream().filter(d -> drinkId == d.getDrinkId()).findAny().orElse(null);
		currentDrink.setDrinkQuantity(currentDrink.getDrinkQuantity() - removeQty);
		
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/AddStock");
			URLConnection connexion = url.openConnection();
			connexion.setDoOutput(true); 
			
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			
			// Envoi du nom à rechercher
			fluxsortie.writeObject(currentDrink);
			
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			
			getDrinks();			
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
	}
}
