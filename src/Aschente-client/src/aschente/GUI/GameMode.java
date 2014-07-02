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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Michael
 */
public class GameMode extends Scene implements MouseListener, ActionListener, WindowListener {

    private JButton rock;
    private JButton scissor;
    private JButton paper;
    private JButton aschente;
    private JButton nothing;
    private int mode;
    private boolean stopThread;
    private Thread counter;
    
    /* Mode = 0 before aschente, Mode = 1 round start, Mode = 2 countdown started */
    
    public GameMode() {
        super("GameMode");
        try {
            rock = new JButton(new ImageIcon(ImageIO.read(new File("Resources\\Images\\gou.jpg"))));
            scissor = new JButton(new ImageIcon(ImageIO.read(new File("Resources\\Images\\choki.jpg"))));
            paper = new JButton(new ImageIcon(ImageIO.read(new File("Resources\\Images\\paa.jpg"))));
        } catch (IOException ex) {
            rock = new JButton("Rock");
            scissor = new JButton("Scissor");
            paper = new JButton("Paper");
            rock.setFont(NGNLFont);
            scissor.setFont(NGNLFont);
            paper.setFont(NGNLFont);
        }
        aschente = new JButton("ASCHENTE");
        aschente.setFont(NGNLFont);
        nothing = new JButton();
        mode = 0;
        stopThread = false;
    }

    public void LoadContent() {
        gameFrame.getContentPane().add(rock);
        gameFrame.getContentPane().add(scissor);
        gameFrame.getContentPane().add(paper);
        gameFrame.getContentPane().add(aschente);

        rock.addActionListener(this);
        scissor.addActionListener(this);
        paper.addActionListener(this);
        aschente.addActionListener(this);
        nothing.addActionListener(this);
    }

    @Override
    public void Initialize() {
        gameFrame.getContentPane().removeAll();
        gameFrame.repaint();
        gameFrame.getContentPane().add(this);
        this.LoadContent();
        buttons("disabled");
    }
    
    @Override
    public void Update() {
        if(mode == 1) {
            aschente.setVisible(false);
            buttons("enabled");
            GameData.Countdown = 10;
            countdown();
            mode = 2;
            gameFrame.repaint();
            paint(gameFrame.getGraphics());    
        } else if (mode == 0) {
            aschente.setVisible(true);
        } else if (mode == 3) {
            System.out.println("MODE 3");
            stopThread = false;
            mode = 1;
        }
    }

    @Override
    public void Draw() {
        gameFrame.revalidate();
        
        int start = (gameFrame.getWidth() - (3*130)) / 4;
        
        rock.setBounds(start, gameFrame.getHeight()*2/3, 130, 130);
        scissor.setBounds(start*2+130,gameFrame.getHeight()*2/3,130,130);
        paper.setBounds(start*3+130*2,gameFrame.getHeight()*2/3,130,130);
        
        aschente.setBounds((gameFrame.getWidth()/2)-100, gameFrame.getHeight()/3, 200, 50);
        
        this.paint(gameFrame.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        int len;
        int start;
        String message,message2;
        Font DisplayFont;

        /* Draw Room Name centered above VS */
        GameData.RoomName="TEST ROOM";
        message = GameData.RoomName;
        DisplayFont = NGNLFont.deriveFont(40f);
        g2D.setFont(DisplayFont);
        len = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();  
        start = gameFrame.getWidth()/2 - len/2;  
        g2D.drawString(message, start, 70);
        
        /* Draw centered VS on screen */
        message = "VS";
        DisplayFont = NGNLFont.deriveFont(90f);
        g2D.setFont(DisplayFont);
        len = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();  
        start = gameFrame.getWidth()/2 - len/2;  
        g2D.drawString(message, start, 125);
        
        /* Draw P1 name and score on the left */
        message = GameData.PlayerName;
        DisplayFont = NGNLFont.deriveFont(42f);
        g2D.setFont(DisplayFont);
        start = 25;  
        g2D.drawString(message, start, 100);
        
        message2 = "SCORE: "+Integer.toString(GameData.Score);
        DisplayFont = NGNLFont.deriveFont(32f);
        g2D.setFont(DisplayFont);
        g2D.drawString(message2, start, 125);
        
        /* Draw P2 name and score on the right */
        GameData.Player2Name = "STEPH";
        message = GameData.Player2Name;
        DisplayFont = NGNLFont.deriveFont(42f);
        g2D.setFont(DisplayFont);
        len = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();
        start = gameFrame.getWidth() -25 - len;
        g2D.drawString(message, start, 100);
        
        message2 = "SCORE: "+Integer.toString(GameData.ScoreP2);
        DisplayFont = NGNLFont.deriveFont(32f);
        g2D.setFont(DisplayFont);
        len = (int) g2D.getFontMetrics().getStringBounds(message2, g2D).getWidth();
        start = gameFrame.getWidth() -25 - len;
        g2D.drawString(message2, start, 125);
        
        /* Draw Rounds centered below VS */
        GameData.ToRound=5;
        message = "ROUND: "+Integer.toString(GameData.Round)+" OF "+Integer.toString(GameData.ToRound);
        DisplayFont = NGNLFont.deriveFont(50f);
        g2D.setFont(DisplayFont);
        len = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();  
        start = gameFrame.getWidth()/2 - len/2;  
        g2D.drawString(message, start, 160);
        
        if(mode == 2) {
            /* Draw Countdown centered */
            if(GameData.Countdown > 0) {
                message = Integer.toString(GameData.Countdown);
                DisplayFont = NGNLFont.deriveFont(150f);
                g2D.setFont(DisplayFont);
                len = (int) g2D.getFontMetrics().getStringBounds(message, g2D).getWidth();  
                start = gameFrame.getWidth()/2 - len/2;  
                g2D.drawString(message, start, 300);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(rock)) {
            //network send 
            buttons("disabled");
            countdownStop();
            GameData.Round++;
        } else if (e.getSource().equals(scissor)) {
            //network send scissor
            buttons("disabled");
            countdownStop();
            GameData.Round++;
        } else if (e.getSource().equals(paper)) {
            //network send paper
            buttons("disabled");
            countdownStop();
            GameData.Round++;
        } else if (e.getSource().equals(nothing)) {
            //network send nothing
            buttons("disabled");
            GameData.Round++;
        } else if (e.getSource().equals(aschente)) {
            mode = 1;
            gameFrame.repaint();
            paint(gameFrame.getGraphics());     
        } 
    }

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }
    
    private void buttons(String bool) {
        if (bool.equals("enabled")) {
            rock.setEnabled(true);
            scissor.setEnabled(true);
            paper.setEnabled(true);
        } else {
            rock.setEnabled(false);
            scissor.setEnabled(false);
            paper.setEnabled(false);
        }
    }
    
    private void countdown() {
        counter = new Thread(new Runnable() {
            public void run() {
                while(GameData.Countdown > 0 && !stopThread) {
                    try {
                        Thread.sleep(1000);
                        gameFrame.repaint();
                        paint(gameFrame.getGraphics());
                        GameData.Countdown--;
                    } catch (InterruptedException ex) {}
                }
                stopThread = false;
                if(GameData.Countdown == 0) {
                    nothing.doClick();
                    mode = 3;
                }
            }
        });
        if(stopThread == false) {
            counter.start();
        }
    }
    
    private void countdownStop() {
        stopThread = true;
        mode = 3;
    }
    
}
