package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Boleta;
import pe.edu.upc.service.IBoletaService;
import pe.edu.upc.service.IClienteService;
import pe.edu.upc.service.IMediopagoService;
import pe.edu.upc.service.ITiendaService;


@Controller
@RequestMapping("/boletas")
public class BoletaController {

	@Autowired
	private IBoletaService bS;
	@Autowired
	private IClienteService cS;
	@Autowired
	private IMediopagoService mS;
	@Autowired
	private ITiendaService tS;
	
	@RequestMapping("/index")
	@Secured({ "ROLE_CLIENTE" ,"ROLE_VENDEDOR","ROLE_ADMIN"})
	public String irWelcome() {
		return "welcome";
	}
	
	
	@GetMapping("/new")
	@Secured({ "ROLE_VENDEDOR"})
	public String newBoleta(Model model) {
		model.addAttribute("boleta", new Boleta());
		model.addAttribute("listClientes", cS.list());
		model.addAttribute("listMediopagos", mS.list());
		model.addAttribute("listTiendas", tS.list());
		return "boleta/boleta";
	}
	
	@PostMapping("/save")
	@Secured({ "ROLE_VENDEDOR"})
	public String saveBoleta(@Valid Boleta boleta, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listClientes", cS.list());
			model.addAttribute("listMediopagos", mS.list());
			model.addAttribute("listTiendas", tS.list());
			return "boleta/boleta";
		} else {
			int rpta = bS.insert(boleta);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listClientes", cS.list());
				model.addAttribute("listMediopagos", mS.list());
				model.addAttribute("listTiendas", tS.list());
				return "/boleta/boleta";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listBoletas", bS.list());

		return "/boleta/listBoletas";
	}
	
	@GetMapping("/list")
	@Secured({ "ROLE_CLIENTE" ,"ROLE_VENDEDOR","ROLE_ADMIN"})
	public String listBoleta(Model model) {
		try {
			model.addAttribute("boleta", new Boleta());
			model.addAttribute("listBoletas", bS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/boleta/listBoletas";
	}
	
	
	@RequestMapping("/delete")
	@Secured({ "ROLE_VENDEDOR"})
	public String deleteBoleta(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				bS.delete(id);
				model.put("mensaje", "Eliminado correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No puedes eliminar producto");
		}
		model.put("listBoletas", bS.list());

//		return "redirect:/categories/list";
		return "/boleta/listBoletas";
	}
	
	@GetMapping("/modificar/{id}")
	@Secured({ "ROLE_VENDEDOR"})
	public String modificarBoleta(@PathVariable Integer id, Model model, RedirectAttributes objRedir) {

		Optional<Boleta> objPro = bS.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/boleta/listaBoletas";
		} else {
			model.addAttribute("listClientes", cS.list());
			model.addAttribute("listMediopagos", mS.list());
			model.addAttribute("listTiendas", tS.list());
			
			model.addAttribute("boleta", objPro.get());
			return "boleta/updateBoleta";
		}
	}
	
}
