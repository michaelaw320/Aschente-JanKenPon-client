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
    private boolean buttonsEnabled;
    
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
        
    }

    public void LoadContent() {
        gameFrame.getContentPane().add(rock);
        gameFrame.getContentPane().add(scissor);
        gameFrame.getContentPane().add(paper);

        rock.addActionListener(this);
        scissor.addActionListener(this);
        paper.addActionListener(this);
       
    }

    @Override
    public void Initialize() {
        gameFrame.getContentPane().removeAll();
        gameFrame.repaint();
        gameFrame.getContentPane().add(this);
        this.LoadContent();
        buttonsEnabled = true;
    }
    
    @Override
    public void Update() {
        if (buttonsEnabled) {
            rock.setEnabled(true);
            scissor.setEnabled(true);
            paper.setEnabled(true);
        } else {
            rock.setEnabled(false);
            scissor.setEnabled(false);
            paper.setEnabled(false);
        }
    }

    @Override
    public void Draw() {
        gameFrame.revalidate();
        
        int start = (gameFrame.getWidth() - (3*130)) / 4;
        
        rock.setBounds(start, gameFrame.getHeight()*2/3, 130, 130);
        scissor.setBounds(start*2+130,gameFrame.getHeight()*2/3,130,130);
        paper.setBounds(start*3+130*2,gameFrame.getHeight()*2/3,130,130);
        
        this.paint(gameFrame.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
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
            buttonsEnabled = false;
        } else if (e.getSource().equals(scissor)) {
            //network send scissor
            buttonsEnabled = false;
        } else if (e.getSource().equals(paper)) {
            //network send paper
            buttonsEnabled = false;
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
    
}
