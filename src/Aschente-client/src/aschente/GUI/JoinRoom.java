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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 *
 * @author Michael
 */
public class JoinRoom extends Scene implements ActionListener {
    
    private final JButton refreshButton;
    private final JButton joinButton;
    private final JList room;
    private final JScrollPane scrollPane;

    public JoinRoom() {
        super("JoinRoom");
        refreshButton = new JButton("REFRESH");
        joinButton = new JButton("JOIN");
        room = new JList();
        scrollPane = new JScrollPane();
        refreshButton.setFont(NGNLFont);
        joinButton.setFont(NGNLFont);
        
        ConstructButtonListener();
    }

    private void ConstructButtonListener() {
        refreshButton.addActionListener(this);
        joinButton.addActionListener(this);
        scrollPane.setViewportView(room);
    }
    
    public void LoadContent() {
        gameFrame.getContentPane().add(refreshButton);
        gameFrame.getContentPane().add(joinButton);
        gameFrame.getContentPane().add(scrollPane);
        RefreshRoomList();
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
        refreshButton.setBounds(gameFrame.getWidth()*2/3,100,200,50);
        joinButton.setBounds(gameFrame.getWidth()*2/3,175,200,50);
        scrollPane.setBounds(gameFrame.getWidth()/16,gameFrame.getHeight()/12,(gameFrame.getWidth()/2),gameFrame.getHeight()*5/6);
        this.paint(gameFrame.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(refreshButton)) {
            RefreshRoomList();
        } else if (e.getSource().equals(joinButton)) {
            Network.Send("JOINROOM");
            Network.Receive();
            Network.Send(room.getSelectedValue());
            GameData.RoomName = room.getSelectedValue().toString();
            SceneManager.SwitchScene("GameMode");
        }
    }
    
    private void RefreshRoomList() {
        GameData.clearRoomList();
        Network.Send("REFRESHROOMLIST");
        GameData.RoomList = (ArrayList) Network.Receive();
        room.setListData(GameData.RoomList.toArray());
    }
    
}
