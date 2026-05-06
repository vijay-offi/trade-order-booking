package com.fynxt.trade_order_booking.entity;

import org.hibernate.metamodel.mapping.ForeignKeyDescriptor.Side;

import ch.qos.logback.core.status.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String traderId;
    private String stock;
    private String sector;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private Side side; // BUY / SELL

    @Enumerated(EnumType.STRING)
    private Status status; // PENDING, FILLED, CANCELLED

    private LocalDateTime createdAt;
}
