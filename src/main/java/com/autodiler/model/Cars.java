package com.autodiler.model;

import com.autodiler.model.enums.CarStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cars {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private String[] photos;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CarDescription description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SaleRequest sale;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    private List<Comments> comments;
    private CarStatus status;

    public Cars(String name, int price, String tel, String[] result_photos) {
        this.name = name;
        this.sale = new SaleRequest(tel, price);
        this.status = CarStatus.WAITING;
        this.photos = result_photos;
        comments = new ArrayList<>();
    }

    public void addComment(Comments comment) {
        comments.add(comment);
        comment.setCar(this);
    }

    public void removeComment(Comments comment) {
        comments.remove(comment);
        comment.setCar(null);
    }

    public String[] getPartPhotos() {
        return Arrays.copyOfRange(photos, 1, photos.length);
    }
}
