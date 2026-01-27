package br.com.alurafood.pagamentos.service;

import br.com.alurafood.pagamentos.dto.PaymentDto;
import br.com.alurafood.pagamentos.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FindAllPayments {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PaymentDto> findAllPayments(Pageable pagination) {
        return repository
                .findAll(pagination)
                .map(p -> modelMapper.map(p, PaymentDto.class));
    }
}
