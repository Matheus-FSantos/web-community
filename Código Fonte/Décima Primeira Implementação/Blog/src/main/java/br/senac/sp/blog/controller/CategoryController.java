package br.senac.sp.blog.controller;

import br.senac.sp.blog.model.domain.Category;

import br.senac.sp.blog.model.exception.BlogException;
import br.senac.sp.blog.model.service.CategoryService;
import br.senac.sp.blog.model.validation.CategoryValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> get() {
        if(!categoryService.get().isEmpty())
            return ResponseEntity.ok().body(categoryService.get());

        return ResponseEntity.notFound().build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<Category> getById(@RequestParam("id") Long id) {
        if(categoryService.getById(id) != null)
            return ResponseEntity.ok().body(categoryService.getById(id));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody Category newCategory) throws BlogException {

        CategoryValidation.validation(newCategory);

        try {
            categoryService.post(newCategory);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCategory.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new BlogException("DataIntegrityViolationException", "This category has already bean registered");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody Category newCategory) throws BlogException {

        CategoryValidation.validation(newCategory);
        newCategory.updateId(id);

        try {
            if(categoryService.put(newCategory))
                return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new BlogException("DataIntegrityViolationException", "This category has already bean registered");
        }

        throw new BlogException("Category", "Category code is invalid!!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws BlogException {
        if(categoryService.delete(id))
            return ResponseEntity.noContent().build();

        throw new BlogException("Category", "Category code is invalid!!!");
    }
}
