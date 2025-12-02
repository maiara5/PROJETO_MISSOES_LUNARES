package com.lunarsystems.view;

import com.lunarsystems.model.*;
import com.lunarsystems.service.MissaoService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Main {
    private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Path storage = Path.of("./data/bin").toAbsolutePath();
        Path nitriteFile = Path.of("./data/nitrite.db").toAbsolutePath();

        try (MissaoService service = new MissaoService(storage, nitriteFile)) {
            while (true) {
                System.out.println("\n SISTEMA LUNAR - MENU PRINCIPAL ");
                System.out.println("1 - Criar nova missão");
                System.out.println("2 - Listar missões cadastradas");
                System.out.println("3 - Visualizar missão por código");
                System.out.println("4 - Sair do sistema");
                System.out.print("Escolha uma opção: ");
                String opt = readLineSafe();
                if (opt == null)
                    break;

                switch (opt) {
                    case "1" -> criarMissao(service);
                    case "2" -> listar(service);
                    case "3" -> visualizar(service);
                    case "4" -> {
                        System.out.println("Saindo do Sistema Lunar. Adeus!");
                        return;
                    }
                    default -> System.out.println("Opção inválida. Por favor, escolha um número de 1 a 4.");
                }
            }
        } catch (Exception e) {
            System.err.println("\nOcorreu um erro fatal no sistema:");
            e.printStackTrace();
        }
    }

    /**
     * Helper para ler uma linha do console com tratamento de IOException.
     */
    private static String readLineSafe() {
        try {
            String line = in.readLine();
            return (line != null) ? line.trim() : null;
        } catch (IOException e) {
            System.err.println("Erro de leitura do console: " + e.getMessage());
            return null;
        }
    }

    /**
     * Helper para ler e validar um número inteiro positivo (ou zero) a partir do
     * console.
     */
    private static int readPositiveInt(String prompt, String errorMsg, boolean allowZero) {
        int value = -1;
        while (value < (allowZero ? 0 : 1)) {
            System.out.print(prompt);
            String input = readLineSafe();
            if (input == null || input.isEmpty()) {
                System.out.println("O valor não pode ser vazio.");
                continue;
            }
            try {
                value = Integer.parseInt(input);
                if (value < (allowZero ? 0 : 1)) {
                    System.out.println(errorMsg);
                }
            } catch (NumberFormatException e) {
                System.out
                        .println("Entrada inválida. Por favor, insira um número inteiro.");
                value = -1; // Força a repetição
            }
        }
        return value;
    }

    /**
     * Helper para ler e validar uma data no formato YYYY-MM-DD.
     */
    private static LocalDate readDate(String prompt) {
        LocalDate date = null;
        while (date == null) {
            System.out.print(prompt);
            String input = readLineSafe();
            if (input == null || input.isEmpty()) {
                System.out.println("A data não pode ser vazia.");
                continue;
            }
            try {
                date = LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Por favor, use o formato YYYY-MM-DD.");
            }
        }
        return date;
    }

    /**
     * Coleta dados do usuário para criar uma nova missão, incluindo
     * validação de formato de data, ordem cronológica e entradas numéricas.
     * 
     * @param service O serviço de missões.
     */
    private static void criarMissao(MissaoService service) {
        System.out.println("\n=== ➕ Criar Nova Missão ===");
        System.out.print("Código (enter para gerar): ");
        String codigo = readLineSafe();

        System.out.print("Nome da missão: ");
        String nome = readLineSafe();

        // VALIDAÇÃO: Data de Lançamento
        LocalDate lanc = readDate("Data lançamento (YYYY-MM-DD): ");

        // VALIDAÇÃO: Data de Retorno
        LocalDate ret = null;
        while (ret == null) {
            ret = readDate("Data retorno (YYYY-MM-DD): ");
            // Validação de Lógica: Retorno deve ser igual ou APÓS o Lançamento
            if (ret.isBefore(lanc)) {
                System.out
                        .println("Data de Retorno deve ser igual ou posterior à Data de Lançamento (" + lanc + ").");
                ret = null; // Força a repetição
            }
        }

        System.out.print("Destino: ");
        String destino = readLineSafe();

        System.out.print("Objetivo: ");
        String objetivo = readLineSafe();

        System.out.println("\n--- Dados da Nave ---");
        System.out.print("Nave - id: ");
        String nid = readLineSafe();

        System.out.print("Nave - nome: ");
        String nname = readLineSafe();

        String tipo;
        while (true) {
            System.out.print("Nave - tipo (T para tripulada / C para cargueira): ");
            tipo = readLineSafe();
            if (tipo != null && (tipo.equalsIgnoreCase("T") || tipo.equalsIgnoreCase("C")))
                break;
            System.out.println("Tipo de nave inválido. Use 'T' ou 'C'.");
        }

        // VALIDAÇÃO: Capacidade de Tripulantes
        int cap = readPositiveInt("Nave - capacidade de tripulantes: ", "Capacidade não pode ser negativa.", false);

        Nave nave = tipo.equalsIgnoreCase("T") ? new NaveTripulada(nid, nname, cap)
                : new NaveCargueira(nid, nname, cap);

        Missao m = new Missao(codigo, nome, lanc, ret, destino, objetivo, nave);

        System.out.println("\n=== Adicionar Astronautas ===");
        System.out.println("(Digite 'fim' no id para parar)");
        while (true) {
            System.out.print("Astronauta - id: ");
            String aid = readLineSafe();
            if (aid == null || aid.equalsIgnoreCase("fim"))
                break;

            System.out.print("Nome: ");
            String an = readLineSafe();

            // VALIDAÇÃO: Idade (mínimo 21)
            int idade = readPositiveInt("Idade(Idade mínima para astronautas é 21 anos): ", "Idade mínima para astronautas é 21 anos.", false);
            while (idade < 21) {
                idade = readPositiveInt("Idade(Idade mínima para astronautas é 21 anos): ", "Idade mínima para astronautas é 21 anos.", false);
            }

            System.out.print("Especialidade: ");
            String esp = readLineSafe();

            // VALIDAÇÃO: Horas de Voo
            int horas = readPositiveInt("Horas de voo: ", "Horas de voo não podem ser negativas.", true);

            try {
                m.adicionarAstronauta(new Austronauta(aid, an, idade, esp, horas));
                System.out.println("Astronauta adicionado.");
            } catch (Exception ex) {
                System.out.println("Erro ao adicionar astronauta: " + ex.getMessage());
            }
        }

        try {
            service.cadastrarMissao(m);
            System.out.println("\nMissão **" + m.getNome() + "** (" + m.getCodigo()
                    + ") cadastrada com sucesso! Lançamento: " + m.getDataLancamento());
        } catch (Exception e) {
            System.out.println("\nErro ao cadastrar missão: " + e.getMessage());
        }

    }

    private static void listar(MissaoService service) {
        List<Missao> list = service.listarMissao();
        if (list.isEmpty()) {
            System.out.println("\nNenhuma missão cadastrada.");
        } else {
            System.out.println("\n=== Lista de Missões Cadastradas (" + list.size() + ") ===");
            System.out.println("--------------------------------------------------------------------------------");
            list.forEach(m -> {
                long duracaoDias = (m.duracao() != null) ? m.duracao().toDays() : 0;

                System.out.printf("  [CÓDIGO: %s] | NOME: %s\n", m.getCodigo(), m.getNome());
                System.out.printf("  > Destino: %s | Lançamento: %s | Duração: %d dias\n",
                        m.getDestino(), m.getDataLancamento(), duracaoDias);
                System.out.println("--------------------------------------------------------------------------------");
            });
            System.out.println("================================================================================\n");
        }
    }

    private static void visualizar(MissaoService service) {
        System.out.print("Código da missão: ");
        String codigo = readLineSafe();

        if (codigo == null || codigo.isBlank()) {
            System.out.println("O código da missão não pode ser vazio.");
            return;
        }

        Missao m = service.buscarPorCodigo(codigo);

        if (m == null) {
            System.out.println("\nMissão não encontrada para o código: " + codigo);
        } else {
            System.out.println("\n=== 🛰️ Detalhes da Missão: " + m.getNome() + " ===");
            System.out.println("CÓDIGO: " + m.getCodigo());
            System.out.println("DESTINO: " + m.getDestino());
            System.out.println("OBJETIVO: " + m.getObjetivo());
            System.out.println("LANÇAMENTO: " + m.getDataLancamento());
            System.out.println("RETORNO: " + m.getDataRetorno());

            long duracaoDias = (m.duracao() != null) ? m.duracao().toDays() : 0;
            System.out.println("DURAÇÃO TOTAL: " + duracaoDias + " dias");

            // Detalhes da Nave
            System.out.println("\n--- Nave Designada ---");
            Nave nave = m.getNave();
            System.out.println("ID: " + nave.getId());
            System.out.println("NOME: " + nave.getNome());

            if (nave instanceof NaveTripulada nt) {
                System.out.println("TIPO: Tripulada");
                System.out.println("CAPACIDADE DE TRIPULANTES: " + nt.getCapacidadeTripulantes());
            } else if (nave instanceof NaveCargueira nc) {
                System.out.println("TIPO: Cargueira");
                System.out.println("CAPACIDADE MÁXIMA (simulada): " + nc.getCapacidadeTripulantes());
            }

            // Detalhes da Tripulação
            List<Austronauta> tripulacao = m.getTripulacao();
            System.out.println("\n--- Tripulação (" + tripulacao.size() + " membros) ---");
            if (tripulacao.isEmpty()) {
                System.out.println("Nenhuma tripulação cadastrada para esta missão.");
            } else {
                for (Austronauta a : tripulacao) {
                    System.out.printf("  > ID: %s | NOME: %s | IDADE: %d | ESP: %s | HORAS DE VOO: %d\n",
                            a.getId(), a.getNome(), a.getIdade(), a.getEspecialidade(), a.getHorasVoo());
                }
            }
            System.out.println("========================================\n");
        }
    }
}

