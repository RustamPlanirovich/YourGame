package com.example.yourgame;


public class FriendsResponse {
    private String Name;
    private String Description;
    private String Price;
    private String image;
    private String id;

    public FriendsResponse() {
    }

    public FriendsResponse(String name, String description, String price, String image, String id) {
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.image = image;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String title) {
        this.Description = Description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String company) {
        this.Price = Price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {return id;}

    public void setId(String id) { this.id = id;}
}
