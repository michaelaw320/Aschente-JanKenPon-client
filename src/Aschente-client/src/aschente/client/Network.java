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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Network {
    public static Socket connection = null;
    public static ObjectOutputStream out;
    public static ObjectInputStream in;
    public static String serverAddress;
    public static int serverPort;
    public static void Connect() throws IOException {
        /* Read server configuration */
        try {
            File server = new File ("Config\\server.txt");
            Scanner sc = new Scanner(server);
            String read = sc.nextLine();
            String[] split = read.split(":");
            serverAddress = split[0];
            serverPort = Integer.parseInt(split[1]);
        } catch (FileNotFoundException ex) {
            System.err.println("Can't find server.txt, using default value of localhost:45373");
            serverAddress = "localhost";
            serverPort = 45373;
        }
        Connect(serverAddress, serverPort);
    }
    public static void Connect(String host, int port) throws IOException {
            connection = new Socket(host,port);
            connection.setSoTimeout(15000);
            out = new ObjectOutputStream(connection.getOutputStream());
            in = new ObjectInputStream(connection.getInputStream());
    }
    public static void Send(Object toSend) throws IOException {
        out.writeObject(toSend);
        out.flush();
        out.reset();
    }
    public static Object Receive() throws IOException {
        try {
            return in.readObject();
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
}
