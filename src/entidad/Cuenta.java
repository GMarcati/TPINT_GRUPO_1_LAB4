package entidad;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.concurrent.ThreadLocalRandom;


public class Cuenta 
{
		private long idCuenta;
		private long idUsuario;
		private long numeroCuenta;
		private TipoCuenta tipoCuenta;
		private Date fechaCreacion;
		private long CBU;
		private double saldo;
		private int estado;
		
		public Cuenta() 
		{
			this.numeroCuenta=GenerarNumeroCuenta();
			this.CBU=GenerarNumeroCBU();
		}
		
		public long GenerarNumeroCuenta() 
		{
			numeroCuenta= ThreadLocalRandom.current().nextLong(100000000, 999999999);
			
			return numeroCuenta;
		}
		
		public long GenerarNumeroCBU() 
		{
			CBU= ThreadLocalRandom.current().nextLong(100000000, 999999999);
			//CBU+= ThreadLocalRandom.current().nextLong(100000000, 999999999);
			return CBU;
		}
		

		public Cuenta(long idCuenta, long numeroCuenta, TipoCuenta tipoCuenta, Date fechaCreacion, long cBU,
				double saldo, int estado) {
			super();
			this.idCuenta = idCuenta;
			this.numeroCuenta = numeroCuenta;
			this.tipoCuenta = tipoCuenta;
			this.fechaCreacion = fechaCreacion;
			CBU = cBU;
			this.saldo = saldo;
			this.estado = estado;
		}


		@Override
		public String toString() {
			return "Cuenta [idCuenta=" + idCuenta + ", idUsuario=" + idUsuario + ", numeroCuenta=" + numeroCuenta
					+ ", tipoCuenta=" + tipoCuenta + ", fechaCreacion=" + fechaCreacion + ", CBU=" + CBU + ", saldo="
					+ saldo + ", estado=" + estado + "]";
		}

		public int getEstado() {
			return estado;
		}

		public void setEstado(int estado) {
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

		public double getSaldo() {
			return saldo;
		}

		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}

		public long getIdUsuario() {
			return idUsuario;
		}

		public void setIdUsuario(long idUsuario) {
			this.idUsuario = idUsuario;
		}

}
