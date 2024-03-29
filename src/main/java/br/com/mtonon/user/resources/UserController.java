package br.com.mtonon.user.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.mtonon.user.domain.dto.UserDTO;
import br.com.mtonon.user.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
//@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/msg")
	public String mensagem() {
		return "Rota endpoint user funcionando!";
	}
	
	@GetMapping(value = "/listar")
	public List<UserDTO> getUsers(){
		return userService.getAll();
	}
	
	@GetMapping(value = "/{id}")
	public UserDTO findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@GetMapping(value = "/{cpf}/cpf")
	public UserDTO getUserFiltro(@PathVariable String cpf) {
		return userService.findByCpf(cpf);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO inserir(@RequestBody @Valid UserDTO userDTO) {
		return userService.save(userDTO);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
	
	@GetMapping(value = "/search")
	public List<UserDTO> queryByName(@RequestParam(name="nome", required = true) String nome){
		return userService.queryByName(nome);
	}
	
	@PatchMapping(value = "/{id}")
	public UserDTO editUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return userService.editUser(id, userDTO);
	}
	
	@GetMapping(value = "/pageable")
	public Page<UserDTO> getUsersPage(Pageable pageable) {
		return userService.findAllPage(pageable);
	}

}
