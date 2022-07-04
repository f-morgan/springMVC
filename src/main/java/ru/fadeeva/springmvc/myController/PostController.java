package ru.fadeeva.springmvc.myController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.fadeeva.springmvc.exception.NotFoundException;
import ru.fadeeva.springmvc.model.Post;
import ru.fadeeva.springmvc.myService.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException() {
        return "post not found";
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) {
        try {
            return service.getById(id);
        } catch (NotFoundException exception) {
            throw new NotFoundException();
        }
    }

    @PostMapping
    public Post save(@RequestBody Post post) {
        if (post.getId() != 0) {
            getById(post.getId());
        }
        return service.save(post);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        service.removeById(id);
    }
}
