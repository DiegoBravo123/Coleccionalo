package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Cliente;
import pe.edu.upc.entity.Mediopago;
import pe.edu.upc.service.IClienteService;
import pe.edu.upc.service.IMediopagoService;


@Controller
@RequestMapping("/mediopagos")
public class MediopagoController {

	@Autowired
	public IMediopagoService mS;
	
	@Autowired
	public IClienteService cS;
	
	@RequestMapping("/index")
	@Secured({ "ROLE_VENDEDOR","ROLE_ADMIN","ROLE_CLIENTE"})
	public String irWelcome() {
		return "welcome";
	}
	@GetMapping("/new")
	@Secured({ "ROLE_CLIENTE"})
	public String nuevoMediopago(Model model) {
		model.addAttribute("mediopago", new Mediopago());
		return "mediopago/mediopago";
	}
	
	@PostMapping("/save")
	@Secured({"ROLE_CLIENTE"})
	public String saveMediopago(@Valid Mediopago mediopago, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Cliente cliente = new Cliente();
		cliente = cS.findByUsername(authentication.getName());
		
		
		
		if (result.hasErrors()) {
			return "mediopago/mediopago";
		} else {
			mediopago.setCliente(cliente);

			mS.insertar(mediopago);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
		}
		model.addAttribute("listMediopagos", mS.list(cliente));

		return "/mediopago/mediopago";
	}
	
	@GetMapping("/list")
	@Secured({ "ROLE_CLIENTE"})
	public String listMediopago(Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Cliente cliente = new Cliente();
			cliente = cS.findByUsername(authentication.getName());
			
			
			
			model.addAttribute("mediopago", new Mediopago());
			model.addAttribute("listMediopagos", mS.list(cliente));
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/mediopago/listMediopagos";
	}
	
	@RequestMapping("/delete")
	@Secured({ "ROLE_VENDEDOR","ROLE_ADMIN","ROLE_CLIENTE"})
	public String delete(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Cliente cliente = new Cliente();
		cliente = cS.findByUsername(authentication.getName());
		
		try {
	
				
			
			
			
			if (id != null && id > 0) {
				mS.delete(id);
				model.put("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se puede eliminar");
		}
		model.put("listMediopagos", mS.list(cliente));

		return "/mediopago/listMediopagos";

	}
	
	@GetMapping("/detalle/{idMedioPago}")
	@Secured({ "ROLE_VENDEDOR","ROLE_ADMIN","ROLE_CLIENTE"})
	public String detailsJuego(@PathVariable(value = "idMedioPago") Integer idMedioPago, Model model) {
		try {
			Optional<Mediopago> mediopago = mS.listarId(idMedioPago);
			if (!mediopago.isPresent()) {
				model.addAttribute("info", "Medio pago no existe");
				return "redirect:/mediopagos/list";
			} else {
				model.addAttribute("mediopago", mediopago.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/mediopago/updateMediopago";
	}
	
}
