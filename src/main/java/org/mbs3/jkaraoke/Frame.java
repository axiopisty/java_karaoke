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
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.WindowConstants;


public class Frame extends javax.swing.JFrame {
    private Display kPanel1;

    static final long serialVersionUID = 1;
    /**
    * Auto-generated main method to display this JFrame
    */
    public static void main(String[] args) {
        Frame inst = new Frame();
        inst.setVisible(true);
    }
    
    public Frame() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        try {
            setSize(Display.CDG_FULL_WIDTH, Display.CDG_FULL_HEIGHT);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.addComponentListener(new ComponentAdapter() {
				public void componentResized(ComponentEvent evt) {
					rootComponentResized(evt);
				}
			});
            {
                kPanel1 = new Display();
                getContentPane().add(getPanel(), BorderLayout.CENTER);
            }
            pack();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
    
    public Display getPanel() {
        return kPanel1;
    }
    
	private void rootComponentResized(ComponentEvent evt) {
		System.out.println("this.componentResized, event=" + evt);
		this.kPanel1.setSize(this.getSize());
	}

}
