package GraphicalUserInterface;

import java.awt.*;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import ADTMapBST.*;
import Fonts.Roboto_Black;

/**
 * This class creates a JPanel that draws a binary search tree.
 */
public class TreePanel extends JPanel{
    private BSTNode<Integer,Character> rootNode, leftRootNode;
    private Color white = new Color(255, 255, 255);
	private Color black = new Color(0, 0, 0);
	private Roboto_Black roboto_Black = new Roboto_Black();
    private BSTMapExtended<Integer,Character> map;
    private Point centralPoint = new Point(getWidth() / 2, 0);
    private double scale = 1.0;
    private Graphics2D g2d;

    public TreePanel(BSTMapExtended<Integer,Character> map) {
        this.map = map;
        this.rootNode = map.root();

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    scale += 0.1;
                    centralPoint.translate(-200, 0);
                } else {
                    scale -= 0.1;
                    centralPoint.translate(200, 0);
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3.0f));
        g2d.setFont(roboto_Black.MyFont(0, 12));
        g2d.scale(scale, scale);
        g2d.translate(centralPoint.getX(), centralPoint.getY());
        if (rootNode.getLeft() != null || rootNode.getRight() != null) {
            drawNode(g2d, rootNode, getWidth() / 2, 50, 50); 
        }
        
    }

    /**
     * This method draws a node and its children.
     */
    private void drawNode(Graphics2D g2d, BSTNode<Integer,Character> node, int x, int y, int diameter) {
        int width = diameter * 2;
        Ellipse2D oval = new Ellipse2D.Double(x - width / 2, y - diameter / 2, width, diameter);
        g2d.setColor(white);
        g2d.fill(oval);
        g2d.setColor(black);
        g2d.draw(oval);

        FontMetrics fm = g2d.getFontMetrics();
        String text = "Dummy node";
        if (node.getKey() != null) {
            text ="Key: " + node.getKey() + " Value: " + node.getValue();
        }
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent() - fm.getDescent();
        int textX = x - textWidth / 2;
        int textY = y + textHeight / 2;
        g2d.drawString(text, textX, textY); 

        int xOffset = 100; 
        int yOffset = 80; 
        int depth = map.depth(node);
        
        if (depth == 1) {
            leftRootNode = rootNode.getLeft();
        }
        
        switch (depth) {
            case 0:
                if (node.getLeft() != null) {
                    drawNode(g2d, node.getLeft(), x - xOffset, y + yOffset, diameter);
                    g2d.drawLine(x, y + diameter/2, x - xOffset, y + yOffset - diameter / 2);
                }
                if (node.getRight() != null) {
                    drawNode(g2d, node.getRight(), x + xOffset, y + yOffset, diameter);
                    g2d.drawLine(x, y + diameter/2, x + xOffset, y + yOffset - diameter / 2);
                }
                break;
            default:
                //Node is on the left subtree
                if (map.leftOrRight(node, leftRootNode)) {
                    if (node.getLeft() != null) {
                        drawNode(g2d, node.getLeft(), x - xOffset - (110 * depth), y + yOffset, diameter);
                        g2d.drawLine(x, y + diameter/2, x - xOffset - (110 * depth), y + yOffset - diameter / 2);
                    }
                    if (node.getRight() != null) {
                        drawNode(g2d, node.getRight(), x, y + yOffset * 2, diameter);
                        g2d.drawLine(x, y + diameter/2, x, y + yOffset * 2 - diameter / 2);
                    }
                } else { //Node is on the right subtree
                    if (node.getLeft() != null) {
                        drawNode(g2d, node.getLeft(), x, y + yOffset * 2, diameter);
                        g2d.drawLine(x, y + diameter/2, x, y + yOffset * 2 - diameter / 2);
                    }
                    if (node.getRight() != null) {
                        drawNode(g2d, node.getRight(), x + xOffset + (110 * depth), y + yOffset, diameter);
                        g2d.drawLine(x, y + diameter/2, x + xOffset + (110 * depth), y + yOffset - diameter / 2);
                    }
                }
                break;
        }
    }

    /**
     * This method updates the tree.
     */
    public void updateTree(BSTMapExtended<Integer,Character> map) {
        this.map = map;
        rootNode = map.root();
    }
}
