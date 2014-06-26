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
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Michael
 */
public class ImageLoader extends JPanel
{
    private static ArrayList<Image> allImages = new ArrayList<>();  
    
    private static Image loadImage(String path)
    {
        Image load = null;
        load = new ImageIcon(path).getImage();
        return load;
    }
    
    public static void loadAllImages()
    {
        // Image buat MainMenu
        allImages.add(loadImage("Image/MainMenu/title.png"));
        allImages.add(loadImage("Image/MainMenu/mainmenu_bg.png"));
        allImages.add(loadImage("Image/MainMenu/newgame.png"));
        allImages.add(loadImage("Image/MainMenu/achievement.png"));
        allImages.add(loadImage("Image/MainMenu/options.png"));
        allImages.add(loadImage("Image/MainMenu/about.png"));
        allImages.add(loadImage("Image/MainMenu/exit.png"));
        allImages.add(loadImage("Image/MainMenu/back.png"));
        
        //Image buat level menu
        allImages.add(loadImage("Image/LevelMenu/background.png"));
        allImages.add(loadImage("Image/LevelMenu/cube_mazer(ice).png")); 
        allImages.add(loadImage("Image/LevelMenu/cube_mazer(fire).png"));
        allImages.add(loadImage("Image/LevelMenu/Frozen Cube 800x600.jpg"));
        allImages.add(loadImage("Image/LevelMenu/Burning Cube 800x600.png"));
        
        //Image buat level 1
        allImages.add(loadImage("Image/LevelMenu/level(ice).png"));        
        allImages.add(loadImage("Image/LevelMenu/Ice_Mage.png"));
        allImages.add(loadImage("Image/LevelMenu/locked(ice).png"));
        allImages.add(loadImage("Image/LevelMenu/Unlocked(ice).png"));
        allImages.add(loadImage("Image/LevelMenu/Ice Arrow.png"));
        allImages.add(loadImage("Image/LevelMenu/Ice Arrow 2.png"));
        allImages.add(loadImage("Image/LevelMenu/Back.png"));
        
        //Untuk level 2
        allImages.add(loadImage("Image/LevelMenu/level(fire).png"));        
        allImages.add(loadImage("Image/LevelMenu/fire maze.png"));
        allImages.add(loadImage("Image/LevelMenu/locked(fire).png"));
        allImages.add(loadImage("Image/LevelMenu/Unlocked(fire).png"));
        allImages.add(loadImage("Image/LevelMenu/Flamming Arrow.png"));
        allImages.add(loadImage("Image/LevelMenu/Flamming Arrow2.png"));
        allImages.add(loadImage("Image/LevelMenu/Back Button.png"));
        
        // Image achievement
        allImages.add(loadImage("Image/Other/AchievementBack.png"));
        
        // Image GameMenu
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_path.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_wall.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_hole.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_teleporter.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_gold.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_char_right_left.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_char_up_down.png"));
        
        // Image OptionMenu
        allImages.add(loadImage("Image/OptionMenu/AudioBar.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice.JPG"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/coin.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/ice_notifications.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/OK.png"));
        allImages.add(loadImage("Image/GameMenu/Ice_Maze/OK1.png"));
        
        // Image Fire Maze
        allImages.add(loadImage("Image/GameMenu/Fire_Maze/character.png"));
        allImages.add(loadImage("Image/GameMenu/Fire_Maze/wall.jpg"));
        allImages.add(loadImage("Image/GameMenu/Fire_Maze/hole.jpg"));
        allImages.add(loadImage("Image/GameMenu/Fire_Maze/lava_floor.jpg"));
        allImages.add(loadImage("Image/GameMenu/Fire_Maze/teleporter.jpg"));
        allImages.add(loadImage("Image/GameMenu/Fire_Maze/coin.png"));
        allImages.add(loadImage("Image/GameMenu/Fire_Maze/background.jpg"));
        allImages.add(loadImage("Image/Other/Choice_Border.png"));
        allImages.add(loadImage("Image/Other/Choice_Border_Hover.png"));
        allImages.add(loadImage("Image/Other/cursor.png"));
        allImages.add(loadImage("Image/Other/User_Border.png"));
        allImages.add(loadImage("Image/MainMenu/exit_hover.png"));
    }
    
    public static Image getImage(String imgName)
    {
        if ("title".equalsIgnoreCase(imgName))
        {
            return allImages.get(0);
        }
        else if ("mainmenu_bg".equalsIgnoreCase(imgName))
        {
            return allImages.get(1);
        }
        else if ("newgame".equalsIgnoreCase(imgName))
        {
            return allImages.get(2);
        }
        else if ("achievement".equalsIgnoreCase(imgName))
        {
            return allImages.get(3);
        }
        else if ("options".equalsIgnoreCase(imgName))
        {
            return allImages.get(4);
        }
        else if ("about".equalsIgnoreCase(imgName))
        {
            return allImages.get(5);
        }
        else if ("exit".equalsIgnoreCase(imgName))
        {
            return allImages.get(6);
        }
        else if ("back".equalsIgnoreCase(imgName))
        {
            return allImages.get(7);
        }
        else if ("background".equalsIgnoreCase(imgName))
        {
            return allImages.get(8);
        }
        else if ("cube_mazer_ice".equalsIgnoreCase(imgName))
        {
            return allImages.get(9);
        }
        else if ("cube_mazer_fire".equalsIgnoreCase(imgName))
        {
            return allImages.get(10);
        }
        else if ("frozen_cube".equalsIgnoreCase(imgName))
        {
            return allImages.get(11);
        }
        else if ("burning_cube".equalsIgnoreCase(imgName))
        {
            return allImages.get(12);
        }
        
        //khusus level ice
        else if ("level_ice".equalsIgnoreCase(imgName))
        {
            return allImages.get(13);
        }
        else if ("ice_mage".equalsIgnoreCase(imgName))
        {
            return allImages.get(14);
        }
        else if ("locked_ice".equalsIgnoreCase(imgName))
        {
            return allImages.get(15);
        }
        else if ("unlocked_ice".equalsIgnoreCase(imgName))
        {
            return allImages.get(16);
        }
        else if ("ice_arrow".equalsIgnoreCase(imgName))
        {
            return allImages.get(17);
        }
        
        else if ("ice_arrow_2".equalsIgnoreCase(imgName))
        {
            return allImages.get(18);
        }
        else if ("back_ice".equalsIgnoreCase(imgName))
        {
            return allImages.get(19);
        }
        
        //bagian fire(level 2)
        else if ("level_fire".equalsIgnoreCase(imgName))
        {
            return allImages.get(20);
        }
        else if ("fire_maze".equalsIgnoreCase(imgName))
        {
            return allImages.get(21);
        }
        else if ("locked_fire".equalsIgnoreCase(imgName))
        {
            return allImages.get(22);
        }
        else if ("unlocked_fire".equalsIgnoreCase(imgName))
        {
            return allImages.get(23);
        }
        else if ("fire_arrow".equalsIgnoreCase(imgName))
        {
            return allImages.get(24);
        }
        else if ("fire_arrow_2".equalsIgnoreCase(imgName))
        {
            return allImages.get(25);
        }
        else if ("back_fire".equalsIgnoreCase(imgName))
        {
            return allImages.get(26);
        }        
        else if ("AchievementBack".equalsIgnoreCase(imgName))
        {
            return allImages.get(27);
        }
        
        else if ("Ice_Path".equalsIgnoreCase(imgName))
        {
            return allImages.get(28);
        }
        else if ("Ice_Wall".equalsIgnoreCase(imgName))
        {
            return allImages.get(29);
        }
        else if ("Ice_Hole".equalsIgnoreCase(imgName))
        {
            return allImages.get(30);
        }else if ("Ice_Teleporter".equalsIgnoreCase(imgName))
        {
            return allImages.get(31);
        }
        else if ("Ice_Gold".equalsIgnoreCase(imgName))
        {
            return allImages.get(32);
        }
        else if ("Ice_Char_RL".equalsIgnoreCase(imgName))
        {
            return allImages.get(33);
        }
        else if ("Ice_Char_UD".equalsIgnoreCase(imgName))
        {
            return allImages.get(34);
        }       
        else if("AudioBar".equalsIgnoreCase(imgName))
        {
            return allImages.get(35);
        }
        else if("ice".equalsIgnoreCase(imgName))
        {
            return allImages.get(36);
        }
        else if("coin".equalsIgnoreCase(imgName))
        {
            return allImages.get(37);
        }
        else if("ice_notification".equalsIgnoreCase(imgName))
        {
            return allImages.get(38);
        }
        else if("ice_ok".equalsIgnoreCase(imgName))
        {
            return allImages.get(39);
        }
        else if("ice_ok1".equalsIgnoreCase(imgName))
        {
            return allImages.get(40);
        }
        else if("fire_char".equalsIgnoreCase((imgName)))
        {
            return allImages.get(41);
        }
        else if("fire_wall".equalsIgnoreCase(imgName))
        {
            return allImages.get(42);
        }
        else if("fire_hole".equalsIgnoreCase(imgName))
        {
            return allImages.get(43);
        }
        else if("fire_floor".equalsIgnoreCase(imgName))
        {
            return allImages.get(44);
        }
        else if("fire_teleporter".equalsIgnoreCase(imgName))
        {
            return allImages.get(45);
        }
        else if("fire_coin".equalsIgnoreCase(imgName))
        {
            return allImages.get(46);
        }  
        else if("fire_back".equalsIgnoreCase(imgName))
        {
            return allImages.get(47);
        }
        else if ("border".equalsIgnoreCase(imgName))
        {
            return allImages.get(48);
        }
        else if ("border_hover".equalsIgnoreCase(imgName))
        {
            return allImages.get(49);
        }
        else if ("cursor".equalsIgnoreCase(imgName))
        {
            return allImages.get(50);
        }
        else if ("user_border".equalsIgnoreCase(imgName))
        {
            return allImages.get(51);
        }
        else if ("exit_hover".equalsIgnoreCase(imgName))
        {
            return allImages.get(52);
        }
        return null;
    }
    
}
