package Fonts;

import java.awt.Font;
import java.io.InputStream;

/**
 * This class implements a font loader for Roboto Black font.
 * It contains a reference to the font.
 */
public class Roboto_Black {
	private Font font = null;
	
	public Roboto_Black() {
		try {
			InputStream is =  getClass().getResourceAsStream("Roboto-Black.ttf");
	        font = Font.createFont(Font.TRUETYPE_FONT, is);
	    } catch (Exception ex) {
	    	//If there is an error, the default ARIAL font is loaded.
	        System.err.println("Roboto-Black.ttf :" + " No se cargo la fuente");
	        font = new Font("Arial", Font.PLAIN, 14);            
	    }
	}
	
	/**
     * Method that configures the characteristics of the font.
     * @param style Indicates the font style (PLAIN = 0 , BOLD = 1 , ITALIC = 2)
     * @param size Indicates the font size
     * @return Returns the font to be used
     */
    public Font MyFont(int style, float size) {
        Font tfont = font.deriveFont(style, size);
        return tfont;
    }
}
