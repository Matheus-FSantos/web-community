package br.senac.sp.blog.model.service;

import br.senac.sp.blog.model.domain.Category;
import br.senac.sp.blog.model.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> get() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id){
        if(categoryRepository.existsById(id))
            return categoryRepository.findById(id).get();

        return null;
    }

    @Transactional
    public Category post(Category category) {
        category.updateCreationDate(LocalDateTime.now());
        category.updateUpdateDate(LocalDateTime.now());

        return categoryRepository.save(category);
    }

    @Transactional
    public Boolean delete(Long id) {
        if(!categoryRepository.existsById(id))
            return false;

        categoryRepository.deleteById(id);
        return true;
    }

    @Transactional
    public Boolean put(Category newCategory) {
        if(this.getById(newCategory.getId()) != null) {
            Category category = this.getById(newCategory.getId());
            this.modify(category, newCategory);
            categoryRepository.save(category);

            return true;
        }
        return false;
    }

    public void modify(Category category, Category newCategory) {
        category.updateUpdateDate(LocalDateTime.now());
        category.updateName(newCategory.getName());
    }
}
