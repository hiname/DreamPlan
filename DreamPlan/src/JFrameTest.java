import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JFrameTest {

	public static void main(String[] args) {
		Display dis = new Display();
		
	}

}

class Display extends JFrame implements WindowListener{
	JPanel header = new JPanel();
	JPanel container = new JPanel();
	JPanel footer = new JPanel();
	int width;
	int height;
	
	public Display(){
		setBounds(100, 100, 400, 600);
		addWindowListener(this);
		setVisible(true);
		
		width = getWidth();
		height = getHeight() - 26;
		
		add(header);
		add(container);
		add(footer);
		
		headerMgr();
		containerMgr();
		footerMgr();

		footerBtn[0].setBackground(Color.BLUE);
	}
	
	JButton[] headerBtn = new JButton[5];
	public void headerMgr(){
		header.setBounds(0, 0, width, (int)(height * 0.1));
		for(int i = 0; i < headerBtn.length; i++) {
			JButton jbtn = new JButton();
			header.add(jbtn);
			jbtn.setBounds((int)(i * (header.getWidth() *  0.2)), 0, (int)(header.getWidth() * 0.2), (int)(header.getHeight()));
			jbtn.setBackground(Color.BLACK);
			headerBtn[i] = jbtn;
		}
		
		header.setBackground(Color.RED);
	}
	
	JLabel girlImgBox = new JLabel();
	ImageIcon girlImg = new ImageIcon();
	public void containerMgr(){
		container.setBounds(0, header.getHeight(), width, (int)(height * 0.8));
		setGirlImg("딸.png");
		girlImgBox.setBounds((int)(container.getWidth() * 0.5 - (girlImg.getIconWidth() / 2)), 
				(int)(container.getHeight() * 0.5 - (girlImg.getIconHeight() / 2)),
				girlImg.getIconWidth(), girlImg.getIconHeight());
		girlImgBox.setVisible(true);
		container.add(girlImgBox);
		container.setBackground(Color.BLACK);
	}
	
	public void setGirlImg(String imgName){
		girlImg = new ImageIcon("./img/" + imgName);
		girlImgBox.setIcon(girlImg);
	}

	JButton[] footerBtn = new JButton[5];
	JLabel[] footerBtnLabel = new JLabel[5];
	public void footerMgr(){
		footer.setBounds(0, container.getY() + container.getHeight(), width, (int)(height * 0.1));
		for(int i = 0; i < footerBtn.length; i++) {
			JButton jbtn = new JButton();
			footer.add(jbtn);
			
			JLabel jl = new JLabel();
			jl.setBounds(25, 5, 50, 50);
			jbtn.setBounds((int)(i * (footer.getWidth() *  0.2)), 0, (int)(footer.getWidth() * 0.2), (int)(footer.getHeight()));
			
			jbtn.setBackground(Color.BLACK);
			footerBtn[i] = jbtn;
			footerBtnLabel[i] = jl;
		}
		
		footer.setBackground(Color.YELLOW);
	}
	
	
	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	
	
}