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

    private Position mapCenter = new Position(57.77947, 14.22107);

    private int zoom=10;
    private boolean autoFollow=true;

    private IMapProvider mapProvider = null;
    private final IPositionProvider positionProvider;

    // Careful when changing globalItems! menuAction() assumes a certain order!
    private String[] viewItems = {"Zoom in", "Zoom out", "Auto follow",
        "Add location", "Select map"};

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
        mapCenter = this.positionProvider.getLastPosition();
    }

    public Position getCenter() {
        return mapCenter;
    }

    public void setCenter(Position center) {
        if (autoFollow) {
            this.mapCenter = center;
            repaint();
        }
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
        if (mapCenter == null) {
            mapCenter = positionProvider.getLastPosition();
            System.out.println("center is null");
        }
        // Map
        g.setClip(0, 0, getWidth(), getHeight());
        Image image = (Image)mapProvider.getMap(mapCenter,
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

    /**
     * Called when a menu item has been selected
     * @param menuItem: index in view menu array
     */
    void menuActionView(int menuItem) {
        System.out.println("meuActionView "+String.valueOf(menuItem));
        switch(menuItem) {
            case 0:
                // Zoom in
                System.out.println("menu Zoom in");
                zoom = mapProvider.zoomIn(zoom);
                repaint();
                break;
            case 1:
                // Zoom out
                System.out.println("menu Zoom out");
                zoom = mapProvider.zoomOut(zoom);
                repaint();
                break;
            case 2:
                // Auto follow
                System.out.println("menu Auto follow");
                mapCenter = positionProvider.getLastPosition();
                autoFollow = true;
                break;
            case 3:
                // Add location
                System.out.println("menu Add location (not impl)");
                break;
            case 4:
                // Select map
                System.out.println("menu Select map (not impl)");
                break;
        }
        System.out.println("menuActionItem: "+String.valueOf(menuItem));
    }

    protected  void keyPressedView(int keyCode) {
        switch(getGameAction(keyCode)) {
            case UP:
                mapCenter = mapProvider.XYtoPosition(getWidth()/2, getHeight()
                        /2-50, mapCenter, getWidth(), getHeight(), zoom);
                autoFollow = false;
                break;
            case DOWN:
                mapCenter = mapProvider.XYtoPosition(getWidth()/2, getHeight()
                        /2+50, mapCenter, getWidth(), getHeight(), zoom);
                autoFollow = false;
                break;
            case LEFT:
                mapCenter = mapProvider.XYtoPosition(getWidth()/2-50, 
                        getHeight()/2, mapCenter, getWidth(), getHeight(), zoom);
                autoFollow = false;
                break;
            case RIGHT:
                mapCenter = mapProvider.XYtoPosition(getWidth()/2+50, 
                        getHeight()/2, mapCenter, getWidth(), getHeight(), zoom);
                autoFollow = false;
                break;
            case FIRE:
                mapCenter = positionProvider.getLastPosition();
                autoFollow = true;
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
        repaint();
    }
    
    public void activate() {
        menu.viewItems(viewItems);
    }

    public void deactivate() {
        menu.clearViewItems();
    }

    public void maptileDownloaded() {
        this.repaint();
    }

}
