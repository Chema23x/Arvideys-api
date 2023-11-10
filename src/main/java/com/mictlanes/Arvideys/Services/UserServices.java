package com.mictlanes.Arvideys.Services;


import java.util.List;


import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mictlanes.Arvideys.Models.DiscountCodes;
import com.mictlanes.Arvideys.Models.User;
import com.mictlanes.Arvideys.Repository.DiscountCodesRepository;
import com.mictlanes.Arvideys.Repository.UserRepository;
@Service
public class UserServices {

    private final UserRepository userRepository;
    private final DiscountCodesRepository discountCodesRespository;
    
    @Autowired
    
    public UserServices(UserRepository userRepository, DiscountCodesRepository discountCodesRespository) {
    	super();
    	this.userRepository = userRepository;
    	this.discountCodesRespository = discountCodesRespository;
    }

    // Método para obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


	// Método para obtener un usuario por su ID
    public User getUserById(Long id) throws AttributeNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new AttributeNotFoundException("Book not found for this id :: " + id));
    }
    
    @Transactional
    public User createUser(User user, Long discountCodesId) throws AttributeNotFoundException {
        DiscountCodes discount_codes = discountCodesRespository.findById(discountCodesId)
                .orElseThrow(() -> new AttributeNotFoundException("DiscountCodes not found for this id :: " + discountCodesId));
        
        user.setDiscount_codes(discount_codes);

        return userRepository.save(user);
    }
    
    // Método para actualizar un usuario existente
    @Transactional
    public User updateUser(Long id, User userDetails, Long discountCodesId) throws AttributeNotFoundException {
       User user = getUserById(id);
            
      user.setFirst_name(userDetails.getFirst_name());
      user.setLast_name(userDetails.getLast_name());
      user.setPhone_number(userDetails.getPhone_number());
      user.setEmail_address(userDetails.getEmail_address());
      user.setAddress(userDetails.getAddress());
      user.setPassword(userDetails.getPassword());
                    
      DiscountCodes discount_codes = discountCodesRespository.findById(discountCodesId)
              .orElseThrow(() -> new AttributeNotFoundException("DiscountCodes not found for this id :: " + discountCodesId));
                      
      user.setDiscount_codes(discount_codes);
                        
      return userRepository.save(user);
    }

    // Método para eliminar un usuario por su ID
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}