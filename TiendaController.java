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
import pe.edu.upc.entity.Tienda;
import pe.edu.upc.service.IPersonalService;
import pe.edu.upc.service.ITiendaService;

@Controller
@RequestMapping("/tiendas")
public class TiendaController {
	
	@Autowired
	private ITiendaService tS;
	@Autowired
	private IPersonalService peS;

	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String nuevaTienda(Model model) {
		model.addAttribute("tienda", new Tienda());
		model.addAttribute("listPersonales", peS.list());
		return "tienda/tienda";
	}
	@PostMapping("/save")
	public String grabarTienda(@Valid Tienda tienda, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listPersonales", peS.list());
			return "tienda/tienda";
		} else {
			int rpta = tS.insertar(tienda);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listPersonales", peS.list());
				return "/tienda/tienda";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listTiendas", tS.list());

		return "/tienda/tienda";
	}
	@GetMapping("/list")
	public String listTienda(Model model) {
		try {
			model.addAttribute("tienda", new Tienda());
			model.addAttribute("listTiendas", tS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tienda/listTiendas";
	}
	
	@RequestMapping("/delete")
	public String deleteTienda(Model model, @RequestParam(value="id")Integer id) {
		try {
			if (id != null && id > 0) {
				tS.delete(id);
				model.addAttribute("mensaje", "Se eliminó correctamente");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "No se puede eliminar");
		}
		model.addAttribute("listTiendas", tS.list());

		return "/tienda/listTiendas";

	}
	
	@GetMapping("/detalle/{idTienda}")
	public String detailsTienda(@PathVariable(value = "idTienda") Integer idTienda, Model model) {
		try {
			Optional<Tienda> tienda = tS.listarId(idTienda);
			if (!tienda.isPresent()) {
				model.addAttribute("info", "Tienda no existe");
				return "redirect:/tiendas/list";
			} else {
				model.addAttribute("tienda", tienda.get());
				model.addAttribute("listPersonales", peS.list());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tienda/updateTienda";
	}

	@GetMapping("/listFind")
	public String listTiendasFind(Model model) {
		try {
			model.addAttribute("tienda", new Personal());
			model.addAttribute("listTiendas", tS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tienda/findTienda";
	}
	
	@RequestMapping("/find")
	public String findByTienda(Map<String, Object> model, @ModelAttribute Tienda tienda) throws ParseException {

		List<Tienda> listTiendas;
		tienda.setDesTienda(tienda.getDesTienda());
		listTiendas = tS.findByName(tienda.getDesTienda());

		if (listTiendas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		
		model.put("listTiendas", listTiendas);
		return "tienda/findTiendas";

	}


}
