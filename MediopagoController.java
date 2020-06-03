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

import pe.edu.upc.entity.Mediopago;
import pe.edu.upc.service.IMediopagoService;


@Controller
@RequestMapping("/mediopagos")
public class MediopagoController {

	@Autowired
	public IMediopagoService mS;
	
	@GetMapping("/new")
	@Secured({ "ROLE_ADMIN" })
	public String nuevoMediopago(Model model) {
		model.addAttribute("mediopago", new Mediopago());
		return "mediopago/mediopago";
	}
	
	@PostMapping("/save")
	public String saveMediopago(@Valid Mediopago mediopago, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "mediopago/mediopago";
		} else {
			int rpta = mS.insertar(mediopago);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/mediopago/mediopago";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listMediopagos", mS.list());

		return "/mediopago/mediopago";
	}
}
