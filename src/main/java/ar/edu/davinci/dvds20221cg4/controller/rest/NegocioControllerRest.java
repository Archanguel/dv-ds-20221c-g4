package ar.edu.davinci.dvds20221cg4.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dvds20221cg4.controller.TiendaAppRest;
import ar.edu.davinci.dvds20221cg4.controller.response.VentaResponse;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.service.VentaService;
import ma.glasnost.orika.MapperFacade;

@RestController
public class NegocioControllerRest extends TiendaAppRest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ClienteControllerRest.class);

	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private MapperFacade mapper;
	
	/**
	 * Listar
	 */
	@GetMapping(path = "/negocio")
	public List<Venta> getListAll() {
		LOGGER.info("listar todas las ventas");

		return ventaService.list();
	}

	/**
	 * Buscar venta por fecha
	 * @param ventaId identificador del venta
	 * @return retorna el venta
	 */
	//@GetMapping(path = "/negocio")
	public ResponseEntity<Object> getVenta(@PathVariable Long ventaId) {
		LOGGER.info("lista al venta solicitado");

		VentaResponse ventaResponse = null;
		Venta venta = null;
		try {

			venta = ventaService.findById(ventaId);
	
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		try {
			ventaResponse = mapper.map(venta, VentaResponse.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<>(ventaResponse, HttpStatus.CREATED);
	}
}
