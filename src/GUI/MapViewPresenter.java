/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.ICacheProvider;
import System.IMapProvider;
import System.IPositionProvider;


public class MapViewPresenter {

    private final CatcherMain main;
    private final IMapView view;
    private final ICacheProvider cacheProvider;
    private final IPositionProvider positionProvider;
    private final IMapProvider mapProvider;

    public MapViewPresenter(CatcherMain main, IMapView view, ICacheProvider cacheProvider, IPositionProvider positionProvider,
            IMapProvider mapProvider) {
        this.main = main;
        this.view = view;
        this.cacheProvider = cacheProvider;
        this.positionProvider = positionProvider;
        this.mapProvider = mapProvider;
    }

    public void keyPressed(int keyCode) {
        
    }

}
