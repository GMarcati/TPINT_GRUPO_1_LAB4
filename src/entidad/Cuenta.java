package entidad;

import java.math.BigDecimal;
import java.sql.Date;


public class Cuenta {

		private long idCuenta;
		private long numeroCuenta;
		private TipoCuenta tipoCuenta;
		private Date fechaCreacion;
		private long CBU;
		private BigDecimal saldo;
		private Estado estado;
		
		public Cuenta() {
			
			
		}
		
		public Cuenta(long idCuenta, long numeroCuenta, TipoCuenta tipoCuenta, Date fechaCreacion, long cBU,
				BigDecimal saldo, Estado estado) {
			super();
			this.idCuenta = idCuenta;
			this.numeroCuenta = numeroCuenta;
			this.tipoCuenta = tipoCuenta;
			this.fechaCreacion = fechaCreacion;
			CBU = cBU;
			this.saldo = saldo;
			this.estado = estado;
		}

		public long getIdCuenta() {
			return idCuenta;
		}

		public void setIdCuenta(long idCuenta) {
			this.idCuenta = idCuenta;
		}

		public long getNumeroCuenta() {
			return numeroCuenta;
		}

		public void setNumeroCuenta(long numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
		}

		public TipoCuenta getTipoCuenta() {
			return tipoCuenta;
		}

		public void setTipoCuenta(TipoCuenta tipoCuenta) {
			this.tipoCuenta = tipoCuenta;
		}

		public Date getFechaCreacion() {
			return fechaCreacion;
		}

		public void setFechaCreacion(Date fechaCreacion) {
			this.fechaCreacion = fechaCreacion;
		}

		public long getCBU() {
			return CBU;
		}

		public void setCBU(long cBU) {
			CBU = cBU;
		}

		public BigDecimal getSaldo() {
			return saldo;
		}

		public void setSaldo(BigDecimal saldo) {
			this.saldo = saldo;
		}

		public Estado getEstado() {
			return estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}

		@Override
		public String toString() {
			return "Cuenta [idCuenta=" + idCuenta + ", numeroCuenta=" + numeroCuenta + ", tipoCuenta=" + tipoCuenta
					+ ", fechaCreacion=" + fechaCreacion + ", CBU=" + CBU + ", saldo=" + saldo + ", estado=" + estado
					+ "]";
		}
		
		
		
		
		
	
}
