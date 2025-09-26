package com.univale.techshop.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table( name = "categories")
public class Category {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    private  String nome;

    @OneToMany( mappedBy = "category")
    private List<Product> products;

    public Category() {}

    public Category(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
