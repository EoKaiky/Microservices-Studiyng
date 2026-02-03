package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.http.OrderClient;
import br.com.alurafood.pagamentos.model.Payment;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfirmPayment {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private OrderClient order;

    public void confirmPayment(Long id) {
        Optional<Payment> payment = repository.findById(id);

        if(!payment.isPresent()) {
            throw new EntityNotFoundException();
        }

        payment.get().setStatus(Status.CONFIRMED);
        repository.save(payment.get());
        order.updatePayment(payment.get().getOrderId());
    }

    public void updateStatus(Long id) {
        Optional<Payment> payment = repository.findById(id);

        if(!payment.isPresent()) {
            throw new EntityNotFoundException();
        }

        payment.get().setStatus(Status.CONFIRMED_NO_INTEGRATION);
        repository.save(payment.get());
    }
}
