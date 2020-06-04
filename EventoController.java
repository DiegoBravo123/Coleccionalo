package pe.edu.upc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Evento;
import pe.edu.upc.service.IEventoService;

@Controller
@RequestMapping("/eventos")
public class EventoController {
	
	@Autowired
	public IEventoService eS;
	
	@GetMapping("/new")
	@Secured({ "ROLE_ADMIN" })
	public String nuevoEvento(Model model) {
		model.addAttribute("evento", new Evento());
		return "evento/evento";
	}
	
	@PostMapping("/save")
	public String saveEvento(@Valid Evento evento, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "evento/evento";
		} else {
			int rpta = eS.insertar(evento);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/evento/evento";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listEventos", eS.list());

		return "/evento/evento";
	}

}
