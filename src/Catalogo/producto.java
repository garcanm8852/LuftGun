package Catalogo;

public class producto {

	String idreferencia;
	String nombre;
	String marca;
	String descripcion;
	Double precio;
	byte[] img;
	int stock;
	int subcategoria;

	public producto(String pidreferencia, String pnombre, String pmarca, String pdescripcion, Double pprecio,
			byte[] pimg, int pstock, int psubcategoria) {
		idreferencia = pidreferencia;
		nombre = pnombre;
		marca = pmarca;
		descripcion = pdescripcion;
		precio = pprecio;
		img = pimg;
		stock = pstock;
		subcategoria = psubcategoria;
	}

	public String getIdreferencia() {
		return idreferencia;
	}

	public void setIdreferencia(String idreferencia) {
		this.idreferencia = idreferencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(int subcategoria) {
		this.subcategoria = subcategoria;
	}

}
