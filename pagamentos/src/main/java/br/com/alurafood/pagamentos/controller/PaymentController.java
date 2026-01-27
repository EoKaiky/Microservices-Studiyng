package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dto.PaymentDto;
import br.com.alurafood.pagamentos.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/payments")
@Tag(name = "1. Payments Controller", description = "Controller payments methods")
public class PaymentController {

    @Autowired
    private CreatePayment createPayment;
    @Autowired
    private DeletePaymentById deleteById;
    @Autowired
    private FindAllPayments findAll;
    @Autowired
    private FindPaymentById findById;
    @Autowired
    private UpdatePayment updatePayment;

    @GetMapping
    @Operation(summary = "List all payments with pagination")
    @ApiResponse(responseCode = "200", description = "Ok")
    public Page<PaymentDto> list(@PageableDefault(size = 10) Pageable pagination) {
        return findAll.findAllPayments(pagination);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find payment by id")
    @ApiResponse(responseCode = "200", description = "Ok")
    public ResponseEntity<PaymentDto> listById(@PathVariable @NotNull Long id) {
        PaymentDto dto = findById.finById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    @Operation(summary = "Create payment")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentDto dto, UriComponentsBuilder uriBuilder) {
        PaymentDto payment = createPayment.createPayment(dto);
        URI endereco = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri();

        return ResponseEntity.created(endereco).body(payment);
    }

    @PutMapping
    @Operation(summary = "Update payment status")
    @ApiResponse(responseCode = "200", description = "Ok")
    public ResponseEntity<PaymentDto> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto dto) {
        PaymentDto update = updatePayment.updatePayment(id, dto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete payment by id")
    @ApiResponse(responseCode = "204", description = "No Content")
    public ResponseEntity<PaymentDto> deleted(@PathVariable @NotNull Long id) {
        deleteById.deletedPaymantById(id);
        return ResponseEntity.noContent().build();
    }

}
