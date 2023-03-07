package br.com.fiap.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_avaliacao")
public class Avaliacao {
    @Id
    @SequenceGenerator(name="avaliacao",sequenceName="seq_avaliacao",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="avaliacao")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_empresa_eletrica", nullable = false)
    private Empresa empresa;
    
    @Column(name="ds_nota",nullable = false)
    private Integer nota;
    
    @Column(name="ds_comentario",columnDefinition = "CLOB")
    private String comentario;
    
    @Column(name="dt_data_avaliacao",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAvaliacao;
    
    
    
    
    
    public Avaliacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PrePersist
    protected void onCreate() {
    	this.dataAvaliacao = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Integer getNota() {
		return nota;
	}

	public void setNota(Integer nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}
    
    
   
}
