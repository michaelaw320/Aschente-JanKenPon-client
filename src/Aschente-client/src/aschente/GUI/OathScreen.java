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
package aschente.GUI;

import aschente.client.*;
import static aschente.client.AschenteClient.NGNLFont;
import static aschente.client.AschenteClient.gameFrame;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;

/**
 *
 * @author Michael
 */
public class OathScreen extends Scene implements ActionListener {

    private final JButton ContinueButton;
    private final ArrayList<String> Oaths;
    private int mode;
    /* Mode = 0 : waiting connection ; Mode = 1 : login failed return to prev screen ; Mode 2 : login success */

    private final Image sora;
    private final Image shiro;
    private final Image jibril;
    private final Image steph;
    private Image disp1;
    private Image disp2;
    private int random1;
    private int random2;
    
    public OathScreen() {
        super("OathScreen");
        ContinueButton = new JButton();
        ContinueButton.setFont(NGNLFont);
        
        Oaths = new ArrayList<>();
        Scanner Sc;
        try {
            Sc = new Scanner(new File("Resources\\ten-oaths.txt"));
            while (Sc.hasNextLine()) {
                Oaths.add(Sc.nextLine());
            }
        } catch (FileNotFoundException ex) {
            Oaths.add("File ten-oaths.txt is missing!");
        }
        sora = ImageLoader.getImage("sora");
        shiro = ImageLoader.getImage("shiro");
        jibril = ImageLoader.getImage("jibril");
        steph = ImageLoader.getImage("steph");
        
    }

    public void LoadContent() {
        ContinueButton.addActionListener(this);
        gameFrame.getContentPane().add(ContinueButton);

        random1 = (int) (Math.random()*4 + 1);
        do {
            random2 = (int) (Math.random()*4 + 1);
        } while (random1 == random2);
        switch (random1) {
            case 1:
                disp1 = sora;
                break;
            case 2:
                disp1 = shiro;
                break;
            case 3:
                disp1 = jibril;
                break;
            case 4:
                disp1 = steph;
                break;
            default:
                System.err.println("randomerr");     
        }
        switch (random2) {
            case 1:
                disp2 = sora;
                break;
            case 2:
                disp2 = shiro;
                break;
            case 3:
                disp2 = jibril;
                break;
            case 4:
                disp2 = steph;
                break;
            default:
                System.err.println("randomerr");     
        }
        mode = 2;
        ContinueButton.setText("CONTINUE");
    }

    @Override
    public void Initialize() {
        gameFrame.getContentPane().removeAll();
        gameFrame.repaint();
        this.LoadContent();
    }

    @Override
    public void Update() {
        if (mode == 0) {
        /* Check for network message on successful connection or not */
            ContinueButton.setEnabled(false);
        }
        else if (mode == 1) {
            ContinueButton.setEnabled(true);
            ContinueButton.setText("GO BACK");
        }
        else if (mode == 2) {
            ContinueButton.setEnabled(true);
        }
                
    }

    @Override
    public void Draw() {
        gameFrame.revalidate();
        ContinueButton.setBounds((gameFrame.getWidth()/2)-125,gameFrame.getHeight()-100, 250, 50);
        this.paint(gameFrame.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        int len;
        int h;
        int start;
        Font DisplayFont;
        g2D.drawImage(disp1, 10, 380, 235,325, gameFrame);
        g2D.drawImage(disp2, gameFrame.getWidth()-245, 380,235,325, gameFrame);
        /* Oath Render */
        h = gameFrame.getHeight()/10;
        for(String message : Oaths) {
            if(gameFrame.getHeight() == AschenteClient.ResolutionHeight) {
                DisplayFont = NGNLFont.deriveFont(16f);
            } else {
                DisplayFont = NGNLFont.deriveFont(13f*gameFrame.getWidth()/AschenteClient.ResolutionWidth);
            }
            g2D.setFont(DisplayFont);
            len = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();  
            h += (int) g2D.getFontMetrics().getStringBounds(message, g2D).getHeight();
            start = gameFrame.getWidth()/2 - len/2;  
            g2D.drawString(message, start, h);
        }
        if(mode == 1) {
            String message = "LOGIN FAILED!";
            DisplayFont = NGNLFont.deriveFont(46f);
            g2D.setFont(DisplayFont);
            len = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();
            start = gameFrame.getWidth()/2 - len/2;
            g2D.drawString(message, start, gameFrame.getHeight()-100);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ContinueButton)) {
            //network send username here
            //to do checking before switch scene
            if(mode == 1) {
                SceneManager.SwitchScene("UserLogin");
            } else if (mode == 2) {
                SceneManager.SwitchScene("MainMenu");
            }
        }
    }
}
