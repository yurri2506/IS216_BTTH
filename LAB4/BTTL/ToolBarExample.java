import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class ToolBarExample extends JFrame {
    public ToolBarExample() {
        // Set up the frame
        setTitle("Basic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(515, 100);

        // Create toolbar
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);

        try {
            // Load images from URLs
            ImageIcon openIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/716/716784.png")); // Folder
            ImageIcon saveIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/84/84380.png")); // Save
            ImageIcon saveAsIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/104/104890.png")); // Save As
            ImageIcon printIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/678/678220.png")); // Printer
            ImageIcon mailIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/888/888858.png")); // Mail    
            ImageIcon facebookIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/733/733547.png")); // Facebook
            ImageIcon monitorIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/258/258315.png")); // Monitor
            ImageIcon handIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/664/664619.png")); // Hand
            ImageIcon documentIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/136/136522.png")); // Document
            ImageIcon cursorIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/994/994928.png")); // Cursor
            ImageIcon noteIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/149/149432.png")); // Note
            ImageIcon cameraIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/266/266738.png")); // Camera
            ImageIcon lockIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/294/294854.png")); // Lock
            ImageIcon pencilIcon = new ImageIcon(new URL("https://cdn-icons-png.flaticon.com/24/149/149309.png")); // Pencil

            // Create buttons with icons
            JButton btnOpen = new JButton(openIcon);
            JButton btnSave = new JButton(saveIcon);
            JButton btnSaveAs = new JButton(saveAsIcon);
            JButton btnPrint = new JButton(printIcon);
            JButton btnMail = new JButton(mailIcon);
            JButton btnFacebook = new JButton(facebookIcon);
            JButton btnMonitor = new JButton(monitorIcon);
            JButton btnHand = new JButton(handIcon);
            JButton btnDocument = new JButton(documentIcon);
            JButton btnCursor = new JButton(cursorIcon);
            JButton btnNote = new JButton(noteIcon);
            JButton btnCamera = new JButton(cameraIcon);
            JButton btnLock = new JButton(lockIcon);
            JButton btnPencil = new JButton(pencilIcon);

            // Set tooltips for better usability
            btnOpen.setToolTipText("Open file");
            btnSave.setToolTipText("Save file");
            btnSaveAs.setToolTipText("Save file as...");
            btnPrint.setToolTipText("Print document");
            btnMail.setToolTipText("Send by email");
            btnFacebook.setToolTipText("Share on Facebook");
            btnMonitor.setToolTipText("View on monitor");
            btnHand.setToolTipText("Hand gesture");
            btnDocument.setToolTipText("Edit document");
            btnCursor.setToolTipText("Move cursor");
            btnNote.setToolTipText("Add note");
            btnCamera.setToolTipText("Take photo");
            btnLock.setToolTipText("Lock screen");
            btnPencil.setToolTipText("Draw with pencil");

            // Add buttons to toolbar
            toolBar.add(btnOpen);
            toolBar.add(btnSave);
            toolBar.add(btnSaveAs);
            toolBar.add(btnPrint);
            toolBar.add(btnMail);
            toolBar.add(btnFacebook);
            toolBar.add(btnMonitor);
            toolBar.add(btnHand);
            toolBar.add(btnDocument);
            toolBar.add(btnCursor);
            toolBar.add(btnNote);
            toolBar.add(btnCamera);
            toolBar.add(btnLock);
            toolBar.add(btnPencil);

            // Add action listeners
            btnOpen.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open file"));
            btnSave.addActionListener(e -> JOptionPane.showMessageDialog(this, "Save file"));
            btnSaveAs.addActionListener(e -> JOptionPane.showMessageDialog(this, "Save file as..."));
            btnPrint.addActionListener(e -> JOptionPane.showMessageDialog(this, "Print document"));
            btnMail.addActionListener(e -> JOptionPane.showMessageDialog(this, "Send by email"));
            btnFacebook.addActionListener(e -> JOptionPane.showMessageDialog(this, "Share on Facebook"));
            btnMonitor.addActionListener(e -> JOptionPane.showMessageDialog(this, "View on monitor"));
            btnHand.addActionListener(e -> JOptionPane.showMessageDialog(this, "Hand gesture"));
            btnDocument.addActionListener(e -> JOptionPane.showMessageDialog(this, "Edit document"));
            btnCursor.addActionListener(e -> JOptionPane.showMessageDialog(this, "Move cursor"));
            btnNote.addActionListener(e -> JOptionPane.showMessageDialog(this, "Add note"));
            btnCamera.addActionListener(e -> JOptionPane.showMessageDialog(this, "Take photo"));
            btnLock.addActionListener(e -> JOptionPane.showMessageDialog(this, "Lock screen"));
            btnPencil.addActionListener(e -> JOptionPane.showMessageDialog(this, "Draw with pencil"));

            // Add toolbar to frame
            add(toolBar, BorderLayout.NORTH);

            // Set frame visibility
            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading icons: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToolBarExample::new);
    }
}