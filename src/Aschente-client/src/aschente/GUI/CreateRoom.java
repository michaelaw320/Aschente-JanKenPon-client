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

/**
 *
 * @author Michael
 */
public class CreateRoom extends Scene implements ActionListener, KeyListener {

    private final JButton CreateButton;
    private String roomName;

    public CreateRoom() {
        super("CreateRoom");
        CreateButton = new JButton("CREATE");
        CreateButton.setFont(NGNLFont);

        ConstructButtonListener();
    }

    private void ConstructButtonListener() {
        CreateButton.addActionListener(this);     
    }
    
    public void LoadContent() {
        gameFrame.getContentPane().add(CreateButton);
        gameFrame.addKeyListener(this);
        gameFrame.requestFocus();
        roomName = "";
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
        CreateButton.setBounds((gameFrame.getWidth()/2)-125,450, 250, 50);
        this.paint(gameFrame.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        /*Title render */
        String Title = "NEW ROOM NAME:";
        int TitleLen = (int) g2D.getFontMetrics().getStringBounds(Title, g2D).getWidth();  
        int start = gameFrame.getWidth()/2 - TitleLen/2;  
        g2D.drawString(Title, start, 175);
        
        /* Username typing */
        int UserLen = (int) g2D.getFontMetrics().getStringBounds(roomName, g2D).getWidth();  
        int startUser = gameFrame.getWidth()/2 - UserLen/2;
        g2D.drawString(roomName, startUser, 320);
        for(int j = 0; j < roomName.length()+1;j++){
        g2D.drawString("_", startUser+j*18, 325);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(CreateButton)) {
            //network send username here
            //to do checking before switch scene
            GameData.RoomName = roomName;
            gameFrame.removeKeyListener(this);
            SceneManager.SwitchScene("GameMode");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == 8 && roomName.length() != 0) {
            roomName = roomName.substring(0, roomName.length() - 1);
            gameFrame.repaint();
            this.paint(gameFrame.getGraphics());
        } else if ((e.getKeyCode() >= 48 && e.getKeyCode() <= 57) || (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) || (e.getKeyCode() == 32)) {
            roomName += e.getKeyChar();
            gameFrame.repaint();
            this.paint(gameFrame.getGraphics());
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            CreateButton.doClick();
        }
    }

}
