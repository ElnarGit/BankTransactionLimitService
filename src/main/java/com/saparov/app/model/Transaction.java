package com.saparov.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "account_from")
	private String accountFrom;
	
	@Column(name = "account_to")
	private String accountTo;
	
	@Column(name = "currency_short_name")
	private String currencyShortName;
	
	@Column(name = "sum")
	private BigDecimal sum;
	
	@Column(name = "expense_category")
	private String expenseCategory;
	
	@Column(name = "datetime")
	private LocalDateTime datetime;
	
	@Column(name = "limit_exceeded")
	private boolean limitExceeded;
}
