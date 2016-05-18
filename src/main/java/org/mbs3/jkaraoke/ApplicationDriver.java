/*
 *   Copyright 2006 Martin B. Smith
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mbs3.jkaraoke;

import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;
import java.util.zip.*;
import javax.sound.sampled.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

/*
 * Created on Jul 12, 2006
 *
 * TODO Nothing yet.
 */

/**
 * @author Martin Smith
 *
 * TODO None yet.
 */
public class ApplicationDriver
{
    private final static JFileChooser jfc = new JFileChooser();

    public static void main (String[] args)
    {
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setCurrentDirectory(new File("/Volumes/data/elliot/karaoke/Tracks"));
        jfc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".zip") || f.getName().endsWith(".ZIP");
            }

            @Override
            public String getDescription() {
                return "*.zip";
            }
        });

        File zipFile;
        while((zipFile = getZipFile()) != null) {
            try {
                play(zipFile);
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }

    }

    public static File getZipFile() {
        int response = jfc.showOpenDialog(null);
        if(response != JFileChooser.APPROVE_OPTION)
            return jfc.getSelectedFile();
        else
            return null;
    }

    public static void play(File zipFileObject) throws IOException {
        ZipFile zf = new ZipFile(zipFileObject);

        ZipEntry zeMusicFile = null;
        ZipEntry zeCdgFile = null;

        Enumeration<? extends ZipEntry> entries = zf.entries();
        while(entries.hasMoreElements() && (zeMusicFile == null || zeCdgFile == null)) {
            ZipEntry zipEntry = entries.nextElement();
            String entryName = zipEntry.getName();
            if(entryName.endsWith(".mp3") || entryName.endsWith(".MP3")) {
                zeMusicFile = zf.getEntry(entryName);
            } else if(entryName.endsWith(".cdg") || entryName.endsWith(".CDG")) {
                zeCdgFile = zf.getEntry(entryName);
            }
        }


        InputStream f  = zf.getInputStream(zeMusicFile);
        InputStream c  = zf.getInputStream(zeCdgFile);

        Frame kFrame = new Frame();
        Dispatcher dispatcher = new Dispatcher(kFrame.getPanel());
        MusicPlayer mp = new MusicPlayer(f, c, dispatcher);
        kFrame.setVisible(true);

        //System.out.println("Creating thread");
        Thread t = new Thread(mp);
        //System.out.println("Thread sent off");
        t.start();
    }
}
