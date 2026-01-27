package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PaymentDto;
import br.com.alurafood.pagamentos.model.Payment;
import br.com.alurafood.pagamentos.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindPaymentById {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public PaymentDto finById(Long id) {
        Payment payment = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapper.map(payment, PaymentDto.class);
    }
}
