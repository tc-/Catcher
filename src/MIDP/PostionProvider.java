/*
 * Catcher
 *
 * License: GPL v3
 * Authors: richard_jonsson@hotmail.com, tommyc@lavabit.com
 */

package MIDP;

import System.IPositionProvider;
import System.Position;


public class PostionProvider implements IPositionProvider {

    public Position getLastPosition() {
        return new Position(47.1, 14.8);
    }

}
