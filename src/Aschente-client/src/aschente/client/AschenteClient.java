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

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Michael
 */
public class AschenteClient {

    static public boolean gameRunning = true;
    static public JFrame gameFrame = new JFrame();
    static public Graphics g = gameFrame.getGraphics();
    static public final int ResolutionWidth = 800;
    static public final int ResolutionHeight = 600;
    
    private final MidiPlayer BGM;
    
    private AschenteClient() {
    //    GameData.loadMap();
    //    GameData.loadPlayer("player.xml");
        
        ImageLoader.loadAllImages();
        prepareFrame();
        
        PrepareScenes();
        BGM = new MidiPlayer();
        BGM.play();
        gameLoop();
    }
    
    /**
     * Menyiapkan frame untuk game Cube Mazer
     */
    private static void prepareFrame()
    {
        gameFrame.setTitle("JanKenPON!!!");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.pack();
        gameFrame.setSize(new Dimension(ResolutionWidth,ResolutionHeight));
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setVisible(true);
            
    }
    
        /**
     * Me-load semua scene yang akan digunakan di dalam game.
     */
    private static void PrepareScenes()
    {
        SceneManager.Initialize();
        
      //  SceneManager.AddScene(new MainMenuGUI());
      //  SceneManager.AddScene(new LevelMenuGUI());
        
    }
    
    private static void gameLoop()
    {
        while(gameRunning) 
        { 
            long time = System.currentTimeMillis(); 
            final int fps = 23;
            
            SceneManager.Draw(); 
            SceneManager.Update(); 
                
            time = (1000 / fps) - (System.currentTimeMillis() - time); 
            if (time > 0) 
            { 
                try 
                { 
                    Thread.sleep(time); 
                } 
                catch(Exception e){} 
            } 
        }   
      //  GameData.savePlayer("player.xml");
        System.exit(0);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AschenteClient Aschente = new AschenteClient();
    }
    
}
