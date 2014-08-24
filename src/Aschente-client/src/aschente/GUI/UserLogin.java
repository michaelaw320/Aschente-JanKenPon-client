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
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Michael
 */
public class UserLogin extends Scene implements ActionListener, KeyListener {

    private final JButton ConnectButton;
    private String userName;

    public UserLogin() {
        super("UserLogin");
        ConnectButton = new JButton("CONNECT");
        ConnectButton.setFont(NGNLFont);

        ConstructButtonListener();
    }

    private void ConstructButtonListener() {
        ConnectButton.addActionListener(this);       
    }
    
    public void LoadContent() {
        gameFrame.getContentPane().add(ConnectButton);
        gameFrame.addKeyListener(this); 
        gameFrame.requestFocus();
        userName = "";
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
        ConnectButton.setBounds((gameFrame.getWidth()/2)-125,450, 250, 50);
        this.paint(gameFrame.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        /*Title render */
        String Title = "PLEASE TYPE IN USERNAME:";
        int TitleLen = (int) g2D.getFontMetrics().getStringBounds(Title, g2D).getWidth();  
        int start = gameFrame.getWidth()/2 - TitleLen/2;  
        g2D.drawString(Title, start, 175);
        
        /* Username typing */
        int UserLen = (int) g2D.getFontMetrics().getStringBounds(userName, g2D).getWidth();  
        int startUser = gameFrame.getWidth()/2 - UserLen/2;
        g2D.drawString(userName, startUser, 320);
        for(int j = 0; j < userName.length()+1;j++){
        g2D.drawString("_", startUser+j*18, 325);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(ConnectButton)) {
            
            if(userName.equals("")) {
                JOptionPane.showMessageDialog(gameFrame, "Username cannot be empty");
                gameFrame.requestFocus();
            } else {
                GameData.PlayerName = userName;
                Network.Send("USERNAMEINPUT");
                Network.Receive();
                Network.Send(userName);
                GameData.Score = (int) Network.Receive();
                //to do duplicate username handling
                gameFrame.removeKeyListener(this);
                SceneManager.SwitchScene("OathScreen");
            }
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
            this.paint(gameFrame.getGraphics());
        } else if ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) || (e.getKeyCode() == 32)) {
            userName += e.getKeyChar();
            gameFrame.repaint();
            this.paint(gameFrame.getGraphics());
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            ConnectButton.doClick();
        }
    }

}
