package com.fakebookstore.fakebookstore.models;

import com.fakebookstore.fakebookstore.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer sku;
    private String description;
    private Double price;

    @Lob
    @Column(nullable = false, length = 64)
    private String image;

    private Status status;
    private Integer stock;

    //ManyToOne for this specific project: There can be many categories which can hold many books.
    @ManyToOne(fetch = FetchType.LAZY)
    //Essentially, this can be used for deserialization
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //JoinColumn specifies the mapping of the owning side
    @JoinColumn(name = "cg_id")
    private Category category;

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSku() {
        return sku;
    }

    public void setSku(Integer sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}