package com.example.cameracaffe.services;

import com.example.cameracaffe.entities.ClienteEntity;
import com.example.cameracaffe.entities.UtilizzoEntity;
import com.example.cameracaffe.repos.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteEntity findByPartitaIva(String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public List<ClienteEntity> findAll(){
        return clienteRepository.findAll();
    }

    public void save(ClienteEntity clienteEntity){
        clienteRepository.save(clienteEntity);
    }

    public void delete(ClienteEntity clienteEntity){
        clienteRepository.delete(clienteEntity);
    }
}
