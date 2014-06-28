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
import static aschente.client.AschenteClient.ResolutionWidth;
import static aschente.client.AschenteClient.gameFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

/**
 *
 * @author Michael
 */
public class UserLogin extends Scene implements ActionListener, KeyListener {

    private JButton ConnectButton;
    private String userName;
    private int mode;

    public UserLogin() {
        super("UserLogin");
        ConnectButton = new JButton("CONNECT");
        ConnectButton.setFont(NGNLFont);
        userName = "";
        mode = 0;
    }

    public void LoadContent() {
        gameFrame.getContentPane().add(ConnectButton);

        ConnectButton.setBounds(275, 450, 250, 50);

        ConnectButton.addActionListener(this);
        gameFrame.addKeyListener(this);

    }

    @Override
    public void Initialize() {
        gameFrame.getContentPane().removeAll();
        gameFrame.repaint();
        gameFrame.getContentPane().add(this);
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
        /*Title render */
        NGNLFont = NGNLFont.deriveFont(64f);
        String Title = "PLEASE TYPE IN USERNAME:";
        int TitleLen = (int) g2D.getFontMetrics().getStringBounds(Title, g2D).getWidth();  
        int start = ResolutionWidth/2 - TitleLen/2;  
        g2D.drawString(Title, start, 175);
        
        /* Username typing */
        NGNLFont = NGNLFont.deriveFont(36f);
        int UserLen = (int) g2D.getFontMetrics().getStringBounds(userName, g2D).getWidth();  
        int startUser = ResolutionWidth/2 - UserLen/2;
        g2D.drawString(userName, startUser, 320);
        for(int j = 0; j < userName.length()+1;j++){
        g2D.drawString("_", startUser+j*18, 325);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ConnectButton)) {
            //network send username here
            //to do checking before switch scene
            SceneManager.SwitchScene("MainMenu");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 8 && userName.length() != 0) {
            userName = userName.substring(0, userName.length() - 1);
            gameFrame.repaint();
        } else if ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) || (e.getKeyCode() == 32)) {
            userName += e.getKeyChar();
            gameFrame.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
