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
import static aschente.client.AschenteClient.gameFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Michael
 */
public class CreateRoom extends Scene implements ActionListener, KeyListener {

    public CreateRoom() {
        super("CreateRoom");
    }

    public void LoadContent() {

    }

    @Override
    public void Initialize() {
        gameFrame.getContentPane().removeAll();
        gameFrame.repaint();
        this.LoadContent();
    }
    
    @Override
    public void Update() {
        
    }

    @Override
    public void Draw() {
        gameFrame.revalidate();
        this.paint(gameFrame.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
