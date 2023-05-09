package br.senac.sp.blog;

import br.senac.sp.blog.model.domain.Category;
import br.senac.sp.blog.model.domain.Comment;
import br.senac.sp.blog.model.domain.Post;
import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.repository.UserRepository;
import br.senac.sp.blog.model.service.CategoryService;
import br.senac.sp.blog.model.service.CommentService;
import br.senac.sp.blog.model.service.PostService;
import br.senac.sp.blog.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BlogApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired UserService userService,
			@Autowired UserRepository userRepository,
			@Autowired PostService postService,
			@Autowired CommentService commentService,
			@Autowired CategoryService categoryService
	){
		return args -> {
			System.out.println("Salvando Usuários...");
			User u1 = userService.post(new User(null, "Matheus Ferreira Santos", "matheus.fs.contato@gmail.com", "1234", LocalDateTime.now(), LocalDateTime.now()));
			User u2 = userService.post(new User(null, "Lucas Ryu Muraoka", "lucasMuraoka@gmail.com", "1234", LocalDateTime.now(), LocalDateTime.now()));
			User u3 = userService.post(new User(null, "Gustavo Aquino", "gustavoAquino@gmail.com", "1234", LocalDateTime.now(), LocalDateTime.now()));
			User u4 = userService.post(new User(null, "Matheus Amaral", "matheusAmaral@gmail.com", "1234", LocalDateTime.now(), LocalDateTime.now()));

			Category c1 = new Category(null, "E-Sports", LocalDateTime.now(), LocalDateTime.now());
			categoryService.post(c1);

			System.out.println("Salvando Postagens...");
			postService.post(new Post(null, "LOUD eliminada do MSI", "A ORG LOUD acaba de ser eliminada no MSI, por 2x0 na MD3", u1, LocalDateTime.now(), LocalDateTime.now(), c1));

			System.out.println("Salvando Comentários...");
			commentService.post(new Comment(null, "Que tristeza...", LocalDateTime.now(), LocalDateTime.now(), u2, postService.getById(1L)));
			commentService.post(new Comment(null, "Hahahaha faz o L", LocalDateTime.now(), LocalDateTime.now(), u3, postService.getById(1L)));
			commentService.post(new Comment(null, "O Brasil, um dia, irá ganhar um mundial!!!", LocalDateTime.now(), LocalDateTime.now(), u4, postService.getById(1L)));

			u1.updatePoints(u1.getPoints() + 5); //atualiza os pontos (regra de negocio)
			u2.updatePoints(u2.getPoints() + 3);
			u3.updatePoints(u3.getPoints() + 3);
			u4.updatePoints(u4.getPoints() + 3);
			userRepository.save(u1);
			userRepository.save(u2);
			userRepository.save(u3);
			userRepository.save(u4);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
