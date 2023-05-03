package br.com.mtonon.user.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.mtonon.user.domain.User;
import br.com.mtonon.user.domain.dto.UserDTO;
import br.com.mtonon.user.repositories.UserRepository;

@Service
//@RequiredArgsConstructor
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserDTO> getAll() {
		List<User> usuarios = userRepository.findAll();
		return usuarios.stream().map(UserDTO::convert).collect(Collectors.toList());
	}
	
	public Page<UserDTO> findAllPage(Pageable page){
		Page<User> usuarios = userRepository.findAll(page);
		return usuarios.map(UserDTO::convert);
	}

	public UserDTO findById(Long userId) {
		User usuario = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		return UserDTO.convert(usuario);
	}

	public UserDTO save(UserDTO userDTO) {
		userDTO.setDataCadastro(LocalDateTime.now());
		User user = userRepository.save(User.convert(userDTO));
		return UserDTO.convert(user);
	}

	public UserDTO delete(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		userRepository.delete(user);
		return UserDTO.convert(user);
	}

	public UserDTO findByCpf(String cpf) {
		User user = userRepository.findByCpf(cpf);
		if (user != null) {
			return UserDTO.convert(user);
		}
		return null;
	}

	public List<UserDTO> queryByName(String name) {
		List<User> usuarios = userRepository.queryByNomeLike(name);
		return usuarios.stream().map(UserDTO::convert).collect(Collectors.toList());
	}

	public UserDTO editUser(Long userId, UserDTO userDTO) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		if(userDTO.getEmail() != null && !user.getEmail().equals(userDTO.getEmail())){
			user.setEmail(userDTO.getEmail());
		}
		if(userDTO.getTelefone() != null && !user.getTelefone().equals(userDTO.getTelefone())) {
			user.setTelefone(userDTO.getTelefone());
		}
		if(userDTO.getEndereco() != null && !user.getEndereco().equals(userDTO.getEndereco())) {
			user.setEndereco(userDTO.getEndereco());
		}
		user = userRepository.save(user);
		return UserDTO.convert(user);
	}
}
