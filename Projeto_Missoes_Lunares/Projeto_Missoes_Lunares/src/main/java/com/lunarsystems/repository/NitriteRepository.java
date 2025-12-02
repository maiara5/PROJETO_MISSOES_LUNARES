
package com.lunarsystems.repository;

import com.lunarsystems.model.Missao;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.nio.file.Path;
import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.*;

public class NitriteRepository implements AutoCloseable {
    private final Nitrite db;
    private final ObjectRepository<Missao> repo;

    public NitriteRepository(Path file) {
        this.db = Nitrite.builder()
                .filePath(file.toString())
                .openOrCreate();
        this.repo = db.getRepository(Missao.class);
    }

    public void salvarMissao(Missao m) {
        repo.insert(m); 
    }

    public List<Missao> listarMissao() {
        return repo.find().toList();
    }

    public Missao buscarPorCodigo(String codigo) {
        return repo.find(eq("codigo", codigo)).firstOrDefault();
    }

    @Override
    public void close() {
        if (!db.isClosed()) {
            db.commit(); 
            db.close();
        }
    }
}



