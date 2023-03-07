package br.com.fiap.Entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_empresa_reclamacao")
public class EmpresaReclamacao {

	@Id
	@SequenceGenerator(name="emp_reclamacao",sequenceName="seq_emp_reclamacao",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="emp_reclamacao")
	@Column(name="id_empresa_reclamacao")
	private Long idEmpresaReclamacao;

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	private Empresa empresa;

	@ManyToOne
	@JoinColumn(name = "id_reclamacao")
	private Reclamacao reclamacao;

	
	
	
	public EmpresaReclamacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdEmpresaReclamacao() {
		return idEmpresaReclamacao;
	}

	public void setIdEmpresaReclamacao(Long idEmpresaReclamacao) {
		this.idEmpresaReclamacao = idEmpresaReclamacao;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Reclamacao getReclamacao() {
		return reclamacao;
	}

	public void setReclamacao(Reclamacao reclamacao) {
		this.reclamacao = reclamacao;
	}

	

}
