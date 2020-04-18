package model;

import javax.persistence.*;

@Entity
@Table(name="FOTOS")
public class Foto {
	@Id @GeneratedValue
	private Long id;
	private String nombre;
    private String contentType;
    
    //@Lob
    @Transient
    private byte[] value;
    private Long size;
    
    public Foto() {
    	super();
    }
    
    public void setId(Long id) {
		this.id = id;
	}

	public Long getId(){
    	return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
       this.nombre=nombre;
    }

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}
	
    
    

}