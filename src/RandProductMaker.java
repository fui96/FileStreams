import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandProductMaker extends JFrame {

    //Fields
    private int i;
    private RandomAccessFile raf;
    //Panels
    JPanel MainPanel, TFPanel, ButtonPanel;
    //Buttons
    JButton ButtonSubmit;
    //TextFields
    JTextField TFName,TFDescription,TFCounter,TFPrice;
    //Label
    JLabel LabelName, LabelDescription, LabelCounter,LabelPrice;

    public RandProductMaker() {
        i = 1;
        //window config
        setTitle("Random Product Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800,600);

        //Create and Add Panels
        createMainPanel();
        add(MainPanel);
        createTFPanel();
        MainPanel.add(TFPanel, BorderLayout.CENTER);
        createButtonPanel();
        MainPanel.add(ButtonPanel, BorderLayout.SOUTH);






        setVisible(true);
    }

    //Creations

    public void createMainPanel() {
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());

    }
    public void createTFPanel() {
        //Panel Config
        TFPanel = new JPanel();
        TFPanel.setLayout(new GridLayout(4,2));
        //TextField config
        TFName = new JTextField(35);
        TFDescription = new JTextField(75);
        TFCounter = new JTextField();
        TFCounter.setEditable(false);
        TFCounter.setText("" + 0);
        TFPrice = new JTextField();
        //Label config
        LabelName = new JLabel("Product Name");
        LabelDescription = new JLabel("Product Description");
        LabelCounter = new JLabel("Number of Records:");
        LabelPrice = new JLabel("Product Price");
        //add to panel
        TFPanel.add(LabelName);
        TFPanel.add(TFName);
        TFPanel.add(LabelDescription);
        TFPanel.add(TFDescription);
        TFPanel.add(LabelPrice);
        TFPanel.add(TFPrice);
        TFPanel.add(LabelCounter);
        TFPanel.add(TFCounter);

    }
    public void createButtonPanel() {
        ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BorderLayout());

        //Button config
        ButtonSubmit = new JButton("Submit");
        ButtonSubmit.addActionListener((ActionEvent ae) -> {
            try{
                newProduct();
            }
            catch(IOException ioe){
                ioe.printStackTrace();
                JOptionPane.showMessageDialog(null, "Buttom Command Failed");
            }
        });

        ButtonPanel.add(ButtonSubmit, BorderLayout.CENTER);
    }
    //methods

    public void newProduct() throws IOException {
        //Create Product
        Product Prod = new Product((String.valueOf(i)),TFName.getText(),TFDescription.getText(),(Double.parseDouble(TFPrice.getText())));
        //Initialize raf
        raf = new RandomAccessFile("src/ProdRandAccess.dat","rw");
        //Clear text fields
        TFName.setText("");
        TFDescription.setText("");
        TFPrice.setText("");
        //write record
        raf.seek(raf.length());
        raf.writeBytes(Prod.toRAF());
        raf.close();
        //append record count and ID counter
        TFCounter.setText("" + i);
        i++;





    }




    public static void main(String[] args) {
        new RandProductMaker();

    }




}
