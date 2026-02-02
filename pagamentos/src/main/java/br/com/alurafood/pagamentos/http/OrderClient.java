package br.com.alurafood.pagamentos.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("oders-ms")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/order/{id}/pago")
    void updatePayment(@PathVariable Long id);
}
