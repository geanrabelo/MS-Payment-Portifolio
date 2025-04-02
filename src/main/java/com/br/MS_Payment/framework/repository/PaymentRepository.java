package com.br.MS_Payment.framework.repository;

import com.br.MS_Payment.framework.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
