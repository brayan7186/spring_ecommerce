package com.curso.ecommerce.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.ecommerce.model.DetalleOrden;
import com.curso.ecommerce.model.Orden;
import com.curso.ecommerce.model.Producto;
import com.curso.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {
   
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ProductoService productoService;
	//para almacenar los detalles de la orden 
	List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();
	
	//datos de la orden
	Orden orden = new Orden();
	
	@GetMapping("")
	public String home(Model model) { 
	
	model.addAttribute("productos",productoService.findAll());
		return "usuario/home";
	}
	@GetMapping("productohome/{id}")
	public String productoHome( @PathVariable Integer id, Model model) {
		logger.info("Id producto enviado como parametreo {}",id);
		
		Producto producto = new Producto();
		Optional<Producto>productoOptional = productoService.get(id);		
		producto = productoOptional.get();
		model.addAttribute("producto",producto);
		
		return "usuario/productohome";
	}
	
	
	@PostMapping("/cart")
	public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad ,Model model) {
		
		DetalleOrden detalleOrden = new DetalleOrden();
		Producto producto = new Producto();
     double sumaTotal;
		 Optional<Producto> optionalProducto = productoService.get(id);
		 logger.info("Producto añadido : {}" , optionalProducto.get());
		 logger.info("cantidad : {}" , cantidad);
		 producto =optionalProducto.get();
		 
		 
		 detalleOrden.setCantidad(cantidad);
		 detalleOrden.setPrecio(producto.getPrecio());
		 detalleOrden.setNombre(producto.getNombre());
		 detalleOrden.setTotal(producto.getPrecio());
		 detalleOrden.setProducto(producto);
		 
		 detalles.add(detalleOrden);
		 
		 
		 sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		 
		 orden.setTotal(sumaTotal);
		 model.addAttribute("cart", detalles);
		 model.addAttribute("orden", orden);
		
		return "usuario/carrito.html";
	}
	
	//quitar un producto del carrito 
	@GetMapping("/delete/cart/{id}")
	public  String deleteProdutoCart(@PathVariable Integer id , Model model) {
		
		List<DetalleOrden> oderNuevo = new ArrayList<DetalleOrden>();
		for (DetalleOrden detalleOrden : oderNuevo) {
			
			if (detalleOrden.getProducto().getId()!=id) {
				oderNuevo.add(detalleOrden);
				
			}
		}
		
		//poner la nueva lista  con prductos restantes
		detalles = oderNuevo;
		
 	double sumaTotal= 0;	
     sumaTotal=detalles.stream().mapToDouble(dt->dt.getTotal()).sum();
		 
		 orden.setTotal(sumaTotal);
		 model.addAttribute("cart", detalles);
		 model.addAttribute("orden", orden);
		
		
		
		return "usuario/carrito";
	}
}















































