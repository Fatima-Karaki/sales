package com.example.demo.controller;

import com.example.demo.common.Response;
import com.example.demo.dto.ClientDto;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController
{

	private final ClientService clientService;

	@GetMapping
	public ResponseEntity<Response> getClients(@RequestParam Integer limit, Integer offset, @RequestParam(required = false) Long clientId)
	{
		return ResponseEntity.ok(clientService.getClients(limit, offset, clientId));
	}

	@PostMapping
	public ResponseEntity<Response> createClient(@RequestBody ClientDto clientDto)
	{
		return ResponseEntity.ok(clientService.createClient(clientDto));
	}

	@PutMapping("/{clientId}")
	public ResponseEntity<Response> updateClient(@PathVariable Long clientId, @RequestBody ClientDto clientDto)
	{
		return ResponseEntity.ok(clientService.updateClient(clientId, clientDto));
	}

}
