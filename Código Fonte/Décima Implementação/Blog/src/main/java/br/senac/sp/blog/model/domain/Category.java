package br.senac.sp.blog.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category_tb")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy="category", orphanRemoval=true)
    private List<Post> posts = new ArrayList<>();

    private LocalDateTime creationDate;

    private LocalDateTime updateDate;

    public Category() {

    }

    public Category(Long id, String name, LocalDateTime creationDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void updateId(Long id) {
        this.setId(id);
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void updateName(String name) {
        this.setName(name);
    }

    private void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCreationFormatedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.getCreationDate().format(formatter);
    }

    public void updateCreationDate(LocalDateTime creationDate) {
        this.setCreationDate(creationDate);
    }

    private void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public String getUpdateFormatedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.getUpdateDate().format(formatter);
    }

    public void updateUpdateDate(LocalDateTime updateDate) {
        this.setUpdateDate(updateDate);
    }

    private void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}
