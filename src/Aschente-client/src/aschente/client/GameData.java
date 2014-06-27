/*
 * Copyright (C) 2014 Michael
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package aschente.client;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class GameData {
    public static String PlayerName;
    public static int Score;
    public static ArrayList<String> RoomList;
    public static String RoomName;
    public static int Round; 
    public static Image background_image = ImageLoader.getImage("Background");
    
    /**
     * Reset dataPlayer dan dataMap
     */
    public static void reset()
    {
        RoomList.clear();
    }
    
}
