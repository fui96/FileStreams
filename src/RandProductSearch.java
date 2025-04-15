import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class RandProductSearch extends JFrame {
    //Fields
    private RandomAccessFile raf;
    private ArrayList<Product> products;
    //Panels
    JPanel MainPanel,SearchPanel,ResultsPanel;
    //TextFields
    JTextField toSearch;
    //Label
    JLabel searchLabel;
    //TextAreas
    JTextArea results;
    //Button
    JButton SearchButton;
    //Scollpane
    JScrollPane resultsScroll;


    public RandProductSearch() {
        products = new ArrayList<>();
        //Window Config
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800,600);
        //Panel Config

        createMainPanel();
        add(MainPanel);

        createSearchPanel();
        MainPanel.add(SearchPanel,BorderLayout.NORTH);

        createResultsPanel();
        MainPanel.add(ResultsPanel,BorderLayout.SOUTH);







        setVisible(true);
    }


    //Create methods
    public void createMainPanel(){
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());

    }
    public void createSearchPanel(){
        SearchPanel = new JPanel();
        SearchPanel.setLayout(new GridLayout(3,1));

        SearchButton = new JButton("Search");
        searchLabel = new JLabel("Search For?");
        searchLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        searchLabel.setVerticalTextPosition(SwingConstants.CENTER);
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        toSearch = new JTextField(10);


        SearchButton.addActionListener((ActionEvent ae) -> {
            results.setText("");
            products.clear();
            try{
                compileStream();
            }
            catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"Rand Access file couldn't compile","Error",JOptionPane.ERROR_MESSAGE);
            }
            String query = toSearch.getText();
            results.append(search(query));
        });


        SearchPanel.add(searchLabel);
        SearchPanel.add(toSearch);
        SearchPanel.add(SearchButton);



    }
    public void createResultsPanel(){
        ResultsPanel = new JPanel();
        ResultsPanel.setLayout(new BorderLayout());

        results = new JTextArea();
        results.setEditable(false);
        results.setLineWrap(true);
        results.setWrapStyleWord(true);
        resultsScroll = new JScrollPane(results);

        ResultsPanel.add(resultsScroll,BorderLayout.CENTER);


    }

    public String readFixedLenString(int length) throws IOException{
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++){
            sb.append(raf.readChar());
        }

        return sb.toString();
    }


    //Methods
    public void compileStream() throws IOException {
        raf = new RandomAccessFile("src/ProdRandAccess.dat","r");
        long numProducts = raf.length() / 240;
        for(int i = 0; i < numProducts; i++){
            raf.seek(240 * i);

            String ID = readFixedLenString(6).trim();
            String Name = readFixedLenString(35).trim();
            String Description = readFixedLenString(75).trim();
            double price = raf.readDouble();

            products.add(new Product(ID, Name, Description, price));
        }

    }

    public String search(String SearchFor){
        StringBuilder sb = new StringBuilder();
        products.stream()
                .filter(p ->p.getName().toLowerCase().contains(SearchFor.toLowerCase()))
                .forEach(p -> {
                    sb.append(p.exportString());
                });
        System.out.println(sb.toString());
        return sb.toString();

    }



    public static void main(String[] args) {
        new RandProductSearch();
    }
}