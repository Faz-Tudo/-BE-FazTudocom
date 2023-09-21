package sptech.faztudo.comLOCAL.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sptech.faztudo.comLOCAL.repositorys.userRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    userRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username);
    }

}
