package com.generation.SaviOurFood.security;

import com.generation.SaviOurFood.model.User;
import com.generation.SaviOurFood.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

//valida a existencia do usuario se ele existe no banco de dados, autenticando - o.
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    Optional<User> user = userRepository.findByEmail(userName);

    if (user.isPresent())
      return new UserDetailsImpl(user.get());
    else
      throw new ResponseStatusException(HttpStatus.FORBIDDEN);
  }
}