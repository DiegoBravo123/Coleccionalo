package pe.edu.upc.controller;

import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.DetalleBoleta;
import pe.edu.upc.service.IBoletaService;
import pe.edu.upc.service.IDetalleBoletaService;
import pe.edu.upc.service.IProductoService;

@Controller
@RequestMapping("/detalleboletas")
public class DetalleBoletaController {
	
	@Autowired
	private IDetalleBoletaService dbS;
	@Autowired
	private IBoletaService bS;
	@Autowired
	private IProductoService pS;

	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	
	@GetMapping("/new")
	public String newDetalleBoleta(Model model) {
		model.addAttribute("detalleBoleta", new DetalleBoleta());
		model.addAttribute("listBoletas", bS.list());
		model.addAttribute("listProductos", pS.list());
		return "detalleBoleta/detalleBoleta";
	}
	
	@PostMapping("/save")
	public String saveDetalleBoleta(@Valid DetalleBoleta detalleBoleta, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listBoletas", bS.list());
			model.addAttribute("listProductos", pS.list());
			return "detalleBoleta/detalleBoleta";
		} else {
			int rpta = dbS.insertar(detalleBoleta);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listBoletas", bS.list());
				model.addAttribute("listProductos", pS.list());
				return "/detalleBoleta/detalleBoleta";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listDetalleBoletas", dbS.list());

		return "/detalleBoleta/listDetalleBoletas";
	}
	
	@GetMapping("/list")
	public String listDetalleBoleta(Model model) {
		try {
			model.addAttribute("detalleBoleta", new DetalleBoleta());
			model.addAttribute("listDetalleBoletas", dbS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detalleBoleta/listDetalleBoletas";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<DetalleBoleta> detalleBoleta = dbS.listarId(id);
		if (detalleBoleta == null) {
			flash.addFlashAttribute("error", "El Producto no existe en la base de datos");
			return "redirect:/detalleboletas/list";
		}

		model.put("detalleBoleta", detalleBoleta.get());

		return "detalleBoleta/ver";
	}

}
