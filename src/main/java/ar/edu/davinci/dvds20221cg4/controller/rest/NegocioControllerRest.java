package ar.edu.davinci.dvds20221cg4.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.davinci.dvds20221cg4.controller.TiendaAppRest;
import ar.edu.davinci.dvds20221cg4.controller.request.ItemInsertRequest;
import ar.edu.davinci.dvds20221cg4.controller.request.ItemUpdateRequest;
import ar.edu.davinci.dvds20221cg4.controller.request.VentaEfectivoRequest;
import ar.edu.davinci.dvds20221cg4.controller.request.VentaTarjetaRequest;
import ar.edu.davinci.dvds20221cg4.controller.response.VentaResponse;
import ar.edu.davinci.dvds20221cg4.domain.Item;
import ar.edu.davinci.dvds20221cg4.domain.Venta;
import ar.edu.davinci.dvds20221cg4.domain.VentaEfectivo;
import ar.edu.davinci.dvds20221cg4.domain.VentaTarjeta;
import ar.edu.davinci.dvds20221cg4.service.VentaService;
import ma.glasnost.orika.MapperFacade;

@RestController
public class NegocioControllerRest extends TiendaAppRest {
	
	private final Logger LOGGER = LoggerFactory.getLogger(VentaControllerRest.class);

	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private MapperFacade mapper;
	
	/**
	 * Listar
	 */
	@GetMapping(path = "negocio/all")
	public List<Venta> getListAll() {
		LOGGER.info("listar todas las ventas");

		return ventaService.list();
	}
	

	/**
	 * Listar paginado
	 */
	@GetMapping(path = "negocio/list")
	public ResponseEntity<Page<VentaResponse>> getList(Pageable pageable) {
		
		LOGGER.info("listar todas las ventas paginadas");
		LOGGER.info("Pageable: " + pageable);
		
		Page<VentaResponse> ventaResponse = null;
		Page<Venta> ventas = null;
		try {
			ventas = ventaService.list(pageable);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		try {
			ventaResponse = ventas.map(venta -> mapper.map(venta, VentaResponse.class));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ventaResponse, HttpStatus.CREATED);
	}

}
