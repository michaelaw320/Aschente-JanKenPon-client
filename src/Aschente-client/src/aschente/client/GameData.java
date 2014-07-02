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
    public static String Player2Name = "";
    public static int Score;
    public static int ScoreP2;
    public static ArrayList<String> RoomList;
    public static String RoomName = "";
    public static int Round = 1; 
    public static int ToRound = 5;
    public static int Countdown;
    
    /**
     * Reset dataPlayer dan dataMap
     */
    public static void reset()
    {
        RoomList.clear();
    }
    
}
