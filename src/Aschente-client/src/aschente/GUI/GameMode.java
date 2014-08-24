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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Michael
 */
public class GameMode extends Scene implements ActionListener, WindowListener {

    private JButton rock;
    private JButton scissor;
    private JButton paper;
    private JButton aschente;
    private JButton nothing;
    private int mode;
    private volatile boolean stopThread;
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
        mode = -1;
        stopThread = false;
        ConstructButtonListener();
    }
    
    private void ConstructButtonListener() {
        rock.addActionListener(this);
        scissor.addActionListener(this);
        paper.addActionListener(this);
        aschente.addActionListener(this);
        nothing.addActionListener(this);
        gameFrame.addWindowListener(this);
    }

    public void LoadContent() {
        gameFrame.getContentPane().add(rock);
        gameFrame.getContentPane().add(scissor);
        gameFrame.getContentPane().add(paper);
        gameFrame.getContentPane().add(aschente);
    }

    @Override
    public void Initialize() {
        gameFrame.getContentPane().removeAll();
        gameFrame.repaint();
        this.LoadContent();
        buttons("disabled");
    }
    
    @Override
    public void Update() {
        if (GameData.Round > GameData.ToRound && !GameData.wait && GameData.ToRound != 0 && mode < 4) {
            mode = 4;
            GameData.Countdown = 0;
            Draw();
        }
        
        if (mode == -1) {
            aschente.setVisible(true);
            aschente.setEnabled(false);
            while(GameData.wait) {
                try {
                    if(Network.ReceiveWait().equals("GAMESTART")) {
                        GameData.wait = false;
                    }
                    Draw();
                } catch (IOException ex) {
                }
            }
            UpdateRoomData();
            aschente.setEnabled(true); 
            mode = 0;
        }
        else if(mode == 0) {
            aschente.setVisible(true);
        } else if (mode == 1) {
            aschente.setVisible(false);
            buttons("enabled");
            GameData.Countdown = 10;
            countdown();
            mode = 2;
            gameFrame.repaint();
        }  //mode 2 = countdown active
        else if (mode == 3) {
            /* Wait for network before returning to mode 0 or 1 */
            String received;
            received = (String) Network.Receive();
            if(received.equals("WIN")) {
                GameData.Score++;
                JOptionPane pane = new JOptionPane("YOU WIN!!");
                final JDialog dialog = pane.createDialog("YOU WIN!!");
                Timer timer = new Timer(3000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                }
                }); 
                timer.setRepeats(false);
                timer.start();
                dialog.setVisible(true);
            } else if (received.equals("LOSE")) {
                GameData.ScoreP2++;
                JOptionPane pane = new JOptionPane("YOU LOSE");
                final JDialog dialog = pane.createDialog("YOU LOSE");
                Timer timer = new Timer(3000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                }
                }); 
                timer.setRepeats(false);
                timer.start();
                dialog.setVisible(true);
            } else if (received.equals("DRAW")) {
                JOptionPane pane = new JOptionPane("DRAW");
                final JDialog dialog = pane.createDialog("DRAW");
                Timer timer = new Timer(3000, new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                }
                }); 
                timer.setRepeats(false);
                timer.start();
                dialog.setVisible(true);
            }
            stopThread = false;
            mode = 1;
            gameFrame.repaint();
        } else if (mode == 4) {
            mode = 5;
            Network.Send("SAVEGAME");
        } else if (mode == 5) {
            int response = JOptionPane.showConfirmDialog(gameFrame, "PLAY AGAIN?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(response == JOptionPane.YES_OPTION) {
                Network.Send("NEWGAME");
                GameData.Round = 0;
                GameData.Player2Name = "";
                GameData.ScoreP2 = 0;
                SceneManager.SwitchScene("MainMenu");
            } else {
                mode = 6;
            }
        } else if (mode == 6) {
            //do nothing
        }
        
    }

    @Override
    public void Draw() {
        gameFrame.revalidate();
        
        int start = (gameFrame.getWidth() - (3*130)) / 4;
        
        rock.setBounds(start, gameFrame.getHeight()*2/3, 130, 130);
        scissor.setBounds(start*2+130,gameFrame.getHeight()*2/3,130,130);
        paper.setBounds(start*3+130*2,gameFrame.getHeight()*2/3,130,130);
        
        aschente.setBounds((gameFrame.getWidth()/2)-100, gameFrame.getHeight()/3 + 20, 200, 50);
        
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
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(rock)) {
            //network send 
            buttons("disabled");
            countdownStop();
            GameData.Round++;
            Network.Send("ROCK");
        } else if (e.getSource().equals(scissor)) {
            //network send scissor
            buttons("disabled");
            countdownStop();
            GameData.Round++;
            Network.Send("SCISSOR");
        } else if (e.getSource().equals(paper)) {
            //network send paper
            buttons("disabled");
            countdownStop();
            GameData.Round++;
            Network.Send("PAPER");
        } else if (e.getSource().equals(nothing)) {
            //network send nothing
            buttons("disabled");
            countdownStop();
            GameData.Round++;
            Network.Send("NOTHINGSELECTED");
        } else if (e.getSource().equals(aschente)) {
            aschente.setEnabled(false);
            Draw();
            Network.Send("ASCHENTE!");
            GameData.wait = true;
            while (GameData.wait) {
                try {
                    if(Network.ReceiveWait().equals("ASCHENTE!")) {
                        GameData.wait = false;
                    }
                } catch (IOException ex) {

                }
            }
            mode = 1;
        } 
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
                if(GameData.Countdown == 0) {
                    nothing.doClick();
                }
            }
        });
        if(stopThread == false) {
            counter.start();
        }
    }
    
    private void countdownStop() {
        stopThread = true;
        counter.interrupt();
        mode = 3;
    }
    
    private void UpdateRoomData() {
        Network.Send("REFRESHGAMEDATA");
        GameData.Player2Name = (String) Network.Receive();
        GameData.ScoreP2 = (int) Network.Receive();
        GameData.ToRound = (int) Network.Receive(); 
        gameFrame.repaint();
        paint(gameFrame.getGraphics());
    }

    @Override
    public void windowOpened(WindowEvent e) {
     }

    @Override
    public void windowClosing(WindowEvent e) {
        Network.Send("SHUTDOWN");
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
}
