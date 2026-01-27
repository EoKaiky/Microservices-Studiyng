package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePaymentById {

    @Autowired
    private PaymentRepository repository;

    public void deletedPaymantById(Long id) {
        repository.deleteById(id);
    }
}
