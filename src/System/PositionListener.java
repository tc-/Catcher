/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;


public interface PositionListener {

    void PositionStatusChanged(IPositionProvider sender, int status);
    void PositionUpdated(IPositionProvider sender, Position newPosition, int accuracy);
    void DirectionUpdated(IPositionProvider sender, Direction newDirection);

}
