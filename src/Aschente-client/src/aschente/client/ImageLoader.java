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
        return new ImageIcon(path).getImage();
    }
    
    public static void loadAllImages()
    {
        // Image Background
        allImages.add(loadImage("Images\\Scene_of_disboard.png"));
        
    }
    
    public static Image getImage(String imgName)
    {
        if ("background_image".equalsIgnoreCase(imgName))
        {
            return allImages.get(0);
        }
        return null;
    }
    
}
