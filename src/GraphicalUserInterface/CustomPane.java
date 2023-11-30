package GraphicalUserInterface;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import Fonts.Roboto_Black;

/**
 * This class creates a custom JOptionPane.
 */
public class CustomPane extends JOptionPane{
	private Color white = new Color(255, 255, 255);
	private Color black = new Color(0, 0, 0);
	private Roboto_Black fontb = new Roboto_Black();
	
	/**
	 * This method creates a custom JOptionPane with a message and a title.
	 */
    public void showCustomDialog(String message, String title) {
        JDialog dialog = createDialog(null, title);
        Image warning = new ImageIcon(getClass().getResource("/Icons/warning.png")).getImage();
        dialog.setIconImage(warning);
        dialog.setModal(true);

        JPanel panel = new JPanel();
        JButton okButton = new JButton("Accept");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		okButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	okButton.setBackground(Color.LIGHT_GRAY);
            }
            public void mouseExited(MouseEvent e) {
            	okButton.setBackground(white); 
            }
        });
		okButton.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dialog.dispose();
				}
			}
		});
		okButton.setActionCommand("Accept");
		okButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		okButton.setPreferredSize(new Dimension(72, 24));
		okButton.setBorder(new LineBorder(black, 2));
		okButton.setBackground(white);
		okButton.setFont(fontb.MyFont(0, 12));
        panel.add(okButton);

        setMessage(message);
        setOptions(new Object[]{panel});

        dialog.setVisible(true);
    }
}
