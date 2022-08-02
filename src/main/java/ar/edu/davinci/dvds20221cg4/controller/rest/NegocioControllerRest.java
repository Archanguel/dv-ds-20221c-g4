package ar.edu.davinci.dvds20221cg4.controller.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dvds20221cg4.Constantes;
import ar.edu.davinci.dvds20221cg4.controller.TiendaAppRest;
import ar.edu.davinci.dvds20221cg4.domain.Negocio;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.service.NegocioService;
import ar.edu.davinci.dvds20221cg4.service.VentaService;
import ma.glasnost.orika.MapperFacade;

@RestController
public class NegocioControllerRest extends TiendaAppRest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(NegocioControllerRest.class);

	@Autowired
	private NegocioService negocioService;
	
	/**
	 * Listar
	 */
	@GetMapping(path = "/negocio/all")
	public List<Negocio> getListAll() {
		LOGGER.info("Listar todos las ventas");

		return negocioService.list();
	}

	/**
	 * Obtener ganancias por fecha de venta
	 */
	@GetMapping(path = "negocio/ganancia")
	public List<Negocio> getNegocioVentas(
			@RequestParam(required = true, name = "fecha") String date) {
		LOGGER.info("Fecha: " + date);

		DateFormat formatearFecha = new SimpleDateFormat(Constantes.FORMATO_FECHA);
		Date fecha = null;
		
		List<Negocio> negocios = null;
		
		try {
			fecha = formatearFecha.parse(date);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			negocios = negocioService.calcularGananciaPorDia(fecha).stream().collect(Collectors.toList());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}

		LOGGER.info("Ganancias de las ventas del dia");

		return negocios;
	}
}
