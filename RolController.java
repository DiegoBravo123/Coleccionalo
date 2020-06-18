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

import pe.edu.upc.entity.Rol;
import pe.edu.upc.service.IRolService;

@Controller
@RequestMapping("/roles")
public class RolController {
	
	@Autowired
	private IRolService rS;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String nuevoRol(Model model) {
		model.addAttribute("rol", new Rol());
		return "rol/rol";
	}
	@PostMapping("/save")
	public String grabarRol(@Valid Rol rol, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "rol/rol";
		} else {
			int rpta = rS.insertar(rol);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				return "/rol/rol";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listRoles", rS.list());

		return "rol/rol";
	}
	@GetMapping("/list")
	public String listRol(Model model) {
		try {
			model.addAttribute("rol", new Rol());
			model.addAttribute("listRoles", rS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "rol/listRoles";
	}
	
	@RequestMapping("/delete")
	public String delete(Model model, @RequestParam(value="id")Integer id) {
		try {
			if (id != null && id > 0) {
				rS.delete(id);
				model.addAttribute("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se puede eliminar");
		}
		model.addAttribute("listRoles", rS.list());

		return "rol/listRoles";

	}
	
	@GetMapping("/detalle/{idRol}")
	public String detailsRol(@PathVariable(value = "idRol") Integer idRol, Model model) {
		try {
			Optional<Rol> rol = rS.listarId(idRol);
			if (!rol.isPresent()) {
				model.addAttribute("info", "Rol no existe");
				return "redirect:/roles/list";
			} else {
				model.addAttribute("rol", rol.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "rol/updateRol";
	}

	@GetMapping("/listFind")
	public String listRolesFind(Model model) {
		try {
			model.addAttribute("rol", new Rol());
			model.addAttribute("listRoles", rS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "rol/findRol";
	}
	
	@RequestMapping("/find")
	public String findByRol(Map<String, Object> model, @ModelAttribute Rol rol) throws ParseException {

		List<Rol> listRoles;
		rol.setDesRol(rol.getDesRol());
		listRoles = rS.findByName(rol.getDesRol());

		if (listRoles.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		
		model.put("listRoles", listRoles);
		return "rol/findRoles";

	}

}
