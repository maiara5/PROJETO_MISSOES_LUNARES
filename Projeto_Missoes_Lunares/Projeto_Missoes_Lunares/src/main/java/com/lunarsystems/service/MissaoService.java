package com.lunarsystems.service;

import com.lunarsystems.model.Missao;
import com.lunarsystems.repository.NitriteRepository;
import com.lunarsystems.repository.SerializacaoRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

public class MissaoService implements AutoCloseable {
    private final SerializacaoRepository serialRepo;
    private final NitriteRepository nitriteRepo;

    public MissaoService(Path storageDir, Path nitriteFile) throws IOException {
        this.serialRepo = new SerializacaoRepository(storageDir);
        this.nitriteRepo = new NitriteRepository(nitriteFile);
    }

    public void cadastrarMissao(Missao m) throws IOException {
        if (m.getCodigo() == null || m.getCodigo().isBlank()) {
            m.setCodigo(UUID.randomUUID().toString());
        }
        serialRepo.salvarMissao(m);
        nitriteRepo.salvarMissao(m);
    }

    public List<Missao> listarMissao() {
        return nitriteRepo.listarMissao();
    }

    public Missao buscarPorCodigo(String codigo) {
        return nitriteRepo.buscarPorCodigo(codigo);
    }

    @Override
    public void close() throws Exception {
        nitriteRepo.close(); 
    }
}
