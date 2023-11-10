package com.mictlanes.Arvideys.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mictlanes.Arvideys.Models.DiscountCodes;
import com.mictlanes.Arvideys.Repository.DiscountCodesRepository;

import java.util.List;
import java.util.Optional;


@Service
public class DiscountCodesServices {

    private final DiscountCodesRepository discount_codesRepository;

    @Autowired
    public DiscountCodesServices(DiscountCodesRepository discount_codesRepository) {
        this.discount_codesRepository = discount_codesRepository;
    }

    public List<DiscountCodes> findAllDiscountCodes() {
        return discount_codesRepository.findAll();
    }

    public Optional<DiscountCodes> findDiscountCodesById(Long id) {
        return discount_codesRepository.findById(id);
    }

    public DiscountCodes saveDiscountCodes(DiscountCodes genre) {
        return discount_codesRepository.save(genre);
    }

    public Optional<DiscountCodes> updateDiscountCodes(Long id, DiscountCodes discount_codesRepositoryDetails) {
        return discount_codesRepository.findById(id).map(existingDiscountCodes -> {
            existingDiscountCodes.setCode(discount_codesRepositoryDetails.getCode());
            existingDiscountCodes.setUsed(discount_codesRepositoryDetails.isUsed());
            return discount_codesRepository.save(existingDiscountCodes);
        });
    }

    public void deleteDiscountCodes(Long id) {
        discount_codesRepository.deleteById(id);
    }
}