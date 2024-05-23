package com.example.demo.service.impl;

import com.example.demo.common.Response;
import com.example.demo.common.ResponseHelper;
import com.example.demo.dto.ClientDto;
import com.example.demo.entity.ClientEntity;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.ExceptionCode;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService
{

	private final ClientRepository clientRepository;

	@Override
	public Response createClient(ClientDto clientDto)
	{
		ClientEntity clientEntity = new ClientEntity();
		BeanUtils.copyProperties(clientDto, clientEntity);

		clientRepository.save(clientEntity);
		return ResponseHelper.CREATED(clientEntity);
	}

	@Override
	public Response getClients(Integer limit, Integer offset, Long clientId)
	{
		if(clientId != null)
		{
			Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
			if(clientEntity.isEmpty())
			{
				throw new BaseException(ExceptionCode.CLIENT_NOT_FOUND);
			}
			return ResponseHelper.SUCCESS(clientEntity.get());
		}

		Pageable pageable = PageRequest.of(offset, limit);
		Page<ClientEntity> clientEntities = clientRepository.findAll(pageable);
		return ResponseHelper.CREATED(clientEntities);
	}

	@Override
	public Response updateClient(Long clientId, ClientDto clientDto)
	{
		Optional<ClientEntity> clientEntity = clientRepository.findById(clientId);
		if(clientEntity.isEmpty())
		{
			throw new BaseException(ExceptionCode.CLIENT_NOT_FOUND);
		}

		ClientEntity client = clientEntity.get();

		Optional.ofNullable(clientDto.getName()).ifPresent(client::setName);
		Optional.ofNullable(clientDto.getLastName()).ifPresent(client::setLastName);
		Optional.ofNullable(clientDto.getMobile()).ifPresent(client::setMobile);

		clientRepository.save(client);
		return ResponseHelper.SUCCESS(client);
	}
}
