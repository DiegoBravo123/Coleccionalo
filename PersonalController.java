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

import pe.edu.upc.entity.Personal;
import pe.edu.upc.service.IPersonalService;
import pe.edu.upc.service.IRolService;

@Controller
@RequestMapping("/personales")
public class PersonalController {
	
	@Autowired
	private IPersonalService peS;
	@Autowired
	private IRolService rS;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String nuevoRol(Model model) {
		model.addAttribute("personal", new Personal());
		model.addAttribute("listRoles", rS.list());
		return "personal/personal";
	}
	@PostMapping("/save")
	public String grabarPersonal(@Valid Personal personal, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listRoles", rS.list());
			return "personal/personal";
		} else {
			int rpta = peS.insertar(personal);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listRoles", rS.list());
				return "/personal/personal";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listPersonales", peS.list());

		return "/personal/personal";
	}
	@GetMapping("/list")
	public String listPersonal(Model model) {
		try {
			model.addAttribute("personal", new Personal());
			model.addAttribute("listPersonales", peS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/personal/listPersonales";
	}
	
	@RequestMapping("/delete")
	public String deletePersonal(Model model, @RequestParam(value="id")Integer id) {
		try {
			if (id != null && id > 0) {
				peS.delete(id);
				model.addAttribute("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se puede eliminar");
		}
		model.addAttribute("listPersonales", peS.list());

		return "/personal/listPersonales";

	}
	
	@GetMapping("/detalle/{idPersonal}")
	public String detailsPersonal(@PathVariable(value = "idPersonal") Integer idPersonal, Model model) {
		try {
			Optional<Personal> personal = peS.listarId(idPersonal);
			if (!personal.isPresent()) {
				model.addAttribute("info", "Personal no existe");
				return "redirect:/personales/list";
			} else {
				model.addAttribute("personal", personal.get());
				model.addAttribute("listRoles", rS.list());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/personal/updatePersonal";
	}

	@GetMapping("/listFind")
	public String listPersonalesFind(Model model) {
		try {
			model.addAttribute("personal", new Personal());
			model.addAttribute("listPersonales", peS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/personal/findPersonal";
	}
	
	@RequestMapping("/find")
	public String findByPersonal(Map<String, Object> model, @ModelAttribute Personal personal) throws ParseException {

		List<Personal> listPersonales;
		personal.setDesPersonal(personal.getDesPersonal());
		listPersonales = peS.findByName(personal.getDesPersonal());

		if (listPersonales.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		
		model.put("listPersonales", listPersonales);
		return "personal/findPersonales";

	}

}
