package org.mbs3.jkaraoke;
import java.awt.BorderLayout;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
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

}
