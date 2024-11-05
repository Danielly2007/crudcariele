package PerfumeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.suaempresa.perfumaria.PerfumeRepository;

import java.util.List;

public class PerfumeController {
@RestController
@RequestMapping("/api/perfumes")


    @Autowired
    private PerfumeRepository perfumeRepository;

    @PostMapping
    public Perfume createPerfume(@RequestBody Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    @GetMapping
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> getPerfumeById(@PathVariable Long id) {
        return perfumeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfume> updatePerfume(@PathVariable Long id, @RequestBody Perfume perfumeDetails) {
        return perfumeRepository.findById(id)
                .map(perfume -> {
                    perfume.setNome(perfumeDetails.getNome());
                    perfume.setDescricao(perfumeDetails.getDescricao());
                    perfume.setPreco(perfumeDetails.getPreco());
                    perfume.setEstoque(perfumeDetails.getEstoque());
                    return ResponseEntity.ok(perfumeRepository.save(perfume));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerfume(@PathVariable Long id) {
        return perfumeRepository.findById(id)
                .map(perfume -> {
                    perfumeRepository.delete(perfume);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
}
