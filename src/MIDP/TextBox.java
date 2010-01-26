/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */
package MIDP;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class TextBox {
    private int yScroll=0;
    private Font font = Font.getDefaultFont();
    private int spaceWidth;
    private int lineHeight;
    private int x,y,width,height;
    private String s="";
    private int nofLines;

    TextBox(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        spaceWidth=font.charWidth(' ');
        lineHeight=font.getHeight();
    }

    public void setText(String s) {
        yScroll = 0;
        nofLines = 1; // Recalculated when painted. Ugly hack.
        this.s = s;
    }

    public void scrollUp() {
        yScroll = (++yScroll>nofLines? nofLines:yScroll);
    }

    public void scrollDown() {
        yScroll = (--yScroll<0? 0:yScroll);
    }

    public void paint(Graphics g) {
        g.setClip(x, y, width, height);
        g.setColor(CatcherCanvas.COLOR_BACKGROUND);
        g.fillRect(x, y, width, height);
        g.setColor(CatcherCanvas.COLOR_TEXT);
        String word="";
        int wordWidth;
        int xPos=x;
        int yPos=y-yScroll*lineHeight;
        nofLines = 0;
        for (int i=0;i<s.length();i++) {
            switch (s.charAt(i)) {
                case '\n':
                case ' ':
                    wordWidth = font.stringWidth(word);
                    if (xPos+wordWidth > width) {
                        xPos = wordWidth+spaceWidth;
                        yPos += lineHeight;
                        ++nofLines;
                        g.drawString(word, x, yPos, Graphics.TOP|Graphics.LEFT);
                    } else {
                        g.drawString(word, xPos, yPos, Graphics.TOP|Graphics.LEFT);
                        xPos += wordWidth+spaceWidth;
                    }
                    word = "";
                    if (s.charAt(i) == '\n') {
                        xPos = x;
                        yPos += lineHeight;
                        ++nofLines;
                    }
                    break;
                default:
                    word += s.charAt(i);
            }
        }
        g.drawString(word, xPos, yPos, Graphics.TOP|Graphics.LEFT);
        nofLines -= height/lineHeight;
    }
}
