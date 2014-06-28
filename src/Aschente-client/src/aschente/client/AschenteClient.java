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

import aschente.GUI.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Michael
 */
public class AschenteClient {

    static public boolean gameRunning = true;
    static public JFrame gameFrame;
    static public Graphics g;
    static public final int ResolutionWidth = 800;
    static public final int ResolutionHeight = 600;
    static public Font NGNLFont;
    
    private final MidiPlayer BGM;
    
    private AschenteClient() {
    //    GameData.loadMap();
    //    GameData.loadPlayer("player.xml");
        gameFrame = new JFrame();
        g = gameFrame.getGraphics();
        
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
        try {
            gameFrame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("Resources\\Images\\Scene_of_disboard.png")))));
            NGNLFont = Font.createFont(Font.TRUETYPE_FONT,new File("Resources\\Font\\NGNL.ttf"));
            NGNLFont = NGNLFont.deriveFont(36f);
        } catch (IOException | FontFormatException ex) {}
        gameFrame.setFont(NGNLFont);
       
    }
    
        /**
     * Me-load semua scene yang akan digunakan di dalam game.
     */
    private static void PrepareScenes()
    {
        SceneManager.Initialize();
        
        SceneManager.AddScene(new UserLogin());
        SceneManager.AddScene(new MainMenu());
        SceneManager.AddScene(new GameMode());
        
        SceneManager.SwitchScene("UserLogin");
        
    }
    
    private static void gameLoop()
    {
        while(gameRunning) 
        { 
            long time = System.currentTimeMillis(); 
            final int fps = 60;
            
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
