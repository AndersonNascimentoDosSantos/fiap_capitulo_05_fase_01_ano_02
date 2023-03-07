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
@Table(name="tbl_mensagem_chat")
public class MensagemChat {
    @Id
    @SequenceGenerator(name="msg_seq",sequenceName="seq_msg",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="msg_seq")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_remetente", nullable = false)
    private Usuario remetente;
    
    @ManyToOne
    @JoinColumn(name = "id_destinatario", nullable = false)
    private Empresa destinatario;
    
    @Column(name="ds_conteudo",nullable = false,columnDefinition = "CLOB")
    private String conteudo;
    
    @Column(name="dt_data_envio",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnvio;
    
    
    
    
    public MensagemChat() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PrePersist
    protected void onCreate() {
    	this.dataEnvio = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public Empresa getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Empresa destinatario) {
		this.destinatario = destinatario;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
    
    
    
    // constructors, getters, setters
}
