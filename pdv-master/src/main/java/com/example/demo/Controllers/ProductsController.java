package com.example.demo.Controllers;

import com.example.demo.DTOS.ProductRecordDto;
import com.example.demo.Model.Products;
import com.example.demo.Repository.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository productsRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public ResponseEntity<Products> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var products = new Products();
        BeanUtils.copyProperties(productRecordDto, products);
        System.out.println(products.getCodigodebarras());
        return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(products));
    }

    @GetMapping
    public ResponseEntity<?> getProductsByName(@RequestParam(value = "name", required = false) String name) {
            try {
                List<Products> products = productsRepository.findByNameContaining(name);
                if (!products.isEmpty()) {
                    return ResponseEntity.ok(products); // Retorna 200 OK se encontrar produtos
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body("Produto com nome " + name + " não encontrado"); // Retorna 404 se não encontrar
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao buscar produtos: " + e.getMessage()); // Retorna 500 se houver erro interno
            }
    }

    @GetMapping("/cod")
    public ResponseEntity<?> getProductsByCod(@RequestParam(value = "codigodebarras") String codigodebarras) {
        try {
            Optional<Products> product = productsRepository.findByCodigodebarras(codigodebarras);
            if (product.isPresent()) {
                return ResponseEntity.ok(product.get()); // Retorna 200 OK com o produto encontrado
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Produto com código " + codigodebarras + " não encontrado"); // Retorna 404 se não encontrar
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar produto: " + e.getMessage()); // Retorna 500 se houver erro interno
        }
    }






    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Integer id) {
        Optional<Products> product = productsRepository.findById(id);

        return product.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
