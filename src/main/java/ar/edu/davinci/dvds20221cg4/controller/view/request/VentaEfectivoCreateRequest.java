package ar.edu.davinci.dvds20221cg4.controller.view.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaEfectivoCreateRequest {

	private Long clienteId;

	private Long negocioId;
	
	private String fecha; 
}
