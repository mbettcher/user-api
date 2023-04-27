package br.com.mtonon.user.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByCpf(String cpf);
	
	List<User> queryByNomeLike(String nome);
	
	Page<User> findAll(Pageable page);
}
