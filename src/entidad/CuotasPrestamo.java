package entidad;

import java.time.LocalDate;

public class CuotasPrestamo {

		private long cuota;
		private Prestamo prestamo;
		private LocalDate fechaPago;
		private boolean estado;
		
		public CuotasPrestamo() {
			
		}

		public long getCuota() {
			return cuota;
		}

		public void setCuota(long cuota) {
			this.cuota = cuota;
		}

		public Prestamo getPrestamo() {
			return prestamo;
		}

		public void setPrestamo(Prestamo prestamo) {
			this.prestamo = prestamo;
		}

		public LocalDate getFechaPago() {
			return fechaPago;
		}

		public void setFechaPago(LocalDate fechaPago) {
			this.fechaPago = fechaPago;
		}

		public boolean getEstado() {
			return estado;
		}

		public void setEstado(boolean estado) {
			this.estado = estado;
		}

		@Override
		public String toString() {
			return "CuotasPrestamo [cuota=" + cuota + ", prestamo=" + prestamo + ", fechaPago=" + fechaPago + ", estado="
					+ estado + "]";
		}
		
		
		
	
}
