/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import GUI.CatcherMain;
import GUI.IMapView;
import System.Direction;
import System.ICacheProvider;
import System.IMapProvider;
import System.IPositionProvider;
import System.Position;
import System.IImageLoader;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class MapView extends CatcherCanvas implements IMapView {

    private Position center = new Position(57.77947, 14.22107);

    private int zoom=10;

    private String msg = "Greetings Catcher";

    private IMapProvider mapProvider = null;
    private final IPositionProvider positionProvider;

    /**
     * constructor
     */
    public MapView(CatcherMain main, ViewResources viewResources,
            ICacheProvider cacheProvider, IPositionProvider positionProvider,
            IMapProvider mapProvider, IImageLoader imageLoader) {
        super(main);
        
        setFullScreenMode(true);
        this.viewResources = viewResources;
        
        this.mapProvider = mapProvider;
        this.positionProvider = positionProvider;
        zoom = mapProvider.zoomOut(0); // Get lowest zoom level
        center = this.positionProvider.getLastPosition();
    }

    public Position getCenter() {
        return center;
    }

    public void setCenter(Position center) {
        this.center = center;
        repaint();
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public Direction getDirection() {
        return null;
    }

    public void setDirection(Direction newDirection) {

    }

    /**
     * paint
     */
    public void paintView(Graphics g) {
        if (center == null) {
            center = positionProvider.getLastPosition();
            System.out.println("center is null");
        }
        // Map
        g.setClip(0, 0, getWidth(), getHeight());
        Image image = (Image)mapProvider.getMap(center,
                getWidth(), getHeight(), zoom);
        if (image != null) {
            g.drawImage(image, 0, 0, Graphics.TOP|Graphics.LEFT);
        }

        // Crosshair, center of view
        g.setColor(0xff0000);
        g.drawLine(getWidth()/2-5, getHeight()/2,
                getWidth()/2+5, getHeight()/2);
        g.drawLine(getWidth()/2, getHeight()/2-5,
                getWidth()/2, getHeight()/2+5);
    }
    
    protected  void keyPressedView(int keyCode) {
        switch(getGameAction(keyCode)) {
            case UP:
                center = mapProvider.XYtoPosition(getWidth()/2, getHeight()/2-50, center, getWidth(), getHeight(), zoom);
                break;
            case DOWN:
                center = mapProvider.XYtoPosition(getWidth()/2, getHeight()/2+50, center, getWidth(), getHeight(), zoom);
                break;
            case LEFT:
                center = mapProvider.XYtoPosition(getWidth()/2-50, getHeight()/2, center, getWidth(), getHeight(), zoom);
                break;
            case RIGHT:
                center = mapProvider.XYtoPosition(getWidth()/2+50, getHeight()/2, center, getWidth(), getHeight(), zoom);
                break;
        }
        switch(keyCode) {
            case KEY_NUM1:
                zoom = mapProvider.zoomOut(zoom);
                break;
            case KEY_NUM3:
                zoom = mapProvider.zoomIn(zoom);
                break;
        }
        this.repaint();
    }
    
    public void activate() {
        String[] viewItems = {"Zoom in", "Zoom out", "Auto follow",
        "Add location", "Select map"};
        menu.viewItems(viewItems);
    }

    public void deactivate() {
        menu.clearViewItems();
    }

    public void maptileDownloaded() {
        this.repaint();
    }

}
