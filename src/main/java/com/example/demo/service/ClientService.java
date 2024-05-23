package com.example.demo.service;

import com.example.demo.common.Response;
import com.example.demo.dto.ClientDto;

public interface ClientService
{
	Response createClient(ClientDto clientDto);

	Response getClients(Integer limit, Integer offset, Long clientId);

	Response updateClient(Long clientId, ClientDto clientDto);
}
