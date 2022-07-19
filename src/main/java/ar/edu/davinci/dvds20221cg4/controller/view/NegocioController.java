package ar.edu.davinci.dvds20221cg4.controller.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.davinci.dvds20221cg4.Constantes;
import ar.edu.davinci.dvds20221cg4.controller.TiendaApp;
import ar.edu.davinci.dvds20221cg4.controller.response.VentaEfectivoResponse;
import ar.edu.davinci.dvds20221cg4.controller.response.VentaResponse;
import ar.edu.davinci.dvds20221cg4.controller.response.VentaTarjetaResponse;
import ar.edu.davinci.dvds20221cg4.controller.view.request.VentaEfectivoCreateRequest;
import ar.edu.davinci.dvds20221cg4.controller.view.request.VentaItemCreateRequest;
import ar.edu.davinci.dvds20221cg4.controller.view.request.VentaTarjetaCreateRequest;
import ar.edu.davinci.dvds20221cg4.domain.Item;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.domain.VentaEfectivo;
import ar.edu.davinci.dvds20221cg4.domain.VentaTarjeta;
import ar.edu.davinci.dvds20221cg4.exception.BusinessException;
import ar.edu.davinci.dvds20221cg4.service.ItemService;
import ar.edu.davinci.dvds20221cg4.service.VentaService;
import ma.glasnost.orika.MapperFacade;

@Controller
public class NegocioController extends TiendaApp {
	private final Logger LOGGER = LoggerFactory.getLogger(VentaController.class);

	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private ItemService itemService;
    
    @Autowired
    private MapperFacade mapper;
    
	@GetMapping(path = "negocio/list")
	public String showVentaPage(Model model) {
		LOGGER.info("GET - showVentaPage  - negocio");
		
		Pageable pageable = PageRequest.of(0, 20);
		Page<Venta> ventas = ventaService.list(pageable);
		LOGGER.info("GET - showVentaPage venta importe final: " + ventas.getContent().toString());
		
		model.addAttribute("listVentas", ventas);

		LOGGER.info("ventas.size: " + ventas.getNumberOfElements());
		return "negocio/list_negocio";
	}

}
