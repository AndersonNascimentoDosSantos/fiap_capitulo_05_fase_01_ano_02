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
@Table(name="tbl_postagem_rede_social")
public class PostagemRedeSocial {
    @Id
    @SequenceGenerator(name="post_rede",sequenceName="seq_post_rede",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="post_rede")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_rede_social", nullable = false)
    private RedeSocial redeSocial;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
    
    @Column(name="ds_messagem",nullable = false,columnDefinition = "CLOB")    
    private String mensagem;
    
    @Column(name="dt_data_postagem",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPostagem;
    
    
    
    
    
    public PostagemRedeSocial() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PrePersist
    protected void onCreate() {
    	this.dataPostagem = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RedeSocial getRedeSocial() {
		return redeSocial;
	}

	public void setRedeSocial(RedeSocial redeSocial) {
		this.redeSocial = redeSocial;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
    
}
