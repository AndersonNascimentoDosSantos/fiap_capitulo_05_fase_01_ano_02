package br.com.fiap.Entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Hibernate;
import org.mindrot.jbcrypt.BCrypt;

import br.com.fiap.Enums.TipoUsuario;

@Entity
@Table(name = "tbl_usuario")
//@NamedQueries({
//    @NamedQuery(name = "Usuario.findByIdWithReclamacoes",
//            query = "SELECT u FROM Usuario u JOIN FETCH u.reclamacoes WHERE u.idUsuario = :id"),
//    
//})
public class Usuario implements Serializable {
 
    private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name="usuario",sequenceName="seq_usuario",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usuario")
    @Column(name = "id_usuario")
    private Long idUsuario;
 
    @Column(name="nom_name",nullable = false)
    private String nome;
 
    @Column(name="ds_email",nullable = false, unique = true)
    private String email;
 
    @Column(name="key_senha",nullable = false)
    private String senha;
 
    @Enumerated(EnumType.STRING)
    @Column(name="enum_tipo_usuario",nullable = false)
    private TipoUsuario tipoUsuario;
 
    @OneToMany(mappedBy = "usuario",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reclamacao> reclamacoes;
 
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avaliacao> avaliacoes;
// 
    public Usuario() {
        // default constructor
    }
 
   


  
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + this.getIdUsuario() + ", nome=" +this.getNome() + ", email=" + this.getEmail() + ", senha=" + senha
				+ ", tipoUsuario=" + this.getTipoUsuario() +  "]";
	}





	public Usuario(String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
        setSenha(senha);
    }
 
//    @Override
//	public String toString() {
//		return "Usuario [idUsuario=" + idUsuario + ", nome=" + nome + ", email=" + email + ", senha=" + senha
//				+ ", tipoUsuario=" + tipoUsuario + ", reclamacoes=" + reclamacoes + ", avaliacoes=" + avaliacoes + "]";
//	}





	public Long getIdUsuario() {
        return idUsuario;
    }
 
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
 
    public String getNome() {
        return nome;
    }
 
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
 
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
 
    public List<Reclamacao> getReclamacoes() {
        return this.reclamacoes;
    }
 
    public void setReclamacoes(List<Reclamacao> reclamacoes) {
        this.reclamacoes = reclamacoes;
    }
 
    public List<Avaliacao> getAvaliacoes() {
        return this.avaliacoes;
    }
 
    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
 
    public void setSenha(String senha) {
        String hash = BCrypt.hashpw(senha, BCrypt.gensalt());
        this.senha = hash;
    }
 
    public boolean verificaSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }
    
    public static Usuario autenticar(String email, String password, EntityManager em) throws Exception {
        try {
            Usuario usuario = em.createNamedQuery("Usuario.findByEmail", Usuario.class)
                                .setParameter("email", email)
                                .getSingleResult();
            if (usuario.verificaSenha(password)) {
                return usuario; // authentication successful
            } else {
                throw new Exception("Invalid password for user " + email);
            }
        } catch (NoResultException e) {
            throw new Exception("User " + email + " not found");
        }
    }
}



