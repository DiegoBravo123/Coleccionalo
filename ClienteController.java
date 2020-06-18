package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;


import pe.edu.upc.entity.Cliente;
import pe.edu.upc.service.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService cS;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String newCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cliente/cliente";
	}
	
	@PostMapping("/save")
	public String saveCliente(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "cliente/cliente";
		} else {
			int rpta = cS.insertar(cliente);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/cliente/cliente";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listClientes", cS.list());

		return "/cliente/cliente";
	}
	
	@GetMapping("/list")
	public String listClientes(Model model) {
		try {
			model.addAttribute("cliente", new Cliente());
			model.addAttribute("listClientes", cS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/cliente/listClientes";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				cS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listClientes", cS.list());

		return "/cliente/listClientes";

	}
	
	@GetMapping("/detalle/{idCliente}")
	public String detailsJuego(@PathVariable(value = "idCliente") Integer idCliente, Model model) {
		try {
			Optional<Cliente> cliente = cS.listarId(idCliente);
			if (!cliente.isPresent()) {
				model.addAttribute("info", "Cliente no existe");
				return "redirect:/clientes/list";
			} else {
				model.addAttribute("cliente", cliente.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/cliente/updateCliente";
	}
	
	@GetMapping("/listFind")
	public String listClientesFind(Model model) {
		try {
			model.addAttribute("cliente", new Cliente());
			model.addAttribute("listClientes", cS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/cliente/findCliente";
	}
	
	@RequestMapping("/find")
	public String findByCliente(Map<String, Object> model, @ModelAttribute Cliente cliente) throws ParseException {

		List<Cliente> listClientes;
		cliente.setDesCliente(cliente.getDesCliente());
		listClientes = cS.findByName(cliente.getDesCliente());

		if (listClientes.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		
		model.put("listClientes", listClientes);
		return "cliente/findCliente";

	}
}
