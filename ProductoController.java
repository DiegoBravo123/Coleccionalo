package pe.edu.upc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;


import pe.edu.upc.entity.Producto;
import pe.edu.upc.service.ICategoriaService;
import pe.edu.upc.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService pS;
	@Autowired
	private ICategoriaService cS;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String newProducto(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("listCategorias", cS.list());
		return "producto/producto";
	}
	
	@PostMapping("/save")
	public String saveProducto(@Valid Producto producto, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listCategorias", cS.list());
			return "producto/producto";
		} else {
			int rpta = pS.insert(producto);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe");
				model.addAttribute("listCategorias", cS.list());
				return "/producto/producto";
			} else {
				model.addAttribute("mensaje", "Se guardo correctamente");
				status.setComplete();
			}
		}
		model.addAttribute("listProductos", pS.list());

		return "/producto/listProductos";
	}

	@GetMapping("/list")
	public String listProducto(Model model) {
		try {
			model.addAttribute("producto", new Producto());
			model.addAttribute("listProductos", pS.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/producto/listProductos";
	}
	
	@RequestMapping("/delete")
	public String deleteProducto(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pS.delete(id);
				model.put("mensaje", "Eliminado correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "You can't delete a publisher");
		}
		model.put("listProductos", pS.list());

//		return "redirect:/categories/list";
		return "/producto/listProductos";
	}
}
