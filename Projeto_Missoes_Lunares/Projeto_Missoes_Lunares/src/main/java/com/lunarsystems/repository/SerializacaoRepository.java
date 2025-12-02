package com.lunarsystems.repository;

import com.lunarsystems.model.Missao;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SerializacaoRepository {
    private final Path storageDir;

    public SerializacaoRepository(Path storageDir) throws IOException {
        this.storageDir = storageDir;
        if (!Files.exists(storageDir)) Files.createDirectories(storageDir);
    }

    public void salvarMissao(Missao m) throws IOException {
        Path file = storageDir.resolve(m.getCodigo() + ".bin");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file.toFile()))) {
            oos.writeObject(m);
        }
    }

    public Missao carregarMissao(String codigo) throws IOException, ClassNotFoundException {
        Path file = storageDir.resolve(codigo + ".bin");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file.toFile()))) {
            return (Missao) ois.readObject();
        }
    }

    public List<Missao> listarMissao() throws IOException {
        List<Missao> list = new ArrayList<>();
        try (var stream = Files.list(storageDir)) {
            stream.filter(p -> p.toString().endsWith(".bin")).forEach(p -> {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(p.toFile()))) {
                    list.add((Missao) ois.readObject());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return list;
    }
}
