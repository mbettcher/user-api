package br.com.mtonon.user.resources;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mtonon.user.domain.dto.UserDTO;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@GetMapping(value = "/msg")
	public String mensagem() {
		return "Rota endpoint user funcionando!";
	}
	
	@GetMapping(value = "/list")
	public List<UserDTO> getUsers(){
		return this.usuarios;
	}
	
	@GetMapping(value = "{cpf}")
	public UserDTO getUserFiltro(@PathVariable String cpf) {
		return usuarios.stream().filter(userDTO -> userDTO.getCpf().equals(cpf)).findFirst()
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO inserir(@RequestBody @Valid UserDTO userDTO) {
		userDTO.setDataCadastro(LocalDateTime.now());
		usuarios.add(userDTO);
		return userDTO;
	}
	
	@DeleteMapping(value = "/{cpf}")
	public boolean remover(@PathVariable String cpf) {
		return usuarios.removeIf(userDTO -> userDTO.getCpf().equals(cpf));
	}
	
	public static List<UserDTO> usuarios = new ArrayList<>();
	
	@PostConstruct
	public void initiateList() {
		UserDTO userDTO1 = new UserDTO();
		userDTO1.setNome("Marcelo");
		userDTO1.setCpf("12345678910");
		userDTO1.setEndereco("Rua São Pedro");
		userDTO1.setEmail("mtonon.pmg@gmail.com");
		userDTO1.setTelefone("3361.8200");
		userDTO1.setDataCadastro(LocalDateTime.now());
		
		UserDTO userDTO2 = new UserDTO();
		userDTO2.setNome("Clara");
		userDTO2.setCpf("12345678911");
		userDTO2.setEndereco("Rua das Flores");
		userDTO2.setEmail("cjavarini@gmail.com");
		userDTO2.setTelefone("3361.0820");
		userDTO2.setDataCadastro(LocalDateTime.now());
		
		UserDTO userDTO3 = new UserDTO();
		userDTO3.setNome("Dalva");
		userDTO3.setCpf("12345678922");
		userDTO3.setEndereco("Rua Águas Marinhas");
		userDTO3.setEmail("djava@gmail.com");
		userDTO3.setTelefone("3361.0082");
		userDTO3.setDataCadastro(LocalDateTime.now());
		
		usuarios.add(userDTO1);
		usuarios.add(userDTO2);
		usuarios.add(userDTO3);
	}

}
