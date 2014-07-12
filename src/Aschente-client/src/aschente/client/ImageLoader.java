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
    private static final ArrayList<Image> allImages = new ArrayList<>();  
    
    private static Image loadImage(String path)
    {
        return new ImageIcon(path).getImage();
    }
    
    public static void loadAllImages()
    {
        // Images for Graphics
        allImages.add(loadImage("Resources\\Images\\image_chara_siro.png"));
        allImages.add(loadImage("Resources\\Images\\image_chara_sora.png"));
        allImages.add(loadImage("Resources\\Images\\image_chara_zibu.png"));
        allImages.add(loadImage("Resources\\Images\\image_chara_steph.png"));
        allImages.add(loadImage("Resources\\Images\\image_chara_steph_lose.png"));
    }
    
    public static Image getImage(String imgName)
    {
        if ("shiro".equalsIgnoreCase(imgName)) {
            return allImages.get(0);
        } else if ("sora".equalsIgnoreCase(imgName)) {
            return allImages.get(1);
        } else if ("jibril".equalsIgnoreCase(imgName)) {
            return allImages.get(2);
        } else if ("steph".equalsIgnoreCase(imgName)) {
            return allImages.get(3);
        } else if ("steph_lose".equalsIgnoreCase(imgName)) {
            return allImages.get(4);
        }
        return null;
    }
    
}
