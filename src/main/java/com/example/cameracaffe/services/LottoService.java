package com.example.cameracaffe.services;

import com.example.cameracaffe.entities.LottoEntity;
import com.example.cameracaffe.repos.LottoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LottoService {
    private LottoRepository lottoRepository;

    public LottoService(LottoRepository lottoRepository) {
        this.lottoRepository = lottoRepository;
    }

    public List<LottoEntity> findByAlimentare_Id(long id) {
        return lottoRepository.findByAlimentare_Id(id);
    }

    public List<LottoEntity> findByKey_LottoId(long id) {
        return lottoRepository.findByKey_LottoId(id);
    }

    public List<LottoEntity> findAll(){
        return lottoRepository.findAll();
    }

    public void save(LottoEntity lottoEntity) {
        lottoRepository.save(lottoEntity);
    }

    public void delete(LottoEntity lottoEntity) {
        lottoRepository.delete(lottoEntity);
    }
}
