package com.leandroadal.vortasks.services.shop;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandroadal.vortasks.entities.shop.GemsPackage;
import com.leandroadal.vortasks.entities.shop.dto.GemsPackageRequestDTO;
import com.leandroadal.vortasks.entities.shop.dto.GemsPackageResponseDTO;
import com.leandroadal.vortasks.repositories.shop.GemsPackageRepository;
import com.leandroadal.vortasks.services.shop.exceptions.GemsPackageNotFoundException;

@Service
public class GemsPackageService {

    private static final Logger logger = LoggerFactory.getLogger(GemsPackageService.class);

    @Autowired
    private GemsPackageRepository gemsPackageRepository;
    
    public List<GemsPackageResponseDTO> getAllGemsPackages() {
        return gemsPackageRepository.findAll().stream()
                .map(GemsPackageResponseDTO::new)
                .toList();
    }

    public GemsPackage getGemsPackageById(Long id) throws GemsPackageNotFoundException {
        return gemsPackageRepository.findById(id).orElseThrow(() -> new GemsPackageNotFoundException("Pacote de gemas não encontrado"));
    }
    
    public void addGemsPackage(GemsPackageRequestDTO gemsPackageDTO) {
        GemsPackage gemsPackage = new GemsPackage(gemsPackageDTO);
        gemsPackageRepository.save(gemsPackage);
    }

    public void editGemsPackage(Long id, GemsPackageRequestDTO gemsPackageDTO) throws GemsPackageNotFoundException {
        GemsPackage gemsPackage = getGemsPackageById(id);
        gemsPackage.edit(gemsPackageDTO);
        gemsPackageRepository.save(gemsPackage);
        logger.info("Pacote de gemas com ID: '{}' foi editado com sucesso!", id);
    }


    public void partialEditGemsPackage(Long id, GemsPackageRequestDTO gemsPackageDTO) throws GemsPackageNotFoundException {
        GemsPackage gemsPackage = getGemsPackageById(id);
        
        updateFieldIfNotNull(gemsPackageDTO.nameOfPackage(), gemsPackage::setNameOfPackage);
        updateFieldIfNotNull(gemsPackageDTO.money(), gemsPackage::setMoney);
        updateFieldIfNotNull(gemsPackageDTO.icon(), gemsPackage::setIcon);
        updateFieldIfPositive(gemsPackageDTO.gems(), gemsPackage::setGems);

        gemsPackageRepository.save(gemsPackage);
        logger.info("Pacote de gemas com ID: '{}' foi parcialmente editado com sucesso!", id);
    }

    public void deleteGemsPackage(Long id) throws GemsPackageNotFoundException {
        GemsPackage gemsPackage = getGemsPackageById(id);
        gemsPackageRepository.delete(gemsPackage);
        logger.info("Pacote de gemas com ID: '{}' foi deletado com sucesso!", id);
    }

    private <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    private void updateFieldIfPositive(int f, IntConsumer setter) {
        if (f > 0) {
            setter.accept(f);
        }
    }
   
}
