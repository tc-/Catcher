/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package System;


public interface IPositionProvider {

    static final int LOC_STAT_NOT_PRESSENT = -1;
    static final int LOC_STAT_INACTIVE = 0;
    static final int LOC_STAT_ACTIVE = 1;
    static final int DIR_STAT_NOT_PRESSENT = -1;
    static final int DIR_STAT_INACTIVE = 0;
    static final int DIR_STAT_ACTIVE = 1;

    Position getLastPosition();
    int getLastAccuracy();
    Direction getLastDirection();

    int getLocationStatus();
    int getDirectionStatus();

    void setPositionListener(PositionListener listener);

    void setEnabled(boolean enabled);
    boolean getEnabled();
    
}
