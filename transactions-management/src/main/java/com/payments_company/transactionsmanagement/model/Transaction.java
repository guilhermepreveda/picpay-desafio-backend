package com.payments_company.transactionsmanagement.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.payments_company.transactionsmanagement.model.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(nullable = false, updatable = false, referencedColumnName = "id")
  @NonNull
  private User payer;

  @ManyToOne
  @JoinColumn(nullable = false, updatable = false, referencedColumnName = "id")
  @NonNull
  private User payee;

  @Column(nullable = false, updatable = false)
  @NonNull
  private float value;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Date date;

}
