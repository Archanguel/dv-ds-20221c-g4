package ar.edu.davinci.dvds20221cg4.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Calcular Venta en Tarjeta
 * 
 * @author frmontero
 *
 */

@Entity
@PrimaryKeyJoinColumn(name = "vta_id")
@DiscriminatorValue("TARJETA")
@Table(name="ventas_tarjeta")


@Data
@NoArgsConstructor
@SuperBuilder
public class VentaTarjeta extends Venta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7549753306871297143L;

	@Column(name = "vtt_cantidad_cuotas")
	private Integer cantidadCuotas;
	
	@Column(name = "vtt_coeficiente")
	private BigDecimal coeficienteTarjeta;

	public BigDecimal importeRecargado() {
		return super.importeBruto()
				.add(super.importeBruto().multiply(new BigDecimal(0.01)))
						.add(coeficienteTarjeta
								.multiply(new BigDecimal(cantidadCuotas)))
									.multiply(new BigDecimal(this.getItems().size()));
	}
	
	@Override
	public Double conRecargo(Double importeBase) {
		return importeBase + (importeBase * 0.01 + coeficienteTarjeta.doubleValue() * cantidadCuotas.doubleValue());
	}

}
