package pe.edu.upc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Personal;
import pe.edu.upc.service.IPersonalService;

@Controller
@RequestMapping("/perfiles")
public class PerfilController {

	@Autowired
	private IPersonalService peS;

	@GetMapping("/nuevo")
	public String nuevoUsuario(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Personal personal = new Personal();
		personal = peS.findByUsername(authentication.getName());
		model.addAttribute("personal", personal);
		return "/perfil/perfil";
	}

	@PostMapping("/guardar")
	public String guardarPerfil(@Valid Personal personal, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/perfil/perfil";
		} else {

		}
		return "/producto/producto";
	}

}
