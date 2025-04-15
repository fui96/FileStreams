import javax.swing.*;
import java.awt.*;
import java.io.RandomAccessFile;

public class RandProductSearch extends JFrame {
    //Fields
    private RandomAccessFile raf;
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
        MainPanel.add(resultsScroll,BorderLayout.SOUTH);







        setVisible(true);
    }


    //Create methods
    public void createMainPanel(){
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());

    }
    public void createSearchPanel(){
        SearchPanel = new JPanel();
        SearchPanel.setLayout(new GridLayout(2,2));

        SearchButton = new JButton("Search");
        searchLabel = new JLabel("Search Product");
        toSearch = new JTextField(10);

        SearchPanel.add(searchLabel);
        SearchPanel.add(toSearch);
        SearchPanel.add(SearchButton);



    }
    public void createResultsPanel(){
        ResultsPanel = new JPanel();
        ResultsPanel.setLayout(new BorderLayout());

    }

    //Methods


}