package apitesting.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("id")
    public Integer id;
    
    @JsonProperty("name")
    public String name;
    
    @JsonProperty("price")
    public String price;
    
    @JsonProperty("brand")
    public String brand;
    
    @JsonProperty("category")
    public Category category;

    public Product() {}

    public Product(Integer id, String name, String price, String brand, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                ", category=" + category +
                '}';
    }
}

