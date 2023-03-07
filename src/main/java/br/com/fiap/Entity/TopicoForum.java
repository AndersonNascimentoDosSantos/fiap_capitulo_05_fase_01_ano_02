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
@Table(name = "tbl_topico_forum")
public class TopicoForum {

	@Id
	@SequenceGenerator(name = "topico", sequenceName = "seq_topico_forum", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topico")
	@Column(name = "id_topico")
	private Long idTopico;
	@Column(name = "ds_titulo", nullable = false)
	private String titulo;
	@Column(name = "dt_descricao", nullable = false, columnDefinition = "CLOB")
	private String descricao;

	@Column(name = "dt_data_criacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuarioCriador;

	@PrePersist
    protected void onCreate() {
    	this.dataCriacao = new Date();
    }

	public Long getIdTopico() {
		return idTopico;
	}

	public void setIdTopico(Long idTopico) {
		this.idTopico = idTopico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Usuario getUsuarioCriador() {
		return usuarioCriador;
	}

	public void setUsuarioCriador(Usuario usuarioCriador) {
		this.usuarioCriador = usuarioCriador;
	}
	

}
