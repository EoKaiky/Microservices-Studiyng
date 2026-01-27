package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PaymentDto;
import br.com.alurafood.pagamentos.model.Payment;
import br.com.alurafood.pagamentos.model.Status;
import br.com.alurafood.pagamentos.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePayment {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public PaymentDto createPayment(PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        payment.setStatus(Status.CREATED);
        repository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }

}
