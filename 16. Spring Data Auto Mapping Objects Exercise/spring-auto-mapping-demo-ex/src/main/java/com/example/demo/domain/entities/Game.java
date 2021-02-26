package com.example.demo.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game extends BaseEntity {
    private String description;
    private String image;
    private BigDecimal price;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private Double size;

    @Column(unique = true)
    private String title;
    private String trailer;
    @ManyToMany(mappedBy = "games")
    private Set<User> users;
    @ManyToMany(mappedBy = "orderedGames")
    private Set<Order> orders;
}




























