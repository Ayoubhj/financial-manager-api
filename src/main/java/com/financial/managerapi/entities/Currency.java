package com.financial.managerapi.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Currency extends BaseEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 3)
    private String code; // ISO 4217 currency code, e.g., USD, EUR, MAD

    @Column(nullable = false)
    private String name; // Full name of the currency, e.g., "US Dollar", "Euro"

    @Column(nullable = true)
    private String symbol; // Symbol of the currency, e.g., "$", "€", "د.م"


}
