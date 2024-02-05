package com.leandroadal.vortasks.controllers.shop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandroadal.vortasks.entities.shop.GemsPackage;
import com.leandroadal.vortasks.entities.shop.dto.GemsPackageRequestDTO;
import com.leandroadal.vortasks.entities.shop.dto.GemsPackageResponseDTO;
import com.leandroadal.vortasks.services.shop.GemsPackageService;
import com.leandroadal.vortasks.services.shop.exceptions.GemsPackageNotFoundException;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/shop")
public class GemsController {

    @Autowired
    private GemsPackageService gemsPackageSellService;

    @PostMapping("/gemsPackage/add")
    public ResponseEntity<String> createGemsPackage(@RequestBody @Valid GemsPackageRequestDTO gemsDTO) {
        gemsPackageSellService.addGemsPackage(gemsDTO);

        return ResponseEntity.status(HttpStatus.OK).body("Adição do pacote de gemas realizada com sucesso");
    }

    @GetMapping("/gemsPackage")
    public ResponseEntity<List<GemsPackageResponseDTO>> getAllGemsPackage() {
        List<GemsPackageResponseDTO> gemsPackage = gemsPackageSellService.getAllGemsPackages();
        return ResponseEntity.status(HttpStatus.OK).body(gemsPackage);
    }

    @GetMapping("/gemsPackage/{gemsPackageId}")
    public ResponseEntity<GemsPackageResponseDTO> getGemsPackage(@PathVariable @NotNull @Positive Long gemsPackageId) {
        GemsPackage gemsPackage = gemsPackageSellService.getGemsPackageById(gemsPackageId);
        return ResponseEntity.status(HttpStatus.OK).body(new GemsPackageResponseDTO(gemsPackage));
    }

    @PutMapping("/gemsPackage/{id}")
    public ResponseEntity<String> editGemsPackage( @PathVariable @NotNull @Positive Long id, @Valid @RequestBody GemsPackageRequestDTO gemsDTO) {
        try {
            gemsPackageSellService.editGemsPackage(id, gemsDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualização do pacote de gemas com ID: " + id + ", realizada com sucesso");
        } catch (GemsPackageNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }      
    }

    @PatchMapping("/gemsPackage/{id}")
    public ResponseEntity<String> partialUpdateGemsPackage(@PathVariable @NotNull @Positive Long id, @RequestBody GemsPackageRequestDTO gemsDTO) {
        try {
            gemsPackageSellService.partialEditGemsPackage(id, gemsDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Atualização parcial do pacote de gemas com ID: " + id + ", foi realizada com sucesso");
        } catch (GemsPackageNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/gemsPackage/{id}")
    public ResponseEntity<String> deleteGemsPackage(@PathVariable @NotNull @Positive Long id) {
        try {
            gemsPackageSellService.deleteGemsPackage(id);
            return ResponseEntity.status(HttpStatus.OK).body("Pacote de gemas com ID: " + id + ", foi excluído com sucesso");
        } catch (GemsPackageNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        
    }
}
