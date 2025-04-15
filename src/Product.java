
public class Product {
    //Fields
    private String Name;
    private String Description;
    private double Price;
    private String ID;

    //Constructor
    /**
     * Assigns information to product
     * @param name Name of Product
     * @param description Product Description
     * @param price Price of Product
     * @param ID Product ID
     */
    public Product(String ID ,String name, String description, double price) {
        this.ID = ID;
        this.Name = name;
        this.Description = description;
        this.Price = price;

    }
    //Getters
    /** Gets the Name of the product
     * @return Product Name
     */
    public String getName() {
        return Name;
    }

    /**
     * Gets the product Desc
     * @return Product Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Gets the price of the product
     * @return Product price
     */
    public double getPrice() {
        return Price;
    }

    /**
     * Gets Product ID
     * @return ID
     */
    public String getID() {
        return ID;
    }

    //Setters

    /**
     * Sets the Name of the product
     * @param name Product Name
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Sets the description
     * @param description Product Description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Sets the price of the product
     * @param price Price of Product
     */
    public void setPrice(double price) {
        Price = price;
    }

    /**
     * Sets the Product ID
     * @param ID Product ID
     */
    public void setID(String ID) {
        this.ID = ID;
    }
    //Special Methods and Conversions

    /**
     * Returns the name and description seperated with a comma
     * @return Name and description
     */
    public String NameAndDesc() {
        return Name + ", " + Description;
    }
    /**
     * Returns a string in CSV format
     * @return CSV String
     */
    public String toCSV() {
        return String.join("," , ID, Name, Description, Double.toString(Price));
    }

    /**
     * Returns a string in a JSON format
     * @return JSON String
     */
    public String toJSON(){
       return String.format("{,\"ID\":\"%s\",\"Name\":\"%s\",\"Description\":\"%s\",\"Price\":\"%s\"}"
               , ID, Name, Description, Double.toString(Price));
    }

    /**
     * Returns an XML format String
     * @return XML String
     */
    public String toXML(){
        return String.format("<Product><ID>%s</ID><Name>%s</Name><Description>%s</Description><Price>%s</Price></Product>"
                ,ID, Name, Description, Double.toString(Price));
    }

    public String toRAF(){
        String RAF = formatID() + ", " + formatName() + ", " + formatDescription();
        return RAF;
    }

    public String formatID(){
        return String.format("%06d",Integer.parseInt(ID));
    }

    public String formatName(){
        return String.format("%35s",Name);
    }

    public String formatDescription(){
        return String.format("%75s",Description);
    }

    public String exportString(){
        return ID + ", " + Name + ", " + Description + ", " + Double.toString(Price);
    }

}