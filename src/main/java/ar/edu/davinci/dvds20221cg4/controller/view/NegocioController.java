package ar.edu.davinci.dvds20221cg4.controller.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ar.edu.davinci.dvds20221cg4.controller.TiendaApp;
import ar.edu.davinci.dvds20221cg4.domain.Negocio;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.service.NegocioService;
import ar.edu.davinci.dvds20221cg4.service.VentaService;

@Controller
public class NegocioController extends TiendaApp {
	private final Logger LOGGER = LoggerFactory.getLogger(NegocioController.class);

	@Autowired
	private NegocioService negocioService;
	@Autowired
	private VentaService ventaService;
	//private List<Venta> ventas;
    
	@GetMapping(path = "negocio/list")
	public String showVentaPage(Model model) {
		LOGGER.info("GET - showVentaPage  - /negocio/list");
		
		Pageable pageable = PageRequest.of(0, 20);
		Page<Venta> ventas = ventaService.list(pageable);
		LOGGER.info("GET - showVentaPage venta importe final: " + ventas.getContent().toString());
		
		model.addAttribute("listVentas", ventas);

		LOGGER.info("ventas.size: " + ventas.getNumberOfElements());
		return "negocio/list_negocio";
	}
	
	/*@GetMapping(path = "negocio/list")
	public String showVentaPage(Model model) {
		LOGGER.info("GET - showVentaPage  - negocio");
		
		LOGGER.info("GET - showVentaPage venta importe final: " + negocioService.getContent().toString());
		
		model.addAttribute("listVentas", negocios);

		LOGGER.info("ventas.size: " + negocios.getNumberOfElements());
		return "negocio/list_negocio";
	}*/

}
