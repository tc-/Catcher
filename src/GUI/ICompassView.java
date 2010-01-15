/*
 * Catcher, Catcher.java
 *
 * License: GPL v2
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package GUI;

import System.Direction;
import System.Position;


/**
 * Displays a compass
 */
public interface ICompassView extends IView {

    Direction getMyDirection();

    Position getMyPosition();

    Position getTargetPosition();

    void setMyDirection(Direction myDirection);

    void setMyPosition(Position myPosition);

    void setTargetPosition(Position targetPosition);

}
