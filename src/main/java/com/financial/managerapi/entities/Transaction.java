package com.financial.managerapi.entities;

import com.financial.managerapi.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type; // ENUM for EXPENSE or INCOME

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false) // Reference to Currency
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false) // Link to a Goal
    private Goal goal;

}