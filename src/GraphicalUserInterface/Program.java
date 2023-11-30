package GraphicalUserInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import ADTMapBST.*;
import Excepcions.InvalidKeyException;
import Fonts.Roboto;
import Fonts.Roboto_Black;

/**
 * This class is the main class of the GUI.
 */
public class Program {
	private BSTMapExtended<Integer,Character> map;
	private JFrame frmBstMapVisulization;
	private JTextField txtKeyvalue;
	private Color white = new Color(255, 255, 255);
	private Color black = new Color(0, 0, 0);
	private Roboto roboto = new Roboto();
	private Roboto_Black roboto_Black = new Roboto_Black(); 
	private JPanel panelActions;
	private TreePanel panelGraphics;
	private JButton btnAdd, btnRemove;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program window = new Program();
					window.frmBstMapVisulization.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Program() {
		map = new BSTMapExtended<>(new DefaultComparator<>());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBstMapVisulization = new JFrame();
		frmBstMapVisulization.setTitle("BST Map Visualizer");
		frmBstMapVisulization.setBounds(100, 100, 750, 600);
		frmBstMapVisulization.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmBstMapVisulization.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBstMapVisulization.getContentPane().setLayout(new BorderLayout(0, 0));
		Image icon = new ImageIcon(getClass().getResource("/Icons/BST_Ico.png")).getImage();
        frmBstMapVisulization.setIconImage(icon);
		
		panelActions = new JPanel();
		frmBstMapVisulization.getContentPane().add(panelActions, BorderLayout.SOUTH);
		
		txtKeyvalue = new JTextField();
		txtKeyvalue.setToolTipText("Insert key and value for adding; insert key for removing");
		txtKeyvalue.setFont(roboto.MyFont(0, 12));
		txtKeyvalue.setPreferredSize(new Dimension(72,24));
		txtKeyvalue.setColumns(6);
		txtKeyvalue.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (txtKeyvalue.getText().length() >= 3) {
						btnAdd.doClick();
					} else {
						btnRemove.doClick();
					}
				}
			}
		});
		panelActions.add(txtKeyvalue);
		
		btnAdd = new JButton("Add");
		btnAdd.setBackground(white);
		btnAdd.setBorder(new LineBorder(black, 2, true));
		btnAdd.setFont(roboto_Black.MyFont(0, 12));
		btnAdd.setPreferredSize(new Dimension(72,24));
		btnAdd.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
            	btnAdd.setBackground(Color.LIGHT_GRAY);
            }
            public void mouseExited(MouseEvent e) {
            	btnAdd.setBackground(white); 
            }
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtKeyvalue.getText().length() > 1) {
					String[] keyvalue = txtKeyvalue.getText().split(",");
					try {
						map.put(Integer.parseInt(keyvalue[0]), keyvalue[1].charAt(0));
						panelGraphics.updateTree(map);
						panelGraphics.repaint();
					} catch (NumberFormatException | InvalidKeyException e1) {
						new CustomPane().showCustomDialog("Please, insert a valid key and value", "Invalid key and value");
					}
				} else {
					new CustomPane().showCustomDialog("Please, insert a valid key and value", "Invalid key and value");
				}
			}
		});
		panelActions.add(btnAdd);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBackground(white);
		btnRemove.setBorder(new LineBorder(black, 2, true));
		btnRemove.setFont(roboto_Black.MyFont(0, 12));
		btnRemove.setPreferredSize(new Dimension(72,24));
		btnRemove.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
            	btnRemove.setBackground(Color.LIGHT_GRAY);
            }
            public void mouseExited(MouseEvent e) {
            	btnRemove.setBackground(white); 
            }
		});
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!map.isEmpty()) {
					if (!txtKeyvalue.getText().isBlank()) {
						String keyvalue = txtKeyvalue.getText();
						try {
							map.remove(Integer.parseInt(keyvalue));
							panelGraphics.updateTree(map);
							panelGraphics.repaint();
						} catch (NumberFormatException | InvalidKeyException e1) {
							new CustomPane().showCustomDialog("Please, insert a valid key", "Invalid key");
						}
					} else {
						new CustomPane().showCustomDialog("Please, insert a valid key", "Invalid key");
					}
				} else {new CustomPane().showCustomDialog("Please, add any entry", "Map empty");}
			}
		});
		panelActions.add(btnRemove);
		
		panelGraphics = new TreePanel(map);
		panelGraphics.setBackground(Color.LIGHT_GRAY);
		frmBstMapVisulization.getContentPane().add(panelGraphics, BorderLayout.CENTER);
	}
}
