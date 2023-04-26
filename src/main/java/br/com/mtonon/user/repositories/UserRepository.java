package br.com.mtonon.user.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtonon.user.domain.User;
import br.com.mtonon.user.domain.dto.UserDTO;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByCpf(String cpf);
	
	List<User> queryByNomeLike(String nome);
	
	Page<UserDTO> getAllPageble(Pageable page);
}
