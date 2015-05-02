
package Outils;
 
import java.io.IOException;
import java.io.OutputStream;
 
import javax.swing.JTextArea;
 
public class CustomOutputStream extends OutputStream {
    private JTextArea textArea;
     
    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;
    }
     
    @Override
    public void write(int b) throws IOException {
        // redirects data to the text area
        textArea.append(new String(new byte[] { (byte) b }));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
    
    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        textArea.append(new String(b, off, len));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
