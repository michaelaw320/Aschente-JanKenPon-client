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

import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class SceneManager 
{
    // Attributes
    static private ArrayList<Scene> Scenes = new ArrayList<>();
    static private boolean Started = false;
    static private Scene activeScene = null;
    
    // Methods
    static public void AddScene(Scene S)
    {
        for (Scene Scn : Scenes)
        {
            if (Scn.getSceneName().equalsIgnoreCase(S.getSceneName())) return;
        }
        Scenes.add(S);
    }
    
    /**
     * Pindah Scene dari satu scene ke scene lain.
     * @param name Scene yang akan dijalankan
     */
    static public void SwitchScene(String name) 
    {
        for (Scene Scn : Scenes)
        {
            if (Scn.getSceneName().equalsIgnoreCase(name))
            {
                activeScene = Scn;
                Scn.Initialize();
                return;
            }
        }
    }
    
    /**
     * Melakukan inisialisasi Scene.
     */
    static public void Initialize()
    {
        Started = true;
    }
    
    /**
     * Melakukan update scene dengan scene pada ActiveScene
     */
    static public void Update()
    {
        if (!Started) return;
        if (activeScene != null)
            activeScene.Update();
    }
    
    /**
     * Menggambar scene yang ada pada ActiveScene.
     */
    static public void Draw()
    {
        if (!Started) return;
        if (activeScene != null)
            activeScene.Draw();
    }
    
    /**
     * Menghentikan game AschenteJanKenPon.
     */
    static public void ShutDown()
    {
        AschenteClient.gameRunning = false;
    }
}
