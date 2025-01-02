package com.financial.managerapi.entities;


import com.financial.managerapi.enums.GoalType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
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
    private java.time.LocalDate targetDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoalType type; // ENUM for SAVINGS or SPENDING

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true) // Optional link
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
