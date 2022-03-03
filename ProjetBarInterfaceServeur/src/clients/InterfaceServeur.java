package clients;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import ejbs.*;
import model.*;
import dtos.*;
import java.util.Set;

public class InterfaceServeur extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableCommandesAssociees;
	private JTable tableBoissonsAssociees;
	private DefaultTableModel modelCommandesAssociees = new DefaultTableModel() {
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
	private DefaultTableModel modelBoissonsAssociees = new DefaultTableModel() {
		   @Override
		   public boolean isCellEditable(int row, int column) {
		       return false;
		   }
		};
	
	private Context context;
	private TablesDto currentTable;
	private OrderDto currentOrder;
	
	private String enPréparation = "En préparation";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceServeur frame = new InterfaceServeur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InterfaceServeur() throws IOException {
		context = connexionServeurWildfly();
		
		Color background_color = new Color(219,219,219);
		Color background_color2 = new Color(240,240,240);
		Color background_color_foncee = new Color(86,124,127);
		Color background_color_logo_clair = new Color(80,160,160);
		Color background_color_logo_clair2 = new Color(128,184,188);
		Color background_bordure = new Color(32,81,81);
		Color background_bordure_interieur = new Color(86,124,127);
		UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.LIGHT_GRAY));

		setTitle("Interface client lourd - Préparation de la commande");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 728);
		
		contentPane = new JPanel();
		JLabel Label_tableSelected = new JLabel("");
		Label_tableSelected.setFont(new Font("Roboto", Font.PLAIN, 30));
		Label_tableSelected.setBounds(870, 0, 55, 77);
		contentPane.add(Label_tableSelected);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
				buttonTable3.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/selected_icone_cercle_3.png")));
				buttonTable2.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_2.png")));
				buttonTable1.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_1.png")));
				getOrdersOfTable(Label_tableSelected.getText());
			}
		});
		buttonTable3.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_3.png")));
		buttonTable3.setBounds(123, 507, 112, 89);
		buttonTable3.setBackground(background_color);
		buttonTable3.setBorder(null);
		contentPane.add(buttonTable3);
		
		buttonTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Label_tableSelected.setText("2");
				buttonTable2.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/selected_icone_cercle_2.png")));
				buttonTable1.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_1.png")));
				buttonTable3.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_3.png")));
				getOrdersOfTable(Label_tableSelected.getText());
			}
		});
		buttonTable2.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_2.png")));
		buttonTable2.setBounds(321, 288, 93, 89);
		buttonTable2.setBackground(background_color);
		buttonTable2.setBorder(null);
		contentPane.add(buttonTable2);
		
		buttonTable1.setSelectedIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/selected_icone_cercle_1.png")));
		buttonTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Label_tableSelected.setText("1");
				buttonTable1.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/selected_icone_cercle_1.png")));
				buttonTable2.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_2.png")));
				buttonTable3.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_3.png")));
				getOrdersOfTable(Label_tableSelected.getText());
			}
		});
		buttonTable1.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/icone_cercle_1.png")));
		buttonTable1.setBounds(142, 114, 93, 89);
		buttonTable1.setBackground(background_color);
		buttonTable1.setBorder(null);
		contentPane.add(buttonTable1);
		
		JLabel labelNumTable = new JLabel("N\u00B0 de table selectionn\u00E9e :");
		labelNumTable.setFont(new Font("Roboto", Font.PLAIN, 25));
		labelNumTable.setBounds(580, 0, 296, 77);
		contentPane.add(labelNumTable);
		
		JLabel Background_img = new JLabel("");
		Background_img.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/plan_salle_2.png")));
		Background_img.setBounds(65, 28, 420, 650);
		contentPane.add(Background_img);
		
		JScrollPane scrollPaneCommandesAssociees = new JScrollPane();
		scrollPaneCommandesAssociees.setFont(new Font("Roboto", Font.PLAIN, 20));
		scrollPaneCommandesAssociees.setBounds(539, 100, 420, 160);
		
		scrollPaneCommandesAssociees.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
		scrollPaneCommandesAssociees.getHorizontalScrollBar().setUI(new BasicScrollBarUI());
		
		contentPane.add(scrollPaneCommandesAssociees);
		
		tableCommandesAssociees = new JTable();
		tableCommandesAssociees.setFont(new Font("Roboto", Font.PLAIN, 18));
		tableCommandesAssociees.setRowHeight(tableCommandesAssociees.getRowHeight() + 10);
		tableCommandesAssociees.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 18));
		tableCommandesAssociees.setSelectionBackground(SystemColor.controlHighlight);
		scrollPaneCommandesAssociees.setViewportView(tableCommandesAssociees);
		
		modelCommandesAssociees.addColumn("ID");
        modelCommandesAssociees.addColumn("Prix");
        modelCommandesAssociees.addColumn("Etat");
        tableCommandesAssociees.setAutoCreateRowSorter(true);
        tableCommandesAssociees.setFillsViewportHeight(true);
        tableCommandesAssociees.setModel(modelCommandesAssociees);

		
		JScrollPane scrollPaneCommandesRestantes = new JScrollPane();
		scrollPaneCommandesRestantes.setFont(new Font("Roboto", Font.PLAIN, 20));
		scrollPaneCommandesRestantes.setBounds(539, 378, 420, 300);
		contentPane.add(scrollPaneCommandesRestantes);
		
		scrollPaneCommandesRestantes.getVerticalScrollBar().setUI(new BasicScrollBarUI() );
		scrollPaneCommandesRestantes.getHorizontalScrollBar().setUI(new BasicScrollBarUI());		
		
		tableBoissonsAssociees = new JTable();
		tableBoissonsAssociees.setAutoCreateRowSorter(true);
		tableBoissonsAssociees.setRowHeight(tableBoissonsAssociees.getRowHeight() + 10);
		tableBoissonsAssociees.getTableHeader().setFont(new Font("Roboto", Font.PLAIN, 18));
		scrollPaneCommandesRestantes.setViewportView(tableBoissonsAssociees);

		modelBoissonsAssociees.addColumn("Nom");
		tableBoissonsAssociees.setAutoCreateRowSorter(true);
		tableBoissonsAssociees.setFillsViewportHeight(true);
		tableBoissonsAssociees.setModel(modelBoissonsAssociees);

		tableBoissonsAssociees.setSelectionBackground(SystemColor.controlHighlight);
		tableBoissonsAssociees.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JLabel labelCommandesAssociees = new JLabel("Commandes associ\u00E9es");
		labelCommandesAssociees.setHorizontalAlignment(SwingConstants.CENTER);
		labelCommandesAssociees.setFont(new Font("Roboto", Font.PLAIN, 20));
		labelCommandesAssociees.setBounds(585, 75, 340, 14);
		contentPane.add(labelCommandesAssociees);
		
		JLabel labelCommandesRestantes = new JLabel("Commandes restantes");
		labelCommandesRestantes.setHorizontalAlignment(SwingConstants.CENTER);
		labelCommandesRestantes.setFont(new Font("Roboto", Font.PLAIN, 20));
		labelCommandesRestantes.setBounds(589, 353, 340, 14);
		contentPane.add(labelCommandesRestantes);
		
		JButton buttonCommandePrete = new JButton("Commande pr\u00EAte ");
		buttonCommandePrete.setBorderPainted(false);
		buttonCommandePrete.setBorder(new EmptyBorder(5, 5, 5, 5));
		buttonCommandePrete.setBackground(background_color_logo_clair2);
		buttonCommandePrete.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonCommandePrete.setOpaque(true);
		
		buttonCommandePrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableCommandesAssociees.getSelectedRow() > -1) {
					String state = (String)tableCommandesAssociees.getValueAt(tableCommandesAssociees.getSelectedRow(), 2).toString();

					if (state.equalsIgnoreCase(enPréparation)) {
						try {
	        				setOrderReady(currentOrder);
	        				tableCommandesAssociees.setValueAt(currentOrder.getOrderState().getStateName(), tableCommandesAssociees.getSelectedRow(), 2);
						} catch (Exception ex) {
							System.out.println("erreur : " + ex.getMessage());
						}
					}
            	}
			}
		});
		buttonCommandePrete.setBounds(642, 271, 214, 33);
		contentPane.add(buttonCommandePrete);
		
		JButton buttonRefresh = new JButton("");
		buttonRefresh.setIcon(new ImageIcon(InterfaceServeur.class.getResource("/img/refresh.png")));
		buttonRefresh.setBackground(background_color2);
		buttonRefresh.setBorder(null);
		buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		buttonRefresh.setBounds(957, 11, 41, 41);
		contentPane.add(buttonRefresh);
		
		tableCommandesAssociees.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
            	if (!event.getValueIsAdjusting() && tableCommandesAssociees.getSelectedRow() > -1) {
            		modelBoissonsAssociees.setRowCount(0);
            		try {
						var orderId = (int)tableCommandesAssociees.getValueAt(tableCommandesAssociees.getSelectedRow(), 0);

						for (var o : currentTable.getOrdersOfTable()) {
		                    if (o.getOrderId() == orderId) {
								currentOrder = o;
								//currentOrder.setTableOfOrder(currentTable);
								break;
							}
		                }
						
						if (currentOrder.getOrderId() == orderId ) {
							for (var d : currentOrder.getDrinksOfOrder()) {
			                    Object[] drink = { d.getDrinkName()};
			                    modelBoissonsAssociees.addRow(drink);
			                }	
						}	
						
					} catch (Exception e) {
						System.out.println("erreur : " + e.getMessage());
					}
            	}
            }
        });
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
		
	private void getOrdersOfTable(String _tableId) {
		int tableId = Integer.parseInt(_tableId);
		modelCommandesAssociees.setRowCount(0);
		modelBoissonsAssociees.setRowCount(0);
		
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
			currentTable = (TablesDto)fluxentree.readObject();
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
			
		if (currentTable != null ) {
			if (currentTable.getOrdersOfTable() != null) {
                for (var o : currentTable.getOrdersOfTable()) {
                    Object[] order = { o.getOrderId(), o.getOrderAmount(), o.getOrderState().getStateName()};
                    modelCommandesAssociees.addRow(order);
                }
			}
		}		
	}

	private void setOrderReady(OrderDto order) {
		try
		{
			// Connexion à la servlet
			URL url=new URL("http://localhost:8080/ProjetBarServlet/SetOrderReady");
			URLConnection connexion=url.openConnection();
			connexion.setDoOutput(true); 
			
			// Récupération du flux de sortie
			ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			
			// Envoi du nom à rechercher
			fluxsortie.writeObject(currentOrder);
			
			// Récupération du flux d’entrée
			ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			
			// Récupération du résultat de la requête
			currentOrder = (OrderDto)fluxentree.readObject();
		}
		catch (Exception e)
		{
			System.out.println("erreur " + e);
		}
	}
}
