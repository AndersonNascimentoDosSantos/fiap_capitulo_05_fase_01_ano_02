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
@Table(name = "tbl_mensagem_forum")
public class MensagemForum {

    @Id
    @SequenceGenerator(name="forum",sequenceName="seq_forum",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="forum")
    @Column(name="id_mensagem")
    private Long idMensagem;
    
    @ManyToOne
    @JoinColumn(name = "id_topico")
    private TopicoForum topico;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
    @Column(name = "ds_conteudo", columnDefinition = "CLOB")
    private String conteudo;
    
    @Column(name = "dt_data_postagem")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPostagem;
    
    
    
    
    public MensagemForum() {
		super();
		// TODO Auto-generated constructor stub
	}

	@PrePersist
    protected void onCreate() {
    	this.dataPostagem = new Date();
    }
    // getters and setters

	public Long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

	public TopicoForum getTopico() {
		return topico;
	}

	public void setTopico(TopicoForum topico) {
		this.topico = topico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
}
