package com.mictlanes.Arvideys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mictlanes.Arvideys.Models.DiscountCodes;
import com.mictlanes.Arvideys.Services.DiscountCodesServices;

	
	@RequestMapping(path="/api/discountCodes")
	@RestController
	public class DiscountCodesController {

	    private final DiscountCodesServices discount_codesServices;

	    @Autowired
	    public DiscountCodesController(DiscountCodesServices discount_codesServices) {
	        this.discount_codesServices = discount_codesServices;
	    }

	    // GET all discount_codes
	    @GetMapping
	    public List<DiscountCodes> getAllDiscountCodes() {
	        return discount_codesServices.findAllDiscountCodes();
	    }

	    // GET a single genre by id
	    @GetMapping("/{id}")
	    public ResponseEntity<DiscountCodes> getDiscountCodesById(@PathVariable Long id) {
	        return discount_codesServices.findDiscountCodesById(id)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // POST a new genre
	    @PostMapping
	    public DiscountCodes createDiscountCodes(@RequestBody DiscountCodes discount_codes) {
	        return discount_codesServices.saveDiscountCodes(discount_codes);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<DiscountCodes> updateDiscountCodes(@PathVariable Long id, @RequestBody DiscountCodes discount_codesDetail) {
	        return discount_codesServices.updateDiscountCodes(id, discount_codesDetail)
	                .map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // DELETE a genre
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDiscountCodes(@PathVariable Long id) {
	    	discount_codesServices.findDiscountCodesById(id)
	                .ifPresent(genre -> discount_codesServices.deleteDiscountCodes(id));
	        return ResponseEntity.ok().build();
	    }

	    // Additional endpoints can be added here if needed
	}