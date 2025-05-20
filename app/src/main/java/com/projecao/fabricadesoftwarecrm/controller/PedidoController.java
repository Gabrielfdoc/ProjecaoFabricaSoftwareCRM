package com.projecao.fabricadesoftwarecrm.controller;

import com.projecao.fabricadesoftwarecrm.model.Pedido;
import com.projecao.fabricadesoftwarecrm.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> registrarPedido(@RequestBody Pedido pedido) {
        return new ResponseEntity<>(pedidoService.registrarPedido(pedido), HttpStatus.CREATED);
    }
}

