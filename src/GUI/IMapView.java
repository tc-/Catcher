/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import GUI.IView;
import System.Position;

/**
 *
 * @author tc
 */
public interface IMapView extends IView {

    Position getCenter();

    int getZoom();

    void setCenter(Position center);

    void setZoom(int zoom);

}
