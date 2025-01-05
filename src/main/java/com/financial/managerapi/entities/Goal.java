package com.financial.managerapi.entities;


import com.financial.managerapi.enums.GoalType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Goal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double targetAmount;

    @Column(nullable = false)
    private Double currentProgress = 0.0;

    @Column(nullable = false)
    private LocalDate targetDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoalType type; // ENUM for SAVINGS or SPENDING

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Optional link
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false) // Reference to Currency
    private Currency currency;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

}
