package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping("/bienvenido")
	@Secured({ "ROLE_CLIENTE" ,"ROLE_VENDEDOR","ROLE_ADMIN"})
	public String irWelcome() {
		return "bienvenido";
	}
	
	@GetMapping("/new")
	@Secured({ "ROLE_VENDEDOR"})
	public String newProducto(Model model) {
		model.addAttribute("producto", new Producto());
		model.addAttribute("listCategorias", cS.list());
		return "producto/producto";
	}
	
	@PostMapping("/save")
	@Secured({ "ROLE_VENDEDOR"})
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
	@Secured({ "ROLE_VENDEDOR","ROLE_ADMIN","ROLE_CLIENTE"})
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
	@Secured({ "ROLE_VENDEDOR","ROLE_ADMIN"})
	public String deleteProducto(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if (id != null && id > 0) {
				pS.delete(id);
				model.put("mensaje", "Eliminado correctamente");

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No puedes eliminar producto");
		}
		model.put("listProductos", pS.list());

//		return "redirect:/categories/list";
		return "/producto/listProductos";
	}
	
	
	@GetMapping("/modificar/{id}")
	@Secured({ "ROLE_VENDEDOR","ROLE_ADMIN"})
	public String modificarProducto(@PathVariable Integer id, Model model, RedirectAttributes objRedir) {

		Optional<Producto> objPro = pS.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/producto/listaProductos";
		} else {
			model.addAttribute("listCategorias", cS.list());
			
			model.addAttribute("producto", objPro.get());
			return "producto/updateProducto";
		}
	}
	
	@RequestMapping("/find")
	@Secured({ "ROLE_VENDEDOR","ROLE_ADMIN","ROLE_CLIENTE"})
	public String findByProducto(Map<String, Object> model, @ModelAttribute Producto producto) throws ParseException {

		List<Producto> listProductos;
		producto.setDesProducto(producto.getDesProducto());
		listProductos = pS.findByDesProducto(producto.getDesProducto());

		if (listProductos.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		
		model.put("listProductos", listProductos);
		return "producto/listProductos";

	}
}
