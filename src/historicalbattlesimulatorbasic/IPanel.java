package historicalbattlesimulatorbasic;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

/*http://stackoverflow.com/questions/15479820/set-background-image-for-jpanel-in-java-breakout-game*/
    public class IPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Image             imageOrg         = null;
    private Image             image            = null;{
        addComponentListener(new ComponentAdapter() {
           @Override
           public void componentResized(final ComponentEvent e) {
                final int w = IPanel.this.getWidth();
                final int h = IPanel.this.getHeight();
                image = w > 0 && h > 0 ? imageOrg.getScaledInstance(w, h, Image.SCALE_SMOOTH) : imageOrg;
                IPanel.this.repaint();
            }
        });
    }
    public IPanel(final Image i){
        imageOrg = i;
        image = i;
    }
    public IPanel(){

    }
    public static void setBackgroundImage(final Image i, IPanel ip){
        ip.imageOrg = i;
        ip.image = i;
    }

    @Override
    public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    if (image != null)
        g.drawImage(image, 0, 0, null);
}
    }