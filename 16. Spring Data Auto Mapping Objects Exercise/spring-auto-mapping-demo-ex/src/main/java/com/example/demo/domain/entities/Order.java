package com.example.demo.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;
    @ManyToMany
    private Set<Game> orderedGames;
}



























