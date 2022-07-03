package ru.fadeeva.springmvc.myService;

import org.springframework.stereotype.Service;
import ru.fadeeva.springmvc.exception.NotFoundException;
import ru.fadeeva.springmvc.model.Post;
import ru.fadeeva.springmvc.myRepository.PostRepositoryImpl;
import java.util.List;

@Service
public class PostService {
    private final PostRepositoryImpl repository;

    public PostService(PostRepositoryImpl repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        return repository.getById(id).orElseThrow(NotFoundException::new);
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        repository.removeById(id);
    }
}

