package com.example.demo.model.DB;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

//MODEL
//商品表格
@Entity
@Table(name = "shopping")
@EntityListeners(AuditingEntityListener.class)
public class Shopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "image_src", nullable = false)
    private String imageSrc;

    @Column(name = "star", nullable = false)
    private Integer star;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "content", nullable = false, length = 10000)
    private String content;

    @Column(name = "type", nullable = false, length = 10)
    private Integer type;

    @Column(name = "inventory", length = 10000)
    private Integer inventory;


    public Shopping(){

    }

    @Override
    public String toString() {
        return "Shopping{" +
                "id=" + id +
                ", imageSrc='" + imageSrc + '\'' +
                ", star=" + star +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", inventory=" + inventory +
                '}';
    }

    public Shopping(Integer id, String imageSrc, Integer star, String title, String content, Integer type, Integer inventory) {
        this.id = id;
        this.imageSrc = imageSrc;
        this.star = star;
        this.title = title;
        this.content = content;
        this.type = type;
        this.inventory = inventory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
