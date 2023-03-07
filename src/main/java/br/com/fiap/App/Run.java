package br.com.fiap.App;

import java.util.List;
import java.util.Scanner;

import br.com.fiap.Entity.Empresa;
//import br.com.fiap.Entity.EmpresaReclamacao;
import br.com.fiap.Entity.Usuario;
import br.com.fiap.Enums.TipoUsuario;
import br.com.fiap.Prompt.EmpresaPrompt;
//import br.com.fiap.Prompt.EmpresaReclamacaoPrompt;
import br.com.fiap.Prompt.UsuarioPrompt;
import br.com.fiap.Repository.EmpresaDAO;
//import br.com.fiap.Repository.EmpresaReclamacaoDAO;
import br.com.fiap.Repository.UsuarioDAO;

public class Run {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int choice = -1;
		while (choice != 0) {
			System.out.println("Please select an option:");
			System.out.println("1. cadastre-se como usuario(USER) or usuario(EMPRESA)");
			System.out.println("2. Cadastre uma Empresa de prestação de serviço");
			System.out.println("3. para delatar o usuario fornecendo o ID");
			System.out.println("4. para efetuar update de informações");
			System.out.println("5. para listar todos os usuarios.");
			System.out.println("0. Exit");
			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				// code to sign up as a user
				System.out.println("You have selected option 1: Sign up as a user");
				Usuario usuario = new Usuario();
				UsuarioPrompt usuarioPrompt = new UsuarioPrompt(usuario);
				UsuarioDAO usuarioDao = new UsuarioDAO(Usuario.class);

				try {
					usuarioDao.save(usuarioPrompt.prompt());
					usuarioPrompt.toString();

				} catch (IllegalAccessException e) {

					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("You have selected option 2: Cadastro de compania de eltricidade");
				Empresa empresa = new Empresa();
				EmpresaDAO empresaDao = new EmpresaDAO(Empresa.class);
				EmpresaPrompt empresaPrompt = new EmpresaPrompt(empresa);
				try {
					empresaDao.save(empresaPrompt.prompt());
					empresaPrompt.toString();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				break;
			case 3:

				System.out.println("You have selected option 3: forneça o id do usuario criado anteriormente");
				UsuarioDAO usuarioDeleteDao = new UsuarioDAO(Usuario.class);
				Long id = (long) scanner.nextInt();
				scanner.nextLine(); // consume the newline character
				if (usuarioDeleteDao.deleteById(id)) {
					System.out.println("Usuário deletado com sucesso!");
				} else {
					System.out.println("Usuário não encontrado!");
				}
//				EmpresaReclamacaoDAO empresaReclamacaoDao = new EmpresaReclamacaoDAO(EmpresaReclamacao.class); 
//				
//				try {
//					empresaReclamacaoDao.save(empresaReclamacaoPrompt.prompt());
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

				break;

			case 4:
				Usuario usuarioUpdate = new Usuario();
				UsuarioDAO usuarioUpdateDao = new UsuarioDAO(Usuario.class);

				System.out.println("You have selected option 4: o usuario sera atualizado para as seguintes dados");
				System.out.println("Nome: joão henrique");
				System.out.println("Email: joaohenrique@gmail.com");
				System.out.println("Tipo Usuario: USER");
				System.out.println("Senha: 123456789");
				System.out.println("Forneça o id Do usuario:");
				Long idUp = (long) scanner.nextInt();
				scanner.nextLine(); // consume the newline character
				usuarioUpdate = usuarioUpdateDao.findById(idUp);
				usuarioUpdate.setEmail("joaohenrique@gmail.com");
				usuarioUpdate.setNome("joão henrique");
				TipoUsuario[] tipo = TipoUsuario.values();
				usuarioUpdate.setTipoUsuario(tipo[0]);
				usuarioUpdate.setSenha("123456789");
				usuarioUpdateDao.update(usuarioUpdate);
				usuarioUpdate.toString();

				break;
			case 5:
				System.out.println("You have selected option 5: listando todos os usuarios");
				UsuarioDAO usuarioSelectDao = new UsuarioDAO(Usuario.class);
				List<Usuario> entities = usuarioSelectDao.findAll();
				for (Usuario entity : entities) {
					System.out.println(entity);
//					entity.toString();
//					System.out.println("ID: " + entity.getIdUsuario());
//					System.out.println("Nome: " + entity.getNome());
//					System.out.println("Email: " + entity.getEmail());
//					System.out.println("Tipo Usuario: " + entity.getTipoUsuario());

					// print other attributes
				}
				break;
			case 0:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("Invalid option selected. Please try again.");
				break;
			}
			System.out.println();
		}
		scanner.close();
	}
}
