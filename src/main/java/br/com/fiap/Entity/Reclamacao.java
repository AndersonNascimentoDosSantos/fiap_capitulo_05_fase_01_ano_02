package br.com.fiap.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.fiap.Enums.StatusReclamacao;

@Entity
@Table(name = "tbl_reclamacao")
public class Reclamacao {
    
    @Id
    @SequenceGenerator(name="reclamacao",sequenceName="seq_reclamacao",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="reclamacao")
    @Column(name = "id_reclamacao")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    
    @Column(name = "dt_data_criacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;
    
    @Column(name = "ds_latitude")
    private double latitude;
    
    @Column(name = "ds_longitude")
    private double longitude;
    
    @Column(name = "ds_descricao")
    private String descricao;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ds_status", nullable = false)
    private StatusReclamacao status;

       
    
	public Reclamacao() {
		super();
		// TODO Auto-generated constructor stub
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

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusReclamacao getStatus() {
		return status;
	}

	public void setStatus(StatusReclamacao status) {
		this.status = status;
	}
    
    // constructors, getters, and setters
}

